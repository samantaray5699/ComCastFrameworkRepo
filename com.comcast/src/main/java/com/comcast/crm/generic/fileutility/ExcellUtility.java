package com.comcast.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcellUtility {

	public String getDataFromExcel(String sheet,int rownum,int celnum) throws Throwable, IOException
	{
		FileInputStream fis = new FileInputStream("./confgTestData/Testdata.xlsx");//path also we can pass with parameter
		Workbook wb = WorkbookFactory.create(fis);
		String data = wb.getSheet(sheet).getRow(rownum).getCell(celnum).getStringCellValue();	
		wb.close();
		return data;
	}
	public String getDataFromSystemExcel(String sheet,int rownum,int celnum) throws Throwable, IOException
	{
		FileInputStream fis = new FileInputStream("C:\\Users\\adity\\OneDrive\\Desktop\\datadriven\\Testdata.xlsx");//path also we can pass with parameter
		Workbook wb = WorkbookFactory.create(fis);
		String data = wb.getSheet(sheet).getRow(rownum).getCell(celnum).getStringCellValue();	
		wb.close();
		return data;
	}
	public int getRowCount(String sheet) throws Throwable, IOException
	{
		FileInputStream fis = new FileInputStream("./confgTestData/Testdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		int rowcount = wb.getSheet(sheet).getLastRowNum();
		wb.close();
		return rowcount;
	}
	public int getRowCountOfSyStemExcel(String sheet) throws Throwable, IOException
	{
		FileInputStream fis = new FileInputStream("C:\\Users\\adity\\OneDrive\\Desktop\\datadriven\\Testdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		int rowcount = wb.getSheet(sheet).getLastRowNum();
		wb.close();
		return rowcount;
	}
	
	public void setDataIntoExcel(String sheet,int rownum,int celnum,String data) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis = new FileInputStream("./confgTestData/Testdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		wb.getSheet(sheet).getRow(rownum).createCell(celnum).setCellValue(data);
		FileOutputStream fos= new FileOutputStream("./confgTestData/Testdata.xlsx");
		wb.write(fos);
		wb.close();
	}
}
