/*   1:    */package com.soofound.protocol.emay;
/*   2:    */
/*   3:    */import java.net.MalformedURLException;
/*   4:    */import java.util.Iterator;
/*   5:    */import org.apache.axis.EngineConfiguration;
/*   6:    */import org.apache.axis.client.Stub;
/*   7:    */
/*   8:    */public class SDKServiceLocator extends org.apache.axis.client.Service implements SDKService
/*   9:    */{
/*  10:    */  public SDKServiceLocator() {}
/*  11:    */  
/*  12:    */  public SDKServiceLocator(EngineConfiguration config)
/*  13:    */  {
/*  14: 14 */    super(config);
/*  15:    */  }
/*  16:    */  
/*  17:    */  public SDKServiceLocator(String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
/*  18: 18 */    super(wsdlLoc, sName);
/*  19:    */  }
/*  20:    */  
/*  21: 21 */  private String SDKService_address = "http://sdkhttp.eucp.b2m.cn/sdk/SDKService";
/*  22:    */  
/*  23:    */  public String getSDKServiceAddress() {
/*  24: 24 */    return this.SDKService_address;
/*  25:    */  }
/*  26:    */  
/*  27: 27 */  private String SDKServiceWSDDServiceName = "SDKService";
/*  28:    */  
/*  29:    */  public String getSDKServiceWSDDServiceName() {
/*  30: 30 */    return this.SDKServiceWSDDServiceName;
/*  31:    */  }
/*  32:    */  
/*  33:    */  public void setSDKServiceWSDDServiceName(String name) {
/*  34: 34 */    this.SDKServiceWSDDServiceName = name;
/*  35:    */  }
/*  36:    */  
/*  37:    */  public SDKClient getSDKService() throws javax.xml.rpc.ServiceException
/*  38:    */  {
/*  39:    */    try {
/*  40: 40 */      endpoint = new java.net.URL(this.SDKService_address);
/*  41:    */    } catch (MalformedURLException e) {
/*  42:    */      java.net.URL endpoint;
/*  43: 43 */      throw new javax.xml.rpc.ServiceException(e); }
/*  44:    */    java.net.URL endpoint;
/*  45: 45 */    return getSDKService(endpoint);
/*  46:    */  }
/*  47:    */  
/*  48:    */  public SDKClient getSDKService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
/*  49:    */    try {
/*  50: 50 */      SDKServiceBindingStub _stub = new SDKServiceBindingStub(portAddress, this);
/*  51: 51 */      _stub.setPortName(getSDKServiceWSDDServiceName());
/*  52: 52 */      return _stub;
/*  53:    */    }
/*  54:    */    catch (org.apache.axis.AxisFault e) {}
/*  55: 55 */    return null;
/*  56:    */  }
/*  57:    */  
/*  58:    */  public void setSDKServiceEndpointAddress(String address)
/*  59:    */  {
/*  60: 60 */    this.SDKService_address = address;
/*  61:    */  }
/*  62:    */  
/*  65:    */  public java.rmi.Remote getPort(Class serviceEndpointInterface)
/*  66:    */    throws javax.xml.rpc.ServiceException
/*  67:    */  {
/*  68:    */    try
/*  69:    */    {
/*  70: 70 */      if (SDKClient.class.isAssignableFrom(serviceEndpointInterface)) {
/*  71: 71 */        SDKServiceBindingStub _stub = new SDKServiceBindingStub(new java.net.URL(this.SDKService_address), this);
/*  72: 72 */        _stub.setPortName(getSDKServiceWSDDServiceName());
/*  73: 73 */        return _stub;
/*  74:    */      }
/*  75:    */    }
/*  76:    */    catch (Throwable t) {
/*  77: 77 */      throw new javax.xml.rpc.ServiceException(t);
/*  78:    */    }
/*  79: 79 */    throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
/*  80:    */  }
/*  81:    */  
/*  85:    */  public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface)
/*  86:    */    throws javax.xml.rpc.ServiceException
/*  87:    */  {
/*  88: 88 */    if (portName == null) {
/*  89: 89 */      return getPort(serviceEndpointInterface);
/*  90:    */    }
/*  91: 91 */    String inputPortName = portName.getLocalPart();
/*  92: 92 */    if ("SDKService".equals(inputPortName)) {
/*  93: 93 */      return getSDKService();
/*  94:    */    }
/*  95:    */    
/*  96: 96 */    java.rmi.Remote _stub = getPort(serviceEndpointInterface);
/*  97: 97 */    ((Stub)_stub).setPortName(portName);
/*  98: 98 */    return _stub;
/*  99:    */  }
/* 100:    */  
/* 101:    */  public javax.xml.namespace.QName getServiceName()
/* 102:    */  {
/* 103:103 */    return new javax.xml.namespace.QName("http://sdkhttp.eucp.b2m.cn/", "SDKService");
/* 104:    */  }
/* 105:    */  
/* 106:106 */  private java.util.HashSet ports = null;
/* 107:    */  
/* 108:    */  public Iterator getPorts() {
/* 109:109 */    if (this.ports == null) {
/* 110:110 */      this.ports = new java.util.HashSet();
/* 111:111 */      this.ports.add(new javax.xml.namespace.QName("http://sdkhttp.eucp.b2m.cn/", "SDKService"));
/* 112:    */    }
/* 113:113 */    return this.ports.iterator();
/* 114:    */  }
/* 115:    */  
/* 117:    */  public void setEndpointAddress(String portName, String address)
/* 118:    */    throws javax.xml.rpc.ServiceException
/* 119:    */  {
/* 120:120 */    if ("SDKService".equals(portName)) {
/* 121:121 */      setSDKServiceEndpointAddress(address);
/* 122:    */    } else {
/* 123:123 */      throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
/* 124:    */    }
/* 125:    */  }
/* 126:    */  
/* 128:    */  public void setEndpointAddress(javax.xml.namespace.QName portName, String address)
/* 129:    */    throws javax.xml.rpc.ServiceException
/* 130:    */  {
/* 131:131 */    setEndpointAddress(portName.getLocalPart(), address);
/* 132:    */  }
/* 133:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.emay.SDKServiceLocator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */