package citi.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import citi.hibernate.entity.Staff;
import citi.hibernate.entity.SystemInfo;
import citi.service.StaffService;
@Controller
public class StaffController {
private static final Log logger = LogFactory.getLog(StaffController.class);
	
	@Resource
	StaffService staffServiceImpl;
	
	@RequestMapping(value = "/getStaff", method = RequestMethod.GET)
	@ResponseBody
	public Staff getStaff(HttpSession session){//servlet-api.jar
		
		Staff result = staffServiceImpl.getStaff(session);
		if(logger.isDebugEnabled()){
			logger.debug("getStaff="+result);
		}
		return result;
	}
	
	@RequestMapping(value = "/getStaffTest", method = RequestMethod.GET)
	@ResponseBody
	public Object getStaffTest(@RequestParam boolean isDefault, HttpSession session){//servlet-api.jar
		Staff result = staffServiceImpl.getStaffTest(isDefault, session);
		if(logger.isDebugEnabled()){
			logger.debug("getStaffTest="+result);
		}
		return result;
	}
	
	@RequestMapping(value = "/getSystemInfo", method = RequestMethod.GET)
	@ResponseBody
	public Object getDeadline(){
		SystemInfo result = staffServiceImpl.getSystemInfo();
		if(logger.isDebugEnabled()){
			logger.debug("getSystemInfo="+result);
		}
		return result;
	}
	
}
