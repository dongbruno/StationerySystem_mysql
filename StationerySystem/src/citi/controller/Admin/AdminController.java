package citi.controller.Admin;

import java.io.File;

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

import citi.controller.stationery.StationeryController;
import citi.service.admin.AdminService;

@Controller
public class AdminController {
	private static final Log logger = LogFactory.getLog( AdminController.class);
	@Resource(name="adminServiceImpl")
	AdminService adminService;
	@RequestMapping(value = "/setDeadline", method = RequestMethod.GET)
	@ResponseBody
	public String setDeadline(@RequestParam String deadline){
		
		return adminService.setDeadline(deadline);
	}
	
	@RequestMapping(value = "/download/{dlType}/{location}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<byte[]> download(@PathVariable String dlType, String location){
		File file = adminService.downloadFile(dlType, location);
		String fileName = file.getName();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDispositionFormData("attachment", fileName);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		
		//commons-io-2.0.jar
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);
		
	}
	@RequestMapping(value = "/setNote", method = RequestMethod.GET)
	@ResponseBody
	public String setNote(@RequestParam String note){
		return adminService.setNote(note);
	}

}
