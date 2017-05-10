package citi.export.excel;

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
import org.springframework.stereotype.Component;
import citi.service.OrdersService;
@Component
public class ExportExcelImpl implements ExportExcel {
	@Resource
	OrdersService ordersServiceImpl;
	@Override
	public String generatePurchaseExcel(String location) throws Exception {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet(location);
		generateLocationSheet(wb, sheet, location);
		
		List<String> units = ordersServiceImpl.selectUnitsInLocation(location);
		String titleName;
		for (String unit : units) {
		sheet = wb.createSheet(location + "_UNIT-" + unit);
		titleName = "CSTS 文具采购表"+": "+location+"_UNIT-"+unit;
		generateUnitSheets(wb, sheet, location, unit, titleName);
		}
		
		String result = "purchase_" + System.currentTimeMillis() + ".xls";
		FileOutputStream fileOut = new FileOutputStream(result);
		wb.write(fileOut);
		fileOut.close();
		return result;
		}
	private void generateLocationSheet(HSSFWorkbook wb, HSSFSheet sheet, String location) {
		// TODO Auto-generated method stub
		createTitleForSheet(wb, sheet, "CSTS 文具采购表"+": "+location);
		List orders = ordersServiceImpl.selectOrdersInLocation(location);
		int indexRow=2;
		for(; indexRow <= orders.size() + 1; indexRow++){
			Object[] order = (Object[])orders.get(indexRow-2);
			createOneRowForSheet(wb, sheet, indexRow, order);
		}
		HSSFRow r = null;
		HSSFCell c = null;
		// row totoal info
		r = sheet.createRow(indexRow);
		// col3 
		c = r.createCell(TOTALINFO_AT_COLUMN);
		HSSFCellStyle cs = wb.createCellStyle();
		HSSFFont f = wb.createFont();
		f.setFontHeightInPoints((short) 16);
		f.setColor(HSSFColor.BLUE_GREY.index);
		f.setBoldweight(f.BOLDWEIGHT_BOLD);
		cs.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs.setFont(f);
		c.setCellStyle(cs);
		c.setCellValue("总计");

		HSSFCellStyle alignCenter = wb.createCellStyle();
		alignCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
		// col4
		String qtyCellFormula = "SUM(E3:E" + indexRow + ")";
		c = r.createCell(TOTALINFO_AT_COLUMN + 1);
		c.setCellFormula(qtyCellFormula);
		CellValue totalQty = evaluator.evaluate(c);
		c.setCellStyle(alignCenter);
		c.setCellValue(totalQty.getNumberValue());
		// col5
		String totalPriceCellFormula = "SUM(F3:F" + indexRow + ")";
		c = r.createCell(TOTALINFO_AT_COLUMN + 2);
		c.setCellFormula(totalPriceCellFormula);
		CellValue totalPrice = evaluator.evaluate(c);
		c.setCellStyle(alignCenter);
		c.setCellValue(totalPrice.getNumberValue());
		sheet.setColumnWidth(1, 11900);
	}
	@Override
	public String generateDispatchExcel(String location) throws Exception {
		// TODO Auto-generated method stub
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet;
        List<String> units = ordersServiceImpl.selectUnitsInLocation(location);
        String titleName = null;
        int indexRow= 0;
		for (String unit : units) {
		sheet = wb.createSheet(location + "_UNIT-" + unit);
		titleName = "CSTS 文具分发表"+": "+location+"_UNIT-"+unit;
		indexRow = generateUnitSheets(wb, sheet, location, unit, titleName);
		generateTeamOrdersInfo(wb, sheet, location, unit, indexRow);
		}
		String result = "dispatch_" + System.currentTimeMillis() + ".xls";
		FileOutputStream fileOut = new FileOutputStream(result);
		wb.write(fileOut);
		fileOut.close();
		return result;
	}
	private void generateTeamOrdersInfo(HSSFWorkbook wb, HSSFSheet sheet, String location, String unit, int indexRow) {
		// TODO Auto-generated method stub
		HSSFCellStyle bold = wb.createCellStyle();
		HSSFFont f2 = wb.createFont();
		f2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
		bold.setFont(f2);
		HSSFRow r = sheet.createRow(indexRow);
		HSSFCell c = r.createCell(0);
		c.setCellStyle(bold);
		c.setCellValue("============================UNIT-".concat(unit).concat(
		" ==========================="));
		sheet.addMergedRegion(new CellRangeAddress(indexRow, indexRow++, 0, 5));
		List<String> teams = ordersServiceImpl.selectTeamsInUnitAndLocation(location, unit);
		for(String team: teams){
			r = sheet.createRow(indexRow);
			c = r.createCell(0);
			c.setCellStyle(bold);
			c.setCellValue("===================Team: ".concat(team).concat(" =================="));
			sheet.addMergedRegion(new CellRangeAddress(indexRow, indexRow++, 0, 5));
			List<String> staffs = ordersServiceImpl.selectStaffsInTeamAndUnitAndLocation(location, unit, team);
			List orders = null;
			String staffName = null;
			String soeId = null;;
			for(String staff: staffs){
				orders = ordersServiceImpl.selectOrdersOfStaffInTeamAndUnitAndLocation(staff);
				Object[] order = (Object[])orders.get(0);
				staffName = order[0].toString();
				soeId = order[1].toString();
				r = sheet.createRow(indexRow);
				c = r.createCell(0);
				c.setCellStyle(bold);
				c.setCellValue("------------- ".concat(staffName+"("+soeId+")").concat(" -------------"));
				sheet.addMergedRegion(new CellRangeAddress(indexRow, indexRow++, 0, 5));
				for(int i = 0; i < orders.size(); i++){
					order = (Object[])orders.get(i);
					indexRow = createRowsForStaff(wb, sheet, indexRow, order);
				}
			}
			
		}
	}
	private int createRowsForStaff(HSSFWorkbook wb, HSSFSheet sheet, int indexRow, Object[] order) {
		// TODO Auto-generated method stub
		
		String quantity = order[2].toString();
		String standard = order[3].toString();
		String stationeryName = order[4].toString();
		
		HSSFRow r = sheet.createRow(indexRow);
		HSSFCell c = r.createCell(0);
		c.setCellValue(quantity.concat(" ")
		.concat(standard).concat(" ")
		.concat(stationeryName));
		sheet.addMergedRegion(new CellRangeAddress(indexRow, indexRow++, 0, 5));
		return indexRow;
	}
	private int generateUnitSheets(HSSFWorkbook wb, HSSFSheet sheet, String location, String unit, String titleName) {
		// TODO Auto-generated method stub
		createTitleForSheet(wb, sheet, titleName);
		List orders = ordersServiceImpl.selectOrdersInUnitAndLocation(location, unit);
		int indexRow=2;
		for(; indexRow <= orders.size() + 1; indexRow++){
			Object[] order = (Object[])orders.get(indexRow-2);
			createOneRowForSheet(wb, sheet, indexRow, order);
		}
		
		HSSFRow r = null;
		HSSFCell c = null;
		// ////sheet.autoSizeColumn(1);
		// row totoal info.==============================
		r = sheet.createRow(indexRow);
		// col3 --
		c = r.createCell(TOTALINFO_AT_COLUMN);
		HSSFCellStyle cs = wb.createCellStyle();
		HSSFFont f = wb.createFont();
		f.setFontHeightInPoints((short) 16);
		f.setColor(HSSFColor.BLUE_GREY.index);
		f.setBoldweight(f.BOLDWEIGHT_BOLD);
		cs.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs.setFont(f);
		c.setCellStyle(cs);
		c.setCellValue("总计");

		HSSFCellStyle alignCenter = wb.createCellStyle();
		alignCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
		// col4
		String qtyCellFormula = "SUM(E3:E" + indexRow + ")";
		c = r.createCell(TOTALINFO_AT_COLUMN + 1);
		c.setCellFormula(qtyCellFormula);
		CellValue totalQty = evaluator.evaluate(c);
		c.setCellStyle(alignCenter);
		c.setCellValue(totalQty.getNumberValue());
		// col5
		String totalPriceCellFormula = "SUM(F3:F" + indexRow + ")";
		c = r.createCell(TOTALINFO_AT_COLUMN + 2);
		c.setCellFormula(totalPriceCellFormula);
		CellValue totalPrice = evaluator.evaluate(c);
		c.setCellStyle(alignCenter);
		c.setCellValue(totalPrice.getNumberValue());
		sheet.setColumnWidth(1, 11900);
		return indexRow+1;
	}

