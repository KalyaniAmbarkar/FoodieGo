package com.food.DAOImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.food.DAO.MenuDAO;
import com.food.model.Menu;
import com.food.utility.DBConnection;

public class MenuDAOImp implements MenuDAO {

	private static final String INSERT_QUERY = "INSERT INTO Menu(restaurantID, itemName, description, rating,price, isAvailable, category,imagePath,createdAt, updateAt, deletedAt) "
			+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)";

	private static final String SELECT_QUERY = "SELECT * FROM Menu WHERE menuID = ?";

	private static final String UPDATE_QUERY = "UPDATE Menu SET restaurantID = ?, itemName = ?, description = ?,rating =?,"
			+ " price = ?, isAvailable = ?,imagePath=? ,category = ?, updateAt = ? " + "WHERE menuID = ?";

	private static final String DELETE_QUERY = "DELETE FROM Menu WHERE menuID = ?";

	private static final String SELECT_ALL_QUERY = "SELECT * FROM Menu";

	@Override
	public void addMenu(Menu menu) {

		Connection connection = DBConnection.getConnection();

		try {
			PreparedStatement pstmt = connection.prepareStatement(INSERT_QUERY);

			pstmt.setInt(1, menu.getRestaurantID());
			pstmt.setString(2, menu.getItemName());
			pstmt.setString(3, menu.getDescription());
			pstmt.setDouble(4, menu.getRating());
			pstmt.setBigDecimal(5, menu.getPrice());
			pstmt.setBoolean(6, menu.isAvailable());
			pstmt.setString(7, menu.getCategory());
			pstmt.setString(8, menu.getImagepath());

			Timestamp now = new Timestamp(System.currentTimeMillis());

			pstmt.setTimestamp(7, now);
			pstmt.setTimestamp(8, now);
			pstmt.setTimestamp(9, menu.getDeletedAt());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Menu getMenu(int menuID) {

		Menu menu = null;
		Connection connection = DBConnection.getConnection();

		try {
			PreparedStatement pstmt = connection.prepareStatement(SELECT_QUERY);

			pstmt.setInt(1, menuID);

			ResultSet res = pstmt.executeQuery();

			if (res.next()) {
				menu = new Menu(res.getInt("restaurantID"), res.getString("itemName"), res.getString("description"),
						res.getDouble("rating"), res.getBigDecimal("price"), res.getBoolean("isAvailable"),
						res.getString("category"), res.getString("imagePath"), res.getTimestamp("createdAt"),
						res.getTimestamp("updatedAt"), res.getTimestamp("deletedAt"));

				menu.setMenuID(res.getInt("menuID"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return menu;
	}

	@Override
	public void updateMenu(Menu menu) {

		Connection connection = DBConnection.getConnection();

		try {
			PreparedStatement pstmt = connection.prepareStatement(UPDATE_QUERY);

			pstmt.setInt(1, menu.getRestaurantID());
			pstmt.setString(2, menu.getItemName());
			pstmt.setString(3, menu.getDescription());
			pstmt.setDouble(4, menu.getRating());
			pstmt.setBigDecimal(5, menu.getPrice());
			pstmt.setBoolean(6, menu.isAvailable());
			pstmt.setString(7, menu.getCategory());
			pstmt.setString(8, menu.getImagepath());

			Timestamp now = new Timestamp(System.currentTimeMillis());
			pstmt.setTimestamp(7, now);

			pstmt.setInt(8, menu.getMenuID());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteMenu(int menuID) {

		Connection connection = DBConnection.getConnection();

		try {
			PreparedStatement pstmt = connection.prepareStatement(DELETE_QUERY);

			pstmt.setInt(1, menuID);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Menu> getAllMenus() {

		List<Menu> list = new ArrayList<Menu>();
		Connection connection = DBConnection.getConnection();

		try {
			Statement stmt = connection.createStatement();
			ResultSet res = stmt.executeQuery(SELECT_ALL_QUERY);

			while (res.next()) {
				Menu menu = new Menu(res.getInt("restaurantID"), res.getString("itemName"),
						res.getString("description"), res.getDouble("rating"), res.getBigDecimal("price"),
						res.getBoolean("isAvailable"), res.getString("category"), res.getString("imagePath"),
						res.getTimestamp("createdAt"), res.getTimestamp("updatedAt"), res.getTimestamp("deletedAt"));

				menu.setMenuID(res.getInt("menuID"));

				list.add(menu);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public List<Menu> getAllMenusByRestaurant(int restaurantID) {

		List<Menu> list = new ArrayList<Menu>();

		String query = "SELECT * FROM Menu WHERE restaurantID = ?";

		Connection connection = DBConnection.getConnection();

		try {
			PreparedStatement pstmt = connection.prepareStatement(query);

			pstmt.setInt(1, restaurantID);

			ResultSet res = pstmt.executeQuery();

			while (res.next()) {
				Menu menu = new Menu(res.getInt("restaurantID"), res.getString("itemName"),
						res.getString("description"), res.getDouble("rating"), res.getBigDecimal("price"),
						res.getBoolean("isAvailable"), res.getString("category"), res.getString("imagePath"),
						res.getTimestamp("createdAt"), res.getTimestamp("updatedAt"), res.getTimestamp("deletedAt"));

				menu.setMenuID(res.getInt("menuID"));

				list.add(menu);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}
}
