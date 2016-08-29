package org.cc.phoneBook.gui;

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;

import javax.swing.JLabel;
import javax.swing.JTextField;

import org.cc.phoneBook.entity.Linkman;
import org.cc.phoneBook.util.ScreenUtil;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;

public class MyDialogPanel extends JDialog {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	
	private JComboBox comboBox;
	
	public static int ADD=1;
	public static int MODIFY=2;
	
	private int operType;
	private Linkman data;
	
	public MyDialogPanel(Linkman e, final int operType,Color color) {
		this.data=e;
		this.operType=operType;
		
		setTitle("添加联系人");
		
		setBackground(color);
		setSize(251,366);
//		setLocation(new Point(ScreenUtil.getLocationOnFooCenter(getWidth(),getHeight(),MainFrame.FRAME_WIDTH,MainFrame.HEIGHT)));
		
		setLocation(ScreenUtil.getScreenCenter(getWidth(),getHeight()));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		
		JLabel lblNewLabel = new JLabel("<html>\r\n<span style=\"color:red;font-size:15px;\"><strong>*</strong></span>姓名：\r\n</html>");
		panel_1.add(lblNewLabel);
		
		textField = new JTextField();
		panel_1.add(textField);
		textField.setColumns(10);
		
		JPanel panel_7 = new JPanel();
		panel.add(panel_7);
		panel_7.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel label_5 = new JLabel("            性别：");
		panel_7.add(label_5);
		
		JPanel panel_9 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_9.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_7.add(panel_9);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"男", "女"}));
		panel_9.add(comboBox);
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3);
		
		JLabel label_1 = new JLabel("<html>\r\n<span style=\"color:red;font-size:15px;\"><strong>*</strong></span>电话：\r\n</html>");
		panel_3.add(label_1);
		
		textField_2 = new JTextField();
		textField_2.setToolTipText("多个号码使用英文逗号分隔");
		textField_2.setColumns(10);
		panel_3.add(textField_2);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		
		JLabel label = new JLabel("备注：");
		panel_2.add(label);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		panel_2.add(textField_1);
		
		JPanel panel_4 = new JPanel();
		panel.add(panel_4);
		
		JLabel label_2 = new JLabel("地址：");
		panel_4.add(label_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		panel_4.add(textField_3);
		
		JPanel panel_5 = new JPanel();
		panel.add(panel_5);
		
		JLabel label_3 = new JLabel("QQ：");
		panel_5.add(label_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		panel_5.add(textField_4);
		
		JPanel panel_6 = new JPanel();
		panel.add(panel_6);
		
		JLabel label_4 = new JLabel("年龄：");
		panel_6.add(label_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		panel_6.add(textField_5);
		
		JLabel label_6 = new JLabel("");
		panel.add(label_6);
		
		JPanel panel_8 = new JPanel();
		panel.add(panel_8);
		
		JButton btnNewButton = new JButton("添加");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Linkman linkman=checkup();
				
				//说明读取数据成功了
				if(linkman!=null){
					//添加
					if(operType==ADD){
						ViewController.getInstance().add(linkman);
						JOptionPane.showMessageDialog(MyDialogPanel.this,"添加成功","提示",JOptionPane.PLAIN_MESSAGE);
						MyDialogPanel.this.dispose();
					}
					//修改
					if(operType==MODIFY){
						linkman.setId(MyDialogPanel.this.data.getId());
						ViewController.getInstance().modify(linkman);
						JOptionPane.showMessageDialog(MyDialogPanel.this,"修改成功","提示",JOptionPane.PLAIN_MESSAGE);
						MyDialogPanel.this.dispose();
					}
				}
				
			}
		});
		panel_8.add(btnNewButton);
		
		JLabel label_7 = new JLabel("       ");
		panel_8.add(label_7);
		
		JButton btnNewButton_1 = new JButton("取消");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MyDialogPanel.this.dispose();
			}
		});
		panel_8.add(btnNewButton_1);
		
		if(operType==MODIFY){
			setTitle("修改信息");
			btnNewButton.setText("修改");
			
			textField.setText(e.getName());
			comboBox.setSelectedItem(e.getSex());
			textField_4.setText(e.getQq());
			textField_3.setText(e.getAddress());
			textField_5.setText(Integer.toString(e.getAge()));
			textField_1.setText(e.getDesc());
			
			List<String> numers=e.getPhoneNumber();
			StringBuilder sb=new StringBuilder();
			
			for(int i=0;i<numers.size();i++){
				sb.append(numers.get(i));
				if(i!=numers.size()-1) sb.append(",");
			}
			textField_2.setText(sb.toString());
			
		}
		
	}
	
	//检查数据有效性同时返回
	private Linkman checkup(){
		Linkman e=new Linkman();
		
		String name=textField.getText().trim();
		
		if("".equals(name)){
			JOptionPane.showMessageDialog(this,"姓名必须填写","警告",JOptionPane.WARNING_MESSAGE);
			return null;
		}
		e.setName(name);
		
		String phoneNumber=textField_2.getText().trim();
		if("".equals(name)){
			JOptionPane.showMessageDialog(this,"电话必须填写","警告",JOptionPane.WARNING_MESSAGE);
			return null;
		}
		e.setPhoneNumber(Arrays.asList(phoneNumber.split(",")));
		
		e.setSex(comboBox.getSelectedItem().toString());
		e.setAddress(textField_3.getText().trim());
		e.setQq(textField_4.getText().trim());
		
		String sAge=textField_5.getText();
		if(!"".equals(sAge)){
			try {
				e.setAge(Integer.parseInt(sAge));
			} catch (NumberFormatException e1) {
//				e1.printStackTrace();
				JOptionPane.showMessageDialog(this,"年龄是数字","警告",JOptionPane.WARNING_MESSAGE);
				textField_5.setText("");
			}
		}
		
		e.setDesc(textField_1.getText().trim());
		
		return e;
	}

}
