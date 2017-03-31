package com.hibernate.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.hibernate.entity.Staff;

public interface StaffDao {
	Staff getStaff(HttpSession session);

	boolean insertStaff(Staff staff);
	
	List findByExample(Staff instance);
	
	String getNote();
	
	String getDeadline();
}
