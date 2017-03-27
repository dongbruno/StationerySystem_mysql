package citi.hibernate.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import citi.hibernate.entity.Orders;

public interface OrdersDao {
	List<Orders> getOrders();

	String saveOrders();


	String submitOrders(int stationeryId, int quantity, HttpSession session);
}
