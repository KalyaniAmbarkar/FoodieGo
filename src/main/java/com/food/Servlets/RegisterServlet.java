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

@WebServlet("/callRegisterServlet")
public class RegisterServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userName = req.getParameter("userName");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String confirmPassword = req.getParameter("confirmPassword");
		String address = req.getParameter("address");
		String role = req.getParameter("role");

		if (password == null || confirmPassword == null || !password.equals(confirmPassword)) {
			resp.sendRedirect("registration.html");
			return;
		}

		String hashpw = BCrypt.hashpw(password, BCrypt.gensalt(12));
		User user = new User(userName, hashpw, email, address, role);

		userDAOImp userDAOimp = new userDAOImp();
		int res = userDAOimp.addUser(user);
		if (res == 1) {
			resp.sendRedirect("login.html");
		} else {
			resp.sendRedirect("registration.html");
		}

	}
}
