package org.cc.phoneBook.test;

import java.util.ArrayList;
import java.util.List;

import org.cc.phoneBook.dao.LinkmanDao;
import org.cc.phoneBook.entity.Linkman;
import org.junit.Test;

public class Test_001 {

	
	@Test
	public void test_001(){
		
		Linkman e=new Linkman();
		
		e.setName("CCCCCC");
		e.setAge(21);
		e.setAddress("地球");
		e.setDesc("人");
		e.setQq("123456");
		e.setSex("男");
		
		List<String> numbers=new ArrayList<>();
		numbers.add("13791486999");
		numbers.add("13791486922");
		e.setPhoneNumber(numbers);
		
		LinkmanDao dao=new LinkmanDao();
//		
//		//删除OK
////		dao.del(1001);
//		
//		//添加OK
////		dao.add(e);
//		
//		//查询OK
//		List<Linkman> list=dao.list();
//		for(Linkman o:list){
//			System.out.println(o.getName());
//		}
//		
//		//修改OK
//		e=list.get(0);
//		e.setName("赵子龙");
//		dao.modify(e);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
