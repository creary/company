/*  1:   */package com.soofound.portal.dao;
/*  2:   */
/*  3:   */import com.soofound.framework.jdbc.BaseDao;
/*  4:   */import com.soofound.framework.util.CommonUtil;
/*  5:   */import com.soofound.portal.bean.BlackWhiteDto;
/*  6:   */import java.util.List;
/*  7:   */import java.util.Map;
/*  8:   */import org.springframework.jdbc.core.JdbcTemplate;
/*  9:   */import org.springframework.stereotype.Component;
/* 10:   */
/* 11:   */@Component
/* 12:   */public final class BlackWhiteDao extends BaseDao<BlackWhiteDto>
/* 13:   */{
/* 14:   */  protected String getQuerySQL(Map<String, String> options)
/* 15:   */  {
/* 16:16 */    StringBuffer sqlText = new StringBuffer(100);
/* 17:17 */    sqlText.append("select * from view_surfing_black_white where 1=1 ");
/* 18:18 */    if (!CommonUtil.isEmpty((String)options.get("bw")))
/* 19:19 */      sqlText.append(" and bw = ").append((String)options.get("bw"));
/* 20:20 */    if (!CommonUtil.isEmpty((String)options.get("mac")))
/* 21:21 */      sqlText.append(" and mac = '").append((String)options.get("mac")).append("'");
/* 22:22 */    if (!CommonUtil.isEmpty((String)options.get("branchId")))
/* 23:23 */      sqlText.append(" and branch_id like '").append((String)options.get("branchId")).append("%'");
/* 24:24 */    sqlText.append(" order by id");
/* 25:25 */    return sqlText.toString();
/* 26:   */  }
/* 27:   */  
/* 28:   */  public BlackWhiteDto findByMac(String branchId, String mac, int bw) {
/* 29:29 */    StringBuffer sqlText = new StringBuffer(100);
/* 30:30 */    sqlText.append("select * from view_surfing_black_white where mac='").append(mac);
/* 31:31 */    sqlText.append("' and branch_id='").append(branchId).append("' and bw=").append(bw);
/* 32:32 */    return (BlackWhiteDto)super.findOne(sqlText.toString());
/* 33:   */  }
/* 34:   */  
/* 35:   */  public List<BlackWhiteDto> findByIDs(String[] ids) {
/* 36:36 */    String sql = "select * from view_surfing_black_white where id in (" + CommonUtil.arrayToQuotString(ids) + ")";
/* 37:37 */    return super.findByCriteria(sql);
/* 38:   */  }
/* 39:   */  
/* 40:   */  public int update(BlackWhiteDto dto)
/* 41:   */  {
/* 42:42 */    StringBuffer sqlText = new StringBuffer(100);
/* 43:43 */    sqlText.append("update surfing_black_white set mac = '").append(dto.getMac());
/* 44:44 */    sqlText.append("', reason = '").append(dto.getReason()).append("' where id=").append(dto.getId());
/* 45:45 */    return super.saveOrUpdate(sqlText.toString());
/* 46:   */  }
/* 47:   */  
/* 48:   */  public synchronized int getNextID() {
/* 49:49 */    Integer id = (Integer)super.getJdbcTemplate().queryForObject("select max(id) + 1 from surfing_black_white", Integer.class);
/* 50:50 */    if (id == null)
/* 51:51 */      return 1;
/* 52:52 */    return id.intValue();
/* 53:   */  }
/* 54:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.dao.BlackWhiteDao
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */