package citi.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import citi.hibernate.dao.StationeryDao;
import citi.hibernate.entity.Orders;
import citi.hibernate.entity.Stationery;
import citi.hibernate.util.HibernateUtil;
import citi.service.StationeryService;

public class StationeryServiceImpl implements StationeryService {
	@Resource(name="stationeryDaoImpl")
	StationeryDao stationeryDaoImpl;
	@Override
	public List<Orders> getStationery() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertStationery(Stationery stationery) {
		// TODO Auto-generated method stub
		HibernateUtil.openSession();
    	int id = stationeryDaoImpl.insertStationery(stationery);
    	HibernateUtil.closeSession();
		return id;
	}

}
