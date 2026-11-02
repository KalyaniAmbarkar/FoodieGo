
package com.food.DAOImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.food.DAO.OrderHistoryDAO;
import com.food.model.OrderHistory;
import com.food.utility.DBConnection;

public class OrderHistoryDAOImp implements OrderHistoryDAO {

	private static final String SELECT_BY_USER_ID_QUERY = "SELECT * FROM OrderHistory WHERE userID = ? ORDER BY changedAt DESC";

	private static final String INSERT_QUERY = "INSERT INTO OrderHistory (orderID, userID, status, changedAt) "
			+ "VALUES (?, ?, ?, ?)";

	private static final String SELECT_QUERY = "SELECT * FROM OrderHistory WHERE orderHistoryID = ?";

	private static final String UPDATE_QUERY = "UPDATE OrderHistory SET orderID = ?, userID = ?, "
			+ "status = ?, changedAt = ? WHERE orderHistoryID = ?";

	private static final String DELETE_QUERY = "DELETE FROM OrderHistory WHERE orderHistoryID = ?";

	private static final String SELECT_ALL_QUERY = "SELECT * FROM OrderHistory";

	// Helper method to set a nullable timestamp
	private void setChangedAt(PreparedStatement pstmt, int index, Timestamp changedAt) throws SQLException {

		if (changedAt != null) {
			pstmt.setTimestamp(index, changedAt);
		} else {
			pstmt.setNull(index, Types.TIMESTAMP);
		}
	}

	// Add order history
	@Override
	public void addOrderHistory(OrderHistory orderHistory) {

		try (Connection connection = DBConnection.getConnection()) {

			if (connection == null) {
				return;
			}

			try (PreparedStatement pstmt = connection.prepareStatement(INSERT_QUERY)) {

				pstmt.setInt(1, orderHistory.getOrderID());
				pstmt.setInt(2, orderHistory.getUserID());
				pstmt.setString(3, orderHistory.getStatus());
				setChangedAt(pstmt, 4, orderHistory.getChangedAt());

				pstmt.executeUpdate();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Get order history by ID
	@Override
	public OrderHistory getOrderHistory(int orderHistoryID) {

		try (Connection connection = DBConnection.getConnection()) {

			if (connection == null) {
				return null;
			}

			try (PreparedStatement pstmt = connection.prepareStatement(SELECT_QUERY)) {

				pstmt.setInt(1, orderHistoryID);

				try (ResultSet res = pstmt.executeQuery()) {

					if (res.next()) {
						OrderHistory history = new OrderHistory(res.getInt("orderID"), res.getInt("userID"),
								res.getString("status"), res.getTimestamp("changedAt"));

						history.setOrderHistoryID(res.getInt("orderHistoryID"));

						return history;
					}
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	// Update order history
	@Override
	public void updateOrderHistory(OrderHistory orderHistory) {

		try (Connection connection = DBConnection.getConnection()) {

			if (connection == null) {
				return;
			}

			try (PreparedStatement pstmt = connection.prepareStatement(UPDATE_QUERY)) {

				pstmt.setInt(1, orderHistory.getOrderID());
				pstmt.setInt(2, orderHistory.getUserID());
				pstmt.setString(3, orderHistory.getStatus());
				setChangedAt(pstmt, 4, orderHistory.getChangedAt());
				pstmt.setInt(5, orderHistory.getOrderHistoryID());

				pstmt.executeUpdate();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Delete order history
	@Override
	public void deleteOrderHistory(int orderHistoryID) {

		try (Connection connection = DBConnection.getConnection()) {

			if (connection == null) {
				return;
			}

			try (PreparedStatement pstmt = connection.prepareStatement(DELETE_QUERY)) {

				pstmt.setInt(1, orderHistoryID);
				pstmt.executeUpdate();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Get all order history records
	@Override
	public List<OrderHistory> getAllOrderHistory() {

		List<OrderHistory> list = new ArrayList<>();

		try (Connection connection = DBConnection.getConnection()) {

			if (connection == null) {
				return list;
			}

			try (Statement stmt = connection.createStatement(); ResultSet res = stmt.executeQuery(SELECT_ALL_QUERY)) {

				while (res.next()) {
					OrderHistory history = new OrderHistory(res.getInt("orderID"), res.getInt("userID"),
							res.getString("status"), res.getTimestamp("changedAt"));

					history.setOrderHistoryID(res.getInt("orderHistoryID"));

					list.add(history);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	// Get order history by user ID
	@Override
	public List<OrderHistory> getOrderHistoryByUserID(int userID) {

		List<OrderHistory> list = new ArrayList<>();

		try (Connection connection = DBConnection.getConnection()) {

			if (connection == null) {
				return list;
			}

			try (PreparedStatement pstmt = connection.prepareStatement(SELECT_BY_USER_ID_QUERY)) {

				pstmt.setInt(1, userID);

				try (ResultSet res = pstmt.executeQuery()) {

					while (res.next()) {
						OrderHistory history = new OrderHistory(res.getInt("orderID"), res.getInt("userID"),
								res.getString("status"), res.getTimestamp("changedAt"));

						history.setOrderHistoryID(res.getInt("orderHistoryID"));

						list.add(history);
					}
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	// Add order history using an existing connection.
	// Useful when this insert must be part of a transaction.
	public void addOrderHistory(OrderHistory history, Connection connection) throws SQLException {

		try (PreparedStatement pstmt = connection.prepareStatement(INSERT_QUERY)) {

			pstmt.setInt(1, history.getOrderID());
			pstmt.setInt(2, history.getUserID());
			pstmt.setString(3, history.getStatus());
			setChangedAt(pstmt, 4, history.getChangedAt());

			pstmt.executeUpdate();
		}
	}
}
