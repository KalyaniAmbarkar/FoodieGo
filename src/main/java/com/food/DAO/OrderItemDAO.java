package com.food.DAO;

import java.util.List;

import com.food.model.OrderItem;

public interface OrderItemDAO {

	boolean addOrderItem(OrderItem orderItem);

	OrderItem getOrderItem(int orderItemID);

	void updateOrderItem(OrderItem orderItem);

	void deleteOrderItem(int orderItemID);

	List<OrderItem> getAllOrderItems();

}
