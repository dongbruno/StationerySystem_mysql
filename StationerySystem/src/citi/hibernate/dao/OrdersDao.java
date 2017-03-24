package citi.hibernate.dao;

import java.util.List;

import citi.hibernate.entity.Orders;

public interface OrdersDao {
	List<Orders> getOrders();

	String saveOrders();

	String submitOrders();
}
