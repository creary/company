package com.soofound.protocol.emay;

import java.rmi.Remote;
import java.rmi.RemoteException;

public abstract interface SDKClient
  extends Remote
{
  public abstract String getVersion()
    throws RemoteException;
  
  public abstract StatusReport[] getReport(String paramString1, String paramString2)
    throws RemoteException;
  
  public abstract int cancelMOForward(String paramString1, String paramString2)
    throws RemoteException;
  
  public abstract int chargeUp(String paramString1, String paramString2, String paramString3, String paramString4)
    throws RemoteException;
  
  public abstract double getBalance(String paramString1, String paramString2)
    throws RemoteException;
  
  public abstract double getEachFee(String paramString1, String paramString2)
    throws RemoteException;
  
  public abstract Mo[] getMO(String paramString1, String paramString2)
    throws RemoteException;
  
  public abstract int logout(String paramString1, String paramString2)
    throws RemoteException;
  
  public abstract int registDetailInfo(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, String paramString10)
    throws RemoteException;
  
  public abstract int registEx(String paramString1, String paramString2, String paramString3)
    throws RemoteException;
  
  public abstract int sendSMS(String paramString1, String paramString2, String paramString3, String[] paramArrayOfString, String paramString4, String paramString5, String paramString6, int paramInt, long paramLong)
    throws RemoteException;
  
  public abstract int serialPwdUpd(String paramString1, String paramString2, String paramString3, String paramString4)
    throws RemoteException;
  
  public abstract int setMOForward(String paramString1, String paramString2, String paramString3)
    throws RemoteException;
  
  public abstract int setMOForwardEx(String paramString1, String paramString2, String[] paramArrayOfString)
    throws RemoteException;
}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.emay.SDKClient
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */