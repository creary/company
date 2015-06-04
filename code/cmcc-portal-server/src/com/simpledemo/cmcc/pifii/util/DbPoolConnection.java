package com.simpledemo.cmcc.pifii.util;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
 




import org.apache.log4j.Logger;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.simpledemo.cmcc.util.LogUtil;
 
public class DbPoolConnection {
    private static DbPoolConnection databasePool = null;
    private static DruidDataSource dds = null;
    private final static Logger log = Logger.getLogger(DbPoolConnection.class);
    static {
        Properties properties = loadPropertyFile("/DB.properties");
        try {
            dds = (DruidDataSource) DruidDataSourceFactory
                    .createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private DbPoolConnection() {}
    public static synchronized DbPoolConnection getInstance() {
        if (null == databasePool) {
            databasePool = new DbPoolConnection();
        }
        return databasePool;
    }
    public DruidPooledConnection getConnection() throws SQLException {
        return dds.getConnection();
    }
    public static Properties loadPropertyFile(String fullFile) {
    	Properties p=new Properties();
    		try {
				p.load(DbPoolConnection.class.getResourceAsStream(fullFile));
			} catch (IOException e) {
				log.error("加载DB.properties异常："+e);
				e.printStackTrace();
			}
    		return p;
    	/*
        String webRootPath = null;
        if (null == fullFile || fullFile.equals(""))
            throw new IllegalArgumentException(
                    "Properties file path can not be null : " + fullFile);
        webRootPath = DbPoolConnection.class.getClassLoader().getResource("")
                .getPath();
        webRootPath = new File(webRootPath).getParent();
        InputStream inputStream = null;
        Properties p = null;
        try {
            inputStream = new FileInputStream(new File(webRootPath
                    + File.separator + fullFile));
            p = new Properties();
            p.load(inputStream);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Properties file not found: "
                    + fullFile);
        } catch (IOException e) {
            throw new IllegalArgumentException(
                    "Properties file can not be loading: " + fullFile);
        } finally {
            try {
                if (inputStream != null)
                    inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return p;
    */
    		
    		}
}