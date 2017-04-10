package citi.hibernate.dao;

public interface AdminDao {
	String setDeadline(String deadline, String soeId);

	String setNote(String note, String soeId);
	
	
}
