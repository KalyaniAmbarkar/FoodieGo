package com.food.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Menu {

	private int menuID;
	private int restaurantID;
	private String itemName;
	private String description;
	private double rating;
	private BigDecimal price;
	private boolean isAvailable;
	private String category;
	private String imagePath;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private Timestamp deletedAt;

	public Menu() {

	}

	public Menu(int restaurantID, String itemName, String description, double rating, BigDecimal price,
			boolean isAvailable, String category, String imagePath, Timestamp createdAt, Timestamp updatedAt,
			Timestamp deletedAt) {
		super();

		this.restaurantID = restaurantID;
		this.itemName = itemName;
		this.description = description;
		this.rating = rating;
		this.price = price;
		this.isAvailable = isAvailable;
		this.category = category;
		this.imagePath = imagePath;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.deletedAt = deletedAt;

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

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getImagepath() {
		return imagePath;
	}

	public void setimagepath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Timestamp getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(Timestamp deletedAt) {
		this.deletedAt = deletedAt;
	}

	@Override
	public String toString() {
		return "Menu [menuID=" + menuID + ", restaurantID=" + restaurantID + ", itemName=" + itemName + ", description="
				+ description + ", rating=" + rating + ", price=" + price + ", isAvailable=" + isAvailable
				+ ", category=" + category + ",imagePath=" + imagePath + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + ", deletedAt=" + deletedAt + "]";
	}

}
