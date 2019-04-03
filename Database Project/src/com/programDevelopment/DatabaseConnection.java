package com.programDevelopment;

import java.sql.*;
import java.util.*;

public class DatabaseConnection 
{
	
	private String value1;
	private String value2;
	ArrayList<String> queryResult= new ArrayList();
	
	public DatabaseConnection(String value1,String value2)
	{
		this.setValue1(value1);
		this.setValue2(value2);
	}
	public ArrayList<String> QueryAll()
	{
		try
		{  
			//step1 load the driver class  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
		  
			//step2 create  the connection object  
			 Connection con=DriverManager.getConnection(  
			"jdbc:oracle:thin:@localhost:1521:xe","rob","rob");  
		  
			//step3 create the statement object  
			Statement stmt=con.createStatement(); 
		
			ResultSet rs=stmt.executeQuery("select * from CRIMES");  
			
			while(rs.next())
			{
				System.out.println(rs.getString("Divisions")+"||"+rs.getString(this.getValue1())+"||"+rs.getString(this.getValue2()));
				queryResult.add(rs.getString("Divisions")+"||"+rs.getString(this.getValue1())+"||"+rs.getString(this.getValue2()));
				
			}	
			//con.close();  
		}
		catch(Exception e)
		{ 
			System.out.println("Could not connect to database:"+ e); 
		}
		return queryResult;
	}
	public String getValue1() {
		return value1;
	}
	public void setValue1(String value1) {
		this.value1 = value1;
	}
	public String getValue2() {
		return value2;
	}
	public void setValue2(String value2) {
		this.value2 = value2;
	}
	
	
}
