<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@
    page import="java.util.List,com.food.model.Restaurant"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>FoodieGo - Restaurants</title>

<style>
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
	font-family: Arial, sans-serif;
}

body {
	background-color: #19191f;
	color: white;
}

header {
	background-color: #222228;
	padding: 20px 4%;
	display: flex;
	justify-content: space-between;
	align-items: center;
}

.logo {
	color: #ff3b75;
	font-size: 28px;
	font-weight: bold;
}

nav a {
	color: white;
	text-decoration: none;
	margin-left: 20px;
	font-size: 15px;
}

.restaurant-grid a, .restaurant-grid a:link, .restaurant-grid a:visited,
	.restaurant-grid a:active {
	color: white;
	text-decoration: none;
}

.restaurant-grid a:hover {
	color: white;
	text-decoration: none;
}

nav a:hover {
	color: #ff3b75;
}

nav {
	margin-left: auto;
	display: flex;
	align-items: center;
	gap: 20px;
}

.heading {
	text-align: center;
	padding: 35px 15px;
	background-color: #292932;
}

.heading h1 {
	color: #ff3b75;
	margin-bottom: 10px;
}

.heading p {
	color: #cccccc;
}

.profile-link {
	display: inline-flex;
	align-items: center;
	gap: 6px;
}

.profile-link img {
	width: 25px;
	height: 25px;
	border-radius: 50%;
	object-fit: cover;
}

.container {
	padding: 35px 4%;
}

.container h2 {
	font-size: 27px;
	margin-bottom: 25px;
}

.restaurant-grid {
	display: grid;
	grid-template-columns: repeat(4, 1fr);
	gap: 22px;
}

.card {
	border: 3px solid transparent;
	border-radius: 14px;
	background: linear-gradient(#222228, #222228) padding-box,
		linear-gradient(135deg, #f8bbd0, #f48fb1, #fce4ec, #f06292) border-box;
	overflow: hidden;
	transition: all 0.3s ease;
}


.card:hover {
	transform: translateY(-5px);
	box-shadow: 0 5px 18px rgba(244, 143, 177, 0.25);
}

.card img {
	width: 100%;
	height: 175px;
	object-fit: cover;
	display: block;
}

.card-content {
	padding: 15px;
}

.top-row {
	display: flex;
	justify-content: space-between;
	align-items: center;
	gap: 8px;
	margin-bottom: 12px;
}

.top-row h3 {
	font-size: 17px;
}

.rating {
	background-color: #19a85b;
	padding: 5px 7px;
	border-radius: 5px;
	font-size: 12px;
}

.cuisine {
	color: #c3c3cc;
	font-size: 14px;
	margin-bottom: 15px;
}

.details {
	display: flex;
	justify-content: space-between;
	gap: 8px;
	font-size: 12px;
	margin-bottom: 15px;
}

.offer {
	background-color: #30242d;
	color: #ff4a84;
	padding: 10px;
	border-radius: 5px;
	font-size: 13px;
	font-weight: bold;
}

footer {
	background-color: #222228;
	color: #bbbbbb;
	text-align: center;
	padding: 22px;
	margin-top: 20px;
}

@media ( max-width : 1000px) {
	.restaurant-grid {
		grid-template-columns: repeat(3, 1fr);
	}
}

@media ( max-width : 700px) {
	header {
		flex-direction: column;
		gap: 15px;
	}
	nav a {
		margin: 0 6px;
	}
	.restaurant-grid {
		grid-template-columns: repeat(2, 1fr);
		gap: 12px;
	}
	.card img {
		height: 140px;
	}
}

@media ( max-width : 400px) {
	.restaurant-grid {
		grid-template-columns: 1fr;
	}
}
</style>
</head>

<body>

	<header>
		<div class="logo">FoodieGo</div>

		<nav>
			<a href="home">Home</a> <a href="callRestaurantServlet">Restaurants</a>
			<a href="cart.jsp">Cart</a> <a href="login.html">Login</a> <a
				href="registration.html">Sign Up</a> <a
				href="${pageContext.request.contextPath}/profile"
				class="profile-link"> <img src="images/im.jpg" alt="Profile">
				Profile
			</a>

		</nav>
	</header>

	<section class="heading">
		<h1>Popular Restaurants</h1>
		<p>Discover delicious food from your favourite restaurants</p>
	</section>

	<main class="container">

		<h2>Restaurants Near You</h2>

		<div class="restaurant-grid">



			<% 
        	List<Restaurant> allRestaurants=(List<Restaurant>)request.getAttribute("allRestaurants");
            for(Restaurant restaurant:allRestaurants) {
        	%>


			<a
				href="menu?restaurantID=<%=restaurant.getRestaurantID()%>&restaurantName=<%=restaurant.getName()%>">
				<div class="card">
					<img src="<%=  restaurant.getImagepath()%>">
					<div class="card-content">
						<div class="top-row">
							<h3><%=restaurant.getName() %></h3>
							<span class="rating">★ <%=restaurant.getRating()%></span>
						</div>

						<p class="cuisine"><%=restaurant.getCuisineType() %></p>

						<div class="details">
							<span><%=restaurant.getAddress() %></span> <span><%=restaurant.getDeliveryTime() %></span>
						</div>

						<div class="offer">10% OFF up to ₹100</div>
					</div>
				</div>
			</a>
			<% 
		}
        %>



		</div>
	</main>

	<footer> © 2026 FoodieGo | Delicious Food Delivered to Your
		Door </footer>

</body>
</html>
