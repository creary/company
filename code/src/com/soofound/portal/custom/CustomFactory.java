/*   1:    */package com.soofound.portal.custom;
/*   2:    */
/*   3:    */import com.soofound.cpe.util.DeviceTree;
/*   4:    */import com.soofound.cpe.util.GenericDeviceTree;
/*   5:    */import com.soofound.cpe.util.SoofacACS;
/*   6:    */import com.soofound.cpe.util.WifiantDeviceTree;
/*   7:    */import com.soofound.framework.util.CommonUtil;
/*   8:    */import com.soofound.framework.util.SysConfigHelper;
/*   9:    */import com.soofound.portal.service.SurfingSessionStore;
/*  10:    */import com.soofound.project.ehulian.EhulianDeviceTree;
/*  11:    */import com.soofound.project.wifibeijing.WifiBeijingDeviceTree;
/*  12:    */import java.util.ArrayList;
/*  13:    */import java.util.HashMap;
/*  14:    */import java.util.List;
/*  15:    */import java.util.Map;
/*  16:    */import net.jradius.packet.attribute.AttributeList;
/*  17:    */
/*  18:    */public class CustomFactory
/*  19:    */{
/*  20:    */  private SmsSender smsSender;
/*  21:    */  private SurfingSessionStore sessionStore;
/*  22:    */  private Map<String, ThirdPartyHandler> thirdPartyHandlers;
/*  23:    */  private Map<String, String> deviceImages;
/*  24:    */  private List<String> brands;
/*  25:    */  private DeviceTree deviceTree;
/*  26:    */  private Map<String, AttributeList> attributes;
/*  27:    */  
/*  28:    */  public CustomFactory()
/*  29:    */  {
/*  30: 30 */    this.attributes = new HashMap();
/*  31:    */  }
/*  32:    */  
/*  33:    */  public Map<String, AttributeList> getAttributes() {
/*  34: 34 */    return this.attributes;
/*  35:    */  }
/*  36:    */  
/*  37:    */  public SmsSender getSmsSender() {
/*  38: 38 */    return this.smsSender;
/*  39:    */  }
/*  40:    */  
/*  41:    */  public void setSmsSenderClazz(String smsSenderClazz) {
/*  42: 42 */    this.smsSender = ((SmsSender)CommonUtil.getInstance(smsSenderClazz));
/*  43:    */  }
/*  44:    */  
/*  45:    */  public SurfingSessionStore getSessionStore() {
/*  46: 46 */    return this.sessionStore;
/*  47:    */  }
/*  48:    */  
/*  49:    */  public void setSessionStoreClazz(String sessionStoreClazz) {
/*  50: 50 */    this.sessionStore = ((SurfingSessionStore)CommonUtil.getInstance(sessionStoreClazz));
/*  51:    */  }
/*  52:    */  
/*  53:    */  public void setThirdPartyHandlers(List<ThirdPartyHandler> handlers) {
/*  54: 54 */    this.thirdPartyHandlers = new HashMap();
/*  55: 55 */    for (ThirdPartyHandler handler : handlers) {
/*  56: 56 */      this.thirdPartyHandlers.put(handler.getPortalIP(), handler);
/*  57:    */    }
/*  58:    */  }
/*  59:    */  
/*  60:    */  public ThirdPartyHandler getThirdPartyHandler(String portalIP) {
/*  61: 61 */    return (ThirdPartyHandler)this.thirdPartyHandlers.get(portalIP);
/*  62:    */  }
/*  63:    */  
/*  64:    */  public Map<String, ThirdPartyHandler> getHandlers() {
/*  65: 65 */    return this.thirdPartyHandlers;
/*  66:    */  }
/*  67:    */  
/*  68:    */  public String getDeviceImage(String model) {
/*  69: 69 */    return (String)this.deviceImages.get(model);
/*  70:    */  }
/*  71:    */  
/*  72:    */  public void setDeviceImages(Map<String, String> deviceImages) {
/*  73: 73 */    this.deviceImages = deviceImages;
/*  74:    */  }
/*  75:    */  
/*  76:    */  public boolean isBigBrand(String brand) {
/*  77: 77 */    return this.brands.contains(brand);
/*  78:    */  }
/*  79:    */  
/*  80:    */  public void setBrands(String brandString) {
/*  81: 81 */    String[] strs = brandString.split(";");
/*  82: 82 */    this.brands = new ArrayList();
/*  83: 83 */    for (String str : strs)
/*  84: 84 */      this.brands.add(str);
/*  85:    */  }
/*  86:    */  
/*  87:    */  public DeviceTree getDeviceTree() {
/*  88: 88 */    if (this.deviceTree == null) {
/*  89: 89 */      SoofacACS acs = (SoofacACS)SysConfigHelper.getBean("soofacACS");
/*  90: 90 */      if (acs.getRealm().equals("wifiant")) {
/*  91: 91 */        this.deviceTree = new WifiantDeviceTree();
/*  92: 92 */      } else if (acs.getRealm().startsWith("wifiBeijing")) {
/*  93: 93 */        this.deviceTree = new WifiBeijingDeviceTree();
/*  94: 94 */      } else if (acs.getRealm().startsWith("ehulian")) {
/*  95: 95 */        this.deviceTree = new WifiantDeviceTree();
/*  96: 96 */      } else if (acs.getRealm().startsWith("qhwifi")) {
/*  97: 97 */        this.deviceTree = new EhulianDeviceTree();
/*  98:    */      } else
/*  99: 99 */        this.deviceTree = new GenericDeviceTree();
/* 100:    */    }
/* 101:101 */    return this.deviceTree;
/* 102:    */  }
/* 103:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.custom.CustomFactory
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */