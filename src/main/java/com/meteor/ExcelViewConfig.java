package com.meteor;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.meteor.dutch.DutchDao;

public class ExcelViewConfig extends AbstractXlsView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<DutchDao> dutchList = (List<DutchDao>) model.get("dutchList");
		Sheet sheet = workbook.createSheet();
		Row header = sheet.createRow(0);
		header.createCell(0).setCellValue("0");
		header.createCell(1).setCellValue("1");
		header.createCell(2).setCellValue("2");
		header.createCell(3).setCellValue("3");
		
		for(DutchDao dutchDao : dutchList) {
			Row row = sheet.createRow(sheet.getLastRowNum()+1);
			header.createCell(0).setCellValue( dutchDao.getId() );
			header.createCell(1).setCellValue(dutchDao.getCoffeeKind().name());
			header.createCell(2).setCellValue(dutchDao.getProduceTime());
			header.createCell(3).setCellValue(dutchDao.getExpiredTime());
			
		}
		
		
		
	}
	

}
