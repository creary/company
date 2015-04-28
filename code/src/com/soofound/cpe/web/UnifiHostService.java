/*   1:    */package com.soofound.cpe.web;
/*   2:    */
/*   3:    */import com.soofound.cpe.util.SoofacACS;
/*   4:    */import com.soofound.framework.util.CommonUtil;
/*   5:    */import com.soofound.framework.util.DateUtil;
/*   6:    */import com.soofound.framework.web.BaseService;
/*   7:    */import java.util.ArrayList;
/*   8:    */import java.util.List;
/*   9:    */import org.apache.commons.lang.StringUtils;
/*  10:    */import org.springframework.beans.factory.annotation.Autowired;
/*  11:    */import org.springframework.beans.factory.annotation.Qualifier;
/*  12:    */import org.springframework.stereotype.Service;
/*  13:    */
/*  14:    */@Service
/*  15:    */public final class UnifiHostService extends BaseService<com.soofound.cpe.dao.UnifiHostDao>
/*  16:    */{
/*  17:    */  @Autowired
/*  18:    */  @Qualifier("defaultJdbcTemplate")
/*  19:    */  private org.springframework.jdbc.core.JdbcTemplate jdbcTemplate;
/*  20:    */  @Autowired
/*  21:    */  private SoofacACS acs;
/*  22:    */  
/*  23:    */  public com.soofound.cpe.bean.UnifiHostDto findBySite(String site)
/*  24:    */  {
/*  25: 25 */    return ((com.soofound.cpe.dao.UnifiHostDao)this.dao).findBySite(site);
/*  26:    */  }
/*  27:    */  
/*  28:    */  public com.soofound.cpe.bean.UnifiHostDto findByHostId(String hostId) {
/*  29: 29 */    return ((com.soofound.cpe.dao.UnifiHostDao)this.dao).findByHostId(hostId);
/*  30:    */  }
/*  31:    */  
/*  32:    */  public int save(com.soofound.framework.jdbc.Persistable dto)
/*  33:    */  {
/*  34: 34 */    com.soofound.cpe.bean.UnifiHostDto uhd = (com.soofound.cpe.bean.UnifiHostDto)dto;
/*  35: 35 */    if (StringUtils.isEmpty(uhd.getSite())) {
/*  36: 36 */      uhd.setSite(uhd.getAcip());
/*  37:    */    }
/*  38: 38 */    com.soofound.cpe.bean.HostBean host = new com.soofound.cpe.bean.HostBean();
/*  39: 39 */    host.setId(this.acs.getHostID());
/*  40: 40 */    host.setSerialNumber("unifi_" + host.getId());
/*  41: 41 */    host.setName(uhd.getSite());
/*  42: 42 */    host.setBranchId("0");
/*  43: 43 */    host.setIpAddress("");
/*  44: 44 */    host.setChannel("");
/*  45: 45 */    host.setTrace("");
/*  46: 46 */    host.setOnline(1);
/*  47: 47 */    host.setUpTime(DateUtil.getCurrentDateTime());
/*  48: 48 */    host.setLastTime(DateUtil.getCurrentDateTime());
/*  49: 49 */    host.setHardwareVersion("Ubnt");
/*  50: 50 */    host.setProductClass("Ubnt");
/*  51: 51 */    host.setMode("Ubnt");
/*  52: 52 */    host.setSoftwareVersion("Ubnt");
/*  53: 53 */    host.setSsid("Unifi");
/*  54: 54 */    host.setName("Unifi-AP");
/*  55: 55 */    host.setStun("unifi");
/*  56: 56 */    com.soofound.cpe.dao.HostDao hdao = new com.soofound.cpe.dao.HostDao();
/*  57: 57 */    hdao.save(host);
/*  58:    */    
/*  59: 59 */    uhd.setHostId(host.getId());
/*  60:    */    
/*  61: 61 */    StringBuilder sqlText = new StringBuilder(200);
/*  62: 62 */    sqlText.append("insert into cpe_ssid(ap_id,ife,policy_id,portal_id,enable,name)values(").append(host.getId());
/*  63: 63 */    sqlText.append(",0,0,0,1,'").append(host.getSsid()).append("')");
/*  64: 64 */    this.jdbcTemplate.update(sqlText.toString());
/*  65:    */    
/*  66: 66 */    return ((com.soofound.cpe.dao.UnifiHostDao)this.dao).save(uhd);
/*  67:    */  }
/*  68:    */  
/*  69:    */  public int update(com.soofound.framework.jdbc.Persistable dto)
/*  70:    */  {
/*  71: 71 */    com.soofound.cpe.bean.UnifiHostDto uhd = (com.soofound.cpe.bean.UnifiHostDto)dto;
/*  72: 72 */    if (StringUtils.isEmpty(uhd.getSite())) {
/*  73: 73 */      uhd.setSite(uhd.getAcip());
/*  74:    */    }
/*  75: 75 */    StringBuilder sqlText = new StringBuilder(200);
/*  76: 76 */    sqlText.append("update cpe_host set name='").append(uhd.getSite()).append("' where id=").append(uhd.getHostId());
/*  77: 77 */    this.jdbcTemplate.update(sqlText.toString());
/*  78:    */    
/*  79: 79 */    return ((com.soofound.cpe.dao.UnifiHostDao)this.dao).update(uhd);
/*  80:    */  }
/*  81:    */  
/*  82:    */  public void active(String[] ids, String branchId) {
/*  83: 83 */    List<String> sqls = new ArrayList();
/*  84: 84 */    for (String id : ids) {
/*  85: 85 */      com.soofound.cpe.bean.UnifiHostDto dto = (com.soofound.cpe.bean.UnifiHostDto)((com.soofound.cpe.dao.UnifiHostDao)this.dao).findByID(id);
/*  86: 86 */      if (dto != null) {
/*  87: 87 */        StringBuilder sqlText1 = new StringBuilder(200);
/*  88: 88 */        sqlText1.append("update cpe_unifi set branch_id='").append(branchId).append("' where id=").append(dto.getId());
/*  89: 89 */        sqls.add(sqlText1.toString());
/*  90:    */        
/*  91: 91 */        StringBuilder sqlText2 = new StringBuilder(200);
/*  92: 92 */        sqlText2.append("update cpe_host set branch_id='").append(branchId).append("' where id=").append(dto.getHostId());
/*  93: 93 */        sqls.add(sqlText2.toString());
/*  94:    */      }
/*  95:    */    }
/*  96: 96 */    this.jdbcTemplate.batchUpdate(CommonUtil.toArray(sqls));
/*  97:    */  }
/*  98:    */  
/* 103:    */  @Autowired
/* 104:    */  protected void setDao(com.soofound.cpe.dao.UnifiHostDao dao)
/* 105:    */  {
/* 106:106 */    this.dao = dao;
/* 107:    */  }
/* 108:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.cpe.web.UnifiHostService
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */