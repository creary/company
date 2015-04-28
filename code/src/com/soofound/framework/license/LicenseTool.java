/*   1:    */package com.soofound.framework.license;
/*   2:    */
/*   3:    */import java.io.ByteArrayOutputStream;
/*   4:    */import java.io.File;
/*   5:    */import java.io.FileOutputStream;
/*   6:    */import java.io.InputStream;
/*   7:    */import java.net.NetworkInterface;
/*   8:    */import java.text.ParseException;
/*   9:    */import java.text.SimpleDateFormat;
/*  10:    */import java.util.Date;
/*  11:    */import java.util.Enumeration;
/*  12:    */import java.util.List;
/*  13:    */import org.apache.commons.codec.binary.Base64;
/*  14:    */import org.jdom2.Element;
/*  15:    */import org.jdom2.input.SAXBuilder;
/*  16:    */import org.jdom2.output.Format;
/*  17:    */import org.jdom2.output.XMLOutputter;
/*  18:    */
/*  19:    */public final class LicenseTool
/*  20:    */{
/*  21: 21 */  public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
/*  22:    */  
/*  23:    */  public static final String LICENSE_TRIAL = "Trial";
/*  24:    */  
/*  25:    */  public static final String LICENSE_REGISTERED = "Registered";
/*  26:    */  
/*  27:    */  public static final String PRODUCT_NAME_ES = "ElementSentry";
/*  28:    */  
/*  29:    */  public static final String PRODUCT_NAME_SF = "SooFac";
/*  30:    */  public static final String PRODUCT_TYPE_STANDARD = "Standard";
/*  31:    */  public static final String PRODUCT_TYPE_PROFESSIONAL = "Professional";
/*  32:    */  public static final String ES_DEFAULT_BOOT = "/WEB-INF/config/boot.xml";
/*  33:    */  
/*  34:    */  public static org.jdom2.Document file2Doc(String xmlPath)
/*  35:    */  {
/*  36: 36 */    return file2Doc(xmlPath, false);
/*  37:    */  }
/*  38:    */  
/*  39:    */  public static org.jdom2.Document file2Doc(String xmlPath, boolean validate) {
/*  40: 40 */    SAXBuilder builder = new SAXBuilder(validate);
/*  41: 41 */    org.jdom2.Document doc = null;
/*  42:    */    try {
/*  43: 43 */      doc = builder.build(new File(xmlPath.replace("%20", " ")));
/*  44:    */    } catch (Exception e) {
/*  45: 45 */      e.printStackTrace();
/*  46:    */    }
/*  47: 47 */    return doc;
/*  48:    */  }
/*  49:    */  
/*  50:    */  public static org.jdom2.Document file2Doc(InputStream stream, boolean validate) {
/*  51: 51 */    SAXBuilder builder = new SAXBuilder(validate);
/*  52: 52 */    org.jdom2.Document doc = null;
/*  53:    */    try {
/*  54: 54 */      doc = builder.build(stream);
/*  55:    */    } catch (Exception e) {
/*  56: 56 */      e.printStackTrace();
/*  57:    */    }
/*  58: 58 */    return doc;
/*  59:    */  }
/*  60:    */  
/*  61:    */  public static org.jdom2.Document file2Doc(InputStream stream) {
/*  62: 62 */    SAXBuilder builder = new SAXBuilder(true);
/*  63: 63 */    org.jdom2.Document doc = null;
/*  64:    */    try {
/*  65: 65 */      doc = builder.build(stream);
/*  66:    */    } catch (Exception e) {
/*  67: 67 */      e.printStackTrace();
/*  68:    */    }
/*  69: 69 */    return doc;
/*  70:    */  }
/*  71:    */  
/*  72:    */  public static void updateXML(String xmlFullPath, org.jdom2.Document doc) {
/*  73:    */    try {
/*  74: 74 */      Format format = Format.getCompactFormat();
/*  75: 75 */      format.setEncoding("UTF-8");
/*  76: 76 */      format.setIndent("\t");
/*  77: 77 */      XMLOutputter serializer = new XMLOutputter(format);
/*  78: 78 */      FileOutputStream fos = new FileOutputStream(xmlFullPath.replace("%20", " "));
/*  79: 79 */      serializer.output(doc, fos);
/*  80: 80 */      fos.close();
/*  81:    */    } catch (Exception e) {
/*  82: 82 */      e.printStackTrace();
/*  83:    */    }
/*  84:    */  }
/*  85:    */  
/*  86:    */  public static String getMachineID() {
/*  87: 87 */    StringBuilder systemUuid = new StringBuilder(16);
/*  88:    */    try {
/*  89: 89 */      ByteArrayOutputStream bos = new ByteArrayOutputStream();
/*  90: 90 */      Enumeration<NetworkInterface> el = NetworkInterface.getNetworkInterfaces();
/*  91: 91 */      while (el.hasMoreElements()) {
/*  92: 92 */        NetworkInterface netInterface = (NetworkInterface)el.nextElement();
/*  93: 93 */        byte[] mac = netInterface.getHardwareAddress();
/*  94: 94 */        if (mac != null)
/*  95: 95 */          bos.write(mac);
/*  96:    */      }
/*  97: 97 */      bos.flush();
/*  98: 98 */      byte[] allbyte = bos.toByteArray();
/*  99: 99 */      String str = Base64.encodeBase64String(allbyte).toLowerCase();
/* 100:100 */      if (str.length() == 8) {
/* 101:101 */        systemUuid.append("soofac-").append(str.substring(0, 4)).append("-").append(str.substring(4, 8));
/* 102:102 */      } else if (str.length() == 16) {
/* 103:103 */        systemUuid.append(str.substring(0, 4)).append("-").append(str.substring(4, 8));
/* 104:104 */        systemUuid.append("-").append(str.substring(8, 12)).append("-").append(str.substring(12, 16));
/* 105:105 */      } else if (str.length() > 16) {
/* 106:106 */        systemUuid.append(str.substring(0, 4)).append("-").append(str.substring(5, 9));
/* 107:107 */        systemUuid.append("-").append(str.substring(10, 14)).append(str.substring(15, 19));
/* 108:    */      } else {
/* 109:109 */        systemUuid.append("xds3-fec4-sd12-77k9");
/* 110:    */      }
/* 111:    */    } catch (Exception e) {
/* 112:112 */      e.printStackTrace();
/* 113:    */    }
/* 114:114 */    return systemUuid.toString();
/* 115:    */  }
/* 116:    */  
/* 117:    */  public static License parseLicense(String xmlFile) {
/* 118:118 */    License lic = new License();
/* 119:    */    try {
/* 120:120 */      org.jdom2.Document doc = file2Doc(xmlFile);
/* 121:121 */      Element cdEle = doc.getRootElement().getChild("CustomerDetails");
/* 122:122 */      lic.setProductID(getPropertyValue(cdEle, "ProductID"));
/* 123:123 */      lic.setVendor(getPropertyValue(cdEle, "Vendor"));
/* 124:124 */      lic.setCustomer(getPropertyValue(cdEle, "Customer"));
/* 125:125 */      lic.setMachineID(getPropertyValue(cdEle, "MachineID"));
/* 126:126 */      lic.setLicenseType(getPropertyValue(cdEle, "LicenseType"));
/* 127:127 */      lic.setExpiryDate(getPropertyValue(cdEle, "ExpiryDate"));
/* 128:    */      
/* 129:129 */      Element pdEle = doc.getRootElement().getChild("ProductDetails");
/* 130:130 */      lic.setProductName(getPropertyValue(pdEle, "ProductName"));
/* 131:131 */      lic.setProductType(getPropertyValue(pdEle, "ProductType"));
/* 132:132 */      lic.setVersion(getPropertyValue(pdEle, "Version"));
/* 133:133 */      lic.setReleaseDate(getPropertyValue(pdEle, "ReleaseDate"));
/* 134:134 */      lic.setDeviceNumber(Integer.parseInt(getPropertyValue(pdEle, "DeviceNumber")));
/* 135:135 */      lic.setBoot(getPropertyValue(pdEle, "Boot"));
/* 136:136 */      lic.setCode(doc.getRootElement().getChildText("Code"));
/* 137:    */    } catch (Exception e) {
/* 138:138 */      lic = null;
/* 139:139 */      e.printStackTrace();
/* 140:    */    }
/* 141:141 */    return lic;
/* 142:    */  }
/* 143:    */  
/* 144:    */  private static String getPropertyValue(Element root, String propName) {
/* 145:145 */    List<Element> eles = root.getChildren();
/* 146:146 */    for (Element ele : eles) {
/* 147:147 */      if (ele.getAttributeValue("Name").equals(propName))
/* 148:148 */        return ele.getAttributeValue("Value");
/* 149:    */    }
/* 150:150 */    return null;
/* 151:    */  }
/* 152:    */  
/* 153:    */  public static int getDiffDays(String time1, String time2) {
/* 154:    */    try {
/* 155:155 */      long diffTime = dateFormat.parse(time2).getTime() - dateFormat.parse(time1).getTime();
/* 156:156 */      return (int)(diffTime / 1000L / 86400L);
/* 157:    */    } catch (ParseException e) {}
/* 158:158 */    return 0;
/* 159:    */  }
/* 160:    */  
/* 161:    */  public static void checkLicense(License lic)
/* 162:    */  {
/* 163:163 */    String mid = getMachineID();
/* 164:164 */    if (lic.getCode() == null)
/* 165:165 */      throw new IllegalStateException("无效License1,设备ID=" + mid);
/* 166:166 */    if (lic.getLicenseType().equals("Trial")) {
/* 167:167 */      int days = getDiffDays(lic.getCurrentDate(), lic.getExpiryDate());
/* 168:168 */      if (days <= 0) {
/* 169:169 */        throw new IllegalStateException("试用版已过期,设备ID=" + mid);
/* 170:    */      }
/* 171:171 */    } else if (!lic.getMachineID().equals(mid)) {
/* 172:172 */      throw new IllegalStateException("无效License2,设备ID=" + mid);
/* 173:    */    }
/* 174:    */  }
/* 175:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.framework.license.LicenseTool
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */