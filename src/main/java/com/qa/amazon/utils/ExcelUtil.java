package com.qa.amazon.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

	private static final String TEST_DATA_SHEET_PATH = "./src/test/resources/testdata/AmazonAppTestData.xlsx";
	private static Workbook book;
	private static Sheet sheet;
	private static FileInputStream ip = null;
	private static FileOutputStream op = null;
	
	public static Object[][] getTestData(String sheetName) {

		Object data[][] = null;
		try {
			ip = new FileInputStream(TEST_DATA_SHEET_PATH);
			book = WorkbookFactory.create(ip);
			sheet = book.getSheet(sheetName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
				Cell cell=  sheet.getRow(i + 1).getCell(j);
				switch (cell.getCellType()) 
				{
				 case Cell.CELL_TYPE_STRING:
					data[i][j] = sheet.getRow(i + 1).getCell(j).getStringCellValue();
					break;
				 case Cell.CELL_TYPE_NUMERIC:
					 DataFormatter df=new DataFormatter();
					 data[i][j] = df.formatCellValue(cell);
				     //data[i][j] = sheet.getRow(i + 1).getCell(j).getNumericCellValue();
				    break;
				 case Cell.CELL_TYPE_BOOLEAN:
					data[i][j] = sheet.getRow(i + 1).getCell(j).getBooleanCellValue();
		            break;
		         default:
		        	data[i][j] = sheet.getRow(i + 1).getCell(j).getStringCellValue();
				    break;
		            	
				}
			} 
		}

		return data;
	}
	
	public static void setCellValue(String sheetName, String value, int rownum, int cellnum)
	{
				
		try {
			sheet = book.getSheet(sheetName);
			sheet.getRow(rownum).createCell(cellnum).setCellValue(value);
				
		    op = new FileOutputStream(TEST_DATA_SHEET_PATH);
			book.write(op);
			op.close();
			book = new XSSFWorkbook(new FileInputStream(TEST_DATA_SHEET_PATH)); 
			}
		catch (IOException e1) {
				e1.printStackTrace();
			}
		catch (Exception e) {
				e.printStackTrace();
			}
	  }
	
	
	
}
			
			
		
		
		
		
		
		
		
	


