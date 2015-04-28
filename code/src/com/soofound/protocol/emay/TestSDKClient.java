/*   1:    */package com.soofound.protocol.emay;
/*   2:    */
/*   3:    */import java.io.InputStream;
/*   4:    */import java.rmi.RemoteException;
/*   5:    */import java.util.Iterator;
/*   6:    */import java.util.List;
/*   7:    */
/*   8:    */public class TestSDKClient
/*   9:    */{
/*  10: 10 */  public static String password = "592188";
/*  11:    */  
/*  16:    */  public static void main(String[] args)
/*  17:    */  {
/*  18:    */    
/*  19:    */    
/*  23:    */    try
/*  24:    */    {
/*  25: 25 */      StartMenu();
/*  26:    */      for (;;) {
/*  27: 27 */        System.out.println("请输入序号进行操作");
/*  28: 28 */        byte[] command = new byte[4];
/*  29: 29 */        System.in.read(command);
/*  30: 30 */        int operate = 0;
/*  31:    */        try {
/*  32: 32 */          String commandString = new String(command);
/*  33: 33 */          commandString = commandString.replaceAll("\r\n", "").trim();
/*  34: 34 */          operate = Integer.parseInt(commandString);
/*  35:    */        } catch (Exception e) {
/*  36: 36 */          System.out.println("命令输入错误！！！");
/*  37:    */        }
/*  38: 38 */        switch (operate) {
/*  39:    */        case 1: 
/*  40: 40 */          testRegistEx();
/*  41: 41 */          break;
/*  42:    */        case 2: 
/*  43: 43 */          testRegistDetailInfo();
/*  44: 44 */          break;
/*  45:    */        case 3: 
/*  46: 46 */          testGetBalance();
/*  47: 47 */          break;
/*  48:    */        case 4: 
/*  49: 49 */          testChargeUp();
/*  50: 50 */          break;
/*  51:    */        case 5: 
/*  52: 52 */          testSerialPwdUpd();
/*  53: 53 */          break;
/*  54:    */        case 6: 
/*  55: 55 */          testSendSMS();
/*  56: 56 */          break;
/*  57:    */        case 7: 
/*  58: 58 */          testsSendScheduledSMS();
/*  59: 59 */          break;
/*  60:    */        case 8: 
/*  61: 61 */          testsSendSMSAddMessageId();
/*  62: 62 */          break;
/*  63:    */        case 9: 
/*  64: 64 */          testGetMO();
/*  65: 65 */          break;
/*  66:    */        case 10: 
/*  67: 67 */          testgetReport();
/*  68: 68 */          break;
/*  69:    */        case 11: 
/*  70: 70 */          testLogout();
/*  71: 71 */          break;
/*  72:    */        case 12: 
/*  73: 73 */          System.exit(0);
/*  74:    */        default: 
/*  75: 75 */          System.out.println("没有该命令 " + operate);
/*  76:    */        }
/*  77:    */      }
/*  78:    */    }
/*  79:    */    catch (Exception e) {
/*  80: 80 */      e.printStackTrace();
/*  81:    */    }
/*  82:    */  }
/*  83:    */  
/*  84:    */  public static void StartMenu() {
/*  85: 85 */    int i = 1;
/*  86: 86 */    System.out.println(i + "、激活序列号,初次使用、已注销后再次使用时调用该方法.");
/*  87: 87 */    i++;
/*  88: 88 */    System.out.println(i + "、企业信息注册,目地在于短信发送异常时Emay可以连系到企业.");
/*  89: 89 */    i++;
/*  90: 90 */    System.out.println(i + "、余额查询");
/*  91: 91 */    i++;
/*  92: 92 */    System.out.println(i + "、充值");
/*  93: 93 */    i++;
/*  94: 94 */    System.out.println(i + "、密码修改");
/*  95: 95 */    i++;
/*  96: 96 */    System.out.println(i + "、发送即时短信");
/*  97: 97 */    i++;
/*  98: 98 */    System.out.println(i + "、发送定时短信");
/*  99: 99 */    i++;
/* 100:100 */    System.out.println(i + "、发送带有信息ID的短信,可供查询状态报告");
/* 101:101 */    i++;
/* 102:102 */    System.out.println(i + "、获取上行短信");
/* 103:103 */    i++;
/* 104:104 */    System.out.println(i + "、获得下行短信状态报告");
/* 105:105 */    i++;
/* 106:106 */    System.out.println(i + "、软件注销");
/* 107:107 */    i++;
/* 108:108 */    System.out.println(i + "、关闭程序");
/* 109:    */  }
/* 110:    */  
/* 112:    */  public static void testLogout()
/* 113:    */  {
/* 114:    */    try
/* 115:    */    {
/* 116:116 */      int a = SingletonClient.getClient().logout();
/* 117:117 */      System.out.println("testLogout:" + a);
/* 118:    */    } catch (Exception e) {
/* 119:119 */      e.printStackTrace();
/* 120:    */    }
/* 121:    */  }
/* 122:    */  
/* 125:    */  public static void testRegistEx()
/* 126:    */  {
/* 127:    */    try
/* 128:    */    {
/* 129:129 */      int i = SingletonClient.getClient().registEx(password);
/* 130:130 */      System.out.println("testTegistEx:" + i);
/* 131:    */    } catch (RemoteException e) {
/* 132:132 */      e.printStackTrace();
/* 133:    */    }
/* 134:    */  }
/* 135:    */  
/* 142:    */  public static void testSendSMS()
/* 143:    */  {
/* 144:    */    try
/* 145:    */    {
/* 146:146 */      int i = SingletonClient.getClient().sendSMS(new String[] { "13826129825" }, "【WiFiBeijing】短信发送请参考使用手册", "", 5);
/* 147:147 */      System.out.println("testSendSMS=====" + i);
/* 148:    */    } catch (Exception e) {
/* 149:149 */      e.printStackTrace();
/* 150:    */    }
/* 151:    */  }
/* 152:    */  
/* 160:    */  public static void testsSendScheduledSMS()
/* 161:    */  {
/* 162:    */    try
/* 163:    */    {
/* 164:164 */      int i = SingletonClient.getClient().sendScheduledSMSEx(new String[] { "15000000000" }, 
/* 165:165 */        "异步内容", "20110430174830", "GBK");
/* 166:166 */      System.out.println("testsSendScheduledSMS=====" + i);
/* 167:    */    } catch (Exception e) {
/* 168:168 */      e.printStackTrace();
/* 169:    */    }
/* 170:    */  }
/* 171:    */  
/* 179:    */  public static void testsSendSMSAddMessageId()
/* 180:    */  {
/* 181:    */    try
/* 182:    */    {
/* 183:183 */      int i = SingletonClient.getClient().sendSMSEx(new String[] { "15000000000" }, "带有信息ID的短信", "", "GBK", 5, 123456789L);
/* 184:184 */      System.out.println("testsSendScheduledSMS=====" + i);
/* 185:    */    } catch (Exception e) {
/* 186:186 */      e.printStackTrace();
/* 187:    */    }
/* 188:    */  }
/* 189:    */  
/* 192:    */  public static void testChargeUp()
/* 193:    */  {
/* 194:    */    try
/* 195:    */    {
/* 196:196 */      int a = SingletonClient.getClient().chargeUp("充值卡卡号", "密码");
/* 197:197 */      System.out.println("testChargeUp:" + a);
/* 198:    */    } catch (Exception e) {
/* 199:199 */      e.printStackTrace();
/* 200:    */    }
/* 201:    */  }
/* 202:    */  
/* 210:    */  public static void testRegistDetailInfo()
/* 211:    */  {
/* 212:    */    try
/* 213:    */    {
/* 214:214 */      int a = SingletonClient.getClient().registDetailInfo("企业名称", "联系人", "01058750425", 
/* 215:215 */        "13000000000", "sjfkls@yahoo.cn", "01058750500", "企业地址", "056900");
/* 216:216 */      System.out.println("testRegistDetailInfo:" + a);
/* 217:    */    } catch (Exception e) {
/* 218:218 */      e.printStackTrace();
/* 219:    */    }
/* 220:    */  }
/* 221:    */  
/* 224:    */  public static void testSerialPwdUpd()
/* 225:    */  {
/* 226:    */    try
/* 227:    */    {
/* 228:228 */      int a = SingletonClient.getClient().serialPwdUpd("123456", password);
/* 229:229 */      System.out.println("testSerialPwdUpd:" + a);
/* 230:    */    } catch (Exception e) {
/* 231:231 */      e.printStackTrace();
/* 232:    */    }
/* 233:    */  }
/* 234:    */  
/* 235:    */  public static void testGetBalance()
/* 236:    */  {
/* 237:    */    try {
/* 238:238 */      double a = SingletonClient.getClient().getBalance();
/* 239:239 */      System.out.println("testGetBalance:" + a);
/* 240:    */    } catch (Exception e) {
/* 241:241 */      e.printStackTrace();
/* 242:    */    }
/* 243:    */  }
/* 244:    */  
/* 245:    */  public static void testGetEachFee()
/* 246:    */  {
/* 247:    */    try {
/* 248:248 */      double a = SingletonClient.getClient().getEachFee();
/* 249:249 */      System.out.println("testGetEachFee:" + a);
/* 250:    */    } catch (Exception e) {
/* 251:251 */      e.printStackTrace();
/* 252:    */    }
/* 253:    */  }
/* 254:    */  
/* 256:    */  public static void testGetMO()
/* 257:    */  {
/* 258:    */    try
/* 259:    */    {
/* 260:260 */      List<Mo> a = SingletonClient.getClient().getMO();
/* 261:    */      
/* 262:262 */      if (a != null) {
/* 263:263 */        System.out.println("testGetMO1size:" + a.size());
/* 264:264 */        Iterator<Mo> it = a.iterator();
/* 265:    */        
/* 266:266 */        while (it.hasNext()) {
/* 267:267 */          Mo m = (Mo)it.next();
/* 268:    */          
/* 269:269 */          System.out.println("短信内容:" + m.getSmsContent());
/* 270:270 */          System.out.println("通道号:" + m.getChannelnumber());
/* 271:271 */          System.out.println("手机号:" + m.getMobileNumber());
/* 272:272 */          System.out.println("附加码:" + m.getAddSerialRev());
/* 273:273 */          System.out.println("附加码:" + m.getAddSerial());
/* 274:    */        }
/* 275:    */      }
/* 276:    */      else {
/* 277:277 */        System.out.println("NO HAVE MO");
/* 278:    */      }
/* 279:    */    } catch (Exception e) {
/* 280:280 */      e.printStackTrace();
/* 281:    */    }
/* 282:    */  }
/* 283:    */  
/* 284:    */  public static void testgetReport()
/* 285:    */  {
/* 286:    */    try {
/* 287:287 */      List<StatusReport> list = SingletonClient.getClient().getReport();
/* 288:288 */      if (list != null) {
/* 289:289 */        Iterator it = list.iterator();
/* 290:290 */        while (it.hasNext()) {
/* 291:291 */          StatusReport report = (StatusReport)it.next();
/* 292:292 */          System.out.println("手机号码:" + report.getMobile() + "\t状态:" + 
/* 293:293 */            report.getReportStatus() + "\t信息ID：" + report.getSeqID());
/* 294:    */        }
/* 295:    */      } else {
/* 296:296 */        System.out.println("no have data");
/* 297:    */      }
/* 298:    */    }
/* 299:    */    catch (Exception localException) {}
/* 300:    */  }
/* 301:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.emay.TestSDKClient
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */