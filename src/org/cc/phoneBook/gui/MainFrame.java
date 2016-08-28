package org.cc.phoneBook.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.cc.phoneBook.util.ScreenUtil;
import javax.swing.JTextField;
import java.awt.CardLayout;
import javax.swing.BoxLayout;
import java.awt.Toolkit;

public class MainFrame extends JFrame {

	public static int FRAME_WIDTH=350;
	public static int FRAME_HEIGHT=500;
	private JTextField textField;
	
	public MainFrame() {
		setTitle("电话本管理系统 v0.1");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/org/cc/phoneBook/resource/phone_book.png")));
		
		intiLayout();
		
	}
	
	private void intiLayout(){
		
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setLocation(ScreenUtil.getScreenCenter(FRAME_WIDTH,FRAME_HEIGHT));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("搜索");
		panel.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		scrollPane.setViewportView(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		
		for(int i=0;i<100;i++){
			panel_1.add(new JButton("New button"));
		}
		
		
		setVisible(true);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
