/*  1:   */package com.soofound.portal.service;
/*  2:   */
/*  3:   */import com.soofound.portal.custom.RadiusHandler;
/*  4:   */import io.netty.channel.Channel;
/*  5:   */import io.netty.channel.ChannelOption;
/*  6:   */import io.netty.channel.EventLoopGroup;
/*  7:   */import io.netty.channel.nio.NioEventLoopGroup;
/*  8:   */import io.netty.channel.socket.nio.NioDatagramChannel;
/*  9:   */import javax.annotation.PostConstruct;
/* 10:   */import org.springframework.beans.factory.annotation.Autowired;
/* 11:   */import org.springframework.stereotype.Service;
/* 12:   */
/* 13:   */@Service
/* 14:   */public class QuoteOfMessageServer
/* 15:   */{
/* 16:   */  public static final int PORTAL_PORT = 2000;
/* 17:   */  public static final int RADIUS_PORT = 3799;
/* 18:   */  @Autowired
/* 19:   */  private QuoteOfMessageHandler handler;
/* 20:   */  
/* 21:   */  @PostConstruct
/* 22:   */  public void start()
/* 23:   */  {
/* 24:24 */    Runnable udpServer1 = new Runnable()
/* 25:   */    {
/* 26:   */      public void run() {
/* 27:27 */        EventLoopGroup group = new NioEventLoopGroup();
/* 28:   */        try {
/* 29:29 */          io.netty.bootstrap.Bootstrap b = new io.netty.bootstrap.Bootstrap();
/* 30:   */          
/* 33:33 */          ((io.netty.bootstrap.Bootstrap)((io.netty.bootstrap.Bootstrap)((io.netty.bootstrap.Bootstrap)b.group(group)).channel(NioDatagramChannel.class).option(ChannelOption.SO_BROADCAST, Boolean.valueOf(false))).option(ChannelOption.SO_REUSEADDR, Boolean.valueOf(true)))
/* 34:34 */            .handler(QuoteOfMessageServer.this.handler);
/* 35:35 */          Channel channel = b.bind(2000).sync().channel();
/* 36:36 */          channel.closeFuture().sync();
/* 37:   */        } catch (Exception e) {
/* 38:38 */          e.printStackTrace();
/* 39:   */        } finally {
/* 40:40 */          group.shutdownGracefully();
/* 41:   */        }
/* 42:   */      }
/* 43:43 */    };
/* 44:44 */    Thread thread1 = new Thread(udpServer1);
/* 45:45 */    thread1.start();
/* 46:   */    
/* 47:47 */    Runnable udpServer2 = new Runnable()
/* 48:   */    {
/* 49:   */      public void run() {
/* 50:50 */        EventLoopGroup group = new NioEventLoopGroup();
/* 51:   */        try {
/* 52:52 */          io.netty.bootstrap.Bootstrap b = new io.netty.bootstrap.Bootstrap();
/* 53:   */          
/* 56:56 */          ((io.netty.bootstrap.Bootstrap)((io.netty.bootstrap.Bootstrap)((io.netty.bootstrap.Bootstrap)b.group(group)).channel(NioDatagramChannel.class).option(ChannelOption.SO_BROADCAST, Boolean.valueOf(false))).option(ChannelOption.SO_REUSEADDR, Boolean.valueOf(true)))
/* 57:57 */            .handler(new RadiusHandler());
/* 58:58 */          Channel channel = b.bind(3799).sync().channel();
/* 59:59 */          channel.closeFuture().sync();
/* 60:   */        } catch (Exception e) {
/* 61:61 */          e.printStackTrace();
/* 62:   */        } finally {
/* 63:63 */          group.shutdownGracefully();
/* 64:   */        }
/* 65:   */      }
/* 66:66 */    };
/* 67:67 */    Thread thread2 = new Thread(udpServer2);
/* 68:68 */    thread2.start();
/* 69:   */  }
/* 70:   */  
/* 71:   */  public QuoteOfMessageHandler getHandler() {
/* 72:72 */    return this.handler;
/* 73:   */  }
/* 74:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.service.QuoteOfMessageServer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */