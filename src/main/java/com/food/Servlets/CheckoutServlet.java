
package com.food.Servlets;

import com.food.DAOImp.OrderHistoryDAOImp;
import com.food.model.OrderHistory;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.food.DAOImp.OrderItemDAOImp;
import com.food.DAOImp.OrdersDAOImp;
import com.food.model.Cart;
import com.food.model.CartItem;
import com.food.model.OrderItem;
import com.food.model.Orders;
import com.food.model.User;
import com.food.utility.DBConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();

		// 1. Check whether the user is logged in
		User user = (User) session.getAttribute("user");

		if (user == null) {
			session.setAttribute("redirectPage", "checkout.jsp");
			resp.sendRedirect("login.html");
			return;
		}

		// 2. Get the cart and payment method
		Cart cart = (Cart) session.getAttribute("cart");
		String paymentMode = req.getParameter("paymentMode");

		// 3. Validate the cart
		if (cart == null || cart.getItems().isEmpty()) {
			resp.sendRedirect("cart.jsp");
			return;
		}

		// 4. Validate the payment method
		if (paymentMode == null || paymentMode.trim().isEmpty()) {
			resp.sendRedirect("cart.jsp");
			return;
		}

		// 5. Group cart items by restaurant ID
		Map<Integer, List<CartItem>> restaurantItems = new LinkedHashMap<>();

		for (CartItem item : cart.getItems().values()) {
			int restaurantID = item.getRestaurantID();

			restaurantItems.computeIfAbsent(restaurantID, k -> new ArrayList<CartItem>()).add(item);
		}

		// 6. Create DAO objects
		OrdersDAOImp ordersDAOImp = new OrdersDAOImp();
		OrderItemDAOImp orderItemDAOImp = new OrderItemDAOImp();

		OrderHistoryDAOImp orderHistoryDAOImp = new OrderHistoryDAOImp();

		List<Integer> orderIDs = new ArrayList<>();
		boolean checkoutSuccessful = false;

		// 7. Get ONE connection for the entire checkout
		try (Connection connection = DBConnection.getConnection()) {

			if (connection == null) {
				resp.sendRedirect("cart.jsp");
				return;
			}

			// Disable automatic saving until everything succeeds
			connection.setAutoCommit(false);

			try {

				// 8. Create an order for each restaurant
				for (Map.Entry<Integer, List<CartItem>> entry : restaurantItems.entrySet()) {

					int restaurantID = entry.getKey();
					List<CartItem> items = entry.getValue();

					// Calculate this restaurant's total
					BigDecimal restaurantTotal = BigDecimal.ZERO;

					for (CartItem item : items) {
						restaurantTotal = restaurantTotal.add(item.getTotalPrice());
					}

					// Create the order
					Orders order = new Orders();

					order.setUserID(user.getUserId());
					order.setRestaurantID(restaurantID);
					order.setOrderDate(new Timestamp(System.currentTimeMillis()));
					order.setTotalAmount(restaurantTotal);
					order.setStatus("pending");
					order.setPaymentMethod(paymentMode);

					// Save using the SAME connection
					int orderID = ordersDAOImp.addOrder(order, connection);

					if (orderID <= 0) {
						throw new SQLException("Failed to save the order.");
					}

					OrderHistory history = new OrderHistory(orderID, user.getUserId(), "pending",
							new Timestamp(System.currentTimeMillis()));

					orderHistoryDAOImp.addOrderHistory(history, connection);

					// Store every order ID for confirmation
					orderIDs.add(orderID);

					// 9. Save the items belonging to this order
					for (CartItem item : items) {

						OrderItem orderItem = new OrderItem();

						orderItem.setOrderID(orderID);
						orderItem.setMenuID(item.getMenuID());
						orderItem.setQuantity(item.getQuantity());
						orderItem.setItemTotal(item.getTotalPrice());

						boolean itemSaved = orderItemDAOImp.addOrderItem(orderItem, connection);

						if (!itemSaved) {
							throw new SQLException("Failed to save an order item.");
						}
					}
				}

				// 10. Save all orders and items together
				connection.commit();
				checkoutSuccessful = true;

			} catch (SQLException e) {

				// Undo all database changes if any operation fails
				try {
					connection.rollback();
				} catch (SQLException rollbackError) {
					e.addSuppressed(rollbackError);
				}

				e.printStackTrace();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 11. Redirect if checkout failed
		if (!checkoutSuccessful) {
			resp.sendRedirect("cart.jsp");
			return;
		}

		// 12. Clear the cart only after successful commit
		session.removeAttribute("cart");
		session.removeAttribute("restaurantID");
		session.removeAttribute("finalAmount");

		// 13. Redirect to the confirmation page
		// Store all order IDs and redirect to confirmation page
		session.setAttribute("checkoutOrderIDs", orderIDs);

		resp.sendRedirect("orderConfirmation.jsp?paymentMethod="
				+ java.net.URLEncoder.encode(paymentMode, java.nio.charset.StandardCharsets.UTF_8));
	}
}
