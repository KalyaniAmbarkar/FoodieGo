package com.food.DAO;

import java.util.List;
import com.food.model.Orders;

public interface OrderDAO {

	int addOrder(Orders order);

	Orders getOrder(int orderID);

	void updateOrder(Orders order);

	void deleteOrder(int orderID);

	List<Orders> getAllOrders();

}
