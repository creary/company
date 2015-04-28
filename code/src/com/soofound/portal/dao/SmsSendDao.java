/*  1:   */package com.soofound.portal.dao;
/*  2:   */
/*  3:   */import com.soofound.framework.jdbc.BaseDao;
/*  4:   */import com.soofound.framework.util.CommonUtil;
/*  5:   */import com.soofound.portal.bean.SmsSendDto;
/*  6:   */import java.util.Map;
/*  7:   */import org.springframework.stereotype.Component;
/*  8:   */
/*  9:   */@Component
/* 10:   */public final class SmsSendDao
/* 11:   */  extends BaseDao<SmsSendDto>
/* 12:   */{
/* 13:   */  protected String getQuerySQL(Map<String, String> options)
/* 14:   */  {
/* 15:15 */    StringBuffer sqlText = new StringBuffer(100);
/* 16:16 */    sqlText.append("select * from view_surfing_sms where 1=1 ");
/* 17:17 */    if (!CommonUtil.isEmpty((String)options.get("branchId")))
/* 18:18 */      sqlText.append(" and branch_id like '").append((String)options.get("branchId")).append("%'");
/* 19:19 */    if (!CommonUtil.isEmpty((String)options.get("mobile")))
/* 20:20 */      sqlText.append(" and mobile = '").append((String)options.get("mobile")).append("'");
/* 21:21 */    if (!CommonUtil.isEmpty((String)options.get("content")))
/* 22:22 */      sqlText.append(" and content like '%").append((String)options.get("content")).append("%'");
/* 23:23 */    sqlText.append(" order by log_time desc");
/* 24:24 */    return sqlText.toString();
/* 25:   */  }
/* 26:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.dao.SmsSendDao
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */