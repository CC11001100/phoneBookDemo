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

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BoxLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

	public static int FRAME_WIDTH=350;
	public static int FRAME_HEIGHT=500;
	private JTextField textField;
	
	public MainFrame() {
		setBackground(Color.RED);
		setTitle("电话本管理系统 v0.1");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/org/cc/phoneBook/resource/phone_book.png")));
		
		intiLayout();
		
	}
	
	private void intiLayout(){
		
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setLocation(ScreenUtil.getScreenCenter(FRAME_WIDTH,FRAME_HEIGHT));
		
		setBackground(new Color(1,161,135));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(165,202,244));
		getContentPane().add(panel, BorderLayout.NORTH);
		MouseListener searchListener=new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String keyword=textField.getText().trim();
				ViewController.getInstance().fillCardItemByFilter(keyword);
			}
		};
		
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String keyword=textField.getText().trim();
				ViewController.getInstance().fillCardItemByFilter(keyword);
			}
		});
		textField.setBackground(new Color(165,202,240));
		panel.add(textField);
		textField.setColumns(10);
		
		final JLabel label = new JLabel("");
		
		label.addMouseListener(searchListener);
		label.setIcon(new ImageIcon(MainFrame.class.getResource("/org/cc/phoneBook/resource/searching15.png")));
		panel.add(label);
		
		JLabel label_1 = new JLabel("");
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MyDialogPanel dialog=new MyDialogPanel(null,MyDialogPanel.ADD,new Color(31,207,109));
				dialog.setVisible(true);
			}
		});
		label_1.setIcon(new ImageIcon(MainFrame.class.getResource("/org/cc/phoneBook/resource/增加.png")));
		panel.add(label_1);
		
//		AbstractAction search=new AbstractAction(){
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				
//			}
//			
//		};
//		panel.add(search);
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		ViewController.getInstance().fillCardItem(scrollPane);
		
		setVisible(true);
		
	}
	
}
