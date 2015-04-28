/*   1:    */package com.soofound.protocol.emay;
/*   2:    */
/*   3:    */import junit.framework.AssertionFailedError;
/*   4:    */
/*   5:    */public class Client
/*   6:    */{
/*   7:    */  private String softwareSerialNo;
/*   8:    */  private String key;
/*   9:    */  SDKServiceBindingStub binding;
/*  10:    */  
/*  11:    */  public Client(String sn, String key) {
/*  12: 12 */    this.softwareSerialNo = sn;
/*  13: 13 */    this.key = key;
/*  14: 14 */    init();
/*  15:    */  }
/*  16:    */  
/*  17:    */  public void init()
/*  18:    */  {
/*  19:    */    try
/*  20:    */    {
/*  21: 21 */      this.binding = ((SDKServiceBindingStub)new SDKServiceLocator().getSDKService());
/*  22:    */    } catch (javax.xml.rpc.ServiceException jre) {
/*  23: 23 */      if (jre.getLinkedCause() != null)
/*  24: 24 */        jre.getLinkedCause().printStackTrace();
/*  25: 25 */      throw new AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
/*  26:    */    }
/*  27:    */  }
/*  28:    */  
/*  29:    */  public int chargeUp(String cardNo, String cardPass) throws java.rmi.RemoteException {
/*  30: 30 */    int value = -1;
/*  31: 31 */    value = this.binding.chargeUp(this.softwareSerialNo, this.key, cardNo, cardPass);
/*  32: 32 */    return value;
/*  33:    */  }
/*  34:    */  
/*  35:    */  public double getBalance() throws java.rmi.RemoteException {
/*  36: 36 */    double value = 0.0D;
/*  37: 37 */    value = this.binding.getBalance(this.softwareSerialNo, this.key);
/*  38: 38 */    return value;
/*  39:    */  }
/*  40:    */  
/*  41:    */  public double getEachFee() throws java.rmi.RemoteException {
/*  42: 42 */    double value = 0.0D;
/*  43: 43 */    value = this.binding.getEachFee(this.softwareSerialNo, this.key);
/*  44: 44 */    return value;
/*  45:    */  }
/*  46:    */  
/*  47:    */  public java.util.List<Mo> getMO() throws java.rmi.RemoteException {
/*  48: 48 */    Mo[] mo = this.binding.getMO(this.softwareSerialNo, this.key);
/*  49: 49 */    if (mo == null) {
/*  50: 50 */      return null;
/*  51:    */    }
/*  52: 52 */    java.util.List<Mo> molist = java.util.Arrays.asList(mo);
/*  53: 53 */    return molist;
/*  54:    */  }
/*  55:    */  
/*  56:    */  public java.util.List<StatusReport> getReport() throws java.rmi.RemoteException
/*  57:    */  {
/*  58: 58 */    StatusReport[] sr = this.binding.getReport(this.softwareSerialNo, this.key);
/*  59: 59 */    if (sr != null) {
/*  60: 60 */      return java.util.Arrays.asList(sr);
/*  61:    */    }
/*  62: 62 */    return null;
/*  63:    */  }
/*  64:    */  
/*  65:    */  public int logout() throws java.rmi.RemoteException
/*  66:    */  {
/*  67: 67 */    int value = -1;
/*  68: 68 */    value = this.binding.logout(this.softwareSerialNo, this.key);
/*  69: 69 */    return value;
/*  70:    */  }
/*  71:    */  
/*  72:    */  public int registDetailInfo(String eName, String linkMan, String phoneNum, String mobile, String email, String fax, String address, String postcode)
/*  73:    */    throws java.rmi.RemoteException
/*  74:    */  {
/*  75: 75 */    int value = -1;
/*  76: 76 */    value = this.binding.registDetailInfo(this.softwareSerialNo, this.key, eName, linkMan, phoneNum, mobile, email, fax, address, postcode);
/*  77: 77 */    return value;
/*  78:    */  }
/*  79:    */  
/*  80:    */  public int registEx(String password) throws java.rmi.RemoteException {
/*  81: 81 */    int value = -1;
/*  82: 82 */    value = this.binding.registEx(this.softwareSerialNo, this.key, password);
/*  83: 83 */    return value;
/*  84:    */  }
/*  85:    */  
/*  86:    */  public int sendSMS(String[] mobiles, String smsContent, String addSerial, int smsPriority) throws java.rmi.RemoteException {
/*  87: 87 */    int value = -1;
/*  88: 88 */    value = this.binding.sendSMS(this.softwareSerialNo, this.key, "", mobiles, smsContent, addSerial, "gbk", smsPriority, 0L);
/*  89: 89 */    return value;
/*  90:    */  }
/*  91:    */  
/*  92:    */  public int sendScheduledSMSEx(String[] mobiles, String smsContent, String sendTime, String srcCharset) throws java.rmi.RemoteException {
/*  93: 93 */    int value = -1;
/*  94: 94 */    value = this.binding.sendSMS(this.softwareSerialNo, this.key, sendTime, mobiles, smsContent, "", srcCharset, 3, 0L);
/*  95: 95 */    return value;
/*  96:    */  }
/*  97:    */  
/*  98:    */  public int sendSMSEx(String[] mobiles, String smsContent, String addSerial, String srcCharset, int smsPriority, long smsID) throws java.rmi.RemoteException {
/*  99: 99 */    int value = -1;
/* 100:100 */    value = this.binding.sendSMS(this.softwareSerialNo, this.key, "", mobiles, smsContent, addSerial, srcCharset, smsPriority, smsID);
/* 101:101 */    return value;
/* 102:    */  }
/* 103:    */  
/* 104:    */  public int serialPwdUpd(String serialPwd, String serialPwdNew) throws java.rmi.RemoteException {
/* 105:105 */    int value = -1;
/* 106:106 */    value = this.binding.serialPwdUpd(this.softwareSerialNo, this.key, serialPwd, serialPwdNew);
/* 107:107 */    return value;
/* 108:    */  }
/* 109:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.emay.Client
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */