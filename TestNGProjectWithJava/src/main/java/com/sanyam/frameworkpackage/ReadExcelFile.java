package com.sanyam.frameworkpackage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ReadExcelFile {
static HSSFWorkbook workbook;
static HSSFSheet worksheet;
static HSSFRow rowObj;
static HashMap<String, String> testData = null;


	public static HashMap<String,String> testDataCollector(String TestdataIdentifier){
		
		try {
			String excelpath = System.getProperty("user.dir") + "\\TestData\\TestData.xls";
			File src = new File(excelpath);
			FileInputStream fis = new FileInputStream(src);
			workbook = new HSSFWorkbook(fis);
			worksheet = workbook.getSheet("Sheet1");
			rowObj = worksheet.getRow(0);
			int lastRowNumber = worksheet.getLastRowNum();
			int lastColumnNumber = rowObj.getLastCellNum();
			int testCaseRow = 0; 
			testData = new HashMap<String, String>();
			for(int i=1;i<=lastRowNumber;i++){
				String Identifier = worksheet.getRow(i).getCell(0).getStringCellValue();
				if(TestdataIdentifier.equals(Identifier)){
					testCaseRow = i;
					break;
				}
				else{
					System.out.println("Please select currect test Identifier");
				}
			}
			for(int j=0;j<lastColumnNumber;j++){
			String key1 = worksheet.getRow(0).getCell(j).getStringCellValue();
			String value1 = worksheet.getRow(testCaseRow).getCell(j).getStringCellValue();
			testData.put(key1, value1);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return testData;
		
		
	}
}
