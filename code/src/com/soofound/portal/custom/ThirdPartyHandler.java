/*   1:    */package com.soofound.portal.custom;
/*   2:    */
/*   3:    */import com.soofound.cpe.bean.HostBean;
/*   4:    */import com.soofound.cpe.util.CpeActionExecutor;
/*   5:    */import com.soofound.cpe.util.StunServerLauncher;
/*   6:    */import com.soofound.framework.util.SysConfigHelper;
/*   7:    */import com.soofound.protocol.cmcc.PortalPacket;
/*   8:    */import com.soofound.protocol.stun.StunServer;
/*   9:    */import java.io.PrintStream;
/*  10:    */import java.net.DatagramSocket;
/*  11:    */import java.net.InetAddress;
/*  12:    */import java.net.InetSocketAddress;
/*  13:    */import java.util.HashMap;
/*  14:    */import java.util.Map;
/*  15:    */import net.jradius.client.RadiusClient;
/*  16:    */import net.jradius.packet.attribute.AttributeFactory;
/*  17:    */
/*  18:    */public abstract class ThirdPartyHandler
/*  19:    */{
/*  20: 20 */  private static final byte[] UDP_REQUEST = "please connect acs, thanks.\n\r".getBytes();
/*  21:    */  
/*  22:    */  protected final CpeActionExecutor cae;
/*  23:    */  protected RadiusClient radiusClient;
/*  24:    */  protected String portalIP;
/*  25:    */  protected String radiusIP;
/*  26:    */  protected String secret;
/*  27:    */  protected int authPort;
/*  28:    */  protected int acctPort;
/*  29:    */  protected int timeout;
/*  30:    */  protected int noFeeling;
/*  31:    */  private Map<Integer, net.jradius.packet.attribute.AttributeList> attrStore;
/*  32:    */  
/*  33:    */  public ThirdPartyHandler()
/*  34:    */  {
/*  35: 35 */    this.cae = new CpeActionExecutor();
/*  36: 36 */    this.authPort = 1812;
/*  37: 37 */    this.acctPort = 1813;
/*  38: 38 */    this.timeout = 1000;
/*  39: 39 */    this.attrStore = new HashMap();
/*  40:    */  }
/*  41:    */  
/*  42:    */  public void wakeup(HostBean host) {
/*  43: 43 */    StunServerLauncher ssl = (StunServerLauncher)SysConfigHelper.getBean("stunServer");
/*  44:    */    try {
/*  45: 45 */      String[] stun = host.getStun().split("-");
/*  46: 46 */      java.net.DatagramPacket packet = new java.net.DatagramPacket(UDP_REQUEST, UDP_REQUEST.length);
/*  47: 47 */      packet.setAddress(InetAddress.getByName(stun[0]));
/*  48: 48 */      packet.setPort(Integer.parseInt(stun[1]));
/*  49: 49 */      ssl.getStunServer().sendUDP(packet);
/*  50:    */    } catch (Exception e) {
/*  51: 51 */      System.err.println(host.getSerialNumber() + "#wakeup error:" + host.getStun());
/*  52:    */    }
/*  53:    */  }
/*  54:    */  
/*  55:    */  public void callback(PortalPacket pp, String remoteIP, int remotePort) {
/*  56: 56 */    DatagramSocket ds = null;
/*  57:    */    try {
/*  58: 58 */      ds = new DatagramSocket(null);
/*  59: 59 */      ds.setReuseAddress(true);
/*  60: 60 */      ds.bind(new InetSocketAddress(2000));
/*  61: 61 */      byte[] ppbytes = pp.getBytes();
/*  62: 62 */      java.net.DatagramPacket packet = new java.net.DatagramPacket(ppbytes, ppbytes.length);
/*  63: 63 */      packet.setAddress(InetAddress.getByName(remoteIP));
/*  64: 64 */      packet.setPort(remotePort);
/*  65: 65 */      ds.send(packet);
/*  66:    */      
/*  67: 67 */      System.out.println("callback:" + remoteIP + "/" + remotePort + "#" + pp.getClass().getSimpleName());
/*  68:    */    } catch (Exception e) {
/*  69: 69 */      e.printStackTrace();
/*  70:    */      try
/*  71:    */      {
/*  72: 72 */        ds.close(); } catch (Exception localException1) {} } finally { try { ds.close();
/*  73:    */      }
/*  74:    */      catch (Exception localException2) {}
/*  75:    */    }
/*  76:    */  }
/*  77:    */  
/*  78:    */  public String getRadiusIP() {
/*  79: 79 */    return this.radiusIP;
/*  80:    */  }
/*  81:    */  
/*  82:    */  public String getSecret() {
/*  83: 83 */    return this.secret;
/*  84:    */  }
/*  85:    */  
/*  86:    */  public String getPortalIP() {
/*  87: 87 */    return this.portalIP;
/*  88:    */  }
/*  89:    */  
/*  90:    */  public void setPortalIP(String portalIP) {
/*  91: 91 */    this.portalIP = portalIP;
/*  92:    */  }
/*  93:    */  
/*  94:    */  public void setRadiusIP(String radiusIP) {
/*  95: 95 */    this.radiusIP = radiusIP;
/*  96:    */  }
/*  97:    */  
/*  98:    */  public void setSecret(String secret) {
/*  99: 99 */    this.secret = secret;
/* 100:    */  }
/* 101:    */  
/* 102:    */  public void setAuthPort(int authPort) {
/* 103:103 */    this.authPort = authPort;
/* 104:    */  }
/* 105:    */  
/* 106:    */  public void setAcctPort(int acctPort) {
/* 107:107 */    this.acctPort = acctPort;
/* 108:    */  }
/* 109:    */  
/* 110:    */  public void setTimeout(int timeout) {
/* 111:111 */    this.timeout = timeout;
/* 112:    */  }
/* 113:    */  
/* 114:    */  public int getNoFeeling() {
/* 115:115 */    return this.noFeeling;
/* 116:    */  }
/* 117:    */  
/* 118:    */  public void setNoFeeling(int noFeeling) {
/* 119:119 */    this.noFeeling = noFeeling;
/* 120:    */  }
/* 121:    */  
/* 122:    */  public net.jradius.packet.attribute.AttributeList getAttributes(int id) {
/* 123:123 */    return (net.jradius.packet.attribute.AttributeList)this.attrStore.get(Integer.valueOf(id));
/* 124:    */  }
/* 125:    */  
/* 126:    */  public void putAttributes(int id, net.jradius.packet.attribute.AttributeList attrs) {
/* 127:127 */    this.attrStore.put(Integer.valueOf(id), attrs);
/* 128:    */  }
/* 129:    */  
/* 130:    */  public net.jradius.packet.attribute.AttributeList removeAttributes(int serialNo) {
/* 131:131 */    return (net.jradius.packet.attribute.AttributeList)this.attrStore.remove(Integer.valueOf(serialNo));
/* 132:    */  }
/* 133:    */  
/* 134:    */  public RadiusClient getRadiusClient() {
/* 135:135 */    if (this.radiusClient == null) {
/* 136:    */      try {
/* 137:137 */        this.radiusClient = new RadiusClient(InetAddress.getByName(this.radiusIP), this.secret, this.authPort, this.acctPort, this.timeout);
/* 138:138 */        AttributeFactory.loadAttributeDictionary("net.jradius.dictionary.AttributeDictionaryImpl");
/* 139:    */      } catch (Exception ex) {
/* 140:140 */        ex.printStackTrace();
/* 141:    */      }
/* 142:    */    }
/* 143:143 */    return this.radiusClient;
/* 144:    */  }
/* 145:    */  
/* 146:    */  public abstract void doHandle(PortalPacket paramPortalPacket);
/* 147:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.custom.ThirdPartyHandler
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */