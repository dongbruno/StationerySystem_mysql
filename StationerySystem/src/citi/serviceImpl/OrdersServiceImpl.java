package citi.serviceImpl;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import citi.hibernate.dao.OrdersDao;
import citi.hibernate.entity.Orders;
import citi.hibernate.util.HibernateUtil;
import citi.service.OrdersService;
@Service
public class OrdersServiceImpl implements OrdersService {
@Resource
OrdersDao ordersDaoImpl;
	@Override
	public List<Object> getOrders(HttpSession session) {
		// TODO Auto-generated method stub
		 HibernateUtil.openSession();
		 List<Object> orders = ordersDaoImpl.getOrders(session);
         HibernateUtil.closeSession();
         return orders;
	}


	@Override
	public boolean saveOrders(JsonArray orderJsonArray, HttpSession session) {
		// TODO Auto-generated method stub
		 for(int i=0;i<orderJsonArray.size();i++){
             JsonObject subObject=orderJsonArray.get(i).getAsJsonObject();
             int stationeryId=subObject.get("stationeryId").getAsInt();
             int quantity=subObject.get("quantity").getAsInt();
             HibernateUtil.openSession();
             ordersDaoImpl.saveOrders(stationeryId, quantity, session);
             HibernateUtil.closeSession();
         }
		return true;
	}

	@Override
	public boolean submitOrders(JsonArray orderJsonArray, HttpSession session) {
		// TODO Auto-generated method stub
		 for(int i=0;i<orderJsonArray.size();i++){
             JsonObject subObject=orderJsonArray.get(i).getAsJsonObject();
             int stationeryId=subObject.get("stationeryId").getAsInt();
             int quantity=subObject.get("quantity").getAsInt();
             HibernateUtil.openSession();
             ordersDaoImpl.submitOrders(stationeryId, quantity, session);
             HibernateUtil.closeSession();
         }
		return true;
	}

}
