package com.programDevelopment;

import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JTable;

public class CrimeQuery 
{	
	private String value1;
	private String value2;
	static Vector<Vector<String>> data = new Vector<Vector<String>>();
	
	Connection connection1;
	
	 public CrimeQuery(String value1,String value2)
	{
		this.setValue1(value1);
		this.setValue2(value2);
		connection1=DatabaseConnection.getConnection();
	}
	public JTable QueryAll() throws SQLException
	{
		//statement object  
		Statement stmt=connection1.createStatement();
	
		//this will select divisions and what two topics the user wants(value1,value2)
		ResultSet rs=stmt.executeQuery("select  Divisions,"+this.getValue1()+","+this.getValue2()+" from CRIMES");  
		
		Vector<String> columnNames = new Vector<String>();
        columnNames.add("Divisions");
        columnNames.add(this.getValue1());
        columnNames.add(this.getValue2());
		
		
		//iterate through to add query data into queryResult 
		while(rs.next())
		{
			System.out.println(rs.getString("Divisions")+"||"+rs.getString(this.getValue1())+"||"+rs.getString(this.getValue2()));
			Vector<String> vString = new Vector<String>();
			
			vString.addElement(rs.getString("Divisions"));
            vString.addElement(rs.getString(this.getValue1()));
            vString.addElement(rs.getString(this.getValue2()));

            data.add(vString);
		}
		JTable querytable=new JTable(data,columnNames);
		return querytable;
	}
	
	
	
	public void sumQuery() throws SQLException
	{
		Statement stmt=connection1.createStatement();
		PreparedStatement statement=  connection1.prepareStatement("select sum("+this.getValue1()+"),sum("+this.getValue2()+") from CRIMES");
	    ResultSet rs= statement.executeQuery();
		
		//ResultSet rs=stmt.executeQuery("select sum("+this.getValue1()+"),sum("+this.getValue2()+") from CRIMES");
	  
		while(rs.next())
		{
			System.out.println(rs.getString(this.getValue1())+"||"+rs.getString(this.getValue2()));
		}
	}
	
	
	 
	 

	public String getValue1() {
		return value1;
	}

	public void setValue1(String value1) {
		this.value1 = value1;
	}

	public String getValue2() 
	{
		return value2;
	}

	public void setValue2(String value2)
	{
		this.value2 = value2;
	}
	
}
