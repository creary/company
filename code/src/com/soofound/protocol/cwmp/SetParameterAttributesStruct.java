/*  1:   */package com.soofound.protocol.cwmp;
/*  2:   */
/*  3:   */import java.io.Serializable;
/*  4:   */
/*  5:   */class SetParameterAttributesStruct implements Serializable {
/*  6:   */  private static final long serialVersionUID = 201312221937001L;
/*  7:   */  public String Name;
/*  8:   */  public boolean NotificationChange;
/*  9:   */  public int Notification;
/* 10:   */  public boolean AccessListChange;
/* 11:   */  public String[] AccessList;
/* 12:   */  
/* 13:   */  SetParameterAttributesStruct(String Name, boolean NotificationChange, int Notification, boolean AccessListChange, String[] AccessList) {
/* 14:14 */    this.Name = Name;
/* 15:15 */    this.NotificationChange = NotificationChange;
/* 16:16 */    this.Notification = Notification;
/* 17:17 */    this.AccessList = AccessList;
/* 18:18 */    this.AccessListChange = AccessListChange;
/* 19:   */  }
/* 20:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.cwmp.SetParameterAttributesStruct
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */