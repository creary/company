package com.soofound.portal.service;

import com.soofound.portal.bean.SurfingUser;

public abstract interface SurfingSessionStore
{
  public abstract void recordOnline(SurfingUser paramSurfingUser);
  
  public abstract void recordTraffic(SurfingUser paramSurfingUser);
  
  public abstract void recordOffline(SurfingUser paramSurfingUser);
  
  public abstract void recordRoaming(SurfingUser paramSurfingUser);
}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.service.SurfingSessionStore
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */