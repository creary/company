/*   1:    */package com.soofound.cpe.dao;
/*   2:    */
/*   3:    */import com.soofound.cpe.bean.PropertyBean;
/*   4:    */import com.soofound.framework.jdbc.BaseDao;
/*   5:    */import com.soofound.framework.util.CommonUtil;
/*   6:    */import java.util.List;
/*   7:    */import java.util.Map;
/*   8:    */import org.springframework.jdbc.core.JdbcTemplate;
/*   9:    */import org.springframework.stereotype.Component;
/*  10:    */
/*  11:    */@Component
/*  12:    */public final class PropertyDao extends BaseDao<PropertyBean>
/*  13:    */{
/*  14: 14 */  private static String FIND_ALL_SQL = "SELECT * FROM cpe_property ORDER BY id";
/*  15:    */  
/*  16:    */  protected String getQuerySQL(Map<String, String> options)
/*  17:    */  {
/*  18: 18 */    StringBuffer sqlText = new StringBuffer(100);
/*  19: 19 */    sqlText.append("select * from cpe_property where 1=1 ");
/*  20: 20 */    if (!CommonUtil.isEmpty((String)options.get("cn_name")))
/*  21: 21 */      sqlText.append(" and cn_name like '%").append((String)options.get("cn_name")).append("%'");
/*  22: 22 */    if (!CommonUtil.isEmpty((String)options.get("en_name")))
/*  23: 23 */      sqlText.append(" and en_name like '%").append((String)options.get("en_name")).append("%'");
/*  24: 24 */    sqlText.append(" order by name");
/*  25: 25 */    return sqlText.toString();
/*  26:    */  }
/*  27:    */  
/*  32:    */  public List<PropertyBean> findProperties(String model, int action)
/*  33:    */  {
/*  34: 34 */    StringBuilder sqlText = new StringBuilder(100);
/*  35: 35 */    sqlText.append("select * from cpe_property where tag in (");
/*  36: 36 */    if (action == 1) {
/*  37: 37 */      if (model.endsWith("-A")) {
/*  38: 38 */        sqlText.append("1,2,3,4");
/*  39:    */      } else
/*  40: 40 */        sqlText.append("1,3");
/*  41: 41 */    } else if (action == 2) {
/*  42: 42 */      if (model.endsWith("-A")) {
/*  43: 43 */        sqlText.append("1,2");
/*  44:    */      } else
/*  45: 45 */        sqlText.append("1");
/*  46: 46 */    } else if (action == 3) {
/*  47: 47 */      if (model.endsWith("-A")) {
/*  48: 48 */        sqlText.append("3,4");
/*  49:    */      } else
/*  50: 50 */        sqlText.append("3");
/*  51:    */    }
/*  52: 52 */    sqlText.append(") order by id");
/*  53: 53 */    return findByCriteria(sqlText.toString());
/*  54:    */  }
/*  55:    */  
/*  56:    */  public List<PropertyBean> findAll()
/*  57:    */  {
/*  58: 58 */    return super.findByCriteria(FIND_ALL_SQL);
/*  59:    */  }
/*  60:    */  
/*  61:    */  public PropertyBean findByID(String id)
/*  62:    */  {
/*  63: 63 */    String sql = "select * from cpe_property where id=" + id;
/*  64: 64 */    return (PropertyBean)super.findOne(sql);
/*  65:    */  }
/*  66:    */  
/*  67:    */  public PropertyBean findByName(String name) {
/*  68: 68 */    String sql = "select * from cpe_property where name='" + name + "'";
/*  69: 69 */    return (PropertyBean)super.findOne(sql);
/*  70:    */  }
/*  71:    */  
/*  72:    */  public int update(PropertyBean dto)
/*  73:    */  {
/*  74: 74 */    StringBuilder sqlText = new StringBuilder();
/*  75: 75 */    sqlText.append("update cpe_property set tag=").append(dto.getTag());
/*  76: 76 */    sqlText.append(",cn_name='").append(dto.getCnName());
/*  77: 77 */    sqlText.append("',en_name='").append(dto.getEnName());
/*  78: 78 */    sqlText.append("',descr='").append(dto.getDescr());
/*  79: 79 */    sqlText.append("' where id=").append(dto.getId());
/*  80: 80 */    return super.saveOrUpdate(sqlText.toString());
/*  81:    */  }
/*  82:    */  
/*  83:    */  public List<PropertyBean> findByConfigable(String model, boolean admin) {
/*  84: 84 */    StringBuilder sqlText = new StringBuilder(100);
/*  85: 85 */    sqlText.append("select * from cpe_property where cfg in (");
/*  86: 86 */    if (admin) {
/*  87: 87 */      if (model.endsWith("-A")) {
/*  88: 88 */        sqlText.append("1,2,3");
/*  89:    */      } else {
/*  90: 90 */        sqlText.append("1,3");
/*  91:    */      }
/*  92: 92 */    } else if (model.endsWith("-A")) {
/*  93: 93 */      sqlText.append("1,2");
/*  94:    */    } else {
/*  95: 95 */      sqlText.append("1");
/*  96:    */    }
/*  97: 97 */    sqlText.append(") order by id");
/*  98: 98 */    return findByCriteria(sqlText.toString());
/*  99:    */  }
/* 100:    */  
/* 101:    */  public synchronized int getNextID() {
/* 102:102 */    Integer id = (Integer)super.getJdbcTemplate().queryForObject("select max(id) + 1 from cpe_property", Integer.class);
/* 103:103 */    if (id == null)
/* 104:104 */      return 1;
/* 105:105 */    return id.intValue();
/* 106:    */  }
/* 107:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.cpe.dao.PropertyDao
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */