package com.food.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Orders {

	private int orderID;
	private int userID;
	private int restaurantID;
	private Timestamp orderDate;
	private BigDecimal totalAmount;
	private String status;
	private String paymentMethod;

	public Orders() {

	}

	public Orders(int userID, int restaurantID, Timestamp orderDate, BigDecimal totalAmount, String status,
			String paymentMethod) {
		super();
		this.userID = userID;
		this.restaurantID = restaurantID;
		this.orderDate = orderDate;
		this.totalAmount = totalAmount;
		this.status = status;
		this.paymentMethod = paymentMethod;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getRestaurantID() {
		return restaurantID;
	}

	public void setRestaurantID(int restaurantID) {
		this.restaurantID = restaurantID;
	}

	public Timestamp getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	@Override
	public String toString() {
		return "OrderTable [orderID=" + orderID + ", userID=" + userID + ", restaurantID=" + restaurantID
				+ ", orderDate=" + orderDate + ", totalAmount=" + totalAmount + ", status=" + status
				+ ", paymentMethod=" + paymentMethod + "]";
	}

}