	private void createOneRowForSheet(HSSFWorkbook wb, HSSFSheet sheet, int indexRow, Object[] order) {
		// TODO Auto-generated method stub
		HSSFCellStyle alignLeft = wb.createCellStyle();
		alignLeft.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		HSSFCellStyle alignCenter = wb.createCellStyle();
		alignCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFRow r = null;
		HSSFCell c = null;
		r = sheet.createRow(indexRow);
		int i = 0;
		for(; i < 3; i++){
			c = r.createCell(i);
			if(i < 2){
				c.setCellStyle(alignLeft);
			} else {
				c.setCellStyle(alignCenter);
			}
			c.setCellValue(order[i].toString());
		}
		BigDecimal p = new BigDecimal(order[i].toString());
		double price = p.doubleValue();
		c = r.createCell(i);
		c.setCellStyle(alignCenter);
		c.setCellValue(price);
		BigDecimal q = new BigDecimal(order[i+1].toString());
		double quantity = q.doubleValue();
		c = r.createCell(i+1);
		c.setCellStyle(alignCenter);
		c.setCellValue(quantity);
		double total = price * quantity;
		c = r.createCell(i+2);
		c.setCellStyle(alignCenter);
		c.setCellValue(total);
	}

	private void createTitleForSheet(HSSFWorkbook wb, HSSFSheet sheet, String titleName) {
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
		c.setCellValue(titleName);
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
