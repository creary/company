/*   1:    */package com.soofound.cpe.dao;
/*   2:    */
/*   3:    */import com.soofound.cpe.bean.ConfigParamBean;
/*   4:    */import com.soofound.cpe.bean.SoftwareBean;
/*   5:    */import com.soofound.framework.jdbc.BaseDao;
/*   6:    */import com.soofound.framework.util.CommonUtil;
/*   7:    */import java.sql.ResultSet;
/*   8:    */import java.sql.SQLException;
/*   9:    */import java.util.ArrayList;
/*  10:    */import java.util.List;
/*  11:    */import java.util.Map;
/*  12:    */import org.springframework.dao.DataAccessException;
/*  13:    */import org.springframework.jdbc.core.JdbcTemplate;
/*  14:    */import org.springframework.jdbc.core.ResultSetExtractor;
/*  15:    */import org.springframework.stereotype.Component;
/*  16:    */import org.springframework.util.StringUtils;
/*  17:    */
/*  18:    */@Component
/*  19:    */public final class SoftwareDao extends BaseDao<SoftwareBean>
/*  20:    */{
/*  21:    */  protected String getQuerySQL(Map<String, String> options)
/*  22:    */  {
/*  23: 23 */    StringBuffer sqlText = new StringBuffer(100);
/*  24: 24 */    sqlText.append("select * from view_cpe_software where tag=").append((String)options.get("tag"));
/*  25: 25 */    if (!CommonUtil.isEmpty((String)options.get("productModel")))
/*  26: 26 */      sqlText.append(" and product_model like '%").append((String)options.get("productModel")).append("%'");
/*  27: 27 */    if (!CommonUtil.isEmpty((String)options.get("descr")))
/*  28: 28 */      sqlText.append(" and descr like '%").append((String)options.get("descr")).append("%'");
/*  29: 29 */    if (!CommonUtil.isEmpty((String)options.get("branchId")))
/*  30: 30 */      sqlText.append(" and branch_id like '").append((String)options.get("branchId")).append("%'");
/*  31: 31 */    if (!CommonUtil.isEmpty((String)options.get("version"))) {
/*  32: 32 */      if ("nonAccess".equals(options.get("version"))) {
/*  33: 33 */        sqlText.append(" and version != 'access'");
/*  34:    */      } else
/*  35: 35 */        sqlText.append(" and version like '%").append((String)options.get("version")).append("%'");
/*  36:    */    }
/*  37: 37 */    sqlText.append(" order by id");
/*  38: 38 */    return sqlText.toString();
/*  39:    */  }
/*  40:    */  
/*  43:    */  public SoftwareBean findConfig(String branchId, String version, String productModel)
/*  44:    */  {
/*  45: 45 */    StringBuilder sqlText = new StringBuilder(100);
/*  46: 46 */    sqlText.append("select * from cpe_software where tag=2 and version='").append(version).append("' and branch_id='");
/*  47: 47 */    sqlText.append(branchId).append("'");
/*  48: 48 */    if (productModel != null)
/*  49: 49 */      sqlText.append(" and product_model='").append(productModel).append("'");
/*  50: 50 */    return (SoftwareBean)super.findOne(sqlText.toString());
/*  51:    */  }
/*  52:    */  
/*  55:    */  public List<SoftwareBean> findSoftware(int tag, String branchId)
/*  56:    */  {
/*  57: 57 */    StringBuilder sqlText = new StringBuilder(100);
/*  58: 58 */    sqlText.append("select * from cpe_software where tag=").append(tag);
/*  59: 59 */    if (StringUtils.hasText(branchId))
/*  60: 60 */      sqlText.append(" and branch_id like '").append(branchId).append("%'");
/*  61: 61 */    sqlText.append(" order by id desc");
/*  62: 62 */    return super.findByCriteria(sqlText.toString());
/*  63:    */  }
/*  64:    */  
/*  65:    */  public SoftwareBean findLastSoftware(int verCode, String productModel, String branchId) {
/*  66: 66 */    StringBuilder sqlText = new StringBuilder();
/*  67: 67 */    sqlText.append("select * from cpe_software where tag=1 and version_code > ").append(verCode);
/*  68: 68 */    sqlText.append(" and product_model='").append(productModel).append("'");
/*  69: 69 */    if (branchId != null)
/*  70: 70 */      sqlText.append(" and branch_id like '").append(branchId).append("%'");
/*  71: 71 */    sqlText.append(" order by version_code desc");
/*  72: 72 */    List<SoftwareBean> sbs = super.findByCriteria(sqlText.toString());
/*  73: 73 */    if (CommonUtil.isEmpty(sbs))
/*  74: 74 */      return null;
/*  75: 75 */    return (SoftwareBean)sbs.get(0);
/*  76:    */  }
/*  77:    */  
/*  78:    */  public List<SoftwareBean> findByIDs(String[] ids) {
/*  79: 79 */    String idstr = CommonUtil.arrayToString(ids);
/*  80: 80 */    String sql = "select * from cpe_software where id in (" + idstr + ")";
/*  81: 81 */    return super.findByCriteria(sql);
/*  82:    */  }
/*  83:    */  
/*  84:    */  public SoftwareBean findByID(String id) {
/*  85: 85 */    String sql = "select * from cpe_software where id = " + id;
/*  86: 86 */    return (SoftwareBean)super.findOne(sql);
/*  87:    */  }
/*  88:    */  
/*  89:    */  public SoftwareBean findConfigByProductModel(String branchId, String productModel) {
/*  90: 90 */    StringBuilder sqlText = new StringBuilder();
/*  91: 91 */    sqlText.append("select * from cpe_software where tag='2' and product_model='");
/*  92: 92 */    sqlText.append(productModel).append("' and branch_id like '").append(branchId).append("%'");
/*  93: 93 */    return (SoftwareBean)super.findOne(sqlText.toString());
/*  94:    */  }
/*  95:    */  
/*  96:    */  public List<ConfigParamBean> getConfigParams(int cfgId) {
/*  97: 97 */    String sqlText = "SELECT a.cfg_id,a.attribute_id,a.attribute_value,b.name FROM cpe_configuration a,cpe_property b WHERE a.attribute_id=b.id AND cfg_id=" + 
/*  98: 98 */      cfgId + " order by attribute_id";
/*  99: 99 */    List<ConfigParamBean> beans = (List)super.getJdbcTemplate().query(sqlText, new ResultSetExtractor() {
/* 100:    */      public List<ConfigParamBean> extractData(ResultSet rs) throws SQLException, DataAccessException {
/* 101:101 */        List<ConfigParamBean> beans = new ArrayList();
/* 102:102 */        while (rs.next()) {
/* 103:103 */          ConfigParamBean bean = new ConfigParamBean();
/* 104:104 */          bean.setCfgId(rs.getInt("cfg_id"));
/* 105:105 */          bean.setId(rs.getInt("attribute_id"));
/* 106:106 */          bean.setName(rs.getString("name"));
/* 107:107 */          bean.setValue(rs.getString("attribute_value"));
/* 108:108 */          beans.add(bean);
/* 109:    */        }
/* 110:110 */        return beans;
/* 111:111 */      } } );
/* 112:112 */    return beans;
/* 113:    */  }
/* 114:    */  
/* 115:    */  public int save(SoftwareBean dto)
/* 116:    */  {
/* 117:117 */    if (dto.getVersion().equals("default"))
/* 118:    */    {
/* 119:119 */      String sql = "update cpe_software set version='' where version='default' and tag=2 and product_model='" + dto.getProductModel() + "'";
/* 120:120 */      super.getJdbcTemplate().update(sql);
/* 121:    */    }
/* 122:122 */    return super.save(dto);
/* 123:    */  }
/* 124:    */  
/* 125:    */  public int update(SoftwareBean dto)
/* 126:    */  {
/* 127:127 */    if (dto.getVersion().equals("default"))
/* 128:    */    {
/* 129:129 */      String sql = "update cpe_software set version='' where version='default' and tag=2 and product_model='" + dto.getProductModel() + "'";
/* 130:130 */      super.getJdbcTemplate().update(sql);
/* 131:    */    }
/* 132:132 */    return super.update(dto);
/* 133:    */  }
/* 134:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.cpe.dao.SoftwareDao
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */