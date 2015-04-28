/*   1:    */package com.soofound.protocol.alipay.httpClient;
/*   2:    */
/*   3:    */import org.apache.commons.httpclient.NameValuePair;
/*   4:    */
/*  21:    */public class HttpRequest
/*  22:    */{
/*  23:    */  public static final String METHOD_GET = "GET";
/*  24:    */  public static final String METHOD_POST = "POST";
/*  25: 25 */  private String url = null;
/*  26:    */  
/*  30: 30 */  private String method = "POST";
/*  31:    */  
/*  32: 32 */  private int timeout = 0;
/*  33:    */  
/*  34: 34 */  private int connectionTimeout = 0;
/*  35:    */  
/*  39: 39 */  private NameValuePair[] parameters = null;
/*  40:    */  
/*  44: 44 */  private String queryString = null;
/*  45:    */  
/*  49: 49 */  private String charset = "GBK";
/*  50:    */  
/*  54:    */  private String clientIp;
/*  55:    */  
/*  59: 59 */  private HttpResultType resultType = HttpResultType.BYTES;
/*  60:    */  
/*  61:    */  public HttpRequest(HttpResultType resultType)
/*  62:    */  {
/*  63: 63 */    this.resultType = resultType;
/*  64:    */  }
/*  65:    */  
/*  66:    */  public String getClientIp() {
/*  67: 67 */    return this.clientIp;
/*  68:    */  }
/*  69:    */  
/*  70:    */  public void setClientIp(String clientIp) {
/*  71: 71 */    this.clientIp = clientIp;
/*  72:    */  }
/*  73:    */  
/*  74:    */  public NameValuePair[] getParameters() {
/*  75: 75 */    return this.parameters;
/*  76:    */  }
/*  77:    */  
/*  78:    */  public void setParameters(NameValuePair[] parameters) {
/*  79: 79 */    this.parameters = parameters;
/*  80:    */  }
/*  81:    */  
/*  82:    */  public String getQueryString() {
/*  83: 83 */    return this.queryString;
/*  84:    */  }
/*  85:    */  
/*  86:    */  public void setQueryString(String queryString) {
/*  87: 87 */    this.queryString = queryString;
/*  88:    */  }
/*  89:    */  
/*  90:    */  public String getUrl() {
/*  91: 91 */    return this.url;
/*  92:    */  }
/*  93:    */  
/*  94:    */  public void setUrl(String url) {
/*  95: 95 */    this.url = url;
/*  96:    */  }
/*  97:    */  
/*  98:    */  public String getMethod() {
/*  99: 99 */    return this.method;
/* 100:    */  }
/* 101:    */  
/* 102:    */  public void setMethod(String method) {
/* 103:103 */    this.method = method;
/* 104:    */  }
/* 105:    */  
/* 106:    */  public int getConnectionTimeout() {
/* 107:107 */    return this.connectionTimeout;
/* 108:    */  }
/* 109:    */  
/* 110:    */  public void setConnectionTimeout(int connectionTimeout) {
/* 111:111 */    this.connectionTimeout = connectionTimeout;
/* 112:    */  }
/* 113:    */  
/* 114:    */  public int getTimeout() {
/* 115:115 */    return this.timeout;
/* 116:    */  }
/* 117:    */  
/* 118:    */  public void setTimeout(int timeout) {
/* 119:119 */    this.timeout = timeout;
/* 120:    */  }
/* 121:    */  
/* 122:    */  public String getCharset() {
/* 123:123 */    return this.charset;
/* 124:    */  }
/* 125:    */  
/* 126:    */  public void setCharset(String charset) {
/* 127:127 */    this.charset = charset;
/* 128:    */  }
/* 129:    */  
/* 130:    */  public HttpResultType getResultType() {
/* 131:131 */    return this.resultType;
/* 132:    */  }
/* 133:    */  
/* 134:    */  public void setResultType(HttpResultType resultType) {
/* 135:135 */    this.resultType = resultType;
/* 136:    */  }
/* 137:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.alipay.httpClient.HttpRequest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */