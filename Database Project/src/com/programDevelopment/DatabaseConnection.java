package com.programDevelopment;

import java.sql.*;

public class DatabaseConnection 
{
	public static Connection con;

	//this class is only for the local database connection in my laptop
	public static Connection getConnection()
	{
		//this is an amalgamation of code found on StackOverflow and my own attempts at making it work which has been remodelled to return connection specifically for the way my program works
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
