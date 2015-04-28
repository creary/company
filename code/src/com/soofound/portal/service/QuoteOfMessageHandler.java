/*  1:   */package com.soofound.portal.service;
/*  2:   */
/*  3:   */import com.soofound.framework.util.SysConfigHelper;
/*  4:   */import com.soofound.portal.custom.CustomFactory;
/*  5:   */import com.soofound.portal.custom.ThirdPartyHandler;
/*  6:   */import io.netty.buffer.ByteBuf;
/*  7:   */import io.netty.channel.ChannelHandlerContext;
/*  8:   */import io.netty.channel.SimpleChannelInboundHandler;
/*  9:   */import java.net.InetSocketAddress;
/* 10:   */import java.util.Map;
/* 11:   */import java.util.concurrent.ConcurrentHashMap;
/* 12:   */import org.springframework.stereotype.Service;
/* 13:   */
/* 14:   */@Service
/* 15:   */public class QuoteOfMessageHandler extends SimpleChannelInboundHandler<io.netty.channel.socket.DatagramPacket>
/* 16:   */{
/* 17:   */  private final Map<String, String> IPAndMacs;
/* 18:   */  private final CustomFactory cf;
/* 19:   */  
/* 20:   */  public QuoteOfMessageHandler()
/* 21:   */  {
/* 22:22 */    this.IPAndMacs = new ConcurrentHashMap();
/* 23:23 */    this.cf = ((CustomFactory)SysConfigHelper.getBean("customFactory"));
/* 24:   */  }
/* 25:   */  
/* 26:   */  public void messageReceived(ChannelHandlerContext ctx, io.netty.channel.socket.DatagramPacket dp) throws Exception
/* 27:   */  {
/* 28:   */    try {
/* 29:29 */      String sender = ((InetSocketAddress)dp.sender()).toString();
/* 30:30 */      int loc1 = sender.indexOf("/");
/* 31:31 */      int loc2 = sender.indexOf(":");
/* 32:32 */      String remoteIP = sender.substring(loc1 + 1, loc2);
/* 33:33 */      int remotePort = Integer.parseInt(sender.substring(loc2 + 1));
/* 34:   */      
/* 35:35 */      byte[] bytes = new byte[((ByteBuf)dp.content()).readableBytes()];
/* 36:36 */      ((ByteBuf)dp.content()).readBytes(bytes);
/* 37:37 */      com.soofound.protocol.cmcc.PortalPacket pp = com.soofound.protocol.cmcc.PortalPacket.parse(bytes);
/* 38:38 */      pp.setRemoteIP(remoteIP);
/* 39:39 */      pp.setRemotePort(remotePort);
/* 40:   */      
/* 41:41 */      this.cf.getThirdPartyHandler(remoteIP).doHandle(pp);
/* 42:   */    } catch (Exception ex) {
/* 43:43 */      ex.printStackTrace();
/* 44:   */    }
/* 45:   */  }
/* 46:   */  
/* 47:   */  public String getMACs(String ips) {
/* 48:48 */    return (String)this.IPAndMacs.get(ips);
/* 49:   */  }
/* 50:   */  
/* 51:   */  public String removeMACs(String ips) {
/* 52:52 */    return (String)this.IPAndMacs.remove(ips);
/* 53:   */  }
/* 54:   */  
/* 55:   */  public void putIPAndMAC(String ips, String macs) {
/* 56:56 */    this.IPAndMacs.put(ips, macs);
/* 57:   */  }
/* 58:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.service.QuoteOfMessageHandler
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */