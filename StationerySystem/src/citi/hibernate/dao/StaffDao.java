package citi.hibernate.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import citi.hibernate.entity.Staff;
import citi.hibernate.entity.SystemInfo;

public interface StaffDao {
	Staff getStaff(HttpSession session);

	boolean insertStaff(Staff staff);
	
	List findByExample(Staff instance);
	
	SystemInfo getSystemInfo();
}
