/*  1:   */package com.soofound.portal.util;
/*  2:   */
/*  3:   */import com.soofound.framework.util.SimpleXMLUtil;
/*  4:   */import com.soofound.framework.util.SysConfigHelper;
/*  5:   */import java.util.HashMap;
/*  6:   */import java.util.List;
/*  7:   */import java.util.Map;
/*  8:   */import javax.annotation.PostConstruct;
/*  9:   */import org.jdom2.Document;
/* 10:   */import org.jdom2.Element;
/* 11:   */import org.springframework.stereotype.Service;
/* 12:   */
/* 13:   */@Service
/* 14:   */public class OuiFactory
/* 15:   */{
/* 16:   */  private Map<String, String> ouis;
/* 17:   */  
/* 18:   */  @PostConstruct
/* 19:   */  public void start()
/* 20:   */  {
/* 21:21 */    load();
/* 22:   */  }
/* 23:   */  
/* 24:   */  public void load() {
/* 25:25 */    this.ouis = new HashMap();
/* 26:26 */    Element root = SimpleXMLUtil.file2Doc(SysConfigHelper.getAttribute("configPath") + "/oui.xml").getRootElement();
/* 27:27 */    List<Element> items = root.getChildren("item");
/* 28:28 */    for (Element item : items) {
/* 29:29 */      String val = item.getAttributeValue("cn_name");
/* 30:30 */      if ("".equals(val)) {
/* 31:31 */        String ent = item.getAttributeValue("enterprise");
/* 32:32 */        val = ent.split(" ")[0];
/* 33:   */      }
/* 34:34 */      this.ouis.put(item.getAttributeValue("oui"), val);
/* 35:   */    }
/* 36:   */  }
/* 37:   */  
/* 38:   */  public String getBrand(String mac) {
/* 39:39 */    String result = null;
/* 40:40 */    if (mac != null)
/* 41:41 */      result = (String)this.ouis.get(mac.substring(0, 8));
/* 42:42 */    if (result == null)
/* 43:43 */      return "未知";
/* 44:44 */    return result;
/* 45:   */  }
/* 46:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.util.OuiFactory
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */