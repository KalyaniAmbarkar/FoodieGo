package com.food.model;

import java.math.BigDecimal;

public class CartItem {
	private int menuID;
	private int restaurantID;
	private String name;
	private BigDecimal price;
	private int quantity;

	public CartItem() {

	}

	public CartItem(int menuID, int restaurantID, String name, BigDecimal price, int quantity) {
		super();
		this.menuID = menuID;
		this.restaurantID = restaurantID;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}

	public int getMenuID() {
		return menuID;
	}

	public void setMenuID(int menuID) {
		this.menuID = menuID;
	}

	public int getRestaurantID() {
		return restaurantID;
	}

	public void setRestaurantID(int restaurantID) {
		this.restaurantID = restaurantID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getTotalPrice() {
		return price.multiply(BigDecimal.valueOf(quantity));
	}

}
