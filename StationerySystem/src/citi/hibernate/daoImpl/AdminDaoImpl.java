package citi.hibernate.daoImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import citi.hibernate.dao.AdminDao;
import citi.hibernate.util.HibernateUtil;
@Repository
public class AdminDaoImpl implements AdminDao {

	@Override
	public String setNote(String note, String soeId) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		String queryString = "update SystemInfo s set s.note = ?, s.date = ?, s.operator = ? where s.systemId = ?";
		Query query = session.createQuery(queryString).setParameter(0, note).setParameter(1, new Date()).setParameter(2, soeId).setParameter(3, 1);
		query.executeUpdate();
		session.getTransaction().commit();
		return "success";
	}

	@Override
	public String setDeadline(String deadline, String soeId) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		String queryString = "update SystemInfo s set s.deadline = ?, s.date = ?, s.operator = ? where s.systemId = ?";
		Query query = session.createQuery(queryString).setParameter(0, toDateFromStr(deadline)).setParameter(1, new Date()).setParameter(2, soeId).setParameter(3, 1);
		query.executeUpdate();
		session.getTransaction().commit();
		return "success";
	}
	private Date toDateFromStr(String sourceTime){
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
		Date date = null;
	         try {
			 date = format.parse(sourceTime);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return date;
	}

}
