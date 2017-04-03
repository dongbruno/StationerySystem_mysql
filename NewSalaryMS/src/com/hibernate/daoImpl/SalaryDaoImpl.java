package com.hibernate.daoImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import com.hibernate.dao.SalaryDao;
import com.hibernate.entity.Staff;
import com.hibernate.util.HibernateUtil;

@Repository
public class SalaryDaoImpl implements SalaryDao {
	@Resource
	StaffDao StaffDaoImpl;

	@Override
	public List<Salary> getSalary(HttpSession session) {
		// TODO Auto-generated method stub
		Session sessionHibernate = HibernateUtil.getSession();
		//String queryString = "select Salary.Staff.StaffId as StaffId,Salary.Staff.name as name,Salary.Staff.price as price,Salary.quantity as quantity,Salary.Staff.standard as standard,Salary.Staff.kind as kind from Salary as Salary where Salary.staff.soeId = ?";
		String queryString = "select new Salary(Salary.orderId,staff,Staff,Salary.quantity,Salary.date) from Salary Salary left join Salary.staff staff left join Salary.Staff Staff where staff.soeId = ?";
		String soeId = (String) session.getAttribute("soeId");
		List<Salary> Salary = sessionHibernate.createQuery(queryString).setParameter(0, soeId).list();
		return Salary;
	}

	@Override
	public boolean saveSalary(int StaffId, int quantity, HttpSession session) {
		// TODO Auto-generated method stub
		Session session2 = HibernateUtil.getSession();
		int orderId = selectOrder(StaffId, (String) session.getAttribute("soeId"));
		if (orderId > 0) {
			updateOrder(orderId, quantity);
		} else {
			session2.beginTransaction();
			session2.save(new Salary((Staff) session.getAttribute("staff"), StaffDaoImpl.findById(StaffId),
					quantity, new Date()));
			session2.getTransaction().commit();
		}
		return true;
	}

	@Override
	public boolean submitSalary(int StaffId, int quantity, HttpSession session) {
		// TODO Auto-generated method stub
		Session session2 = HibernateUtil.getSession();
		int orderId = selectOrder(StaffId, (String) session.getAttribute("soeId"));
		if (orderId > 0) {
			addOrder(orderId, quantity);
		} else {
			session2.beginTransaction();
			session2.save(new Salary((Staff) session.getAttribute("staff"), StaffDaoImpl.findById(StaffId),
					quantity, new Date()));
			session2.getTransaction().commit();
		}

		return true;
	}

	private int selectOrder(int StaffId, String soeId) {
		Session session = HibernateUtil.getSession();
		String queryString = "select Salary.orderId from Salary Salary left join Salary.Staff Staff left join Salary.staff staff where Staff.StaffId = ? and staff.soeId = ?";
		Object orderId = session.createQuery(queryString).setParameter(0, StaffId).setParameter(1, soeId)
				.uniqueResult();
		if (orderId == null) {
			return 0;
		} else {
			return (int) (orderId);
		}

	}

	private boolean updateOrder(int orderId, int quantity) {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		String queryString = "update Salary Salary set Salary.quantity = ? where Salary.orderId = ?";
		Query query = session.createQuery(queryString).setParameter(0, quantity).setParameter(1, orderId);
		query.executeUpdate();
		session.getTransaction().commit();
		return true;
	}

	private boolean addOrder(int orderId, int quantity) {
		Session session = HibernateUtil.getSession();
		String oldQueryString = "select Salary.quantity as orderId from Salary Salary where Salary.orderId = ?";
		int oldQuantity = (int) session.createQuery(oldQueryString).setParameter(0, orderId).uniqueResult();
		quantity += oldQuantity;

		session.beginTransaction();
		String queryString = "update Salary Salary set Salary.quantity = ? where Salary.orderId = ?";
		Query query = session.createQuery(queryString).setParameter(0, quantity).setParameter(1, orderId);
		query.executeUpdate();
		session.getTransaction().commit();
		return true;
	}

	@Override
	public List selectSalaryInLocation(String location) {
		// TODO Auto-generated method stub
		Session sessionHibernate = HibernateUtil.getSession();
		//String queryString = "select Salary.Staff.StaffId as StaffId,Salary.Staff.name as name,Salary.Staff.price as price,Salary.quantity as quantity,Salary.Staff.standard as standard,Salary.Staff.kind as kind from Salary as Salary where Salary.staff.soeId = ?";
		String queryString = "select Staff.kind,Staff.name,Staff.standard,Staff.price,sum(Salary.quantity) from Salary Salary left join Salary.staff staff left join Salary.Staff Staff where staff.location = ? group by Staff.StaffId";
		List Salary = sessionHibernate.createQuery(queryString).setParameter(0, location).list();
		return Salary;
	}

	@Override
	public List selectSalaryInUnitAndLocation(String location, String unit) {
		// TODO Auto-generated method stub
		Session sessionHibernate = HibernateUtil.getSession();
		//String queryString = "select Salary.Staff.StaffId as StaffId,Salary.Staff.name as name,Salary.Staff.price as price,Salary.quantity as quantity,Salary.Staff.standard as standard,Salary.Staff.kind as kind from Salary as Salary where Salary.staff.soeId = ?";
		String queryString = "select Staff.kind,Staff.name,Staff.standard,Staff.price,sum(Salary.quantity) from Salary Salary left join Salary.staff staff left join Salary.Staff Staff where staff.location = ? and staff.unit = ? group by Staff.StaffId";
		List Salary = sessionHibernate.createQuery(queryString).setParameter(0, location).setParameter(1, unit).list();
		return Salary;
	}

	@Override
	public List<String> selectUnitsInLocation(String location) {
		// TODO Auto-generated method stub
		Session sessionHibernate = HibernateUtil.getSession();
		String queryString = "select distinct staff.unit from Salary Salary left join Salary.staff staff left join Salary.Staff Staff where staff.location = ?";
		List<String> units = sessionHibernate.createQuery(queryString).setParameter(0, location).list();
		return units;
	}

	@Override
	public List<String> selectTeamsInUnitAndLocation(String location, String unit) {
		// TODO Auto-generated method stub
		Session sessionHibernate = HibernateUtil.getSession();
		String queryString = "select distinct staff.team from Salary Salary left join Salary.staff staff left join Salary.Staff Staff where staff.location = ? and staff.unit = ?";
		List<String> teams = sessionHibernate.createQuery(queryString).setParameter(0, location).setParameter(1, unit).list();
		return teams;
	}

	@Override
	public List<String> selectStaffsInTeamAndUnitAndLocation(String location, String unit, String team) {
		// TODO Auto-generated method stub
		Session sessionHibernate = HibernateUtil.getSession();
		String queryString = "select distinct staff.soeId from Salary Salary left join Salary.staff staff left join Salary.Staff Staff where staff.location = ? and staff.unit = ? and staff.team = ?";
		List<String> staffs = sessionHibernate.createQuery(queryString).setParameter(0, location).setParameter(1, unit).setParameter(2, team).list();
		return staffs;
	}

	@Override
	public List selectSalaryOfStaffInTeamAndUnitAndLocation(String staff) {
		// TODO Auto-generated method stub
		Session sessionHibernate = HibernateUtil.getSession();
		String queryString = "select staff.name,staff.soeId,Salary.quantity,Staff.standard,Staff.name from Salary Salary left join Salary.staff staff left join Salary.Staff Staff where staff.soeId = ? ";
		List Salary = sessionHibernate.createQuery(queryString).setParameter(0, staff).list();
		return Salary;
	}

}
