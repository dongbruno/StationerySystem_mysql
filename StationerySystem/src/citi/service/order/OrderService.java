package citi.service.order;

import java.util.List;

import citi.entity.Order;

public interface OrderService {

	List<Order> getOrders();

	String saveOrders();

	String submitOrders();

}
