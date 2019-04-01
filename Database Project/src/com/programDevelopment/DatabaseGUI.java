package com.programDevelopment;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.Random;

public class DatabaseGUI extends JFrame implements ActionListener, MouseListener
{
	
	private JButton button1;
	private DatabaseConnection connection1;
	private String[] columnNames= new String[] {"Station", "Divisions",
            "Dangerous Acts 2004", "Dangerous Acts 2016"};
	
	public DatabaseGUI()
	{	
		super("Crime Data info");
		setLayout(new BorderLayout());
		
		JFrame Frame=new JFrame("Crime Data Information");
		JPanel Panel1=new JPanel();
		button1=new JButton("click to query");
		
		JComboBox<String> topics1= new JComboBox<>(columnNames);
		JComboBox<String> topics2= new JComboBox<>(columnNames);
		
		Panel1.setLayout(null);
		
		add(Panel1,BorderLayout.CENTER);
		
				
		Panel1.add(button1);
		Panel1.add(topics1);
		Panel1.add(topics2);
		
		button1.setBounds(100,450,150,40);
		topics1.setBounds(100,350,150,40);
		topics2.setBounds(100,250,150,40);
		
		
		
		Panel1.setSize(1080,700);
		setSize(1080,700);
		setVisible(true);
		button1.addActionListener(this);
		
		//removes the program from memory
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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

	@Override
	public void actionPerformed(ActionEvent anything) 
	{
		if (anything.getSource() == button1)
		{
			//System.out.println("Button is pressed");
			connection1 =  new DatabaseConnection();
			JOptionPane.showMessageDialog(this,connection1);
		
			
		}
	}
	
}
