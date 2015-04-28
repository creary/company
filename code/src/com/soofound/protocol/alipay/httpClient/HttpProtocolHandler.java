/*   1:    */package com.soofound.protocol.alipay.httpClient;
/*   2:    */
/*   3:    */import java.io.File;
/*   4:    */import java.io.IOException;
/*   5:    */import java.net.UnknownHostException;
/*   6:    */import java.util.ArrayList;
/*   7:    */import java.util.List;
/*   8:    */import org.apache.commons.httpclient.HttpClient;
/*   9:    */import org.apache.commons.httpclient.HttpConnectionManager;
/*  10:    */import org.apache.commons.httpclient.HttpException;
/*  11:    */import org.apache.commons.httpclient.HttpMethod;
/*  12:    */import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
/*  13:    */import org.apache.commons.httpclient.NameValuePair;
/*  14:    */import org.apache.commons.httpclient.methods.GetMethod;
/*  15:    */import org.apache.commons.httpclient.methods.PostMethod;
/*  16:    */import org.apache.commons.httpclient.methods.multipart.FilePart;
/*  17:    */import org.apache.commons.httpclient.methods.multipart.FilePartSource;
/*  18:    */import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
/*  19:    */import org.apache.commons.httpclient.methods.multipart.Part;
/*  20:    */import org.apache.commons.httpclient.methods.multipart.StringPart;
/*  21:    */import org.apache.commons.httpclient.params.HttpClientParams;
/*  22:    */import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
/*  23:    */import org.apache.commons.httpclient.params.HttpMethodParams;
/*  24:    */import org.apache.commons.httpclient.util.IdleConnectionTimeoutThread;
/*  25:    */
/*  35:    */public class HttpProtocolHandler
/*  36:    */{
/*  37: 37 */  private static String DEFAULT_CHARSET = "GBK";
/*  38:    */  
/*  40: 40 */  private int defaultConnectionTimeout = 8000;
/*  41:    */  
/*  43: 43 */  private int defaultSoTimeout = 30000;
/*  44:    */  
/*  46: 46 */  private int defaultIdleConnTimeout = 60000;
/*  47:    */  
/*  48: 48 */  private int defaultMaxConnPerHost = 30;
/*  49:    */  
/*  50: 50 */  private int defaultMaxTotalConn = 80;
/*  51:    */  
/*  54:    */  private static final long defaultHttpConnectionManagerTimeout = 3000L;
/*  55:    */  
/*  57:    */  private HttpConnectionManager connectionManager;
/*  58:    */  
/*  60: 60 */  private static HttpProtocolHandler httpProtocolHandler = new HttpProtocolHandler();
/*  61:    */  
/*  66:    */  public static HttpProtocolHandler getInstance()
/*  67:    */  {
/*  68: 68 */    return httpProtocolHandler;
/*  69:    */  }
/*  70:    */  
/*  74:    */  private HttpProtocolHandler()
/*  75:    */  {
/*  76: 76 */    this.connectionManager = new MultiThreadedHttpConnectionManager();
/*  77: 77 */    this.connectionManager.getParams().setDefaultMaxConnectionsPerHost(this.defaultMaxConnPerHost);
/*  78: 78 */    this.connectionManager.getParams().setMaxTotalConnections(this.defaultMaxTotalConn);
/*  79:    */    
/*  80: 80 */    IdleConnectionTimeoutThread ict = new IdleConnectionTimeoutThread();
/*  81: 81 */    ict.addConnectionManager(this.connectionManager);
/*  82: 82 */    ict.setConnectionTimeout(this.defaultIdleConnTimeout);
/*  83:    */    
/*  84: 84 */    ict.start();
/*  85:    */  }
/*  86:    */  
/*  94:    */  public HttpResponse execute(HttpRequest request, String strParaFileName, String strFilePath)
/*  95:    */    throws HttpException, IOException
/*  96:    */  {
/*  97: 97 */    HttpClient httpclient = new HttpClient(this.connectionManager);
/*  98:    */    
/* 100:100 */    int connectionTimeout = this.defaultConnectionTimeout;
/* 101:101 */    if (request.getConnectionTimeout() > 0) {
/* 102:102 */      connectionTimeout = request.getConnectionTimeout();
/* 103:    */    }
/* 104:104 */    httpclient.getHttpConnectionManager().getParams().setConnectionTimeout(connectionTimeout);
/* 105:    */    
/* 107:107 */    int soTimeout = this.defaultSoTimeout;
/* 108:108 */    if (request.getTimeout() > 0) {
/* 109:109 */      soTimeout = request.getTimeout();
/* 110:    */    }
/* 111:111 */    httpclient.getHttpConnectionManager().getParams().setSoTimeout(soTimeout);
/* 112:    */    
/* 114:114 */    httpclient.getParams().setConnectionManagerTimeout(3000L);
/* 115:    */    
/* 116:116 */    String charset = request.getCharset();
/* 117:117 */    charset = charset == null ? DEFAULT_CHARSET : charset;
/* 118:118 */    HttpMethod method = null;
/* 119:    */    
/* 121:121 */    if (request.getMethod().equals("GET")) {
/* 122:122 */      method = new GetMethod(request.getUrl());
/* 123:123 */      method.getParams().setCredentialCharset(charset);
/* 124:    */      
/* 126:126 */      method.setQueryString(request.getQueryString());
/* 127:127 */    } else if ((strParaFileName.equals("")) && (strFilePath.equals("")))
/* 128:    */    {
/* 129:129 */      method = new PostMethod(request.getUrl());
/* 130:130 */      ((PostMethod)method).addParameters(request.getParameters());
/* 131:131 */      method.addRequestHeader("Content-Type", "application/x-www-form-urlencoded; text/html; charset=" + charset);
/* 132:    */    }
/* 133:    */    else {
/* 134:134 */      method = new PostMethod(request.getUrl());
/* 135:135 */      List<Part> parts = new ArrayList();
/* 136:136 */      for (int i = 0; i < request.getParameters().length; i++) {
/* 137:137 */        parts.add(new StringPart(request.getParameters()[i].getName(), request.getParameters()[i].getValue(), charset));
/* 138:    */      }
/* 139:    */      
/* 140:140 */      parts.add(new FilePart(strParaFileName, new FilePartSource(new File(strFilePath))));
/* 141:    */      
/* 143:143 */      ((PostMethod)method).setRequestEntity(new MultipartRequestEntity((Part[])parts.toArray(new Part[0]), new HttpMethodParams()));
/* 144:    */    }
/* 145:    */    
/* 147:147 */    method.addRequestHeader("User-Agent", "Mozilla/4.0");
/* 148:148 */    HttpResponse response = new HttpResponse();
/* 149:    */    try {
/* 150:150 */      httpclient.executeMethod(method);
/* 151:151 */      if (request.getResultType().equals(HttpResultType.STRING)) {
/* 152:152 */        response.setStringResult(method.getResponseBodyAsString());
/* 153:153 */      } else if (request.getResultType().equals(HttpResultType.BYTES)) {
/* 154:154 */        response.setByteResult(method.getResponseBody());
/* 155:    */      }
/* 156:156 */      response.setResponseHeaders(method.getResponseHeaders());
/* 157:    */    } catch (UnknownHostException ex) {
/* 158:158 */      return null;
/* 159:    */    } catch (IOException ex) {
/* 160:160 */      return null;
/* 161:    */    } catch (Exception ex) {
/* 162:162 */      return null;
/* 163:    */    } finally {
/* 164:164 */      method.releaseConnection(); } method.releaseConnection();
/* 165:    */    
/* 166:166 */    return response;
/* 167:    */  }
/* 168:    */  
/* 174:    */  protected String toString(NameValuePair[] nameValues)
/* 175:    */  {
/* 176:176 */    if ((nameValues == null) || (nameValues.length == 0)) {
/* 177:177 */      return "null";
/* 178:    */    }
/* 179:179 */    StringBuffer buffer = new StringBuffer();
/* 180:180 */    for (int i = 0; i < nameValues.length; i++) {
/* 181:181 */      NameValuePair nameValue = nameValues[i];
/* 182:182 */      if (i == 0) {
/* 183:183 */        buffer.append(nameValue.getName() + "=" + nameValue.getValue());
/* 184:    */      } else
/* 185:185 */        buffer.append("&" + nameValue.getName() + "=" + nameValue.getValue());
/* 186:    */    }
/* 187:187 */    return buffer.toString();
/* 188:    */  }
/* 189:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.alipay.httpClient.HttpProtocolHandler
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */