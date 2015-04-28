/*   1:    */package com.soofound.protocol.emay;
/*   2:    */
/*   3:    */import java.io.Serializable;
/*   4:    */import javax.xml.namespace.QName;
/*   5:    */import org.apache.axis.description.ElementDesc;
/*   6:    */import org.apache.axis.description.TypeDesc;
/*   7:    */import org.apache.axis.encoding.Deserializer;
/*   8:    */import org.apache.axis.encoding.Serializer;
/*   9:    */import org.apache.axis.encoding.ser.BeanDeserializer;
/*  10:    */import org.apache.axis.encoding.ser.BeanSerializer;
/*  11:    */
/*  13:    */public class StatusReport
/*  14:    */  implements Serializable
/*  15:    */{
/*  16:    */  private String errorCode;
/*  17:    */  private String memo;
/*  18:    */  private String mobile;
/*  19:    */  private String receiveDate;
/*  20:    */  private int reportStatus;
/*  21:    */  private long seqID;
/*  22:    */  private String serviceCodeAdd;
/*  23:    */  private String submitDate;
/*  24:    */  
/*  25:    */  public StatusReport(String errorCode, String memo, String mobile, String receiveDate, int reportStatus, long seqID, String serviceCodeAdd, String submitDate)
/*  26:    */  {
/*  27: 27 */    this.errorCode = errorCode;
/*  28: 28 */    this.memo = memo;
/*  29: 29 */    this.mobile = mobile;
/*  30: 30 */    this.receiveDate = receiveDate;
/*  31: 31 */    this.reportStatus = reportStatus;
/*  32: 32 */    this.seqID = seqID;
/*  33: 33 */    this.serviceCodeAdd = serviceCodeAdd;
/*  34: 34 */    this.submitDate = submitDate;
/*  35:    */  }
/*  36:    */  
/*  42:    */  public String getErrorCode()
/*  43:    */  {
/*  44: 44 */    return this.errorCode;
/*  45:    */  }
/*  46:    */  
/*  52:    */  public void setErrorCode(String errorCode)
/*  53:    */  {
/*  54: 54 */    this.errorCode = errorCode;
/*  55:    */  }
/*  56:    */  
/*  62:    */  public String getMemo()
/*  63:    */  {
/*  64: 64 */    return this.memo;
/*  65:    */  }
/*  66:    */  
/*  72:    */  public void setMemo(String memo)
/*  73:    */  {
/*  74: 74 */    this.memo = memo;
/*  75:    */  }
/*  76:    */  
/*  82:    */  public String getMobile()
/*  83:    */  {
/*  84: 84 */    return this.mobile;
/*  85:    */  }
/*  86:    */  
/*  92:    */  public void setMobile(String mobile)
/*  93:    */  {
/*  94: 94 */    this.mobile = mobile;
/*  95:    */  }
/*  96:    */  
/* 102:    */  public String getReceiveDate()
/* 103:    */  {
/* 104:104 */    return this.receiveDate;
/* 105:    */  }
/* 106:    */  
/* 112:    */  public void setReceiveDate(String receiveDate)
/* 113:    */  {
/* 114:114 */    this.receiveDate = receiveDate;
/* 115:    */  }
/* 116:    */  
/* 122:    */  public int getReportStatus()
/* 123:    */  {
/* 124:124 */    return this.reportStatus;
/* 125:    */  }
/* 126:    */  
/* 132:    */  public void setReportStatus(int reportStatus)
/* 133:    */  {
/* 134:134 */    this.reportStatus = reportStatus;
/* 135:    */  }
/* 136:    */  
/* 142:    */  public long getSeqID()
/* 143:    */  {
/* 144:144 */    return this.seqID;
/* 145:    */  }
/* 146:    */  
/* 152:    */  public void setSeqID(long seqID)
/* 153:    */  {
/* 154:154 */    this.seqID = seqID;
/* 155:    */  }
/* 156:    */  
/* 162:    */  public String getServiceCodeAdd()
/* 163:    */  {
/* 164:164 */    return this.serviceCodeAdd;
/* 165:    */  }
/* 166:    */  
/* 172:    */  public void setServiceCodeAdd(String serviceCodeAdd)
/* 173:    */  {
/* 174:174 */    this.serviceCodeAdd = serviceCodeAdd;
/* 175:    */  }
/* 176:    */  
/* 182:    */  public String getSubmitDate()
/* 183:    */  {
/* 184:184 */    return this.submitDate;
/* 185:    */  }
/* 186:    */  
/* 192:    */  public void setSubmitDate(String submitDate)
/* 193:    */  {
/* 194:194 */    this.submitDate = submitDate;
/* 195:    */  }
/* 196:    */  
/* 197:197 */  private Object __equalsCalc = null;
/* 198:    */  
/* 199:199 */  public synchronized boolean equals(Object obj) { if (!(obj instanceof StatusReport)) return false;
/* 200:200 */    StatusReport other = (StatusReport)obj;
/* 201:201 */    if (obj == null) return false;
/* 202:202 */    if (this == obj) return true;
/* 203:203 */    if (this.__equalsCalc != null) {
/* 204:204 */      return this.__equalsCalc == obj;
/* 205:    */    }
/* 206:206 */    this.__equalsCalc = obj;
/* 207:    */    
/* 208:208 */    boolean _equals = 
/* 209:209 */      ((this.errorCode == null) && (other.getErrorCode() == null)) || (
/* 210:210 */      (this.errorCode != null) && 
/* 211:211 */      (this.errorCode.equals(other.getErrorCode())) && (
/* 212:212 */      ((this.memo == null) && (other.getMemo() == null)) || (
/* 213:213 */      (this.memo != null) && 
/* 214:214 */      (this.memo.equals(other.getMemo())) && (
/* 215:215 */      ((this.mobile == null) && (other.getMobile() == null)) || (
/* 216:216 */      (this.mobile != null) && 
/* 217:217 */      (this.mobile.equals(other.getMobile())) && (
/* 218:218 */      ((this.receiveDate == null) && (other.getReceiveDate() == null)) || (
/* 219:219 */      (this.receiveDate != null) && 
/* 220:220 */      (this.receiveDate.equals(other.getReceiveDate())) && 
/* 221:221 */      (this.reportStatus == other.getReportStatus()) && 
/* 222:222 */      (this.seqID == other.getSeqID()) && (
/* 223:223 */      ((this.serviceCodeAdd == null) && (other.getServiceCodeAdd() == null)) || (
/* 224:224 */      (this.serviceCodeAdd != null) && 
/* 225:225 */      (this.serviceCodeAdd.equals(other.getServiceCodeAdd())) && (
/* 226:226 */      ((this.submitDate == null) && (other.getSubmitDate() == null)) || (
/* 227:227 */      (this.submitDate != null) && 
/* 228:228 */      (this.submitDate.equals(other.getSubmitDate())))))))))))));
/* 229:229 */    this.__equalsCalc = null;
/* 230:230 */    return _equals;
/* 231:    */  }
/* 232:    */  
/* 233:233 */  private boolean __hashCodeCalc = false;
/* 234:    */  
/* 235:235 */  public synchronized int hashCode() { if (this.__hashCodeCalc) {
/* 236:236 */      return 0;
/* 237:    */    }
/* 238:238 */    this.__hashCodeCalc = true;
/* 239:239 */    int _hashCode = 1;
/* 240:240 */    if (getErrorCode() != null) {
/* 241:241 */      _hashCode += getErrorCode().hashCode();
/* 242:    */    }
/* 243:243 */    if (getMemo() != null) {
/* 244:244 */      _hashCode += getMemo().hashCode();
/* 245:    */    }
/* 246:246 */    if (getMobile() != null) {
/* 247:247 */      _hashCode += getMobile().hashCode();
/* 248:    */    }
/* 249:249 */    if (getReceiveDate() != null) {
/* 250:250 */      _hashCode += getReceiveDate().hashCode();
/* 251:    */    }
/* 252:252 */    _hashCode += getReportStatus();
/* 253:253 */    _hashCode += new Long(getSeqID()).hashCode();
/* 254:254 */    if (getServiceCodeAdd() != null) {
/* 255:255 */      _hashCode += getServiceCodeAdd().hashCode();
/* 256:    */    }
/* 257:257 */    if (getSubmitDate() != null) {
/* 258:258 */      _hashCode += getSubmitDate().hashCode();
/* 259:    */    }
/* 260:260 */    this.__hashCodeCalc = false;
/* 261:261 */    return _hashCode;
/* 262:    */  }
/* 263:    */  
/* 266:266 */  private static TypeDesc typeDesc = new TypeDesc(StatusReport.class, true);
/* 267:    */  
/* 268:    */  static {
/* 269:269 */    typeDesc.setXmlType(new QName("http://sdkhttp.eucp.b2m.cn/", "statusReport"));
/* 270:270 */    ElementDesc elemField = new ElementDesc();
/* 271:271 */    elemField.setFieldName("errorCode");
/* 272:272 */    elemField.setXmlName(new QName("", "errorCode"));
/* 273:273 */    elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
/* 274:274 */    elemField.setMinOccurs(0);
/* 275:275 */    elemField.setNillable(false);
/* 276:276 */    typeDesc.addFieldDesc(elemField);
/* 277:277 */    elemField = new ElementDesc();
/* 278:278 */    elemField.setFieldName("memo");
/* 279:279 */    elemField.setXmlName(new QName("", "memo"));
/* 280:280 */    elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
/* 281:281 */    elemField.setMinOccurs(0);
/* 282:282 */    elemField.setNillable(false);
/* 283:283 */    typeDesc.addFieldDesc(elemField);
/* 284:284 */    elemField = new ElementDesc();
/* 285:285 */    elemField.setFieldName("mobile");
/* 286:286 */    elemField.setXmlName(new QName("", "mobile"));
/* 287:287 */    elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
/* 288:288 */    elemField.setMinOccurs(0);
/* 289:289 */    elemField.setNillable(false);
/* 290:290 */    typeDesc.addFieldDesc(elemField);
/* 291:291 */    elemField = new ElementDesc();
/* 292:292 */    elemField.setFieldName("receiveDate");
/* 293:293 */    elemField.setXmlName(new QName("", "receiveDate"));
/* 294:294 */    elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
/* 295:295 */    elemField.setMinOccurs(0);
/* 296:296 */    elemField.setNillable(false);
/* 297:297 */    typeDesc.addFieldDesc(elemField);
/* 298:298 */    elemField = new ElementDesc();
/* 299:299 */    elemField.setFieldName("reportStatus");
/* 300:300 */    elemField.setXmlName(new QName("", "reportStatus"));
/* 301:301 */    elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "int"));
/* 302:302 */    elemField.setNillable(false);
/* 303:303 */    typeDesc.addFieldDesc(elemField);
/* 304:304 */    elemField = new ElementDesc();
/* 305:305 */    elemField.setFieldName("seqID");
/* 306:306 */    elemField.setXmlName(new QName("", "seqID"));
/* 307:307 */    elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "long"));
/* 308:308 */    elemField.setNillable(false);
/* 309:309 */    typeDesc.addFieldDesc(elemField);
/* 310:310 */    elemField = new ElementDesc();
/* 311:311 */    elemField.setFieldName("serviceCodeAdd");
/* 312:312 */    elemField.setXmlName(new QName("", "serviceCodeAdd"));
/* 313:313 */    elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
/* 314:314 */    elemField.setMinOccurs(0);
/* 315:315 */    elemField.setNillable(false);
/* 316:316 */    typeDesc.addFieldDesc(elemField);
/* 317:317 */    elemField = new ElementDesc();
/* 318:318 */    elemField.setFieldName("submitDate");
/* 319:319 */    elemField.setXmlName(new QName("", "submitDate"));
/* 320:320 */    elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
/* 321:321 */    elemField.setMinOccurs(0);
/* 322:322 */    elemField.setNillable(false);
/* 323:323 */    typeDesc.addFieldDesc(elemField);
/* 324:    */  }
/* 325:    */  
/* 328:    */  public static TypeDesc getTypeDesc()
/* 329:    */  {
/* 330:330 */    return typeDesc;
/* 331:    */  }
/* 332:    */  
/* 338:    */  public static Serializer getSerializer(String mechType, Class _javaType, QName _xmlType)
/* 339:    */  {
/* 340:340 */    return 
/* 341:341 */      new BeanSerializer(
/* 342:342 */      _javaType, _xmlType, typeDesc);
/* 343:    */  }
/* 344:    */  
/* 350:    */  public static Deserializer getDeserializer(String mechType, Class _javaType, QName _xmlType)
/* 351:    */  {
/* 352:352 */    return 
/* 353:353 */      new BeanDeserializer(
/* 354:354 */      _javaType, _xmlType, typeDesc);
/* 355:    */  }
/* 356:    */  
/* 357:    */  public StatusReport() {}
/* 358:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.emay.StatusReport
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */