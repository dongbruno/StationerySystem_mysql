package com.hibernate.daoImpl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;

import com.hibernate.dao.StaffDao;
import com.hibernate.entity.Staff;
import com.hibernate.util.HibernateUtil;
import com.serviceImpl.StaffServiceImpl;
@Repository
public class StaffDaoImpl implements StaffDao {
	private static final Log log = LogFactory.getLog(StaffDaoImpl.class);
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

	@Override
	public boolean insertStaff(Staff staff) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		session.save(staff);
		session.getTransaction().commit();
		return true;
	}
	public List findByExample(Staff instance) {
		log.debug("finding Staff instance by example");
		try {
			List results = HibernateUtil.getSession().createCriteria("citi.hibernate.entity.Staff")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

}
