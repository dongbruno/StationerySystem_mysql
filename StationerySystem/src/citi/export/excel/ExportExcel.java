package citi.export.excel;

public interface ExportExcel {
	int TOTALINFO_AT_COLUMN = 3;

	String generatePurchaseExcel(String location) throws Exception;
	
	String generateDispatchExcel(String location) throws Exception;
}
