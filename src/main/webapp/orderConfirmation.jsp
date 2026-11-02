<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>

<%
    List<Integer> orderIDs =
            (List<Integer>) session.getAttribute("checkoutOrderIDs");
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>FoodieGo - Order Confirmation</title>

<style>
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
	font-family: Arial, sans-serif;
}

body {
	background: #101117;
	color: white;
	min-height: 100vh;
	display: flex;
	flex-direction: column;
}


header {
	height: 84px;
	background: #151720;
	border-bottom: 1px solid #30313b;
	display: flex;
	justify-content: space-between;
	align-items: center;
	padding: 0 7%;
}

.logo {
	font-size: 38px;
	font-weight: bold;
	color: white;
	letter-spacing: -1px;
}

.logo span {
	color: #ff2874;
}

nav {
	display: flex;
	align-items: center;
	gap: 48px;
}

nav a {
	color: #f4f4f8;
	text-decoration: none;
	font-size: 17px;
	font-weight: 600;
}

nav a:first-child, nav a:hover {
	color: #ff2874;
}

main {
	flex: 1;
	display: flex;
	justify-content: center;
	align-items: center;
	padding: 54px 20px 60px;
}

.confirmation-card {
	width: 100%;
	max-width: 820px;
	background: linear-gradient(135deg, #1b1e29, #191b25);
	border: 1px solid #242733;
	border-radius: 13px;
	padding: 30px 35px 42px;
	text-align: center;
	box-shadow: 0 15px 40px rgba(0, 0, 0, 0.2);
}


.success-icon {
	width: 108px;
	height: 108px;
	background: #0bc879;
	border-radius: 50%;
	color: white;
	font-size: 69px;
	font-weight: normal;
	display: flex;
	justify-content: center;
	align-items: center;
	margin: 0 auto 19px;
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

h1 {
	font-size: 40px;
	color: #0bc879;
	margin-bottom: 16px;
}

.message {
	color: #b9c9e5;
	font-size: 21px;
	line-height: 1.5;
	margin-bottom: 25px;
}


.order-details {
	background: rgba(15, 17, 25, 0.45);
	border: 1px solid #292c39;
	border-radius: 11px;
	text-align: left;
	padding: 18px 30px;
	margin-bottom: 34px;
}

.detail-row {
	display: flex;
	align-items: center;
	gap: 28px;
	min-height: 76px;
}

.detail-icon {
	color: #ff2874;
	font-size: 34px;
	width: 34px;
	text-align: center;
}

.detail-label {
	color: #b9c9e5;
	font-size: 16px;
	margin-bottom: 5px;
}

.detail-value {
	color: #f5f5fa;
	font-size: 18px;
	font-weight: bold;
	overflow-wrap: anywhere;
}


.buttons {
	display: flex;
	justify-content: center;
	gap: 22px;
}

.btn {
	width: 48%;
	min-height: 60px;
	display: flex;
	justify-content: center;
	align-items: center;
	border-radius: 9px;
	text-decoration: none;
	font-size: 17px;
	font-weight: bold;
	transition: 0.25s;
}

.primary-btn {
	background: #ff2874;
	color: white;
	border: 1px solid #ff2874;
}

.primary-btn:hover {
	background: #e92066;
	color: white;
}

.secondary-btn {
	color: #ff2874;
	border: 2px solid #ff2874;
}

.secondary-btn:hover {
	background: #ff2874;
	color: white;
}

footer {
	min-height: 101px;
	background: #151720;
	border-top: 1px solid #30313b;
	display: flex;
	justify-content: center;
	align-items: center;
	color: #b9c9e5;
	font-size: 15px;
}


@media ( max-width : 650px) {
	header {
		height: auto;
		padding: 20px;
		flex-direction: column;
		gap: 20px;
	}
	.logo {
		font-size: 32px;
	}
	nav {
		gap: 18px;
		flex-wrap: wrap;
		justify-content: center;
	}
	nav a {
		font-size: 14px;
	}
	main {
		padding: 30px 14px;
	}
	.confirmation-card {
		padding: 28px 18px;
	}
	.success-icon {
		width: 85px;
		height: 85px;
		font-size: 54px;
	}
	h1 {
		font-size: 27px;
	}
	.message {
		font-size: 16px;
	}
	.order-details {
		padding: 15px;
	}
	.detail-row {
		gap: 16px;
	}
	.buttons {
		flex-direction: column;
		gap: 14px;
	}
	.btn {
		width: 100%;
		min-height: 52px;
		font-size: 15px;
	}
}
</style>
</head>

<body>

	<header>
		<div class="logo">
			&#127860; Foodie<span>Go</span>
		</div>

		<nav>
			<a href="home">Home</a> <a href="callRestaurantServlet">Restaurants</a>
			<a href="cart.jsp">Cart</a> <a href="#" class="profile-link"> <img
				src="images/im.jpg" alt="Profile"> Profile
			</a>
		</nav>
	</header>

	<main>
		<section class="confirmation-card">

			<div class="success-icon">&#10003;</div>

			<h1>Order Placed Successfully!</h1>

			<p class="message">
				Thank you for ordering with FoodieGo!<br> Your order has been
				placed successfully.<br> Get ready to enjoy your delicious
				food!
			</p>

			<div class="order-details">

				<div class="detail-row">
					<div class="detail-icon">&#128203;</div>
					<div>
						<div class="detail-label">Order ID(s):</div>
						<div class="detail-value">
							<%
                if (orderIDs != null && !orderIDs.isEmpty()) {
                    for (Integer id : orderIDs) {
            %>
							<div>
								Order #<%= id %></div>
							<%
                    }
                } else {
            %>
							Not available
							<%
                }
            %>
						</div>
					</div>
				</div>

				<div class="detail-row">
					<div class="detail-icon">&#10004;</div>
					<div>
						<div class="detail-label">Order Status:</div>
						<div class="detail-value">Pending</div>
					</div>
				</div>

				<div class="detail-row">
					<div class="detail-icon">&#128179;</div>
					<div>
						<div class="detail-label">Payment:</div>

						<div class="detail-value">
							<%= request.getParameter("paymentMethod") != null
        ? request.getParameter("paymentMethod")
        : "Not specified" %>
						</div>

					</div>
				</div>

			</div>

			<div class="buttons">
				<a href="cart.jsp" class="btn primary-btn"> &#128722;&nbsp;
					Continue Shopping </a> <a href="callRestaurantServlet"
					class="btn secondary-btn"> &#8962;&nbsp; Back to Home </a>
			</div>

		</section>
	</main>

	<footer> &copy; 2026 FoodieGo. All rights reserved. </footer>

</body>
</html>
