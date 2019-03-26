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
			"jdbc:oracle:thin:@localhost:1521:xe","system","oracle");  
			  
			//step3 create the statement object  
			Statement stmt=con.createStatement();  
			  
			con.close();  
			  
			}
			catch(Exception e)
			{ 
				System.out.println(e); 
			}
		}
}


