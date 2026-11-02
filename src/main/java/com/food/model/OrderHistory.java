package com.food.model;

import java.sql.Timestamp;

public class OrderHistory {

	private int orderHistoryID;
	private int orderID;
	private int userID;
	private String status;
	private Timestamp changedAt;

	public OrderHistory() {
	}

	public OrderHistory(int orderID, int userID, String status, Timestamp changedAt) {
		super();
		this.orderID = orderID;
		this.userID = userID;
		this.status = status;
		this.changedAt = changedAt;
	}

	public int getOrderHistoryID() {
		return orderHistoryID;
	}

	public void setOrderHistoryID(int orderHistoryID) {
		this.orderHistoryID = orderHistoryID;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getChangedAt() {
		return changedAt;
	}

	public void setChangedAt(Timestamp changedAt) {
		this.changedAt = changedAt;
	}

	@Override
	public String toString() {
		return "OrderHistory [orderHistoryID=" + orderHistoryID + ", orderID=" + orderID + ", userID=" + userID
				+ ", status=" + status + ", changedAt=" + changedAt + "]";
	}

}
