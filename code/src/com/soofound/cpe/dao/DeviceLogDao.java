/*  1:   */package com.soofound.cpe.dao;
/*  2:   */
/*  3:   */import com.soofound.cpe.bean.DeviceLogBean;
/*  4:   */import com.soofound.framework.jdbc.BaseDao;
/*  5:   */import com.soofound.framework.util.CommonUtil;
/*  6:   */import com.soofound.framework.util.DateUtil;
/*  7:   */import java.util.Map;
/*  8:   */import org.springframework.jdbc.core.JdbcTemplate;
/*  9:   */import org.springframework.stereotype.Component;
/* 10:   */
/* 11:   */@Component
/* 12:   */public final class DeviceLogDao extends BaseDao<DeviceLogBean>
/* 13:   */{
/* 14:   */  protected String getQuerySQL(Map<String, String> options)
/* 15:   */  {
/* 16:16 */    StringBuilder sqlText = new StringBuilder(100);
/* 17:17 */    sqlText.append("select * from view_device_log where 1=1 ");
/* 18:18 */    if (!CommonUtil.isEmpty((String)options.get("branchId")))
/* 19:19 */      sqlText.append(" and branch_id like '").append((String)options.get("branchId")).append("%'");
/* 20:20 */    if (!CommonUtil.isEmpty((String)options.get("name")))
/* 21:21 */      sqlText.append(" and name like '%").append((String)options.get("name")).append("%'");
/* 22:22 */    if (!CommonUtil.isEmpty((String)options.get("mac")))
/* 23:23 */      sqlText.append(" and serial_number like '%").append((String)options.get("mac")).append("%'");
/* 24:24 */    if (!CommonUtil.isEmpty((String)options.get("message")))
/* 25:25 */      sqlText.append(" and message like '%").append((String)options.get("message")).append("%'");
/* 26:26 */    if (!CommonUtil.isEmpty((String)options.get("branch")))
/* 27:27 */      sqlText.append(" and branch like '%").append((String)options.get("branch")).append("%'");
/* 28:28 */    sqlText.append(" order by log_time desc");
/* 29:29 */    return sqlText.toString();
/* 30:   */  }
/* 31:   */  
/* 32:   */  public DeviceLogBean findByID(String id)
/* 33:   */  {
/* 34:34 */    String sql = "select * from view_device_log where id='" + id + "'";
/* 35:35 */    return (DeviceLogBean)super.findOne(sql);
/* 36:   */  }
/* 37:   */  
/* 38:   */  public DeviceLogBean findLastLog(int hostId, String log) {
/* 39:39 */    StringBuilder sqlText = new StringBuilder(100);
/* 40:40 */    sqlText.append("select * from view_device_log where host_id=").append(hostId);
/* 41:41 */    sqlText.append(" and message='").append(log).append("' and log_time>DATE_ADD(NOW(),INTERVAL-5 minute)");
/* 42:42 */    return (DeviceLogBean)super.findOne(sqlText.toString());
/* 43:   */  }
/* 44:   */  
/* 45:   */  public void writeLog(int hostId, String operator, String message) {
/* 46:46 */    StringBuffer sqlText = new StringBuffer(200);
/* 47:47 */    sqlText.append("insert into cpe_device_log(id,host_id,operator,message,log_time)values('").append(DateUtil.getCurrentTimeAsID());
/* 48:48 */    sqlText.append(hostId).append("','").append(hostId).append("','").append(operator).append("','").append(message).append("',now())");
/* 49:49 */    super.getJdbcTemplate().update(sqlText.toString());
/* 50:   */  }
/* 51:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.cpe.dao.DeviceLogDao
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */