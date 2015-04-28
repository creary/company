/*  1:   */package com.soofound.cpe.web;
/*  2:   */
/*  3:   */import com.soofound.framework.jdbc.DBUtil;
/*  4:   */import com.soofound.framework.util.CommonUtil;
/*  5:   */import com.soofound.framework.web.BaseService;
/*  6:   */import java.util.ArrayList;
/*  7:   */import org.springframework.beans.factory.annotation.Qualifier;
/*  8:   */import org.springframework.jdbc.core.JdbcTemplate;
/*  9:   */import org.springframework.stereotype.Service;
/* 10:   */
/* 11:   */@Service
/* 12:   */public final class HostPropertyService extends BaseService<com.soofound.cpe.dao.HostPropertyDao>
/* 13:   */{
/* 14:   */  @org.springframework.beans.factory.annotation.Autowired
/* 15:   */  @Qualifier("defaultJdbcTemplate")
/* 16:   */  private JdbcTemplate jdbcTemplate;
/* 17:   */  
/* 18:   */  public java.util.List<com.soofound.cpe.bean.HostPropertyBean> findByHost(String hostId)
/* 19:   */  {
/* 20:20 */    return ((com.soofound.cpe.dao.HostPropertyDao)this.dao).findByHost(hostId);
/* 21:   */  }
/* 22:   */  
/* 23:   */  public java.util.List<com.soofound.cpe.bean.HostPropertyBean> findByHostSsids(String hostId) {
/* 24:24 */    return ((com.soofound.cpe.dao.HostPropertyDao)this.dao).findByHostSsids(hostId);
/* 25:   */  }
/* 26:   */  
/* 27:   */  public com.soofound.cpe.bean.HostPropertyBean getPropertyByName(String hostId, String name) {
/* 28:28 */    return ((com.soofound.cpe.dao.HostPropertyDao)this.dao).getPropertyByName(hostId, name);
/* 29:   */  }
/* 30:   */  
/* 31:   */  public com.soofound.cpe.bean.HostPropertyBean getPropertyByID(String hostId, String propId) {
/* 32:32 */    return ((com.soofound.cpe.dao.HostPropertyDao)this.dao).getPropertyByID(hostId, propId);
/* 33:   */  }
/* 34:   */  
/* 35:   */  public void turnOnTrace(String hostId, String trace) {
/* 36:36 */    java.util.List<String> sqls = new ArrayList();
/* 37:37 */    StringBuilder sqlText1 = new StringBuilder(100);
/* 38:38 */    sqlText1.append("update cpe_host set trace='").append(trace).append("' where id=").append(hostId);
/* 39:39 */    sqls.add(sqlText1.toString());
/* 40:40 */    if ((!trace.equals("off")) && (!trace.equals("ad"))) {
/* 41:41 */      StringBuilder sqlText2 = new StringBuilder(100);
/* 42:42 */      sqlText2.append("update cpe_host set detect='").append(trace).append("' where id=").append(hostId);
/* 43:43 */      sqlText2.append(" and detect not in ('off','')");
/* 44:44 */      sqls.add(sqlText2.toString());
/* 45:   */    }
/* 46:46 */    DBUtil.executeBatch(sqls);
/* 47:   */  }
/* 48:   */  
/* 49:   */  public void turnOnDetect(String hostId, String detect) {
/* 50:50 */    java.util.List<String> sqls = new ArrayList();
/* 51:51 */    StringBuilder sqlText1 = new StringBuilder(100);
/* 52:52 */    sqlText1.append("update cpe_host set detect='").append(detect).append("' where id=").append(hostId);
/* 53:53 */    sqls.add(sqlText1.toString());
/* 54:   */    
/* 55:55 */    if (!detect.equals("off")) {
/* 56:56 */      StringBuilder sqlText2 = new StringBuilder(100);
/* 57:57 */      sqlText2.append("update cpe_host set trace='").append(detect).append("' where id=").append(hostId);
/* 58:58 */      sqlText2.append(" and trace not in ('off','ad','')");
/* 59:59 */      sqls.add(sqlText1.toString());
/* 60:   */    }
/* 61:61 */    this.jdbcTemplate.batchUpdate(CommonUtil.toArray(sqls));
/* 62:   */  }
/* 63:   */  
/* 64:   */  public void turnOnQos(String hostId, int pid, String[] qoss) {
/* 65:65 */    ((com.soofound.cpe.dao.HostPropertyDao)this.dao).updateQos(hostId, pid, qoss);
/* 66:   */  }
/* 67:   */  
/* 68:   */  public java.util.List<com.soofound.cpe.bean.HostPropertyBean> getQos(String hostId) {
/* 69:69 */    return ((com.soofound.cpe.dao.HostPropertyDao)this.dao).getQos(hostId);
/* 70:   */  }
/* 71:   */  
/* 74:   */  @org.springframework.beans.factory.annotation.Autowired
/* 75:   */  protected void setDao(com.soofound.cpe.dao.HostPropertyDao dao)
/* 76:   */  {
/* 77:77 */    this.dao = dao;
/* 78:   */  }
/* 79:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.cpe.web.HostPropertyService
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */