/*  1:   */package com.soofound.cpe.util;
/*  2:   */
/*  3:   */import java.io.PrintStream;
/*  4:   */import org.springframework.beans.factory.InitializingBean;
/*  5:   */
/*  6:   */public final class StunServerLauncher implements InitializingBean
/*  7:   */{
/*  8:   */  public static boolean STARTED;
/*  9:   */  private int port;
/* 10:   */  private String address;
/* 11:   */  private com.soofound.protocol.stun.StunServer stunServer;
/* 12:   */  
/* 13:   */  public void afterPropertiesSet() throws Exception
/* 14:   */  {
/* 15:   */    try
/* 16:   */    {
/* 17:17 */      this.stunServer = new com.soofound.protocol.stun.StunServer(this.port);
/* 18:18 */      this.stunServer.startServer();
/* 19:19 */      STARTED = true;
/* 20:   */    } catch (java.io.IOException e) {
/* 21:21 */      System.out.println("IOException:" + e.getMessage());
/* 22:   */    }
/* 23:   */  }
/* 24:   */  
/* 25:   */  public int getPort() {
/* 26:26 */    return this.port;
/* 27:   */  }
/* 28:   */  
/* 29:   */  public void setPort(int port) {
/* 30:30 */    this.port = port;
/* 31:   */  }
/* 32:   */  
/* 33:   */  public String getAddress() {
/* 34:34 */    return this.address;
/* 35:   */  }
/* 36:   */  
/* 37:   */  public void setAddress(String address) {
/* 38:38 */    this.address = address;
/* 39:   */  }
/* 40:   */  
/* 41:   */  public com.soofound.protocol.stun.StunServer getStunServer() {
/* 42:42 */    return this.stunServer;
/* 43:   */  }
/* 44:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.cpe.util.StunServerLauncher
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */