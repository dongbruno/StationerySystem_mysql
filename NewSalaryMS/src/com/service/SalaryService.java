package com.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.google.gson.JsonArray;

import com.hibernate.entity.Salary;


public interface SalaryService {

	List<Salary> getOrders(HttpSession session);

	boolean submitOrders(JsonArray orderJsonArray, HttpSession session);

	boolean saveOrders(JsonArray orderJsonArray, HttpSession session);

	List selectOrdersInLocation(String location);

	List<String> selectUnitsInLocation(String location);

	List selectOrdersInUnitAndLocation(String location, String unit);

	List<String> selectTeamsInUnitAndLocation(String location, String unit);

	List<String> selectStaffsInTeamAndUnitAndLocation(String location, String unit, String team);

	List selectOrdersOfStaffInTeamAndUnitAndLocation(String staff);
}
