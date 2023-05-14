package com.demo.ExcelProject;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Readinvoices {

	public static void main(String[] args) throws SQLException, IOException {
	
		//Database connection
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","Svdkf@678");
		Statement stmt=con.createStatement();
		
		//create a new table in the database 'places'
		String sql="create table info7 (Student_ID int, Student_Name varchar(40),Father_name varchar(40),Mother_name varchar(30),Adderss varchar(25),Country varchar(20),State varchar(40),Pincode int,Mobile_no int,Blood_grp varchar(40))";
		stmt.execute(sql);
		
		//Excel
		FileInputStream fis=new FileInputStream("C:/Users/Acer/Downloads/Studentinfo.xlsx");
		XSSFWorkbook workbook=new XSSFWorkbook(fis);
		XSSFSheet sheet=workbook.getSheet("Sheet1");
		
		int rows=sheet.getLastRowNum();
		
		for(int r=1;r<=rows;r++)
		{
			XSSFRow row=sheet.getRow(r);
			double student_ID=row.getCell(0).getNumericCellValue();
			String student_name=row.getCell(1).getStringCellValue();
			String fathername=row.getCell(2).getStringCellValue();
			String mothername=row.getCell(3).getStringCellValue();
			String address=row.getCell(4).getStringCellValue();
			String country=row.getCell(5).getStringCellValue();
			String state=row.getCell(6).getStringCellValue();
			int pincode=(int) row.getCell(7).getNumericCellValue();
			int mobile=(int)row.getCell(8).getNumericCellValue();
			String bloodgrp=row.getCell(9).getStringCellValue();
			
			sql="insert into info7 values('"+student_ID+"', '"+student_name+"', '"+fathername+"', '"+mothername+"', '"+address+"', '"+country+"', '"+state+"', '"+pincode+"', '"+mobile+"','"+bloodgrp+"')";
			stmt.execute(sql);
			stmt.execute("commit");
		}
		
		
		workbook.close();
		fis.close();
		con.close();
		
		System.out.println("Done!!");
		
	}

}
