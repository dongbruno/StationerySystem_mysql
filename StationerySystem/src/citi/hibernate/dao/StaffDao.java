package citi.hibernate.dao;

import javax.servlet.http.HttpSession;

import citi.hibernate.entity.Staff;

public interface StaffDao {
	Staff getStaff(HttpSession session);

	String getNote();
	
	String getDeadline();
}
