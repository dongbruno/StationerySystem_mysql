package com.service;

import java.util.List;

import com.hibernate.entity.Orders;
import com.hibernate.entity.Stationery;


public interface StationeryService {

	int insertStationery(Stationery stationery);
	
	List<Stationery> getStationery();


}
