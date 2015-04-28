/*  1:   */package com.soofound.project.wifibeijing;
/*  2:   */
/*  3:   */import com.soofound.portal.custom.SmsSender;
/*  4:   */
/*  5:   */public class WifiBeijingSmsSender extends SmsSender
/*  6:   */{
/*  7:   */  private com.soofound.protocol.emay.Client client;
/*  8:   */  
/*  9:   */  public WifiBeijingSmsSender() {
/* 10:10 */    this.client = new com.soofound.protocol.emay.Client("0SDK-EBB-0130-JISML", "592188");
/* 11:   */  }
/* 12:   */  
/* 13:   */  public boolean sendSMS(String branchId, String mobile, String content)
/* 14:   */  {
/* 15:15 */    int result = 0;
/* 16:   */    try {
/* 17:17 */      result = this.client.sendSMS(mobile.split(","), "【WiFiBeijing】" + content, "", 5);
/* 18:18 */      System.out.println("WifiBeijingSmsSend=" + result);
/* 19:   */    } catch (Exception e) {
/* 20:20 */      e.printStackTrace();
/* 21:   */    }
/* 22:22 */    return result == 0;
/* 23:   */  }
/* 24:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.project.wifibeijing.WifiBeijingSmsSender
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */