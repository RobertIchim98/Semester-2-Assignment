package com.programDevelopment;

import java.sql.*;

public class Control 
{

	public static void main(String[] args) 
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
			
			//select the first three columns
			while(rs.next()) 
			{
				System.out.println(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3) );
			}
			
			con.close();  
			  
			
			}
			catch(Exception e)
			{ 
				System.out.println(e); 
			}
		}
}


