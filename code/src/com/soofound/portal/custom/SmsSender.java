/*  1:   */package com.soofound.portal.custom;
/*  2:   */
/*  3:   */import com.soofound.framework.util.SysConfigHelper;
/*  4:   */import com.soofound.portal.util.WifiDogUtils;
/*  5:   */
/*  6:   */public abstract class SmsSender
/*  7:   */{
/*  8:   */  public void doRecord(String branchId, String mobile, String content)
/*  9:   */  {
/* 10:10 */    org.springframework.jdbc.core.JdbcTemplate jdbc = (org.springframework.jdbc.core.JdbcTemplate)SysConfigHelper.getBean("defaultJdbcTemplate");
/* 11:11 */    String[] phones = mobile.split(",");
/* 12:12 */    StringBuilder sqlText = new StringBuilder(100);
/* 13:13 */    sqlText.append("update surfing_sms_stat set sms_used = sms_used + ").append(phones.length);
/* 14:14 */    sqlText.append(" where branch_id='").append(branchId).append("'");
/* 15:15 */    jdbc.update(sqlText.toString());
/* 16:16 */    for (String phone : phones) {
/* 17:17 */      StringBuilder sqlText2 = new StringBuilder(200);
/* 18:18 */      sqlText2.append("insert into surfing_sms(id,branch_id,mobile,content,log_time)values('").append(WifiDogUtils.getMyUUID());
/* 19:19 */      sqlText2.append("','").append(branchId).append("','").append(phone).append("','").append(content).append("',now())");
/* 20:20 */      jdbc.update(sqlText2.toString());
/* 21:   */    }
/* 22:   */  }
/* 23:   */  
/* 24:   */  public abstract boolean sendSMS(String paramString1, String paramString2, String paramString3);
/* 25:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.custom.SmsSender
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */