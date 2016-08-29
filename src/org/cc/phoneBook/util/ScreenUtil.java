package org.cc.phoneBook.util;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

/**
 * 用于屏幕辅助计算的工具类
 * @author cc
 *
 */
public class ScreenUtil {

	//needs the window frame width and height. 返回窗体若居中时应处位置
	public static Point getScreenCenter(int width,int height){
		int x=Toolkit.getDefaultToolkit().getScreenSize().width/2-width/2;
		int y=Toolkit.getDefaultToolkit().getScreenSize().height/2-height/2;
		return new Point(x,y);
	}
	
	//返回屏幕大小
	public static  Dimension getScreenSize(){
		return Toolkit.getDefaultToolkit().getScreenSize();
	}
	
	//如果与某元素居中的话那么偏移量是多少呢
	public static Point getLocationOnFooCenter(int w,int h,int pw,int ph){
		return new Point((pw-w)/2,(ph-h)/2);
	}
	
}
