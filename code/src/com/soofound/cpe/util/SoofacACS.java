/*   1:    */package com.soofound.cpe.util;
/*   2:    */
/*   3:    */import com.soofound.cpe.dao.HostDao;
/*   4:    */import com.soofound.framework.util.SimpleXMLUtil;
/*   5:    */import com.soofound.framework.util.SysConfigHelper;
/*   6:    */import java.util.ArrayList;
/*   7:    */import java.util.HashMap;
/*   8:    */import java.util.List;
/*   9:    */import java.util.Map;
/*  10:    */import java.util.concurrent.CopyOnWriteArrayList;
/*  11:    */import java.util.concurrent.atomic.AtomicInteger;
/*  12:    */import org.jdom2.Document;
/*  13:    */import org.jdom2.Element;
/*  14:    */import org.springframework.beans.factory.InitializingBean;
/*  15:    */import org.springframework.util.StringUtils;
/*  16:    */
/*  17:    */public class SoofacACS
/*  18:    */  implements InitializingBean
/*  19:    */{
/*  20:    */  public static String PATH_FIRMWARE;
/*  21:    */  public static String PATH_FULL_FIRMWARE;
/*  22:    */  private Map<String, Object> cpeProperties;
/*  23:    */  private List<String> customSQLs;
/*  24:    */  private String accessControl;
/*  25:    */  private String acsURL;
/*  26:    */  private String rosMac;
/*  27:    */  private String realm;
/*  28:    */  private String username;
/*  29:    */  private String password;
/*  30:    */  private String mysqlBackupCMD;
/*  31:    */  private AtomicInteger hostId;
/*  32:    */  private List<String> iphoneURLs;
/*  33:    */  
/*  34:    */  public void afterPropertiesSet() throws Exception
/*  35:    */  {
/*  36: 36 */    PATH_FIRMWARE = "/acs/wesite/";
/*  37: 37 */    PATH_FULL_FIRMWARE = SysConfigHelper.getAttribute("sysPath") + PATH_FIRMWARE;
/*  38:    */    
/*  39: 39 */    HostDao hdao = new HostDao();
/*  40: 40 */    this.hostId = new AtomicInteger(hdao.getNextID());
/*  41: 41 */    this.iphoneURLs = new CopyOnWriteArrayList();
/*  42: 42 */    Document doc = SimpleXMLUtil.file2Doc(SysConfigHelper.getAttribute("configPath") + "iPhone.xml");
/*  43: 43 */    List<Element> eles = doc.getRootElement().getChildren("gateway");
/*  44: 44 */    for (Element ele : eles) {
/*  45: 45 */      this.iphoneURLs.add(ele.getText());
/*  46:    */    }
/*  47:    */  }
/*  48:    */  
/*  49:    */  public boolean isIPhoneURL(String url) {
/*  50: 50 */    if (url != null) {
/*  51: 51 */      for (String ipurl : this.iphoneURLs) {
/*  52: 52 */        if (url.indexOf(ipurl) >= 0)
/*  53: 53 */          return true;
/*  54:    */      }
/*  55:    */    }
/*  56: 56 */    return false;
/*  57:    */  }
/*  58:    */  
/*  59:    */  public int getHostID() {
/*  60: 60 */    return this.hostId.getAndIncrement();
/*  61:    */  }
/*  62:    */  
/*  63:    */  public void setAcsURL(String acsURL) {
/*  64: 64 */    this.acsURL = acsURL;
/*  65:    */  }
/*  66:    */  
/*  67:    */  public String getAcsURL() {
/*  68: 68 */    return this.acsURL;
/*  69:    */  }
/*  70:    */  
/*  71:    */  public String getAccessControl() {
/*  72: 72 */    return this.accessControl;
/*  73:    */  }
/*  74:    */  
/*  75:    */  public void setAccessControl(String accessControl) {
/*  76: 76 */    this.accessControl = accessControl;
/*  77:    */  }
/*  78:    */  
/*  79:    */  public void setCpeProperties(Map<String, String> strs) {
/*  80: 80 */    this.cpeProperties = new HashMap(strs.size());
/*  81: 81 */    for (String str : strs.keySet()) {
/*  82: 82 */      List<String> list = new ArrayList();
/*  83: 83 */      Map<String, String> map = new HashMap();
/*  84: 84 */      String[] vals = ((String)strs.get(str)).split(";");
/*  85: 85 */      for (String val : vals) {
/*  86: 86 */        String[] tempval = val.split(":");
/*  87: 87 */        if (tempval.length == 1) {
/*  88: 88 */          list.add(val);
/*  89:    */        } else {
/*  90: 90 */          map.put(tempval[0], tempval[1]);
/*  91: 91 */          this.cpeProperties.put(str, map);
/*  92:    */        }
/*  93:    */      }
/*  94: 94 */      if (list.isEmpty()) {
/*  95: 95 */        this.cpeProperties.put(str, map);
/*  96:    */      } else
/*  97: 97 */        this.cpeProperties.put(str, list);
/*  98:    */    }
/*  99:    */  }
/* 100:    */  
/* 101:    */  public String getCpePropertySelectBox(String propName, String value, String boxName) {
/* 102:102 */    if (!this.cpeProperties.containsKey(propName)) {
/* 103:103 */      return null;
/* 104:    */    }
/* 105:105 */    StringBuffer box = new StringBuffer(100);
/* 106:106 */    box.append("<select style=\"width:120px;\" class=\"s\" name=\"").append(boxName).append("\">");
/* 107:107 */    Object obj = this.cpeProperties.get(propName);
/* 108:108 */    if ((obj instanceof List)) {
/* 109:109 */      ArrayList<String> cates = (ArrayList)obj;
/* 110:110 */      for (String keys : cates) {
/* 111:111 */        box.append("<option value=\"").append(keys);
/* 112:112 */        if (value.equals(keys)) {
/* 113:113 */          box.append("\" selected>");
/* 114:    */        } else
/* 115:115 */          box.append("\">");
/* 116:116 */        box.append(keys).append("</option>");
/* 117:    */      }
/* 118:    */    } else {
/* 119:119 */      HashMap<String, String> cates = (HashMap)obj;
/* 120:120 */      for (String keys : cates.keySet()) {
/* 121:121 */        box.append("<option value=\"").append(keys);
/* 122:122 */        if (value.equals(keys)) {
/* 123:123 */          box.append("\" selected>");
/* 124:    */        } else
/* 125:125 */          box.append("\">");
/* 126:126 */        box.append((String)cates.get(keys)).append("</option>");
/* 127:    */      }
/* 128:    */    }
/* 129:129 */    box.append("</select>");
/* 130:130 */    return box.toString();
/* 131:    */  }
/* 132:    */  
/* 133:    */  public String getCpePropertyTextBox(String boxName, String value, int rw) {
/* 134:134 */    String temp = null;
/* 135:135 */    if ((StringUtils.isEmpty(value)) || ("N/A".equals(value))) {
/* 136:136 */      temp = "";
/* 137:    */    } else
/* 138:138 */      temp = value;
/* 139:139 */    StringBuffer box = new StringBuffer(100);
/* 140:140 */    box.append("<input class=\"field\" style=\"width:120px\" name=\"").append(boxName).append("\" ");
/* 141:141 */    box.append("value=\"").append(temp).append("\" />");
/* 142:142 */    return box.toString();
/* 143:    */  }
/* 144:    */  
/* 145:    */  public String getRosMac() {
/* 146:146 */    return this.rosMac;
/* 147:    */  }
/* 148:    */  
/* 149:    */  public void setRosMac(String rosMac) {
/* 150:150 */    this.rosMac = rosMac;
/* 151:    */  }
/* 152:    */  
/* 153:    */  public String getUsername() {
/* 154:154 */    return this.username;
/* 155:    */  }
/* 156:    */  
/* 157:    */  public void setUsername(String username) {
/* 158:158 */    this.username = username;
/* 159:    */  }
/* 160:    */  
/* 161:    */  public String getPassword() {
/* 162:162 */    return this.password;
/* 163:    */  }
/* 164:    */  
/* 165:    */  public void setPassword(String password) {
/* 166:166 */    this.password = password;
/* 167:    */  }
/* 168:    */  
/* 169:    */  public String getMysqlBackupCMD() {
/* 170:170 */    return this.mysqlBackupCMD;
/* 171:    */  }
/* 172:    */  
/* 173:    */  public void setMysqlBackupCMD(String mysqlBackupCMD) {
/* 174:174 */    this.mysqlBackupCMD = mysqlBackupCMD;
/* 175:    */  }
/* 176:    */  
/* 177:    */  public void setCustomSQLs(List<String> customSQLs) {
/* 178:178 */    this.customSQLs = customSQLs;
/* 179:    */  }
/* 180:    */  
/* 181:    */  public List<String> getCustomSQLs() {
/* 182:182 */    return this.customSQLs;
/* 183:    */  }
/* 184:    */  
/* 185:    */  public void setRealm(String realm) {
/* 186:186 */    this.realm = realm;
/* 187:    */  }
/* 188:    */  
/* 189:    */  public String getRealm() {
/* 190:190 */    return this.realm;
/* 191:    */  }
/* 192:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.cpe.util.SoofacACS
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */