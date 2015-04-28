/*   1:    */package com.soofound.protocol.cwmp;
/*   2:    */
/*   3:    */import java.util.Hashtable;
/*   4:    */import java.util.Iterator;
/*   5:    */import java.util.LinkedHashSet;
/*   6:    */import java.util.Map.Entry;
/*   7:    */import java.util.Set;
/*   8:    */import javax.xml.soap.Name;
/*   9:    */import javax.xml.soap.SOAPBodyElement;
/*  10:    */import javax.xml.soap.SOAPElement;
/*  11:    */import javax.xml.soap.SOAPException;
/*  12:    */import javax.xml.soap.SOAPFactory;
/*  13:    */import org.springframework.util.StringUtils;
/*  14:    */
/*  18:    */public class Inform
/*  19:    */  extends Message
/*  20:    */{
/*  21:    */  private static final long serialVersionUID = 201312221920001L;
/*  22:    */  public static final String EVENT_BOOT_STRAP = "0 BOOTSTRAP";
/*  23:    */  public static final String EVENT_BOOT = "1 BOOT";
/*  24:    */  public static final String EVENT_PERIODIC = "2 PERIODIC";
/*  25:    */  public static final String EVENT_SCHEDULED = "3 SCHEDULED";
/*  26:    */  public static final String EVENT_VALUE_CHANGE = "4 VALUE CHANGE";
/*  27:    */  public static final String EVENT_KICKED = "5 KICKED";
/*  28:    */  public static final String EVENT_CONNECTION_REQUEST = "6 CONNECTION REQUEST";
/*  29:    */  public static final String EVENT_TRANSFER_COMPLETE = "7 TRANSFER COMPLETE";
/*  30:    */  public static final String EVENT_EXTERNAL_ADDR_CHANGE = "101 EXTERNAL ADDR CHANGE";
/*  31:    */  private String serialNumber;
/*  32:    */  private String productClass;
/*  33:    */  private int maxEnvelopes;
/*  34:    */  private int uptime;
/*  35:    */  private int retryCount;
/*  36: 36 */  private String root = null;
/*  37:    */  private Hashtable<String, String> params;
/*  38:    */  private Set<Map.Entry<String, String>> events;
/*  39:    */  
/*  40:    */  protected void createBody(SOAPBodyElement body, SOAPFactory spf) throws SOAPException
/*  41:    */  {}
/*  42:    */  
/*  43:    */  protected void parseBody(SOAPBodyElement body, SOAPFactory spf) throws SOAPException {
/*  44: 44 */    SOAPElement deviceid = getRequestChildElement(spf, body, "DeviceId");
/*  45:    */    
/*  46: 46 */    this.serialNumber = getRequestElement(spf, deviceid, "SerialNumber");
/*  47: 47 */    this.productClass = getRequestElement(spf, deviceid, "ProductClass");
/*  48: 48 */    if (this.productClass == null)
/*  49: 49 */      this.productClass = "";
/*  50: 50 */    this.maxEnvelopes = Integer.parseInt(getRequestElement(spf, body, "MaxEnvelopes"));
/*  51: 51 */    this.retryCount = Integer.parseInt(getRequestElement(spf, body, "RetryCount"));
/*  52:    */    try {
/*  53: 53 */      this.uptime = Integer.parseInt(getRequestElement(spf, body, "UpTime"));
/*  54:    */    }
/*  55:    */    catch (Exception localException) {}
/*  56: 56 */    Iterator pi = getRequestChildElement(spf, body, "ParameterList").getChildElements(spf.createName("ParameterValueStruct"));
/*  57:    */    
/*  58: 58 */    Name nameKey = spf.createName("Name");
/*  59: 59 */    Name nameValue = spf.createName("Value");
/*  60: 60 */    this.params = new Hashtable();
/*  61: 61 */    while (pi.hasNext()) {
/*  62: 62 */      SOAPElement param = (SOAPElement)pi.next();
/*  63: 63 */      String key = getRequestElement(param, nameKey);
/*  64: 64 */      if ((this.root == null) && (!key.startsWith("."))) {
/*  65: 65 */        if (key.startsWith("Device.")) {
/*  66: 66 */          this.root = "Device";
/*  67: 67 */        } else if (key.startsWith("InternetGatewayDevice.")) {
/*  68: 68 */          this.root = "InternetGatewayDevice";
/*  69:    */        } else
/*  70: 70 */          throw new RuntimeException("Invalid root. Must be InternetGatewayDevice or Device: " + key);
/*  71:    */      }
/*  72: 72 */      String value = null;
/*  73:    */      try {
/*  74: 74 */        value = getRequestElement(param, nameValue);
/*  75:    */      }
/*  76:    */      catch (Exception localException1) {}
/*  77: 77 */      if (value == null) value = "";
/*  78: 78 */      this.params.put(key, value);
/*  79:    */    }
/*  80: 80 */    if (this.root == null) {
/*  81: 81 */      throw new RuntimeException("Invalid root. Must be InternetGatewayDevice or Device");
/*  82:    */    }
/*  83: 83 */    pi = getRequestChildElement(spf, body, "Event").getChildElements(spf.createName("EventStruct"));
/*  84: 84 */    Name eventCode = spf.createName("EventCode");
/*  85: 85 */    Name commandKey = spf.createName("CommandKey");
/*  86: 86 */    this.events = new LinkedHashSet();
/*  87: 87 */    while (pi.hasNext()) {
/*  88: 88 */      SOAPElement param = (SOAPElement)pi.next();
/*  89: 89 */      String event = getRequestElement(param, eventCode);
/*  90: 90 */      String cmdKey = getRequestElement(param, commandKey);
/*  91: 91 */      if (cmdKey == null) cmdKey = "";
/*  92: 92 */      this.events.add(new Event(event, cmdKey));
/*  93:    */    }
/*  94:    */  }
/*  95:    */  
/*  96:    */  public String getProperty(String propertyName) {
/*  97: 97 */    String val = (String)this.params.get(propertyName);
/*  98: 98 */    if (val == null)
/*  99: 99 */      val = "";
/* 100:100 */    return val;
/* 101:    */  }
/* 102:    */  
/* 103:    */  public boolean isEvent(String event) {
/* 104:104 */    for (Map.Entry<String, String> e : this.events) {
/* 105:105 */      if (((String)e.getKey()).equals(event))
/* 106:106 */        return true;
/* 107:    */    }
/* 108:108 */    return false;
/* 109:    */  }
/* 110:    */  
/* 111:    */  public Hashtable<String, String> getParams() {
/* 112:112 */    return this.params;
/* 113:    */  }
/* 114:    */  
/* 115:    */  public Set<Map.Entry<String, String>> getEvents() {
/* 116:116 */    return this.events;
/* 117:    */  }
/* 118:    */  
/* 119:    */  public String getSerialNumber() {
/* 120:120 */    return this.serialNumber;
/* 121:    */  }
/* 122:    */  
/* 123:    */  public String getProductClass() {
/* 124:124 */    return this.productClass;
/* 125:    */  }
/* 126:    */  
/* 127:    */  public int getMaxEnvelopes() {
/* 128:128 */    return this.maxEnvelopes;
/* 129:    */  }
/* 130:    */  
/* 131:    */  public int getRetryCount() {
/* 132:132 */    return this.retryCount;
/* 133:    */  }
/* 134:    */  
/* 135:    */  public int getUptime() {
/* 136:136 */    return this.uptime;
/* 137:    */  }
/* 138:    */  
/* 139:    */  public String getStun() {
/* 140:140 */    if (StringUtils.hasText(getProperty("InternetGatewayDevice.DeviceInfo.ip_outside")))
/* 141:141 */      return getProperty("InternetGatewayDevice.DeviceInfo.ip_outside") + "-" + getProperty("InternetGatewayDevice.DeviceInfo.port_outside");
/* 142:142 */    return "";
/* 143:    */  }
/* 144:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.cwmp.Inform
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */