package citi.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import citi.hibernate.entity.Orders;
import citi.service.AdminService;
import citi.service.OrdersService;

@Controller
public class AdminController {
	private static final Log logger = LogFactory.getLog( AdminController.class);
	@Resource
	AdminService adminServiceImpl;
	@Resource
	OrdersService ordersServiceImpl;
	@RequestMapping(value = "/setDeadline", method = RequestMethod.GET)
	@ResponseBody
	public String setDeadline(@RequestParam String deadline, HttpSession session){
		
		return adminServiceImpl.setDeadline(deadline, session);
	}
	
	@RequestMapping(value = "/download/{dlType}/{location}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<byte[]> download(@PathVariable String dlType, @PathVariable String location) throws IOException{
		String result = adminServiceImpl.downloadFile(dlType, location);
		File file =new File(result);
		String fileName = file.getName();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDispositionFormData("attachment", fileName);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		//commons-io-2.0.jar
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);
		
	}
	@RequestMapping(value = "/setNote", method = RequestMethod.GET)
	@ResponseBody
	public String setNote(@RequestParam String note, HttpSession session){
		return adminServiceImpl.setNote(note, session);
	}
	@RequestMapping(value = "/searchOrdersBySoeid", method = RequestMethod.GET)
	@ResponseBody
	public List<Orders> searchOrdersBySoeid(@RequestParam String soeid){
		List<Orders> result = ordersServiceImpl.searchOrdersBySoeid(soeid);
		return result;
	}

}
