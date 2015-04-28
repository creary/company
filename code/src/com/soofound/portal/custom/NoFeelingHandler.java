/*   1:    */package com.soofound.portal.custom;
/*   2:    */
/*   3:    */import com.soofound.cpe.bean.HostBean;
/*   4:    */import com.soofound.cpe.bean.HostPropertyBean;
/*   5:    */import com.soofound.cpe.util.CpeActionExecutor;
/*   6:    */import com.soofound.cpe.web.HostService;
/*   7:    */import com.soofound.framework.util.NetworkUtil;
/*   8:    */import com.soofound.framework.util.SysConfigHelper;
/*   9:    */import com.soofound.portal.bean.SurfingAccount;
/*  10:    */import com.soofound.portal.dao.SurfingAccountDao;
/*  11:    */import com.soofound.portal.service.WifiDogService;
/*  12:    */import com.soofound.protocol.cmcc.AcknowledgeAuthentication;
/*  13:    */import java.util.ArrayList;
/*  14:    */import java.util.List;
/*  15:    */import net.jradius.client.RadiusClient;
/*  16:    */import net.jradius.dictionary.Attr_AcctSessionId;
/*  17:    */import net.jradius.dictionary.Attr_CalledStationId;
/*  18:    */import net.jradius.dictionary.Attr_FramedIPAddress;
/*  19:    */import net.jradius.dictionary.Attr_NASIdentifier;
/*  20:    */import net.jradius.dictionary.Attr_NASPortId;
/*  21:    */import net.jradius.dictionary.Attr_UserName;
/*  22:    */import net.jradius.dictionary.Attr_UserPassword;
/*  23:    */import net.jradius.packet.AccessReject;
/*  24:    */import net.jradius.packet.AccessRequest;
/*  25:    */import net.jradius.packet.RadiusResponse;
/*  26:    */import net.jradius.packet.attribute.AttributeList;
/*  27:    */
/*  28:    */public class NoFeelingHandler
/*  29:    */{
/*  30:    */  public boolean doHandle(String portalIP, String staip, String apmac, String stamac, String ife)
/*  31:    */  {
/*  32:    */    try
/*  33:    */    {
/*  34: 34 */      HostService hostService = (HostService)SysConfigHelper.getBean("hostService");
/*  35: 35 */      HostBean host = hostService.findBySerialNumber(apmac);
/*  36: 36 */      WifiDogService wds = (WifiDogService)SysConfigHelper.getBean("wifiDogService");
/*  37: 37 */      CustomFactory cf = (CustomFactory)SysConfigHelper.getBean("customFactory");
/*  38: 38 */      com.soofound.portal.bean.SurfingUser user = wds.getSurfingUser(host.getBranchId(), stamac);
/*  39: 39 */      if (user != null) {
/*  40: 40 */        return true;
/*  41:    */      }
/*  42: 42 */      AcknowledgeAuthentication aa = new AcknowledgeAuthentication();
/*  43: 43 */      aa.setId(convertIP(staip));
/*  44: 44 */      aa.setRemoteIP(portalIP);
/*  45:    */      
/*  46: 46 */      ThirdPartyHandler handler = cf.getThirdPartyHandler(portalIP);
/*  47: 47 */      AttributeList attrs = new AttributeList();
/*  48: 48 */      String _mac = stamac.replace(":", "");
/*  49: 49 */      attrs.add(new Attr_UserName(_mac));
/*  50: 50 */      attrs.add(new Attr_UserPassword(_mac));
/*  51: 51 */      attrs.add(new Attr_NASIdentifier("edras128.0.0.99"));
/*  52: 52 */      attrs.add(new Attr_AcctSessionId(Integer.valueOf(aa.getId())));
/*  53: 53 */      attrs.add(new Attr_FramedIPAddress(staip));
/*  54: 54 */      attrs.add(new Attr_CalledStationId(stamac));
/*  55: 55 */      attrs.add(new Attr_NASPortId(apmac));
/*  56:    */      
/*  57: 57 */      AccessRequest ar = new AccessRequest(handler.getRadiusClient(), attrs);
/*  58: 58 */      RadiusResponse reply = handler.getRadiusClient().authenticate(ar, null, 3);
/*  59: 59 */      if ((reply instanceof AccessReject))
/*  60:    */      {
/*  61: 61 */        return false;
/*  62:    */      }
/*  63: 63 */      handler.putAttributes(aa.getId(), attrs);
/*  64:    */      
/*  65: 65 */      user = new com.soofound.portal.bean.SurfingUser();
/*  66: 66 */      user.setUsername(stamac.replace(":", ""));
/*  67: 67 */      user.setBranchId(host.getBranchId());
/*  68: 68 */      user.setCpeId(host.getId());
/*  69: 69 */      user.setTerminalMac(stamac);
/*  70: 70 */      user.setTerminalIP(staip);
/*  71: 71 */      user.setPacket(aa);
/*  72: 72 */      user.setSsid(host.getId() + "-" + ife);
/*  73: 73 */      wds.recordOnline(user);
/*  74:    */      
/*  75: 75 */      List<HostPropertyBean> hpbs = new ArrayList();
/*  76: 76 */      HostPropertyBean hpb = new HostPropertyBean();
/*  77: 77 */      hpb.setName("InternetGatewayDevice.DeviceInfo.add_station_mac");
/*  78: 78 */      hpb.setValue(stamac);
/*  79: 79 */      hpbs.add(hpb);
/*  80:    */      
/*  81: 81 */      CpeActionExecutor cae = new CpeActionExecutor();
/*  82: 82 */      hostService.putCommand(host.getId(), cae.getSetParameterValuesString(hpbs));
/*  83: 83 */      handler.wakeup(host);
/*  84:    */      
/*  85: 85 */      SurfingAccountDao dao = new SurfingAccountDao();
/*  86: 86 */      SurfingAccount dto = dao.findByUsername(host.getBranchId(), user.getUsername());
/*  87: 87 */      if (dto == null) {
/*  88: 88 */        dto = new SurfingAccount();
/*  89: 89 */        dto.setMac(stamac);
/*  90: 90 */        dto.setUsername(user.getUsername());
/*  91: 91 */        dto.setBranchId(user.getBranchId());
/*  92: 92 */        dto.setPassword("-");
/*  93: 93 */        dto.setFlag("thirdPart");
/*  94: 94 */        dao.save(dto);
/*  95:    */      } else {
/*  96: 96 */        dao.update(dto);
/*  97: 97 */        dto.setFlag("thirdPart");
/*  98:    */      }
/*  99:    */    } catch (Exception ex) {
/* 100:100 */      ex.printStackTrace();
/* 101:    */    }
/* 102:102 */    return true;
/* 103:    */  }
/* 104:    */  
/* 105:    */  public int convertIP(String ipAddress) {
/* 106:106 */    long ipa = NetworkUtil.ipToLong(ipAddress);
/* 107:107 */    int val = (int)ipa;
/* 108:108 */    if (val < 0)
/* 109:109 */      val += 2147483647;
/* 110:110 */    return val;
/* 111:    */  }
/* 112:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.custom.NoFeelingHandler
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */