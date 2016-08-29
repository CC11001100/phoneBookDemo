package org.cc.phoneBook.service;

import java.util.List;

import org.cc.phoneBook.dao.LinkmanDao;
import org.cc.phoneBook.entity.Linkman;

/**
 * Linkman service
 * @author CC11001100
 *
 */
public class LinkmanService {

	private LinkmanDao dao;
	
	public LinkmanService() {
		dao=new LinkmanDao();
	}
	
	public void add(Linkman e){
		dao.add(e);
	}
	
	public void del(int id){
		dao.del(id);
	}
	
	public void modify(Linkman e){
		dao.modify(e);
	}
	
	public List<Linkman> list(){
		return dao.list();
	}
	
}
