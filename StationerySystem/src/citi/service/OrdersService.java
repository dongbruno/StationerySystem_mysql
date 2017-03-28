package citi.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.google.gson.JsonArray;

import citi.hibernate.entity.Orders;


public interface OrdersService {

	List<Orders> getOrders(HttpSession session);

	boolean submitOrders(JsonArray orderJsonArray, HttpSession session);

	boolean saveOrders(JsonArray orderJsonArray, HttpSession session);
}
