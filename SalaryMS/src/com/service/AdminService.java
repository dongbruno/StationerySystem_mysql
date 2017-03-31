package com.service;

import java.io.File;

public interface AdminService {

	String setDeadline(String deadline);

	String downloadFile(String dlType, String location);

	String setNote(String note);
	
	

}
