/*  1:   */package com.soofound.admin.bean;
/*  2:   */
/*  3:   */import com.soofound.framework.jdbc.BaseDao;
/*  4:   */import com.soofound.framework.util.CommonUtil;
/*  5:   */import java.util.List;
/*  6:   */import java.util.Map;
/*  7:   */import org.springframework.dao.EmptyResultDataAccessException;
/*  8:   */import org.springframework.jdbc.core.JdbcTemplate;
/*  9:   */import org.springframework.stereotype.Component;
/* 10:   */
/* 11:   */@Component
/* 12:   */public final class AlarmMessageDao extends BaseDao<AlarmMessage>
/* 13:   */{
/* 14:   */  private static final String SQL_TOTAL = "SELECT COUNT(*) FROM admin_announcement";
/* 15:   */  private static final String LIST_SQL = "select a.*,CASE WHEN username IS NULL THEN 0 ELSE 1 END AS flag from admin_announcement a left join admin_announcement_read b on a.id=b.aid and b.username='";
/* 16:   */  
/* 17:   */  protected String getQuerySQL(Map<String, String> options)
/* 18:   */  {
/* 19:19 */    StringBuffer sqlText = new StringBuffer(100);
/* 20:20 */    sqlText.append("SELECT * FROM admin_announcement where 1 = 1 ");
/* 21:21 */    if (!CommonUtil.isEmpty((String)options.get("title")))
/* 22:22 */      sqlText.append(" and title like '%").append((String)options.get("title")).append("%'");
/* 23:23 */    if (!CommonUtil.isEmpty((String)options.get("content")))
/* 24:24 */      sqlText.append(" and content like '%").append((String)options.get("content")).append("%'");
/* 25:25 */    sqlText.append(" order by create_time desc");
/* 26:26 */    return sqlText.toString();
/* 27:   */  }
/* 28:   */  
/* 29:   */  public List<AlarmMessage> getMessages(String username, String count, String start) {
/* 30:30 */    StringBuilder sqlText = new StringBuilder(200);
/* 31:31 */    sqlText.append("select a.*,CASE WHEN username IS NULL THEN 0 ELSE 1 END AS flag from admin_announcement a left join admin_announcement_read b on a.id=b.aid and b.username='").append(username).append("' order by create_time desc");
/* 32:32 */    sqlText.append(" limit ").append(start).append(",").append(count);
/* 33:33 */    return super.findByCriteria(sqlText.toString());
/* 34:   */  }
/* 35:   */  
/* 36:   */  public int getMessageTotal() {
/* 37:   */    try {
/* 38:38 */      return ((Integer)getJdbcTemplate().queryForObject("SELECT COUNT(*) FROM admin_announcement", Integer.class)).intValue();
/* 39:   */    } catch (EmptyResultDataAccessException ex) {}
/* 40:40 */    return 0;
/* 41:   */  }
/* 42:   */  
/* 43:   */  public int getUnreadMessageTotal(String username)
/* 44:   */  {
/* 45:45 */    StringBuilder sqlText = new StringBuilder(200);
/* 46:46 */    sqlText.append("select count(*) from (").append("select a.*,CASE WHEN username IS NULL THEN 0 ELSE 1 END AS flag from admin_announcement a left join admin_announcement_read b on a.id=b.aid and b.username='").append(username).append("') t WHERE flag=0");
/* 47:   */    try {
/* 48:48 */      return ((Integer)getJdbcTemplate().queryForObject(sqlText.toString(), Integer.class)).intValue();
/* 49:   */    } catch (EmptyResultDataAccessException ex) {}
/* 50:50 */    return 0;
/* 51:   */  }
/* 52:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.admin.bean.AlarmMessageDao
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */