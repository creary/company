/*   1:    */package com.soofound.protocol.wechat;
/*   2:    */
/*   3:    */import java.security.cert.CertificateException;
/*   4:    */import java.security.cert.X509Certificate;
/*   5:    */import javax.net.ssl.X509TrustManager;
/*   6:    */
/* 299:    */class MyX509TrustManager
/* 300:    */  implements X509TrustManager
/* 301:    */{
/* 302:    */  public X509Certificate[] getAcceptedIssuers()
/* 303:    */  {
/* 304:304 */    return null;
/* 305:    */  }
/* 306:    */  
/* 307:    */  public void checkClientTrusted(X509Certificate[] chain, String authType)
/* 308:    */    throws CertificateException
/* 309:    */  {}
/* 310:    */  
/* 311:    */  public void checkServerTrusted(X509Certificate[] chain, String authType)
/* 312:    */    throws CertificateException
/* 313:    */  {}
/* 314:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.wechat.MyX509TrustManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */