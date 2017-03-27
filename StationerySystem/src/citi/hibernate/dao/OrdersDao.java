package citi.hibernate.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import citi.hibernate.entity.Orders;

public interface OrdersDao {
	boolean saveOrders(int stationeryId, int quantity, HttpSession session);

	boolean submitOrders(int stationeryId, int quantity, HttpSession session);

	List<Object> getOrders(HttpSession session);
}
