package citi.service.order;

import java.util.List;

import citi.hibernate.entity.Orders;


public interface OrderService {

	List<Orders> getOrders();

	String saveOrders();

	String submitOrders();

}
