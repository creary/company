/*   1:    */package com.soofound.portal.service;
/*   2:    */
/*   3:    */import com.soofound.cpe.bean.HostBean;
/*   4:    */import com.soofound.cpe.bean.HostPropertyBean;
/*   5:    */import com.soofound.cpe.util.CpeActionExecutor;
/*   6:    */import com.soofound.cpe.util.CpeWaker;
/*   7:    */import com.soofound.cpe.web.HostService;
/*   8:    */import com.soofound.framework.jdbc.Persistable;
/*   9:    */import com.soofound.framework.web.BaseService;
/*  10:    */import com.soofound.portal.bean.SurfingAccount;
/*  11:    */import com.soofound.portal.util.WifiDogUtils;
/*  12:    */import java.io.PrintStream;
/*  13:    */import java.util.ArrayList;
/*  14:    */import java.util.Iterator;
/*  15:    */import org.springframework.beans.factory.annotation.Qualifier;
/*  16:    */import org.springframework.jdbc.core.JdbcTemplate;
/*  17:    */import org.springframework.stereotype.Service;
/*  18:    */
/*  19:    */@Service
/*  20:    */public final class SurfingPolicyService extends BaseService<com.soofound.portal.dao.SurfingPolicyDao>
/*  21:    */{
/*  22:    */  @org.springframework.beans.factory.annotation.Autowired
/*  23:    */  private WifiDogService wds;
/*  24:    */  @org.springframework.beans.factory.annotation.Autowired
/*  25:    */  private SurfingAccountService sas;
/*  26:    */  @org.springframework.beans.factory.annotation.Autowired
/*  27:    */  @Qualifier("defaultJdbcTemplate")
/*  28:    */  private JdbcTemplate jdbcTemplate;
/*  29:    */  @org.springframework.beans.factory.annotation.Autowired
/*  30:    */  private HostService hostService;
/*  31:    */  @org.springframework.beans.factory.annotation.Autowired
/*  32:    */  private com.soofound.cpe.dao.BssidDao smdao;
/*  33:    */  
/*  34:    */  public java.util.List<com.soofound.portal.bean.SurfingPolicyDto> findByBranch(String branchId)
/*  35:    */  {
/*  36: 36 */    return ((com.soofound.portal.dao.SurfingPolicyDao)this.dao).findByBranch(branchId);
/*  37:    */  }
/*  38:    */  
/*  39:    */  public com.soofound.portal.bean.SurfingPolicyDto getPolicyByName(String branchId, String name) {
/*  40: 40 */    return ((com.soofound.portal.dao.SurfingPolicyDao)this.dao).getPolicyByName(branchId, name);
/*  41:    */  }
/*  42:    */  
/*  43:    */  public com.soofound.portal.bean.SurfingPolicyDto getPolicy(String ssid) {
/*  44: 44 */    com.soofound.portal.bean.SurfingPolicyDto spd = null;
/*  45:    */    try {
/*  46: 46 */      String[] temp = ssid.split("-");
/*  47: 47 */      String ife = WifiDogUtils.getInterface(temp[1]);
/*  48: 48 */      com.soofound.cpe.bean.BssidDto smd = this.smdao.findByKey(temp[0], ife);
/*  49: 49 */      if (smd != null)
/*  50: 50 */        spd = ((com.soofound.portal.dao.SurfingPolicyDao)this.dao).findByID(smd.getPolicyId());
/*  51:    */    } catch (Exception ex) {
/*  52: 52 */      ex.printStackTrace();
/*  53:    */    }
/*  54: 54 */    if (spd == null) {
/*  55: 55 */      System.out.println("Error#Can not find policy of SSID=" + ssid);
/*  56: 56 */      spd = com.soofound.portal.bean.SurfingPolicyDto.bornDefaultPolicy();
/*  57:    */    }
/*  58: 58 */    return spd;
/*  59:    */  }
/*  60:    */  
/*  61:    */  public com.soofound.portal.bean.SurfingPolicyDto getPolicyBySSID(String apId, String ife) {
/*  62: 62 */    return ((com.soofound.portal.dao.SurfingPolicyDao)this.dao).getPolicyBySSID(apId, ife);
/*  63:    */  }
/*  64:    */  
/*  65:    */  public void simpleSaveOrUpdate(com.soofound.portal.bean.SurfingPolicyDto dto) {
/*  66: 66 */    ((com.soofound.portal.dao.SurfingPolicyDao)this.dao).simpleSaveOrUpdate(dto);
/*  67:    */  }
/*  68:    */  
/*  69:    */  public com.soofound.portal.bean.SurfingPolicyDto getDefaultPolicy(String branchId) {
/*  70: 70 */    com.soofound.portal.bean.SurfingPolicyDto dto = ((com.soofound.portal.dao.SurfingPolicyDao)this.dao).getDefaultPolicy(branchId);
/*  71: 71 */    if (dto == null) {
/*  72: 72 */      java.util.List<com.soofound.portal.bean.SurfingPolicyDto> spdtos = ((com.soofound.portal.dao.SurfingPolicyDao)this.dao).findByBranch(branchId);
/*  73: 73 */      if (spdtos.size() > 0) {
/*  74: 74 */        dto = (com.soofound.portal.bean.SurfingPolicyDto)spdtos.get(0);
/*  75:    */      } else
/*  76: 76 */        dto = com.soofound.portal.bean.SurfingPolicyDto.bornDefaultPolicy();
/*  77:    */    }
/*  78: 78 */    return dto;
/*  79:    */  }
/*  80:    */  
/*  81:    */  public int update(Persistable dto)
/*  82:    */  {
/*  83: 83 */    com.soofound.portal.bean.SurfingPolicyDto spd = (com.soofound.portal.bean.SurfingPolicyDto)dto;
/*  84: 84 */    int result = ((com.soofound.portal.dao.SurfingPolicyDao)this.dao).update(spd);
/*  85: 85 */    com.soofound.cpe.dao.BssidDao bdao = new com.soofound.cpe.dao.BssidDao();
/*  86: 86 */    java.util.List<com.soofound.cpe.bean.BssidDto> bds = bdao.findByPolicy(spd.getId());
/*  87: 87 */    Iterator localIterator2; for (Iterator localIterator1 = bds.iterator(); localIterator1.hasNext(); 
/*  88:    */        
/*  89: 89 */        localIterator2.hasNext())
/*  90:    */    {
/*  91: 87 */      com.soofound.cpe.bean.BssidDto bd = (com.soofound.cpe.bean.BssidDto)localIterator1.next();
/*  92: 88 */      java.util.List<com.soofound.portal.bean.SurfingUser> sus = this.wds.getSurfingUser3(bd.getApId(), bd.getIfe());
/*  93: 89 */      localIterator2 = sus.iterator();continue;com.soofound.portal.bean.SurfingUser su = (com.soofound.portal.bean.SurfingUser)localIterator2.next();
/*  94: 90 */      SurfingAccount sa = this.sas.findByUsername(spd.getBranchId(), su.getUsername());
/*  95: 91 */      if ((sa != null) && (sa.getUpSpeed() == 0))
/*  96: 92 */        su.setUpSpeed(spd.getUpSpeed());
/*  97: 93 */      if ((sa != null) && (sa.getDownSpeed() == 0)) {
/*  98: 94 */        su.setDownSpeed(spd.getDownSpeed());
/*  99:    */      }
/* 100:    */    }
/* 101: 97 */    if (spd.getWechatShare() == 1) {
/* 102: 98 */      openWechat(spd.getId(), 1);
/* 103: 99 */    } else if (spd.getWechatAuth() == 1)
/* 104:100 */      openWechat(spd.getId(), 2);
/* 105:101 */    return result;
/* 106:    */  }
/* 107:    */  
/* 108:    */  private void openWechat(int policyId, int wxpass) {
/* 109:105 */    CpeWaker cw = new CpeWaker();
/* 110:106 */    CpeActionExecutor cae = new CpeActionExecutor();
/* 111:107 */    java.util.List<com.soofound.cpe.bean.BssidDto> bds = this.smdao.findByPolicy(policyId);
/* 112:108 */    java.util.List<Integer> hids = new ArrayList();
/* 113:109 */    for (com.soofound.cpe.bean.BssidDto bd : bds) {
/* 114:110 */      if (!hids.contains(Integer.valueOf(bd.getApId())))
/* 115:    */      {
/* 117:113 */        hids.add(Integer.valueOf(bd.getApId()));
/* 118:114 */        String hid = bd.getApId();
/* 119:115 */        HostBean hb = (HostBean)this.hostService.findByID(hid);
/* 120:116 */        if ((hb != null) && (hb.getOnline() == 1))
/* 121:    */        {
/* 123:119 */          java.util.List<HostPropertyBean> props = new ArrayList();
/* 124:120 */          HostPropertyBean prop = new HostPropertyBean();
/* 125:121 */          prop.setName("InternetGatewayDevice.DeviceInfo.weixing_pass");
/* 126:122 */          prop.setValue(wxpass);
/* 127:123 */          props.add(prop);
/* 128:    */          
/* 129:125 */          this.hostService.putCommand(bd.getApId(), cae.getSetParameterValuesString(props));
/* 130:126 */          this.hostService.putCommand(bd.getApId(), cae.getApplyString());
/* 131:127 */          cw.wakeup(String.valueOf(bd.getApId()));
/* 132:    */          
/* 133:129 */          String sql = "update cpe_host_property set value=" + wxpass + " where pid=15 and host_id=" + hb.getId();
/* 134:130 */          this.jdbcTemplate.update(sql);
/* 135:    */        }
/* 136:    */      }
/* 137:    */    }
/* 138:    */  }
/* 139:    */  
/* 148:    */  @org.springframework.beans.factory.annotation.Autowired
/* 149:    */  protected void setDao(com.soofound.portal.dao.SurfingPolicyDao dao)
/* 150:    */  {
/* 151:147 */    this.dao = dao;
/* 152:    */  }
/* 153:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.service.SurfingPolicyService
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */