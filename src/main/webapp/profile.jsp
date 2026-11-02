<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="com.food.model.User"%>


<%
    User profileUser = (User) request.getAttribute("profileUser");

    if (profileUser == null) {
        response.sendRedirect("login.html");
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>My Profile - FoodieGo</title>

<style>
* {
	box-sizing: border-box;
}

body {
	margin: 0;
	font-family: Arial, sans-serif;
	background: #19191f;
	color: white;
}

header {
	background: #222228;
	padding: 18px 8%;
	display: flex;
	justify-content: space-between;
	align-items: center;
}

.logo {
	color: #ff3b75;
	font-size: 25px;
	font-weight: bold;
}

header a {
	color: white;
	text-decoration: none;
}

.container {
	width: 90%;
	max-width: 650px;
	margin: 45px auto;
	padding: 30px;
	background: #222228;
	border-radius: 12px;
}

h2 {
	margin-top: 0;
	color: #ff3b75;
}

label {
	display: block;
	margin: 18px 0 8px;
}

input, textarea {
	width: 100%;
	padding: 12px;
	border: 1px solid #444;
	border-radius: 6px;
	background: #19191f;
	color: white;
	font-size: 15px;
}

button {
	margin-top: 24px;
	padding: 12px 22px;
	background: #ff3b75;
	color: white;
	border: none;
	border-radius: 6px;
	cursor: pointer;
}

button:hover {
	background: #e92d65;
}
</style>
</head>

<body>

	<header>
		<div class="logo">FoodieGo</div>
		<a href="callRestaurantServlet">Back to Restaurants</a>
	</header>

	<div class="container">
		<h2>My Profile</h2>

		<form action="updateProfile" method="post">



			<label for="userName">Full Name</label> <input type="text"
				id="userName" name="userName"
				value="<%= profileUser.getUserName() == null ? "" : profileUser.getUserName() %>"
				required> <label for="email">Email Address</label> <input
				type="email" id="email" name="email"
				value="<%= profileUser.getEmail() == null ? "" : profileUser.getEmail() %>"
				required> <label for="address">Delivery Address</label>
			<textarea id="address" name="address" rows="3" required><%= profileUser.getAddress() == null? "" : profileUser.getAddress() %></textarea>

			<button type="submit">Save Changes</button>

		</form>
	</div>

</body>
</html>