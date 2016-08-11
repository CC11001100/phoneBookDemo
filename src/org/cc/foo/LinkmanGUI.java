package org.cc.foo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * GUI负责交互，显示一些乱七八糟的丑得不行的界面   （View）
 * @author cc
 *
 */
public class LinkmanGUI {

	private Scanner sc=new Scanner(System.in);
	private String logo="";
	
	private static LinkmanGUI instance;
	
	public static LinkmanGUI getInstance() {
		if(instance==null) instance=new LinkmanGUI();
		return instance;
	}

	private LinkmanGUI() {
		//读取Logo
		try {
			BufferedReader reader=new BufferedReader(new FileReader(new File(Thread.currentThread().getContextClassLoader().getResource("org/cc/foo/resource/logo.txt").getPath())));
			StringBuilder sb=new StringBuilder();
			while(reader.ready()) sb.append(reader.readLine()).append("\n");
			logo=sb.toString();
		} catch (FileNotFoundException e) {
//			e.printStackTrace();
		} catch (IOException e) {
//			e.printStackTrace();
		}
	}
	
	//读入用户的选项
	public int readChoose(){
		return readValidNumber("请选择业务：");
	}
	
	//添加联系人
	public void add(){
		System.out.println("电话本管理系统-->添加联系人");
		showAllNames();
		
		Linkman e=readLinkman(null);
		
		boolean res=LinkmanManager.getInstance().add(e);
		
		if(res){
			System.out.println("√ 添加联系人成功：");
			System.out.println(e);
		}else{
			System.out.println("× 添加联系人失败。   :(");
		}
		
	}
	
	//删除联系人
	public void delete(){
		System.out.println("电话本管理系统-->删除联系人");
		
		showAllNames();
		
		String name=readLineNotSpace("请输入要删除的人的名字：");
		
		if(LinkmanManager.getInstance().load(name)==null){
			System.out.println("× 查无此人");
		}else{
			String comfirm=readLineNotSpace("确定删除？Y/N：");
			if("Y".equalsIgnoreCase(comfirm) && LinkmanManager.getInstance().remove(name)){
				System.out.println("√ 删除联系人"+name+"，您失去了一位朋友。");
			}else{
				System.out.println("× 未删除。");
			}
		}
	}
	
	//修改
	public void modify(){
		System.out.println("电话本管理系统-->修改联系人");
		showAllNames();
		
		String name=readLineNotSpace("请输入要修改的姓名：");
		
		if(LinkmanManager.getInstance().load(name)==null){
			System.out.println("× 此联系人不存在");
		}else{
			Linkman e=readLinkman(name);
			LinkmanManager.getInstance().modify(name,e);
		}
		
	}
	
	//显示所有联系人信息
	public void queryAll(){
		System.out.println("电话本管理系统-->所有联系人信息");
		Linkman list[]=LinkmanManager.getInstance().list();
		for(int i=0;i<list.length;i++){
			System.out.println((i+1)+".\n"+list[i]);
		}
		
	}
	
	//根据名字查询
	public void queryByName(){
		System.out.println("电话本管理系统-->根据名字查询");
		showAllNames();
		String name=readLineNotSpace("请输入名字查询详细信息：");
		Linkman e=LinkmanManager.getInstance().load(name);
		
		if(e!=null){
			System.out.println("\n"+e);
		}else{
			System.out.println("× 查无此人！");
		}
		
	}
	
	//退出
	public void exit(){
		System.out.println("Bye~");
		System.exit(0);
	}
	
	//显示欢迎信息
	public void showBanner(){
		System.out.println(logo);
		System.out.println("------------------------------电话本管理系统-----------------------------");
		System.out.println("1.添加            2.删除          3.修改           4.查询所有            5.根据姓名查询           6.退出");
	}
	
	//在输入不合法的选项的时候打印错误信息
	public void showErrorMessage(){
		System.err.println("WARNNING： 无法识别的选项...");
	}
	
	//从控制台读入一个联系人，因为用了多次，冗余就抽取出来了
	private Linkman readLinkman(String name){
		Linkman e=new Linkman();
		
		e.setName(readLineNotSpace("姓名："));
		
		//不许重名
		while(LinkmanManager.getInstance().load(e.getName())!=null && !e.getName().equalsIgnoreCase(name)){
			e.setName(readLineNotSpace("联系人已存在，请重新输入姓名："));
		}
		
		e.setSex(readLineNotSpace("性别："));
		e.setAge(readValidNumber("年龄："));
		e.setPhoneNumber(readLineNotSpace("电话："));
		e.setQq(readLineNotSpace("QQ："));
		e.setAddress(readLineNotSpace("地址："));
		
		return e;
	}
	
	//显示所有人的名字以方便用户操作，不然的话总感觉很...2b...
	private void showAllNames(){
		Linkman list[]=LinkmanManager.getInstance().list();
		if(list.length==0) return;
		
		System.out.println("当前所有联系人：");
		for(int i=0;i<list.length;i++){
			System.out.println((i+1)+". "+list[i].getName());
		}
	}
	
	//读入一行文本但不为空
	private String readLineNotSpace(String tipMessage){
		String s="";
		while("".equals(s.trim())){
			System.out.print(tipMessage);
			s=sc.nextLine();
		}
		return s;
	}
	
	//读入一个有效的数字
	private int readValidNumber(String tipMessage){
		String s="";
		while(true){
			System.out.print(tipMessage);
			s=sc.nextLine();
			try {
				int res=Integer.parseInt(s);
				return res;
			} catch (NumberFormatException e) {
				continue;
			}
		}
	}
}
