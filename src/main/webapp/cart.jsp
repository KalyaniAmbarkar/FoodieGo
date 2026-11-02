<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@ page
	import="java.util.Map,java.math.BigDecimal,com.food.model.Cart,com.food.model.CartItem"%>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>FoodieGo - Your Cart</title>

<style>
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
	font-family: 'Segoe UI', Arial, sans-serif;
}

body {
	background-color: #0f1117;
	color: white;
}


.navbar {
	display: flex;
	justify-content: space-between;
	align-items: center;
	padding: 20px 7%;
	background-color: #222228;
	border-bottom: 1px solid #34343d;
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

.logo {
	font-size: 27px;
	font-weight: bold;
	color: white;
}

.logo span {
	color: #ff3b75;
}

.nav-links {
	display: flex;
	gap: 25px;
}

.nav-links a {
	text-decoration: none;
	color: white;
	font-size: 15px;
	transition: 0.3s;
}

.nav-links a:hover {
	color: #ff3b75;
}


.cart-container {
	width: 90%;
	max-width: 1100px;
	margin: 45px auto;
}

.cart-heading {
	margin-bottom: 30px;
}

.cart-heading h1 {
	font-size: 32px;
	margin-bottom: 8px;
}

.cart-heading p {
	color: #aaaab5;
}


.cart-box {
	background-color: #222228;
	border: 1px solid #34343d;
	border-radius: 12px;
	overflow: hidden;
}

.cart-header, .cart-item {
	display: grid;
	grid-template-columns: 2fr 1fr 1fr 1.5fr 1fr;
	align-items: center;
	gap: 15px;
	padding: 20px;
}

.cart-header {
	background-color: #2b2b33;
	color: #ff3b75;
	font-weight: bold;
}

.cart-item {
	border-bottom: 1px solid #383840;
}

.cart-item:last-child {
	border-bottom: none;
}

.item-name {
	font-size: 16px;
	font-weight: 600;
	overflow-wrap: anywhere;
}

.item-price {
	color: #ddd;
}

.item-total {
	color: #19c37d;
	font-weight: bold;
}


.quantity-box {
	display: flex;
	align-items: center;
	gap: 12px;
}

.quantity {
	min-width: 20px;
	text-align: center;
	font-weight: bold;
}

.qty-btn {
	width: 32px;
	height: 32px;
	border: none;
	border-radius: 6px;
	background-color: #ff3b75;
	color: white;
	font-size: 20px;
	cursor: pointer;
}

.qty-btn:hover {
	background-color: #e92d65;
}

.delete-btn {
	background: transparent;
	color: #ff3b75;
	border: 1px solid #ff3b75;
	border-radius: 6px;
	padding: 9px 12px;
	cursor: pointer;
}

.delete-btn:hover {
	background: transparent;
	color: #ff3b75;
	border-color: #e92d65;
}


.cart-footer {
	padding: 25px;
	border-top: 1px solid #383840;
}

.grand-total {
	display: flex;
	justify-content: space-between;
	align-items: center;
	font-size: 22px;
	font-weight: bold;
	margin-bottom: 25px;
}

.total-amount {
	color: #19c37d;
}

.cart-actions {
	display: flex;
	justify-content: space-between;
	gap: 15px;
	flex-wrap: wrap;
}

.add-more-btn, .checkout-btn {
	display: inline-block;
	padding: 13px 22px;
	text-decoration: none;
	border-radius: 7px;
	text-align: center;
	font-weight: bold;
	transition: 0.3s;
}

.add-more-btn {
	background-color: #34343d;
	color: white;
}

.add-more-btn:hover {
	background-color: #454550;
}

.checkout-btn {
	background-color: #ff3b75;
	color: white;
	border: none;
	cursor: pointer;
}

.checkout-btn:hover {
	background-color: #e92d65;
}


.empty-cart {
	text-align: center;
	padding: 65px 20px;
	background-color: #222228;
	border: 1px solid #34343d;
	border-radius: 12px;
}

.empty-cart h2 {
	margin-bottom: 12px;
}

.empty-cart p {
	color: #aaaab5;
	margin-bottom: 25px;
}

@media ( max-width : 750px) {
	.navbar {
		flex-direction: column;
		gap: 18px;
	}
	.nav-links {
		gap: 15px;
		flex-wrap: wrap;
		justify-content: center;
	}
	.cart-container {
		width: 95%;
		margin: 25px auto;
	}
	.cart-box {
		overflow-x: auto;
	}
	.cart-header, .cart-item {
		min-width: 700px;
	}
	.cart-heading h1 {
		font-size: 26px;
	}
}
</style>
</head>

<body>


	<div class="navbar">

		<div class="logo">
			Foodie<span>Go</span>
		</div>

		<div class="nav-links">
			<a href="home">Home</a> <a href="login.html">Login</a> <a
				href="registration.html">Sign Up</a>


			<%
			String imageUrl = request.getContextPath() + "/images/im.jpg";
			%>


			<a href="${pageContext.request.contextPath}/profile"
				class="profile-link"> <img src="<%=imageUrl%>" alt="Profile"
				class="profile-icon"> Profile
			</a>


		</div>

	</div>


	<div class="cart-container">

		<div class="cart-heading">
			<h1>Your Cart</h1>
			<p>Review your selected food items</p>
		</div>

		<%
		Cart cart = (Cart) session.getAttribute("cart");

		Integer restaurantID = (Integer) session.getAttribute("restaurantID");

		BigDecimal grandTotal = BigDecimal.ZERO;

		if (cart != null && !cart.getItems().isEmpty()) {
		%>

		<div class="cart-box">

	
			<div class="cart-header">
				<div>Item</div>
				<div>Price</div>
				<div>Total</div>
				<div>Quantity</div>
				<div>Action</div>
			</div>

			<%
			for (CartItem item : cart.getItems().values()) {

				grandTotal = grandTotal.add(item.getTotalPrice());
			%>

		
			<div class="cart-item">

				<div class="item-name">
					<%=item.getName()%>
				</div>

				<div class="item-price">
					₹<%=item.getPrice()%>
				</div>

				<div class="item-total">
					₹<%=item.getTotalPrice()%>
				</div>

			
				<div class="quantity-box">

				
					<form action="cartServlet" method="post">

						<input type="hidden" name="menuId" value="<%=item.getMenuID()%>">
						<input type="hidden" name="restaurantID"
							value="<%=restaurantID%>">

						<%
						if (item.getQuantity() - 1 <= 0) {
						%>
						<input type="hidden" name="action" value="delete">
						<%
						} else {
						%>
						<input type="hidden" name="action" value="update"> <input
							type="hidden" name="quantity"
							value="<%=item.getQuantity() - 1%>">
						<%
						}
						%>
						<button class="qty-btn" type="submit"
							aria-label="Decrease quantity">−</button>

					</form>

					<span class="quantity"><%=item.getQuantity()%> </span>


					<form action="cartServlet" method="post">


						<input type="hidden" name="menuId" value="<%=item.getMenuID()%>">
						<input type="hidden" name="restaurantID"
							value="<%=restaurantID%>"> <input type="hidden"
							name="action" value="update"> <input type="hidden"
							name="quantity" value="<%=item.getQuantity() + 1%>">
						<button class="qty-btn" type="submit"
							aria-label="Increase quantity">+</button>


					</form>

				</div>

				
				<div>
					<form action="cartServlet" method="post">

						<input type="hidden" name="menuId" value="<%=item.getMenuID()%>">

						<input type="hidden" name="restaurantID"
							value="<%=restaurantID%>"> <input type="hidden"
							name="action" value="delete">

						<button type="submit" class="delete-btn">Remove</button>
					</form>
				</div>

			</div>

			<%
			}
			%>

		
			<div class="cart-footer">

				<div class="grand-total">
					<span>Grand Total</span> <span class="total-amount"> ₹<%=grandTotal%>
					</span>
				</div>

				<div class="cart-actions">


					<a class="add-more-btn" href="callRestaurantServlet">← Add More
						Items</a> <a class="checkout-btn" href="checkout.jsp">Proceed to
						Checkout →</a>

				</div>

			</div>

		</div>

		<%
		} else {
		%>

	
		<div class="empty-cart">

			<h2>Your cart is empty</h2>

			<p>Please add some delicious food items from the menu.</p>

			<a class="checkout-btn" href="callRestaurantServlet"> Browse
				Restaurants</a>




		</div>

		<%
		}
		%>

	</div>

</body>
</html>
