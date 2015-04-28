/*  1:   */package com.soofound.framework.jdbc;
/*  2:   */
/*  3:   */import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
/*  4:   */
/*  5:   */public class DynamicDataSource extends AbstractRoutingDataSource
/*  6:   */{
/*  7:   */  private String dsName;
/*  8:   */  
/*  9:   */  protected Object determineCurrentLookupKey() {
/* 10:10 */    return this.dsName;
/* 11:   */  }
/* 12:   */  
/* 13:   */  public String getDsName() {
/* 14:14 */    return this.dsName;
/* 15:   */  }
/* 16:   */  
/* 17:   */  public void setDsName(String dsName) {
/* 18:18 */    this.dsName = dsName;
/* 19:   */  }
/* 20:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.framework.jdbc.DynamicDataSource
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */