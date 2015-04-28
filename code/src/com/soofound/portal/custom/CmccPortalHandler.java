/*   1:    */package com.soofound.portal.custom;
/*   2:    */
/*   3:    */import com.soofound.cpe.bean.HostBean;
/*   4:    */import com.soofound.cpe.bean.HostPropertyBean;
/*   5:    */import com.soofound.cpe.util.CpeActionExecutor;
/*   6:    */import com.soofound.cpe.web.HostService;
/*   7:    */import com.soofound.framework.util.SysConfigHelper;
/*   8:    */import com.soofound.portal.bean.SurfingAccount;
/*   9:    */import com.soofound.portal.dao.SurfingAccountDao;
/*  10:    */import com.soofound.portal.service.QuoteOfMessageHandler;
/*  11:    */import com.soofound.portal.service.WifiDogService;
/*  12:    */import com.soofound.protocol.cmcc.AcknowledgeAuthentication;
/*  13:    */import com.soofound.protocol.cmcc.AcknowledgeChallenge;
/*  14:    */import com.soofound.protocol.cmcc.AcknowledgeLogout;
/*  15:    */import com.soofound.protocol.cmcc.AffirmAcknowledgeAuthentication;
/*  16:    */import com.soofound.protocol.cmcc.ReplyMessageAttribute;
/*  17:    */import com.soofound.protocol.cmcc.RequestAuthentication;
/*  18:    */import com.soofound.protocol.cmcc.RequestChallenge;
/*  19:    */import com.soofound.protocol.cmcc.RequestLogout;
/*  20:    */import com.soofound.protocol.cmcc.UserNameAttribute;
/*  21:    */import com.soofound.protocol.cmcc.UserPasswordAttribute;
/*  22:    */import java.io.PrintStream;
/*  23:    */import java.util.ArrayList;
/*  24:    */import java.util.List;
/*  25:    */import net.jradius.client.RadiusClient;
/*  26:    */import net.jradius.dictionary.Attr_AcctSessionId;
/*  27:    */import net.jradius.dictionary.Attr_CalledStationId;
/*  28:    */import net.jradius.dictionary.Attr_FramedIPAddress;
/*  29:    */import net.jradius.dictionary.Attr_NASIdentifier;
/*  30:    */import net.jradius.dictionary.Attr_NASPortId;
/*  31:    */import net.jradius.dictionary.Attr_UserName;
/*  32:    */import net.jradius.dictionary.Attr_UserPassword;
/*  33:    */import net.jradius.packet.AccessAccept;
/*  34:    */import net.jradius.packet.AccessReject;
/*  35:    */import net.jradius.packet.AccessRequest;
/*  36:    */import net.jradius.packet.RadiusResponse;
/*  37:    */import net.jradius.packet.attribute.AttributeList;
/*  38:    */import net.jradius.packet.attribute.RadiusAttribute;
/*  39:    */import net.jradius.packet.attribute.value.AttributeValue;
/*  40:    */
/*  41:    */public class CmccPortalHandler extends ThirdPartyHandler
/*  42:    */{
/*  43:    */  public void doHandle(com.soofound.protocol.cmcc.PortalPacket pp)
/*  44:    */  {
/*  45: 45 */    WifiDogService wds = (WifiDogService)SysConfigHelper.getBean("wifiDogService");
/*  46:    */    try {
/*  47: 47 */      if ((pp instanceof RequestLogout)) {
/*  48: 48 */        List<com.soofound.portal.bean.SurfingUser> users = wds.getSurfingUser4(pp.getUserIP());
/*  49: 49 */        if (users.isEmpty()) {
/*  50: 50 */          System.out.println("RequestLogout user's empty:" + pp.getUserIP());
/*  51:    */        } else {
/*  52: 52 */          for (com.soofound.portal.bean.SurfingUser user : users) {
/*  53: 53 */            if ("thirdParty".equals(user.getFlag())) {
/*  54: 54 */              System.out.println("RequestLogout:" + user.getTerminalIP() + "@" + user.getBranchId());
/*  55: 55 */              wds.recordOffline(user);
/*  56:    */            }
/*  57:    */          }
/*  58:    */        }
/*  59: 59 */        RequestLogout rl = (RequestLogout)pp;
/*  60: 60 */        AcknowledgeLogout al = new AcknowledgeLogout(rl);
/*  61: 61 */        callback(al, pp.getRemoteIP(), pp.getRemotePort());
/*  62: 62 */        return;
/*  63:    */      }
/*  64: 64 */      if ((pp instanceof AffirmAcknowledgeAuthentication)) {
/*  65: 65 */        return;
/*  66:    */      }
/*  67: 67 */      if ((pp instanceof RequestChallenge)) {
/*  68: 68 */        AcknowledgeChallenge ac = new AcknowledgeChallenge((RequestChallenge)pp);
/*  69: 69 */        callback(ac, pp.getRemoteIP(), pp.getRemotePort());
/*  70: 70 */        return;
/*  71:    */      }
/*  72: 72 */      String staip = pp.getUserIP();
/*  73: 73 */      String ips = staip + "@" + pp.getRemoteIP();
/*  74: 74 */      QuoteOfMessageHandler handler = (QuoteOfMessageHandler)SysConfigHelper.getBean("quoteOfMessageHandler");
/*  75: 75 */      String macs = handler.getMACs(ips);
/*  76: 76 */      if (macs == null) {
/*  77: 77 */        if ((pp instanceof RequestAuthentication)) {
/*  78: 78 */          AcknowledgeAuthentication aa = new AcknowledgeAuthentication((RequestAuthentication)pp);
/*  79: 79 */          ReplyMessageAttribute pa = new ReplyMessageAttribute("无效IP地址.");
/*  80: 80 */          aa.setErrCode(1);
/*  81: 81 */          aa.setAttrNum(1);
/*  82: 82 */          aa.addAttribute(pa);
/*  83: 83 */          aa.setErrCode(4);
/*  84: 84 */          callback(aa, pp.getRemoteIP(), pp.getRemotePort());
/*  85:    */        }
/*  86: 86 */        return;
/*  87:    */      }
/*  88: 88 */      String[] temps = macs.split("@");
/*  89: 89 */      String stamac = temps[0];
/*  90: 90 */      String apmac = temps[1];
/*  91: 91 */      HostService hostService = (HostService)SysConfigHelper.getBean("hostService");
/*  92: 92 */      HostBean host = hostService.findBySerialNumber(apmac);
/*  93: 93 */      if (host == null) {
/*  94: 94 */        System.out.println(apmac + " can not get ap by mac");
/*  95: 95 */        return;
/*  96:    */      }
/*  97: 97 */      RequestAuthentication ra = (RequestAuthentication)pp;
/*  98: 98 */      AcknowledgeAuthentication aa = new AcknowledgeAuthentication(ra);
/*  99:    */      
/* 100:100 */      UserNameAttribute una = (UserNameAttribute)ra.findAttribute(1);
/* 101:101 */      UserPasswordAttribute upa = (UserPasswordAttribute)ra.findAttribute(2);
/* 102:102 */      AttributeList attrs = new AttributeList();
/* 103:103 */      attrs.add(new Attr_UserName(una.getUserName()));
/* 104:104 */      attrs.add(new Attr_UserPassword(upa.getPassword()));
/* 105:105 */      attrs.add(new Attr_NASIdentifier("edras128.0.0.99"));
/* 106:106 */      attrs.add(new Attr_AcctSessionId(Integer.valueOf(ra.getId())));
/* 107:107 */      attrs.add(new Attr_FramedIPAddress(ra.getUserIP()));
/* 108:108 */      attrs.add(new Attr_CalledStationId(stamac));
/* 109:109 */      attrs.add(new Attr_NASPortId(apmac));
/* 110:110 */      putAttributes(pp.getId(), attrs);
/* 111:    */      
/* 112:112 */      AccessRequest ar = new AccessRequest(getRadiusClient(), attrs);
/* 113:113 */      RadiusResponse reply = getRadiusClient().authenticate(ar, null, 3);
/* 114:114 */      if ((reply instanceof AccessReject)) {
/* 115:115 */        AccessReject aj = (AccessReject)reply;
/* 116:116 */        String err = null;
/* 117:117 */        if ((aj.getAttributes() != null) && (aj.getAttributes().get("Reply-Message") != null)) {
/* 118:118 */          err = aj.getAttributes().get("Reply-Message").getValue().toString();
/* 119:    */        } else
/* 120:120 */          err = "用户名或密码错误.";
/* 121:121 */        ReplyMessageAttribute pa = new ReplyMessageAttribute(err);
/* 122:122 */        aa.setErrCode(1);
/* 123:123 */        aa.setAttrNum(1);
/* 124:124 */        aa.addAttribute(pa);
/* 125:125 */        callback(aa, pp.getRemoteIP(), pp.getRemotePort());
/* 126:126 */        return;
/* 127:    */      }
/* 128:128 */      System.out.println(stamac + "-----AccessRequest pass------");
/* 129:129 */      AccessAccept aap = (AccessAccept)reply;
/* 130:130 */      List<RadiusAttribute> ras = aap.getAttributes().getAttributeList();
/* 131:131 */      for (RadiusAttribute ratt : ras) {
/* 132:132 */        System.out.println(ratt.getAttributeName() + "-->>" + ratt.getValue().toString());
/* 133:    */      }
/* 134:134 */      long upSpeed = 0L;
/* 135:135 */      long downSpeed = 0L;
/* 136:136 */      RadiusAttribute attr1 = aap.getAttributes().get("Huawei-Input-Average-Rate");
/* 137:137 */      if (attr1 != null)
/* 138:138 */        upSpeed = Long.parseLong(attr1.getValue().toString()) / 1024L;
/* 139:139 */      RadiusAttribute attr2 = aap.getAttributes().get("Huawei-Output-Average-Rate");
/* 140:140 */      if (attr2 != null)
/* 141:141 */        downSpeed = Long.parseLong(attr2.getValue().toString()) / 1024L;
/* 142:142 */      callback(aa, pp.getRemoteIP(), pp.getRemotePort());
/* 143:143 */      handler.removeMACs(ips);
/* 144:    */      
/* 145:145 */      com.soofound.portal.bean.SurfingUser user = wds.getSurfingUser(host.getBranchId(), stamac);
/* 146:146 */      if (user != null) {
/* 147:147 */        wds.recordOffline(user);
/* 148:148 */        System.out.println("--offline online user--" + user.getUsername());
/* 149:    */      }
/* 150:150 */      user = new com.soofound.portal.bean.SurfingUser();
/* 151:151 */      user.setUsername(una.getUserName());
/* 152:152 */      user.setBranchId(host.getBranchId());
/* 153:153 */      user.setCpeId(host.getId());
/* 154:154 */      user.setTerminalMac(stamac);
/* 155:155 */      user.setTerminalIP(staip);
/* 156:156 */      user.setPacket(ra);
/* 157:157 */      user.setSsid(temps[2]);
/* 158:    */      try {
/* 159:159 */        user.setIfe(Integer.parseInt(temps[2].split("-")[1]));
/* 160:    */      }
/* 161:    */      catch (Exception localException1) {}
/* 162:162 */      user.setFlag("thirdParty");
/* 163:163 */      user.setUpSpeed(upSpeed);
/* 164:164 */      user.setDownSpeed(downSpeed);
/* 165:165 */      wds.recordOnline(user);
/* 166:    */      
/* 167:167 */      List<HostPropertyBean> hpbs = new ArrayList();
/* 168:168 */      HostPropertyBean hpb = new HostPropertyBean();
/* 169:169 */      hpb.setName("InternetGatewayDevice.DeviceInfo.add_station_mac");
/* 170:170 */      hpb.setValue(stamac);
/* 171:171 */      hpbs.add(hpb);
/* 172:172 */      hostService.putCommand(host.getId(), this.cae.getSetParameterValuesString(hpbs));
/* 173:173 */      wakeup(host);
/* 174:174 */      SurfingAccountDao dao = new SurfingAccountDao();
/* 175:175 */      SurfingAccount dto = dao.findByUsername(host.getBranchId(), user.getUsername());
/* 176:176 */      if (dto == null) {
/* 177:177 */        dto = new SurfingAccount();
/* 178:178 */        dto.setMac(stamac);
/* 179:179 */        dto.setUsername(user.getUsername());
/* 180:180 */        dto.setBranchId(user.getBranchId());
/* 181:181 */        dto.setPassword("-");
/* 182:182 */        dto.setFlag("thirdParty");
/* 183:183 */        dao.save(dto);
/* 184:    */      } else {
/* 185:185 */        dto.setPassword("-");
/* 186:186 */        dto.setFlag("thirdParty");
/* 187:187 */        dao.update(dto);
/* 188:    */      }
/* 189:    */    } catch (Exception ex) {
/* 190:190 */      ex.printStackTrace();
/* 191:    */    }
/* 192:    */  }
/* 193:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.custom.CmccPortalHandler
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */