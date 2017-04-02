package com.service;

import javax.servlet.http.HttpSession;

import com.hibernate.entity.Staff;


public interface StaffService {
//servlet-api.jar
	Staff getStaff(HttpSession session);

	boolean insertStaff(Staff staff);
	
	String getNote();
	
	String getDeadline();
	
	boolean isExpired();

	Staff getStaffTest(boolean isDefault, HttpSession session);
}
