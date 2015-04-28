/*  1:   */package com.soofound.cpe.util;
/*  2:   */
/*  3:   */import com.danga.MemCached.MemCachedClient;
/*  4:   */import com.soofound.cpe.bean.HostBean;
/*  5:   */import com.soofound.cpe.dao.HostDao;
/*  6:   */import com.soofound.framework.util.SysConfigHelper;
/*  7:   */import com.soofound.protocol.stun.StunServer;
/*  8:   */import java.io.PrintStream;
/*  9:   */import java.net.DatagramPacket;
/* 10:   */import java.net.InetAddress;
/* 11:   */import java.net.InetSocketAddress;
/* 12:   */
/* 13:   */public class CpeWaker
/* 14:   */{
/* 15:   */  private boolean wakeup(String[] stun)
/* 16:   */  {
/* 17:17 */    boolean result = false;
/* 18:   */    try {
/* 19:19 */      DatagramPacket packet = new DatagramPacket(CpeUtils.UDP_REQUEST, CpeUtils.UDP_REQUEST.length);
/* 20:20 */      packet.setAddress(InetAddress.getByName(stun[0]));
/* 21:21 */      packet.setPort(Integer.parseInt(stun[1]));
/* 22:22 */      if (StunServerLauncher.STARTED) {
/* 23:23 */        StunServerLauncher ssl = (StunServerLauncher)SysConfigHelper.getBean("stunServer");
/* 24:24 */        ssl.getStunServer().sendUDP(packet);
/* 25:   */      } else {
/* 26:26 */        java.net.DatagramSocket ds = new java.net.DatagramSocket(null);
/* 27:27 */        ds.setReuseAddress(true);
/* 28:28 */        ds.bind(new InetSocketAddress(8888));
/* 29:29 */        ds.send(packet);
/* 30:   */      }
/* 31:31 */      System.out.println("#wakeUpCpe:" + stun[0] + ",port=" + stun[1]);
/* 32:32 */      Thread.sleep(500L);
/* 33:33 */      result = true;
/* 34:   */    } catch (Exception ex) {
/* 35:35 */      ex.printStackTrace();
/* 36:36 */      System.out.println("#error stun:" + stun[0] + ":" + stun[1]);
/* 37:   */    }
/* 38:38 */    return result;
/* 39:   */  }
/* 40:   */  
/* 41:   */  public boolean wakeup(String apid) {
/* 42:42 */    HostDao dao = new HostDao();
/* 43:43 */    HostBean host = dao.findByID(apid);
/* 44:44 */    if (host == null) {
/* 45:45 */      System.out.println("ap not exist:" + apid);
/* 46:46 */      return false;
/* 47:   */    }
/* 48:48 */    if (StunServerLauncher.STARTED) {
/* 49:49 */      return wakeup(host.getStun().split("-"));
/* 50:   */    }
/* 51:51 */    MemCachedClient mcc = (MemCachedClient)SysConfigHelper.getBean("memcachedClient");
/* 52:52 */    String ipkey = "ip_" + host.getSerialNumber().replace(":", "_");
/* 53:53 */    if (mcc.get(ipkey) == null) {
/* 54:54 */      System.out.println("not Long connect info:" + ipkey);
/* 55:55 */      return false;
/* 56:   */    }
/* 57:57 */    return wakeup(((String)mcc.get(ipkey)).split("_"));
/* 58:   */  }
/* 59:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.cpe.util.CpeWaker
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */