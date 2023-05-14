package com.demo.ExcelProject;
import java.io.*;

import java.sql.*;
import java.util.*;
import static java.lang.Integer.parseInt;
public class Readinvoices{
	public static void main(String[]args) {
		String jdbcUrl="jdbc:mysql://localhost:3306/info";
		String username="root";
		String password="Svdkf@678";
		String FilePath="C:/Users/Acer/Downloads/Book.xlsx";
		int batchSize=20;
		Connection connection=null;
		try {
			connection=DriverManager.getConnection(jdbcUrl, username, password);
			connection.setAutoCommit(false);
			String sql="insert into student(id,name,address,salary) values(?,?,?,?)";
			PreparedStatement statement=connection.prepareStatement(sql);
			BufferedReader lineReader=new BufferedReader(new FileReader(FilePath));
			String linetext=null;
			int count=0;
			lineReader.readLine();
			//while((linetext=lineReader.readLine())!=null) {
			//	String[] data=linetext.split(",");
//				try {
//				String id=data[0];
//				String name=data[1];
//				String address=data[2];
//				String salary=data[3];
//				}
//				catch(ArrayIndexOutOfBoundsException e) {
//					System.out.println("exception catched");
//					statement.setString(1,id);
//					statement.setString(2, name);
//					statement.setString(3, address);
//					statement.setString(4, salary);
//				}

				//statement.addBatch();
				if(count%batchSize==0) {
					statement.executeBatch();
				}
	
			//}
			lineReader.close();
			statement.executeBatch();
			connection.commit();
			connection.close();
			System.out.println("Data has been inserted successfully");

			
			
			
		}
		catch(Exception exception) {
			exception.printStackTrace();
			
		}
	}
}