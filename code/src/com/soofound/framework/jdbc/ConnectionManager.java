/*  1:   */package com.soofound.framework.jdbc;
/*  2:   */
/*  3:   */import com.soofound.framework.util.SysConfigHelper;
/*  4:   */import java.sql.Connection;
/*  5:   */import javax.sql.DataSource;
/*  6:   */import org.springframework.dao.DataAccessResourceFailureException;
/*  7:   */
/* 16:   */public final class ConnectionManager
/* 17:   */{
/* 18:   */  public static final String DEFAULT_DS = "defaultDS";
/* 19:   */  
/* 20:   */  public static Connection getConnection()
/* 21:   */  {
/* 22:22 */    return getConnection("defaultDS");
/* 23:   */  }
/* 24:   */  
/* 27:   */  public static Connection getConnection(String dsName)
/* 28:   */  {
/* 29:   */    try
/* 30:   */    {
/* 31:31 */      DataSource ds = (DataSource)SysConfigHelper.getBean(dsName);
/* 32:32 */      return ds.getConnection();
/* 33:   */    } catch (Exception e) {
/* 34:34 */      throw new DataAccessResourceFailureException("Can not get BasicDataSource:" + dsName);
/* 35:   */    }
/* 36:   */  }
/* 37:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.framework.jdbc.ConnectionManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */