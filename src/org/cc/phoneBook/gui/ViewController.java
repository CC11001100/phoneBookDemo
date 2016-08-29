package org.cc.phoneBook.gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.cc.phoneBook.entity.Linkman;
import org.cc.phoneBook.service.LinkmanService;
import org.cc.phoneBook.util.ScreenUtil;

import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;

/**
 * 是数据和视图的接口,负责协调它们
 * @author CC11001100
 *
 */
public class ViewController {

	private LinkmanService service;
	
	private JScrollPane lastFillScrollPanel;
	
	private ViewController() {
		service=new LinkmanService();
	}
	
	private static ViewController instance;
	
	public static ViewController getInstance(){
		if(instance==null) instance=new ViewController();
		return instance;
	}

	/**
	 * 初始化联系人卡片
	 */
	public void fillCardItem(JScrollPane scrollPane){
		this.lastFillScrollPanel=scrollPane;
		
		JPanel panel=new JPanel();
		panel.setLayout(new GridLayout(0, 1, 1, 0));
		scrollPane.setViewportView(panel);
		
		List<Linkman> list=service.list();
		
		for(int i=0;i<list.size();i++){
			CardItem t=new CardItem(list.get(i));
			if(i%2==0){
				t.setBackground(new Color(31,207,109));
			}else{
				t.setBackground(new Color(1,191,157));
			}
			panel.add(t);
		}
	}
	
	/**
	 * 删除一个联系人
	 */
	public void del(int id){
		//从数据库中删除
		service.del(id);
		//重新渲染
		fillCardItem(lastFillScrollPanel);
	}
	
	/**
	 * 修改数据
	 */
	public void modify(Linkman data) {
//		JDialog dialog=new MyDialogPanel(data,MyDialogPanel.MODIFY);
//		dialog.setModal(true);
//		dialog.setVisible(true);
		service.modify(data);
		fillCardItem(lastFillScrollPanel);
	}

	/**
	 * 根据关键词过滤（支持拼音）
	 */
	public void fillCardItemByFilter(String keyword) {
		JPanel panel=new JPanel();
		panel.setLayout(new GridLayout(0, 1, 1, 0));
		lastFillScrollPanel.setViewportView(panel);
		
		List<Linkman> list=service.list();
		
		try {
			for(int i=list.size()-1;i>=0;i--){
				String name=list.get(i).getName();
				if(name.indexOf(keyword)==-1 && PinyinHelper.convertToPinyinString(name,"",PinyinFormat.WITHOUT_TONE).indexOf(keyword)==-1){
					list.remove(i);
				}
			}
		} catch (PinyinException e) {
			e.printStackTrace();
		}
		
		for(int i=0;i<list.size();i++){
			CardItem t=new CardItem(list.get(i));
			if(i%2==0){
				t.setBackground(new Color(31,207,109));
			}else{
				t.setBackground(new Color(1,191,157));
			}
			panel.add(t);
		}
	}

	/**
	 * 添加一个联系人
	 * @param linkman
	 */
	public void add(Linkman e) {
		//先添加
		service.add(e);
		//重新渲染
				fillCardItem(lastFillScrollPanel);
	}
	
}
