package citi.service;

import java.util.List;

import citi.hibernate.entity.Orders;


public interface OrdersService {

	List<Orders> getOrders();

	String saveOrders();

	String submitOrders();

}
