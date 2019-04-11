package com.programDevelopment;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.GridLayout;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.*;


public class DatabaseGUI extends JFrame implements ActionListener, MouseListener
{
	
	
	
	//column names which match the column names from the database
	private String[] columnNames= new String[] {
            "DANGEROUS_ACTS_2004", "DANGEROUS_ACTS_2016",
            "ATTEMPTMURDER_RELATED2004","ATTEMPTMURDER_RELATED2016",
            "KIDNAPPING_RELATED2004","KIDNAPPING_RELATED2016",
            "BURGLARY_RELATED2004","BURGLARY_RELATED2016",
            "WEAPONS_EXPLOSIVES2004","WEAPONS_EXPLOSIVES2016"};
	private JComboBox<String> topics1= new JComboBox<>(columnNames);
	private JComboBox<String> topics2= new JComboBox<>(columnNames);
	private JPanel Panel1;
	
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	private JButton button5;

	private String value1;
	private String value2;
	private CrimeQuery query1;
	
	public DatabaseGUI()
	{	
		super("Irish Crime Data info");
		
		button1=new JButton("Query The selected");
		button2=new JButton("Sum up rows");
		button3=new JButton("Select new data");
		button4=new JButton("<- go back");
		button5=new JButton("Max rows");
		
		
		setLayout(new BorderLayout());
	
		Panel1=new JPanel();

		selectMenu();
		
		Panel1.setSize(1080,700);
		setSize(1080,600);
		setVisible(true);
		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		button4.addActionListener(this);
		button5.addActionListener(this);
		
		//removes the program from memory
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent anything) 
	{
		
		if (anything.getSource() == button1)
		{	
			queryAllMenu();
		}
		
		if(anything.getSource() == button2)
		{
			querySumMenu();
		}
		if(anything.getSource() == button3)
		{
			selectMenu();
		}
		if(anything.getSource() == button4)
		{
			queryAllMenu();
		}
		if(anything.getSource() == button5)
		{
			queryMaxMenu();
		}
		
		
		
	}
	
	
	private void selectMenu()
	{
		clearPanel(Panel1);
		
		JLabel label1=new JLabel("Select two rows to inspect data:");
		
		Panel1.setLayout(null);
		
		add(Panel1,BorderLayout.CENTER);
		
				
		Panel1.add(button1);
		Panel1.add(topics1);
		Panel1.add(topics2);
		Panel1.add(label1);
		
		label1.setBounds(100,10,400,40);
		button1.setBounds(100,350,230,60);
		topics1.setBounds(100,80,230,60);
		topics2.setBounds(100,215,230,60);

		label1.setFont(new Font("Serif", Font.CENTER_BASELINE, 24));	
	}
	
	
	private void queryAllMenu()
	{
		clearPanel(Panel1);
		
		setValue1(topics1.getSelectedItem().toString());	
		setValue2(topics2.getSelectedItem().toString());
		
		//load user values into query1
		setQuery1(new CrimeQuery(getValue1(),getValue2()));
		
		//get the table
		JTable table=getQuery1().QueryAll();
		
		//add table with new options
		JScrollPane scrolltable = new JScrollPane(table);
		scrolltable.setBounds(500,10,500,500);
	
		Panel1.add(scrolltable);
		//queryAllTable(table);
		
		Panel1.add(button2);
		Panel1.add(button5);
		Panel1.add(button3);
		button3.setBounds(260,10,180,60);
		button2.setBounds(100,350,230,60);
		button5.setBounds(100,215,230,60);
	}
	
	private void querySumMenu()
	{
		clearPanel(Panel1);
		
		JLabel label1=new JLabel("This is the total numbers\nFor:"+getValue1()+"and"+getValue2());
		JTable table=getQuery1().sumQuery(); 
		
		JScrollPane scrolltable = new JScrollPane(table);
		label1.setBounds(100,10,400,40);
		scrolltable.setBounds(500,10,500,500);
		Panel1.add(scrolltable);
		Panel1.add(button3);
		Panel1.add(button4);
		button3.setBounds(260,10,180,60);
		button4.setBounds(80,10,180,60);
	}
	
	private void queryMaxMenu()
	{
		clearPanel(Panel1);
		
		JLabel label1=new JLabel("Top 10 worst from "+this.getValue1()+":");
		JLabel label2=new JLabel("Top 10 worst from "+this.getValue2()+":");
		
		JTable table1=getQuery1().MaxQuery(value1); 
		JTable table2=getQuery1().MaxQuery(value2); 
		
		JScrollPane scrolltable1 = new JScrollPane(table1);
		JScrollPane scrolltable2= new JScrollPane(table2);
		
		scrolltable1.setBounds(500,60,500,190);
		scrolltable2.setBounds(500,310,500,190);
		button3.setBounds(260,10,180,60);
		button4.setBounds(80,10,180,60);
		label1.setBounds(100,10,500,190);
		label2.setBounds(100,280,500,190);
		
		label1.setFont(new Font("Serif", Font.CENTER_BASELINE, 15));
		label2.setFont(new Font("Serif", Font.CENTER_BASELINE, 15));
		
		Panel1.add(scrolltable1);
		Panel1.add(scrolltable2);
		Panel1.add(button3);
		Panel1.add(button4);
		Panel1.add(label1);
		Panel1.add(label2);
		
		
	}
	
	
	
	//this will clear the panel so there wont be need for more
	private void clearPanel(JPanel Panel1)
	{
		Panel1.removeAll();
		Panel1.invalidate();
		Panel1.validate();
		Panel1.repaint();
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
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


	public CrimeQuery getQuery1() {
		return query1;
	}


	public void setQuery1(CrimeQuery query1) {
		this.query1 = query1;
	}


	
}
