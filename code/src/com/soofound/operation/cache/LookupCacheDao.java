package com.soofound.operation.cache;

import com.soofound.cpe.bean.HostBean;
import com.soofound.portal.bean.SurfingAccount;
import com.soofound.portal.bean.SurfingPolicyDto;

public abstract interface LookupCacheDao
{
  public abstract HostBean getCacheByApmac(String paramString);
  
  public abstract SurfingPolicyDto getCacheByPolicy(String paramString);
  
  public abstract SurfingAccount getCacheByAccount(String paramString1, String paramString2);
}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.operation.cache.LookupCacheDao
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */