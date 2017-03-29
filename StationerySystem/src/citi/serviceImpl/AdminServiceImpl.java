package citi.serviceImpl;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Service;

import citi.hibernate.entity.Orders;
import citi.service.AdminService;
import citi.service.OrdersService;
@Service
public class AdminServiceImpl implements AdminService {
	@Resource
	OrdersService ordersServiceImpl;
	private static final int TOTALINFO_AT_COLUMN = 0;

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
				result = generatePurchaseExcel(location);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(dlType.equalsIgnoreCase("dispatch_detail_ByUnit")){
			return result;
		}
		
		return result;
	}

	
	@Override
	public String setNote(String note) {
		// TODO Auto-generated method stub
		return null;
	}


	private String generatePurchaseExcel(String location) throws Exception {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet(location);
		createTitleForSheet(wb, sheet, location);
		List orders = ordersServiceImpl.selectOrdersInLocation(location);
		for(int indexRow=2; indexRow <= orders.size() + 1; indexRow++){
			Object[] order = (Object[])orders.get(indexRow-2);
			createOneRowForsheet(wb, sheet, indexRow, order);
		}
		sheet.setColumnWidth(1,5000);
		int sumRows = 35;
		/*
		HSSFRow r = null;
		HSSFCell c = null;
		// ////sheet.autoSizeColumn(1);
		// row totoal info.==============================
		r = sheet.createRow(sumRows+1);
		// col3 --
		c = r.createCell(TOTALINFO_AT_COLUMN+1);
		HSSFCellStyle cs = wb.createCellStyle();
		HSSFFont f = wb.createFont();
		f.setFontHeightInPoints((short) 16);
		f.setColor(HSSFColor.BLUE_GREY.index);
		f.setBoldweight(f.BOLDWEIGHT_BOLD);
		cs.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs.setFont(f);
		c.setCellStyle(cs);
		c.setCellValue("总计");

		FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
		// col4
		String qtyCellFormula = "SUM(E3:E" + sumRows + ")";
		c = r.createCell(TOTALINFO_AT_COLUMN + 1);
		c.setCellFormula(qtyCellFormula);
		CellValue totalQty = evaluator.evaluate(c);
		c.setCellValue(totalQty.getNumberValue());
		// col5
		String totalPriceCellFormula = "SUM(F3:F" + sumRows + ")";
		c = r.createCell(TOTALINFO_AT_COLUMN + 2);
		c.setCellFormula(totalPriceCellFormula);
		CellValue totalPrice = evaluator.evaluate(c);
		c.setCellValue(totalPrice.getNumberValue());

		sheet.setColumnWidth(1, 11900);
		// sheet.createFreezePane(6, 0, 6, 0);
		List<String> units = null;
		for (String unit : units) {
		sheet = wb.createSheet(location + "_" + unit);
		processFinalUnitSheets(wb, sheet, location, unit);
		}
		*/
		String result = "D:/export2003_" + System.currentTimeMillis() + ".xls";
		FileOutputStream fileOut = new FileOutputStream(result);
		wb.write(fileOut);
		fileOut.close();
		return result;
		}
	private void processFinalUnitSheets(HSSFWorkbook wb, HSSFSheet sheet, String location, String unit) {
		// TODO Auto-generated method stub
		
	}

	private void createOneRowForsheet(HSSFWorkbook wb, HSSFSheet sheet, int indexRow, Object[] order) {
		// TODO Auto-generated method stub
		HSSFCellStyle alignLeft = wb.createCellStyle();
		alignLeft.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		HSSFCellStyle alignCenter = wb.createCellStyle();
		alignCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		
		HSSFRow r = null;
		HSSFCell c = null;
		r = sheet.createRow(indexRow);
		int i = 0;
		for(; i < order.length; i++){
			c = r.createCell(i);
			if(i < 2){
				c.setCellStyle(alignLeft);
			} else {
				c.setCellStyle(alignCenter);
			}
			c.setCellValue(order[i].toString());
		}
		BigDecimal p = new BigDecimal(order[i-2].toString());
		double price = p.doubleValue();
		BigDecimal q = new BigDecimal(order[i-1].toString());
		double quantity = q.doubleValue();
		double total = price * quantity;
		c = r.createCell(i);
		c.setCellStyle(alignCenter);
		c.setCellValue(total);
	}

	private void createTitleForSheet(HSSFWorkbook wb, HSSFSheet sheet, String location) {
		// TODO Auto-generated method stub
		HSSFRow r = null;
		HSSFCell c = null;
		// Generate row0, ==========================================
		HSSFCellStyle cs = wb.createCellStyle();
		HSSFFont f = wb.createFont();
		f.setFontHeightInPoints((short) 16);
		f.setColor(HSSFColor.BLUE_GREY.index);
		f.setBoldweight(f.BOLDWEIGHT_BOLD);
		cs.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs.setFont(f);
		r = sheet.createRow(0);
		c = r.createCell(0);
		c.setCellStyle(cs);
		c.setCellValue("CSTS 文具采购表明细"+": "+location);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));
		
		HSSFCellStyle cs2 = wb.createCellStyle();
		cs2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFFont f2 = wb.createFont();
		f2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
		cs2.setFont(f2);
		
		r = sheet.createRow(1);
		// Cell0,--------
		c = r.createCell(0);
		c.setCellStyle(cs2);
		c.setCellValue("分类");
		// Cell1,--------
		c = r.createCell(1);
		c.setCellStyle(cs2);
		c.setCellValue("文具名");
		// Cell2,--------
		c = r.createCell(2);
		c.setCellStyle(cs2);
		c.setCellValue("规格");
		// Cell3,--------
		c = r.createCell(3);
		c.setCellStyle(cs2);
		c.setCellValue("单价");
		// Cell4,--------
		c = r.createCell(4);
		c.setCellStyle(cs2);
		c.setCellValue("数量");
		// Cell5,--------
		c = r.createCell(5);
		c.setCellStyle(cs2);
		c.setCellValue("金额");
	}
}
