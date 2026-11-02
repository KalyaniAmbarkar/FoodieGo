package com.food.DAOImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.food.DAO.OrderItemDAO;
import com.food.model.OrderItem;
import com.food.utility.DBConnection;

public class OrderItemDAOImp implements OrderItemDAO {
	private static final String INSERT_QUERY = "INSERT INTO OrderItem(orderID, menuID, quantity, itemTotal) "
			+ "VALUES(?, ?, ?, ?)";

	private static final String SELECT_QUERY = "SELECT * FROM OrderItem WHERE orderItemID = ?";

	private static final String UPDATE_QUERY = "UPDATE OrderItem SET orderID = ?, menuID = ?, "
			+ "quantity = ?, itemTotal = ? WHERE orderItemID = ?";

	private static final String DELETE_QUERY = "DELETE FROM OrderItem WHERE orderItemID = ?";

	private static final String SELECT_ALL_QUERY = "SELECT * FROM OrderItem";

	@Override
	public boolean addOrderItem(OrderItem orderItem) {

		Connection connection = DBConnection.getConnection();

		if (connection == null) {
			return false;
		}

		try (PreparedStatement pstmt = connection.prepareStatement(INSERT_QUERY)) {

			pstmt.setInt(1, orderItem.getOrderID());
			pstmt.setInt(2, orderItem.getMenuID());
			pstmt.setInt(3, orderItem.getQuantity());
			pstmt.setBigDecimal(4, orderItem.getItemTotal());

			int rows = pstmt.executeUpdate();

			return rows == 1;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean addOrderItem(OrderItem orderItem, Connection connection) throws SQLException {

		try (PreparedStatement pstmt = connection.prepareStatement(INSERT_QUERY)) {

			pstmt.setInt(1, orderItem.getOrderID());
			pstmt.setInt(2, orderItem.getMenuID());
			pstmt.setInt(3, orderItem.getQuantity());
			pstmt.setBigDecimal(4, orderItem.getItemTotal());

			return pstmt.executeUpdate() == 1;
		}
	}

	@Override
	public OrderItem getOrderItem(int orderItemID) {

		OrderItem orderItem = null;
		Connection connection = DBConnection.getConnection();

		try {
			PreparedStatement pstmt = connection.prepareStatement(SELECT_QUERY);

			pstmt.setInt(1, orderItemID);

			ResultSet res = pstmt.executeQuery();

			if (res.next()) {
				orderItem = new OrderItem(res.getInt("orderItemID"), res.getInt("orderID"), res.getInt("menuID"),
						res.getInt("quantity"), res.getBigDecimal("itemTotal"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return orderItem;
	}

	@Override
	public void updateOrderItem(OrderItem orderItem) {

		Connection connection = DBConnection.getConnection();

		try {
			PreparedStatement pstmt = connection.prepareStatement(UPDATE_QUERY);

			pstmt.setInt(1, orderItem.getOrderID());
			pstmt.setInt(2, orderItem.getMenuID());
			pstmt.setInt(3, orderItem.getQuantity());
			pstmt.setBigDecimal(4, orderItem.getItemTotal());
			pstmt.setInt(5, orderItem.getOrderItemID());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteOrderItem(int orderItemID) {

		Connection connection = DBConnection.getConnection();

		try {
			PreparedStatement pstmt = connection.prepareStatement(DELETE_QUERY);

			pstmt.setInt(1, orderItemID);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<OrderItem> getAllOrderItems() {

		List<OrderItem> list = new ArrayList<OrderItem>();
		Connection connection = DBConnection.getConnection();

		try {
			Statement stmt = connection.createStatement();

			ResultSet res = stmt.executeQuery(SELECT_ALL_QUERY);

			while (res.next()) {
				OrderItem orderItem = new OrderItem(res.getInt("orderItemID"), res.getInt("orderID"),
						res.getInt("menuID"), res.getInt("quantity"), res.getBigDecimal("itemTotal"));

				list.add(orderItem);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}
}
