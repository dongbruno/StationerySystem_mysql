package citi.controller.stationery;

import org.springframework.stereotype.Controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.h2.store.fs.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import citi.hibernate.entity.Orders;
import citi.service.orders.OrdersService;
import citi.service.stationery.StationeryService;
@Controller
public class StationeryController {
	private static final Log logger = LogFactory.getLog(StationeryController.class);
	
	@Resource(name = "stationeryServiceImpl")
	StationeryService stationeryServiceImpl;
	@RequestMapping(value = "/getStationery", method = RequestMethod.GET)
	@ResponseBody
	public Object getOrders(){
		List<Orders> result = stationeryServiceImpl.getStationery();
		if(logger.isDebugEnabled()){
			logger.debug("getStationery="+result);
		}
		return result;
	}
	
			

}
