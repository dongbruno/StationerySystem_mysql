package citi.hibernate.daoImpl;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.hibernate.Query;
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
	public List<Orders> getOrders(HttpSession session) {
		// TODO Auto-generated method stub
		Session sessionHibernate = HibernateUtil.getSession();
		//String queryString = "select orders.stationery.stationeryId as stationeryId,orders.stationery.name as name,orders.stationery.price as price,orders.quantity as quantity,orders.stationery.standard as standard,orders.stationery.kind as kind from Orders as orders where orders.staff.soeId = ?";
		String queryString = "select new Orders(orders.orderId,staff,stationery,orders.quantity,orders.date) from Orders orders left join orders.staff staff left join orders.stationery stationery where staff.soeId = ?";
		String soeId = (String) session.getAttribute("soeId");
		List<Orders> orders = sessionHibernate.createQuery(queryString).setParameter(0, soeId).list();
		return orders;
	}

	@Override
	public boolean saveOrders(int stationeryId, int quantity, HttpSession session) {
		// TODO Auto-generated method stub
		Session session2 = HibernateUtil.getSession();
		int orderId = selectOrder(stationeryId, (String) session.getAttribute("soeId"));
		if (orderId > 0) {
			updateOrder(orderId, quantity);
		} else {
			session2.beginTransaction();
			session2.save(new Orders((Staff) session.getAttribute("staff"), stationeryDaoImpl.findById(stationeryId),
					quantity, new Date()));
			session2.getTransaction().commit();
		}
		return true;
	}

	@Override
	public boolean submitOrders(int stationeryId, int quantity, HttpSession session) {
		// TODO Auto-generated method stub
		Session session2 = HibernateUtil.getSession();
		int orderId = selectOrder(stationeryId, (String) session.getAttribute("soeId"));
		if (orderId > 0) {
			addOrder(orderId, quantity);
		} else {
			session2.beginTransaction();
			session2.save(new Orders((Staff) session.getAttribute("staff"), stationeryDaoImpl.findById(stationeryId),
					quantity, new Date()));
			session2.getTransaction().commit();
		}

		return true;
	}

	private int selectOrder(int stationeryId, String soeId) {
		Session session = HibernateUtil.getSession();
		String queryString = "select orders.orderId from Orders orders left join orders.stationery stationery left join orders.staff staff where stationery.stationeryId = ? and staff.soeId = ?";
		Object orderId = session.createQuery(queryString).setParameter(0, stationeryId).setParameter(1, soeId)
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
		String queryString = "update Orders orders set orders.quantity = ? where orders.orderId = ?";
		Query query = session.createQuery(queryString).setParameter(0, quantity).setParameter(1, orderId);
		query.executeUpdate();
		session.getTransaction().commit();
		return true;
	}

	private boolean addOrder(int orderId, int quantity) {
		Session session = HibernateUtil.getSession();
		String oldQueryString = "select orders.quantity as orderId from Orders orders where orders.orderId = ?";
		int oldQuantity = (int) session.createQuery(oldQueryString).setParameter(0, orderId).uniqueResult();
		quantity += oldQuantity;

		session.beginTransaction();
		String queryString = "update Orders orders set orders.quantity = ? where orders.orderId = ?";
		Query query = session.createQuery(queryString).setParameter(0, quantity).setParameter(1, orderId);
		query.executeUpdate();
		session.getTransaction().commit();
		return true;
	}

	@Override
	public List selectOrdersInLocation(String location) {
		// TODO Auto-generated method stub
		Session sessionHibernate = HibernateUtil.getSession();
		//String queryString = "select orders.stationery.stationeryId as stationeryId,orders.stationery.name as name,orders.stationery.price as price,orders.quantity as quantity,orders.stationery.standard as standard,orders.stationery.kind as kind from Orders as orders where orders.staff.soeId = ?";
		String queryString = "select stationery.kind,stationery.name,stationery.standard,stationery.price,sum(orders.quantity) from Orders orders left join orders.staff staff left join orders.stationery stationery where staff.location = ? group by stationery.stationeryId";
		List orders = sessionHibernate.createQuery(queryString).setParameter(0, location).list();
		return orders;
	}

	@Override
	public List selectOrdersInUnitAndLocation(String location, String unit) {
		// TODO Auto-generated method stub
		Session sessionHibernate = HibernateUtil.getSession();
		//String queryString = "select orders.stationery.stationeryId as stationeryId,orders.stationery.name as name,orders.stationery.price as price,orders.quantity as quantity,orders.stationery.standard as standard,orders.stationery.kind as kind from Orders as orders where orders.staff.soeId = ?";
		String queryString = "select stationery.kind,stationery.name,stationery.standard,stationery.price,sum(orders.quantity) from Orders orders left join orders.staff staff left join orders.stationery stationery where staff.location = ? and staff.unit = ? group by stationery.stationeryId";
		List orders = sessionHibernate.createQuery(queryString).setParameter(0, location).setParameter(1, unit).list();
		return orders;
	}

	@Override
	public List<String> selectUnitsInLocation(String location) {
		// TODO Auto-generated method stub
		Session sessionHibernate = HibernateUtil.getSession();
		String queryString = "select distinct staff.unit from Orders orders left join orders.staff staff left join orders.stationery stationery where staff.location = ?";
		List<String> units = sessionHibernate.createQuery(queryString).setParameter(0, location).list();
		return units;
	}

	@Override
	public List<String> selectTeamsInUnitAndLocation(String location, String unit) {
		// TODO Auto-generated method stub
		Session sessionHibernate = HibernateUtil.getSession();
		String queryString = "select distinct staff.team from Orders orders left join orders.staff staff left join orders.stationery stationery where staff.location = ? and staff.unit = ?";
		List<String> teams = sessionHibernate.createQuery(queryString).setParameter(0, location).setParameter(1, unit).list();
		return teams;
	}

	@Override
	public List<String> selectStaffsInTeamAndUnitAndLocation(String location, String unit, String team) {
		// TODO Auto-generated method stub
		Session sessionHibernate = HibernateUtil.getSession();
		String queryString = "select distinct staff.soeId from Orders orders left join orders.staff staff left join orders.stationery stationery where staff.location = ? and staff.unit = ? and staff.team = ?";
		List<String> staffs = sessionHibernate.createQuery(queryString).setParameter(0, location).setParameter(1, unit).setParameter(2, team).list();
		return staffs;
	}

	@Override
	public List selectOrdersOfStaffInTeamAndUnitAndLocation(String staff) {
		// TODO Auto-generated method stub
		Session sessionHibernate = HibernateUtil.getSession();
		String queryString = "select staff.name,staff.soeId,orders.quantity,stationery.standard,stationery.name from Orders orders left join orders.staff staff left join orders.stationery stationery where staff.soeId = ? ";
		List orders = sessionHibernate.createQuery(queryString).setParameter(0, staff).list();
		return orders;
	}

}
