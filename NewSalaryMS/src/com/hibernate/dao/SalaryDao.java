package com.hibernate.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.hibernate.entity.Salary;


public interface SalaryDao {
	boolean saveOrders(int stationeryId, int quantity, HttpSession session);

	boolean submitOrders(int stationeryId, int quantity, HttpSession session);

	List<Salary> getOrders(HttpSession session);

	List selectOrdersInLocation(String location);

	List selectOrdersInUnitAndLocation(String location, String unit);

	List<String> selectUnitsInLocation(String location);

	List<String> selectTeamsInUnitAndLocation(String location, String unit);

	List<String> selectStaffsInTeamAndUnitAndLocation(String location, String unit, String team);

	List selectOrdersOfStaffInTeamAndUnitAndLocation(String staff);
}
