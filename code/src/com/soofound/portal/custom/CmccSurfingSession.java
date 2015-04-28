/*  1:   */package com.soofound.portal.custom;
/*  2:   */
/*  3:   */import com.soofound.framework.util.SysConfigHelper;
/*  4:   */import com.soofound.portal.service.SurfingSessionStore;
/*  5:   */import java.util.Map;
/*  6:   */import net.jradius.client.RadiusClient;
/*  7:   */import net.jradius.dictionary.Attr_AcctInputOctets;
/*  8:   */import net.jradius.dictionary.Attr_AcctOutputOctets;
/*  9:   */import net.jradius.packet.RadiusResponse;
/* 10:   */import net.jradius.packet.attribute.RadiusAttribute;
/* 11:   */
/* 12:   */public class CmccSurfingSession implements SurfingSessionStore
/* 13:   */{
/* 14:   */  private CustomFactory cf;
/* 15:   */  private ThirdPartyHandler handler;
/* 16:   */  
/* 17:   */  public CmccSurfingSession()
/* 18:   */  {
/* 19:19 */    this.cf = ((CustomFactory)SysConfigHelper.getBean("customFactory"));
/* 20:20 */    this.handler = ((ThirdPartyHandler)SysConfigHelper.getBean("zhongxingHandler"));
/* 21:   */  }
/* 22:   */  
/* 23:   */  public void recordOnline(com.soofound.portal.bean.SurfingUser user)
/* 24:   */  {
/* 25:   */    try {
/* 26:26 */      net.jradius.packet.attribute.AttributeList attrs = (net.jradius.packet.attribute.AttributeList)this.cf.getAttributes().get(user.getSessionId());
/* 27:27 */      if (attrs == null) {
/* 28:28 */        System.out.println(user.getSessionId() + "#AttributeList is empty");
/* 29:29 */        return;
/* 30:   */      }
/* 31:31 */      for (RadiusAttribute ra : attrs.getAttributeList()) {
/* 32:32 */        System.out.println(ra.getAttributeName() + "--" + ra.getValue());
/* 33:   */      }
/* 34:34 */      net.jradius.packet.AccountingRequest arp = new net.jradius.packet.AccountingRequest(this.handler.getRadiusClient(), attrs);
/* 35:35 */      arp.setAccountingStatusType(1);
/* 36:36 */      Object rr = this.handler.getRadiusClient().accounting(arp, 3);
/* 37:37 */      System.out.println(user.getSessionId() + "----recordOnline----" + ((RadiusResponse)rr).getCode());
/* 38:   */    } catch (Exception ex) {
/* 39:39 */      ex.printStackTrace();
/* 40:   */    }
/* 41:   */  }
/* 42:   */  
/* 43:   */  public void recordTraffic(com.soofound.portal.bean.SurfingUser user)
/* 44:   */  {
/* 45:   */    try {
/* 46:46 */      net.jradius.packet.attribute.AttributeList attrs = (net.jradius.packet.attribute.AttributeList)this.cf.getAttributes().get(user.getSessionId());
/* 47:47 */      if (attrs == null) {
/* 48:48 */        System.out.println(user.getSessionId() + "#AttributeList is empty");
/* 49:49 */        return;
/* 50:   */      }
/* 51:51 */      attrs.add(new Attr_AcctOutputOctets(Long.valueOf(user.getOutputOctets())));
/* 52:52 */      attrs.add(new Attr_AcctInputOctets(Long.valueOf(user.getInputOctets())));
/* 53:53 */      net.jradius.packet.AccountingRequest arp = new net.jradius.packet.AccountingRequest(this.handler.getRadiusClient(), attrs);
/* 54:54 */      arp.setAccountingStatusType(3);
/* 55:55 */      RadiusResponse rr = this.handler.getRadiusClient().accounting(arp, 3);
/* 56:56 */      System.out.println(user.getSessionId() + "----recordTraffic----" + rr.getCode());
/* 57:   */    } catch (Exception ex) {
/* 58:58 */      ex.printStackTrace();
/* 59:   */    }
/* 60:   */  }
/* 61:   */  
/* 62:   */  public void recordOffline(com.soofound.portal.bean.SurfingUser user)
/* 63:   */  {
/* 64:   */    try {
/* 65:65 */      net.jradius.packet.attribute.AttributeList attrs = (net.jradius.packet.attribute.AttributeList)this.cf.getAttributes().get(user.getSessionId());
/* 66:66 */      if (attrs == null) {
/* 67:67 */        System.out.println(user.getSessionId() + "#AttributeList is empty");
/* 68:68 */        return;
/* 69:   */      }
/* 70:70 */      net.jradius.packet.AccountingRequest arp = new net.jradius.packet.AccountingRequest(this.handler.getRadiusClient(), attrs);
/* 71:71 */      arp.setAccountingStatusType(2);
/* 72:72 */      RadiusResponse rr = this.handler.getRadiusClient().accounting(arp, 3);
/* 73:73 */      System.out.println(user.getSessionId() + "----recordOffline----" + rr.getCode());
/* 74:74 */      this.cf.getAttributes().remove(user.getSessionId());
/* 75:   */    } catch (Exception ex) {
/* 76:76 */      ex.printStackTrace();
/* 77:   */    }
/* 78:   */  }
/* 79:   */  
/* 80:   */  public void recordRoaming(com.soofound.portal.bean.SurfingUser user) {}
/* 81:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.custom.CmccSurfingSession
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */