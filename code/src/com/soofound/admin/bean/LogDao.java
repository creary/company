/*  1:   */package com.soofound.admin.bean;
/*  2:   */
/*  3:   */import com.soofound.framework.jdbc.BaseDao;
/*  4:   */import com.soofound.framework.util.CommonUtil;
/*  5:   */import java.util.Map;
/*  6:   */import org.springframework.stereotype.Component;
/*  7:   */
/*  8:   */@Component
/*  9:   */public final class LogDao
/* 10:   */  extends BaseDao<LogDto>
/* 11:   */{
/* 12:   */  protected String getQuerySQL(Map<String, String> options)
/* 13:   */  {
/* 14:14 */    StringBuilder sqlText = new StringBuilder(100);
/* 15:15 */    sqlText.append("select * from view_admin_log where 1=1");
/* 16:16 */    if (!CommonUtil.isEmpty((String)options.get("branchId")))
/* 17:17 */      sqlText.append(" and branch_id like '").append((String)options.get("branchId")).append("%'");
/* 18:18 */    if (!CommonUtil.isEmpty((String)options.get("username")))
/* 19:19 */      sqlText.append(" and username like '%").append((String)options.get("username")).append("%'");
/* 20:20 */    if (!CommonUtil.isEmpty((String)options.get("fullname")))
/* 21:21 */      sqlText.append(" and fullname like '%").append((String)options.get("fullname")).append("%'");
/* 22:22 */    if (!CommonUtil.isEmpty((String)options.get("ip")))
/* 23:23 */      sqlText.append(" and ip like '%").append((String)options.get("ip")).append("%'");
/* 24:24 */    if (!CommonUtil.isEmpty((String)options.get("startLogTime")))
/* 25:25 */      sqlText.append(" and log_time >= '").append((String)options.get("startLogTime")).append("'");
/* 26:26 */    if (!CommonUtil.isEmpty((String)options.get("endLogTime")))
/* 27:27 */      sqlText.append(" and log_time <= '").append((String)options.get("endLogTime")).append("'");
/* 28:28 */    sqlText.append(" order by log_time desc");
/* 29:29 */    return sqlText.toString();
/* 30:   */  }
/* 31:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.admin.bean.LogDao
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */