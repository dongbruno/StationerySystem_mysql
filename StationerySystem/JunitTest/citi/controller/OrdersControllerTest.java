package citi.controller;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.junit.Test;

import com.google.gson.Gson;

import citi.hibernate.entity.Orders;
import citi.service.OrdersService;

public class OrdersControllerTest {
	@Resource
	OrdersService ordersServiceImpl;
	@Test
	public void testGetOrders(HttpSession session) {
//	List<Orders> result = ordersServiceImpl.getOrders(session);
//	Gson gson=new Gson();
//    String obj=gson.toJson(result);
//    System.out.println(obj);
		
	}

}
