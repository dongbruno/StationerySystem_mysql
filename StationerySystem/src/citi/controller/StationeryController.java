package citi.controller;

import org.springframework.stereotype.Controller;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import citi.hibernate.entity.Stationery;
import citi.service.StationeryService;
@Controller
public class StationeryController {
	private static final Log logger = LogFactory.getLog(StationeryController.class);
	
	@Resource
	StationeryService stationeryServiceImpl;
	@RequestMapping(value = "/getStationery", method = RequestMethod.GET)
	@ResponseBody
	public Object getStationery(){
		List<Stationery> result = stationeryServiceImpl.getStationery();
		if(logger.isDebugEnabled()){
			logger.debug("getStationery="+result);
		}
		return result;
	}
	
			

}
