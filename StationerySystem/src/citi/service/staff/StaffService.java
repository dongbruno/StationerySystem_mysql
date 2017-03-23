package citi.service.staff;

import javax.servlet.http.HttpSession;

import citi.hibernate.entity.Staff;


public interface StaffService {
//servlet-api.jar
	Staff getStaff(HttpSession session);

}
