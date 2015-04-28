/*   1:    */package com.soofound.framework.jdbc;
/*   2:    */
/*   3:    */import com.soofound.framework.annotation.FieldAttribute;
/*   4:    */import com.soofound.framework.annotation.PersistableAttribute;
/*   5:    */import com.soofound.framework.annotation.PersistableAttributeFactory;
/*   6:    */import com.soofound.framework.util.CommonUtil;
/*   7:    */import com.soofound.framework.util.SysConfigHelper;
/*   8:    */import java.lang.reflect.Method;
/*   9:    */import java.util.List;
/*  10:    */import java.util.Map;
/*  11:    */import org.apache.log4j.Logger;
/*  12:    */import org.springframework.jdbc.core.JdbcTemplate;
/*  13:    */import org.springframework.util.Assert;
/*  14:    */
/*  16:    */public class MySQLDataExtractor
/*  17:    */{
/*  18: 18 */  private static Logger logger = Logger.getLogger(MySQLDataExtractor.class);
/*  19:    */  
/*  20:    */  private String convertSpecialChar(String str) {
/*  21: 21 */    if ((str != null) && (str.indexOf("'") != -1))
/*  22: 22 */      return str.replaceAll("'", "''");
/*  23: 23 */    return str;
/*  24:    */  }
/*  25:    */  
/*  26:    */  public synchronized String buildInsertSQL(Persistable persistable) {
/*  27: 27 */    Assert.notNull(persistable, "Persistable must be not null");
/*  28:    */    
/*  29: 29 */    Class<? extends Persistable> clazz = persistable.getClass();
/*  30: 30 */    PersistableAttribute pa = PersistableAttributeFactory.getFactory().getPersistableAttribute(clazz);
/*  31:    */    try {
/*  32: 32 */      StringBuffer sqlText = new StringBuffer(200);
/*  33: 33 */      StringBuffer valueText = new StringBuffer(100);
/*  34: 34 */      sqlText.append("insert into ").append(pa.getTableName()).append("(");
/*  35: 35 */      for (FieldAttribute fa : pa.getFieldAttributes())
/*  36: 36 */        if (!fa.isJoin())
/*  37:    */        {
/*  38: 38 */          Method method = CommonUtil.lookupMethod(clazz, "get" + fa.getAttribute());
/*  39: 39 */          if (method == null)
/*  40: 40 */            method = CommonUtil.lookupMethod(clazz, "is" + fa.getAttribute());
/*  41: 41 */          if (method == null) {
/*  42: 42 */            throw new IllegalArgumentException(persistable.getClass() + ":不存在get或is " + fa.getAttribute() + "方法");
/*  43:    */          }
/*  44: 44 */          Object _obj = method.invoke(persistable, new Object[0]);
/*  45: 45 */          String _value = null;
/*  46: 46 */          if (_obj != null) {
/*  47: 47 */            if (fa.isPrimaryKey()) {
/*  48: 48 */              if (fa.isAutoIncrement()) {
/*  49:    */                continue;
/*  50:    */              }
/*  51: 51 */              if (_obj.toString().equals("0")) {
/*  52: 52 */                int nextId = getNextID(pa.getTableName(), fa.getName());
/*  53: 53 */                _value = String.valueOf(nextId);
/*  54: 54 */                Method _method = CommonUtil.lookupMethod(clazz, "set" + fa.getAttribute());
/*  55: 55 */                if (_method != null)
/*  56: 56 */                  _method.invoke(persistable, new Object[] { Integer.valueOf(nextId) });
/*  57:    */              } else {
/*  58: 58 */                _value = _obj.toString();
/*  59: 59 */              } } else if ((_obj instanceof Boolean)) {
/*  60: 60 */              if (Boolean.parseBoolean(_obj.toString())) {
/*  61: 61 */                _value = "1";
/*  62:    */              } else
/*  63: 63 */                _value = "0";
/*  64:    */            } else {
/*  65: 65 */              _value = _obj.toString(); }
/*  66: 66 */            sqlText.append(fa.getName()).append(",");
/*  67: 67 */            if (CommonUtil.isEmpty(_value)) {
/*  68: 68 */              valueText.append("null,");
/*  69:    */            } else
/*  70: 70 */              valueText.append("'").append(convertSpecialChar(_value)).append("',");
/*  71:    */          }
/*  72:    */        }
/*  73: 73 */      if (valueText.length() == 0) {
/*  74: 74 */        Assert.notNull(sqlText.toString() + "SQL有错误");
/*  75:    */      }
/*  76: 76 */      sqlText.delete(sqlText.length() - 1, sqlText.length());
/*  77: 77 */      valueText.delete(valueText.length() - 1, valueText.length());
/*  78: 78 */      sqlText.append(")values(").append(valueText.toString()).append(")");
/*  79:    */      
/*  80: 80 */      return sqlText.toString();
/*  81:    */    } catch (Exception e) {
/*  82: 82 */      logger.error(e);
/*  83:    */    }
/*  84: 84 */    return null;
/*  85:    */  }
/*  86:    */  
/*  87:    */  public String buildPaginationSQL(String sql, int startRow, int perPageRowTotal) {
/*  88: 88 */    StringBuffer sqlBuf = new StringBuffer(500);
/*  89: 89 */    sqlBuf.append("select * from (").append(sql);
/*  90: 90 */    sqlBuf.append(") t limit ").append(startRow - 1);
/*  91: 91 */    sqlBuf.append(",").append(perPageRowTotal);
/*  92:    */    
/*  93: 93 */    return sqlBuf.toString();
/*  94:    */  }
/*  95:    */  
/*  96:    */  public String buildFindByIDSQL(Class<? extends Persistable> clazz, String id) {
/*  97: 97 */    PersistableAttribute paa = PersistableAttributeFactory.getFactory().getPersistableAttribute(clazz);
/*  98: 98 */    List<FieldAttribute> persistAttributes = paa.getFieldAttributes();
/*  99: 99 */    String pk = "id";
/* 100:100 */    for (FieldAttribute pa : persistAttributes) {
/* 101:101 */      if (pa.isPrimaryKey())
/* 102:102 */        pk = pa.getName();
/* 103:    */    }
/* 104:104 */    StringBuffer sqlText = new StringBuffer(50);
/* 105:105 */    sqlText.append("select * from ").append(paa.getTableName());
/* 106:106 */    sqlText.append(" where ").append(pk).append("='").append(id).append("'");
/* 107:    */    
/* 108:108 */    return sqlText.toString();
/* 109:    */  }
/* 110:    */  
/* 111:    */  public String[] buildDeleteSQL(Class<? extends Persistable> clazz, String[] ids) {
/* 112:112 */    PersistableAttribute paa = PersistableAttributeFactory.getFactory().getPersistableAttribute(clazz);
/* 113:113 */    List<FieldAttribute> persistAttributes = paa.getFieldAttributes();
/* 114:114 */    String pk = "id";
/* 115:115 */    for (FieldAttribute pa : persistAttributes) {
/* 116:116 */      if (pa.isPrimaryKey())
/* 117:117 */        pk = pa.getName();
/* 118:    */    }
/* 119:119 */    String[] sqls = new String[ids.length];
/* 120:120 */    for (int i = 0; i < ids.length; i++) {
/* 121:121 */      sqls[i] = ("delete from " + paa.getTableName() + " where " + pk + "='" + ids[i] + "'");
/* 122:    */    }
/* 123:123 */    return sqls;
/* 124:    */  }
/* 125:    */  
/* 126:    */  public String buildDeleteSQL(Class<? extends Persistable> clazz, String id) {
/* 127:127 */    PersistableAttribute paa = PersistableAttributeFactory.getFactory().getPersistableAttribute(clazz);
/* 128:128 */    List<FieldAttribute> persistAttributes = paa.getFieldAttributes();
/* 129:129 */    String pk = "id";
/* 130:130 */    for (FieldAttribute pa : persistAttributes) {
/* 131:131 */      if (pa.isPrimaryKey())
/* 132:132 */        pk = pa.getName();
/* 133:    */    }
/* 134:134 */    String sql = "delete from " + paa.getTableName() + " where " + pk + "='" + id + "'";
/* 135:135 */    return sql;
/* 136:    */  }
/* 137:    */  
/* 138:    */  public String buildUpdateSQL(Persistable persistable) {
/* 139:139 */    Class<? extends Persistable> clazz = persistable.getClass();
/* 140:140 */    PersistableAttribute paa = PersistableAttributeFactory.getFactory().getPersistableAttribute(clazz);
/* 141:    */    
/* 142:142 */    String updateSQL = null;
/* 143:    */    try {
/* 144:144 */      StringBuffer sqlText = new StringBuffer(200);
/* 145:145 */      sqlText.append("update ").append(paa.getTableName()).append(" set ");
/* 146:146 */      for (FieldAttribute pa : paa.getFieldAttributes())
/* 147:147 */        if ((!pa.isPrimaryKey()) && (!pa.isJoin()))
/* 148:    */        {
/* 150:150 */          Method method = CommonUtil.lookupMethod(clazz, "get" + pa.getAttribute());
/* 151:151 */          if (method == null)
/* 152:152 */            method = CommonUtil.lookupMethod(clazz, "is" + pa.getAttribute());
/* 153:153 */          if (method == null) {
/* 154:154 */            throw new IllegalArgumentException("不存在get 或 is " + pa.getAttribute() + "方法");
/* 155:    */          }
/* 156:156 */          Object _obj = method.invoke(persistable, new Object[0]);
/* 157:157 */          sqlText.append(pa.getName()).append("=");
/* 158:158 */          if (_obj == null) {
/* 159:159 */            sqlText.append("null,");
/* 160:    */          } else
/* 161:161 */            sqlText.append("'").append(convertSpecialChar(_obj.toString())).append("',");
/* 162:    */        }
/* 163:163 */      sqlText.delete(sqlText.length() - 1, sqlText.length());
/* 164:164 */      sqlText.append(" where ");
/* 165:165 */      for (FieldAttribute pa : paa.getFieldAttributes()) {
/* 166:166 */        if (pa.isPrimaryKey()) {
/* 167:167 */          Method method = CommonUtil.lookupMethod(clazz, "get" + pa.getAttribute());
/* 168:168 */          sqlText.append(pa.getName()).append("='");
/* 169:169 */          sqlText.append(method.invoke(persistable, new Object[0])).append("' and ");
/* 170:    */        }
/* 171:    */      }
/* 172:172 */      sqlText.delete(sqlText.length() - 4, sqlText.length());
/* 173:173 */      updateSQL = sqlText.toString();
/* 174:    */    } catch (Exception e) {
/* 175:175 */      logger.error(e);
/* 176:    */    }
/* 177:177 */    return updateSQL;
/* 178:    */  }
/* 179:    */  
/* 182:    */  public synchronized int getNextID(String tableName, String fieldName)
/* 183:    */  {
/* 184:184 */    int id = 1;
/* 185:    */    try {
/* 186:186 */      JdbcTemplate jdbcTemplate = (JdbcTemplate)SysConfigHelper.getBean("defaultJdbcTemplate");
/* 187:187 */      id = ((Integer)jdbcTemplate.queryForObject("select max(" + fieldName + ") from " + tableName, Integer.class)).intValue() + 1;
/* 188:    */    } catch (Exception ex) {
/* 189:189 */      logger.error("error:getNextID#" + tableName);
/* 190:    */    }
/* 191:191 */    return id;
/* 192:    */  }
/* 193:    */  
/* 194:    */  public String buildPaginationSQL(String sql, Pagination page) {
/* 195:195 */    StringBuffer sqlText = new StringBuffer(100);
/* 196:196 */    sqlText.append(sql).append(" limit ").append(page.getPerSize());
/* 197:197 */    sqlText.append(" offset ").append(page.getStartRow() - 1);
/* 198:198 */    return sqlText.toString();
/* 199:    */  }
/* 200:    */  
/* 201:    */  public String buildQuerySQL(Class<? extends Persistable> clazz, Map<String, String> options) {
/* 202:202 */    PersistableAttribute paa = PersistableAttributeFactory.getFactory().getPersistableAttribute(clazz);
/* 203:203 */    StringBuffer sqlText = new StringBuffer(200);
/* 204:204 */    sqlText.append("select ");
/* 205:205 */    for (FieldAttribute pa : paa.getFieldAttributes()) {
/* 206:206 */      sqlText.append(pa.getName()).append(",");
/* 207:    */    }
/* 208:208 */    sqlText.setLength(sqlText.length() - 1);
/* 209:209 */    sqlText.append(" from ").append(paa.getTableName()).append(" where 1=1 ");
/* 210:210 */    for (FieldAttribute pa : paa.getFieldAttributes()) {
/* 211:211 */      String input = (String)options.get(pa.getAttribute());
/* 212:212 */      if (!CommonUtil.isEmpty(input)) {
/* 213:213 */        sqlText.append(" and ").append(pa.getName()).append(" like '%");
/* 214:214 */        sqlText.append(input).append("%'");
/* 215:    */      }
/* 216:    */    }
/* 217:217 */    return sqlText.toString();
/* 218:    */  }
/* 219:    */  
/* 220:    */  public String buildBagSQL(Persistable persistable, int elementId, String logTime) {
/* 221:221 */    Assert.notNull(persistable, "Persistable must be not null");
/* 222:    */    
/* 223:223 */    Class<? extends Persistable> clazz = persistable.getClass();
/* 224:224 */    PersistableAttribute pa = PersistableAttributeFactory.getFactory().getPersistableAttribute(clazz);
/* 225:    */    try {
/* 226:226 */      StringBuffer sqlText = new StringBuffer(200);
/* 227:227 */      StringBuffer valueText = new StringBuffer(100);
/* 228:228 */      sqlText.append("insert into ").append(pa.getTableName()).append("(element_id,log_time,");
/* 229:229 */      valueText.append(elementId).append(",'").append(logTime).append("',");
/* 230:230 */      for (FieldAttribute fa : pa.getFieldAttributes())
/* 231:231 */        if (!fa.isJoin())
/* 232:    */        {
/* 233:233 */          Method method = CommonUtil.lookupMethod(clazz, "get" + fa.getAttribute());
/* 234:234 */          if (method == null)
/* 235:235 */            method = CommonUtil.lookupMethod(clazz, "is" + fa.getAttribute());
/* 236:236 */          if (method == null) {
/* 237:237 */            throw new IllegalArgumentException("不存在get 或 is " + fa.getAttribute() + "方法");
/* 238:    */          }
/* 239:239 */          Object _obj = method.invoke(persistable, new Object[0]);
/* 240:240 */          if (_obj != null) {
/* 241:241 */            String _value = _obj.toString();
/* 242:242 */            sqlText.append(fa.getName()).append(",");
/* 243:243 */            if (CommonUtil.isEmpty(_value)) {
/* 244:244 */              valueText.append("null,");
/* 245:    */            } else
/* 246:246 */              valueText.append("'").append(_value).append("',");
/* 247:    */          }
/* 248:    */        }
/* 249:249 */      if (valueText.length() == 0)
/* 250:250 */        Assert.notNull(sqlText.toString() + "SQL有错误");
/* 251:251 */      sqlText.delete(sqlText.length() - 1, sqlText.length());
/* 252:252 */      valueText.delete(valueText.length() - 1, valueText.length());
/* 253:253 */      sqlText.append(")values(").append(valueText.toString()).append(")");
/* 254:254 */      return sqlText.toString();
/* 255:    */    } catch (Exception e) {
/* 256:256 */      logger.error(e);
/* 257:    */    }
/* 258:258 */    return null;
/* 259:    */  }
/* 260:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.framework.jdbc.MySQLDataExtractor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */