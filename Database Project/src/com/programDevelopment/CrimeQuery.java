package com.programDevelopment;

import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class CrimeQuery extends JFrame
{	
	private String value1;
	private String value2;
	private String[] columnsDivisions={"Divisions",value1,value2};
	JTable table;
	
	
	Connection connection1;
	
	 public CrimeQuery(String value1,String value2)
	{
		this.setValue1(value1);
		this.setValue2(value2);
		connection1=DatabaseConnection.getConnection();
	}	
	 
	public JTable QueryAll()
	{	
		Vector<Object> columnNames=new Vector<Object>();
		Vector<Object> data=new Vector<Object>();
		
		try
		{
			//statement object  
			Statement stmt=connection1.createStatement();
			
			//this will select divisions and what two topics the user wants(value1,value2)
			ResultSet rs=stmt.executeQuery("select  Divisions,"+this.getValue1()+","+this.getValue2()+" from CRIMES");  
			
			ResultSetMetaData md=rs.getMetaData();
			
			int columns=md.getColumnCount();
			
			//get column names
			for(int i=1;i<=columns;i++)
			{
				columnNames.addElement(md.getColumnName(i));
				//System.out.println("Column added");
			}
			
			//iterate through to add query data into queryResult 
			while(rs.next())
			{
				Vector<Object> row=new Vector<Object>(columns);
				for (int i=1; i<=columns; i++)
				{
					row.addElement(rs.getObject(i));
					
				}
				data.addElement(row);
				//System.out.println("row added");
			}
			rs.close();
			stmt.close();
			connection1.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		table=new JTable(data,columnNames);
		return table;
	}
	
	
	
	public void sumQuery() throws SQLException
	{
		try
		{
			Statement stmt=connection1.createStatement();
			PreparedStatement statement=  connection1.prepareStatement("select sum("+this.getValue1()+"),sum("+this.getValue2()+") from CRIMES");
		    ResultSet rs= statement.executeQuery();
			
			while(rs.next())
			{
				//System.out.println(rs.getString(this.getValue1())+"||"+rs.getString(this.getValue2()));
			}
		}
		catch(Exception e)
		{
			
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
