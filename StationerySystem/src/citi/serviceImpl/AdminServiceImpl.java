package citi.serviceImpl;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import citi.export.excel.ExportExcel;
import citi.hibernate.dao.AdminDao;
import citi.hibernate.util.HibernateUtil;
import citi.service.AdminService;
@Service
public class AdminServiceImpl implements AdminService {
	@Resource
	ExportExcel ExportExcelImpl;
	@Resource
	AdminDao adminDaoImpl;
	
	@Override
	public String setNote(String note, HttpSession session) {
		// TODO Auto-generated method stub
		 HibernateUtil.openSession();
		 String result = adminDaoImpl.setDeadline(note, (String) session.getAttribute("soeId"));
         HibernateUtil.closeSession();
		 return result;
	}
	
	@Override
	public String setDeadline(String deadline, HttpSession session) {
		// TODO Auto-generated method stub
		
		 HibernateUtil.openSession();
		 String result = adminDaoImpl.setDeadline(deadline, (String) session.getAttribute("soeId"));
         HibernateUtil.closeSession();
		 return result;
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

	
	
}
