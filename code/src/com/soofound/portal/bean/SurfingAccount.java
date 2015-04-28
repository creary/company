/*   1:    */package com.soofound.portal.bean;
/*   2:    */
/*   3:    */import com.soofound.framework.annotation.PersistableAnnotation;
/*   4:    */import com.soofound.framework.jdbc.Persistable;
/*   5:    */import com.soofound.framework.util.SysConfigHelper;
/*   6:    */import org.springframework.util.StringUtils;
/*   7:    */
/*   8:    */@PersistableAnnotation(associate="surfing_account")
/*   9:    */public class SurfingAccount
/*  10:    */  implements Persistable
/*  11:    */{
/*  12:    */  @PersistableAnnotation(associate="id", primaryKey=true)
/*  13:    */  private int id;
/*  14:    */  @PersistableAnnotation(associate="username")
/*  15:    */  private String username;
/*  16:    */  @PersistableAnnotation(associate="open_id")
/*  17:    */  private String openId;
/*  18:    */  @PersistableAnnotation(associate="password")
/*  19:    */  private String password;
/*  20:    */  @PersistableAnnotation(associate="branch_id")
/*  21:    */  private String branchId;
/*  22:    */  @PersistableAnnotation(associate="branch", join=true)
/*  23:    */  private String branch;
/*  24:    */  @PersistableAnnotation(associate="create_time")
/*  25:    */  private String createTime;
/*  26:    */  @PersistableAnnotation(associate="flag")
/*  27:    */  private String flag;
/*  28:    */  @PersistableAnnotation(associate="nickname")
/*  29:    */  private String nickname;
/*  30:    */  @PersistableAnnotation(associate="online")
/*  31:    */  private int online;
/*  32:    */  @PersistableAnnotation(associate="mac")
/*  33:    */  private String mac;
/*  34:    */  @PersistableAnnotation(associate="times")
/*  35:    */  private int times;
/*  36:    */  @PersistableAnnotation(associate="up_speed")
/*  37:    */  private int upSpeed;
/*  38:    */  @PersistableAnnotation(associate="down_speed")
/*  39:    */  private int downSpeed;
/*  40:    */  private String smsstr;
/*  41:    */  private boolean updateSpeed;
/*  42:    */  private long lastUpdateTime;
/*  43:    */  public static final String AUTH_TYPE_WECHAT = "wechat";
/*  44:    */  public static final String AUTH_TYPE_MOBILE = "mobile";
/*  45:    */  public static final String AUTH_TYPE_DB = "db";
/*  46:    */  
/*  47:    */  public int getId()
/*  48:    */  {
/*  49: 49 */    return this.id;
/*  50:    */  }
/*  51:    */  
/*  52:    */  public void setId(int id) {
/*  53: 53 */    this.id = id;
/*  54:    */  }
/*  55:    */  
/*  56:    */  public String getUsername() {
/*  57: 57 */    return this.username;
/*  58:    */  }
/*  59:    */  
/*  60:    */  public void setUsername(String username) {
/*  61: 61 */    this.username = username;
/*  62:    */  }
/*  63:    */  
/*  64:    */  public String getPassword() {
/*  65: 65 */    return this.password;
/*  66:    */  }
/*  67:    */  
/*  68:    */  public void setPassword(String password) {
/*  69: 69 */    this.password = password;
/*  70:    */  }
/*  71:    */  
/*  72:    */  public String getBranchId() {
/*  73: 73 */    return this.branchId;
/*  74:    */  }
/*  75:    */  
/*  76:    */  public void setBranchId(String branchId) {
/*  77: 77 */    this.branchId = branchId;
/*  78:    */  }
/*  79:    */  
/*  80:    */  public String getBranch() {
/*  81: 81 */    return this.branch;
/*  82:    */  }
/*  83:    */  
/*  84:    */  public void setBranch(String branch) {
/*  85: 85 */    this.branch = branch;
/*  86:    */  }
/*  87:    */  
/*  88:    */  public String getCreateTime() {
/*  89: 89 */    return this.createTime;
/*  90:    */  }
/*  91:    */  
/*  92:    */  public void setCreateTime(String createTime) {
/*  93: 93 */    this.createTime = createTime;
/*  94:    */  }
/*  95:    */  
/*  96:    */  public String getFlag() {
/*  97: 97 */    return this.flag;
/*  98:    */  }
/*  99:    */  
/* 100:    */  public void setFlag(String flag) {
/* 101:101 */    this.flag = flag;
/* 102:    */  }
/* 103:    */  
/* 104:    */  public String getNickname() {
/* 105:105 */    return this.nickname;
/* 106:    */  }
/* 107:    */  
/* 108:    */  public void setNickname(String nickname) {
/* 109:109 */    this.nickname = nickname;
/* 110:    */  }
/* 111:    */  
/* 112:    */  public int getOnline() {
/* 113:113 */    return this.online;
/* 114:    */  }
/* 115:    */  
/* 116:    */  public void setOnline(int online) {
/* 117:117 */    this.online = online;
/* 118:    */  }
/* 119:    */  
/* 120:    */  public long getLastUpdateTime() {
/* 121:121 */    return this.lastUpdateTime;
/* 122:    */  }
/* 123:    */  
/* 124:    */  public void setLastUpdateTime(long lastUpdateTime) {
/* 125:125 */    this.lastUpdateTime = lastUpdateTime;
/* 126:    */  }
/* 127:    */  
/* 128:    */  public String getOpenId() {
/* 129:129 */    return this.openId;
/* 130:    */  }
/* 131:    */  
/* 132:    */  public void setOpenId(String openId) {
/* 133:133 */    this.openId = openId;
/* 134:    */  }
/* 135:    */  
/* 136:    */  public String getMac() {
/* 137:137 */    return this.mac;
/* 138:    */  }
/* 139:    */  
/* 140:    */  public void setMac(String mac) {
/* 141:141 */    this.mac = mac;
/* 142:    */  }
/* 143:    */  
/* 144:    */  public int getTimes() {
/* 145:145 */    return this.times;
/* 146:    */  }
/* 147:    */  
/* 148:    */  public void setTimes(int times) {
/* 149:149 */    this.times = times;
/* 150:    */  }
/* 151:    */  
/* 152:    */  public int getUpSpeed() {
/* 153:153 */    return this.upSpeed;
/* 154:    */  }
/* 155:    */  
/* 156:    */  public void setUpSpeed(int upSpeed) {
/* 157:157 */    this.upSpeed = upSpeed;
/* 158:    */  }
/* 159:    */  
/* 160:    */  public int getDownSpeed() {
/* 161:161 */    return this.downSpeed;
/* 162:    */  }
/* 163:    */  
/* 164:    */  public void setDownSpeed(int downSpeed) {
/* 165:165 */    this.downSpeed = downSpeed;
/* 166:    */  }
/* 167:    */  
/* 168:    */  public boolean isUpdateSpeed() {
/* 169:169 */    return this.updateSpeed;
/* 170:    */  }
/* 171:    */  
/* 172:    */  public void setUpdateSpeed(boolean updateSpeed) {
/* 173:173 */    this.updateSpeed = updateSpeed;
/* 174:    */  }
/* 175:    */  
/* 176:    */  public String getSmsstr() {
/* 177:177 */    return this.smsstr;
/* 178:    */  }
/* 179:    */  
/* 180:    */  public void setSmsstr(String smsstr) {
/* 181:181 */    this.smsstr = smsstr;
/* 182:    */  }
/* 183:    */  
/* 184:    */  public String getUsernameText() {
/* 185:185 */    if (("wechat".equals(this.flag)) && (StringUtils.hasText(this.nickname)))
/* 186:186 */      return this.username + "(" + this.nickname + ")";
/* 187:187 */    return this.username;
/* 188:    */  }
/* 189:    */  
/* 190:    */  public String getFlagText() {
/* 191:191 */    if ("wechat".equals(this.flag))
/* 192:192 */      return "微信";
/* 193:193 */    if ("mobile".equals(this.flag))
/* 194:194 */      return "手机";
/* 195:195 */    if ("free".equals(this.flag))
/* 196:196 */      return "免认证";
/* 197:197 */    if ("thirdParty".equals(this.flag))
/* 198:198 */      return "第三方";
/* 199:199 */    return "会员帐号";
/* 200:    */  }
/* 201:    */  
/* 202:    */  public String getStatusImage() {
/* 203:203 */    StringBuffer href = new StringBuffer(100);
/* 204:204 */    href.append("<img src='").append(SysConfigHelper.getAttribute("imagePath"));
/* 205:205 */    if (this.online == 1) {
/* 206:206 */      href.append("status5_small.png' title='在线'");
/* 207:    */    } else
/* 208:208 */      href.append("status6_small.png' title='离线'");
/* 209:209 */    href.append(" align='absmiddle' />");
/* 210:210 */    return href.toString();
/* 211:    */  }
/* 212:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.bean.SurfingAccount
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */