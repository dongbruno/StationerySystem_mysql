package citi.controller;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import citi.hibernate.entity.Orders;
import citi.service.OrdersService;
@Controller
public class OrdersController {
	private static final Log logger = LogFactory.getLog(OrdersController.class);
	
	@Resource
	OrdersService ordersServiceImpl;
	@RequestMapping(value = "/getOrders", method = RequestMethod.GET)
	@ResponseBody
	public Object getOrders(HttpSession session){
		List<Object> result = ordersServiceImpl.getOrders(session);
		if(logger.isDebugEnabled()){
			logger.debug("getOrders="+result);
		}
		return result;
	}
	
	@RequestMapping(value = "/saveOrders", method = RequestMethod.POST)
	@ResponseBody
	public String saveOrders(@RequestBody JsonObject orderJson, HttpSession session){
		JsonArray orderJsonArray=orderJson.get("order").getAsJsonArray();
		boolean result = ordersServiceImpl.saveOrders(orderJsonArray, session);
		if(logger.isDebugEnabled()){
			logger.debug("saveOrders="+result);
		}
		return "success";
	}
	@RequestMapping(value = "/submitOrders", method = RequestMethod.POST)
	@ResponseBody
	public String submitOrders(@RequestBody String orderJson, HttpSession session){
		JsonArray orderJsonArray=new JsonParser().parse(orderJson).getAsJsonArray();
		boolean result = ordersServiceImpl.submitOrders(orderJsonArray, session);
		if(logger.isDebugEnabled()){
			logger.debug("submitOrders="+result);
		}
		return "success";
	}
	

}
