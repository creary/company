/*  1:   */package com.soofound.portal.util;
/*  2:   */
/*  3:   */import com.soofound.framework.job.ScheduleJob;
/*  4:   */import com.soofound.framework.license.License;
/*  5:   */import com.soofound.framework.util.DateUtil;
/*  6:   */import com.soofound.framework.util.SysConfigHelper;
/*  7:   */import java.util.ArrayList;
/*  8:   */import java.util.HashMap;
/*  9:   */import java.util.List;
/* 10:   */import java.util.concurrent.TimeUnit;
/* 11:   */import org.apache.log4j.Logger;
/* 12:   */import org.springframework.http.converter.HttpMessageConverter;
/* 13:   */import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
/* 14:   */import org.springframework.web.client.RestTemplate;
/* 15:   */
/* 16:   */public class SooFacHeartBeat implements ScheduleJob
/* 17:   */{
/* 18:18 */  private Logger loger = Logger.getLogger("SooFacHeartBeat");
/* 19:   */  private long lastTime;
/* 20:   */  private RestTemplate restTemplate;
/* 21:   */  private List<HttpMessageConverter<?>> converters;
/* 22:   */  
/* 23:   */  public SooFacHeartBeat() {
/* 24:24 */    this.converters = new ArrayList();
/* 25:25 */    this.converters.add(new MappingJacksonHttpMessageConverter());
/* 26:26 */    this.restTemplate = new RestTemplate();
/* 27:27 */    this.restTemplate.setMessageConverters(this.converters);
/* 28:   */  }
/* 29:   */  
/* 30:   */  public void run()
/* 31:   */  {
/* 32:   */    try {
/* 33:33 */      HashMap<String, Object> stunInfo = new HashMap();
/* 34:34 */      stunInfo.put("productID", SysConfigHelper.getLicense().getProductID());
/* 35:35 */      if (this.lastTime == 0L) {
/* 36:36 */        this.lastTime = DateUtil.getCurrentLongDateTime();
/* 37:   */      } else {
/* 38:38 */        stunInfo.put("stun", "afunms");
/* 39:39 */        Runtime.getRuntime().gc();
/* 40:   */      }
/* 41:41 */      this.restTemplate.postForObject("http://121.199.34.227/site/putProductStun.do", stunInfo, HashMap.class, new Object[0]);
/* 42:   */    } catch (Exception ex) {
/* 43:43 */      this.loger.error("=====heart beat failed=====");
/* 44:   */    }
/* 45:   */  }
/* 46:   */  
/* 47:   */  public int getDelay() {
/* 48:48 */    return 1;
/* 49:   */  }
/* 50:   */  
/* 51:   */  public int getPeriod() {
/* 52:52 */    return 15;
/* 53:   */  }
/* 54:   */  
/* 55:   */  public TimeUnit getTimeUnit() {
/* 56:56 */    return TimeUnit.MINUTES;
/* 57:   */  }
/* 58:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.util.SooFacHeartBeat
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */