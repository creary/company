/*  1:   */package com.soofound.framework.jdbc;
/*  2:   */
/*  3:   */import java.util.ArrayList;
/*  4:   */import java.util.List;
/*  5:   */
/*  6:   */public abstract class OracleDao<T extends Persistable> extends BaseDao<T>
/*  7:   */{
/*  8:   */  public OracleDao()
/*  9:   */  {
/* 10:10 */    super("oracleDS");
/* 11:   */  }
/* 12:   */  
/* 13:   */  public List<T> listByPage(int perPage, int currentPage, String sql)
/* 14:   */  {
/* 15:15 */    org.springframework.util.Assert.notNull(sql, "sql must be not null");
/* 16:16 */    int count = ((Integer)getJdbcTemplate().queryForObject("select count(1) from (" + sql + ") t", Integer.class)).intValue();
/* 17:17 */    this.page = new Pagination(perPage, currentPage, count);
/* 18:18 */    if (count == 0) { return new ArrayList();
/* 19:   */    }
/* 20:20 */    String pageSql = buildOraclePaginationSQL(sql, this.page);
/* 21:21 */    return getJdbcTemplate().query(pageSql, getRowMapper());
/* 22:   */  }
/* 23:   */  
/* 24:   */  private String buildOraclePaginationSQL(String sql, Pagination page) {
/* 25:25 */    StringBuffer sqlText = new StringBuffer(500);
/* 26:26 */    sqlText.append("select * from (select A.*, rownum rum from (").append(sql);
/* 27:27 */    sqlText.append(") A ) where rum between ").append(page.getStartRow()).append(" AND ").append(page.getEndRow());
/* 28:28 */    return sqlText.toString();
/* 29:   */  }
/* 30:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.framework.jdbc.OracleDao
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */