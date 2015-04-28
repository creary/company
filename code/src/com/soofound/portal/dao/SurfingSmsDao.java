/*  1:   */package com.soofound.portal.dao;
/*  2:   */
/*  3:   */import com.soofound.framework.jdbc.BaseDao;
/*  4:   */import com.soofound.framework.util.CommonUtil;
/*  5:   */import com.soofound.portal.bean.SurfingSmsDto;
/*  6:   */import com.soofound.portal.bean.SurfingSmsStat;
/*  7:   */import java.sql.ResultSet;
/*  8:   */import java.sql.SQLException;
/*  9:   */import java.util.ArrayList;
/* 10:   */import java.util.List;
/* 11:   */import java.util.Map;
/* 12:   */import org.springframework.dao.DataAccessException;
/* 13:   */import org.springframework.jdbc.core.JdbcTemplate;
/* 14:   */import org.springframework.jdbc.core.ResultSetExtractor;
/* 15:   */import org.springframework.stereotype.Component;
/* 16:   */
/* 17:   */@Component
/* 18:   */public final class SurfingSmsDao extends BaseDao<SurfingSmsDto>
/* 19:   */{
/* 20:   */  protected String getQuerySQL(Map<String, String> options)
/* 21:   */  {
/* 22:22 */    StringBuffer sqlText = new StringBuffer(100);
/* 23:23 */    sqlText.append("select * from view_surfing_sms where 1=1 ");
/* 24:24 */    if (!CommonUtil.isEmpty((String)options.get("branchId")))
/* 25:25 */      sqlText.append(" and branch_id like '").append((String)options.get("branchId")).append("%'");
/* 26:26 */    if (!CommonUtil.isEmpty((String)options.get("mobile")))
/* 27:27 */      sqlText.append(" and mobile = '").append((String)options.get("mobile")).append("'");
/* 28:28 */    if (!CommonUtil.isEmpty((String)options.get("content")))
/* 29:29 */      sqlText.append(" and content like '%").append((String)options.get("content")).append("%'");
/* 30:30 */    sqlText.append(" order by log_time desc");
/* 31:31 */    return sqlText.toString();
/* 32:   */  }
/* 33:   */  
/* 34:   */  public List<SurfingSmsStat> getSurfingSmsStats(String branchId) {
/* 35:35 */    String sqlText = "select * from view_surfing_sms_stat where branch_id like '" + branchId + "%'";
/* 36:36 */    List<SurfingSmsStat> stats = (List)getJdbcTemplate().query(sqlText, new ResultSetExtractor() {
/* 37:   */      public List<SurfingSmsStat> extractData(ResultSet rs) throws SQLException, DataAccessException {
/* 38:38 */        List<SurfingSmsStat> stats = new ArrayList();
/* 39:39 */        while (rs.next()) {
/* 40:40 */          SurfingSmsStat stat = new SurfingSmsStat();
/* 41:41 */          stat.setBranch(rs.getString("branch"));
/* 42:42 */          stat.setPvTotal(rs.getInt("pv_total"));
/* 43:43 */          stat.setPvBiz(rs.getInt("pv_biz"));
/* 44:44 */          stat.setSmsUsed(rs.getInt("sms_used"));
/* 45:45 */          stat.setSmsRemain(rs.getInt("sms_remain"));
/* 46:46 */          stats.add(stat);
/* 47:   */        }
/* 48:48 */        return stats;
/* 49:49 */      } } );
/* 50:50 */    return stats;
/* 51:   */  }
/* 52:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.dao.SurfingSmsDao
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */