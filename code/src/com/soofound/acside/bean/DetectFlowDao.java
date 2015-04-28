/*  1:   */package com.soofound.acside.bean;
/*  2:   */
/*  3:   */import com.soofound.framework.jdbc.BaseDao;
/*  4:   */import java.util.ArrayList;
/*  5:   */import java.util.List;
/*  6:   */import org.springframework.jdbc.core.JdbcTemplate;
/*  7:   */import org.springframework.stereotype.Component;
/*  8:   */
/*  9:   */@Component
/* 10:   */public class DetectFlowDao extends BaseDao<DetectFlowDto>
/* 11:   */{
/* 12:   */  public DetectFlowDao()
/* 13:   */  {
/* 14:14 */    super("acsideDS");
/* 15:   */  }
/* 16:   */  
/* 17:   */  public List<DetectFlowDto> listByPage(int perPage, int currentPage, String sql)
/* 18:   */  {
/* 19:19 */    int count = ((Integer)getJdbcTemplate().queryForObject("select count(1) from (" + sql + ") t", Integer.class)).intValue();
/* 20:20 */    this.page = new com.soofound.framework.jdbc.Pagination(perPage, currentPage, count);
/* 21:21 */    if (count == 0) { return new ArrayList();
/* 22:   */    }
/* 23:23 */    StringBuffer sqlText = new StringBuffer(100);
/* 24:24 */    sqlText.append("select * from (").append(sql).append(") t limit ").append(this.page.getPerSize());
/* 25:25 */    sqlText.append(" offset ").append(this.page.getStartRow() - 1);
/* 26:26 */    return getJdbcTemplate().query(sqlText.toString(), getRowMapper());
/* 27:   */  }
/* 28:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.acside.bean.DetectFlowDao
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */