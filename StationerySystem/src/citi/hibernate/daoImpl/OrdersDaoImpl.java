package citi.hibernate.daoImpl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import citi.hibernate.dao.OrdersDao;
import citi.hibernate.dao.StationeryDao;
import citi.hibernate.entity.Orders;
import citi.hibernate.entity.Staff;
import citi.hibernate.util.HibernateUtil;
@Repository
public class OrdersDaoImpl implements OrdersDao {
	@Resource
	StationeryDao stationeryDaoImpl;
	@Override
	public List<Orders> getOrders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String saveOrders() {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public String submitOrders(int stationeryId, int quantity, HttpSession session) {
		// TODO Auto-generated method stub
		Session session2 = HibernateUtil.getSession();
		session2.beginTransaction();
		session2.save(new Orders((Staff) session.getAttribute("staff"), stationeryDaoImpl.findById(stationeryId), quantity, new Date()));
		session2.getTransaction().commit();
		return "true";
	}

}
