package citi.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.google.gson.JsonArray;

import citi.hibernate.entity.Orders;


public interface OrdersService {

	List<Orders> getOrders();

	String saveOrders(JsonArray orderJsonArray);

	String submitOrders(JsonArray orderJsonArray, HttpSession session);

}
