package citi.hibernate.daoImpl;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import citi.hibernate.dao.StationeryDao;
import citi.hibernate.entity.Orders;
import citi.hibernate.entity.Stationery;
import citi.hibernate.util.HibernateUtil;
@Repository
public class StationeryDaoImpl implements StationeryDao {
	private static final Log log = LogFactory.getLog(StationeryDaoImpl.class);
	
	@Override
	public int insertStationery(Stationery stationery) {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		session.save(stationery);
		session.getTransaction().commit();
		return stationery.getStationeryId();
	}

	@Override
	public List<Stationery> getStationery() {
			Session session = HibernateUtil.getSession();
			session.beginTransaction();
			List<Stationery> result = session.createQuery("from Stationery").list();
			session.getTransaction().commit();
			return result;
		
	}
	
	public Stationery findById(Integer id) {
		log.debug("getting Stationery instance with id: " + id);
		try {
			Stationery instance = (Stationery) HibernateUtil.getSession()
					.get("citi.hibernate.entity.Stationery", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

}
