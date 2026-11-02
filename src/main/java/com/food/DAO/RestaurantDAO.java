package com.food.DAO;

import java.util.List;

import com.food.model.Restaurant;

public interface RestaurantDAO {
	
	void addRestaurant(Restaurant restaurant);

	Restaurant getRestaurant(int restaurantID);

	void updateRestaurant(Restaurant restaurant);

	void deleteRestaurant(int restaurantID);

	List<Restaurant> getAllRestaurants();

}
