/*  1:   */package com.soofound.acside.bean;
/*  2:   */
/*  3:   */import java.io.PrintStream;
/*  4:   */
/*  5:   */@org.springframework.stereotype.Component
/*  6:   */public class CpeCustomDao extends com.soofound.framework.jdbc.BaseDao<CpeCustomDto>
/*  7:   */{
/*  8:   */  public CpeCustomDao()
/*  9:   */  {
/* 10:10 */    super("acsideDS");
/* 11:   */  }
/* 12:   */  
/* 13:   */  public int getEnterSNR(String apmac) {
/* 14:14 */    String sql = "SELECT snr FROM ap_custom WHERE ap_mac='" + apmac + "'";
/* 15:   */    try {
/* 16:16 */      return ((Integer)getJdbcTemplate().queryForObject(sql, Integer.class)).intValue();
/* 17:   */    } catch (Exception ex) {
/* 18:18 */      System.out.println(ex.getMessage()); }
/* 19:19 */    return 60;
/* 20:   */  }
/* 21:   */  
/* 22:   */  public int updateSNR(String apmac, int snr)
/* 23:   */  {
/* 24:24 */    String sql = "update ap_custom set snr=" + snr + " where ap_mac='" + apmac + "'";
/* 25:25 */    int result = 0;
/* 26:   */    try {
/* 27:27 */      result = getJdbcTemplate().update(sql);
/* 28:28 */      if (result == 0) {
/* 29:29 */        sql = "insert into ap_custom(ap_mac,alias,snr)values('" + apmac + "','" + apmac + "'," + snr + ")";
/* 30:30 */        result = getJdbcTemplate().update(sql);
/* 31:   */      }
/* 32:   */    } catch (Exception ex) {
/* 33:33 */      ex.printStackTrace();
/* 34:   */    }
/* 35:35 */    return result;
/* 36:   */  }
/* 37:   */  
/* 38:   */  public int updateAlias(String apmac, String alias) {
/* 39:39 */    String sql = "update ap_custom set alias='" + alias + "' where ap_mac='" + apmac + "'";
/* 40:40 */    int result = 0;
/* 41:   */    try {
/* 42:42 */      result = getJdbcTemplate().update(sql);
/* 43:43 */      if (result == 0) {
/* 44:44 */        sql = "insert into ap_custom(ap_mac,alias)values('" + apmac + "','" + alias + "')";
/* 45:45 */        result = getJdbcTemplate().update(sql);
/* 46:   */      }
/* 47:   */    } catch (Exception ex) {
/* 48:48 */      ex.printStackTrace();
/* 49:   */    }
/* 50:50 */    return result;
/* 51:   */  }
/* 52:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.acside.bean.CpeCustomDao
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */