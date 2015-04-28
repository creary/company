/*   1:    */package com.soofound.protocol.wechat;
/*   2:    */
/*   3:    */import com.soofound.protocol.wechat.message.Attachment;
/*   4:    */import java.io.BufferedInputStream;
/*   5:    */import java.io.BufferedReader;
/*   6:    */import java.io.DataInputStream;
/*   7:    */import java.io.DataOutputStream;
/*   8:    */import java.io.File;
/*   9:    */import java.io.FileInputStream;
/*  10:    */import java.io.IOException;
/*  11:    */import java.io.InputStream;
/*  12:    */import java.io.InputStreamReader;
/*  13:    */import java.io.OutputStream;
/*  14:    */import java.io.UnsupportedEncodingException;
/*  15:    */import java.net.HttpURLConnection;
/*  16:    */import java.net.URL;
/*  17:    */import java.net.URLEncoder;
/*  18:    */import java.security.KeyManagementException;
/*  19:    */import java.security.NoSuchAlgorithmException;
/*  20:    */import java.security.NoSuchProviderException;
/*  21:    */import java.security.SecureRandom;
/*  22:    */import java.util.Map;
/*  23:    */import java.util.Map.Entry;
/*  24:    */import javax.net.ssl.HttpsURLConnection;
/*  25:    */import javax.net.ssl.SSLContext;
/*  26:    */import javax.net.ssl.SSLSocketFactory;
/*  27:    */import javax.net.ssl.TrustManager;
/*  28:    */import org.apache.commons.lang.StringUtils;
/*  29:    */import org.apache.log4j.Logger;
/*  30:    */
/*  38:    */public class HttpKit
/*  39:    */{
/*  40:    */  private static final String DEFAULT_CHARSET = "UTF-8";
/*  41: 41 */  private static final Logger LOGGER = Logger.getLogger(HttpKit.class);
/*  42:    */  
/*  44:    */  public static String get(String url)
/*  45:    */    throws NoSuchAlgorithmException, NoSuchProviderException, IOException, KeyManagementException
/*  46:    */  {
/*  47: 47 */    StringBuffer bufferRes = null;
/*  48: 48 */    TrustManager[] tm = { new MyX509TrustManager() };
/*  49: 49 */    SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
/*  50: 50 */    sslContext.init(null, tm, new SecureRandom());
/*  51:    */    
/*  52: 52 */    SSLSocketFactory ssf = sslContext.getSocketFactory();
/*  53:    */    
/*  54: 54 */    URL urlGet = new URL(url);
/*  55: 55 */    HttpsURLConnection http = (HttpsURLConnection)urlGet.openConnection();
/*  56:    */    
/*  57: 57 */    http.setConnectTimeout(25000);
/*  58:    */    
/*  59: 59 */    http.setReadTimeout(25000);
/*  60: 60 */    http.setRequestMethod("GET");
/*  61: 61 */    http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
/*  62: 62 */    http.setSSLSocketFactory(ssf);
/*  63: 63 */    http.setDoOutput(true);
/*  64: 64 */    http.setDoInput(true);
/*  65: 65 */    http.connect();
/*  66:    */    
/*  67: 67 */    InputStream in = http.getInputStream();
/*  68: 68 */    BufferedReader read = new BufferedReader(new InputStreamReader(in, "UTF-8"));
/*  69: 69 */    String valueString = null;
/*  70: 70 */    bufferRes = new StringBuffer();
/*  71: 71 */    while ((valueString = read.readLine()) != null) {
/*  72: 72 */      bufferRes.append(valueString);
/*  73:    */    }
/*  74: 74 */    in.close();
/*  75: 75 */    if (http != null)
/*  76:    */    {
/*  77: 77 */      http.disconnect();
/*  78:    */    }
/*  79: 79 */    return bufferRes.toString();
/*  80:    */  }
/*  81:    */  
/*  83:    */  public static String get(String url, Boolean https)
/*  84:    */    throws NoSuchAlgorithmException, NoSuchProviderException, IOException, KeyManagementException
/*  85:    */  {
/*  86: 86 */    if ((https != null) && (https.booleanValue())) {
/*  87: 87 */      return get(url);
/*  88:    */    }
/*  89: 89 */    StringBuffer bufferRes = null;
/*  90: 90 */    URL urlGet = new URL(url);
/*  91: 91 */    HttpURLConnection http = (HttpURLConnection)urlGet.openConnection();
/*  92:    */    
/*  93: 93 */    http.setConnectTimeout(25000);
/*  94:    */    
/*  95: 95 */    http.setReadTimeout(25000);
/*  96: 96 */    http.setRequestMethod("GET");
/*  97: 97 */    http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
/*  98: 98 */    http.setDoOutput(true);
/*  99: 99 */    http.setDoInput(true);
/* 100:100 */    http.connect();
/* 101:    */    
/* 102:102 */    InputStream in = http.getInputStream();
/* 103:103 */    BufferedReader read = new BufferedReader(new InputStreamReader(in, "UTF-8"));
/* 104:104 */    String valueString = null;
/* 105:105 */    bufferRes = new StringBuffer();
/* 106:106 */    while ((valueString = read.readLine()) != null) {
/* 107:107 */      bufferRes.append(valueString);
/* 108:    */    }
/* 109:109 */    in.close();
/* 110:110 */    if (http != null)
/* 111:    */    {
/* 112:112 */      http.disconnect();
/* 113:    */    }
/* 114:114 */    return bufferRes.toString();
/* 115:    */  }
/* 116:    */  
/* 119:    */  public static String get(String url, Map<String, String> params)
/* 120:    */    throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, IOException
/* 121:    */  {
/* 122:122 */    return get(initParams(url, params));
/* 123:    */  }
/* 124:    */  
/* 126:    */  public static String post(String url, String params)
/* 127:    */    throws IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException
/* 128:    */  {
/* 129:129 */    StringBuffer bufferRes = null;
/* 130:130 */    TrustManager[] tm = { new MyX509TrustManager() };
/* 131:131 */    SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
/* 132:132 */    sslContext.init(null, tm, new SecureRandom());
/* 133:    */    
/* 134:134 */    SSLSocketFactory ssf = sslContext.getSocketFactory();
/* 135:    */    
/* 136:136 */    URL urlGet = new URL(url);
/* 137:137 */    HttpsURLConnection http = (HttpsURLConnection)urlGet.openConnection();
/* 138:    */    
/* 139:139 */    http.setConnectTimeout(25000);
/* 140:    */    
/* 141:141 */    http.setReadTimeout(25000);
/* 142:142 */    http.setRequestMethod("POST");
/* 143:143 */    http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
/* 144:144 */    http.setSSLSocketFactory(ssf);
/* 145:145 */    http.setDoOutput(true);
/* 146:146 */    http.setDoInput(true);
/* 147:147 */    http.connect();
/* 148:    */    
/* 149:149 */    if (params != null) {
/* 150:150 */      OutputStream out = http.getOutputStream();
/* 151:151 */      out.write(params.getBytes("UTF-8"));
/* 152:152 */      out.flush();
/* 153:153 */      out.close();
/* 154:    */    }
/* 155:155 */    InputStream in = http.getInputStream();
/* 156:156 */    BufferedReader read = new BufferedReader(new InputStreamReader(in, "UTF-8"));
/* 157:157 */    String valueString = null;
/* 158:158 */    bufferRes = new StringBuffer();
/* 159:159 */    while ((valueString = read.readLine()) != null) {
/* 160:160 */      bufferRes.append(valueString);
/* 161:    */    }
/* 162:162 */    in.close();
/* 163:163 */    if (http != null)
/* 164:    */    {
/* 165:165 */      http.disconnect();
/* 166:    */    }
/* 167:167 */    return bufferRes.toString();
/* 168:    */  }
/* 169:    */  
/* 171:    */  public static String upload(String url, File file)
/* 172:    */    throws IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException
/* 173:    */  {
/* 174:174 */    String BOUNDARY = "----WebKitFormBoundaryiDGnV9zdZA1eM1yL";
/* 175:175 */    StringBuffer bufferRes = null;
/* 176:176 */    URL urlGet = new URL(url);
/* 177:177 */    HttpURLConnection conn = (HttpURLConnection)urlGet.openConnection();
/* 178:178 */    conn.setDoOutput(true);
/* 179:179 */    conn.setDoInput(true);
/* 180:180 */    conn.setUseCaches(false);
/* 181:181 */    conn.setRequestMethod("POST");
/* 182:182 */    conn.setRequestProperty("connection", "Keep-Alive");
/* 183:183 */    conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1700.107 Safari/537.36");
/* 184:184 */    conn.setRequestProperty("Charsert", "UTF-8");
/* 185:185 */    conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
/* 186:    */    
/* 187:187 */    OutputStream out = new DataOutputStream(conn.getOutputStream());
/* 188:188 */    byte[] end_data = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
/* 189:189 */    StringBuilder sb = new StringBuilder();
/* 190:190 */    sb.append("--");
/* 191:191 */    sb.append(BOUNDARY);
/* 192:192 */    sb.append("\r\n");
/* 193:193 */    sb.append("Content-Disposition: form-data;name=\"media\";filename=\"" + file.getName() + "\"\r\n");
/* 194:194 */    sb.append("Content-Type:application/octet-stream\r\n\r\n");
/* 195:195 */    byte[] data = sb.toString().getBytes();
/* 196:196 */    out.write(data);
/* 197:197 */    DataInputStream fs = new DataInputStream(new FileInputStream(file));
/* 198:198 */    int bytes = 0;
/* 199:199 */    byte[] bufferOut = new byte[1024];
/* 200:200 */    while ((bytes = fs.read(bufferOut)) != -1) {
/* 201:201 */      out.write(bufferOut, 0, bytes);
/* 202:    */    }
/* 203:203 */    out.write("\r\n".getBytes());
/* 204:204 */    fs.close();
/* 205:205 */    out.write(end_data);
/* 206:206 */    out.flush();
/* 207:207 */    out.close();
/* 208:    */    
/* 210:210 */    InputStream in = conn.getInputStream();
/* 211:211 */    BufferedReader read = new BufferedReader(new InputStreamReader(in, "UTF-8"));
/* 212:212 */    String valueString = null;
/* 213:213 */    bufferRes = new StringBuffer();
/* 214:214 */    while ((valueString = read.readLine()) != null) {
/* 215:215 */      bufferRes.append(valueString);
/* 216:    */    }
/* 217:217 */    in.close();
/* 218:218 */    if (conn != null)
/* 219:    */    {
/* 220:220 */      conn.disconnect();
/* 221:    */    }
/* 222:222 */    return bufferRes.toString();
/* 223:    */  }
/* 224:    */  
/* 226:    */  public static Attachment download(String url)
/* 227:    */    throws IOException
/* 228:    */  {
/* 229:229 */    Attachment att = new Attachment();
/* 230:230 */    URL urlGet = new URL(url);
/* 231:231 */    HttpURLConnection conn = (HttpURLConnection)urlGet.openConnection();
/* 232:232 */    conn.setDoOutput(true);
/* 233:233 */    conn.setDoInput(true);
/* 234:234 */    conn.setUseCaches(false);
/* 235:235 */    conn.setRequestMethod("GET");
/* 236:236 */    conn.connect();
/* 237:237 */    if (conn.getContentType().equalsIgnoreCase("text/plain"))
/* 238:    */    {
/* 239:239 */      InputStream in = conn.getInputStream();
/* 240:240 */      BufferedReader read = new BufferedReader(new InputStreamReader(in, "UTF-8"));
/* 241:241 */      String valueString = null;
/* 242:242 */      StringBuffer bufferRes = new StringBuffer();
/* 243:243 */      while ((valueString = read.readLine()) != null) {
/* 244:244 */        bufferRes.append(valueString);
/* 245:    */      }
/* 246:246 */      in.close();
/* 247:247 */      att.setError(bufferRes.toString());
/* 248:    */    } else {
/* 249:249 */      BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
/* 250:250 */      String ds = conn.getHeaderField("Content-disposition");
/* 251:251 */      String fullName = ds.substring(ds.indexOf("filename=\"") + 10, ds.length() - 1);
/* 252:252 */      String relName = fullName.substring(0, fullName.lastIndexOf("."));
/* 253:253 */      String suffix = fullName.substring(relName.length() + 1);
/* 254:    */      
/* 255:255 */      att.setFullName(fullName);
/* 256:256 */      att.setFileName(relName);
/* 257:257 */      att.setSuffix(suffix);
/* 258:258 */      att.setContentLength(conn.getHeaderField("Content-Length"));
/* 259:259 */      att.setContentType(conn.getHeaderField("Content-Type"));
/* 260:    */      
/* 261:261 */      att.setFileStream(bis);
/* 262:    */    }
/* 263:263 */    return att;
/* 264:    */  }
/* 265:    */  
/* 268:    */  private static String initParams(String url, Map<String, String> params)
/* 269:    */  {
/* 270:270 */    if ((params == null) || (params.isEmpty())) {
/* 271:271 */      return url;
/* 272:    */    }
/* 273:273 */    StringBuilder sb = new StringBuilder(url);
/* 274:274 */    if (url.indexOf("?") == -1) {
/* 275:275 */      sb.append("?");
/* 276:    */    } else {
/* 277:277 */      sb.append("&");
/* 278:    */    }
/* 279:279 */    boolean first = true;
/* 280:280 */    for (Map.Entry<String, String> entry : params.entrySet()) {
/* 281:281 */      if (first) {
/* 282:282 */        first = false;
/* 283:    */      } else {
/* 284:284 */        sb.append("&");
/* 285:    */      }
/* 286:286 */      String key = (String)entry.getKey();
/* 287:287 */      String value = (String)entry.getValue();
/* 288:288 */      sb.append(key).append("=");
/* 289:289 */      if (StringUtils.isNotEmpty(value)) {
/* 290:    */        try {
/* 291:291 */          sb.append(URLEncoder.encode(value, "UTF-8"));
/* 292:    */        } catch (UnsupportedEncodingException e) {
/* 293:293 */          e.printStackTrace();
/* 294:294 */          LOGGER.error(url, e);
/* 295:    */        }
/* 296:    */      }
/* 297:    */    }
/* 298:298 */    return sb.toString();
/* 299:    */  }
/* 300:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.wechat.HttpKit
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */