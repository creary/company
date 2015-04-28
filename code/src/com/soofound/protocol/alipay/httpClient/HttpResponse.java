/*  1:   */package com.soofound.protocol.alipay.httpClient;
/*  2:   */
/*  3:   */import com.soofound.protocol.alipay.AlipayConfig;
/*  4:   */import java.io.UnsupportedEncodingException;
/*  5:   */import org.apache.commons.httpclient.Header;
/*  6:   */
/* 28:   */public class HttpResponse
/* 29:   */{
/* 30:   */  private Header[] responseHeaders;
/* 31:   */  private String stringResult;
/* 32:   */  private byte[] byteResult;
/* 33:   */  
/* 34:   */  public Header[] getResponseHeaders()
/* 35:   */  {
/* 36:36 */    return this.responseHeaders;
/* 37:   */  }
/* 38:   */  
/* 39:   */  public void setResponseHeaders(Header[] responseHeaders) {
/* 40:40 */    this.responseHeaders = responseHeaders;
/* 41:   */  }
/* 42:   */  
/* 43:   */  public byte[] getByteResult() {
/* 44:44 */    if (this.byteResult != null) {
/* 45:45 */      return this.byteResult;
/* 46:   */    }
/* 47:47 */    if (this.stringResult != null) {
/* 48:48 */      return this.stringResult.getBytes();
/* 49:   */    }
/* 50:50 */    return null;
/* 51:   */  }
/* 52:   */  
/* 53:   */  public void setByteResult(byte[] byteResult) {
/* 54:54 */    this.byteResult = byteResult;
/* 55:   */  }
/* 56:   */  
/* 57:   */  public String getStringResult() throws UnsupportedEncodingException {
/* 58:58 */    if (this.stringResult != null) {
/* 59:59 */      return this.stringResult;
/* 60:   */    }
/* 61:61 */    if (this.byteResult != null) {
/* 62:62 */      return new String(this.byteResult, AlipayConfig.input_charset);
/* 63:   */    }
/* 64:64 */    return null;
/* 65:   */  }
/* 66:   */  
/* 67:   */  public void setStringResult(String stringResult) {
/* 68:68 */    this.stringResult = stringResult;
/* 69:   */  }
/* 70:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.alipay.httpClient.HttpResponse
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */