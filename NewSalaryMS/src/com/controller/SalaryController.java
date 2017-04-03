package com.controller;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.service.SalaryService;

import com.hibernate.entity.Salary;
@Controller
public class SalaryController {
	private static final Log logger = LogFactory.getLog(SalaryController.class);
	
	@Resource
	SalaryService SalaryServiceImpl;
	@RequestMapping(value = "/getSalary", method = RequestMethod.GET)
	@ResponseBody
	public List<Salary> getSalary(HttpSession session){
		List<Salary> result = SalaryServiceImpl.getSalary(session);
		
		if(logger.isDebugEnabled()){
			logger.debug("getSalary="+result);
		}
		return result;
	}
	
	@RequestMapping(value = "/saveSalary", method = RequestMethod.POST)
	@ResponseBody
	public String saveSalary(@RequestBody String orderJson, HttpSession session){
		JsonArray orderJsonArray=new JsonParser().parse(orderJson).getAsJsonArray();
		boolean result = SalaryServiceImpl.saveSalary(orderJsonArray, session);
		if(logger.isDebugEnabled()){
			logger.debug("saveSalary="+result);
		}
		return "success";
	}
	@RequestMapping(value = "/submitSalary", method = RequestMethod.POST)
	@ResponseBody
	public String submitSalary(@RequestBody String orderJson, HttpSession session){
		JsonArray orderJsonArray=new JsonParser().parse(orderJson).getAsJsonArray();
		boolean result = SalaryServiceImpl.submitSalary(orderJsonArray, session);
		if(logger.isDebugEnabled()){
			logger.debug("submitSalary="+result);
		}
		return "success";
	}
	

}
