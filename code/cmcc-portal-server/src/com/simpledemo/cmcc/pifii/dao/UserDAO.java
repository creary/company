package com.simpledemo.cmcc.pifii.dao;

import java.sql.SQLException;
import java.util.List;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.simpledemo.cmcc.pifii.util.DbPoolConnection;
import com.simpledemo.cmcc.util.DBUtilApache;
import com.simpledemo.cmcc.util.DateUtil;

public class UserDAO {
	static DbPoolConnection dbp = DbPoolConnection.getInstance();
	static 	DruidPooledConnection con;
	static {
		try {
			con = dbp.getConnection();
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
* @Title: updateNumber 
* @Description: TODO(这里用一句话描述这个方法的作用) 
* @param @param mac
* @param @param num
* @param @return
* @param @throws SQLException    设定文件 
* @return boolean    返回类型 
* @throws
 */
	public static void insertNumber(String mac) throws SQLException{
		
		int ids=new Long(System.currentTimeMillis()).intValue();
		String sql="insert into bp_device (id,mac,online_num,report_date) values ('"+ids+"','"+mac+"','1','"+DateUtil.getNow()+"')";
		DBUtilApache.update(con.getConnection() , sql, null);
	}
	/**
	 * 
	* @Title: updateNumber 
	* @Description: TODO(是否在线 :true --在线         false ---不在线) 
	* @param @param mac
	* @param @param num
	* @param @return
	* @param @throws SQLException    设定文件 
	* @return boolean    返回类型 
	* @throws
	 */
	public static boolean isThere(String mac) throws SQLException{
		boolean s=false;
		int ids=new Long(System.currentTimeMillis()).intValue();
		String sql="select online_num from bp_device where router_sn =? ";
		List<Object[]> list=	DBUtilApache.queryArrayList(con.getConnection() , sql, mac);
		if(list.size()>0){
			s=true;
		}
		return s;
	}
	/**
	 * 
	* @Title: updateIsonLine 
	* @Description: TODO(跟新在线人数为传入的值) 
	* @param @param mac
	* @param @param num
	* @param @return
	* @param @throws SQLException    设定文件 
	* @return boolean    返回类型 
	* @throws
	 */
	public static void updateIsonLine(String router_sn,int num) throws SQLException{
		int ids=new Long(System.currentTimeMillis()).intValue();
		String sql="update bp_device set  online_num =? ,report_date=? where mac=? ";
		DBUtilApache.update(con.getConnection() , sql, new Object[]{num,DateUtil.getNow(),router_sn});
	}
	/**
	 * 
	* @Title: selectIsonLine 
	* @Description: TODO(查询指定用户的在线人数) 
	* @param @param router_sn
	* @param @return
	* @param @throws SQLException    设定文件 
	* @return Object    返回类型 
	* @throws
	 */
	public static Object selectIsonLine(String router_sn) throws SQLException{
		String sql="select online_num from bp_device where router_sn =? ";
		Object[] ob=  DBUtilApache.queryArray(con.getConnection() , sql, router_sn);
		return ob[0];
	}
	
	
}
