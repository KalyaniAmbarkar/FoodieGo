package com.food.Servlets;

import java.io.IOException;

import com.food.DAOImp.MenuDAOImp;
import com.food.model.Cart;
import com.food.model.CartItem;
import com.food.model.Menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/cartServlet")
public class CartServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();

		Cart cart = (Cart) session.getAttribute("cart");

		if (cart == null) {
			cart = new Cart();
			session.setAttribute("cart", cart);
		}

		String action = req.getParameter("action");

		if ("add".equals(action)) {
			addItemToCart(req, cart);

			
			String referer = req.getHeader("Referer");

			if (referer != null && !referer.isEmpty()) {
				resp.sendRedirect(referer);
			} else {
				resp.sendRedirect("callRestaurantServlet");
			}

		} else if ("update".equals(action)) {
			updateItemToCart(req, cart);
			resp.sendRedirect("cart.jsp");

		} else if ("delete".equals(action)) {
			deleteItemFromCart(req, cart);
			resp.sendRedirect("cart.jsp");

		} else {
			resp.sendRedirect("cart.jsp");
		}
	}

	private void addItemToCart(HttpServletRequest req, Cart cart) {
		int menuID = Integer.parseInt(req.getParameter("menuId"));
		int quantity = Integer.parseInt(req.getParameter("quantity"));

		MenuDAOImp menuDAOImp = new MenuDAOImp();
		Menu menu = menuDAOImp.getMenu(menuID);

		if (menu != null) {
			CartItem cartItem = new CartItem(menu.getMenuID(), menu.getRestaurantID(), menu.getItemName(),
					menu.getPrice(), quantity);

			cart.addItem(cartItem);
		}
	}

	private void updateItemToCart(HttpServletRequest req, Cart cart) {
		int menuID = Integer.parseInt(req.getParameter("menuId"));
		int quantity = Integer.parseInt(req.getParameter("quantity"));

		cart.updateItem(menuID, quantity);

	}

	private void deleteItemFromCart(HttpServletRequest req, Cart cart) {
		int menuID = Integer.parseInt(req.getParameter("menuId"));

		cart.removeItem(menuID);

	}

}
