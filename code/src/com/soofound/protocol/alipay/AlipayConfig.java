/*  1:   */package com.soofound.protocol.alipay;
/*  2:   */
/*  3:   */import com.soofound.framework.util.SimpleXMLUtil;
/*  4:   */import com.soofound.framework.util.SysConfigHelper;
/*  5:   */import org.jdom2.Document;
/*  6:   */import org.jdom2.Element;
/*  7:   */
/*  8:   */public class AlipayConfig
/*  9:   */{
/* 10:10 */  public static String log_path = SysConfigHelper.getAttribute("configPath") + "log4app.log";
/* 11:11 */  public static String input_charset = "UTF-8";
/* 12:12 */  public static String sign_type = "MD5";
/* 13:13 */  public static String transport = "http";
/* 14:   */  private String partner;
/* 15:   */  private String key;
/* 16:   */  private String seller;
/* 17:   */  
/* 18:   */  public AlipayConfig()
/* 19:   */  {
/* 20:20 */    String xml_path = SysConfigHelper.getAttribute("configPath") + "alipay.xml";
/* 21:21 */    Document doc = SimpleXMLUtil.file2Doc(xml_path);
/* 22:22 */    Element root = doc.getRootElement();
/* 23:23 */    this.partner = root.getChildText("partner");
/* 24:24 */    this.key = root.getChildText("key");
/* 25:25 */    this.seller = root.getChildText("seller");
/* 26:   */  }
/* 27:   */  
/* 28:   */  public String getKey() {
/* 29:29 */    return this.key;
/* 30:   */  }
/* 31:   */  
/* 32:   */  public String getPartner() {
/* 33:33 */    return this.partner;
/* 34:   */  }
/* 35:   */  
/* 36:   */  public String getSeller() {
/* 37:37 */    return this.seller;
/* 38:   */  }
/* 39:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.alipay.AlipayConfig
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */