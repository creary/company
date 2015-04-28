/*  1:   */package com.soofound.portal.dao;
/*  2:   */
/*  3:   */import com.soofound.framework.jdbc.BaseDao;
/*  4:   */import org.springframework.jdbc.core.JdbcTemplate;
/*  5:   */import org.springframework.stereotype.Component;
/*  6:   */
/*  7:   */@Component
/*  8:   */public final class WechatGuideDao extends BaseDao<com.soofound.portal.bean.WechatGuideDto>
/*  9:   */{
/* 10:   */  private static final String SQL_UPDATE_WECHAT_GUIDE = "update portal_wechat_guide set page_value=?,flag=? where branch_id=?";
/* 11:   */  private static final String SQL_INSERT_WECHAT_GUIDE = "insert into portal_wechat_guide(branch_id,flag,page_value)values(?,?,?)";
/* 12:   */  private static final String SQL_WECHAT_RESPONSE = "select page_value from portal_wechat_response where branch_id=?";
/* 13:   */  private static final String SQL_UPDATE_WECHAT_RESPONSE = "update portal_wechat_response set page_value=? where branch_id=?";
/* 14:   */  private static final String SQL_INSERT_WECHAT_RESPONSE = "insert into portal_wechat_response(page_value,branch_id)values(?,?)";
/* 15:   */  
/* 16:   */  public com.soofound.portal.bean.WechatGuideDto findByID(String branchId)
/* 17:   */  {
/* 18:18 */    String sql = "select * from portal_wechat_guide where branch_id='" + branchId + "'";
/* 19:19 */    return (com.soofound.portal.bean.WechatGuideDto)super.findOne(sql);
/* 20:   */  }
/* 21:   */  
/* 22:   */  public int save(com.soofound.portal.bean.WechatGuideDto dto)
/* 23:   */  {
/* 24:24 */    int result = getJdbcTemplate().update("update portal_wechat_guide set page_value=?,flag=? where branch_id=?", new Object[] { dto.getPageValue(), dto.getFlag(), dto.getBranchId() });
/* 25:25 */    if (result == 0)
/* 26:26 */      result = getJdbcTemplate().update("insert into portal_wechat_guide(branch_id,flag,page_value)values(?,?,?)", new Object[] { dto.getBranchId(), dto.getFlag(), dto.getPageValue() });
/* 27:27 */    return result;
/* 28:   */  }
/* 29:   */  
/* 30:   */  public String getWechatResponse(String branchId) {
/* 31:   */    try {
/* 32:32 */      return (String)super.getJdbcTemplate().queryForObject("select page_value from portal_wechat_response where branch_id=?", new String[] { branchId }, String.class);
/* 33:   */    } catch (Exception e) {}
/* 34:34 */    return null;
/* 35:   */  }
/* 36:   */  
/* 37:   */  public int updateWechatResponse(String branchId, String content)
/* 38:   */  {
/* 39:39 */    int result = super.getJdbcTemplate().update("update portal_wechat_response set page_value=? where branch_id=?", new Object[] { content, branchId });
/* 40:40 */    if (result == 0)
/* 41:41 */      result = super.getJdbcTemplate().update("insert into portal_wechat_response(page_value,branch_id)values(?,?)", new Object[] { content, branchId });
/* 42:42 */    return result;
/* 43:   */  }
/* 44:   */  
/* 45:   */  public int updateWechatIPPolicy(String branchId, String name, String imgUrl, String postUrl, String ipAddress) {
/* 46:46 */    StringBuilder sqlText = new StringBuilder(100);
/* 47:47 */    sqlText.append("insert into portal_wechat_ip_response(branch_id,name,img_url,post_url,ip_address)values('");
/* 48:48 */    sqlText.append(branchId).append("','").append(name).append("','").append(imgUrl).append("','").append(postUrl);
/* 49:49 */    sqlText.append("','").append(ipAddress).append("')");
/* 50:50 */    int result = getJdbcTemplate().update(sqlText.toString());
/* 51:51 */    return result;
/* 52:   */  }
/* 53:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.dao.WechatGuideDao
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */