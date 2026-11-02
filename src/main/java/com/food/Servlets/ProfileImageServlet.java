package com.food.Servlets;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/profileImage/*")
public class ProfileImageServlet extends HttpServlet {

	private static final String UPLOAD_PATH = "C:/FoodieGoUploads/profiles";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String fileName = request.getPathInfo();

		if (fileName == null || fileName.length() <= 1) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		fileName = fileName.substring(1);

		// Prevent directory traversal
		if (fileName.contains("/") || fileName.contains("\\") || fileName.contains("..")) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		File imageFile = new File(UPLOAD_PATH, fileName);

		if (!imageFile.isFile()) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		String contentType = Files.probeContentType(imageFile.toPath());

		if (contentType == null || !contentType.matches("image/(jpeg|png|gif|webp)")) {
			response.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
			return;
		}

		response.setContentType(contentType);
		response.setContentLengthLong(imageFile.length());

		Files.copy(imageFile.toPath(), response.getOutputStream());
	}

}
