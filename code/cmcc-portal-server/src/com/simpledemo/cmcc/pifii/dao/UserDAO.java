package com.simpledemo.cmcc.pifii.dao;

import java.sql.SQLException;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.mysql.jdbc.Connection;
import com.simpledemo.cmcc.pifii.util.DbPoolConnection;

public class UserDAO {
	static {
		DbPoolConnection dbp = DbPoolConnection.getInstance();
		DruidPooledConnection con;
		try {
			con = dbp.getConnection();
			System.out.println(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public UserDAO() {
		
	}
	/**
	* @Title: isOnline 
	* @Description: TODO(当前用户是否在线) 
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @throws
	 */
	public static boolean isOnline(){
		
		return false;
	}
	/**
	 * 
	* @Title: isOnline 
	* @Description: TODO(根据用户名判断是否在线) 
	* @param @param username
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @throws
	 */
	public static boolean isOnline(String username){
		return false;
	}
	
}
