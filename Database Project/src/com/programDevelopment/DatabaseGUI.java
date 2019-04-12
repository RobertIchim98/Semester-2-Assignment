package com.programDevelopment;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

import java.awt.*;
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
	
	//combo boxes so user can select topics
	private JComboBox<String> topics1= new JComboBox<>(getColumnNames());
	private JComboBox<String> topics2= new JComboBox<>(getColumnNames());
	
	private JPanel Panel1;
	
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	private JButton button5;
	private JButton button6;

	//values to store users choice from combo box
	private String value1;
	private String value2;
	
	//CrimeQuery variable to connect to database and do querries
	private CrimeQuery query1;
	
	
	//main GUI which will display the main menu first and initialise buttons,styles and listeners
	public DatabaseGUI()
	{	
		super("Irish Crime Data info");
		
		//buttons that will be used
		setButton1(new JButton("Look at the data"));
		setButton2(new JButton("Total Crimes"));
		setButton3(new JButton("Select new data"));
		setButton4(new JButton("<- go back"));
		setButton5(new JButton("Top 10 Unsafe"));
		setButton6(new JButton("Top 10 Safe"));
		
		//setting font for all buttons
		getTopics1().setFont(new Font("Serif", Font.CENTER_BASELINE, 12));
		getTopics2().setFont(new Font("Serif", Font.CENTER_BASELINE, 12));
		getButton1().setFont(new Font("Serif", Font.CENTER_BASELINE, 19));
		getButton2().setFont(new Font("Serif", Font.CENTER_BASELINE, 19));
		getButton3().setFont(new Font("Serif", Font.CENTER_BASELINE, 19));
		getButton4().setFont(new Font("Serif", Font.CENTER_BASELINE, 19));
		getButton5().setFont(new Font("Serif", Font.CENTER_BASELINE, 19));
		getButton6().setFont(new Font("Serif", Font.CENTER_BASELINE, 19));
		
		//setting up style
		
		getTopics1().setBackground(Color.white);
		getTopics2().setBackground(Color.white);
		getPanel1().setBackground(Color.white);
		getButton3().setBackground(Color.LIGHT_GRAY);
		getButton4().setBackground(Color.LIGHT_GRAY);
		
		
		//setting up layout and adding a panel
		setLayout(new BorderLayout());
	
		setPanel1(new JPanel());
		
		//start the main menu
		selectMenu();
		
		//set the panel size and window size
		getPanel1().setSize(1080,700);
		setSize(1080,600);
		setVisible(true);
		
		
		getButton1().addActionListener(this);
		getButton2().addActionListener(this);
		getButton3().addActionListener(this);
		getButton4().addActionListener(this);
		getButton5().addActionListener(this);
		getButton6().addActionListener(this);
		
		//removes the program from memory (sometimes takes up too much memory, ran out at one point)
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent anything) 
	{
		//whenever the buttons are pressed the function will be called to display the menu
		
		if (anything.getSource() == getButton1())
		{	
			queryAllMenu();
		}
		
		if(anything.getSource() == getButton2())
		{
			querySumMenu();
		}
		if(anything.getSource() == getButton3())
		{
			selectMenu();
		}
		if(anything.getSource() == getButton4())
		{
			queryAllMenu();
		}
		if(anything.getSource() == getButton5())
		{
			queryMaxMenu();
		}
		if(anything.getSource()==getButton6())
		{
			queryMinMenu();
		}
		
		
		
	}
	
	
	//this is the main menu where you chose two columns to inspect
	private void selectMenu()
	{
		//clear the panel first
		clearPanel(getPanel1());
		
		JLabel label1=new JLabel("Select two rows to inspect data:");
		
		//set up panel
		getPanel1().setLayout(null);
		add(getPanel1(),BorderLayout.CENTER);
		
		//add buttons to panel
		getPanel1().add(getButton1());
		getPanel1().add(getTopics1());
		getPanel1().add(getTopics2());
		getPanel1().add(label1);
		
		
		//manually set their position
		label1.setBounds(380,10,400,40);
		getButton1().setBounds(430,350,230,60);
		getTopics1().setBounds(430,80,230,60);
		getTopics2().setBounds(430,215,230,60);

		label1.setFont(new Font("Serif", Font.CENTER_BASELINE, 24));	
	}
	
	//menu where user will select what to see from data
	private void queryAllMenu()
	{
		clearPanel(getPanel1());
		
		//get value from combo boxes into Values
		setValue1(getTopics1().getSelectedItem().toString());	
		setValue2(getTopics2().getSelectedItem().toString());
		
		//load user values into query1 by calling CrimeQuery and passing the values
		setQuery1(new CrimeQuery(getValue1(),getValue2()));
		
		//get the table from QueryAll from CrimeQuery class
		JTable table=getQuery1().QueryAll();
		
		//add table with new options
		JScrollPane scrolltable = new JScrollPane(table);
		scrolltable.setBounds(500,10,500,500);
	
		getPanel1().add(scrolltable);
		
		getPanel1().add(getButton2());
		getPanel1().add(getButton5());
		getPanel1().add(getButton3());
		getPanel1().add(getButton6());
		getButton3().setBounds(100,10,230,60);
		getButton6().setBounds(100,215,230,60);
		getButton5().setBounds(100,315,230,60);
		getButton2().setBounds(100,410,230,60);
		
		
		
	}
	
	//this will display the sum of the two columns selected by user
	private void querySumMenu()
	{
		clearPanel(getPanel1());
		
		JLabel label1=new JLabel("This is the total numbers\nFor:"+getValue1()+"and"+getValue2());
		
		//get table from sumQuery and put it in table
		JTable table=getQuery1().sumQuery(); 
		
		JScrollPane scrolltable = new JScrollPane(table);
		label1.setBounds(100,10,400,40);
		scrolltable.setBounds(500,10,500,500);
		getPanel1().add(scrolltable);
		getPanel1().add(getButton3());
		getPanel1().add(getButton4());
		getButton3().setBounds(260,10,180,60);
		getButton4().setBounds(80,10,180,60);
	}
	
	//This  will return the top 10 worst places in regards to the columns selected
	private void queryMaxMenu()
	{
		clearPanel(getPanel1());
		
		JLabel label1=new JLabel("Top 10 worst from "+this.getValue1()+":");
		JLabel label2=new JLabel("Top 10 worst from "+this.getValue2()+":");
		
		//two separate tables for the queries
		JTable table1=getQuery1().MaxQuery(value1); 
		JTable table2=getQuery1().MaxQuery(value2); 
		
		//add them each to a scrolltable so they can be displayed properly
		JScrollPane scrolltable1 = new JScrollPane(table1);
		JScrollPane scrolltable2= new JScrollPane(table2);
		
		scrolltable1.setBounds(500,60,500,190);
		scrolltable2.setBounds(500,310,500,190);
		getButton3().setBounds(260,10,180,60);
		getButton4().setBounds(80,10,180,60);
		label1.setBounds(30,10,500,190);
		label2.setBounds(30,280,500,190);
		
		label1.setFont(new Font("Serif", Font.CENTER_BASELINE, 19));
		label2.setFont(new Font("Serif", Font.CENTER_BASELINE, 19));
		
		//add buttons with select new data and go back
		getPanel1().add(scrolltable1);
		getPanel1().add(scrolltable2);
		getPanel1().add(getButton3());
		getPanel1().add(getButton4());
		getPanel1().add(label1);
		getPanel1().add(label2);
	}
	
	//This will select the Top 10 safest places in regards to user option
	private void queryMinMenu()
	{
		clearPanel(getPanel1());
		
		JLabel label1=new JLabel("Top 10 safe from "+this.getValue1()+":");
		JLabel label2=new JLabel("Top 10 safe from "+this.getValue2()+":");
		
		//min query will also take each value individually and display it next to each other
		JTable table1=getQuery1().MinQuery(value1); 
		JTable table2=getQuery1().MinQuery(value2); 
		
		
		JScrollPane scrolltable1 = new JScrollPane(table1);
		JScrollPane scrolltable2= new JScrollPane(table2);
		
		scrolltable1.setBounds(500,60,500,190);
		scrolltable2.setBounds(500,310,500,190);
		getButton3().setBounds(260,10,180,60);
		getButton4().setBounds(80,10,180,60);
		label1.setBounds(30,10,500,190);
		label2.setBounds(30,280,500,190);
		
		label1.setFont(new Font("Serif", Font.CENTER_BASELINE, 19));
		label2.setFont(new Font("Serif", Font.CENTER_BASELINE, 19));
		
		getPanel1().add(scrolltable1);
		getPanel1().add(scrolltable2);
		getPanel1().add(getButton3());
		getPanel1().add(getButton4());
		getPanel1().add(label1);
		getPanel1().add(label2);
		
		
	}
	
	
	//this will clear the panel so i wont call 4 lines of code each time
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
	
	
	//setters and getters


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


	public JButton getButton1() {
		return button1;
	}


	public void setButton1(JButton button1) {
		this.button1 = button1;
	}


	public JButton getButton2() {
		return button2;
	}


	public void setButton2(JButton button2) {
		this.button2 = button2;
	}


	public JButton getButton3() {
		return button3;
	}


	public void setButton3(JButton button3) {
		this.button3 = button3;
	}


	public JButton getButton4() {
		return button4;
	}


	public void setButton4(JButton button4) {
		this.button4 = button4;
	}


	public JButton getButton5() {
		return button5;
	}


	public void setButton5(JButton button5) {
		this.button5 = button5;
	}


	public JButton getButton6() {
		return button6;
	}


	public void setButton6(JButton button6) {
		this.button6 = button6;
	}


	public JPanel getPanel1() {
		return Panel1;
	}


	public void setPanel1(JPanel panel1) {
		Panel1 = panel1;
	}


	public JComboBox<String> getTopics1() {
		return topics1;
	}


	public void setTopics1(JComboBox<String> topics1) {
		this.topics1 = topics1;
	}


	public JComboBox<String> getTopics2() {
		return topics2;
	}


	public void setTopics2(JComboBox<String> topics2) {
		this.topics2 = topics2;
	}


	public String[] getColumnNames() {
		return columnNames;
	}


	public void setColumnNames(String[] columnNames) {
		this.columnNames = columnNames;
	}


	
}
