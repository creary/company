/*  1:   */package com.soofound.framework.jdbc;
/*  2:   */
/*  3:   */import com.soofound.framework.annotation.FieldAttribute;
/*  4:   */import com.soofound.framework.annotation.PersistableAttribute;
/*  5:   */import com.soofound.framework.annotation.PersistableAttributeFactory;
/*  6:   */import com.soofound.framework.util.CommonUtil;
/*  7:   */import java.lang.reflect.Method;
/*  8:   */import java.sql.ResultSet;
/*  9:   */import java.sql.ResultSetMetaData;
/* 10:   */import java.sql.SQLException;
/* 11:   */import java.util.List;
/* 12:   */import org.apache.log4j.Logger;
/* 13:   */import org.springframework.jdbc.core.RowMapper;
/* 14:   */
/* 15:   */public final class ResultRowMapper<T extends Persistable>
/* 16:   */  implements RowMapper<T>
/* 17:   */{
/* 18:18 */  private static Logger logger = Logger.getLogger(ResultRowMapper.class);
/* 19:   */  private Class<T> clazz;
/* 20:   */  
/* 21:   */  public ResultRowMapper(Class<T> clazz) {
/* 22:22 */    this.clazz = clazz;
/* 23:   */  }
/* 24:   */  
/* 27:   */  public T mapRow(ResultSet rs, int rowNum)
/* 28:   */    throws SQLException
/* 29:   */  {
/* 30:30 */    List<FieldAttribute> persistAttributes = PersistableAttributeFactory.getFactory().getPersistableAttribute(this.clazz).getFieldAttributes();
/* 31:31 */    Persistable dto = null;
/* 32:   */    try {
/* 33:33 */      dto = (Persistable)this.clazz.newInstance();
/* 34:34 */      for (FieldAttribute pa : persistAttributes)
/* 35:35 */        if (isColumnExist(rs, pa.getName()))
/* 36:   */        {
/* 37:37 */          Method method = CommonUtil.lookupMethod(this.clazz, "set" + pa.getAttribute());
/* 38:38 */          if (method == null) {
/* 39:39 */            throw new IllegalArgumentException(this.clazz.getName() + "不存在方法set" + pa.getAttribute());
/* 40:   */          }
/* 41:41 */          String paraType = method.getParameterTypes()[0].getName();
/* 42:42 */          if ((paraType.equals("int")) || (paraType.equals("java.lang.Integer"))) {
/* 43:43 */            method.invoke(dto, new Object[] { Integer.valueOf(rs.getInt(pa.getName())) });
/* 44:44 */          } else if ((paraType.equals("short")) || (paraType.equals("java.lang.Short"))) {
/* 45:45 */            method.invoke(dto, new Object[] { Short.valueOf(rs.getShort(pa.getName())) });
/* 46:46 */          } else if ((paraType.equals("long")) || (paraType.equals("java.lang.Long"))) {
/* 47:47 */            method.invoke(dto, new Object[] { Long.valueOf(rs.getLong(pa.getName())) });
/* 48:48 */          } else if ((paraType.equals("float")) || (paraType.equals("java.lang.Float"))) {
/* 49:49 */            method.invoke(dto, new Object[] { Float.valueOf(rs.getFloat(pa.getName())) });
/* 50:50 */          } else if ((paraType.equals("double")) || (paraType.equals("java.lang.Double"))) {
/* 51:51 */            method.invoke(dto, new Object[] { Double.valueOf(rs.getDouble(pa.getName())) });
/* 52:52 */          } else if ((paraType.equals("char")) || (paraType.equals("java.lang.Character"))) {
/* 53:53 */            String _value = rs.getString(pa.getName());
/* 54:54 */            if (_value == null) {
/* 55:55 */              method.invoke(dto, new Object[] { Character.valueOf(' ') });
/* 56:   */            } else
/* 57:57 */              method.invoke(dto, new Object[] { Character.valueOf(_value.charAt(0)) });
/* 58:58 */          } else if ((paraType.equals("boolean")) || (paraType.equals("java.lang.Boolean"))) {
/* 59:59 */            int _value = rs.getInt(pa.getName());
/* 60:60 */            method.invoke(dto, new Object[] { Boolean.valueOf(_value == 0 ? 0 : true) });
/* 61:61 */          } else if (paraType.equals("java.lang.String")) {
/* 62:62 */            String _value = rs.getString(pa.getName());
/* 63:63 */            if (_value == null)
/* 64:64 */              _value = "";
/* 65:65 */            method.invoke(dto, new Object[] { _value });
/* 66:   */          } else {
/* 67:67 */            logger.debug("unknownType[" + pa.getName() + "]=" + paraType + ",rs=" + rs.getObject(pa.getName()).getClass());
/* 68:   */          }
/* 69:   */        }
/* 70:70 */      return dto;
/* 71:   */    } catch (Exception e) {
/* 72:72 */      logger.error(e);
/* 73:73 */      e.printStackTrace();
/* 74:   */    }
/* 75:75 */    return null;
/* 76:   */  }
/* 77:   */  
/* 78:   */  private static boolean isColumnExist(ResultSet rs, String columnName) throws SQLException {
/* 79:79 */    ResultSetMetaData rsm = rs.getMetaData();
/* 80:80 */    for (int i = 1; i <= rsm.getColumnCount(); i++) {
/* 81:81 */      if (rsm.getColumnLabel(i).equalsIgnoreCase(columnName))
/* 82:82 */        return true;
/* 83:   */    }
/* 84:84 */    return false;
/* 85:   */  }
/* 86:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.framework.jdbc.ResultRowMapper
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */