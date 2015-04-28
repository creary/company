/*  1:   */package com.soofound.portal.custom;
/*  2:   */
/*  3:   */import com.soofound.framework.util.SysConfigHelper;
/*  4:   */import com.soofound.portal.bean.SurfingUser;
/*  5:   */import com.soofound.portal.service.WifiDogService;
/*  6:   */import io.netty.buffer.ByteBuf;
/*  7:   */import io.netty.channel.ChannelHandlerContext;
/*  8:   */import io.netty.channel.SimpleChannelInboundHandler;
/*  9:   */import java.io.PrintStream;
/* 10:   */import java.nio.ByteBuffer;
/* 11:   */import net.jradius.packet.PacketFactory;
/* 12:   */import net.jradius.packet.RadiusPacket;
/* 13:   */import net.jradius.packet.attribute.AttributeList;
/* 14:   */import net.jradius.packet.attribute.value.AttributeValue;
/* 15:   */
/* 16:   */public class RadiusHandler extends SimpleChannelInboundHandler<io.netty.channel.socket.DatagramPacket>
/* 17:   */{
/* 18:   */  private WifiDogService wds;
/* 19:   */  
/* 20:   */  public RadiusHandler()
/* 21:   */  {
/* 22:22 */    this.wds = ((WifiDogService)SysConfigHelper.getBean("wifiDogService"));
/* 23:   */  }
/* 24:   */  
/* 25:   */  public void messageReceived(ChannelHandlerContext ctx, io.netty.channel.socket.DatagramPacket dp) throws Exception
/* 26:   */  {
/* 27:27 */    byte[] bytes = new byte[((ByteBuf)dp.content()).readableBytes()];
/* 28:28 */    ((ByteBuf)dp.content()).readBytes(bytes);
/* 29:29 */    RadiusPacket rp = PacketFactory.parseUDP(ByteBuffer.wrap(bytes));
/* 30:30 */    AttributeList al = rp.getAttributes();
/* 31:31 */    String username = null;
/* 32:32 */    String ipAddress = null;
/* 33:33 */    for (net.jradius.packet.attribute.RadiusAttribute ra : al.getAttributeList()) {
/* 34:34 */      System.out.println(ra.getAttributeName() + "==" + ra.getValue());
/* 35:35 */      if (ra.getAttributeName().equals("User-Name"))
/* 36:36 */        username = ra.getValue().toString();
/* 37:37 */      if (ra.getAttributeName().equals("Framed-IP-Address"))
/* 38:38 */        ipAddress = ra.getValue().toString();
/* 39:   */    }
/* 40:40 */    SurfingUser user = this.wds.getSurfingUser5(username, ipAddress);
/* 41:41 */    if (user != null) {
/* 42:42 */      System.out.println("DisconnectRequest==" + username + "[" + ipAddress + "]");
/* 43:43 */      this.wds.recordOffline(user);
/* 44:   */    }
/* 45:   */  }
/* 46:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.custom.RadiusHandler
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */