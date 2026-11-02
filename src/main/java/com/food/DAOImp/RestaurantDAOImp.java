package com.food.DAOImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.food.DAO.RestaurantDAO;
import com.food.model.Restaurant;
import com.food.utility.DBConnection;

public class RestaurantDAOImp implements RestaurantDAO {

	private static final String INSERT_QUERY = "INSERT INTO Restaurant(name, cuisineType, deliveryTime, address, adminUserID, rating, isActive, imagePath) "
			+ "VALUES(?, ?, ?, ?, ?, ?, ?,?)";

	private static final String SELECT_QUERY = "SELECT * FROM Restaurant WHERE restaurantID = ?";

	private static final String UPDATE_QUERY = "UPDATE Restaurant SET name = ?, cuisineType = ?, deliveryTime = ?, "
			+ "address = ?, adminUserID = ?, rating = ?, isActive = ?, imagePath =? " + "WHERE restaurantID = ?";

	private static final String DELETE_QUERY = "DELETE FROM Restaurant WHERE restaurantID = ?";

	private static final String SELECT_ALL_QUERY = "SELECT * FROM Restaurant";

	@Override
	public void addRestaurant(Restaurant restaurant) {
		Connection connection = DBConnection.getConnection();

		try {
			PreparedStatement pstmt = connection.prepareStatement(INSERT_QUERY);

			pstmt.setString(1, restaurant.getName());
			pstmt.setString(2, restaurant.getCuisineType());
			pstmt.setInt(3, restaurant.getDeliveryTime());
			pstmt.setString(4, restaurant.getAddress());
			pstmt.setInt(5, restaurant.getAdminUserID());
			pstmt.setDouble(6, restaurant.getRating());
			pstmt.setBoolean(7, restaurant.isActive());
			pstmt.setString(8, restaurant.getImagepath());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Restaurant getRestaurant(int restaurantID) {
		Restaurant restaurant = null;
		Connection connection = DBConnection.getConnection();

		try {
			PreparedStatement pstmt = connection.prepareStatement(SELECT_QUERY);
			pstmt.setInt(1, restaurantID);

			ResultSet res = pstmt.executeQuery();

			if (res.next()) {
				restaurant = new Restaurant(res.getString("name"), res.getString("cuisineType"),
						res.getInt("deliveryTime"), res.getString("address"), res.getInt("adminUserID"),
						res.getDouble("rating"), res.getBoolean("isActive"), res.getString("imagePath"));

				restaurant.setRestaurantID(res.getInt("restaurantID"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return restaurant;
	}

	@Override
	public void updateRestaurant(Restaurant restaurant) {
		Connection connection = DBConnection.getConnection();

		try {
			PreparedStatement pstmt = connection.prepareStatement(UPDATE_QUERY);

			pstmt.setString(1, restaurant.getName());
			pstmt.setString(2, restaurant.getCuisineType());
			pstmt.setInt(3, restaurant.getDeliveryTime());
			pstmt.setString(4, restaurant.getAddress());
			pstmt.setInt(5, restaurant.getAdminUserID());
			pstmt.setDouble(6, restaurant.getRating());
			pstmt.setBoolean(7, restaurant.isActive());
			pstmt.setInt(8, restaurant.getRestaurantID());
			pstmt.setString(8, restaurant.getImagepath());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteRestaurant(int restaurantID) {
		Connection connection = DBConnection.getConnection();

		try {
			PreparedStatement pstmt = connection.prepareStatement(DELETE_QUERY);
			pstmt.setInt(1, restaurantID);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Restaurant> getAllRestaurants() {
		List<Restaurant> list = new ArrayList<Restaurant>();
		Connection connection = DBConnection.getConnection();

		try {
			Statement stmt = connection.createStatement();
			ResultSet res = stmt.executeQuery(SELECT_ALL_QUERY);

			while (res.next()) {
				Restaurant restaurant = new Restaurant(res.getString("name"), res.getString("cuisineType"),
						res.getInt("deliveryTime"), res.getString("address"), res.getInt("adminUserID"),
						res.getDouble("rating"), res.getBoolean("isActive"), res.getString("imagePath"));

				restaurant.setRestaurantID(res.getInt("restaurantID"));
				list.add(restaurant);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<Restaurant> searchRestaurants(String search) {

		List<Restaurant> list = new ArrayList<Restaurant>();

		String query = "SELECT DISTINCT r.* FROM Restaurant r " + "LEFT JOIN Menu m ON r.restaurantID = m.restaurantID "
				+ "WHERE r.name LIKE ? " + "OR r.cuisineType LIKE ? " + "OR m.itemName LIKE ? "
				+ "ORDER BY r.restaurantID";

		try (Connection connection = DBConnection.getConnection()) {

			if (connection == null) {
				return list;
			}

			try (PreparedStatement pstmt = connection.prepareStatement(query)) {

				String keyword = "%" + search + "%";

				pstmt.setString(1, keyword);
				pstmt.setString(2, keyword);
				pstmt.setString(3, keyword);

				try (ResultSet res = pstmt.executeQuery()) {

					while (res.next()) {

						Restaurant restaurant = new Restaurant(res.getString("name"), res.getString("cuisineType"),
								res.getInt("deliveryTime"), res.getString("address"), res.getInt("adminUserID"),
								res.getDouble("rating"), res.getBoolean("isActive"), res.getString("imagePath"));

						restaurant.setRestaurantID(res.getInt("restaurantID"));

						list.add(restaurant);
					}
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

}
