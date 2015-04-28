/*   1:    */package com.soofound.protocol.cwmp;
/*   2:    */
/*   3:    */import java.io.ByteArrayInputStream;
/*   4:    */import java.io.IOException;
/*   5:    */import java.io.OutputStream;
/*   6:    */import java.io.PrintStream;
/*   7:    */import java.io.Serializable;
/*   8:    */import java.io.StringWriter;
/*   9:    */import java.util.Calendar;
/*  10:    */import java.util.Hashtable;
/*  11:    */import java.util.Iterator;
/*  12:    */import java.util.NoSuchElementException;
/*  13:    */import javax.xml.soap.MessageFactory;
/*  14:    */import javax.xml.soap.Name;
/*  15:    */import javax.xml.soap.Node;
/*  16:    */import javax.xml.soap.SOAPBody;
/*  17:    */import javax.xml.soap.SOAPBodyElement;
/*  18:    */import javax.xml.soap.SOAPEnvelope;
/*  19:    */import javax.xml.soap.SOAPException;
/*  20:    */import javax.xml.soap.SOAPHeader;
/*  21:    */import javax.xml.soap.SOAPHeaderElement;
/*  22:    */import javax.xml.soap.SOAPMessage;
/*  23:    */import javax.xml.soap.SOAPPart;
/*  24:    */import javax.xml.transform.Transformer;
/*  25:    */import javax.xml.transform.TransformerFactory;
/*  26:    */import javax.xml.transform.dom.DOMSource;
/*  27:    */import javax.xml.transform.stream.StreamResult;
/*  28:    */import org.w3c.dom.Document;
/*  29:    */
/*  30:    */public abstract class Message implements Serializable
/*  31:    */{
/*  32:    */  private static final long serialVersionUID = 201312221918001L;
/*  33:    */  protected String name;
/*  34:    */  protected String id;
/*  35:    */  public boolean noMore;
/*  36: 36 */  protected String URN_CWMP = "urn:dslforum-org:cwmp-1-0";
/*  37:    */  protected static final String CWMP = "cwmp";
/*  38:    */  protected static final String PARAMETER_KEY = "ParameterKey";
/*  39:    */  protected static final String COMMAND_KEY = "CommandKey";
/*  40:    */  protected static final String XSI_TYPE = "xsi:type";
/*  41:    */  protected static final String XSD_STRING = "xsd:string";
/*  42:    */  protected static final String XSD_UNSIGNEDINT = "xsd:unsignedInt";
/*  43:    */  protected static final String XSD_INT = "xsd:int";
/*  44:    */  protected static final String XSD_BOOLEAN = "xsd:boolean";
/*  45:    */  protected static final String XSD_DATETIME = "xsd:dateTime";
/*  46:    */  protected static final String XSD_BASE64 = "xsd:base64";
/*  47:    */  protected static final String SOAP_ARRAY_TYPE = "SOAP-ENC:arrayType";
/*  48:    */  public static final String FAULT_CODE = "FaultCode";
/*  49:    */  public static final String FAULT_STRING = "FaultString";
/*  50:    */  public static final String TYPE_OBJECT = "object";
/*  51:    */  public static final String TYPE_STRING = "string";
/*  52:    */  public static final String TYPE_BOOLEAN = "boolean";
/*  53:    */  public static final String TYPE_DATETIME = "dateTime";
/*  54:    */  public static final String TYPE_UNSIGNEDINT = "unsignedInt";
/*  55:    */  public static final String TYPE_INT = "int";
/*  56:    */  public static final String TYPE_BASE64 = "base64";
/*  57:    */  
/*  58:    */  protected abstract void createBody(SOAPBodyElement paramSOAPBodyElement, javax.xml.soap.SOAPFactory paramSOAPFactory) throws SOAPException;
/*  59:    */  
/*  60:    */  protected abstract void parseBody(SOAPBodyElement paramSOAPBodyElement, javax.xml.soap.SOAPFactory paramSOAPFactory) throws SOAPException;
/*  61:    */  
/*  62:    */  public static SOAPBodyElement getRequest(SOAPMessage msg) throws SOAPException {
/*  63: 63 */    SOAPBodyElement request = null;
/*  64: 64 */    Iterator i1 = msg.getSOAPBody().getChildElements();
/*  65: 65 */    while (i1.hasNext()) {
/*  66: 66 */      Node n = (Node)i1.next();
/*  67: 67 */      if (n.getNodeType() == 1)
/*  68: 68 */        request = (SOAPBodyElement)n;
/*  69:    */    }
/*  70: 70 */    return request;
/*  71:    */  }
/*  72:    */  
/*  73:    */  private static String getRequestName(SOAPMessage msg) throws SOAPException {
/*  74: 74 */    if (msg.getSOAPBody().hasFault())
/*  75: 75 */      return "Fault";
/*  76: 76 */    String name = getRequest(msg).getNodeName();
/*  77: 77 */    if (name.startsWith("cwmp:")) {
/*  78: 78 */      name = name.substring(5);
/*  79: 79 */    } else if (name.startsWith("cwmp_x:"))
/*  80: 80 */      name = name.substring(7);
/*  81: 81 */    return name;
/*  82:    */  }
/*  83:    */  
/*  84:    */  public static Message doParse(SOAPMessage soapMsg) throws Exception {
/*  85: 85 */    String reqname = getRequestName(soapMsg);
/*  86: 86 */    Message msg = (Message)Class.forName("com.soofound.protocol.cwmp." + reqname).newInstance();
/*  87: 87 */    msg = msg.parse(soapMsg);
/*  88: 88 */    return msg;
/*  89:    */  }
/*  90:    */  
/*  91:    */  private Message parse(SOAPMessage soapMsg) throws SOAPException, Exception {
/*  92: 92 */    SOAPEnvelope env = soapMsg.getSOAPPart().getEnvelope();
/*  93: 93 */    Iterator pfxs = env.getNamespacePrefixes();
/*  94: 94 */    while (pfxs.hasNext()) {
/*  95: 95 */      String pfx = (String)pfxs.next();
/*  96: 96 */      String uri = env.getNamespaceURI(pfx);
/*  97: 97 */      if (uri.startsWith("urn:dslforum-org:cwmp-")) {
/*  98: 98 */        this.URN_CWMP = uri;
/*  99:    */      }
/* 100:    */    }
/* 101:101 */    javax.xml.soap.SOAPFactory spf = javax.xml.soap.SOAPFactory.newInstance();
/* 102:102 */    SOAPBodyElement soaprequest = getRequest(soapMsg);
/* 103:103 */    SOAPHeader hdr = soapMsg.getSOAPHeader();
/* 104:104 */    this.id = "device_did_not_send_id";
/* 105:105 */    this.noMore = false;
/* 106:106 */    if (hdr != null) {
/* 107:    */      try {
/* 108:108 */        this.id = getHeaderElement(spf, hdr, "ID");
/* 109:    */      }
/* 110:    */      catch (NoSuchElementException localNoSuchElementException) {}
/* 111:    */      try {
/* 112:112 */        this.noMore = getHeaderElement(spf, hdr, "NoMoreRequests").equals("1");
/* 113:    */      }
/* 114:    */      catch (NoSuchElementException localNoSuchElementException1) {}
/* 115:    */    }
/* 116:116 */    this.name = getRequestName(soapMsg);
/* 117:117 */    if (soaprequest != null) {
/* 118:    */      try {
/* 119:119 */        parseBody(soaprequest, spf);
/* 120:    */      } catch (Exception e) {
/* 121:121 */        Document doc = soapMsg.getSOAPPart().getEnvelope().getOwnerDocument();
/* 122:122 */        StringWriter output = new StringWriter();
/* 123:123 */        TransformerFactory.newInstance().newTransformer().transform(new DOMSource(doc), new StreamResult(output));
/* 124:124 */        System.out.println("===parse error:" + this.name);
/* 125:125 */        System.out.println(output.toString());
/* 126:    */        
/* 127:127 */        javax.xml.soap.SOAPElement se = getRequestChildElement(spf, soaprequest, "FaultCode");
/* 128:128 */        String FaultCode = se != null ? se.getValue() : "0";
/* 129:129 */        javax.xml.soap.SOAPElement se2 = getRequestChildElement(spf, soaprequest, "FaultString");
/* 130:130 */        String FaultString = se2 != null ? se2.getValue() : "0";
/* 131:131 */        if ((se != null) || (se2 != null))
/* 132:132 */          return new Fault(FaultCode, FaultString, this.id);
/* 133:133 */        throw e;
/* 134:    */      }
/* 135:    */    }
/* 136:136 */    return this;
/* 137:    */  }
/* 138:    */  
/* 139:    */  public void writeTo(OutputStream out) {
/* 140:    */    try {
/* 141:141 */      MessageFactory mf = MessageFactory.newInstance();
/* 142:142 */      javax.xml.soap.SOAPFactory spf = javax.xml.soap.SOAPFactory.newInstance();
/* 143:143 */      StringBuffer soapstr = new StringBuffer(256);
/* 144:144 */      soapstr.append("<SOAP-ENV:Envelope");
/* 145:145 */      soapstr.append(" xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"");
/* 146:146 */      soapstr.append(" xmlns:SOAP-ENC=\"http://schemas.xmlsoap.org/soap/encoding/\"");
/* 147:147 */      soapstr.append(" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"");
/* 148:148 */      soapstr.append(" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"");
/* 149:149 */      soapstr.append(" xmlns:cwmp=\"").append(this.URN_CWMP);
/* 150:150 */      soapstr.append("\"><SOAP-ENV:Header></SOAP-ENV:Header><SOAP-ENV:Body></SOAP-ENV:Body></SOAP-ENV:Envelope>");
/* 151:151 */      ByteArrayInputStream in = new ByteArrayInputStream(soapstr.toString().getBytes());
/* 152:152 */      SOAPMessage msg = mf.createMessage(null, in);
/* 153:    */      
/* 154:154 */      SOAPHeaderElement elmntId = msg.getSOAPHeader().addHeaderElement(spf.createName("ID", "cwmp", this.URN_CWMP));
/* 155:155 */      elmntId.setValue(getId());
/* 156:156 */      elmntId.setAttribute("SOAP-ENV:mustUnderstand", "1");
/* 157:157 */      msg.getSOAPHeader().addHeaderElement(spf.createName("NoMoreRequests", "cwmp", this.URN_CWMP)).setValue(this.noMore ? "1" : "0");
/* 158:    */      
/* 159:159 */      SOAPBodyElement bd = msg.getSOAPBody().addBodyElement(spf.createName(this.name, "cwmp", this.URN_CWMP));
/* 160:160 */      if ((this.name == null) || (this.name.equals("")))
/* 161:161 */        this.name = getClass().getSimpleName();
/* 162:162 */      createBody(bd, spf);
/* 163:163 */      msg.writeTo(out);
/* 164:    */    } catch (SOAPException ex) {
/* 165:165 */      ex.printStackTrace();
/* 166:    */    } catch (IOException e) {
/* 167:167 */      e.printStackTrace();
/* 168:    */    }
/* 169:    */  }
/* 170:    */  
/* 171:    */  protected javax.xml.soap.SOAPElement getRequestChildElement(javax.xml.soap.SOAPFactory f, javax.xml.soap.SOAPElement req, String name) throws SOAPException {
/* 172:172 */    Iterator i = req.getChildElements();
/* 173:173 */    while (i.hasNext()) {
/* 174:174 */      Object o = i.next();
/* 175:    */      try {
/* 176:176 */        Node nn = (Node)o;
/* 177:177 */        String n = nn.getLocalName();
/* 178:178 */        if ((n != null) && (n.equals(name)))
/* 179:179 */          return (javax.xml.soap.SOAPElement)o;
/* 180:    */      } catch (Exception e) {
/* 181:181 */        System.out.println("Exception: " + e.getMessage() + " " + e.getClass().getName());
/* 182:    */      }
/* 183:    */    }
/* 184:184 */    return null;
/* 185:    */  }
/* 186:    */  
/* 187:    */  protected javax.xml.soap.SOAPElement getRequestChildElement2(javax.xml.soap.SOAPFactory f, javax.xml.soap.SOAPElement req, String name) throws SOAPException {
/* 188:188 */    return (javax.xml.soap.SOAPElement)req.getChildElements(f.createName(name, "cwmp", this.URN_CWMP)).next();
/* 189:    */  }
/* 190:    */  
/* 191:    */  protected String getRequestElement(javax.xml.soap.SOAPFactory f, javax.xml.soap.SOAPElement req, String name) throws SOAPException {
/* 192:192 */    return getRequestChildElement(f, req, name).getValue();
/* 193:    */  }
/* 194:    */  
/* 195:    */  protected String getRequestElement(javax.xml.soap.SOAPFactory f, javax.xml.soap.SOAPElement req, String name, String def) throws SOAPException {
/* 196:196 */    String v = getRequestChildElement(f, req, name).getValue();
/* 197:197 */    return v != null ? v : def;
/* 198:    */  }
/* 199:    */  
/* 200:    */  protected javax.xml.soap.SOAPElement getRequestChildElement(javax.xml.soap.SOAPElement req, Name name) throws SOAPException {
/* 201:201 */    return (javax.xml.soap.SOAPElement)req.getChildElements(name).next();
/* 202:    */  }
/* 203:    */  
/* 204:    */  protected String getRequestElement(javax.xml.soap.SOAPElement req, Name name) throws SOAPException {
/* 205:205 */    return getRequestChildElement(req, name).getValue();
/* 206:    */  }
/* 207:    */  
/* 208:    */  protected String getHeaderElement(javax.xml.soap.SOAPFactory f, SOAPHeader hdr, String name) throws SOAPException {
/* 209:209 */    return ((SOAPHeaderElement)hdr.getChildElements(f.createName(name, "cwmp", this.URN_CWMP)).next()).getValue();
/* 210:    */  }
/* 211:    */  
/* 212:    */  protected Hashtable<String, String> parseParamList(javax.xml.soap.SOAPElement body, javax.xml.soap.SOAPFactory spf) throws SOAPException {
/* 213:213 */    return parseParamList(body, spf, "ParameterValueStruct", "Value");
/* 214:    */  }
/* 215:    */  
/* 216:    */  protected Hashtable<String, String> parseParamList(javax.xml.soap.SOAPElement body, javax.xml.soap.SOAPFactory spf, String sn, String vn) throws SOAPException {
/* 217:217 */    Iterator pi = getRequestChildElement(spf, body, "ParameterList").getChildElements(spf.createName(sn));
/* 218:218 */    Name nameKey = spf.createName("Name");
/* 219:219 */    Name nameValue = spf.createName(vn);
/* 220:220 */    Hashtable<String, String> pl = new Hashtable();
/* 221:    */    
/* 222:222 */    while (pi.hasNext()) {
/* 223:223 */      javax.xml.soap.SOAPElement param = (javax.xml.soap.SOAPElement)pi.next();
/* 224:224 */      String key = getRequestElement(param, nameKey);
/* 225:225 */      String value = getRequestElement(param, nameValue);
/* 226:226 */      if (value == null) value = "";
/* 227:227 */      pl.put(key, value);
/* 228:    */    }
/* 229:229 */    return pl;
/* 230:    */  }
/* 231:    */  
/* 232:    */  protected int getArrayCount(javax.xml.soap.SOAPFactory spf, javax.xml.soap.SOAPElement e) throws SOAPException {
/* 233:233 */    return getArrayCount(spf, e, null);
/* 234:    */  }
/* 235:    */  
/* 236:    */  protected int getArrayCount(javax.xml.soap.SOAPFactory spf, javax.xml.soap.SOAPElement e, ArrayType type) throws SOAPException {
/* 237:237 */    Name nameArray = spf.createName("arrayType", "soap-enc", "http://schemas.xmlsoap.org/soap/encoding/");
/* 238:238 */    String attr = e.getAttributeValue(nameArray);
/* 239:239 */    if (attr == null)
/* 240:240 */      return 0;
/* 241:241 */    attr = attr.replaceAll(" ", "");
/* 242:242 */    int i = attr.indexOf('[');
/* 243:243 */    String c = attr.substring(i + 1, attr.length() - 1);
/* 244:244 */    if (type != null)
/* 245:245 */      type.setType(attr.substring(0, i));
/* 246:246 */    return Integer.parseInt(c);
/* 247:    */  }
/* 248:    */  
/* 249:    */  public boolean isFault() {
/* 250:250 */    return this.name.equals("Fault");
/* 251:    */  }
/* 252:    */  
/* 253:    */  protected String b2s(boolean b) {
/* 254:254 */    return b ? "1" : "0";
/* 255:    */  }
/* 256:    */  
/* 257:    */  public String getName() {
/* 258:258 */    return this.name;
/* 259:    */  }
/* 260:    */  
/* 261:    */  public String getId() {
/* 262:262 */    if (this.id == null)
/* 263:263 */      this.id = ("ID:intrnl.unset.id." + (this.name != null ? this.name : "") + (Calendar.getInstance().getTimeInMillis() + 3600000L) + "." + hashCode());
/* 264:264 */    return this.id;
/* 265:    */  }
/* 266:    */  
/* 267:    */  String getXmlType(String type) {
/* 268:268 */    if (type.equals("base64"))
/* 269:269 */      return "xsd:base64";
/* 270:270 */    if (type.equals("boolean"))
/* 271:271 */      return "xsd:boolean";
/* 272:272 */    if (type.equals("dateTime"))
/* 273:273 */      return "xsd:dateTime";
/* 274:274 */    if (type.equals("int"))
/* 275:275 */      return "xsd:int";
/* 276:276 */    if (type.equals("object"))
/* 277:277 */      return "";
/* 278:278 */    if (type.equals("string"))
/* 279:279 */      return "xsd:string";
/* 280:280 */    if (type.equals("unsignedInt"))
/* 281:281 */      return "xsd:unsignedInt";
/* 282:282 */    return type;
/* 283:    */  }
/* 284:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.cwmp.Message
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */