/*  1:   */package com.soofound.acside.bean;
/*  2:   */
/*  3:   */import java.io.PrintStream;
/*  4:   */import java.util.List;
/*  5:   */import org.springframework.jdbc.core.JdbcTemplate;
/*  6:   */import org.springframework.stereotype.Component;
/*  7:   */
/*  8:   */@Component
/*  9:   */public class TraceFlowDao extends com.soofound.framework.jdbc.BaseDao<TraceFlowDto>
/* 10:   */{
/* 11:   */  private static final String SQL_TABLE_NAME = "SELECT COUNT(*) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA='acside' AND TABLE_NAME='";
/* 12:   */  
/* 13:   */  public TraceFlowDao()
/* 14:   */  {
/* 15:15 */    super("acsideDS");
/* 16:   */  }
/* 17:   */  
/* 18:   */  protected String getQuerySQL(java.util.Map<String, String> options)
/* 19:   */  {
/* 20:20 */    StringBuffer sqlText = new StringBuffer(100);
/* 21:21 */    sqlText.append("select * from ").append((String)options.get("tableName"));
/* 22:22 */    sqlText.append(" where log_time >= '").append((String)options.get("startTime"));
/* 23:23 */    sqlText.append("' and log_time <= '").append((String)options.get("endTime"));
/* 24:24 */    sqlText.append("' and mac='").append((String)options.get("mac")).append("'");
/* 25:25 */    if (options.get("url") != null)
/* 26:26 */      sqlText.append(" and url like '%").append((String)options.get("url")).append("%'");
/* 27:27 */    sqlText.append(" order by log_time desc");
/* 28:28 */    System.out.println(sqlText.toString());
/* 29:29 */    return sqlText.toString();
/* 30:   */  }
/* 31:   */  
/* 32:   */  public List<TraceFlowDto> getTraceFlow(java.util.Map<String, String> options) {
/* 33:33 */    return super.findByCriteria(getQuerySQL(options));
/* 34:   */  }
/* 35:   */  
/* 36:   */  public boolean hasTable(String tableName) {
/* 37:37 */    Integer id = (Integer)super.getJdbcTemplate().queryForObject("SELECT COUNT(*) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA='acside' AND TABLE_NAME='" + tableName + "'", Integer.class);
/* 38:38 */    return id.intValue() > 0;
/* 39:   */  }
/* 40:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.acside.bean.TraceFlowDao
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */