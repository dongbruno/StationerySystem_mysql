package citi.hibernate.daoImpl.test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hibernate.dao.StationeryDao;
import com.hibernate.entity.Stationery;
import com.hibernate.util.HibernateUtil;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class AddStationeryFromFileTestCase {
	@Resource(name="stationeryDaoImpl")
	StationeryDao stationeryDaoImpl;
	
	
	@Test
	public void addStationery(){
		String filePath = "E:\\毕设资料\\stationery.txt";
		try {
            String encoding="utf-8";
            File file=new File(filePath);
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while((lineTxt = bufferedReader.readLine()) != null){
                	String[] arr=lineTxt.split("\\s");
                	System.out.println(new BigDecimal(arr[3]));
                	Stationery sta = new Stationery(arr[0], arr[1], arr[2], new BigDecimal(arr[3]));
                	HibernateUtil.openSession();
                	stationeryDaoImpl.insertStationery(sta);
                	HibernateUtil.closeSession();
                }
                read.close();
    }else{
        System.out.println("找不到指定的文件");
    }
    } catch (Exception e) {
        System.out.println("读取文件内容出错");
        e.printStackTrace();
    }

	}

}
