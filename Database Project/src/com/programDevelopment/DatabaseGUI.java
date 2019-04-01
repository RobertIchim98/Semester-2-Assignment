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
	public DatabaseConnection connection1;
	//private DatabaseConnection connection1;
	
	public DatabaseGUI()
	{	
		super("Crime Data info");
		setLayout(new BorderLayout());
		
		JFrame Frame=new JFrame("Crime Data Information");
		JPanel Panel1=new JPanel();
		button1=new JButton("click to query");
		Panel1.setLayout(null);
		
		add(Panel1,BorderLayout.CENTER);
		
				
		Panel1.add(button1);
		button1.setBounds(50,100,100,40);
		
		
		
		Panel1.setSize(700,700);
		setSize(700,700);
		setVisible(true);
		button1.addActionListener(this);
		
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
