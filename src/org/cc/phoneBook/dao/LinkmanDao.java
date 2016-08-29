package org.cc.phoneBook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.cc.phoneBook.entity.Linkman;
import org.cc.phoneBook.util.DbUtil;

public class LinkmanDao {

	/**
	 * 添加联系人
	 * @param e
	 */
	public void add(Linkman e){
		
		Connection conn=null;

		try {
			conn=DbUtil.getInstance().getConnection();
			conn.setAutoCommit(false);
			
			//先把人插入到t_phonebook表中
			String sql="INSERT INTO t_phonebook (name,sex,age,qq,address,comment) VALUES (?,?,?,?,?,?)";
			dml(conn,sql,e.getName(),e.getSex(),e.getAge(),e.getQq(),e.getAddress(),e.getDesc());
			
			//因为这个时候事务没提交，所以只好手动的计算下一个id（感觉这么做很不好...待改进）
			sql="SELECT * FROM t_phonebook LEFT JOIN t_phonenumber ON t_phonebook.id=t_phonenumber.id ORDER BY t_phonebook.id DESC LIMIT 1";
			int id=dql(sql).get(0).getId()+1;
			
			//再将电话号码插入到t_phonenumber表中
			sql="INSERT INTO t_phonenumber (id,phonenumber) values (?,?)";
			List<String> numbers=e.getPhoneNumber();
			for(int i=0;i<numbers.size();i++){
				dml(conn,sql,id,numbers.get(i));
			}
			
			conn.commit();
		} catch (SQLException e1) {
			e1.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}finally{
			DbUtil.getInstance().close(conn);
		}
	}
	
	/**
	 * 删除联系人
	 * @param id
	 */
	public void del(int id){
		
		Connection conn=null;
		try {
			conn=DbUtil.getInstance().getConnection();
			conn.setAutoCommit(false);
			
			
			//先将号码删除（子表）
			String  sql="DELETE FROM t_phonenumber WHERE id=?";
			dml(conn,sql,id);
			
			//再将人删除（父表）
			sql="DELETE FROM t_phonebook WHERE id=?";
			dml(conn,sql,id);
			
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			DbUtil.getInstance().close(conn);
		}
	}
	
	/**
	 * 修改联系人
	 * @param e
	 */
	public void modify(Linkman e){
//		//修改的操作定义为先删除再添加(但是这个样子的话id会发生变化的)
//		del(e.getId());
//		add(e);
		
		Connection conn=null;
		
		try {
			conn=DbUtil.getInstance().getConnection();
			conn.setAutoCommit(false);
			
			String sql="DELETE FROM t_phonenumber WHERE id=?";
			dml(conn,sql,e.getId());
			
			sql="INSERT INTO t_phonenumber (id,phoneNumber) VALUES (?,?)";
			for(int i=0;i<e.getPhoneNumber().size();i++){
				dml(conn,sql,e.getId(),e.getPhoneNumber().get(i));
			}
			
			sql="UPDATE t_phonebook SET name=?, sex=?, age=?, qq=?, address=?, comment=? WHERE id=?";
			dml(conn,sql,e.getName(),e.getSex(),e.getAge(),e.getQq(),e.getAddress(),e.getDesc(),e.getId());
			
			conn.commit();
		} catch (SQLException e1) {
			e1.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}finally{
			DbUtil.getInstance().close(conn);
		}
	}
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<Linkman> list(){
		String sql="SELECT * FROM t_phonebook JOIN t_phonenumber ON t_phonebook.id=t_phonenumber.id";
		return dql(sql);
	}
	
	/**
	 * 执行dml语句（因为可能有事务，所以需要传入Connection）
	 * @param conn
	 * @param sql
	 * @param args
	 * @throws SQLException 
	 */
	public void dml(Connection conn,String sql,Object ...args) throws SQLException{
		
		PreparedStatement pstmt=null;
		
		try {
			pstmt=conn.prepareStatement(sql);
			
			for(int i=0;i<args.length;i++){
				pstmt.setObject(i+1,args[i]);
			}
			
			pstmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}finally{
			DbUtil.getInstance().close(pstmt);
		}
	}
	
	/**
	 * 执行dql
	 * @param sql
	 * @return
	 */
	public List<Linkman> dql(String sql){
		
		List<Linkman> list=new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn=DbUtil.getInstance().getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				
				Linkman e=new Linkman();
				
				e.setId(rs.getInt("id"));
				
				int t=list.indexOf(e);
				if(t!=-1){
					list.get(t).getPhoneNumber().add(rs.getString("phoneNumber"));
					continue;
				}
				
				e.setName(rs.getString("name"));
				e.setAge(rs.getInt("age"));
				e.setAddress(rs.getString("address"));
				e.setDesc(rs.getString("comment"));
				e.setQq(rs.getString("qq"));
				e.setSex(rs.getString("sex"));
				
				List<String> phoneNumbers=new ArrayList<>();
				phoneNumbers.add(rs.getString("phoneNumber"));
				
				e.setPhoneNumber(phoneNumbers);
				
				list.add(e);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DbUtil.getInstance().close(conn,pstmt,rs);
		}
		
		return list;
	}
	
}
