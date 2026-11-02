package com.food.DAO;

import java.util.List;
import com.food.model.User;

public interface userDAO {

	int addUser(User user);

	User getUser(int userId);

	void updateUser(User user);

	void updateProfile(User user);

	void deleteUser(int userId);

	List<User> getAllUser();

	User getUserByUsername(String userName);

}
