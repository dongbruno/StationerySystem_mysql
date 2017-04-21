package citi.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.google.gson.JsonArray;

import citi.hibernate.entity.Orders;


public interface OrdersService {

	List<Orders> getOrders(HttpSession session);
	
	List<Orders> searchOrdersBySoeid(String soeid);

	boolean submitOrders(JsonArray orderJsonArray, HttpSession session);

	boolean saveOrders(JsonArray orderJsonArray, HttpSession session);

	List selectOrdersInLocation(String location);

	List<String> selectUnitsInLocation(String location);

	List selectOrdersInUnitAndLocation(String location, String unit);

	List<String> selectTeamsInUnitAndLocation(String location, String unit);

	List<String> selectStaffsInTeamAndUnitAndLocation(String location, String unit, String team);

	List selectOrdersOfStaffInTeamAndUnitAndLocation(String staff);
}
