/*  1:   */package com.soofound.cpe.dao;
/*  2:   */
/*  3:   */import com.soofound.cpe.bean.UpgradeBean;
/*  4:   */import com.soofound.framework.jdbc.BaseDao;
/*  5:   */import com.soofound.framework.util.CommonUtil;
/*  6:   */import java.util.Map;
/*  7:   */import org.springframework.jdbc.core.JdbcTemplate;
/*  8:   */import org.springframework.stereotype.Component;
/*  9:   */
/* 10:   */@Component
/* 11:   */public final class UpgradeDao extends BaseDao<UpgradeBean>
/* 12:   */{
/* 13:   */  protected String getQuerySQL(Map<String, String> options)
/* 14:   */  {
/* 15:15 */    StringBuffer sqlText = new StringBuffer(100);
/* 16:16 */    sqlText.append("select * from view_cpe_upgrade where 1=1 ");
/* 17:17 */    if (!CommonUtil.isEmpty((String)options.get("branchId")))
/* 18:18 */      sqlText.append(" and branch_id like '").append((String)options.get("branchId")).append("%'");
/* 19:19 */    if (!CommonUtil.isEmpty((String)options.get("branch")))
/* 20:20 */      sqlText.append(" and branch like '%").append((String)options.get("branch")).append("%'");
/* 21:21 */    sqlText.append(" order by branch_id");
/* 22:22 */    return sqlText.toString();
/* 23:   */  }
/* 24:   */  
/* 25:   */  public UpgradeBean findByID(String id)
/* 26:   */  {
/* 27:27 */    StringBuffer sqlText = new StringBuffer(100);
/* 28:28 */    sqlText.append("select * from view_cpe_upgrade where branch_id='").append(id).append("'");
/* 29:29 */    return (UpgradeBean)super.findOne(sqlText.toString());
/* 30:   */  }
/* 31:   */  
/* 32:   */  public int update(UpgradeBean dto)
/* 33:   */  {
/* 34:34 */    StringBuffer sqlText = new StringBuffer(100);
/* 35:35 */    sqlText.append("update cpe_upgrade set flag=").append(dto.getFlag()).append(" where branch_id='");
/* 36:36 */    sqlText.append(dto.getBranchId()).append("'");
/* 37:37 */    int result = super.getJdbcTemplate().update(sqlText.toString());
/* 38:38 */    if (result == 0) {
/* 39:39 */      sqlText.setLength(0);
/* 40:40 */      sqlText.append("insert into cpe_upgrade(branch_id,flag)values('").append(dto.getBranchId()).append("',");
/* 41:41 */      sqlText.append(dto.getFlag()).append(")");
/* 42:42 */      result = super.getJdbcTemplate().update(sqlText.toString());
/* 43:   */    }
/* 44:44 */    return result;
/* 45:   */  }
/* 46:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.cpe.dao.UpgradeDao
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */