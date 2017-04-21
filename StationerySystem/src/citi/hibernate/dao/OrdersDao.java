package citi.hibernate.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import citi.hibernate.entity.Orders;

public interface OrdersDao {
	boolean saveOrders(int stationeryId, int quantity, HttpSession session);

	boolean submitOrders(int stationeryId, int quantity, HttpSession session);

	List<Orders> getOrders(HttpSession session);
	
	List<Orders> searchOrdersBySoeid(String soeId);

	List selectOrdersInLocation(String location);

	List selectOrdersInUnitAndLocation(String location, String unit);

	List<String> selectUnitsInLocation(String location);

	List<String> selectTeamsInUnitAndLocation(String location, String unit);

	List<String> selectStaffsInTeamAndUnitAndLocation(String location, String unit, String team);

	List selectOrdersOfStaffInTeamAndUnitAndLocation(String staff);
}
