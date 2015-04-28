/*  1:   */package com.soofound.protocol.emay;
/*  2:   */
/*  3:   */public class SingletonClient {
/*  4: 4 */  private static Client client = null;
/*  5:   */  
/*  7:   */  public static synchronized Client getClient(String softwareSerialNo, String key)
/*  8:   */  {
/*  9: 9 */    if (client == null) {
/* 10:   */      try {
/* 11:11 */        client = new Client(softwareSerialNo, key);
/* 12:   */      } catch (Exception e) {
/* 13:13 */        e.printStackTrace();
/* 14:   */      }
/* 15:   */    }
/* 16:16 */    return client;
/* 17:   */  }
/* 18:   */  
/* 19:   */  public static synchronized Client getClient()
/* 20:   */  {
/* 21:21 */    if (client == null) {
/* 22:   */      try
/* 23:   */      {
/* 24:24 */        client = new Client("0SDK-EBB-0130-JISML", "592188");
/* 25:   */      } catch (Exception e) {
/* 26:26 */        e.printStackTrace();
/* 27:   */      }
/* 28:   */    }
/* 29:29 */    return client;
/* 30:   */  }
/* 31:   */  
/* 32:   */  public static void main(String[] str) {
/* 33:33 */    getClient();
/* 34:   */  }
/* 35:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.emay.SingletonClient
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */