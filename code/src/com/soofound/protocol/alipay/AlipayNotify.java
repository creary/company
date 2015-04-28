/*   1:    */package com.soofound.protocol.alipay;
/*   2:    */
/*   3:    */import java.io.BufferedReader;
/*   4:    */import java.io.InputStreamReader;
/*   5:    */import java.net.HttpURLConnection;
/*   6:    */import java.net.URL;
/*   7:    */import java.util.Map;
/*   8:    */
/*  21:    */public class AlipayNotify
/*  22:    */{
/*  23: 23 */  private static AlipayConfig cfg = new AlipayConfig();
/*  24:    */  
/*  29:    */  private static final String HTTPS_VERIFY_URL = "https://mapi.alipay.com/gateway.do?service=notify_verify&";
/*  30:    */  
/*  36:    */  public static boolean verify(Map<String, String> params)
/*  37:    */  {
/*  38: 38 */    String responseTxt = "true";
/*  39: 39 */    if (params.get("notify_id") != null) {
/*  40: 40 */      String notify_id = (String)params.get("notify_id");
/*  41: 41 */      responseTxt = verifyResponse(notify_id);
/*  42:    */    }
/*  43: 43 */    String sign = "";
/*  44: 44 */    if (params.get("sign") != null) sign = (String)params.get("sign");
/*  45: 45 */    boolean isSign = getSignVeryfy(params, sign);
/*  46:    */    
/*  50: 50 */    if ((isSign) && (responseTxt.equals("true"))) {
/*  51: 51 */      return true;
/*  52:    */    }
/*  53: 53 */    return false;
/*  54:    */  }
/*  55:    */  
/*  63:    */  private static boolean getSignVeryfy(Map<String, String> Params, String sign)
/*  64:    */  {
/*  65: 65 */    Map<String, String> sParaNew = AlipayCore.paraFilter(Params);
/*  66:    */    
/*  67: 67 */    String preSignStr = AlipayCore.createLinkString(sParaNew);
/*  68:    */    
/*  69: 69 */    boolean isSign = false;
/*  70: 70 */    if (AlipayConfig.sign_type.equals("MD5")) {
/*  71: 71 */      isSign = MD5.verify(preSignStr, sign, cfg.getKey(), AlipayConfig.input_charset);
/*  72:    */    }
/*  73: 73 */    return isSign;
/*  74:    */  }
/*  75:    */  
/*  85:    */  private static String verifyResponse(String notify_id)
/*  86:    */  {
/*  87: 87 */    String veryfy_url = "https://mapi.alipay.com/gateway.do?service=notify_verify&partner=" + cfg.getPartner() + "&notify_id=" + notify_id;
/*  88: 88 */    return checkUrl(veryfy_url);
/*  89:    */  }
/*  90:    */  
/*  99:    */  private static String checkUrl(String urlvalue)
/* 100:    */  {
/* 101:101 */    String inputLine = "";
/* 102:    */    try {
/* 103:103 */      URL url = new URL(urlvalue);
/* 104:104 */      HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
/* 105:105 */      BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection
/* 106:106 */        .getInputStream()));
/* 107:107 */      inputLine = in.readLine().toString();
/* 108:    */    } catch (Exception e) {
/* 109:109 */      e.printStackTrace();
/* 110:110 */      inputLine = "";
/* 111:    */    }
/* 112:112 */    return inputLine;
/* 113:    */  }
/* 114:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.alipay.AlipayNotify
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */