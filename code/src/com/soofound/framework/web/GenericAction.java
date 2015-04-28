/*   1:    */package com.soofound.framework.web;
/*   2:    */
/*   3:    */import com.soofound.admin.bean.UserDao;
/*   4:    */import com.soofound.admin.bean.UserDto;
/*   5:    */import com.soofound.framework.annotation.FieldAttribute;
/*   6:    */import com.soofound.framework.annotation.PersistableAttribute;
/*   7:    */import com.soofound.framework.annotation.PersistableAttributeFactory;
/*   8:    */import com.soofound.framework.jdbc.Persistable;
/*   9:    */import com.soofound.framework.util.CommonUtil;
/*  10:    */import com.soofound.framework.util.DateUtil;
/*  11:    */import com.soofound.framework.util.SysConfigHelper;
/*  12:    */import java.io.FileInputStream;
/*  13:    */import java.io.InputStream;
/*  14:    */import java.io.OutputStream;
/*  15:    */import java.util.Enumeration;
/*  16:    */import java.util.HashMap;
/*  17:    */import java.util.Iterator;
/*  18:    */import java.util.List;
/*  19:    */import java.util.Set;
/*  20:    */import javax.servlet.http.HttpServletResponse;
/*  21:    */import javax.servlet.http.HttpSession;
/*  22:    */import org.springframework.jdbc.core.JdbcTemplate;
/*  23:    */import org.springframework.security.core.Authentication;
/*  24:    */import org.springframework.security.core.context.SecurityContext;
/*  25:    */import org.springframework.security.core.context.SecurityContextHolder;
/*  26:    */import org.springframework.security.core.userdetails.User;
/*  27:    */
/*  28:    */public abstract class GenericAction
/*  29:    */{
/*  30:    */  protected static final String PAGE_HINT = "/common/hint.jsp";
/*  31:    */  protected static final String CONTENT_TYPE_XML = "text/xml;charset=UTF-8";
/*  32:    */  protected static final int PER_PAGE_RECORD = 100;
/*  33:    */  
/*  34:    */  public int getCurrentPage(javax.servlet.http.HttpServletRequest request)
/*  35:    */  {
/*  36: 36 */    String jp = request.getParameter("jp");
/*  37: 37 */    int curPage = 1;
/*  38: 38 */    if (jp != null)
/*  39: 39 */      curPage = Integer.parseInt(jp);
/*  40: 40 */    return curPage;
/*  41:    */  }
/*  42:    */  
/*  43:    */  public int getPerPageRowTotal(javax.servlet.http.HttpServletRequest request) {
/*  44: 44 */    String perPageStr = request.getParameter("perPage");
/*  45: 45 */    int pp = 100;
/*  46: 46 */    if (perPageStr != null)
/*  47: 47 */      pp = Integer.parseInt(perPageStr);
/*  48: 48 */    return pp;
/*  49:    */  }
/*  50:    */  
/*  51:    */  protected java.util.Map<String, String> getParas(javax.servlet.http.HttpServletRequest request) {
/*  52: 52 */    java.util.Map<String, String> paras = new HashMap();
/*  53: 53 */    for (Enumeration em = request.getParameterNames(); em.hasMoreElements();) {
/*  54: 54 */      String para = (String)em.nextElement();
/*  55: 55 */      String value = request.getParameter(para);
/*  56: 56 */      if (!CommonUtil.isEmpty(value))
/*  57: 57 */        paras.put(para, value);
/*  58:    */    }
/*  59: 59 */    return paras;
/*  60:    */  }
/*  61:    */  
/*  62:    */  protected Persistable extractForm(Class<? extends Persistable> clazz, javax.servlet.http.HttpServletRequest request) {
/*  63: 63 */    List<FieldAttribute> persistAttributes = PersistableAttributeFactory.getFactory().getPersistableAttribute(clazz).getFieldAttributes();
/*  64: 64 */    Persistable dto = null;
/*  65:    */    try {
/*  66: 66 */      dto = (Persistable)clazz.newInstance();
/*  67: 67 */      for (FieldAttribute pa : persistAttributes) {
/*  68: 68 */        String paraValue = request.getParameter(pa.getAttribute());
/*  69: 69 */        java.lang.reflect.Method method = CommonUtil.lookupMethod(clazz, "set" + pa.getAttribute());
/*  70: 70 */        if ((paraValue != null) && (method != null))
/*  71:    */        {
/*  72: 72 */          String paraType = method.getParameterTypes()[0].getName();
/*  73: 73 */          if ((paraType.equals("int")) || (paraType.equals("java.lang.Integer"))) {
/*  74: 74 */            method.invoke(dto, new Object[] { Integer.valueOf(Integer.parseInt(paraValue)) });
/*  75: 75 */          } else if ((paraType.equals("char")) || (paraType.equals("java.lang.Character"))) {
/*  76: 76 */            method.invoke(dto, new Object[] { Character.valueOf(paraValue.toCharArray()[0]) });
/*  77: 77 */          } else if ((paraType.equals("short")) || (paraType.equals("java.lang.Short"))) {
/*  78: 78 */            method.invoke(dto, new Object[] { Short.valueOf(Short.parseShort(paraValue)) });
/*  79: 79 */          } else if ((paraType.equals("long")) || (paraType.equals("java.lang.Long"))) {
/*  80: 80 */            method.invoke(dto, new Object[] { Long.valueOf(Long.parseLong(paraValue)) });
/*  81: 81 */          } else if ((paraType.equals("float")) || (paraType.equals("java.lang.Float"))) {
/*  82: 82 */            method.invoke(dto, new Object[] { Float.valueOf(Float.parseFloat(paraValue)) });
/*  83: 83 */          } else if ((paraType.equals("double")) || (paraType.equals("java.lang.Double"))) {
/*  84: 84 */            method.invoke(dto, new Object[] { Double.valueOf(Double.parseDouble(paraValue)) });
/*  85: 85 */          } else if ((paraType.equals("boolean")) || (paraType.equals("java.lang.Boolean"))) {
/*  86: 86 */            if (("1".equals(paraValue)) || ("true".equals(paraValue))) {
/*  87: 87 */              method.invoke(dto, new Object[] { Boolean.TRUE });
/*  88:    */            } else
/*  89: 89 */              method.invoke(dto, new Object[] { Boolean.FALSE });
/*  90: 90 */          } else if ((paraType.equals("String")) || (paraType.equals("java.lang.String")))
/*  91: 91 */            method.invoke(dto, new Object[] { paraValue });
/*  92:    */        }
/*  93:    */      }
/*  94: 94 */    } catch (Exception e) { e.printStackTrace();
/*  95:    */    }
/*  96: 96 */    return dto;
/*  97:    */  }
/*  98:    */  
/*  99:    */  protected void logOperation(javax.servlet.http.HttpServletRequest request, String operation) {
/* 100:100 */    UserDto user = getCurrentUser(request);
/* 101:101 */    if (user != null) {
/* 102:102 */      StringBuffer sqlText = new StringBuffer(100);
/* 103:103 */      sqlText.append("insert into admin_log(id,username,fullname,operation,ip,location,log_time)values('");
/* 104:104 */      sqlText.append(DateUtil.getCurrentTimeAsID()).append("','").append(user.getUsername());
/* 105:105 */      sqlText.append("','").append(user.getFullname()).append("','").append(operation);
/* 106:106 */      sqlText.append("','").append(request.getRemoteAddr()).append("','','").append(DateUtil.getCurrentDateTime()).append("')");
/* 107:107 */      JdbcTemplate jdbc = (JdbcTemplate)SysConfigHelper.getBean("defaultJdbcTemplate");
/* 108:108 */      jdbc.update(sqlText.toString());
/* 109:    */    }
/* 110:    */  }
/* 111:    */  
/* 112:    */  protected UserDto getCurrentUser(javax.servlet.http.HttpServletRequest request) {
/* 113:113 */    HttpSession session = request.getSession();
/* 114:114 */    if ((session.getAttribute("CURRENT_USER") == null) && 
/* 115:115 */      (SecurityContextHolder.getContext().getAuthentication() != null)) {
/* 116:116 */      User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
/* 117:117 */      if (user != null) {
/* 118:118 */        UserDao dao = new UserDao();
/* 119:119 */        UserDto dto = dao.findByID(user.getUsername());
/* 120:120 */        dto.setIpAddress(request.getRemoteAddr());
/* 121:121 */        session.setAttribute("CURRENT_USER", dto);
/* 122:122 */        return dto;
/* 123:    */      }
/* 124:    */    }
/* 125:125 */    return (UserDto)session.getAttribute("CURRENT_USER");
/* 126:    */  }
/* 127:    */  
/* 128:    */  protected void transfer(javax.servlet.http.HttpServletRequest request, java.util.Map serviceResultMap) {
/* 129:129 */    Iterator keys = serviceResultMap.keySet().iterator();
/* 130:130 */    while (keys.hasNext()) {
/* 131:131 */      String key = (String)keys.next();
/* 132:132 */      request.setAttribute(key, serviceResultMap.get(key));
/* 133:    */    }
/* 134:134 */    serviceResultMap = null;
/* 135:    */  }
/* 136:    */  
/* 137:    */  protected java.util.Map<String, Object> getSucceedResult(String msg) {
/* 138:138 */    java.util.Map<String, Object> result = new HashMap();
/* 139:139 */    result.put("status", Integer.valueOf(1));
/* 140:140 */    if (msg == null)
/* 141:141 */      msg = "操作成功";
/* 142:142 */    result.put("msg", msg);
/* 143:143 */    return result;
/* 144:    */  }
/* 145:    */  
/* 146:    */  protected java.util.Map<String, Object> getFailedResult(String msgCode) {
/* 147:147 */    java.util.Map<String, Object> result = new HashMap();
/* 148:148 */    result.put("status", Integer.valueOf(2));
/* 149:149 */    if (msgCode == null)
/* 150:150 */      msgCode = "操作失败";
/* 151:151 */    result.put("msg", msgCode);
/* 152:152 */    return result;
/* 153:    */  }
/* 154:    */  
/* 155:    */  protected void downloadFile(HttpServletResponse response, String srcFile) {
/* 156:156 */    InputStream is = null;
/* 157:157 */    OutputStream os = null;
/* 158:    */    try {
/* 159:159 */      String filename = null;
/* 160:160 */      int loc1 = srcFile.lastIndexOf("/");
/* 161:161 */      int loc2 = srcFile.lastIndexOf("\\");
/* 162:162 */      if (loc1 > loc2)
/* 163:163 */        loc2 = loc1;
/* 164:164 */      filename = srcFile.substring(loc2 + 1);
/* 165:165 */      response.setContentType("application/x-msdownload;charset=UTF-8");
/* 166:166 */      response.setHeader("Content-Disposition", "attachment; filename=" + filename);
/* 167:167 */      os = response.getOutputStream();
/* 168:168 */      is = new FileInputStream(srcFile);
/* 169:169 */      byte[] b = new byte[1024];
/* 170:170 */      while (is.read(b) != -1)
/* 171:171 */        os.write(b);
/* 172:172 */      b = (byte[])null;
/* 173:    */    } catch (Exception e) {
/* 174:174 */      e.printStackTrace();
/* 175:    */      try
/* 176:    */      {
/* 177:177 */        os.flush();
/* 178:178 */        os.close();
/* 179:179 */        is.close();
/* 180:    */      }
/* 181:    */      catch (Exception localException1) {}
/* 182:    */    }
/* 183:    */    finally
/* 184:    */    {
/* 185:    */      try
/* 186:    */      {
/* 187:177 */        os.flush();
/* 188:178 */        os.close();
/* 189:179 */        is.close();
/* 190:    */      }
/* 191:    */      catch (Exception localException2) {}
/* 192:    */    }
/* 193:    */  }
/* 194:    */  
/* 195:    */  protected java.util.Map<String, Object> bornData(Object list) {
/* 196:186 */    java.util.Map<String, Object> result = new HashMap();
/* 197:187 */    result.put("status", Integer.valueOf(1));
/* 198:188 */    result.put("data", list);
/* 199:189 */    return result;
/* 200:    */  }
/* 201:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.framework.web.GenericAction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */