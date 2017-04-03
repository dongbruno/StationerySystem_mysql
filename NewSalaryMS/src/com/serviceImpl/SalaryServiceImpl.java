package com.serviceImpl;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.service.SalaryService;

import com.hibernate.dao.SalaryDao;
import com.hibernate.entity.Salary;
import com.hibernate.util.HibernateUtil;
@Service
public class SalaryServiceImpl implements SalaryService {
@Resource
SalaryDao SalaryDaoImpl;
	@Override
	public List<Salary> getSalary(HttpSession session) {
		// TODO Auto-generated method stub
		 HibernateUtil.openSession();
		 List<Salary> Salary = SalaryDaoImpl.getSalary(session);
         HibernateUtil.closeSession();
         return Salary;
	}

	@Override
	public boolean saveSalary(JsonArray orderJsonArray, HttpSession session) {
		// TODO Auto-generated method stub
		 for(int i=0;i<orderJsonArray.size();i++){
             JsonObject subObject=orderJsonArray.get(i).getAsJsonObject();
             int stationeryId=subObject.get("stationeryId").getAsInt();
             int quantity=subObject.get("quantity").getAsInt();
             HibernateUtil.openSession();
             SalaryDaoImpl.saveSalary(stationeryId, quantity, session);
             HibernateUtil.closeSession();
         }
		return true;
	}

	@Override
	public boolean submitSalary(JsonArray orderJsonArray, HttpSession session) {
		// TODO Auto-generated method stub
		 for(int i=0;i<orderJsonArray.size();i++){
             JsonObject subObject=orderJsonArray.get(i).getAsJsonObject();
             int stationeryId=subObject.get("stationeryId").getAsInt();
             int quantity=subObject.get("quantity").getAsInt();
             HibernateUtil.openSession();
             SalaryDaoImpl.submitSalary(stationeryId, quantity, session);
             HibernateUtil.closeSession();
         }
		return true;
	}
	@Override
	public List selectSalaryInLocation(String location) {
		// TODO Auto-generated method stub
		 HibernateUtil.openSession();
		 List Salary = SalaryDaoImpl.selectSalaryInLocation(location);
         HibernateUtil.closeSession();
         return Salary;
	}

	@Override
	public List<String> selectUnitsInLocation(String location) {
		// TODO Auto-generated method stub
		HibernateUtil.openSession();
		List<String> units = SalaryDaoImpl.selectUnitsInLocation(location);
        HibernateUtil.closeSession();
        return units;
	}

	@Override
	public List selectSalaryInUnitAndLocation(String location, String unit) {
		// TODO Auto-generated method stub
		 HibernateUtil.openSession();
		 List Salary = SalaryDaoImpl.selectSalaryInUnitAndLocation(location, unit);
         HibernateUtil.closeSession();
         return Salary;
	}

	@Override
	public List<String> selectTeamsInUnitAndLocation(String location, String unit) {
		// TODO Auto-generated method stub
		HibernateUtil.openSession();
		List<String> teams = SalaryDaoImpl.selectTeamsInUnitAndLocation(location, unit);
        HibernateUtil.closeSession();
        return teams;
	}

	@Override
	public List<String> selectStaffsInTeamAndUnitAndLocation(String location, String unit, String team) {
		// TODO Auto-generated method stub
		HibernateUtil.openSession();
		List<String> staffs = SalaryDaoImpl.selectStaffsInTeamAndUnitAndLocation(location, unit, team);
        HibernateUtil.closeSession();
        return staffs;
	}

	@Override
	public List selectSalaryOfStaffInTeamAndUnitAndLocation(String staff) {
		// TODO Auto-generated method stub
		 HibernateUtil.openSession();
		 List Salary = SalaryDaoImpl.selectSalaryOfStaffInTeamAndUnitAndLocation(staff);
         HibernateUtil.closeSession();
         return Salary;
	}
}
