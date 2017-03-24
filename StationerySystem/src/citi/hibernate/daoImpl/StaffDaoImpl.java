package citi.hibernate.daoImpl;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Repository;

import citi.hibernate.dao.StaffDao;
import citi.hibernate.entity.Staff;
@Repository
public class StaffDaoImpl implements StaffDao {

	@Override
	public Staff getStaff(HttpSession session) {
		// TODO Auto-generated method stub
		return null;
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
