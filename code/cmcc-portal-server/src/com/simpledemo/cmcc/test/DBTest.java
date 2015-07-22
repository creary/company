package com.simpledemo.cmcc.test;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import com.simpledemo.cmcc.pifii.util.DbPoolConnection;
import com.simpledemo.cmcc.util.DBUtilApache;
import com.simpledemo.cmcc.util.DateUtil;

public class DBTest {
	static  DbPoolConnection conn=DbPoolConnection.getInstance();
	
	//数据库连接操作测试
	/**
	 * 
	* @Title: testInsert 
	* @Description: TODO(数据库插入：apache dbutil的使用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws
	 */
	@Test
	public void testInsert(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.format(new Date());
		int ids=new Long(System.currentTimeMillis()).intValue();
		String sql="insert into bp_device (id,mac,online_num,report_date) values ('"+ids+"','"+ids+"','1','"+sdf.format(new Date())+"')";
		try {
			DBUtilApache.update(conn.getConnection(), sql, null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	* @Title: testSelect 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws	
	 */
	@Test
	public void testSelect(){
		String sql="select online_num from bp_device where router_sn =? ";
		try {
			Object[] ob=  DBUtilApache.queryArray(conn.getConnection() , sql, "015357ff59fcbfbc");
			System.out.println(ob[0]);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
	
	@Test
	public void testUPdate(){
		int ids=new Long(System.currentTimeMillis()).intValue();
		String sql="update bp_device set  online_num =? ,report_date=? where router_sn=? ";
		try {
			DBUtilApache.update(conn.getConnection() , sql, new Object[]{12,DateUtil.getNow(),"015357ff59fcbfbc"});
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	* @Title: testDruid 
	* @Description: TODO(研究测试druid数据库连接池性能) 
	* @param     设定文件  junit测试
	* @return void    返回类型  void 类型
	* @throws Exception
	 */
	@Test
	public void testDruid(){
		
				
	}
	
}
