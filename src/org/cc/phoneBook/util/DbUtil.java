package org.cc.phoneBook.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 数据库工具类
 * @author CC11001100
 *
 */
public class DbUtil {

	//保存连接属性
	private Properties attr;
	//默认的配置文件位置
	private String DEFAULT_CONFIG="org/cc/phoneBook/resource/db.properties";
	
	private DbUtil() {
		attr=new Properties();
		try {
			//在构造器中加载，尽量少的进行IO操作
			attr.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(DEFAULT_CONFIG));
			Class.forName(attr.getProperty("driver"));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private static DbUtil instance;
	
	public static DbUtil getInstance(){
		if(instance==null) instance=new DbUtil();
		return instance;
	}
	
	/**
	 * 重新加载配置文件
	 * @param attr
	 */
	public void load(Properties attr){
		this.attr=attr;
	}
	
	/**
	 * 重新加载配置文件
	 * @param configPath
	 */
	public void load(String configPath){
		attr=new Properties();
		try {
			attr.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(configPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获得一个数据库连接
	 * @return
	 */
	public Connection getConnection(){
		try {
			return DriverManager.getConnection(attr.getProperty("url"),attr);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 关闭Connection和Statement
	 * @param conn
	 * @param stmt
	 */
	public void close(Connection conn,Statement stmt){
		close(conn,stmt,null);
	}
	
	public void close(Connection conn){
		close(conn,null,null);
	}
	
	public void close(Statement stmt){
		close(null,stmt,null);
	}
	
	public void close(Statement stmt,ResultSet rs){
		close(null,stmt,rs);
	}
	
	/**
	 * 关闭Connection/Statement/ResultSet
	 * @param conn
	 * @param stmt
	 * @param rs
	 */
	public void close(Connection conn,Statement stmt,ResultSet rs){
		try {
			if(rs!=null && !rs.isClosed()) rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			if(stmt!=null && !stmt.isClosed()) stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			if(conn!=null && !conn.isClosed()) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
