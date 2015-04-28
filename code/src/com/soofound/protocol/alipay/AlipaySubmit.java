/*   1:    */package com.soofound.protocol.alipay;
/*   2:    */
/*   3:    */import com.soofound.protocol.alipay.httpClient.HttpProtocolHandler;
/*   4:    */import com.soofound.protocol.alipay.httpClient.HttpRequest;
/*   5:    */import com.soofound.protocol.alipay.httpClient.HttpResponse;
/*   6:    */import com.soofound.protocol.alipay.httpClient.HttpResultType;
/*   7:    */import java.io.IOException;
/*   8:    */import java.net.MalformedURLException;
/*   9:    */import java.net.URL;
/*  10:    */import java.util.ArrayList;
/*  11:    */import java.util.Iterator;
/*  12:    */import java.util.List;
/*  13:    */import java.util.Map;
/*  14:    */import java.util.Map.Entry;
/*  15:    */import org.apache.commons.httpclient.NameValuePair;
/*  16:    */import org.dom4j.Document;
/*  17:    */import org.dom4j.DocumentException;
/*  18:    */import org.dom4j.Node;
/*  19:    */import org.dom4j.io.SAXReader;
/*  20:    */
/*  30:    */public class AlipaySubmit
/*  31:    */{
/*  32: 32 */  private static AlipayConfig cfg = new AlipayConfig();
/*  33:    */  
/*  37:    */  private static final String ALIPAY_GATEWAY_NEW = "https://mapi.alipay.com/gateway.do?";
/*  38:    */  
/*  42:    */  public static String buildRequestMysign(Map<String, String> sPara)
/*  43:    */  {
/*  44: 44 */    String prestr = AlipayCore.createLinkString(sPara);
/*  45: 45 */    String mysign = "";
/*  46: 46 */    if (AlipayConfig.sign_type.equals("MD5")) {
/*  47: 47 */      mysign = MD5.sign(prestr, cfg.getKey(), AlipayConfig.input_charset);
/*  48:    */    }
/*  49: 49 */    return mysign;
/*  50:    */  }
/*  51:    */  
/*  57:    */  private static Map<String, String> buildRequestPara(Map<String, String> sParaTemp)
/*  58:    */  {
/*  59: 59 */    Map<String, String> sPara = AlipayCore.paraFilter(sParaTemp);
/*  60:    */    
/*  61: 61 */    String mysign = buildRequestMysign(sPara);
/*  62:    */    
/*  63: 63 */    sPara.put("sign", mysign);
/*  64: 64 */    sPara.put("sign_type", AlipayConfig.sign_type);
/*  65:    */    
/*  66: 66 */    return sPara;
/*  67:    */  }
/*  68:    */  
/*  76:    */  public static String buildRequest(Map<String, String> sParaTemp, String strMethod, String strButtonName)
/*  77:    */  {
/*  78: 78 */    Map<String, String> sPara = buildRequestPara(sParaTemp);
/*  79: 79 */    List<String> keys = new ArrayList(sPara.keySet());
/*  80:    */    
/*  81: 81 */    StringBuffer sbHtml = new StringBuffer();
/*  82:    */    
/*  83: 83 */    sbHtml.append("<form id=\"alipaysubmit\" name=\"alipaysubmit\" action=\"https://mapi.alipay.com/gateway.do?_input_charset=" + 
/*  84: 84 */      AlipayConfig.input_charset + "\" method=\"" + strMethod + 
/*  85: 85 */      "\">");
/*  86:    */    
/*  87: 87 */    for (int i = 0; i < keys.size(); i++) {
/*  88: 88 */      String name = (String)keys.get(i);
/*  89: 89 */      String value = (String)sPara.get(name);
/*  90:    */      
/*  91: 91 */      sbHtml.append("<input type=\"hidden\" name=\"" + name + "\" value=\"" + value + "\"/>");
/*  92:    */    }
/*  93:    */    
/*  95: 95 */    sbHtml.append("<input type=\"submit\" value=\"" + strButtonName + "\" style=\"display:none;\"></form>");
/*  96: 96 */    sbHtml.append("<script>document.forms['alipaysubmit'].submit();</script>");
/*  97:    */    
/*  98: 98 */    return sbHtml.toString();
/*  99:    */  }
/* 100:    */  
/* 109:    */  public static String buildRequest(Map<String, String> sParaTemp, String strMethod, String strButtonName, String strParaFileName)
/* 110:    */  {
/* 111:111 */    Map<String, String> sPara = buildRequestPara(sParaTemp);
/* 112:112 */    List<String> keys = new ArrayList(sPara.keySet());
/* 113:    */    
/* 114:114 */    StringBuffer sbHtml = new StringBuffer();
/* 115:    */    
/* 116:116 */    sbHtml.append("<form id=\"alipaysubmit\" name=\"alipaysubmit\"  enctype=\"multipart/form-data\" action=\"https://mapi.alipay.com/gateway.do?_input_charset=" + 
/* 117:117 */      AlipayConfig.input_charset + "\" method=\"" + strMethod + 
/* 118:118 */      "\">");
/* 119:    */    
/* 120:120 */    for (int i = 0; i < keys.size(); i++) {
/* 121:121 */      String name = (String)keys.get(i);
/* 122:122 */      String value = (String)sPara.get(name);
/* 123:    */      
/* 124:124 */      sbHtml.append("<input type=\"hidden\" name=\"" + name + "\" value=\"" + value + "\"/>");
/* 125:    */    }
/* 126:126 */    sbHtml.append("<input type=\"file\" name=\"" + strParaFileName + "\" />");
/* 127:    */    
/* 128:128 */    sbHtml.append("<input type=\"submit\" value=\"" + strButtonName + "\" style=\"display:none;\"></form>");
/* 129:    */    
/* 130:130 */    return sbHtml.toString();
/* 131:    */  }
/* 132:    */  
/* 142:    */  public static String buildRequest(String strParaFileName, String strFilePath, Map<String, String> sParaTemp)
/* 143:    */    throws Exception
/* 144:    */  {
/* 145:145 */    Map<String, String> sPara = buildRequestPara(sParaTemp);
/* 146:146 */    HttpProtocolHandler httpProtocolHandler = HttpProtocolHandler.getInstance();
/* 147:147 */    HttpRequest request = new HttpRequest(HttpResultType.BYTES);
/* 148:    */    
/* 149:149 */    request.setCharset(AlipayConfig.input_charset);
/* 150:150 */    request.setParameters(generatNameValuePair(sPara));
/* 151:151 */    request.setUrl("https://mapi.alipay.com/gateway.do?_input_charset=" + AlipayConfig.input_charset);
/* 152:152 */    HttpResponse response = httpProtocolHandler.execute(request, strParaFileName, strFilePath);
/* 153:153 */    if (response == null) {
/* 154:154 */      return null;
/* 155:    */    }
/* 156:156 */    String strResult = response.getStringResult();
/* 157:157 */    return strResult;
/* 158:    */  }
/* 159:    */  
/* 164:    */  private static NameValuePair[] generatNameValuePair(Map<String, String> properties)
/* 165:    */  {
/* 166:166 */    NameValuePair[] nameValuePair = new NameValuePair[properties.size()];
/* 167:167 */    int i = 0;
/* 168:168 */    for (Map.Entry<String, String> entry : properties.entrySet()) {
/* 169:169 */      nameValuePair[(i++)] = new NameValuePair((String)entry.getKey(), (String)entry.getValue());
/* 170:    */    }
/* 171:171 */    return nameValuePair;
/* 172:    */  }
/* 173:    */  
/* 181:    */  public static String query_timestamp()
/* 182:    */    throws MalformedURLException, DocumentException, IOException
/* 183:    */  {
/* 184:184 */    String strUrl = "https://mapi.alipay.com/gateway.do?service=query_timestamp&partner=" + cfg.getPartner() + "&_input_charset" + AlipayConfig.input_charset;
/* 185:185 */    StringBuffer result = new StringBuffer();
/* 186:    */    
/* 187:187 */    SAXReader reader = new SAXReader();
/* 188:188 */    Document doc = reader.read(new URL(strUrl).openStream());
/* 189:189 */    List<Node> nodeList = doc.selectNodes("//alipay/*");
/* 190:190 */    Iterator localIterator2; label191: for (Iterator localIterator1 = nodeList.iterator(); localIterator1.hasNext(); 
/* 191:    */        
/* 195:195 */        localIterator2.hasNext())
/* 196:    */    {
/* 197:190 */      Node node = (Node)localIterator1.next();
/* 198:    */      
/* 199:192 */      if ((!node.getName().equals("is_success")) || (!node.getText().equals("T")))
/* 200:    */        break label191;
/* 201:194 */      List<Node> nodeList1 = doc.selectNodes("//response/timestamp/*");
/* 202:195 */      localIterator2 = nodeList1.iterator();continue;Node node1 = (Node)localIterator2.next();
/* 203:196 */      result.append(node1.getText());
/* 204:    */    }
/* 205:    */    
/* 207:200 */    return result.toString();
/* 208:    */  }
/* 209:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.alipay.AlipaySubmit
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */