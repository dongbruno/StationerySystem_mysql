package citi.service;

import javax.servlet.http.HttpSession;

import citi.hibernate.entity.Staff;


public interface StaffService {
//servlet-api.jar
	Staff getStaff(HttpSession session);

	String getNote();
	
	String getDeadline();
}
