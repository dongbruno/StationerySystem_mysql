package citi.serviceImpl;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import citi.hibernate.entity.Staff;
import citi.service.StaffService;
@Service
public class StaffServiceImpl implements StaffService {

	@Override
	public Staff getStaff(HttpSession session) {
		// TODO Auto-generated method stub
		
		Staff staff = (Staff) session.getAttribute("staff");
		
		return staff;
	}

	@Override
	public String getNote() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDeadline() {
		// TODO Auto-generated method stub
		return null;
	}

}
