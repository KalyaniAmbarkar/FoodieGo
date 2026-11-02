<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.List,com.food.model.Restaurant"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>FoodieGo - Home</title>

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
	gap: 20px;
}

.logo {
	color: #ff3b75;
	font-size: 28px;
	font-weight: bold;
}

nav {
	display: flex;
	align-items: center;
	gap: 20px;
	flex-wrap: wrap;
}

nav a {
	color: white;
	text-decoration: none;
	font-size: 15px;
}

nav a:hover, nav a.active {
	color: #ff3b75;
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

.hero {
	display: grid;
	grid-template-columns: 1.4fr 0.8fr;
	align-items: center;
	gap: 35px;
	padding: 55px 5%;
	background: linear-gradient(120deg, #29212d, #19191f 70%);
}

.hero h1 {
	font-size: 44px;
	line-height: 1.2;
	margin-bottom: 18px;
}

.hero h1 span {
	color: #ff3b75;
}

.hero p {
	color: #cccccc;
	line-height: 1.7;
	margin-bottom: 25px;
	max-width: 540px;
}

.search-box {
	display: flex;
	max-width: 520px;
	background-color: #292932;
	border: 1px solid #41414c;
	border-radius: 35px;
	padding: 5px;
}

.search-box input {
	flex: 1;
	min-width: 0;
	background: transparent;
	border: none;
	outline: none;
	padding: 12px 15px;
	color: white;
	font-size: 14px;
}

.search-box input::placeholder {
	color: #aaaaaa;
}

.search-box button {
	background-color: #ff3b75;
	color: white;
	border: none;
	border-radius: 30px;
	padding: 12px 23px;
	font-weight: bold;
	cursor: pointer;
}

.search-box button:hover {
	background-color: #e92d65;
}

.deal-card {
	background: linear-gradient(#222228, #222228) padding-box,
		linear-gradient(135deg, #f8bbd0, #f48fb1, #fce4ec, #f06292) border-box;
	border: 3px solid transparent;
	border-radius: 15px;
	padding: 12px;
	box-shadow: 0 4px 12px rgba(0, 0, 0, 0.3);
	transition: all 0.3s ease;
}

.deal-card:hover {
	transform: translateY(-5px);
	box-shadow: 0 5px 18px rgba(244, 143, 177, 0.25);
}

.deal-card img {
	width: 100%;
	height: 205px;
	object-fit: cover;
	display: block;
	border-radius: 10px;
}

.deal-content {
	padding: 15px 5px 7px;
}

.deal-content h2 {
	font-size: 23px;
	margin-bottom: 10px;
}

.deal-content p {
	color: #cccccc;
	font-size: 14px;
	line-height: 1.6;
	margin-bottom: 12px;
}

.deal-tag {
	display: inline-block;
	background-color: #30242d;
	color: #ff4a84;
	padding: 9px 12px;
	border-radius: 6px;
	font-size: 13px;
	font-weight: bold;
}

.container {
	padding: 35px 4%;
}

.section-heading {
	display: flex;
	align-items: center;
	justify-content: space-between;
	gap: 15px;
	margin-bottom: 25px;
}

.section-heading h2 {
	font-size: 27px;
}

.view-all {
	color: #ff3b75;
	text-decoration: none;
	font-weight: bold;
}

.view-all:hover {
	color: #ff7ca4;
}

.restaurant-grid {
	display: grid;
	grid-template-columns: repeat(4, minmax(0, 1fr));
	gap: 22px;
}

.restaurant-grid a, .restaurant-grid a:link, .restaurant-grid a:visited,
	.restaurant-grid a:hover, .restaurant-grid a:active {
	color: white;
	text-decoration: none;
}

.card {
	background: linear-gradient(#222228, #222228) padding-box,
		linear-gradient(135deg, #f8bbd0, #f48fb1, #fce4ec, #f06292) border-box;
	border: 3px solid transparent;
	border-radius: 14px;
	overflow: hidden;
	box-shadow: 0 4px 12px rgba(0, 0, 0, 0.3);
	transition: all 0.3s ease;
}

.card:hover {
	transform: translateY(-5px);
	box-shadow: 0 5px 18px rgba(244, 143, 177, 0.25);
}

.card img {
	width: 100%;
	height: 160px;
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
	overflow-wrap: anywhere;
}

.rating {
	background-color: #19a85b;
	padding: 5px 7px;
	border-radius: 5px;
	font-size: 12px;
	white-space: nowrap;
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
	color: #c3c3cc;
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

.empty-message {
	color: #cccccc;
	padding: 15px 0;
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
		grid-template-columns: repeat(3, minmax(0, 1fr));
	}
}

@media ( max-width : 750px) {
	header {
		flex-direction: column;
	}
	nav {
		justify-content: center;
		gap: 14px;
	}
	.hero {
		grid-template-columns: 1fr;
	}
	.hero h1 {
		font-size: 35px;
	}
	.deal-card {
		max-width: 500px;
	}
	.restaurant-grid {
		grid-template-columns: repeat(2, minmax(0, 1fr));
		gap: 12px;
	}
}

@media ( max-width : 420px) {
	.restaurant-grid {
		grid-template-columns: 1fr;
	}
	.card img {
		height: 190px;
	}
	.search-box button {
		padding: 12px 14px;
	}
}
</style>
</head>

<body>



	<header>
		<div class="logo">FoodieGo</div>

		<nav>
			<a href="home" class="active">Home</a> <a
				href="callRestaurantServlet">Restaurants</a> <a href="cart.jsp">Cart</a>
			<a href="login.html">Login</a> <a href="registration.html">Sign
				Up</a> <a href="orderHistory">Order History</a> <a
				href="${pageContext.request.contextPath}/profile"
				class="profile-link"> <img src="images/im.jpg" alt="Profile">
				Profile
			</a>

		</nav>
	</header>



	<section class="hero">

		<div>
			<h1>
				Discover the Best<br> <span>Restaurants</span> Near You
			</h1>

			<p>Explore your favorite restaurants, discover delicious meals,
				and enjoy a convenient food ordering experience with FoodieGo.</p>

			<form class="search-box" action="home" method="get">

				<input type="text" name="search"
					placeholder="Search restaurants or cuisines..."
					aria-label="Search restaurants">

				<button type="submit">Search</button>
			</form>
		</div>

		<div class="deal-card">

			<img
				src="https://images.unsplash.com/photo-1546069901-ba9599a7e63c?auto=format&fit=crop&w=800&q=80"
				alt="Delicious healthy food">

			<div class="deal-content">
				<h2>Hot Deals Today</h2>

				<p>Find your next favorite meal and explore delicious food from
					restaurants around you.</p>

				<span class="deal-tag"> 10% OFF up to ₹100 </span>
			</div>

		</div>

	</section>



	<main class="container">

		<div class="section-heading">
			<h2>Popular Restaurants</h2>

			<a href="callRestaurantServlet" class="view-all"> View All &rarr;
			</a>
		</div>

		<div class="restaurant-grid">


			<%
			List<Restaurant> allRestaurants = (List<Restaurant>) request.getAttribute("allRestaurants");

			if (allRestaurants != null && !allRestaurants.isEmpty()) {

				for (Restaurant restaurant : allRestaurants) {
			%>


			<a
				href="menu?restaurantID=<%=restaurant.getRestaurantID()%>&restaurantName=<%=java.net.URLEncoder.encode(restaurant.getName(), "UTF-8")%>">

				<div class="card">

					<img src="<%=restaurant.getImagepath()%>"
						alt="<%=restaurant.getName()%>">

					<div class="card-content">

						<div class="top-row">
							<h3><%=restaurant.getName()%></h3>

							<span class="rating"> &#9733; <%=restaurant.getRating()%>
							</span>
						</div>

						<p class="cuisine">
							<%=restaurant.getCuisineType()%>
						</p>

						<div class="details">
							<span><%=restaurant.getAddress()%></span> <span><%=restaurant.getDeliveryTime()%></span>
						</div>

						<div class="offer">10% OFF up to ₹100</div>

					</div>
				</div>

			</a>

			<%
			}

			} else {
			%>

			<p class="empty-message">Restaurants are not available right now.
			</p>

			<%
			}
			%>

		</div>

	</main>



	<footer> &copy; 2026 FoodieGo | Delicious Food Delivered to
		Your Door </footer>

</body>
</html>
