/*   1:    */package com.soofound.protocol.emay;
/*   2:    */
/*   3:    */import java.io.Serializable;
/*   4:    */import org.apache.axis.description.TypeDesc;
/*   5:    */import org.apache.axis.encoding.Deserializer;
/*   6:    */import org.apache.axis.encoding.Serializer;
/*   7:    */import org.apache.axis.encoding.ser.BeanDeserializer;
/*   8:    */import org.apache.axis.encoding.ser.BeanSerializer;
/*   9:    */
/*  10:    */public class Mo implements Serializable
/*  11:    */{
/*  12:    */  private static final long serialVersionUID = 201412092042001L;
/*  13:    */  private String addSerial;
/*  14:    */  private String addSerialRev;
/*  15:    */  private String channelnumber;
/*  16:    */  private String mobileNumber;
/*  17:    */  private String sentTime;
/*  18:    */  private String smsContent;
/*  19:    */  
/*  20:    */  public Mo(String addSerial, String addSerialRev, String channelnumber, String mobileNumber, String sentTime, String smsContent)
/*  21:    */  {
/*  22: 22 */    this.addSerial = addSerial;
/*  23: 23 */    this.addSerialRev = addSerialRev;
/*  24: 24 */    this.channelnumber = channelnumber;
/*  25: 25 */    this.mobileNumber = mobileNumber;
/*  26: 26 */    this.sentTime = sentTime;
/*  27: 27 */    this.smsContent = smsContent;
/*  28:    */  }
/*  29:    */  
/*  35:    */  public String getAddSerial()
/*  36:    */  {
/*  37: 37 */    return this.addSerial;
/*  38:    */  }
/*  39:    */  
/*  45:    */  public void setAddSerial(String addSerial)
/*  46:    */  {
/*  47: 47 */    this.addSerial = addSerial;
/*  48:    */  }
/*  49:    */  
/*  55:    */  public String getAddSerialRev()
/*  56:    */  {
/*  57: 57 */    return this.addSerialRev;
/*  58:    */  }
/*  59:    */  
/*  65:    */  public void setAddSerialRev(String addSerialRev)
/*  66:    */  {
/*  67: 67 */    this.addSerialRev = addSerialRev;
/*  68:    */  }
/*  69:    */  
/*  75:    */  public String getChannelnumber()
/*  76:    */  {
/*  77: 77 */    return this.channelnumber;
/*  78:    */  }
/*  79:    */  
/*  85:    */  public void setChannelnumber(String channelnumber)
/*  86:    */  {
/*  87: 87 */    this.channelnumber = channelnumber;
/*  88:    */  }
/*  89:    */  
/*  95:    */  public String getMobileNumber()
/*  96:    */  {
/*  97: 97 */    return this.mobileNumber;
/*  98:    */  }
/*  99:    */  
/* 105:    */  public void setMobileNumber(String mobileNumber)
/* 106:    */  {
/* 107:107 */    this.mobileNumber = mobileNumber;
/* 108:    */  }
/* 109:    */  
/* 115:    */  public String getSentTime()
/* 116:    */  {
/* 117:117 */    return this.sentTime;
/* 118:    */  }
/* 119:    */  
/* 125:    */  public void setSentTime(String sentTime)
/* 126:    */  {
/* 127:127 */    this.sentTime = sentTime;
/* 128:    */  }
/* 129:    */  
/* 135:    */  public String getSmsContent()
/* 136:    */  {
/* 137:137 */    return this.smsContent;
/* 138:    */  }
/* 139:    */  
/* 145:    */  public void setSmsContent(String smsContent)
/* 146:    */  {
/* 147:147 */    this.smsContent = smsContent;
/* 148:    */  }
/* 149:    */  
/* 150:150 */  private Object __equalsCalc = null;
/* 151:    */  
/* 152:152 */  public synchronized boolean equals(Object obj) { if (!(obj instanceof Mo)) return false;
/* 153:153 */    Mo other = (Mo)obj;
/* 154:154 */    if (this == obj) return true;
/* 155:155 */    if (this.__equalsCalc != null) {
/* 156:156 */      return this.__equalsCalc == obj;
/* 157:    */    }
/* 158:158 */    this.__equalsCalc = obj;
/* 159:    */    
/* 160:160 */    boolean _equals = 
/* 161:161 */      ((this.addSerial == null) && (other.getAddSerial() == null)) || (
/* 162:162 */      (this.addSerial != null) && 
/* 163:163 */      (this.addSerial.equals(other.getAddSerial())) && (
/* 164:164 */      ((this.addSerialRev == null) && (other.getAddSerialRev() == null)) || (
/* 165:165 */      (this.addSerialRev != null) && 
/* 166:166 */      (this.addSerialRev.equals(other.getAddSerialRev())) && (
/* 167:167 */      ((this.channelnumber == null) && (other.getChannelnumber() == null)) || (
/* 168:168 */      (this.channelnumber != null) && 
/* 169:169 */      (this.channelnumber.equals(other.getChannelnumber())) && (
/* 170:170 */      ((this.mobileNumber == null) && (other.getMobileNumber() == null)) || (
/* 171:171 */      (this.mobileNumber != null) && 
/* 172:172 */      (this.mobileNumber.equals(other.getMobileNumber())) && (
/* 173:173 */      ((this.sentTime == null) && (other.getSentTime() == null)) || (
/* 174:174 */      (this.sentTime != null) && 
/* 175:175 */      (this.sentTime.equals(other.getSentTime())) && (
/* 176:176 */      ((this.smsContent == null) && (other.getSmsContent() == null)) || (
/* 177:177 */      (this.smsContent != null) && 
/* 178:178 */      (this.smsContent.equals(other.getSmsContent())))))))))))));
/* 179:179 */    this.__equalsCalc = null;
/* 180:180 */    return _equals;
/* 181:    */  }
/* 182:    */  
/* 183:183 */  private boolean __hashCodeCalc = false;
/* 184:    */  
/* 185:185 */  public synchronized int hashCode() { if (this.__hashCodeCalc) {
/* 186:186 */      return 0;
/* 187:    */    }
/* 188:188 */    this.__hashCodeCalc = true;
/* 189:189 */    int _hashCode = 1;
/* 190:190 */    if (getAddSerial() != null) {
/* 191:191 */      _hashCode += getAddSerial().hashCode();
/* 192:    */    }
/* 193:193 */    if (getAddSerialRev() != null) {
/* 194:194 */      _hashCode += getAddSerialRev().hashCode();
/* 195:    */    }
/* 196:196 */    if (getChannelnumber() != null) {
/* 197:197 */      _hashCode += getChannelnumber().hashCode();
/* 198:    */    }
/* 199:199 */    if (getMobileNumber() != null) {
/* 200:200 */      _hashCode += getMobileNumber().hashCode();
/* 201:    */    }
/* 202:202 */    if (getSentTime() != null) {
/* 203:203 */      _hashCode += getSentTime().hashCode();
/* 204:    */    }
/* 205:205 */    if (getSmsContent() != null) {
/* 206:206 */      _hashCode += getSmsContent().hashCode();
/* 207:    */    }
/* 208:208 */    this.__hashCodeCalc = false;
/* 209:209 */    return _hashCode;
/* 210:    */  }
/* 211:    */  
/* 214:214 */  private static TypeDesc typeDesc = new TypeDesc(Mo.class, true);
/* 215:    */  
/* 216:    */  static {
/* 217:217 */    typeDesc.setXmlType(new javax.xml.namespace.QName("http://sdkhttp.eucp.b2m.cn/", "mo"));
/* 218:218 */    org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
/* 219:219 */    elemField.setFieldName("addSerial");
/* 220:220 */    elemField.setXmlName(new javax.xml.namespace.QName("", "addSerial"));
/* 221:221 */    elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
/* 222:222 */    elemField.setMinOccurs(0);
/* 223:223 */    elemField.setNillable(false);
/* 224:224 */    typeDesc.addFieldDesc(elemField);
/* 225:225 */    elemField = new org.apache.axis.description.ElementDesc();
/* 226:226 */    elemField.setFieldName("addSerialRev");
/* 227:227 */    elemField.setXmlName(new javax.xml.namespace.QName("", "addSerialRev"));
/* 228:228 */    elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
/* 229:229 */    elemField.setMinOccurs(0);
/* 230:230 */    elemField.setNillable(false);
/* 231:231 */    typeDesc.addFieldDesc(elemField);
/* 232:232 */    elemField = new org.apache.axis.description.ElementDesc();
/* 233:233 */    elemField.setFieldName("channelnumber");
/* 234:234 */    elemField.setXmlName(new javax.xml.namespace.QName("", "channelnumber"));
/* 235:235 */    elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
/* 236:236 */    elemField.setMinOccurs(0);
/* 237:237 */    elemField.setNillable(false);
/* 238:238 */    typeDesc.addFieldDesc(elemField);
/* 239:239 */    elemField = new org.apache.axis.description.ElementDesc();
/* 240:240 */    elemField.setFieldName("mobileNumber");
/* 241:241 */    elemField.setXmlName(new javax.xml.namespace.QName("", "mobileNumber"));
/* 242:242 */    elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
/* 243:243 */    elemField.setMinOccurs(0);
/* 244:244 */    elemField.setNillable(false);
/* 245:245 */    typeDesc.addFieldDesc(elemField);
/* 246:246 */    elemField = new org.apache.axis.description.ElementDesc();
/* 247:247 */    elemField.setFieldName("sentTime");
/* 248:248 */    elemField.setXmlName(new javax.xml.namespace.QName("", "sentTime"));
/* 249:249 */    elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
/* 250:250 */    elemField.setMinOccurs(0);
/* 251:251 */    elemField.setNillable(false);
/* 252:252 */    typeDesc.addFieldDesc(elemField);
/* 253:253 */    elemField = new org.apache.axis.description.ElementDesc();
/* 254:254 */    elemField.setFieldName("smsContent");
/* 255:255 */    elemField.setXmlName(new javax.xml.namespace.QName("", "smsContent"));
/* 256:256 */    elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
/* 257:257 */    elemField.setMinOccurs(0);
/* 258:258 */    elemField.setNillable(false);
/* 259:259 */    typeDesc.addFieldDesc(elemField);
/* 260:    */  }
/* 261:    */  
/* 264:    */  public static TypeDesc getTypeDesc()
/* 265:    */  {
/* 266:266 */    return typeDesc;
/* 267:    */  }
/* 268:    */  
/* 274:    */  public static Serializer getSerializer(String mechType, Class _javaType, javax.xml.namespace.QName _xmlType)
/* 275:    */  {
/* 276:276 */    return 
/* 277:277 */      new BeanSerializer(
/* 278:278 */      _javaType, _xmlType, typeDesc);
/* 279:    */  }
/* 280:    */  
/* 286:    */  public static Deserializer getDeserializer(String mechType, Class _javaType, javax.xml.namespace.QName _xmlType)
/* 287:    */  {
/* 288:288 */    return 
/* 289:289 */      new BeanDeserializer(
/* 290:290 */      _javaType, _xmlType, typeDesc);
/* 291:    */  }
/* 292:    */  
/* 293:    */  public Mo() {}
/* 294:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.emay.Mo
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */