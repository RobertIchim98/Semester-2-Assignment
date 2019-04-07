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
	
	private JButton button1;
	private DatabaseConnection connection1;
	private String[] columnNames= new String[] {
            "DANGEROUS_ACTS_2004", "DANGEROUS_ACTS_2016",
            "ATTEMPTMURDER_RELATED2004","ATTEMPTMURDER_RELATED2016",
            "KIDNAPPING_RELATED2004","KIDNAPPING_RELATED2016",
            "BURGLARY_RELATED2004","BURGLARY_RELATED2016",
            "WEAPONS_EXPLOSIVES2004","WEAPONS_EXPLOSIVES2016"};
	private JComboBox<String> topics1= new JComboBox<>(columnNames);
	private JComboBox<String> topics2= new JComboBox<>(columnNames);
	
	//test purposes 
	String jcolumn[]= {"DANGEROUS_ACTS_2004", "DANGEROUS_ACTS_2016"};
	String[][] jdata= { 
            { "Crime this", "Crime That"}, 
            { "yesyes", "hello"} 
        }; 
	
	public Vector<String> columns1=new Vector<String>();
	public Vector<String> data=new Vector<String>();
	private JTable table=new JTable(jdata,jcolumn);
	
	
	public DatabaseGUI()
	{	
		super("Irish Crime Data info");
		setLayout(new BorderLayout());
		
		//JFrame Frame=new JFrame("Crime Data Information");
		JPanel Panel1=new JPanel();
		JLabel label1=new JLabel("Select two rows to inspect data:");
		button1=new JButton("Query The selected");
		JScrollPane scrolltable = new JScrollPane(table); 
		
		/*
		columns1.addElement("Crime Stuff");
		columns1.addElement("More Crime Stuff");
		data.addElement("Stufff");
		data.addElement("More Stuff");
		*/
		Panel1.setLayout(null);
		
		add(Panel1,BorderLayout.CENTER);
		
				
		Panel1.add(button1);
		Panel1.add(topics1);
		Panel1.add(topics2);
		Panel1.add(label1);
		Panel1.add(scrolltable);
		
		label1.setBounds(100,10,400,40);
		button1.setBounds(100,350,230,60);
		topics1.setBounds(100,80,230,60);
		topics2.setBounds(100,215,230,60);
		table.setBounds(500,10,500,500);
		scrolltable.setBounds(500,10,500,500);
		label1.setFont(new Font("Serif", Font.CENTER_BASELINE, 24));
		
		
		
		Panel1.setSize(1080,700);
		setSize(1080,600);
		setVisible(true);
		button1.addActionListener(this);
		
		//removes the program from memory
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent anything) 
	{
		if (anything.getSource() == button1)
		{	
			String value1=topics1.getSelectedItem().toString();	
			String value2=topics2.getSelectedItem().toString();
			
			CrimeQuery query1=new CrimeQuery(value1,value2);
			try
			{
				JOptionPane.showMessageDialog(this,table);
				table=query1.QueryAll();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
			
			
		}
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


	
}
