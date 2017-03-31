package com.hibernate.dao;
import java.util.List;

import com.hibernate.entity.Orders;
import com.hibernate.entity.Stationery;

public interface StationeryDao {
	
	int insertStationery(Stationery stationery);
	
	List<Stationery> getStationery();
	
	Stationery findById(Integer id);
	
}
