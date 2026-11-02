package com.food.Servlets;

import java.io.IOException;
import java.util.List;

import com.food.DAOImp.RestaurantDAOImp;
import com.food.model.Restaurant;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/callRestaurantServlet", "/home" })
public class RestaurantServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RestaurantDAOImp restaurantDAOImp = new RestaurantDAOImp();

		String search = req.getParameter("search");

		List<Restaurant> allRestaurants;

		if (search != null && !search.trim().isEmpty()) {
			allRestaurants = restaurantDAOImp.searchRestaurants(search.trim());
		} else {
			allRestaurants = restaurantDAOImp.getAllRestaurants();
		}

		req.setAttribute("allRestaurants", allRestaurants);
		req.setAttribute("search", search);

		String path = req.getServletPath();

		if ("/home".equals(path)) {
			RequestDispatcher rd = req.getRequestDispatcher("home.jsp");
			rd.forward(req, resp);
		} else {
			RequestDispatcher rd = req.getRequestDispatcher("restaurant.jsp");
			rd.forward(req, resp);
		}
	}
}
