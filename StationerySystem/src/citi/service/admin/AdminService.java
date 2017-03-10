package citi.service.admin;

import java.io.File;

public interface AdminService {

	String setDeadline(String deadline);

	File downloadFile(String dlType, String location);

	String setNote(String note);
	
	

}
