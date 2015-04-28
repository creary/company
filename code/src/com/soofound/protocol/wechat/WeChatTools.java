/*   1:    */package com.soofound.protocol.wechat;
/*   2:    */
/*   3:    */import com.soofound.framework.util.DateUtil;
/*   4:    */import com.soofound.protocol.wechat.message.InMessage;
/*   5:    */import com.soofound.protocol.wechat.message.NewsOutMessage;
/*   6:    */import com.soofound.protocol.wechat.message.TextOutMessage;
/*   7:    */import com.sun.image.codec.jpeg.JPEGCodec;
/*   8:    */import com.sun.image.codec.jpeg.JPEGEncodeParam;
/*   9:    */import com.sun.image.codec.jpeg.JPEGImageEncoder;
/*  10:    */import com.thoughtworks.xstream.XStream;
/*  11:    */import java.awt.Color;
/*  12:    */import java.awt.Graphics2D;
/*  13:    */import java.awt.Image;
/*  14:    */import java.awt.image.BufferedImage;
/*  15:    */import java.io.File;
/*  16:    */import java.io.FileOutputStream;
/*  17:    */import java.io.IOException;
/*  18:    */import java.io.InputStream;
/*  19:    */import java.io.UnsupportedEncodingException;
/*  20:    */import java.net.URL;
/*  21:    */import java.net.URLConnection;
/*  22:    */import java.util.ArrayList;
/*  23:    */import java.util.Collections;
/*  24:    */import java.util.Comparator;
/*  25:    */import java.util.List;
/*  26:    */import javax.imageio.ImageIO;
/*  27:    */import org.apache.commons.codec.digest.DigestUtils;
/*  28:    */
/*  38:    */public final class WeChatTools
/*  39:    */{
/*  40:    */  public static final String MESSAGE_TYPE_TEXT = "text";
/*  41:    */  public static final String MESSAGE_TYPE_EVENT = "event";
/*  42:    */  public static final String MESSAGE_TYPE_NEWS = "news";
/*  43:    */  public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";
/*  44:    */  public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
/*  45:    */  public static final String EVENT_TYPE_SCAN = "SCAN";
/*  46:    */  public static final String WECHAT_FLAG = "wechatOpenId=";
/*  47:    */  public static final String WIFI_TEXT = "wifi";
/*  48:    */  public static final String WIFI_ANT = "wifiant";
/*  49:    */  
/*  50:    */  private static final String inputStreamTOString(InputStream in)
/*  51:    */    throws UnsupportedEncodingException, IOException
/*  52:    */  {
/*  53: 53 */    if (in == null)
/*  54: 54 */      return "";
/*  55: 55 */    StringBuffer out = new StringBuffer();
/*  56: 56 */    byte[] b = new byte[4096];
/*  57: 57 */    int n; while ((n = in.read(b)) != -1) { int n;
/*  58: 58 */      out.append(new String(b, 0, n, "UTF-8"));
/*  59:    */    }
/*  60: 60 */    return out.toString();
/*  61:    */  }
/*  62:    */  
/*  66:    */  public static InMessage parsingInMessage(InputStream in)
/*  67:    */    throws Exception
/*  68:    */  {
/*  69: 69 */    String xmlstr = inputStreamTOString(in);
/*  70:    */    
/*  71: 71 */    XStream xs = XStreamFactory.init(false);
/*  72: 72 */    xs.ignoreUnknownElements();
/*  73: 73 */    xs.alias("xml", InMessage.class);
/*  74: 74 */    InMessage msg = (InMessage)xs.fromXML(xmlstr);
/*  75: 75 */    return msg;
/*  76:    */  }
/*  77:    */  
/*  78:    */  public static String outMessageToXML(NewsOutMessage om) throws Exception {
/*  79: 79 */    XStream xs = XStreamFactory.init(true);
/*  80: 80 */    xs.alias("xml", om.getClass());
/*  81: 81 */    return xs.toXML(om);
/*  82:    */  }
/*  83:    */  
/*  84:    */  public static String outMessageToXML(TextOutMessage om) throws Exception {
/*  85: 85 */    XStream xs = XStreamFactory.init(true);
/*  86: 86 */    xs.alias("xml", om.getClass());
/*  87: 87 */    return xs.toXML(om);
/*  88:    */  }
/*  89:    */  
/*  90:    */  public static final boolean checkSignature(String token, String signature, String timestamp, String nonce) {
/*  91: 91 */    List<String> params = new ArrayList();
/*  92: 92 */    params.add(token);
/*  93: 93 */    params.add(timestamp);
/*  94: 94 */    params.add(nonce);
/*  95: 95 */    Collections.sort(params, new Comparator()
/*  96:    */    {
/*  97:    */      public int compare(String o1, String o2) {
/*  98: 98 */        return o1.compareTo(o2);
/*  99:    */      }
/* 100:100 */    });
/* 101:101 */    String temp = (String)params.get(0) + (String)params.get(1) + (String)params.get(2);
/* 102:102 */    return DigestUtils.shaHex(temp).equals(signature);
/* 103:    */  }
/* 104:    */  
/* 105:    */  public static String getTextMessage(String fromUser, String toUser, String msg) throws Exception {
/* 106:106 */    TextOutMessage tom = new TextOutMessage();
/* 107:107 */    tom.setToUserName(fromUser);
/* 108:108 */    tom.setFromUserName(toUser);
/* 109:109 */    tom.setCreateTime(Long.valueOf(DateUtil.getCurrentLongDateTime()));
/* 110:110 */    tom.setMsgType("text");
/* 111:111 */    tom.setContent(msg);
/* 112:112 */    return outMessageToXML(tom);
/* 113:    */  }
/* 114:    */  
/* 115:    */  public static boolean createImageFromURL(String urlstr, String jpgPath) {
/* 116:    */    try {
/* 117:117 */      URL url = new URL(urlstr);
/* 118:118 */      URLConnection connection = url.openConnection();
/* 119:119 */      connection.setDoOutput(true);
/* 120:120 */      BufferedImage bi = ImageIO.read(connection.getInputStream());
/* 121:    */      
/* 122:122 */      FileOutputStream outFile = new FileOutputStream(jpgPath);
/* 123:123 */      JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(outFile);
/* 124:124 */      JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bi);
/* 125:125 */      param.setQuality(1.0F, false);
/* 126:126 */      encoder.setJPEGEncodeParam(param);
/* 127:127 */      encoder.encode(bi);
/* 128:128 */      outFile.close();
/* 129:129 */      return true;
/* 130:    */    } catch (Exception ex) {
/* 131:131 */      ex.printStackTrace();
/* 132:    */    }
/* 133:133 */    return false;
/* 134:    */  }
/* 135:    */  
/* 136:    */  public static boolean composeImages(String qrcode, String logo) {
/* 137:    */    try {
/* 138:138 */      Image p1 = ImageIO.read(new File(qrcode));
/* 139:139 */      Image p2 = ImageIO.read(new File(logo));
/* 140:140 */      int width1 = p1.getWidth(null);
/* 141:141 */      int height1 = p1.getHeight(null);
/* 142:142 */      int width2 = p2.getWidth(null);
/* 143:143 */      int height2 = p2.getHeight(null);
/* 144:    */      
/* 145:145 */      BufferedImage bi = new BufferedImage(width1, height1, 1);
/* 146:146 */      if ((width2 > 120) || (height2 > 120)) {
/* 147:147 */        p2 = p2.getScaledInstance(120, 120, bi.getType());
/* 148:148 */        width2 = p2.getWidth(null);
/* 149:149 */        height2 = p2.getHeight(null);
/* 150:    */      }
/* 151:151 */      int x = (width1 - width2) / 2;
/* 152:152 */      int y = (height1 - height2) / 2;
/* 153:    */      
/* 154:154 */      Graphics2D gd = bi.createGraphics();
/* 155:155 */      gd.setBackground(Color.WHITE);
/* 156:156 */      gd.clearRect(0, 0, width1, height1);
/* 157:157 */      gd.drawImage(p1, 0, 0, width1, height1, null);
/* 158:    */      
/* 159:159 */      gd.clearRect(x, y, width2, height2);
/* 160:160 */      gd.drawImage(p2, x, y, width2, height2, null);
/* 161:    */      
/* 162:162 */      FileOutputStream outFile = new FileOutputStream(qrcode);
/* 163:163 */      JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(outFile);
/* 164:164 */      JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bi);
/* 165:165 */      param.setQuality(1.0F, false);
/* 166:166 */      encoder.setJPEGEncodeParam(param);
/* 167:167 */      encoder.encode(bi);
/* 168:168 */      outFile.close();
/* 169:169 */      return true;
/* 170:    */    } catch (Exception ex) {
/* 171:171 */      ex.printStackTrace();
/* 172:    */    }
/* 173:173 */    return false;
/* 174:    */  }
/* 175:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.wechat.WeChatTools
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */