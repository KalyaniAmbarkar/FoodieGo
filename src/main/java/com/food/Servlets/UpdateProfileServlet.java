package com.food.Servlets;

import java.io.IOException;

import com.food.DAOImp.userDAOImp;
import com.food.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/updateProfile")
public class UpdateProfileServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("user") == null) {
			response.sendRedirect("login.html");
			return;
		}

		User user = (User) session.getAttribute("user");

		String userName = request.getParameter("userName");
		String email = request.getParameter("email");
		String address = request.getParameter("address");

		if (userName == null || userName.trim().isEmpty() || email == null || email.trim().isEmpty() || address == null
				|| address.trim().isEmpty()) {

			response.sendRedirect("profile?error=empty");
			return;
		}

		user.setUserName(userName.trim());
		user.setEmail(email.trim());
		user.setAddress(address.trim());

		userDAOImp dao = new userDAOImp();
		dao.updateProfile(user);

		
		session.setAttribute("user", user);

		response.sendRedirect("profile?success=updated");
	}

}
