/*  1:   */package com.soofound.portal.dao;
/*  2:   */
/*  3:   */import com.soofound.framework.jdbc.BaseDao;
/*  4:   */import com.soofound.framework.util.CommonUtil;
/*  5:   */import com.soofound.portal.bean.PortalTemplateDto;
/*  6:   */import java.util.Map;
/*  7:   */import org.springframework.jdbc.core.JdbcTemplate;
/*  8:   */import org.springframework.stereotype.Component;
/*  9:   */
/* 14:   */@Component
/* 15:   */public final class PortalTemplateDao
/* 16:   */  extends BaseDao<PortalTemplateDto>
/* 17:   */{
/* 18:   */  protected String getQuerySQL(Map<String, String> options)
/* 19:   */  {
/* 20:20 */    StringBuffer sqlText = new StringBuffer(100);
/* 21:21 */    sqlText.append("select * from portal_template where 1=1 ");
/* 22:22 */    if (!CommonUtil.isEmpty((String)options.get("name")))
/* 23:23 */      sqlText.append(" and name like '%").append((String)options.get("name")).append("%'");
/* 24:24 */    sqlText.append(" order by create_time desc");
/* 25:25 */    return sqlText.toString();
/* 26:   */  }
/* 27:   */  
/* 28:   */  public int delete(String id)
/* 29:   */  {
/* 30:30 */    String sql = "delete from portal_template where id='" + id + "'";
/* 31:31 */    return getJdbcTemplate().update(sql);
/* 32:   */  }
/* 33:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.dao.PortalTemplateDao
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */