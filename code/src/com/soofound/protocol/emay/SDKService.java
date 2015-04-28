package com.soofound.protocol.emay;

import java.net.URL;
import javax.xml.rpc.Service;
import javax.xml.rpc.ServiceException;

public abstract interface SDKService
  extends Service
{
  public abstract String getSDKServiceAddress();
  
  public abstract SDKClient getSDKService()
    throws ServiceException;
  
  public abstract SDKClient getSDKService(URL paramURL)
    throws ServiceException;
}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.emay.SDKService
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */