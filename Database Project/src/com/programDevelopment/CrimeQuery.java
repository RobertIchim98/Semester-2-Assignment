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
	
	
	Connection connection1;
	
	 public CrimeQuery(String value1,String value2)
	{
		this.setValue1(value1);
		this.setValue2(value2);
		connection1=DatabaseConnection.getConnection();
	}	
	 
	//this will query the whole set required by user 
	public JTable QueryAll()
	{	
		try
		{
			String queryStatement="select  Divisions,"+this.getValue1()+","+this.getValue2()+" from CRIMES";
			PreparedStatement statement=connection1.prepareStatement(queryStatement);
			table=CreateQueryTable(statement,queryStatement);
			
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(this,e);
		}
		return table;
	}
	
	
	//this will sum up the the two topics requested by the user
	public JTable sumQuery()
	{	
		try
		{
			String queryStatement="select sum("+this.getValue1()+"),sum("+this.getValue2()+") from CRIMES";
			PreparedStatement statement=connection1.prepareStatement(queryStatement);
			table=CreateQueryTable(statement,queryStatement);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(this,e);
		}
		return table;
	}
	public JTable MaxQuery(String value)
	{
		try
		{
			String queryStatement1="SELECT * FROM \r\n" + 
								   "    (SELECT station,divisions,"+value+" FROM crimes order by "+value+" desc) \r\n" + 
								   "where rownum <= 10";
			PreparedStatement statement=connection1.prepareStatement(queryStatement1);
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
			
			//From here onwards this code is from StackOverflow
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
		queryTable=new JTable(data,columnNames);
		return queryTable;
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
