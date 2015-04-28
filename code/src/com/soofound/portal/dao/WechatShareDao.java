/*  1:   */package com.soofound.portal.dao;
/*  2:   */
/*  3:   */import com.soofound.framework.jdbc.BaseDao;
/*  4:   */import com.soofound.portal.bean.WechatShareDto;
/*  5:   */import java.sql.ResultSet;
/*  6:   */import java.sql.SQLException;
/*  7:   */import java.util.ArrayList;
/*  8:   */import org.springframework.dao.DataAccessException;
/*  9:   */import org.springframework.dao.EmptyResultDataAccessException;
/* 10:   */import org.springframework.jdbc.core.JdbcTemplate;
/* 11:   */import org.springframework.jdbc.core.ResultSetExtractor;
/* 12:   */import org.springframework.stereotype.Component;
/* 13:   */
/* 14:   */@Component
/* 15:   */public final class WechatShareDao extends BaseDao<WechatShareDto>
/* 16:   */{
/* 17:   */  private static final String SHARE_ARTICLE = "SELECT a.id,a.share_id,a.portal_id,b.title,b.content,b.cover FROM portal_wechat_share a RIGHT JOIN surfing_advertise b ON a.share_id=b.id WHERE flag=1 ";
/* 18:   */  
/* 19:   */  public java.util.List<WechatShareDto> getShareByPortal(String pid)
/* 20:   */  {
/* 21:21 */    StringBuilder sqlText = new StringBuilder(100);
/* 22:22 */    sqlText.append("SELECT a.id,a.share_id,a.portal_id,b.title,b.content,b.cover FROM portal_wechat_share a RIGHT JOIN surfing_advertise b ON a.share_id=b.id WHERE flag=1 ").append(" AND portal_id=").append(pid);
/* 23:23 */    return findByCriteria(sqlText.toString());
/* 24:   */  }
/* 25:   */  
/* 26:   */  public java.util.List<WechatShareDto> getShareByBranch(String bid) {
/* 27:27 */    StringBuilder sqlText = new StringBuilder(100);
/* 28:28 */    sqlText.append("SELECT a.id,a.share_id,a.portal_id,b.title,b.content,b.cover FROM portal_wechat_share a RIGHT JOIN surfing_advertise b ON a.share_id=b.id WHERE flag=1 ").append(" and b.branch_id='").append(bid).append("'");
/* 29:29 */    return findByCriteria(sqlText.toString());
/* 30:   */  }
/* 31:   */  
/* 32:   */  public int addShareArticle(String pid, String aid) {
/* 33:33 */    StringBuilder sqlText = new StringBuilder(100);
/* 34:34 */    sqlText.append("insert into portal_wechat_share(id,portal_id,share_id,flag)values(").append(getID("portal_wechat_share"));
/* 35:35 */    sqlText.append(",").append(pid).append(",").append(aid).append(",1)");
/* 36:36 */    return saveOrUpdate(sqlText.toString());
/* 37:   */  }
/* 38:   */  
/* 39:   */  public int addShareMac(String sid, String mac) {
/* 40:40 */    StringBuilder sqlText = new StringBuilder(100);
/* 41:41 */    sqlText.append("insert into membership_share_mac(sid,mac,log_time)values(").append(sid).append(",'").append(mac).append("',now())");
/* 42:42 */    return saveOrUpdate(sqlText.toString());
/* 43:   */  }
/* 44:   */  
/* 45:   */  public boolean hasShared(String sid, String mac) {
/* 46:46 */    StringBuilder sqlText = new StringBuilder(200);
/* 47:47 */    sqlText.append("select count(*) from membership_share_mac where sid=").append(sid).append(" and mac='").append(mac).append("'");
/* 48:48 */    int c = 0;
/* 49:   */    try {
/* 50:50 */      c = ((Integer)getJdbcTemplate().queryForObject(sqlText.toString(), Integer.class)).intValue();
/* 51:   */    }
/* 52:   */    catch (EmptyResultDataAccessException localEmptyResultDataAccessException) {}
/* 53:53 */    return c > 0;
/* 54:   */  }
/* 55:   */  
/* 56:   */  public java.util.List<Integer> getShareIds(String mac) {
/* 57:57 */    String sql = "select sid from membership_share_mac where mac='" + mac + "'";
/* 58:58 */    java.util.List<Integer> sids = (java.util.List)super.getJdbcTemplate().query(sql, new ResultSetExtractor() {
/* 59:   */      public java.util.List<Integer> extractData(ResultSet rs) throws SQLException, DataAccessException {
/* 60:60 */        java.util.List<Integer> sids = new ArrayList();
/* 61:61 */        while (rs.next())
/* 62:62 */          sids.add(Integer.valueOf(rs.getInt(1)));
/* 63:63 */        return sids;
/* 64:64 */      } } );
/* 65:65 */    return sids;
/* 66:   */  }
/* 67:   */  
/* 68:   */  public int delete(String id) {
/* 69:69 */    String sql = "delete from portal_wechat_share where id=" + id;
/* 70:70 */    return saveOrUpdate(sql);
/* 71:   */  }
/* 72:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.dao.WechatShareDao
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */