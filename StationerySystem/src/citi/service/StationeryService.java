package citi.service;

import java.util.List;

import citi.hibernate.entity.Orders;
import citi.hibernate.entity.Stationery;


public interface StationeryService {

	int insertStationery(Stationery stationery);
	
	List<Stationery> getStationery();


}
