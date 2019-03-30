package com.programDevelopment;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import java.util.Random;

public class DatabaseGUI extends JFrame implements ActionListener, MouseListener
{
	
	private JButton button1;
	private DatabaseConnection connection1;
	
	public DatabaseGUI()
	{	
		super("Crime Data info");
		setLayout(new BorderLayout());
		
		JFrame Frame=new JFrame("Crime Data Information");
		JPanel Panel1=new JPanel();
		button1=new JButton("click to query");
		//DatabaseConnection connection1=new DatabaseConnection();
		
		
		add(Panel1,BorderLayout.CENTER);
		
		Panel1.add(button1);
		
		Panel1.setSize(700,700);
		setSize(700,700);
		setVisible(true);
		
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
			DatabaseConnection connection1=new DatabaseConnection();
			
		}
	}
	
}
