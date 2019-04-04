package com.programDevelopment;

import java.sql.*;

public class DatabaseConnection 
{
	public static Connection con;

	public static Connection getConnection()
	{
		try
		{  
			//load the driver class  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			//creating the connection object  
			 con=DriverManager.getConnection(  
			"jdbc:oracle:thin:@localhost:1521:xe","rob","rob");  
		}
		//error check if something goes wrong
		catch(Exception e)
		{ 
			System.out.println("Could not connect to database:"+ e); 
		}
		//return queryResult;
		return con;
	}
}
