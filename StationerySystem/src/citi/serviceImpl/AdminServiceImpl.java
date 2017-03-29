package citi.serviceImpl;
import java.io.FileOutputStream;
import java.util.List;
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
@Service
public class AdminServiceImpl implements AdminService {

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
		
		
		HSSFCellStyle alignCenter = wb.createCellStyle();
		alignCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		Orders orders = null;
		int sumRows = 35;
		for(int indexRow=1; indexRow < 6; indexRow++){
			createContentForsheet(sheet,alignCenter, indexRow, orders);
		}
		
		
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
		c.setCellValue("总结");

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
		String result = "D:/export2003_" + System.currentTimeMillis() + ".xls";
		FileOutputStream fileOut = new FileOutputStream(result);
		wb.write(fileOut);
		fileOut.close();
		return result;
		}
	private void processFinalUnitSheets(HSSFWorkbook wb, HSSFSheet sheet, String location, String unit) {
		// TODO Auto-generated method stub
		
	}

	private void createContentForsheet(HSSFSheet sheet, HSSFCellStyle alignCenter, int indexRow, Orders orders) {
		// TODO Auto-generated method stub
		HSSFRow r = null;
		HSSFCell c = null;
		r = sheet.createRow(indexRow);
		// Cell0,--------
		c = r.createCell(0);
		c.setCellStyle(alignCenter);
		c.setCellValue("分类");
		// Cell1,--------
		c = r.createCell(1);
		c.setCellStyle(alignCenter);
		c.setCellValue("品名");
		// Cell2,--------
		c = r.createCell(2);
		c.setCellStyle(alignCenter);
		c.setCellValue("规格");
		// Cell3,--------
		c = r.createCell(3);
		c.setCellStyle(alignCenter);
		c.setCellValue("单价");
		// Cell4,--------
		c = r.createCell(4);
		c.setCellStyle(alignCenter);
		c.setCellValue("数量");
		// Cell5,--------
		c = r.createCell(5);
		c.setCellStyle(alignCenter);
		c.setCellValue("总价");
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
		c.setCellValue("CSTS 文具申请明细"+": "+location);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));
	}
}
