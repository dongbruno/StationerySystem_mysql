package citi.hibernate.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;

import javax.annotation.Resource;


import citi.hibernate.dao.StationeryDao;
import citi.hibernate.dao.StationeryDaoImpl;
import citi.hibernate.entity.Stationery;
import citi.hibernate.util.HibernateUtil;

public class AddStationeryFromFile {
//	@Resource(name="stationeryDaoImpl")
//	StationeryDaoImpl stationeryDaoImpl;


	public void readTxtFile(String filePath){
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
	                    	StationeryDaoImpl stationeryDaoImpl = new StationeryDaoImpl();
	                    	HibernateUtil.openSession();
	                    	stationeryDaoImpl.addStationery(sta);
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String filePath = "E:\\毕设资料\\stationery.txt";
		AddStationeryFromFile add = new AddStationeryFromFile();
		add.readTxtFile(filePath);
	}

}
