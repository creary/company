/*   1:    */package com.soofound.protocol.wechat.message;
/*   2:    */
/*   3:    */import java.util.HashMap;
/*   4:    */import java.util.Map;
/*   5:    */
/*   6:    */public class InMessage
/*   7:    */{
/*   8:    */  private String ToUserName;
/*   9:    */  private String FromUserName;
/*  10:    */  private Long CreateTime;
/*  11: 11 */  private String MsgType = "text";
/*  12:    */  
/*  13:    */  private Long MsgId;
/*  14:    */  
/*  15:    */  private String Content;
/*  16:    */  
/*  17:    */  private String PicUrl;
/*  18:    */  
/*  19:    */  private String Location_X;
/*  20:    */  
/*  21:    */  private String Location_Y;
/*  22:    */  private Long Scale;
/*  23:    */  private String Label;
/*  24:    */  private String Title;
/*  25:    */  private String Description;
/*  26:    */  private String Url;
/*  27:    */  private String MediaId;
/*  28:    */  private String Format;
/*  29:    */  private String Recognition;
/*  30:    */  private String Event;
/*  31:    */  private String EventKey;
/*  32:    */  private String Ticket;
/*  33:    */  
/*  34:    */  public String getToUserName()
/*  35:    */  {
/*  36: 36 */    return this.ToUserName;
/*  37:    */  }
/*  38:    */  
/*  39:    */  public void setToUserName(String toUserName) {
/*  40: 40 */    this.ToUserName = toUserName;
/*  41:    */  }
/*  42:    */  
/*  43:    */  public String getFromUserName() {
/*  44: 44 */    return this.FromUserName;
/*  45:    */  }
/*  46:    */  
/*  47:    */  public void setFromUserName(String fromUserName) {
/*  48: 48 */    this.FromUserName = fromUserName;
/*  49:    */  }
/*  50:    */  
/*  51:    */  public Long getCreateTime() {
/*  52: 52 */    return this.CreateTime;
/*  53:    */  }
/*  54:    */  
/*  55:    */  public void setCreateTime(Long createTime) {
/*  56: 56 */    this.CreateTime = createTime;
/*  57:    */  }
/*  58:    */  
/*  59:    */  public String getMsgType() {
/*  60: 60 */    return this.MsgType;
/*  61:    */  }
/*  62:    */  
/*  63:    */  public void setMsgType(String msgType) {
/*  64: 64 */    this.MsgType = msgType;
/*  65:    */  }
/*  66:    */  
/*  67:    */  public Long getMsgId() {
/*  68: 68 */    return this.MsgId;
/*  69:    */  }
/*  70:    */  
/*  71:    */  public void setMsgId(Long msgId) {
/*  72: 72 */    this.MsgId = msgId;
/*  73:    */  }
/*  74:    */  
/*  75:    */  public String getContent() {
/*  76: 76 */    return this.Content;
/*  77:    */  }
/*  78:    */  
/*  79:    */  public void setContent(String content) {
/*  80: 80 */    this.Content = content;
/*  81:    */  }
/*  82:    */  
/*  83:    */  public String getPicUrl() {
/*  84: 84 */    return this.PicUrl;
/*  85:    */  }
/*  86:    */  
/*  87:    */  public void setPicUrl(String picUrl) {
/*  88: 88 */    this.PicUrl = picUrl;
/*  89:    */  }
/*  90:    */  
/*  91:    */  public String getLocation_X() {
/*  92: 92 */    return this.Location_X;
/*  93:    */  }
/*  94:    */  
/*  95:    */  public void setLocation_X(String location_X) {
/*  96: 96 */    this.Location_X = location_X;
/*  97:    */  }
/*  98:    */  
/*  99:    */  public String getLocation_Y() {
/* 100:100 */    return this.Location_Y;
/* 101:    */  }
/* 102:    */  
/* 103:    */  public void setLocation_Y(String location_Y) {
/* 104:104 */    this.Location_Y = location_Y;
/* 105:    */  }
/* 106:    */  
/* 107:    */  public Long getScale() {
/* 108:108 */    return this.Scale;
/* 109:    */  }
/* 110:    */  
/* 111:    */  public void setScale(Long scale) {
/* 112:112 */    this.Scale = scale;
/* 113:    */  }
/* 114:    */  
/* 115:    */  public String getLabel() {
/* 116:116 */    return this.Label;
/* 117:    */  }
/* 118:    */  
/* 119:    */  public void setLabel(String label) {
/* 120:120 */    this.Label = label;
/* 121:    */  }
/* 122:    */  
/* 123:    */  public String getTitle() {
/* 124:124 */    return this.Title;
/* 125:    */  }
/* 126:    */  
/* 127:    */  public void setTitle(String title) {
/* 128:128 */    this.Title = title;
/* 129:    */  }
/* 130:    */  
/* 131:    */  public String getDescription() {
/* 132:132 */    return this.Description;
/* 133:    */  }
/* 134:    */  
/* 135:    */  public void setDescription(String description) {
/* 136:136 */    this.Description = description;
/* 137:    */  }
/* 138:    */  
/* 139:    */  public String getUrl() {
/* 140:140 */    return this.Url;
/* 141:    */  }
/* 142:    */  
/* 143:    */  public void setUrl(String url) {
/* 144:144 */    this.Url = url;
/* 145:    */  }
/* 146:    */  
/* 147:    */  public String getEvent() {
/* 148:148 */    return this.Event;
/* 149:    */  }
/* 150:    */  
/* 151:    */  public void setEvent(String event) {
/* 152:152 */    this.Event = event;
/* 153:    */  }
/* 154:    */  
/* 155:    */  public String getEventKey() {
/* 156:156 */    return this.EventKey;
/* 157:    */  }
/* 158:    */  
/* 159:    */  public void setEventKey(String eventKey) {
/* 160:160 */    this.EventKey = eventKey;
/* 161:    */  }
/* 162:    */  
/* 163:    */  public String getMediaId() {
/* 164:164 */    return this.MediaId;
/* 165:    */  }
/* 166:    */  
/* 167:    */  public void setMediaId(String mediaId) {
/* 168:168 */    this.MediaId = mediaId;
/* 169:    */  }
/* 170:    */  
/* 171:    */  public String getFormat() {
/* 172:172 */    return this.Format;
/* 173:    */  }
/* 174:    */  
/* 175:    */  public void setFormat(String format) {
/* 176:176 */    this.Format = format;
/* 177:    */  }
/* 178:    */  
/* 179:    */  public String getRecognition() {
/* 180:180 */    return this.Recognition;
/* 181:    */  }
/* 182:    */  
/* 183:    */  public void setRecognition(String recognition) {
/* 184:184 */    this.Recognition = recognition;
/* 185:    */  }
/* 186:    */  
/* 187:    */  public String getTicket() {
/* 188:188 */    return this.Ticket;
/* 189:    */  }
/* 190:    */  
/* 191:    */  public void setTicket(String ticket) {
/* 192:192 */    this.Ticket = ticket;
/* 193:    */  }
/* 194:    */  
/* 195:    */  public Map<String, Object> toMap() {
/* 196:196 */    Map<String, Object> map = new HashMap();
/* 197:197 */    java.lang.reflect.Field[] fields = InMessage.class.getDeclaredFields();
/* 198:198 */    for (java.lang.reflect.Field field : fields) {
/* 199:    */      try
/* 200:    */      {
/* 201:201 */        Object obj = field.get(this);
/* 202:202 */        if (obj != null) {
/* 203:203 */          map.put(field.getName(), obj);
/* 204:    */        }
/* 205:205 */        map.remove("ToUserName");
/* 206:    */      } catch (IllegalArgumentException e) {
/* 207:207 */        e.printStackTrace();
/* 208:    */      } catch (IllegalAccessException e) {
/* 209:209 */        e.printStackTrace();
/* 210:    */      }
/* 211:    */    }
/* 212:212 */    return map;
/* 213:    */  }
/* 214:    */  
/* 215:    */  public Boolean isEvent() {
/* 216:216 */    return Boolean.valueOf(this.MsgType.equals(MessageTypes.EVENT.getType()));
/* 217:    */  }
/* 218:    */  
/* 219:    */  public Boolean isText() {
/* 220:220 */    return Boolean.valueOf(this.MsgType.equals(MessageTypes.TEXT.getType()));
/* 221:    */  }
/* 222:    */  
/* 223:    */  public Boolean isImage() {
/* 224:224 */    return Boolean.valueOf(this.MsgType.equals(MessageTypes.IMAGE.getType()));
/* 225:    */  }
/* 226:    */  
/* 227:    */  public Boolean isVoice() {
/* 228:228 */    return Boolean.valueOf(this.MsgType.equals(MessageTypes.VOICE.getType()));
/* 229:    */  }
/* 230:    */  
/* 231:    */  public Boolean isVideo() {
/* 232:232 */    return Boolean.valueOf(this.MsgType.equals(MessageTypes.VIDEO.getType()));
/* 233:    */  }
/* 234:    */  
/* 235:    */  public Boolean isLocation() {
/* 236:236 */    return Boolean.valueOf(this.MsgType.equals(MessageTypes.LOCATION.getType()));
/* 237:    */  }
/* 238:    */  
/* 239:    */  public Boolean isLink() {
/* 240:240 */    return Boolean.valueOf(this.MsgType.equals(MessageTypes.LINK.getType()));
/* 241:    */  }
/* 242:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.wechat.message.InMessage
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */