package citi.service;

import javax.servlet.http.HttpSession;

import citi.hibernate.entity.Staff;
import citi.hibernate.entity.SystemInfo;


public interface StaffService {
//servlet-api.jar
	Staff getStaff(HttpSession session);

	boolean insertStaff(Staff staff);
	
	SystemInfo getSystemInfo();
	
	boolean isExpired();

	Staff getStaffTest(boolean isDefault, HttpSession session);
}
