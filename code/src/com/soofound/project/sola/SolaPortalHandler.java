/*  1:   */package com.soofound.project.sola;
/*  2:   */
/*  3:   */import com.soofound.cpe.bean.HostBean;
/*  4:   */import com.soofound.cpe.bean.HostPropertyBean;
/*  5:   */import com.soofound.cpe.util.CpeActionExecutor;
/*  6:   */import com.soofound.cpe.web.HostService;
/*  7:   */import com.soofound.framework.util.SysConfigHelper;
/*  8:   */import com.soofound.portal.custom.ThirdPartyHandler;
/*  9:   */import com.soofound.portal.dao.SurfingAccountDao;
/* 10:   */import com.soofound.portal.service.QuoteOfMessageHandler;
/* 11:   */import com.soofound.portal.service.WifiDogService;
/* 12:   */import com.soofound.protocol.cmcc.PortalPacket;
/* 13:   */import com.soofound.protocol.cmcc.RequestChallenge;
/* 14:   */import java.io.PrintStream;
/* 15:   */import java.util.ArrayList;
/* 16:   */import java.util.List;
/* 17:   */
/* 18:   */public class SolaPortalHandler extends ThirdPartyHandler
/* 19:   */{
/* 20:   */  public void doHandle(PortalPacket pp)
/* 21:   */  {
/* 22:   */    try
/* 23:   */    {
/* 24:24 */      if (!(pp instanceof RequestChallenge)) {
/* 25:25 */        return;
/* 26:   */      }
/* 27:27 */      QuoteOfMessageHandler handler = (QuoteOfMessageHandler)SysConfigHelper.getBean("quoteOfMessageHandler");
/* 28:28 */      RequestChallenge rc = (RequestChallenge)pp;
/* 29:29 */      String ips = rc.getUserIP() + "@" + rc.getRemoteIP();
/* 30:30 */      String macs = handler.removeMACs(ips);
/* 31:31 */      if (macs == null) {
/* 32:32 */        System.out.println(ips + " find no macs...");
/* 33:33 */        return;
/* 34:   */      }
/* 35:35 */      String[] temps = macs.split("@");
/* 36:36 */      String stamac = temps[0];
/* 37:37 */      String apmac = temps[1];
/* 38:38 */      if (stamac == null) {
/* 39:39 */        return;
/* 40:   */      }
/* 41:41 */      HostService hostService = (HostService)SysConfigHelper.getBean("hostService");
/* 42:42 */      HostBean host = hostService.findBySerialNumber(apmac);
/* 43:43 */      if (host == null) { return;
/* 44:   */      }
/* 45:45 */      WifiDogService wds = (WifiDogService)SysConfigHelper.getBean("wifiDogService");
/* 46:46 */      com.soofound.portal.bean.SurfingUser user = wds.getSurfingUser(host.getBranchId(), stamac);
/* 47:47 */      if (user == null) {
/* 48:48 */        user = new com.soofound.portal.bean.SurfingUser();
/* 49:49 */        user.setUsername(stamac.replace(":", ""));
/* 50:50 */        user.setBranchId(host.getBranchId());
/* 51:51 */        user.setCpeId(host.getId());
/* 52:52 */        user.setTerminalMac(stamac);
/* 53:53 */        user.setTerminalIP(rc.getUserIP());
/* 54:54 */        user.setSsid(temps[2]);
/* 55:55 */        user.setFlag("thirdParty");
/* 56:56 */        wds.recordOnline(user);
/* 57:   */        
/* 58:58 */        List<HostPropertyBean> hpbs = new ArrayList();
/* 59:59 */        HostPropertyBean hpb = new HostPropertyBean();
/* 60:60 */        hpb.setName("InternetGatewayDevice.DeviceInfo.add_station_mac");
/* 61:61 */        hpb.setValue(stamac);
/* 62:62 */        hpbs.add(hpb);
/* 63:63 */        hostService.putCommand(host.getId(), this.cae.getSetParameterValuesString(hpbs));
/* 64:64 */        wakeup(host);
/* 65:   */        
/* 66:66 */        SurfingAccountDao dao = new SurfingAccountDao();
/* 67:67 */        com.soofound.portal.bean.SurfingAccount dto = dao.findByUsername(host.getBranchId(), user.getUsername());
/* 68:68 */        if (dto == null) {
/* 69:69 */          dto = new com.soofound.portal.bean.SurfingAccount();
/* 70:70 */          dto.setMac(stamac);
/* 71:71 */          dto.setTimes(1);
/* 72:72 */          dto.setOnline(1);
/* 73:73 */          dto.setUsername(user.getUsername());
/* 74:74 */          dto.setBranchId(user.getBranchId());
/* 75:75 */          dto.setPassword("-");
/* 76:76 */          dto.setFlag("thirdParty");
/* 77:77 */          dao.save(dto);
/* 78:   */        }
/* 79:   */      }
/* 80:   */    } catch (Exception ex) {
/* 81:81 */      ex.printStackTrace();
/* 82:   */    }
/* 83:   */  }
/* 84:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.project.sola.SolaPortalHandler
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */