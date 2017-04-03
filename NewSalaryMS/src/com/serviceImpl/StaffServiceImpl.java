package com.serviceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.service.StaffService;

import com.hibernate.dao.StaffDao;
import com.hibernate.entity.Staff;
import com.hibernate.util.HibernateUtil;
@Service
public class StaffServiceImpl implements StaffService {
	private static final Log log = LogFactory.getLog(StaffServiceImpl.class);
@Resource
StaffDao staffDaoImpl;
	@Override
	public Staff getStaff(HttpSession session) {
		// TODO Auto-generated method stub
		Staff staff = (Staff) session.getAttribute("staff");
		//insertStaff(staff);
		return staff;
	}
	@Override
	public Staff getStaffTest(boolean isDefault, HttpSession session) {
		// TODO Auto-generated method stub
		if(isDefault){
			session.setAttribute("soeId", "YD83768");
			session.setAttribute("staff", new Staff("YD83768", "董永辉", true, "LJZ", "VIII", "muni"));
		} else {
			Staff newStaff = generateStaff();
			session.setAttribute("soeId", newStaff.getSoeId());
	    	session.setAttribute("staff", newStaff);
		}
		Staff staff = (Staff) session.getAttribute("staff");
		//insertStaff(staff);
		return staff;
	}

	@Override
	public String getNote() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDeadline() {
		// TODO Auto-generated method stub
		return null;
	}

	private Staff generateStaff() {
		// TODO Auto-generated method stub
		int unique = generateUniqueValue();
		String location = generateLocation();
		String unit = generateUnit();
		Staff staff = new Staff("YB"+unique, "张三"+unique, false, location, unit, "team"+unique);
    	insertStaff(staff);
		return staff;
	}
	@Override
	public boolean insertStaff(Staff staff) {
		// TODO Auto-generated method stub
//		if(staffDaoImpl.findByExample(staff)==null){
		HibernateUtil.openSession();
    	staffDaoImpl.insertStaff(staff);
    	HibernateUtil.closeSession();
//		}
		return true;
	}
	private String generateUnit() {
		// TODO Auto-generated method stub
		String[] units = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
		int index = (int) (Math.random()*units.length);
		
		return units[index];
	}

	private String generateLocation() {
		// TODO Auto-generated method stub
		String[] locations = {"DL", "ZJ", "LJZ"};
		int index = (int) (Math.random()*locations.length);
		
		return locations[index];
	}

	private int generateUniqueValue(){
		FileInputStream fis = null;  
        FileOutputStream fos = null;  
        //创建计数器  
        int count = 0;
        try {  
            //创建Properties对象  
            Properties prop = new Properties();  
            //将文件封装在File对象中  
            File file = new File("D:/wonder/git/AStationerySystem/StationerySystem/src/unique.properties");  
            //判断文件是否存在，如果不存在就创建  
            if(!file.exists()){  
                file.createNewFile();  
            }  
            //将文件对象放入流对象中  
            fis = new FileInputStream(file);  
            //加载流文件  
            prop.load(fis);  
            //从配置文件中获取unique的值(次数)  
            String unique = prop.getProperty("unique");  
            //判断times是否为空，如果有值存入count中  
            if(unique!=null){  
                count = Integer.parseInt(unique);  
            }  
            //每访问一次count变加1  
            count ++;  
            //将新的count写入prop中  
            prop.setProperty("unique", count+"");  
            //创建输出流  
            fos = new FileOutputStream(file);  
            //将配置信息重新写入文件中，并加上注释信息comments(也可不写)  
            prop.store(fos, "unique value to make the string unique!");  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            if(fis!=null)  
                try {  
                    fis.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            if(fos!=null){  
                try {  
                    fos.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }
		
		return count;
		
	}

	@Override
	public boolean isExpired() {
		// TODO Auto-generated method stub
		
		return false;
	}

	/*public long getTime()            返回自 1970 年 1 月 1 日 00:00:00 GMT 以来此 Date 对象表示的毫秒数。
	public void setTime(long time)   设置此 Date 对象，以表示 1970 年 1 月 1 日 00:00:00 GMT 以后 time 毫秒的时间点。

	实例：  求出sourceTime，过minuteNumber分钟后的时间
	 public static String getPreTime(String sourceTime, String minuteNumber) {
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
	    String mydate1 = "";
	    try {
	         Date date1 = format.parse(sourceTime);
	         long Time = (date1.getTime() / 1000) + Integer.parseInt(minuteNumber) * 60;
	         date1.setTime(Time * 1000);
	         mydate1 = format.format(date1);
	    } catch (Exception e) { }
	    return mydate1;
	 }
*/
	

}
