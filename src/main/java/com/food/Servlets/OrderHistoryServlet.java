package com.food.Servlets;

import java.io.IOException;
import java.util.List;

import com.food.DAOImp.OrderHistoryDAOImp;
import com.food.model.OrderHistory;
import com.food.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/orderHistory")
public class OrderHistoryServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession(false);

		if (session == null || session.getAttribute("user") == null) {
			resp.sendRedirect("login.html");
			return;
		}

		User user = (User) session.getAttribute("user");

		int userID = user.getUserId();

		OrderHistoryDAOImp dao = new OrderHistoryDAOImp();

		List<OrderHistory> historyList = dao.getOrderHistoryByUserID(userID);

		req.setAttribute("historyList", historyList);

		req.getRequestDispatcher("orderHistory.jsp").forward(req, resp);
	}
}
