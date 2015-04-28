/*  1:   */package com.soofound.protocol.cwmp;
/*  2:   */
/*  3:   */import java.io.Serializable;
/*  4:   */
/*  5:   */public class Event implements java.util.Map.Entry<String, String>, Serializable
/*  6:   */{
/*  7:   */  private static final long serialVersionUID = 201401031056001L;
/*  8:   */  private String event;
/*  9:   */  private String cmdKey;
/* 10:   */  
/* 11:   */  public Event(String event, String cmdKey)
/* 12:   */  {
/* 13:13 */    this.event = event;
/* 14:14 */    this.cmdKey = cmdKey;
/* 15:   */  }
/* 16:   */  
/* 17:   */  public String getKey() {
/* 18:18 */    return this.event;
/* 19:   */  }
/* 20:   */  
/* 21:   */  public String getValue() {
/* 22:22 */    return this.cmdKey;
/* 23:   */  }
/* 24:   */  
/* 25:   */  public String setValue(String value) {
/* 26:26 */    return this.cmdKey = value;
/* 27:   */  }
/* 28:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.cwmp.Event
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */