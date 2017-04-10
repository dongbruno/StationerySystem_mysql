package citi.service;

import javax.servlet.http.HttpSession;
public interface AdminService {

	String setDeadline(String deadline, HttpSession session);

	String downloadFile(String dlType, String location);

	String setNote(String note, HttpSession session);
	
}
