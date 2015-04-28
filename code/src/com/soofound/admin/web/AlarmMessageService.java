/*  1:   */package com.soofound.admin.web;
/*  2:   */
/*  3:   */import com.alibaba.fastjson.JSON;
/*  4:   */import com.soofound.framework.util.CommonUtil;
/*  5:   */import com.soofound.framework.web.BaseService;
/*  6:   */import java.util.ArrayList;
/*  7:   */import java.util.HashMap;
/*  8:   */import org.springframework.beans.factory.annotation.Autowired;
/*  9:   */import org.springframework.beans.factory.annotation.Qualifier;
/* 10:   */import org.springframework.jdbc.core.JdbcTemplate;
/* 11:   */import org.springframework.stereotype.Service;
/* 12:   */
/* 13:   */@Service
/* 14:   */public final class AlarmMessageService extends BaseService<com.soofound.admin.bean.AlarmMessageDao>
/* 15:   */{
/* 16:   */  @Autowired
/* 17:   */  @Qualifier("defaultJdbcTemplate")
/* 18:   */  private JdbcTemplate jdbcTemplate;
/* 19:   */  
/* 20:   */  public java.util.List<com.soofound.admin.bean.AlarmMessage> getMessages(String username, String count, String start)
/* 21:   */  {
/* 22:22 */    return ((com.soofound.admin.bean.AlarmMessageDao)this.dao).getMessages(username, count, start);
/* 23:   */  }
/* 24:   */  
/* 25:   */  public int getMessageTotal() {
/* 26:26 */    return ((com.soofound.admin.bean.AlarmMessageDao)this.dao).getMessageTotal();
/* 27:   */  }
/* 28:   */  
/* 29:   */  public java.util.Map<String, Object> updateBatchAnnouncements(String username, String jsonData) {
/* 30:30 */    java.util.Map<String, Object> result = new HashMap();
/* 31:31 */    result.put("status", Integer.valueOf(1));
/* 32:32 */    StringBuilder idstr = new StringBuilder(100);
/* 33:   */    try {
/* 34:34 */      java.util.List<com.soofound.admin.bean.AlarmMessage> ams = JSON.parseArray(jsonData, com.soofound.admin.bean.AlarmMessage.class);
/* 35:35 */      java.util.List<String> sqls = new ArrayList();
/* 36:36 */      for (com.soofound.admin.bean.AlarmMessage am : ams) {
/* 37:37 */        String deleteSql = "delete from admin_announcement_read where aid=" + am.getId() + " and username='" + username + "'";
/* 38:38 */        sqls.add(deleteSql);
/* 39:   */      }
/* 40:40 */      this.jdbcTemplate.batchUpdate(CommonUtil.toArray(sqls));
/* 41:41 */      sqls.clear();
/* 42:42 */      for (com.soofound.admin.bean.AlarmMessage am : ams) {
/* 43:43 */        String addSql = "insert into admin_announcement_read(aid,username,log_time)values(" + am.getId() + ",'" + username + "',now())";
/* 44:44 */        sqls.add(addSql);
/* 45:45 */        idstr.append(",").append(am.getId());
/* 46:   */      }
/* 47:47 */      this.jdbcTemplate.batchUpdate(CommonUtil.toArray(sqls));
/* 48:   */    } catch (Exception e) {
/* 49:49 */      e.printStackTrace();
/* 50:50 */      result.put("status", Integer.valueOf(2));
/* 51:   */    }
/* 52:52 */    result.put("data", idstr.substring(1));
/* 53:53 */    return result;
/* 54:   */  }
/* 55:   */  
/* 56:   */  public int getUnreadMessageTotal(String username) {
/* 57:57 */    return ((com.soofound.admin.bean.AlarmMessageDao)this.dao).getUnreadMessageTotal(username);
/* 58:   */  }
/* 59:   */  
/* 62:   */  @Autowired
/* 63:   */  protected void setDao(com.soofound.admin.bean.AlarmMessageDao dao)
/* 64:   */  {
/* 65:65 */    this.dao = dao;
/* 66:   */  }
/* 67:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.admin.web.AlarmMessageService
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */