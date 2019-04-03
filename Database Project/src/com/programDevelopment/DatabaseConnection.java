package com.programDevelopment;

import java.sql.*;
import java.util.*;

public class DatabaseConnection 
{
	
	String value1;
	String value2;
	final ArrayList<String> queryResult= new ArrayList();
	
	public ArrayList<String> DatabaseConnection(String value1,String value2)
	{
		this.value1=value1;
		this.value2=value2;
		String data1;
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
				System.out.println(rs.getString("Divisions")+"||"
								   +rs.getString(this.value1)+"||"
						           +rs.getString(this.value2));
				queryResult.add(rs.getString(rs.getString("Divisions")+"||"+rs.getString(this.value1)+"||"+rs.getString(this.value2)));
				
			}
			
		
			
			//con.close();  
		}
		catch(Exception e)
		{ 
			System.out.println("Could not connect to database:"+ e); 
		}
		return queryResult;
	}
}
