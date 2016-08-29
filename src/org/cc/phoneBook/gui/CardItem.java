package org.cc.phoneBook.gui;

import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import org.cc.phoneBook.entity.Linkman;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;

/**
 * 将联系人的信息以卡片的形式显示出来
 * 
 * @author CC11001100
 *
 */
public class CardItem extends JPanel {

	//显示的数据
	private Linkman data;
	
	public CardItem(Linkman e) {
		setToolTipText("双击编辑");
		
		this.data=e;
		
		//设置大小和绝对布局，不然的话就会被挤没掉
		setPreferredSize(new Dimension(315, 100));
		setMinimumSize(getPreferredSize());
		setLayout(null);
		
		//设置姓名
		JLabel lblNewLabel = new JLabel(e.getName());
		lblNewLabel.setBounds(10, 10, 42, 15);
		add(lblNewLabel);
		
		//设置性别，以图标显示
		JLabel lblNewLabel_1 = new JLabel("");
		if("男".equals(e.getSex())){
			lblNewLabel_1.setIcon(new ImageIcon(CardItem.class.getResource("/org/cc/phoneBook/resource/男.png")));
		}else if("女".equals(e.getSex())){
			lblNewLabel_1.setIcon(new ImageIcon(CardItem.class.getResource("/org/cc/phoneBook/resource/女.png")));
		}
		lblNewLabel_1.setBounds(62, 10, 15, 15);
		add(lblNewLabel_1);
		
		StringBuilder sb=new StringBuilder();
		sb.append("<html>").append("电话：");
		List<String> numers=e.getPhoneNumber();
		for(int i=0;i<numers.size();i++){
			sb.append(numers.get(i));
			if(i!=numers.size()-1) sb.append(", ");
		}
		sb.append("</html>");
		JLabel lblNewLabel_2 = new JLabel(sb.toString());
		lblNewLabel_2.setBounds(10, 35, 295, 15);
		add(lblNewLabel_2);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(CardItem.class.getResource("/org/cc/phoneBook/resource/删除.png")));
		label.setBounds(285, 10, 20, 20);
		//添加监听器
		label.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				//删除当前联系人
				int t=JOptionPane.showConfirmDialog(null,"确认删除此联系人？","确认操作",JOptionPane.OK_CANCEL_OPTION);
				if(t==JOptionPane.OK_OPTION){
					ViewController.getInstance().del(data.getId());
				}
			}
		});
		add(label);
		
		JLabel lblAge = new JLabel(e.getAge()==0?"":""+e.getAge());
		lblAge.setBounds(87, 10, 15, 15);
		add(lblAge);
		
		JLabel lblQq = new JLabel("".equals(e.getQq())?"":"QQ:"+e.getQq());
		lblQq.setBounds(121, 10, 82, 15);
		add(lblQq);
		
		JLabel lblAddress = new JLabel(e.getAddress());
		lblAddress.setBounds(203, 10, 72, 15);
		add(lblAddress);

		sb=new StringBuilder();
		sb.append("<html>").append("备注：").append(e.getDesc()).append("<html>");
		JLabel lblComment = new JLabel(sb.toString());
		lblComment.setBounds(10, 60, 295, 33);
		add(lblComment);
		
		addMouseListener(new MouseAdapter() {
			
			//记录上次单击时间
			private long lastClicked=0;
			
			@Override
			public void mouseClicked(MouseEvent e) {
				long t=lastClicked;
				lastClicked=System.currentTimeMillis();
				if(System.currentTimeMillis()-t<500){
					//500毫秒内单击两次认为是双击
					MyDialogPanel dialog=new MyDialogPanel(CardItem.this.data,MyDialogPanel.MODIFY,CardItem.this.getBackground());
					dialog.setVisible(true);
				}
			}
			
			//试图实现拖拉滑动效果....
//			//记录鼠标是否按下
//			private boolean pressed;
//			
//			@Override
//			public void mousePressed(MouseEvent e) {
//				pressed=true;
//			}
//			
//			@Override
//			public void mouseReleased(MouseEvent e) {
//				pressed=false;
//			}
//			
//			@Override
//			public void mouseMoved(MouseEvent e) {
//				System.out.println(e.getX());
//			}
			
		});
	}
}
