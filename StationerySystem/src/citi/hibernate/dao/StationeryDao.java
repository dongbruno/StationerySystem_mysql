package citi.hibernate.dao;
import java.util.List;

import citi.hibernate.entity.Orders;
import citi.hibernate.entity.Stationery;

public interface StationeryDao {
	
	int insertStationery(Stationery stationery);
	
	List<Stationery> getStationery();
	
	Stationery findById(Integer id);
	
}
