package citi.controller.staff;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import citi.controller.stationery.StationeryController;
import citi.hibernate.entity.Staff;
import citi.service.staff.StaffService;
import citi.service.stationery.StationeryService;

public class StaffController {
private static final Log logger = LogFactory.getLog(StaffController.class);
	
	@Resource(name = "userServiceImpl")
	StaffService userServiceImpl;
	@RequestMapping(value = "/getUser", method = RequestMethod.GET)
	@ResponseBody
	public Object getUser(HttpSession session){//servlet-api.jar
		Staff result = userServiceImpl.getStaff(session);
		if(logger.isDebugEnabled()){
			logger.debug("getStaff="+result);
		}
		return result;
	}

}
