/*   1:    */package com.soofound.protocol.emay;
/*   2:    */
/*   3:    */import javax.xml.rpc.ServiceException;
/*   4:    */
/*   5:    */public class SDKClientProxy implements SDKClient {
/*   6:  6 */  private String _endpoint = null;
/*   7:  7 */  private SDKClient sDKClient = null;
/*   8:    */  
/*   9:    */  public SDKClientProxy() {
/*  10: 10 */    _initSDKClientProxy();
/*  11:    */  }
/*  12:    */  
/*  13:    */  public SDKClientProxy(String endpoint) {
/*  14: 14 */    this._endpoint = endpoint;
/*  15: 15 */    _initSDKClientProxy();
/*  16:    */  }
/*  17:    */  
/*  18:    */  private void _initSDKClientProxy() {
/*  19:    */    try {
/*  20: 20 */      this.sDKClient = new SDKServiceLocator().getSDKService();
/*  21: 21 */      if (this.sDKClient != null) {
/*  22: 22 */        if (this._endpoint != null) {
/*  23: 23 */          ((javax.xml.rpc.Stub)this.sDKClient)._setProperty("javax.xml.rpc.service.endpoint.address", this._endpoint);
/*  24:    */        } else {
/*  25: 25 */          this._endpoint = ((String)((javax.xml.rpc.Stub)this.sDKClient)._getProperty("javax.xml.rpc.service.endpoint.address"));
/*  26:    */        }
/*  27:    */      }
/*  28:    */    } catch (ServiceException localServiceException) {}
/*  29:    */  }
/*  30:    */  
/*  31:    */  public String getEndpoint() {
/*  32: 32 */    return this._endpoint;
/*  33:    */  }
/*  34:    */  
/*  35:    */  public void setEndpoint(String endpoint) {
/*  36: 36 */    this._endpoint = endpoint;
/*  37: 37 */    if (this.sDKClient != null)
/*  38: 38 */      ((javax.xml.rpc.Stub)this.sDKClient)._setProperty("javax.xml.rpc.service.endpoint.address", this._endpoint);
/*  39:    */  }
/*  40:    */  
/*  41:    */  public SDKClient getSDKClient() {
/*  42: 42 */    if (this.sDKClient == null)
/*  43: 43 */      _initSDKClientProxy();
/*  44: 44 */    return this.sDKClient;
/*  45:    */  }
/*  46:    */  
/*  47:    */  public String getVersion() throws java.rmi.RemoteException {
/*  48: 48 */    if (this.sDKClient == null)
/*  49: 49 */      _initSDKClientProxy();
/*  50: 50 */    return this.sDKClient.getVersion();
/*  51:    */  }
/*  52:    */  
/*  53:    */  public StatusReport[] getReport(String arg0, String arg1) throws java.rmi.RemoteException {
/*  54: 54 */    if (this.sDKClient == null)
/*  55: 55 */      _initSDKClientProxy();
/*  56: 56 */    return this.sDKClient.getReport(arg0, arg1);
/*  57:    */  }
/*  58:    */  
/*  59:    */  public int cancelMOForward(String arg0, String arg1) throws java.rmi.RemoteException {
/*  60: 60 */    if (this.sDKClient == null)
/*  61: 61 */      _initSDKClientProxy();
/*  62: 62 */    return this.sDKClient.cancelMOForward(arg0, arg1);
/*  63:    */  }
/*  64:    */  
/*  65:    */  public int chargeUp(String arg0, String arg1, String arg2, String arg3) throws java.rmi.RemoteException {
/*  66: 66 */    if (this.sDKClient == null)
/*  67: 67 */      _initSDKClientProxy();
/*  68: 68 */    return this.sDKClient.chargeUp(arg0, arg1, arg2, arg3);
/*  69:    */  }
/*  70:    */  
/*  71:    */  public double getBalance(String arg0, String arg1) throws java.rmi.RemoteException {
/*  72: 72 */    if (this.sDKClient == null)
/*  73: 73 */      _initSDKClientProxy();
/*  74: 74 */    return this.sDKClient.getBalance(arg0, arg1);
/*  75:    */  }
/*  76:    */  
/*  77:    */  public double getEachFee(String arg0, String arg1) throws java.rmi.RemoteException {
/*  78: 78 */    if (this.sDKClient == null)
/*  79: 79 */      _initSDKClientProxy();
/*  80: 80 */    return this.sDKClient.getEachFee(arg0, arg1);
/*  81:    */  }
/*  82:    */  
/*  83:    */  public Mo[] getMO(String arg0, String arg1) throws java.rmi.RemoteException {
/*  84: 84 */    if (this.sDKClient == null)
/*  85: 85 */      _initSDKClientProxy();
/*  86: 86 */    return this.sDKClient.getMO(arg0, arg1);
/*  87:    */  }
/*  88:    */  
/*  89:    */  public int logout(String arg0, String arg1) throws java.rmi.RemoteException {
/*  90: 90 */    if (this.sDKClient == null)
/*  91: 91 */      _initSDKClientProxy();
/*  92: 92 */    return this.sDKClient.logout(arg0, arg1);
/*  93:    */  }
/*  94:    */  
/*  95:    */  public int registDetailInfo(String arg0, String arg1, String arg2, String arg3, String arg4, String arg5, String arg6, String arg7, String arg8, String arg9) throws java.rmi.RemoteException {
/*  96: 96 */    if (this.sDKClient == null)
/*  97: 97 */      _initSDKClientProxy();
/*  98: 98 */    return this.sDKClient.registDetailInfo(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9);
/*  99:    */  }
/* 100:    */  
/* 101:    */  public int registEx(String arg0, String arg1, String arg2) throws java.rmi.RemoteException {
/* 102:102 */    if (this.sDKClient == null)
/* 103:103 */      _initSDKClientProxy();
/* 104:104 */    return this.sDKClient.registEx(arg0, arg1, arg2);
/* 105:    */  }
/* 106:    */  
/* 107:    */  public int sendSMS(String arg0, String arg1, String arg2, String[] arg3, String arg4, String arg5, String arg6, int arg7, long arg8) throws java.rmi.RemoteException {
/* 108:108 */    if (this.sDKClient == null)
/* 109:109 */      _initSDKClientProxy();
/* 110:110 */    return this.sDKClient.sendSMS(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
/* 111:    */  }
/* 112:    */  
/* 113:    */  public int serialPwdUpd(String arg0, String arg1, String arg2, String arg3) throws java.rmi.RemoteException {
/* 114:114 */    if (this.sDKClient == null)
/* 115:115 */      _initSDKClientProxy();
/* 116:116 */    return this.sDKClient.serialPwdUpd(arg0, arg1, arg2, arg3);
/* 117:    */  }
/* 118:    */  
/* 119:    */  public int setMOForward(String arg0, String arg1, String arg2) throws java.rmi.RemoteException {
/* 120:120 */    if (this.sDKClient == null)
/* 121:121 */      _initSDKClientProxy();
/* 122:122 */    return this.sDKClient.setMOForward(arg0, arg1, arg2);
/* 123:    */  }
/* 124:    */  
/* 125:    */  public int setMOForwardEx(String arg0, String arg1, String[] arg2) throws java.rmi.RemoteException {
/* 126:126 */    if (this.sDKClient == null)
/* 127:127 */      _initSDKClientProxy();
/* 128:128 */    return this.sDKClient.setMOForwardEx(arg0, arg1, arg2);
/* 129:    */  }
/* 130:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.emay.SDKClientProxy
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */