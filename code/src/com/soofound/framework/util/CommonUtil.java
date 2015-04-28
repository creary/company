/*   1:    */package com.soofound.framework.util;
/*   2:    */
/*   3:    */import java.io.FileOutputStream;
/*   4:    */import java.io.InputStream;
/*   5:    */import java.lang.reflect.Method;
/*   6:    */import java.util.Collection;
/*   7:    */import java.util.Enumeration;
/*   8:    */import java.util.HashMap;
/*   9:    */import java.util.List;
/*  10:    */import java.util.Map;
/*  11:    */import javax.servlet.http.HttpServletRequest;
/*  12:    */import net.sf.json.JSONArray;
/*  13:    */import net.sf.json.JsonConfig;
/*  14:    */
/*  18:    */public final class CommonUtil
/*  19:    */{
/*  20:    */  public static boolean isEmpty(String string)
/*  21:    */  {
/*  22: 22 */    return (string == null) || ("".equals(string)) || ("null".equalsIgnoreCase(string));
/*  23:    */  }
/*  24:    */  
/*  25:    */  public static boolean isEmpty(Object[] objs) {
/*  26: 26 */    if ((objs == null) || (objs.length == 0))
/*  27: 27 */      return true;
/*  28: 28 */    Object[] arrayOfObject = objs;int j = objs.length; for (int i = 0; i < j; i++) { Object obj = arrayOfObject[i];
/*  29: 29 */      if (obj == null)
/*  30: 30 */        return true;
/*  31:    */    }
/*  32: 32 */    return false;
/*  33:    */  }
/*  34:    */  
/*  35:    */  public static Map<String, String> getParas(HttpServletRequest request) {
/*  36: 36 */    HashMap<String, String> map = new HashMap();
/*  37: 37 */    for (Enumeration em = request.getParameterNames(); em.hasMoreElements();) {
/*  38: 38 */      String name = (String)em.nextElement();
/*  39: 39 */      map.put(name, request.getParameter(name));
/*  40:    */    }
/*  41: 41 */    return map;
/*  42:    */  }
/*  43:    */  
/*  44:    */  public static boolean isEmpty(Collection<?> collection) {
/*  45: 45 */    if ((collection == null) || (collection.isEmpty()))
/*  46: 46 */      return true;
/*  47: 47 */    return false;
/*  48:    */  }
/*  49:    */  
/*  50:    */  public static boolean isEmpty(Map map) {
/*  51: 51 */    if ((map == null) || (map.isEmpty()))
/*  52: 52 */      return true;
/*  53: 53 */    return false;
/*  54:    */  }
/*  55:    */  
/*  56:    */  public static Object getInstance(String className) {
/*  57:    */    try {
/*  58: 58 */      Class<? extends Object> clazz = Class.forName(className);
/*  59: 59 */      return clazz.newInstance();
/*  60:    */    } catch (Exception e) {
/*  61: 61 */      e.printStackTrace();
/*  62: 62 */      throw new IllegalArgumentException("can not instance class:" + className);
/*  63:    */    }
/*  64:    */  }
/*  65:    */  
/*  66:    */  public static Class getClass(String className) {
/*  67:    */    try {
/*  68: 68 */      return Class.forName(className);
/*  69:    */    } catch (Exception e) {
/*  70: 70 */      e.printStackTrace();
/*  71:    */    }
/*  72: 72 */    return null;
/*  73:    */  }
/*  74:    */  
/*  75:    */  public static Object invokeMethod(Object classInstance, String methodName) {
/*  76: 76 */    if ((classInstance == null) || (methodName == null)) {
/*  77: 77 */      throw new IllegalArgumentException(methodName + " doesn't exist");
/*  78:    */    }
/*  79: 79 */    Method method = lookupMethod(classInstance.getClass(), methodName);
/*  80: 80 */    if (method == null)
/*  81: 81 */      throw new IllegalArgumentException(methodName + " is invoked error");
/*  82:    */    try {
/*  83: 83 */      return method.invoke(classInstance, new Object[0]);
/*  84:    */    } catch (Exception e) {
/*  85: 85 */      throw new IllegalArgumentException(e);
/*  86:    */    }
/*  87:    */  }
/*  88:    */  
/*  89:    */  public static Method lookupMethod(Class clazz, String methodName) {
/*  90: 90 */    for (Method method : clazz.getMethods()) {
/*  91: 91 */      if (method.getName().equalsIgnoreCase(methodName))
/*  92: 92 */        return method;
/*  93:    */    }
/*  94: 94 */    return null;
/*  95:    */  }
/*  96:    */  
/*  97:    */  public static String mapToString(Map<String, String> map) {
/*  98: 98 */    StringBuffer str = new StringBuffer(100);
/*  99: 99 */    for (String temp : map.keySet()) {
/* 100:100 */      str.append(";").append(temp).append("=").append((String)map.get(temp));
/* 101:    */    }
/* 102:102 */    return str.substring(1);
/* 103:    */  }
/* 104:    */  
/* 105:    */  public static String arrayToString(String[] array) {
/* 106:106 */    StringBuffer str = new StringBuffer(100);
/* 107:107 */    String[] arrayOfString = array;int j = array.length; for (int i = 0; i < j; i++) { String temp = arrayOfString[i];
/* 108:108 */      str.append(",").append(temp); }
/* 109:109 */    return str.substring(1);
/* 110:    */  }
/* 111:    */  
/* 112:    */  public static String arrayToQuotString(String[] array) {
/* 113:113 */    if (!isEmpty(array)) {
/* 114:114 */      StringBuffer str = new StringBuffer(100);
/* 115:115 */      String[] arrayOfString = array;int j = array.length; for (int i = 0; i < j; i++) { String temp = arrayOfString[i];
/* 116:116 */        str.append(",'").append(temp).append("'"); }
/* 117:117 */      return str.substring(1);
/* 118:    */    }
/* 119:119 */    return "";
/* 120:    */  }
/* 121:    */  
/* 122:    */  public static String listToQuotString(List<String> list) {
/* 123:123 */    if (!isEmpty(list)) {
/* 124:124 */      StringBuffer str = new StringBuffer(100);
/* 125:125 */      for (String temp : list)
/* 126:126 */        str.append(",'").append(temp).append("'");
/* 127:127 */      return str.substring(1);
/* 128:    */    }
/* 129:129 */    return "";
/* 130:    */  }
/* 131:    */  
/* 132:    */  public static String objectToJson(Object obj) {
/* 133:133 */    JSONArray json = JSONArray.fromObject(obj, new JsonConfig());
/* 134:134 */    return json.toString();
/* 135:    */  }
/* 136:    */  
/* 137:    */  public static String[] toArray(List<String> sqls) {
/* 138:138 */    String[] arrsqls = new String[sqls.size()];
/* 139:139 */    sqls.toArray(arrsqls);
/* 140:140 */    return arrsqls;
/* 141:    */  }
/* 142:    */  
/* 143:    */  public static void copyFile(InputStream in, String fileName) {
/* 144:144 */    FileOutputStream fs = null;
/* 145:    */    try {
/* 146:146 */      fs = new FileOutputStream(fileName);
/* 147:147 */      byte[] buffer = new byte[1048576];
/* 148:148 */      int byteread = 0;
/* 149:149 */      while ((byteread = in.read(buffer)) != -1) {
/* 150:150 */        fs.write(buffer, 0, byteread);
/* 151:151 */        fs.flush();
/* 152:    */      }
/* 153:    */    }
/* 154:    */    catch (Exception localException)
/* 155:    */    {
/* 156:    */      try {
/* 157:157 */        fs.close();
/* 158:158 */        in.close();
/* 159:    */      }
/* 160:    */      catch (Exception localException1) {}
/* 161:    */    }
/* 162:    */    finally
/* 163:    */    {
/* 164:    */      try
/* 165:    */      {
/* 166:157 */        fs.close();
/* 167:158 */        in.close();
/* 168:    */      }
/* 169:    */      catch (Exception localException2) {}
/* 170:    */    }
/* 171:    */  }
/* 172:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.framework.util.CommonUtil
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */