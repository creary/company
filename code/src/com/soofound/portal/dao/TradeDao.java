/*  1:   */package com.soofound.portal.dao;
/*  2:   */
/*  3:   */import com.soofound.framework.jdbc.BaseDao;
/*  4:   */import com.soofound.framework.util.CommonUtil;
/*  5:   */import com.soofound.portal.bean.TradeDto;
/*  6:   */import java.util.Map;
/*  7:   */import org.springframework.stereotype.Component;
/*  8:   */
/*  9:   */@Component
/* 10:   */public final class TradeDao
/* 11:   */  extends BaseDao<TradeDto>
/* 12:   */{
/* 13:   */  protected String getQuerySQL(Map<String, String> options)
/* 14:   */  {
/* 15:15 */    StringBuilder sqlText = new StringBuilder();
/* 16:16 */    sqlText.append("select * from view_ebiz_trade where 1=1 ");
/* 17:17 */    if (!CommonUtil.isEmpty((String)options.get("branchId")))
/* 18:18 */      sqlText.append(" and branch_id like '").append((String)options.get("branchId")).append("%'");
/* 19:19 */    if (!CommonUtil.isEmpty((String)options.get("branch")))
/* 20:20 */      sqlText.append(" and branch like '%").append((String)options.get("branch")).append("%'");
/* 21:21 */    if (!CommonUtil.isEmpty((String)options.get("orderNo")))
/* 22:22 */      sqlText.append(" and order_no = '").append((String)options.get("orderNo")).append("'");
/* 23:23 */    if (!CommonUtil.isEmpty((String)options.get("summary")))
/* 24:24 */      sqlText.append(" and summary like '%").append((String)options.get("summary")).append("%'");
/* 25:25 */    sqlText.append(" order by log_time desc");
/* 26:26 */    return sqlText.toString();
/* 27:   */  }
/* 28:   */  
/* 29:   */  public TradeDto findByID(String id)
/* 30:   */  {
/* 31:31 */    String sql = "select * from view_ebiz_trade where order_no='" + id + "'";
/* 32:32 */    return (TradeDto)super.findOne(sql);
/* 33:   */  }
/* 34:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.dao.TradeDao
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */