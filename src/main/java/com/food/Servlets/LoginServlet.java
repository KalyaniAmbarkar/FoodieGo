package com.food.Servlets;

import java.io.IOException;

import org.mindrot.jbcrypt.BCrypt;

import com.food.DAOImp.userDAOImp;
import com.food.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/callLoginServlet")
public class LoginServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String password = req.getParameter("password");

		HttpSession session = req.getSession();

		userDAOImp userDAOimp = new userDAOImp();

		User user = userDAOimp.getUserByUsername(name);
		if (user == null) {
			resp.sendRedirect("login.html");
			return;
		}

		String dbPassword = user.getPassword();

		if (BCrypt.checkpw(password, dbPassword)) {

			session.setAttribute("user", user);

			String page = (String) session.getAttribute("redirectPage");

			if (page != null) {
				session.removeAttribute("redirectPage");
				resp.sendRedirect(page);
			} else {
				resp.sendRedirect("callRestaurantServlet");
			}

		} else {
			resp.sendRedirect("login.html");
		}

	}

}