package com.serviceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.export.excel.ExportExcel;
import com.service.AdminService;
@Service
public class AdminServiceImpl implements AdminService {
	@Resource
	ExportExcel ExportExcelImpl;

	@Override
	public String setDeadline(String deadline) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String downloadFile(String dlType, String location) {
		// TODO Auto-generated method stub
		String result = null;
		if(dlType.equalsIgnoreCase("final_sheet")){
			try {
				result = ExportExcelImpl.generatePurchaseExcel(location);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(dlType.equalsIgnoreCase("dispatch_detail_ByUnit")){
			try {
				result = ExportExcelImpl.generateDispatchExcel(location);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	
	@Override
	public String setNote(String note) {
		// TODO Auto-generated method stub
		return null;
	}
}
