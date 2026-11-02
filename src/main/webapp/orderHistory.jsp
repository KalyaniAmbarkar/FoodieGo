<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List,com.food.model.OrderHistory"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FoodieGo - Order History</title>

<style>
body {
	margin: 0;
	font-family: Arial, sans-serif;
	background: #19191f;
	color: white;
}

header {
	background: #222228;
	padding: 20px 40px;
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

header a:hover {
	color: #ff3b75;
}

.container {
	max-width: 1000px;
	margin: 40px auto;
	padding: 20px;
}

h1 {
	color: #ff3b75;
}

.order-card {
	background: #222228;
	padding: 20px;
	margin: 15px 0;
	border-radius: 12px;
	border: 1px solid #38383f;
}

.order-card p {
	margin: 10px 0;
}

.status {
	color: #19a85b;
	font-weight: bold;
}

.empty {
	background: #222228;
	padding: 25px;
	border-radius: 10px;
	color: #ccc;
}
</style>
</head>

<body>

	<header>
		<div class="logo">FoodieGo</div>
		<nav>
			<a href="callRestaurantServlet">Home</a>
		</nav>
	</header>

	<div class="container">

		<h1>My Order History</h1>
		<p>View your previous orders and their status.</p>

		<%
        List<OrderHistory> historyList =
            (List<OrderHistory>) request.getAttribute("historyList");

        if (historyList != null && !historyList.isEmpty()) {
            for (OrderHistory history : historyList) {
    %>

		<div class="order-card">
			<p>
				<strong>Order ID:</strong>
				<%= history.getOrderID() %>
			</p>

			<p>
				<strong>Status:</strong> <span class="status"> <%= history.getStatus() %>
				</span>
			</p>

			<p>
				<strong>Date:</strong>
				<%= history.getChangedAt() == null
                ? "Not available"
                : history.getChangedAt() %>
			</p>
		</div>

		<%
            }
        } else {
    %>

		<div class="empty">No order history found.</div>

		<%
        }
    %>

	</div>

</body>
</html>