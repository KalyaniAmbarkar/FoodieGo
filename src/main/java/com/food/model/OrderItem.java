package com.food.model;

import java.math.BigDecimal;

public class OrderItem {

	private int orderItemID;
	private int orderID;
	private int menuID;
	private int quantity;
	private BigDecimal itemTotal;

	public OrderItem() {

	}

	public OrderItem(int orderItemID, int orderID, int menuID, int quantity, BigDecimal itemTotal) {
		super();
		this.orderItemID = orderItemID;
		this.orderID = orderID;
		this.menuID = menuID;
		this.quantity = quantity;
		this.itemTotal = itemTotal;
	}

	public int getOrderItemID() {
		return orderItemID;
	}

	public int getOrderID() {
		return orderID;
	}

	public int getMenuID() {
		return menuID;
	}

	public int getQuantity() {
		return quantity;
	}

	public BigDecimal getItemTotal() {
		return itemTotal;
	}

	public void setOrderItemID(int orderItemID) {
		this.orderItemID = orderItemID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public void setMenuID(int menuID) {
		this.menuID = menuID;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setItemTotal(BigDecimal itemTotal) {
		this.itemTotal = itemTotal;
	}

	@Override
	public String toString() {
		return "OrderItem [orderItemID=" + orderItemID + ", orderID=" + orderID + ", menuID=" + menuID + ", quantity="
				+ quantity + ", itemTotal=" + itemTotal + "]";
	}

}
