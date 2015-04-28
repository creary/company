/*   1:    */package com.soofound.cpe.util;
/*   2:    */
/*   3:    */import com.soofound.cpe.bean.BssidDto;
/*   4:    */import com.soofound.cpe.bean.HostBean;
/*   5:    */import com.soofound.cpe.bean.HostPropertyBean;
/*   6:    */import com.soofound.cpe.bean.PropertyBean;
/*   7:    */import com.soofound.cpe.dao.BssidDao;
/*   8:    */import com.soofound.cpe.dao.DeviceLogDao;
/*   9:    */import com.soofound.cpe.dao.HostPropertyDao;
/*  10:    */import com.soofound.cpe.dao.PropertyDao;
/*  11:    */import com.soofound.cpe.web.HostService;
/*  12:    */import com.soofound.framework.util.CommonUtil;
/*  13:    */import com.soofound.framework.util.SysConfigHelper;
/*  14:    */import com.soofound.portal.bean.PortalInstanceDto;
/*  15:    */import com.soofound.portal.bean.SurfingPolicyDto;
/*  16:    */import com.soofound.portal.service.PortalInstanceService;
/*  17:    */import com.soofound.portal.service.SurfingPolicyService;
/*  18:    */import com.soofound.protocol.cwmp.GetParameterValuesResponse;
/*  19:    */import com.soofound.protocol.cwmp.ParameterValueStruct;
/*  20:    */import java.io.PrintStream;
/*  21:    */import java.util.ArrayList;
/*  22:    */import java.util.Hashtable;
/*  23:    */import java.util.List;
/*  24:    */import org.springframework.jdbc.core.JdbcTemplate;
/*  25:    */import org.springframework.util.StringUtils;
/*  26:    */
/*  37:    */public class UpdateHostTask
/*  38:    */  implements Runnable
/*  39:    */{
/*  40: 40 */  private static String[] SSIDS = { "InternetGatewayDevice.DeviceInfo.wireless_ssid", "InternetGatewayDevice.DeviceInfo.wireless_ssid2", "InternetGatewayDevice.DeviceInfo.wireless_ssid_5G", "InternetGatewayDevice.DeviceInfo.wireless_ssid2_5G" };
/*  41:    */  private HostBean host;
/*  42:    */  private GetParameterValuesResponse gpvr;
/*  43:    */  
/*  44:    */  public UpdateHostTask(HostBean host, GetParameterValuesResponse gpvr) {
/*  45: 45 */    this.host = host;
/*  46: 46 */    this.gpvr = gpvr;
/*  47:    */  }
/*  48:    */  
/*  49:    */  public void run() {
/*  50:    */    try {
/*  51: 51 */      updateHost();
/*  52:    */    } catch (Exception e) {
/*  53: 53 */      System.out.println(this.host.getName() + "#updateHost.error");
/*  54:    */    }
/*  55:    */  }
/*  56:    */  
/*  57:    */  private void updateHost() throws Exception {
/*  58: 58 */    List<String> sqls = new ArrayList();
/*  59: 59 */    HostService service = (HostService)SysConfigHelper.getBean("hostService");
/*  60: 60 */    HostBean host = service.findBySerialNumber(this.gpvr.getParamValue("InternetGatewayDevice.DeviceInfo.SerialNumber"));
/*  61: 61 */    String model = host.getHardwareVersion();
/*  62: 62 */    String mode = this.gpvr.getParamValue("InternetGatewayDevice.DeviceInfo.work_mode");
/*  63: 63 */    if (mode == null) mode = "AP";
/*  64: 64 */    int portalId = 0;
/*  65: 65 */    int policyId = 0;
/*  66: 66 */    BssidDao bdao = new BssidDao();
/*  67: 67 */    List<BssidDto> bds = bdao.findByAP(host.getId());
/*  68: 68 */    if ((host.isFresh()) || (bds.size() < 2)) {
/*  69: 69 */      PortalInstanceService pis = (PortalInstanceService)SysConfigHelper.getBean("portalInstanceService");
/*  70: 70 */      PortalInstanceDto pidto = pis.getDefaultPortal(host.getBranchId());
/*  71: 71 */      SurfingPolicyService sps = (SurfingPolicyService)SysConfigHelper.getBean("surfingPolicyService");
/*  72: 72 */      SurfingPolicyDto spdto = sps.getDefaultPolicy(host.getBranchId());
/*  73: 73 */      portalId = pidto.getId();
/*  74: 74 */      policyId = spdto.getId();
/*  75: 75 */      int len = model.endsWith("-A") ? 4 : 2;
/*  76: 76 */      for (int i = 0; i < len; i++) {
/*  77: 77 */        String ssidValue = this.gpvr.getParamValue(SSIDS[i]);
/*  78: 78 */        if (StringUtils.isEmpty(ssidValue)) {
/*  79: 79 */          ssidValue = "N/A";
/*  80:    */        }
/*  81: 81 */        StringBuilder sqlText = new StringBuilder(100);
/*  82: 82 */        sqlText.append("insert into cpe_ssid(ap_id,ife,portal_id,policy_id,name,enable)values(").append(host.getId());
/*  83: 83 */        sqlText.append(",").append(i).append(",").append(portalId).append(",").append(policyId).append(",'").append(ssidValue);
/*  84: 84 */        sqlText.append("',").append("N/A".equals(ssidValue) ? 0 : 1).append(")");
/*  85: 85 */        sqls.add(sqlText.toString());
/*  86:    */      }
/*  87:    */    }
/*  88: 88 */    BssidDto bdto = bdao.findByKey(host.getId(), "255");
/*  89: 89 */    if (bdto == null) {
/*  90: 90 */      StringBuilder sqlText = new StringBuilder(100);
/*  91: 91 */      sqlText.append("insert into cpe_ssid(ap_id,ife,portal_id,policy_id,name,enable)values(").append(host.getId());
/*  92: 92 */      sqlText.append(",255,").append(portalId).append(",").append(policyId).append(",'LAN',").append(model.endsWith("-W") ? 1 : 0).append(")");
/*  93: 93 */      sqls.add(sqlText.toString());
/*  94:    */    }
/*  95: 95 */    PropertyDao pdao = new PropertyDao();
/*  96: 96 */    List<PropertyBean> pbeans = pdao.findAll();
/*  97: 97 */    HostPropertyDao hpdao = new HostPropertyDao();
/*  98: 98 */    List<HostPropertyBean> hpbs = hpdao.findByHost(host.getId());
/*  99: 99 */    Hashtable<String, ParameterValueStruct> pis = this.gpvr.getValues();
/* 100:100 */    JdbcTemplate jdbc = (JdbcTemplate)SysConfigHelper.getBean("defaultJdbcTemplate");
/* 101:101 */    StringBuilder sqlstr; for (PropertyBean pb : pbeans) {
/* 102:102 */      ParameterValueStruct pvs = (ParameterValueStruct)pis.get(pb.getName());
/* 103:103 */      if ((pvs != null) && (pvs.getValue() != null))
/* 104:    */      {
/* 105:105 */        if ((host.isFresh()) || (pb.getTag() < 3))
/* 106:    */        {
/* 110:110 */          if (pvs.getValue().indexOf("'") != -1) {
/* 111:111 */            System.out.println(pb.getName() + "#error value=" + pvs.getValue());
/* 112:    */          }
/* 113:    */          else {
/* 114:114 */            String value = pvs.getValue();
/* 115:115 */            if ((CpeUtils.isThreshold(pb.getEnName())) && (value != null) && (value.startsWith("-")))
/* 116:116 */              value = value.substring(1);
/* 117:117 */            sqlstr = new StringBuilder(200);
/* 118:118 */            sqlstr.append("update cpe_host_property set value ='").append(value);
/* 119:119 */            sqlstr.append("' where pid=").append(pb.getId()).append(" and host_id=").append(this.host.getId());
/* 120:120 */            int row = jdbc.update(sqlstr.toString());
/* 121:121 */            if (row == 0) {
/* 122:122 */              sqlstr.setLength(0);
/* 123:123 */              sqlstr.append("insert into cpe_host_property(host_id,pid,value)values(").append(this.host.getId());
/* 124:124 */              sqlstr.append(",").append(pb.getId()).append(",'").append(value).append("')");
/* 125:125 */              jdbc.update(sqlstr.toString());
/* 126:    */            }
/* 127:    */          } } } }
/* 128:128 */    PropertyDao dao = new PropertyDao();
/* 129:129 */    Object cfgs = dao.findProperties(host.getHardwareVersion(), 3);
/* 130:130 */    List<HostPropertyBean> props = new ArrayList();
/* 131:131 */    for (PropertyBean cfg : (List)cfgs) {
/* 132:132 */      HostPropertyBean hpb = getHostProperty(hpbs, cfg.getName());
/* 133:133 */      ParameterValueStruct pvs = this.gpvr.getParam(cfg.getName());
/* 134:134 */      if ((pvs != null) && (hpb != null))
/* 135:    */      {
/* 137:137 */        String val = null;
/* 138:138 */        if ((CpeUtils.isThreshold(cfg.getName())) && (StringUtils.hasText(pvs.getValue()))) {
/* 139:139 */          val = pvs.getValue().substring(1);
/* 140:    */        } else
/* 141:141 */          val = pvs.getValue();
/* 142:142 */        if (!val.equals(hpb.getValue()))
/* 143:143 */          props.add(hpb);
/* 144:    */      } }
/* 145:145 */    StringBuilder sqlstr = new StringBuilder(200);
/* 146:146 */    sqlstr.append("update cpe_host set product_class='").append(this.gpvr.getParamValue("InternetGatewayDevice.DeviceInfo.ProductClass"));
/* 147:147 */    sqlstr.append("',channel='").append(this.gpvr.getParamValue("InternetGatewayDevice.DeviceInfo.wireless_channel"));
/* 148:148 */    sqlstr.append("',software_version='").append(this.gpvr.getParamValue("InternetGatewayDevice.DeviceInfo.SoftwareVersion"));
/* 149:149 */    sqlstr.append("',mode='").append(mode);
/* 150:150 */    if (host.isFresh())
/* 151:151 */      sqlstr.append("',ssid='").append(CpeUtils.getSsidText(this.gpvr));
/* 152:152 */    sqlstr.append("',online=1 where id=").append(this.host.getId());
/* 153:153 */    sqls.add(sqlstr.toString());
/* 154:    */    
/* 155:155 */    SoofacACS acs = (SoofacACS)SysConfigHelper.getBean("soofacACS");
/* 156:156 */    if (acs.getCustomSQLs().size() > 0)
/* 157:157 */      sqls.addAll(acs.getCustomSQLs());
/* 158:158 */    jdbc.batchUpdate(CommonUtil.toArray(sqls));
/* 159:159 */    if (props.size() > 0) {
/* 160:160 */      CpeActionExecutor cae = new CpeActionExecutor();
/* 161:161 */      String cmd = cae.getSetParameterValuesString(props);
/* 162:162 */      service.putCommand(host.getId(), cmd);
/* 163:    */      try {
/* 164:164 */        Thread.sleep(10L);
/* 165:    */      }
/* 166:    */      catch (Exception localException) {}
/* 167:167 */      service.putCommand(host.getId(), cae.getApplyString());
/* 168:168 */      System.out.println("下发配置\n" + cmd);
/* 169:169 */      CpeWaker cw = new CpeWaker();
/* 170:170 */      cw.wakeup(String.valueOf(host.getId()));
/* 171:    */      
/* 172:172 */      DeviceLogDao dld = new DeviceLogDao();
/* 173:173 */      dld.writeLog(host.getId(), "系统", "下发配置");
/* 174:    */    }
/* 175:175 */    System.out.println(this.host.getName() + "#updateHost");
/* 176:    */  }
/* 177:    */  
/* 178:    */  private HostPropertyBean getHostProperty(List<HostPropertyBean> hpbs, String name) {
/* 179:179 */    for (HostPropertyBean hpb : hpbs) {
/* 180:180 */      if (hpb.getName().equals(name))
/* 181:181 */        return hpb;
/* 182:    */    }
/* 183:183 */    return null;
/* 184:    */  }
/* 185:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.cpe.util.UpdateHostTask
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */