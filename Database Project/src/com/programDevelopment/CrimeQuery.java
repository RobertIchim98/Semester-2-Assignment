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
	JTable table;
	
	//create connection
	private Connection connection1;
	
	//take in the values from the combo boxes
	 public CrimeQuery(String value1,String value2)
	{
		this.setValue1(value1);
		this.setValue2(value2);
		setConnection1(DatabaseConnection.getConnection());
	}	
	 
	//this will query the whole set required by user 
	public JTable QueryAll()
	{	
		try
		{
			//will create the query statement along with PreparedStatement to be sent to CreateQueryTable
			String queryStatement="select  Station,"+this.getValue1()+","+this.getValue2()+" from CRIMES";
			PreparedStatement statement=getConnection1().prepareStatement(queryStatement);
			
			//passing the parameters to get the table returned here
			table=CreateQueryTable(statement,queryStatement);
			
		}
		//catch errors and display then as popup
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(this,e);
		}
		//table will be returned to the GUI, where it is being called
		return table;
	}
	
	
	//this will sum up the the two topics requested by the user
	public JTable sumQuery()
	{	
		try
		{
			String queryStatement="select sum("+this.getValue1()+"),sum("+this.getValue2()+") from CRIMES";
			PreparedStatement statement=getConnection1().prepareStatement(queryStatement);
			table=CreateQueryTable(statement,queryStatement);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(this,e);
		}
		return table;
	}
	
	//This will create top 10 worst or unsafe divisions
	public JTable MaxQuery(String value)
	{
		try
		{
			String queryStatement1="SELECT * FROM \r\n" + 
								   "    (SELECT station,divisions,"+value+" FROM crimes order by "+value+" desc) \r\n" + 
								   "where rownum <= 10";
			PreparedStatement statement=getConnection1().prepareStatement(queryStatement1);
			table=CreateQueryTable(statement,queryStatement1);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(this,e);
		}
		return table;
	}
	
	//This will create top 10 safe divisions
	public JTable MinQuery(String value)
	{
		try
		{
			String queryStatement1="SELECT * FROM \r\n" + 
								   "    (SELECT station,divisions,"+value+" FROM crimes order by "+value+") \r\n" + 
								   "where rownum <= 10";
			PreparedStatement statement=getConnection1().prepareStatement(queryStatement1);
			table=CreateQueryTable(statement,queryStatement1);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(this,e);
		}
		return table;
	}
	
	
	
	//this will query the statements needed and will put it in vectors then in a table 
	private JTable CreateQueryTable(PreparedStatement statement,String queryStatement)
	{
		Vector<Object> columnNames=new Vector<Object>();
		Vector<Object> data=new Vector<Object>();
		JTable queryTable=new JTable();
		
		try
		{
			
			ResultSet rs=statement.executeQuery(queryStatement); 
			
			//From here onwards this code is from StackOverflow (can't find the link) but it is not my code. I do however understand the code as i have also commented it and checked it with system out prints
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
		}//here ends code from StackOverflow
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(this,e);
		}
		//create the table with the user selected query and return it to whichever function needs it
		queryTable=new JTable(data,columnNames);
		return queryTable;
	}
	
	//Getters and Setters
	
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

	public Connection getConnection1() {
		return connection1;
	}

	public void setConnection1(Connection connection1) {
		this.connection1 = connection1;
	}


	
}
