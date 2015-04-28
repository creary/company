/*  1:   */package com.soofound.project.supoin;
/*  2:   */
/*  3:   */import com.soofound.admin.bean.BranchDto;
/*  4:   */import com.soofound.admin.web.BranchService;
/*  5:   */import com.soofound.cpe.bean.HostBean;
/*  6:   */import com.soofound.cpe.web.HostService;
/*  7:   */import com.soofound.framework.util.SysConfigHelper;
/*  8:   */import com.soofound.portal.service.SurfingSessionStore;
/*  9:   */import org.springframework.jdbc.core.JdbcTemplate;
/* 10:   */
/* 11:   */public class SupoinSurfingSession implements SurfingSessionStore
/* 12:   */{
/* 13:   */  private JdbcTemplate jdbc;
/* 14:   */  private BranchService bcSvc;
/* 15:   */  private HostService hostSvc;
/* 16:   */  
/* 17:   */  public SupoinSurfingSession()
/* 18:   */  {
/* 19:19 */    this.jdbc = ((JdbcTemplate)SysConfigHelper.getBean("oracleJdbcTemplate"));
/* 20:20 */    this.bcSvc = ((BranchService)SysConfigHelper.getBean("branchService"));
/* 21:21 */    this.hostSvc = ((HostService)SysConfigHelper.getBean("hostService"));
/* 22:   */  }
/* 23:   */  
/* 24:   */  public void recordOnline(com.soofound.portal.bean.SurfingUser user)
/* 25:   */  {
/* 26:   */    try {
/* 27:27 */      BranchDto bcd = this.bcSvc.getBranch(user.getBranchId());
/* 28:28 */      HostBean hb = (HostBean)this.hostSvc.findByID(user.getCpeId());
/* 29:29 */      StringBuilder sqlText = new StringBuilder(200);
/* 30:30 */      sqlText.append("insert into surfing_session(session_id,username,open_id,ip_address,mac,ap_mac,ap_name,merchant_id,merchant,start_time)values('");
/* 31:31 */      sqlText.append(user.getSessionId()).append("','").append(user.getUsername()).append("','").append(user.getOpenId() == null ? "" : user.getOpenId());
/* 32:32 */      sqlText.append("','").append(user.getTerminalIP()).append("','").append(user.getTerminalMac()).append("','").append(hb.getSerialNumber()).append("','");
/* 33:33 */      sqlText.append(hb == null ? "" : hb.getName()).append("','").append(user.getBranchId()).append("','");
/* 34:34 */      sqlText.append(bcd == null ? "" : bcd.getName()).append("',sysdate)");
/* 35:35 */      this.jdbc.update(sqlText.toString());
/* 36:   */    } catch (Exception ex) {
/* 37:37 */      ex.printStackTrace();
/* 38:   */    }
/* 39:   */  }
/* 40:   */  
/* 41:   */  public void recordTraffic(com.soofound.portal.bean.SurfingUser user)
/* 42:   */  {
/* 43:   */    try {
/* 44:44 */      StringBuilder sqlText = new StringBuilder(200);
/* 45:45 */      sqlText.append("update surfing_session set session_time=TO_NUMBER(sysdate - start_time) * 86400");
/* 46:46 */      sqlText.append(",input_octets=").append(user.getInputOctets()).append(",output_octets=");
/* 47:47 */      sqlText.append(user.getOutputOctets()).append(" where session_id='").append(user.getSessionId()).append("'");
/* 48:48 */      this.jdbc.update(sqlText.toString());
/* 49:   */    } catch (Exception ex) {
/* 50:50 */      ex.printStackTrace();
/* 51:   */    }
/* 52:   */  }
/* 53:   */  
/* 54:   */  public void recordOffline(com.soofound.portal.bean.SurfingUser user)
/* 55:   */  {
/* 56:   */    try {
/* 57:57 */      StringBuilder sqlText = new StringBuilder(200);
/* 58:58 */      sqlText.append("update surfing_session set stop_time=sysdate,session_time=TO_NUMBER(sysdate - start_time) * 86400");
/* 59:59 */      sqlText.append(",input_octets=").append(user.getInputOctets()).append(",output_octets=");
/* 60:60 */      sqlText.append(user.getOutputOctets()).append(" where session_id='").append(user.getSessionId()).append("'");
/* 61:61 */      this.jdbc.update(sqlText.toString());
/* 62:   */    } catch (Exception ex) {
/* 63:63 */      ex.printStackTrace();
/* 64:   */    }
/* 65:   */  }
/* 66:   */  
/* 67:   */  public void recordRoaming(com.soofound.portal.bean.SurfingUser user)
/* 68:   */  {
/* 69:69 */    StringBuilder sqlText = new StringBuilder(200);
/* 70:70 */    sqlText.append("update surfing_session set session_time=TO_NUMBER(sysdate - start_time) * 86400");
/* 71:71 */    sqlText.append(",stop_time = sysdate,input_octets=").append(user.getInputOctets());
/* 72:72 */    sqlText.append(",output_octets=").append(user.getOutputOctets());
/* 73:73 */    sqlText.append(" where session_id='").append(user.getSessionId()).append("'");
/* 74:74 */    this.jdbc.update(sqlText.toString());
/* 75:   */  }
/* 76:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.project.supoin.SupoinSurfingSession
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */