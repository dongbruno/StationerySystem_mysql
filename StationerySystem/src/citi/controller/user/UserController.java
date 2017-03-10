package citi.controller.user;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import citi.controller.stationery.StationeryController;
import citi.entity.Order;
import citi.entity.User;
import citi.service.stationery.StationeryService;
import citi.service.user.UserService;

public class UserController {
private static final Log logger = LogFactory.getLog(UserController.class);
	
	@Resource(name = "userServiceImpl")
	UserService userServiceImpl;
	@RequestMapping(value = "/getUser", method = RequestMethod.GET)
	@ResponseBody
	public Object getUser(HttpSession session){//servlet-api.jar
		User result = userServiceImpl.getUser(session);
		if(logger.isDebugEnabled()){
			logger.debug("getUser="+result);
		}
		return result;
	}

}
