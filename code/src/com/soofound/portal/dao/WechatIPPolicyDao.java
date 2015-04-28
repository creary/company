/*  1:   */package com.soofound.portal.dao;
/*  2:   */
/*  3:   */import com.soofound.framework.jdbc.BaseDao;
/*  4:   */import com.soofound.portal.bean.WechatIPPolicyDto;
/*  5:   */import java.util.List;
/*  6:   */import org.springframework.jdbc.core.JdbcTemplate;
/*  7:   */import org.springframework.stereotype.Component;
/*  8:   */
/*  9:   */@Component
/* 10:   */public final class WechatIPPolicyDao extends BaseDao<WechatIPPolicyDto>
/* 11:   */{
/* 12:   */  public int save(WechatIPPolicyDto dto)
/* 13:   */  {
/* 14:14 */    StringBuilder sqlText = new StringBuilder(100);
/* 15:15 */    sqlText.append("insert into portal_wechat_ip_policy(id,branch_id,name,img_url,post_url,ip_address)values(");
/* 16:16 */    sqlText.append(getID("portal_wechat_ip_policy")).append(",'").append(dto.getBranchId()).append("','").append(dto.getName());
/* 17:17 */    sqlText.append("','").append(dto.getImgUrl()).append("','").append(dto.getPostUrl()).append("','").append(dto.getIpAddress()).append("')");
/* 18:18 */    int result = getJdbcTemplate().update(sqlText.toString());
/* 19:19 */    return result;
/* 20:   */  }
/* 21:   */  
/* 22:   */  public int update(WechatIPPolicyDto dto)
/* 23:   */  {
/* 24:24 */    StringBuilder sqlText = new StringBuilder(100);
/* 25:25 */    sqlText.append("update portal_wechat_ip_policy set name='").append(dto.getName()).append("',img_url='").append(dto.getImgUrl());
/* 26:26 */    sqlText.append("',post_url='").append(dto.getPostUrl()).append("',ip_address='").append(dto.getIpAddress());
/* 27:27 */    sqlText.append("' where id = ").append(dto.getId());
/* 28:28 */    int result = getJdbcTemplate().update(sqlText.toString());
/* 29:29 */    return result;
/* 30:   */  }
/* 31:   */  
/* 32:   */  public List<WechatIPPolicyDto> getWechatIPPolicies(String branchId) {
/* 33:33 */    String sql = "select * from portal_wechat_ip_policy where branch_id='" + branchId + "'";
/* 34:34 */    return findByCriteria(sql);
/* 35:   */  }
/* 36:   */  
/* 37:   */  public int deleteWechatIPPolicy(String id) {
/* 38:38 */    String sql = "delete from portal_wechat_ip_policy where id=" + id;
/* 39:39 */    return getJdbcTemplate().update(sql);
/* 40:   */  }
/* 41:   */  
/* 42:   */  public WechatIPPolicyDto findByID(String id)
/* 43:   */  {
/* 44:44 */    String sql = "select * from portal_wechat_ip_policy where id=" + id;
/* 45:45 */    return (WechatIPPolicyDto)findOne(sql);
/* 46:   */  }
/* 47:   */  
/* 48:   */  public WechatIPPolicyDto findByIP(String ip) {
/* 49:49 */    String sql = "select * from portal_wechat_ip_policy where ip_address='" + ip + "'";
/* 50:50 */    return (WechatIPPolicyDto)findOne(sql);
/* 51:   */  }
/* 52:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.dao.WechatIPPolicyDao
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */