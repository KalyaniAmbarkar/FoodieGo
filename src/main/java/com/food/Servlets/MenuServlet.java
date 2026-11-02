package com.food.Servlets;

import java.io.IOException;
import java.util.List;

import com.food.DAOImp.MenuDAOImp;
import com.food.model.Menu;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/menu")
public class MenuServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		MenuDAOImp menuDAOImp = new MenuDAOImp();

		int restaurantID = Integer.parseInt(req.getParameter("restaurantID"));
		List<Menu> allMenusByRestaurant = menuDAOImp.getAllMenusByRestaurant(restaurantID);

		req.setAttribute("allMenusByRestaurant", allMenusByRestaurant);

		RequestDispatcher rd = req.getRequestDispatcher("menu.jsp");
		rd.forward(req, resp);

	}

}
