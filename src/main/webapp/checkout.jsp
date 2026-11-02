<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page
	import="java.util.Map,
                 java.math.BigDecimal,
                 com.food.model.Cart,
                 com.food.model.CartItem"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>FoodieGo - Checkout</title>

<style>
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
	font-family: Arial, sans-serif;
}

body {
	background-color: #0f1117;
	color: #fff;
}


.navbar {
	display: flex;
	justify-content: space-between;
	align-items: center;
	padding: 20px 7%;
	background-color: #222228;
	border-bottom: 1px solid #34343d;
	flex-wrap: wrap;
	gap: 15px;
}

.logo {
	font-size: 27px;
	font-weight: bold;
	color: #fff;
}

.logo span {
	color: #ff3b75;
}

.nav-links {
	display: flex;
	gap: 25px;
	flex-wrap: wrap;
}

.nav-links a {
	color: #fff;
	text-decoration: none;
	font-size: 15px;
}

.nav-links a:hover {
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


.checkout-container {
	width: 90%;
	max-width: 1430px;
	margin: 40px auto;
}

.checkout-heading {
	margin-bottom: 30px;
}

.checkout-heading h1 {
	font-size: 32px;
	margin-bottom: 10px;
}

.checkout-heading p {
	color: #aaaab5;
	line-height: 1.6;
}


.checkout-wrapper {
	display: grid;
	grid-template-columns: 1.5fr 1fr;
	gap: 28px;
	align-items: start;
}

.checkout-box {
	background-color: #222228;
	border: 1px solid #34343d;
	border-radius: 12px;
	padding: 28px;
}

.section-title {
	font-size: 22px;
	margin-bottom: 28px;
	color: #ff3b75;
}


.form-group {
	margin-bottom: 22px;
}

.form-group label {
	display: block;
	margin-bottom: 10px;
	font-size: 15px;
	font-weight: bold;
}

.form-group input, .form-group textarea, .form-group select {
	width: 100%;
	padding: 14px;
	border: 1px solid #41414c;
	border-radius: 7px;
	background-color: #19191f;
	color: #fff;
	font-size: 15px;
	outline: none;
}

.form-group input:focus, .form-group textarea:focus, .form-group select:focus
	{
	border-color: #ff3b75;
}

.form-group textarea {
	min-height: 120px;
	resize: vertical;
}

.form-group select option {
	background-color: #222228;
	color: #fff;
}


.summary-item {
	display: flex;
	justify-content: space-between;
	align-items: flex-start;
	gap: 15px;
	padding: 15px 0;
	border-bottom: 1px solid #383840;
}

.item-name {
	font-size: 15px;
	font-weight: bold;
	overflow-wrap: anywhere;
}

.item-quantity {
	color: #aaaab5;
	font-size: 13px;
	margin-top: 7px;
}

.item-price {
	white-space: nowrap;
}

.price-row {
	display: flex;
	justify-content: space-between;
	gap: 15px;
	padding: 13px 0;
	color: #d0d0d8;
}

.price-row span:last-child {
	white-space: nowrap;
}

.total-row {
	display: flex;
	justify-content: space-between;
	gap: 15px;
	padding-top: 20px;
	margin-top: 10px;
	border-top: 1px solid #484852;
	font-size: 21px;
	font-weight: bold;
}

.total-amount {
	color: #19c37d;
	white-space: nowrap;
}


.place-order-btn {
	display: block;
	width: 100%;
	padding: 16px;
	margin-top: 28px;
	border: none;
	border-radius: 8px;
	background-color: #ff3b75;
	color: #fff;
	font-size: 16px;
	font-weight: bold;
	cursor: pointer;
	transition: background-color 0.3s ease;
}

.place-order-btn:hover {
	background-color: #e92d65;
}


.back-link {
	display: block;
	width: 100%;
	margin-top: 15px;
	padding: 13px 15px;
	text-align: center;
	color: #ff3b75;
	background-color: transparent;
	border: 1px solid #ff3b75;
	border-radius: 30px;
	text-decoration: none;
	font-size: 15px;
	font-weight: bold;
	transition: all 0.3s ease;
}

.back-link:hover {
	background-color: #ff3b75;
	color: #fff;
}


.empty-cart {
	text-align: center;
	background-color: #222228;
	border: 1px solid #34343d;
	border-radius: 12px;
	padding: 50px 20px;
}

.empty-cart h2 {
	margin-bottom: 12px;
}

.empty-cart p {
	color: #aaaab5;
	margin-bottom: 25px;
}

.browse-btn {
	display: inline-block;
	padding: 12px 20px;
	border-radius: 7px;
	background-color: #ff3b75;
	color: #fff;
	text-decoration: none;
	font-weight: bold;
}

.browse-btn:hover {
	background-color: #e92d65;
}


@media ( max-width : 800px) {
	.checkout-wrapper {
		grid-template-columns: 1fr;
	}
	.navbar {
		flex-direction: column;
	}
	.nav-links {
		justify-content: center;
		gap: 15px;
	}
	.checkout-container {
		width: 94%;
		margin: 25px auto;
	}
	.checkout-heading h1 {
		font-size: 27px;
	}
}

@media ( max-width : 450px) {
	.checkout-box {
		padding: 18px;
	}
	.section-title {
		font-size: 20px;
	}
	.total-row {
		font-size: 18px;
	}
}
</style>
</head>

<body>

	
	<header class="navbar">
		<div class="logo">
			Foodie<span>Go</span>
		</div>

		<nav class="nav-links">
			<a href="home">Home</a> <a href="login.html">Login</a> <a
				href="registration.html">Sign Up</a> <a
				href="${pageContext.request.contextPath}/profile"
				class="profile-link"> <img src="images/im.jpg" alt="Profile">
				Profile
			</a>

		</nav>
	</header>

	<%
    Cart cart = (Cart) session.getAttribute("cart");
    Integer restaurantID = (Integer) session.getAttribute("restaurantID");

    BigDecimal grandTotal = BigDecimal.ZERO;
    BigDecimal deliveryFee = new BigDecimal("40.00");
    BigDecimal platformFee = new BigDecimal("5.00");

    boolean hasItems = cart != null
            && cart.getItems() != null
            && !cart.getItems().isEmpty();

    if (hasItems) {
        for (CartItem item : cart.getItems().values()) {
            grandTotal = grandTotal.add(item.getTotalPrice());
        }
    }

    BigDecimal finalAmount = grandTotal
            .add(deliveryFee)
            .add(platformFee);
%>

	<main class="checkout-container">

		<section class="checkout-heading">
			<h1>Checkout</h1>
			<p>Confirm your delivery details and place your order.</p>
		</section>

		<% if (!hasItems) { %>

		<section class="empty-cart">
			<h2>Your cart is empty</h2>
			<p>Add some delicious food before proceeding to checkout.</p>

			<a class="browse-btn"
				href="<%= request.getContextPath() %>/callRestaurantServlet">
				Browse Restaurants </a>
		</section>

		<% } else { %>

		<form action="checkout" method="post">

			<input type="hidden" name="restaurantID"
				value="<%= restaurantID != null ? restaurantID : "" %>">

			<div class="checkout-wrapper">

				
				<section class="checkout-box">

					<h2 class="section-title">Delivery Details</h2>

					<div class="form-group">
						<label for="customerName">Full Name<span
							style="color: red;">*</span></label> <input type="text" id="customerName"
							name="customerName" placeholder="Enter your full name"
							maxlength="100" autocomplete="name" required>
					</div>

					<div class="form-group">
						<label for="mobileNumber">Mobile Number<span
							style="color: red;">*</span></label> <input type="tel" id="mobileNumber"
							name="mobileNumber" placeholder="Enter your mobile number"
							pattern="[0-9]{10}" maxlength="10" inputmode="numeric"
							autocomplete="tel" title="Enter a valid 10-digit mobile number"
							required>
					</div>

					<div class="form-group">
						<label for="address">Delivery Address<span
							style="color: red;">*</span></label>

						<textarea id="address" name="address"
							placeholder="Enter your complete delivery address"
							maxlength="500" autocomplete="street-address" required></textarea>
					</div>

					<div class="form-group">
						<label for="paymentMode">Payment Method <span
							style="color: red;">*</span></label> <select id="paymentMode"
							name="paymentMode" required>

							<option value="">Select Payment Method</option>
							<option value="Cash On Delivery">Cash On Delivery</option>
							<option value="UPI">UPI</option>
							<option value="Debit Card">Debit Card</option>
							<option value="Credit Card">Credit Card</option>
							<option value="Net Banking">Net Banking</option>

						</select>
					</div>

				</section>

			
				<section class="checkout-box">

					<h2 class="section-title">Order Summary</h2>

					<% for (CartItem item : cart.getItems().values()) { %>

					<div class="summary-item">

						<div>
							<div class="item-name">
								<%= item.getName() %>
							</div>

							<div class="item-quantity">
								Quantity:
								<%= item.getQuantity() %>
							</div>
						</div>

						<div class="item-price">
							₹<%= item.getTotalPrice().setScale(
                                    2, java.math.RoundingMode.HALF_UP) %>
						</div>

					</div>

					<% } %>

					<div class="price-row">
						<span>Item Total</span> <span>₹<%= grandTotal.setScale(
                            2, java.math.RoundingMode.HALF_UP) %></span>
					</div>

					<div class="price-row">
						<span>Delivery Fee</span> <span>₹<%= deliveryFee.setScale(
                            2, java.math.RoundingMode.HALF_UP) %></span>
					</div>

					<div class="price-row">
						<span>Platform Fee</span> <span>₹<%= platformFee.setScale(
                            2, java.math.RoundingMode.HALF_UP) %></span>
					</div>

					<div class="total-row">
						<span>Grand Total</span> <span class="total-amount"> ₹<%= finalAmount.setScale(
                                2, java.math.RoundingMode.HALF_UP) %>
						</span>
					</div>
					<% session.setAttribute("finalAmount", finalAmount); %>

					<input type="hidden" name="restaurantID"
						value="<%= restaurantID %>"> <input type="hidden"
						name="totalAmount" value="<%= finalAmount %>">

					<button type="submit" class="place-order-btn">Place Order
						→</button>


					
					<a class="back-link" href="cart.jsp"> Back to Cart </a>

				</section>

			</div>

		</form>

		<% } %>

	</main>

</body>
</html>
