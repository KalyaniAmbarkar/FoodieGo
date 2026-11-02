package com.food.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {

	private Map<Integer, CartItem> items;

	public Cart() {
		items = new HashMap<Integer, CartItem>();

	}

	public Map<Integer, CartItem> getItems() {
		return items;
	}

	public void addItem(CartItem cartItem) {

		int menuID = cartItem.getMenuID();

		if (items.containsKey(menuID)) {

			CartItem existingCartItem = items.get(menuID);
			existingCartItem.setQuantity(existingCartItem.getQuantity() + cartItem.getQuantity());

		} else {
			items.put(menuID, cartItem);
		}
	}

	public void updateItem(int itemId, int quantity) {
		if (items.containsKey(itemId)) {
			if (quantity > 0) {
				CartItem existingItem = items.get(itemId);
				existingItem.setQuantity(quantity);
			} else {
				items.remove(itemId);
			}
		}
	}

	public void removeItem(int itemId) {
		items.remove(itemId);
	}
}
