/*  1:   */package com.soofound.operation.cache;
/*  2:   */
/*  3:   */import java.io.Serializable;
/*  4:   */import java.lang.reflect.Method;
/*  5:   */import net.sf.ehcache.Cache;
/*  6:   */import net.sf.ehcache.Element;
/*  7:   */import org.aopalliance.intercept.MethodInterceptor;
/*  8:   */import org.springframework.beans.factory.InitializingBean;
/*  9:   */
/* 10:   */public class MethodCacheInterceptor implements MethodInterceptor, InitializingBean
/* 11:   */{
/* 12:   */  private Cache cache;
/* 13:   */  
/* 14:   */  public void setCache(Cache cache)
/* 15:   */  {
/* 16:16 */    this.cache = cache;
/* 17:   */  }
/* 18:   */  
/* 22:   */  public Object invoke(org.aopalliance.intercept.MethodInvocation invocation)
/* 23:   */    throws Throwable
/* 24:   */  {
/* 25:25 */    String targetName = invocation.getThis().getClass().getName();
/* 26:26 */    String methodName = invocation.getMethod().getName();
/* 27:27 */    Object[] arguments = invocation.getArguments();
/* 28:   */    
/* 30:30 */    String cacheKey = getCacheKey(targetName, methodName, arguments);
/* 31:31 */    Element element = this.cache.get(cacheKey);
/* 32:32 */    if (element == null)
/* 33:   */    {
/* 37:37 */      Object result = invocation.proceed();
/* 38:   */      
/* 41:41 */      element = new Element(cacheKey, (Serializable)result);
/* 42:42 */      this.cache.put(element);
/* 43:   */    }
/* 44:44 */    return element.getValue();
/* 45:   */  }
/* 46:   */  
/* 47:   */  private String getCacheKey(String targetName, String methodName, Object[] arguments) {
/* 48:48 */    StringBuffer sb = new StringBuffer();
/* 49:49 */    sb.append(targetName).append(".").append(methodName);
/* 50:50 */    if ((arguments != null) && (arguments.length != 0)) {
/* 51:51 */      for (int i = 0; i < arguments.length; i++) {
/* 52:52 */        sb.append(".").append(arguments[i]);
/* 53:   */      }
/* 54:   */    }
/* 55:55 */    return sb.toString();
/* 56:   */  }
/* 57:   */  
/* 58:   */  public void afterPropertiesSet() throws Exception {
/* 59:59 */    if (this.cache == null) {
/* 60:60 */      throw new IllegalArgumentException("Cache should not be null.");
/* 61:   */    }
/* 62:   */  }
/* 63:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.operation.cache.MethodCacheInterceptor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */