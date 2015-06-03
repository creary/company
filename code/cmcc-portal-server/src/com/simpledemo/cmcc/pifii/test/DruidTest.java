package com.simpledemo.cmcc.pifii.test;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.simpledemo.cmcc.pifii.util.DbPoolConnection;

public class DruidTest {
	static final Logger log=Logger.getLogger(DruidTest.class);
public static void main(String[] args) {
	DbPoolConnection dbp = DbPoolConnection.getInstance();
    DruidPooledConnection con;
	try {
		con = dbp.getConnection();
		System.out.println(con);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
}


}
