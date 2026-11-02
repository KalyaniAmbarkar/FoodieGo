<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page
	import="java.util.List,com.food.model.Menu,com.food.model.Cart,com.food.model.CartItem"%>

<%
Cart cart = (Cart) session.getAttribute("cart");
int cartCount = 0;

if (cart != null && cart.getItems() != null) {
	for (CartItem item : cart.getItems().values()) {
		cartCount += item.getQuantity();
	}
}
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>FoodieGo - Restaurant Menu</title>

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
	padding: 20px 5%;
	display: flex;
	justify-content: space-between;
	align-items: center;
	flex-wrap: wrap;
	gap: 15px;
}

.logo {
	font-size: 28px;
	font-weight: bold;
	color: #ff3b75;
}

nav {
	display: flex;
	align-items: center;
	gap: 25px;
}

nav a {
	color: white;
	text-decoration: none;
	font-size: 15px;
}

nav a:hover {
	color: #ff3b75;
}

.cart-link {
	background-color: #ff3b75;
	padding: 10px 16px;
	border-radius: 6px;
	font-weight: bold;
	display: flex;
	align-items: center;
	gap: 8px;
	color: white;
	text-decoration: none;
}

.cart-icon {
	position: relative;
	display: inline-block;
	font-size: 26px;
	line-height: 1;
}

.cart-count {
	position: absolute;
	top: -7px;
	right: -7px;
	background-color: white;
	color: #ff3b75;
	border-radius: 50%;
	min-width: 18px;
	height: 18px;
	padding: 0 3px;
	box-sizing: border-box;
	font-size: 11px;
	font-weight: bold;
	display: flex;
	align-items: center;
	justify-content: center;
}


.restaurant-header {
	width: 90%;
	max-width: 1200px;
	margin: 30px auto;
	background-color: #25252d;
	border-radius: 12px;
	overflow: hidden;
}

.restaurant-banner {
	height: 220px;
	background: linear-gradient(rgba(0, 0, 0, 0.2), rgba(0, 0, 0, 0.6)),
		url("https://images.unsplash.com/photo-1517248135467-4c7edcad34c4?auto=format&fit=crop&w=1400&q=80");
	background-size: cover;
	background-position: center;
}

.restaurant-info {
	padding: 22px;
}

.restaurant-info h1 {
	font-size: 30px;
	margin-bottom: 12px;
}

.restaurant-info p {
	color: #c5c5ce;
	margin-bottom: 12px;
	line-height: 1.6;
}

.restaurant-meta {
	display: flex;
	gap: 20px;
	flex-wrap: wrap;
	font-size: 14px;
}

.restaurant-rating {
	background-color: #159957;
	padding: 5px 9px;
	border-radius: 5px;
	font-weight: bold;
}


.menu-heading {
	width: 90%;
	max-width: 1200px;
	margin: 35px auto 25px;
}

.menu-heading h2 {
	font-size: 28px;
	margin-bottom: 10px;
}

.menu-heading p {
	color: #bdbdc7;
}

.menu-heading {
	width: 90%;
	max-width: 1200px;
	margin: 35px auto 25px;
	text-align: center;
}

.menu-heading h2 {
	font-size: 28px;
	margin-bottom: 10px;
	color: #ff3b75;
}

.menu-heading p {
	color: #bdbdc7;
	font-size: 16px;
}


.menu-container {
	width: 90%;
	max-width: 1200px;
	margin: auto;
	display: grid;
	grid-template-columns: repeat(3, 1fr);
	gap: 25px;
}


.menu-card {
	background: linear-gradient(#25252d, #25252d) padding-box,
		linear-gradient(135deg, #f8bbd0, #f48fb1, #fce4ec, #f06292) border-box;
	border: 4px solid transparent;
	border-radius: 15px;
	overflow: hidden;
	box-shadow: 0 4px 12px rgba(0, 0, 0, 0.3);
	transition: all 0.3s ease;
}

.menu-card:hover {
	transform: translateY(-5px);
	box-shadow: 0 5px 18px rgba(244, 143, 177, 0.25);
}

.food-image {
	width: 100%;
	height: 210px;
	object-fit: cover;
	display: block;
}

.menu-content {
	padding: 18px;
}

.menu-content h3 {
	font-size: 19px;
	margin-bottom: 12px;
}

.desc {
	color: #bdbdc7;
	font-size: 14px;
	line-height: 1.5;
	min-height: 42px;
	margin-bottom: 15px;
}

.menu-info {
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin-bottom: 18px;
}

.price {
	font-size: 20px;
	font-weight: bold;
}

.rating {
	background-color: #19a85b;
	padding: 5px 7px;
	border-radius: 5px;
	font-size: 12px;
}

.add-btn {
	display: block;
	width: 100%;
	padding: 12px;
	border: 1px solid #ff3b75;
	border-radius: 7px;
	background-color: #ff3b75;
	color: white;
	font-size: 15px;
	font-weight: bold;
	cursor: pointer;
}

.add-btn:hover {
	background-color: #ff3b75;
	color: white;
}


footer {
	background-color: #222228;
	color: #bdbdc7;
	text-align: center;
	padding: 25px;
	margin-top: 50px;
}


@media ( max-width : 900px) {
	.menu-container {
		grid-template-columns: repeat(2, 1fr);
	}
}

@media ( max-width : 600px) {
	header {
		flex-direction: column;
	}
	nav {
		justify-content: center;
		flex-wrap: wrap;
		gap: 15px;
	}
	.menu-container {
		grid-template-columns: 1fr;
	}
	.restaurant-info h1 {
		font-size: 24px;
	}
	.restaurant-banner {
		height: 170px;
	}
	.food-image {
		height: 220px;
	}
}
</style>
</head>

<body>


	<header>
		<div class="logo">FoodieGo</div>

		<nav>
			<a href="home">Home</a> <a href="callRestaurantServlet">Restaurants</a>
			<a href="cart.jsp" class="cart-link"> <span class="cart-icon">
					🛒 <span class="cart-count"><%=cartCount%></span>
			</span> Cart
			</a>
		</nav>
	</header>
	<section class="menu-heading">
		<h2>Restaurant Menu 🍽️</h2>
		<p>Choose your favourite dishes and add them to your cart.</p>
	</section>



	<main class="menu-container">

		<%
		List<Menu> allMenusByRestaurant = (List<Menu>) request.getAttribute("allMenusByRestaurant");

		for (Menu menu : allMenusByRestaurant) {
		%>

		<div class="menu-card">
			<img src="<%=menu.getImagepath()%>" alt="<%=menu.getCategory()%>"
				class="food-image">

			<div class="menu-content">
				<h3><%=menu.getItemName()%></h3>

				<p class="desc">
					<%=menu.getDescription()%>
				</p>

				<div class="menu-info">
					<span class="price"><%=menu.getPrice()%></span> <span
						class="rating">★ <%=menu.getRating()%></span>
				</div>

				<form action="cartServlet">
					<input type="hidden" name="menuId" value="<%=menu.getMenuID()%>">
					<input type="hidden" name="restaurantID"
						value="<%=menu.getRestaurantID()%>"> <input type="hidden"
						name="quantity" value="1"> <input type="hidden"
						name="action" value="add">
					<button class="add-btn">Add to Cart</button>



				</form>


			</div>
		</div>



		<%
		}
		%>




	</main>


	<footer>
		<p>© 2026 FoodieGo | Delicious Food Delivered to Your Door</p>
	</footer>

</body>
</html>



