package com.food.DAOImp;

import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.food.DAO.userDAO;
import com.food.model.User;
import com.food.utility.DBConnection;

public class userDAOImp implements userDAO {

	private static final String INSERT_QUERY = "insert into `user`(userName,password,email,address,role,createDate,lastLoginDate)"
			+ " values(?,?,?,?,?,?,?)";

	private static final String SELECT_QUERY = "select * from `user` where userId=?";

	private static final String UPDATE_QUERY = "update `user` set userName = ?,password = ?, email = ?,address = ?,lastLoginDate = ? "
			+ "where userId = ? ";

	private static final String UPDATE_PROFILE_QUERY = "UPDATE `user` SET userName = ?, email = ?, address = ? "
			+ "WHERE userId = ?";

	private static final String DELETE_QUERY = "delete from `user` where userId = ?";

	private static final String SELECT_ALL_QUERY = "select * from `user`";

	@Override
	public int addUser(User user) {
		Connection connection = DBConnection.getConnection();

		try {
			PreparedStatement pstmt = connection.prepareStatement(INSERT_QUERY);

			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getAddress());
			pstmt.setString(5, user.getRole());

			pstmt.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
			pstmt.setTimestamp(7, new Timestamp(System.currentTimeMillis()));

			int i = pstmt.executeUpdate();
			System.out.println("Rows inserted: " + i);
			return i;

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return 0;

	}

	@Override
	public User getUser(int userId) {
		User user = null;
		Connection connection = DBConnection.getConnection();
		try {
			PreparedStatement pstmt = connection.prepareStatement(SELECT_QUERY);
			pstmt.setInt(1, userId);

			ResultSet res = pstmt.executeQuery();

			while (res.next()) {
				int id = res.getInt("userId");
				String userName = res.getString("userName");
				String password = res.getString("password");
				String email = res.getString("email");
				String address = res.getString("address");
				String role = res.getString("role");
				Timestamp createdDate = res.getTimestamp("createDate");
				Timestamp lastLoginDate = res.getTimestamp("lastLoginDate");

				user = new User(id, userName, password, email, address, role, createdDate, lastLoginDate);

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return user;
	}

	@Override
	public void updateUser(User user) {

		Connection connection = DBConnection.getConnection();
		try {
			PreparedStatement pstmt = connection.prepareStatement(UPDATE_QUERY);

			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getAddress());
			pstmt.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
			pstmt.setInt(6, user.getUserId());

			pstmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	@Override
	public void deleteUser(int userId) {
		Connection connection = DBConnection.getConnection();

		try {
			PreparedStatement pstmt = connection.prepareStatement(DELETE_QUERY);
			pstmt.setInt(1, userId);

			pstmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	@Override
	public List<User> getAllUser() {

		List<User> list = new ArrayList<User>();

		User user = null;
		Connection connection = DBConnection.getConnection();

		try {
			Statement stmt = connection.createStatement();

			ResultSet res = stmt.executeQuery(SELECT_ALL_QUERY);

			while (res.next()) {
				int userId = res.getInt("userId");
				String userName = res.getString("userName");
				String password = res.getString("password");
				String email = res.getString("email");
				String address = res.getString("address");
				String role = res.getString("role");
				Timestamp createdDate = res.getTimestamp("createDate");
				Timestamp lastLoginDate = res.getTimestamp("lastLoginDate");

				user = new User(userId, userName, password, email, address, role, createdDate, lastLoginDate);

				list.add(user);

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return list;

	}

	@Override
	public User getUserByUsername(String userName) {

		User user = null;

		String query = "SELECT * FROM `user` WHERE userName = ?";

		Connection connection = DBConnection.getConnection();

		try {
			PreparedStatement pstmt = connection.prepareStatement(query);

			pstmt.setString(1, userName);

			ResultSet res = pstmt.executeQuery();

			while (res.next()) {

				user = new User(res.getInt("userId"), res.getString("userName"), res.getString("password"),
						res.getString("email"), res.getString("address"), res.getString("role"),
						res.getTimestamp("createDate"), res.getTimestamp("lastLoginDate"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	@Override
	public void updateProfile(User user) {

		Connection connection = DBConnection.getConnection();

		try {
			PreparedStatement pstmt = connection.prepareStatement(UPDATE_PROFILE_QUERY);

			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getEmail());
			pstmt.setString(3, user.getAddress());
			pstmt.setInt(4, user.getUserId());

			int rows = pstmt.executeUpdate();

			System.out.println("Profile rows updated: " + rows);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
