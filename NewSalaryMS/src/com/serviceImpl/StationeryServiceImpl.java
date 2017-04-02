package com.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hibernate.dao.StationeryDao;
import com.hibernate.entity.Stationery;
import com.hibernate.util.HibernateUtil;
import com.service.StationeryService;

@Service
public class StationeryServiceImpl implements StationeryService {
	@Resource
	StationeryDao stationeryDaoImpl;
	@Override
	public List<Stationery> getStationery() {
		// TODO Auto-generated method stub
		HibernateUtil.openSession();
		List<Stationery> result = stationeryDaoImpl.getStationery();
    	HibernateUtil.closeSession();
		return result;
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
