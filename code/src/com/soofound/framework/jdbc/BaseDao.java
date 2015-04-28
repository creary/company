/*   1:    */package com.soofound.framework.jdbc;
/*   2:    */
/*   3:    */import com.soofound.framework.util.SysConfigHelper;
/*   4:    */import java.util.ArrayList;
/*   5:    */import java.util.HashMap;
/*   6:    */import java.util.List;
/*   7:    */import java.util.Map;
/*   8:    */import javax.sql.DataSource;
/*   9:    */import org.apache.log4j.Logger;
/*  10:    */import org.springframework.jdbc.core.JdbcTemplate;
/*  11:    */import org.springframework.jdbc.core.RowMapper;
/*  12:    */import org.springframework.jdbc.core.support.JdbcDaoSupport;
/*  13:    */import org.springframework.util.Assert;
/*  14:    */import org.springframework.util.CollectionUtils;
/*  15:    */
/*  17:    */public abstract class BaseDao<T extends Persistable>
/*  18:    */  extends JdbcDaoSupport
/*  19:    */{
/*  20:    */  protected Class<T> clazz;
/*  21:    */  protected Pagination page;
/*  22:    */  protected MySQLDataExtractor extractor;
/*  23: 23 */  private Logger loger = Logger.getLogger(BaseDao.class);
/*  24:    */  
/*  25:    */  public BaseDao(String dataSourceName) {
/*  26: 26 */    DataSource ds = (DataSource)SysConfigHelper.getBean(dataSourceName);
/*  27: 27 */    setDataSource(ds);
/*  28: 28 */    this.clazz = getDTOClazz();
/*  29: 29 */    this.extractor = new MySQLDataExtractor();
/*  30:    */  }
/*  31:    */  
/*  32:    */  public BaseDao() {
/*  33: 33 */    this("defaultDS");
/*  34:    */  }
/*  35:    */  
/*  36:    */  public List<T> findByCriteria(String sql) {
/*  37: 37 */    Assert.notNull(sql, "sql must be not null");
/*  38: 38 */    this.loger.debug(sql);
/*  39: 39 */    return getJdbcTemplate().query(sql, getRowMapper());
/*  40:    */  }
/*  41:    */  
/*  42:    */  public List<T> findAll() {
/*  43: 43 */    String sql = getQuerySQL(new HashMap());
/*  44: 44 */    return getJdbcTemplate().query(sql, getRowMapper());
/*  45:    */  }
/*  46:    */  
/*  47:    */  public T findOne(String sql) {
/*  48: 48 */    Assert.notNull(sql, "sql must be not null");
/*  49: 49 */    List<T> _dtos = getJdbcTemplate().query(sql, getRowMapper());
/*  50: 50 */    if (!CollectionUtils.isEmpty(_dtos))
/*  51: 51 */      return (Persistable)_dtos.get(0);
/*  52: 52 */    return null;
/*  53:    */  }
/*  54:    */  
/*  55:    */  public T findByID(String id) {
/*  56: 56 */    String sql = this.extractor.buildFindByIDSQL(this.clazz, id);
/*  57: 57 */    return findOne(sql);
/*  58:    */  }
/*  59:    */  
/*  60:    */  public List<T> listByPage(int perPage, int currentPage, String sql) {
/*  61: 61 */    Assert.notNull(sql, "sql must be not null");
/*  62: 62 */    this.loger.debug(sql);
/*  63:    */    
/*  64: 64 */    int count = ((Integer)getJdbcTemplate().queryForObject("select count(1) from (" + sql + ") t", Integer.class)).intValue();
/*  65: 65 */    this.page = new Pagination(perPage, currentPage, count);
/*  66: 66 */    if (count == 0) { return new ArrayList();
/*  67:    */    }
/*  68: 68 */    String pageSql = this.extractor.buildPaginationSQL(sql, this.page);
/*  69: 69 */    List<T> list = getJdbcTemplate().query(pageSql, getRowMapper());
/*  70: 70 */    return list;
/*  71:    */  }
/*  72:    */  
/*  73:    */  public List<T> listByPage(int perPage, int currentPage, Map<String, String> options) {
/*  74: 74 */    return listByPage(perPage, currentPage, getQuerySQL(options));
/*  75:    */  }
/*  76:    */  
/*  77:    */  public int batchUpdate(List<String> sqls) {
/*  78: 78 */    String[] arrsqls = new String[sqls.size()];
/*  79: 79 */    sqls.toArray(arrsqls);
/*  80: 80 */    int[] results = getJdbcTemplate().batchUpdate(arrsqls);
/*  81: 81 */    return results.length;
/*  82:    */  }
/*  83:    */  
/*  84:    */  public int delete(String[] ids) {
/*  85: 85 */    int[] results = getJdbcTemplate().batchUpdate(this.extractor.buildDeleteSQL(this.clazz, ids));
/*  86: 86 */    return results.length;
/*  87:    */  }
/*  88:    */  
/*  89:    */  public int delete(String id) {
/*  90: 90 */    return getJdbcTemplate().update(this.extractor.buildDeleteSQL(this.clazz, id));
/*  91:    */  }
/*  92:    */  
/*  93:    */  public int save(T dto) {
/*  94: 94 */    String sql = this.extractor.buildInsertSQL(dto);
/*  95: 95 */    Assert.notNull(sql, "sql must be not null");
/*  96: 96 */    this.loger.debug(sql);
/*  97: 97 */    return getJdbcTemplate().update(sql);
/*  98:    */  }
/*  99:    */  
/* 100:    */  public int update(T dto) {
/* 101:101 */    String sql = this.extractor.buildUpdateSQL(dto);
/* 102:102 */    Assert.notNull(sql, "sql must be not null");
/* 103:103 */    this.loger.debug(sql);
/* 104:104 */    return getJdbcTemplate().update(sql);
/* 105:    */  }
/* 106:    */  
/* 107:    */  public int saveOrUpdate(String sql) {
/* 108:108 */    Assert.notNull(sql, "sql must be not null");
/* 109:109 */    this.loger.debug(sql);
/* 110:110 */    return getJdbcTemplate().update(sql);
/* 111:    */  }
/* 112:    */  
/* 113:    */  public final Class<T> getDTOClazz() {
/* 114:114 */    if (this.clazz == null)
/* 115:115 */      this.clazz = ((Class)((java.lang.reflect.ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
/* 116:116 */    return this.clazz;
/* 117:    */  }
/* 118:    */  
/* 119:    */  protected final RowMapper getRowMapper() {
/* 120:120 */    return new ResultRowMapper(this.clazz);
/* 121:    */  }
/* 122:    */  
/* 123:    */  public final Pagination getPagination() {
/* 124:124 */    return this.page;
/* 125:    */  }
/* 126:    */  
/* 127:    */  protected String getQuerySQL(Map<String, String> options) {
/* 128:128 */    return this.extractor.buildQuerySQL(this.clazz, options);
/* 129:    */  }
/* 130:    */  
/* 131:    */  public synchronized int getID(String table) {
/* 132:132 */    Integer id = (Integer)super.getJdbcTemplate().queryForObject("select max(id) + 1 from " + table, Integer.class);
/* 133:133 */    if (id == null)
/* 134:134 */      return 1;
/* 135:135 */    return id.intValue();
/* 136:    */  }
/* 137:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.framework.jdbc.BaseDao
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */