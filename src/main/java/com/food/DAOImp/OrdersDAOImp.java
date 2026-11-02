package com.food.DAOImp;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.food.DAO.OrderDAO;
import com.food.model.Orders;
import com.food.utility.DBConnection;

public class OrdersDAOImp implements OrderDAO {

	private static final String INSERT_QUERY = "INSERT INTO Orders(userID, restaurantID, orderDate, totalAmount, status, paymentMethod) "
			+ "VALUES(?, ?, ?, ?, ?, ?)";

	private static final String SELECT_QUERY = "SELECT * FROM Orders WHERE orderID = ?";

	private static final String UPDATE_QUERY = "UPDATE Orders SET userID = ?, restaurantID = ?, orderDate = ?, "
			+ "totalAmount = ?, status = ?, paymentMethod = ? " + "WHERE orderID = ?";

	private static final String DELETE_QUERY = "DELETE FROM Orders WHERE orderID = ?";

	private static final String SELECT_ALL_QUERY = "SELECT * FROM Orders";

	@Override
	public int addOrder(Orders order) {
		int orderID = 0;

		Connection connection = DBConnection.getConnection();

		try {
			PreparedStatement pstmt = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);

			pstmt.setInt(1, order.getUserID());
			pstmt.setInt(2, order.getRestaurantID());

			if (order.getOrderDate() != null) {
				pstmt.setTimestamp(3, order.getOrderDate());
			} else {
				pstmt.setNull(3, java.sql.Types.TIMESTAMP);
			}

			pstmt.setBigDecimal(4, order.getTotalAmount());
			pstmt.setString(5, order.getStatus());
			pstmt.setString(6, order.getPaymentMethod());

			pstmt.executeUpdate();
			ResultSet res = pstmt.getGeneratedKeys();

			if (res.next()) {
				orderID = res.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderID;

	}

	public int addOrder(Orders order, Connection connection) throws SQLException {

		try (PreparedStatement pstmt = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {

			pstmt.setInt(1, order.getUserID());
			pstmt.setInt(2, order.getRestaurantID());

			if (order.getOrderDate() != null) {
				pstmt.setTimestamp(3, order.getOrderDate());
			} else {
				pstmt.setNull(3, java.sql.Types.TIMESTAMP);
			}

			pstmt.setBigDecimal(4, order.getTotalAmount());
			pstmt.setString(5, order.getStatus());
			pstmt.setString(6, order.getPaymentMethod());

			int rows = pstmt.executeUpdate();

			if (rows != 1) {
				return 0;
			}

			try (ResultSet res = pstmt.getGeneratedKeys()) {
				if (res.next()) {
					return res.getInt(1);
				}
			}
		}

		return 0;
	}

	@Override
	public Orders getOrder(int orderID) {

		Orders order = null;
		Connection connection = DBConnection.getConnection();

		try {
			PreparedStatement pstmt = connection.prepareStatement(SELECT_QUERY);

			pstmt.setInt(1, orderID);

			ResultSet res = pstmt.executeQuery();

			if (res.next()) {
				order = new Orders(res.getInt("userID"), res.getInt("restaurantID"), res.getTimestamp("orderDate"),
						res.getBigDecimal("totalAmount"), res.getString("status"), res.getString("paymentMethod"));

				order.setOrderID(res.getInt("orderID"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return order;
	}

	@Override
	public void updateOrder(Orders order) {

		Connection connection = DBConnection.getConnection();

		try {
			PreparedStatement pstmt = connection.prepareStatement(UPDATE_QUERY);

			pstmt.setInt(1, order.getUserID());
			pstmt.setInt(2, order.getRestaurantID());

			if (order.getOrderDate() != null) {
				pstmt.setTimestamp(3, order.getOrderDate());
			} else {
				pstmt.setNull(3, java.sql.Types.TIMESTAMP);
			}

			pstmt.setBigDecimal(4, order.getTotalAmount());
			pstmt.setString(5, order.getStatus());
			pstmt.setString(6, order.getPaymentMethod());
			pstmt.setInt(7, order.getOrderID());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteOrder(int orderID) {

		Connection connection = DBConnection.getConnection();

		try {
			PreparedStatement pstmt = connection.prepareStatement(DELETE_QUERY);

			pstmt.setInt(1, orderID);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Orders> getAllOrders() {

		List<Orders> list = new ArrayList<Orders>();
		Connection connection = DBConnection.getConnection();

		try {
			Statement stmt = connection.createStatement();

			ResultSet res = stmt.executeQuery(SELECT_ALL_QUERY);

			while (res.next()) {

				Orders order = new Orders(res.getInt("userID"), res.getInt("restaurantID"),
						res.getTimestamp("orderDate"), res.getBigDecimal("totalAmount"), res.getString("status"),
						res.getString("paymentMethod"));

				order.setOrderID(res.getInt("orderID"));

				list.add(order);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}
}
