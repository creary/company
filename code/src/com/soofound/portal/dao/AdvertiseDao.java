/*   1:    */package com.soofound.portal.dao;
/*   2:    */
/*   3:    */import com.soofound.framework.jdbc.BaseDao;
/*   4:    */import com.soofound.framework.util.CommonUtil;
/*   5:    */import com.soofound.portal.bean.AdvertiseDto;
/*   6:    */import java.util.ArrayList;
/*   7:    */import java.util.List;
/*   8:    */import java.util.Map;
/*   9:    */import org.springframework.dao.EmptyResultDataAccessException;
/*  10:    */import org.springframework.jdbc.core.JdbcTemplate;
/*  11:    */import org.springframework.stereotype.Component;
/*  12:    */import org.springframework.util.StringUtils;
/*  13:    */
/*  14:    */@Component
/*  15:    */public final class AdvertiseDao extends BaseDao<AdvertiseDto>
/*  16:    */{
/*  17:    */  private static final String SQL_TOTAL = "SELECT COUNT(*) FROM surfing_advertise WHERE branch_id=?";
/*  18:    */  private static final String SQL_TOTAL2 = "SELECT COUNT(*) FROM surfing_advertise WHERE branch_id=? AND category_id=?";
/*  19:    */  
/*  20:    */  protected String getQuerySQL(Map<String, String> options)
/*  21:    */  {
/*  22: 22 */    StringBuffer sqlText = new StringBuffer(100);
/*  23: 23 */    sqlText.append("select * from view_surfing_advertise where 1=1 ");
/*  24: 24 */    if (StringUtils.hasText((String)options.get("title")))
/*  25: 25 */      sqlText.append(" and title like '%").append((String)options.get("title")).append("%'");
/*  26: 26 */    if (StringUtils.hasText((String)options.get("branchId")))
/*  27: 27 */      sqlText.append(" and branch_id like '").append((String)options.get("branchId")).append("%'");
/*  28: 28 */    if (StringUtils.hasText((String)options.get("portalId")))
/*  29: 29 */      sqlText.append(" and title like '%").append((String)options.get("title")).append("%'");
/*  30: 30 */    sqlText.append(" order by create_time desc");
/*  31: 31 */    return sqlText.toString();
/*  32:    */  }
/*  33:    */  
/*  34:    */  public int update(AdvertiseDto dto)
/*  35:    */  {
/*  36: 36 */    StringBuffer sqlText = new StringBuffer(100);
/*  37: 37 */    sqlText.append("update surfing_advertise set title='").append(dto.getTitle());
/*  38: 38 */    if (StringUtils.hasText(dto.getCover()))
/*  39: 39 */      sqlText.append("',cover='").append(dto.getCover());
/*  40: 40 */    sqlText.append("',summary='").append(dto.getSummary());
/*  41: 41 */    sqlText.append("',content='").append(dto.getContent());
/*  42: 42 */    sqlText.append("',category_id=").append(dto.getCategoryId());
/*  43: 43 */    sqlText.append(",create_time='").append(dto.getCreateTime());
/*  44: 44 */    sqlText.append("' where id='").append(dto.getId()).append("'");
/*  45: 45 */    return super.saveOrUpdate(sqlText.toString());
/*  46:    */  }
/*  47:    */  
/*  48:    */  public List<AdvertiseDto> getShareArticles(String branchId, String portalId, int start, int count) {
/*  49: 49 */    StringBuilder sqlText = new StringBuilder(200);
/*  50: 50 */    sqlText.append("select * from surfing_advertise where branch_id='").append(branchId).append("' and id not in (select share_id from portal_wechat_share where portal_id=");
/*  51: 51 */    sqlText.append(portalId).append(") order by create_time desc ").append(" limit ").append(start).append(",").append(count);
/*  52: 52 */    return super.findByCriteria(sqlText.toString());
/*  53:    */  }
/*  54:    */  
/*  55:    */  public int getShareArticleTotal(String portalId) {
/*  56: 56 */    StringBuilder sqlText = new StringBuilder(200);
/*  57: 57 */    sqlText.append("select count(*) from surfing_advertise where id not in (select share_id from portal_wechat_share where portal_id=");
/*  58: 58 */    sqlText.append(portalId).append(")");
/*  59:    */    try {
/*  60: 60 */      return ((Integer)getJdbcTemplate().queryForObject(sqlText.toString(), Integer.class)).intValue();
/*  61:    */    } catch (EmptyResultDataAccessException ex) {}
/*  62: 62 */    return 0;
/*  63:    */  }
/*  64:    */  
/*  65:    */  public List<AdvertiseDto> getArticles(String branchId)
/*  66:    */  {
/*  67: 67 */    StringBuilder sqlText = new StringBuilder(200);
/*  68: 68 */    sqlText.append("select * from view_surfing_advertise where branch_id='").append(branchId);
/*  69: 69 */    sqlText.append("' order by create_time desc");
/*  70: 70 */    return super.findByCriteria(sqlText.toString());
/*  71:    */  }
/*  72:    */  
/*  73:    */  public List<AdvertiseDto> getArticles(String branchId, String cid, String order, int start, int count) {
/*  74: 74 */    StringBuilder sqlText = new StringBuilder(200);
/*  75: 75 */    sqlText.append("select * from view_surfing_advertise where branch_id='").append(branchId).append("' and category_id=");
/*  76: 76 */    sqlText.append(cid).append(" order by ");
/*  77: 77 */    if ("".equals(order)) {
/*  78: 78 */      sqlText.append(" create_time desc");
/*  79:    */    } else
/*  80: 80 */      sqlText.append(order);
/*  81: 81 */    sqlText.append(" limit ").append(start).append(",").append(count);
/*  82: 82 */    return super.findByCriteria(sqlText.toString());
/*  83:    */  }
/*  84:    */  
/*  85:    */  public AdvertiseDto findByID(String id)
/*  86:    */  {
/*  87: 87 */    String sql = "select * from view_surfing_advertise where id='" + id + "'";
/*  88: 88 */    return (AdvertiseDto)super.findOne(sql);
/*  89:    */  }
/*  90:    */  
/*  91:    */  public int delete(String[] ids)
/*  92:    */  {
/*  93: 93 */    List<String> sqls = new ArrayList();
/*  94: 94 */    for (String id : ids) {
/*  95: 95 */      String sql1 = "delete from portal_wechat_share where share_id=" + id;
/*  96: 96 */      sqls.add(sql1);
/*  97: 97 */      String sql2 = "delete from surfing_advertise where id=" + id;
/*  98: 98 */      sqls.add(sql2);
/*  99:    */    }
/* 100:100 */    int[] results = getJdbcTemplate().batchUpdate(CommonUtil.toArray(sqls));
/* 101:101 */    return results.length;
/* 102:    */  }
/* 103:    */  
/* 104:    */  public int getArticleTotal(String branchId, String cid) {
/* 105:    */    try {
/* 106:106 */      return ((Integer)getJdbcTemplate().queryForObject("SELECT COUNT(*) FROM surfing_advertise WHERE branch_id=? AND category_id=?", new String[] { branchId, cid }, Integer.class)).intValue();
/* 107:    */    } catch (EmptyResultDataAccessException ex) {}
/* 108:108 */    return 0;
/* 109:    */  }
/* 110:    */  
/* 111:    */  public int getArticleTotal(String branchId)
/* 112:    */  {
/* 113:    */    try {
/* 114:114 */      return ((Integer)getJdbcTemplate().queryForObject("SELECT COUNT(*) FROM surfing_advertise WHERE branch_id=?", new String[] { branchId }, Integer.class)).intValue();
/* 115:    */    } catch (EmptyResultDataAccessException ex) {}
/* 116:116 */    return 0;
/* 117:    */  }
/* 118:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.dao.AdvertiseDao
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */