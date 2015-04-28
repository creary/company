/*   1:    */package com.soofound.portal.service;
/*   2:    */
/*   3:    */import com.soofound.framework.web.BaseService;
/*   4:    */import java.util.ArrayList;
/*   5:    */import java.util.concurrent.ConcurrentHashMap;
/*   6:    */import org.springframework.beans.factory.annotation.Qualifier;
/*   7:    */import org.springframework.stereotype.Service;
/*   8:    */
/*   9:    */@Service
/*  10:    */public final class BlackWhiteService extends BaseService<com.soofound.portal.dao.BlackWhiteDao>
/*  11:    */{
/*  12:    */  private final java.util.Map<String, com.soofound.portal.bean.BlackWhiteDto> bwds;
/*  13:    */  @org.springframework.beans.factory.annotation.Autowired
/*  14:    */  private com.soofound.cpe.util.SoofacACS acs;
/*  15:    */  @org.springframework.beans.factory.annotation.Autowired
/*  16:    */  @Qualifier("acsideJdbcTemplate")
/*  17:    */  private org.springframework.jdbc.core.JdbcTemplate acsideJdbc;
/*  18:    */  @org.springframework.beans.factory.annotation.Autowired
/*  19:    */  @Qualifier("defaultJdbcTemplate")
/*  20:    */  private org.springframework.jdbc.core.JdbcTemplate jdbcTemplate;
/*  21:    */  
/*  22:    */  public BlackWhiteService()
/*  23:    */  {
/*  24: 24 */    this.bwds = new ConcurrentHashMap();
/*  25:    */  }
/*  26:    */  
/*  27:    */  public int save(com.soofound.framework.jdbc.Persistable dto)
/*  28:    */  {
/*  29: 29 */    com.soofound.portal.bean.BlackWhiteDto bwd = (com.soofound.portal.bean.BlackWhiteDto)dto;
/*  30: 30 */    bwd.setMac(bwd.getMac().toUpperCase());
/*  31:    */    try {
/*  32: 32 */      StringBuilder sqlText = new StringBuilder(100);
/*  33: 33 */      sqlText.append("insert into employee_mac(mac,employee,realm)values('").append(bwd.getMac()).append("','");
/*  34: 34 */      sqlText.append(bwd.getReason()).append("','").append(this.acs.getRealm()).append("')");
/*  35: 35 */      this.acsideJdbc.update(sqlText.toString());
/*  36:    */    } catch (Exception ex) {
/*  37: 37 */      ex.printStackTrace();
/*  38:    */    }
/*  39: 39 */    int cc = ((com.soofound.portal.dao.BlackWhiteDao)this.dao).save(bwd);
/*  40: 40 */    reload();
/*  41: 41 */    return cc;
/*  42:    */  }
/*  43:    */  
/*  44:    */  public int update(com.soofound.framework.jdbc.Persistable dto)
/*  45:    */  {
/*  46: 46 */    com.soofound.portal.bean.BlackWhiteDto bwd = (com.soofound.portal.bean.BlackWhiteDto)dto;
/*  47: 47 */    bwd.setMac(bwd.getMac().toUpperCase());
/*  48:    */    try {
/*  49: 49 */      com.soofound.portal.bean.BlackWhiteDto olddto = (com.soofound.portal.bean.BlackWhiteDto)((com.soofound.portal.dao.BlackWhiteDao)this.dao).findByID(bwd.getId());
/*  50: 50 */      StringBuilder sqlText = new StringBuilder(100);
/*  51: 51 */      sqlText.append("update employee_mac set employee='").append(bwd.getReason()).append("',realm='").append(this.acs.getRealm());
/*  52: 52 */      sqlText.append("',mac='").append(bwd.getMac()).append("' where mac='").append(olddto.getMac()).append("'");
/*  53: 53 */      this.acsideJdbc.update(sqlText.toString());
/*  54:    */    } catch (Exception ex) {
/*  55: 55 */      ex.printStackTrace();
/*  56:    */    }
/*  57: 57 */    int cc = ((com.soofound.portal.dao.BlackWhiteDao)this.dao).update(bwd);
/*  58: 58 */    reload();
/*  59: 59 */    return cc;
/*  60:    */  }
/*  61:    */  
/*  62:    */  public int delete(String[] ids)
/*  63:    */  {
/*  64: 64 */    java.util.List<com.soofound.portal.bean.BlackWhiteDto> dtos = ((com.soofound.portal.dao.BlackWhiteDao)this.dao).findByIDs(ids);
/*  65:    */    try {
/*  66: 66 */      for (com.soofound.portal.bean.BlackWhiteDto dto : dtos) {
/*  67: 67 */        String sql = "delete from employee_mac where mac='" + dto.getMac() + "'";
/*  68: 68 */        this.acsideJdbc.update(sql);
/*  69:    */      }
/*  70:    */    } catch (Exception ex) {
/*  71: 71 */      ex.printStackTrace();
/*  72:    */    }
/*  73: 73 */    int cc = ((com.soofound.portal.dao.BlackWhiteDao)this.dao).delete(ids);
/*  74: 74 */    reload();
/*  75: 75 */    return cc;
/*  76:    */  }
/*  77:    */  
/*  78:    */  public com.soofound.portal.bean.BlackWhiteDto findByMac(String branchId, String mac, int bw) {
/*  79: 79 */    return ((com.soofound.portal.dao.BlackWhiteDao)this.dao).findByMac(branchId, mac, bw);
/*  80:    */  }
/*  81:    */  
/*  82:    */  public int setBlackWhites(String[] macs, String branchId, int bw) {
/*  83: 83 */    int id = ((com.soofound.portal.dao.BlackWhiteDao)this.dao).getNextID();
/*  84: 84 */    java.util.List<String> sqls = new ArrayList();
/*  85: 85 */    for (int i = 0; i < macs.length; i++) {
/*  86: 86 */      StringBuilder sqlText1 = new StringBuilder(100);
/*  87: 87 */      sqlText1.append("delete from surfing_black_white where mac='").append(macs[i]).append("' and branch_id='");
/*  88: 88 */      sqlText1.append(branchId).append("'");
/*  89: 89 */      sqls.add(sqlText1.toString());
/*  90:    */      
/*  91: 91 */      StringBuilder sqlText2 = new StringBuilder(100);
/*  92: 92 */      sqlText2.append("insert into surfing_black_white(id,mac,bw,branch_id)values(").append(id).append(",'");
/*  93: 93 */      sqlText2.append(macs[i]).append("',").append(bw).append(",'").append(branchId).append("')");
/*  94: 94 */      sqls.add(sqlText2.toString());
/*  95: 95 */      id++;
/*  96:    */    }
/*  97: 97 */    String[] arrSqls = new String[sqls.size()];
/*  98: 98 */    sqls.toArray(arrSqls);
/*  99: 99 */    this.jdbcTemplate.batchUpdate(arrSqls);
/* 100:    */    
/* 101:101 */    reload();
/* 102:102 */    return macs.length;
/* 103:    */  }
/* 104:    */  
/* 105:    */  private void reload() {
/* 106:106 */    java.util.List<com.soofound.portal.bean.BlackWhiteDto> dtos = ((com.soofound.portal.dao.BlackWhiteDao)this.dao).findAll();
/* 107:107 */    this.bwds.clear();
/* 108:108 */    for (com.soofound.portal.bean.BlackWhiteDto dto : dtos) {
/* 109:109 */      this.bwds.put(dto.getBranchId() + "-" + dto.getMac(), dto);
/* 110:    */    }
/* 111:    */  }
/* 112:    */  
/* 113:    */  public boolean isBlack(String branchIdAndMac) {
/* 114:114 */    com.soofound.portal.bean.BlackWhiteDto bwd = (com.soofound.portal.bean.BlackWhiteDto)this.bwds.get(branchIdAndMac);
/* 115:115 */    if ((bwd != null) && (bwd.getBw() == 2))
/* 116:116 */      return true;
/* 117:117 */    return false;
/* 118:    */  }
/* 119:    */  
/* 120:    */  public boolean isWhite(String branchIdAndMac) {
/* 121:121 */    com.soofound.portal.bean.BlackWhiteDto bwd = (com.soofound.portal.bean.BlackWhiteDto)this.bwds.get(branchIdAndMac);
/* 122:122 */    if ((bwd != null) && (bwd.getBw() == 1))
/* 123:123 */      return true;
/* 124:124 */    return false;
/* 125:    */  }
/* 126:    */  
/* 134:    */  @org.springframework.beans.factory.annotation.Autowired
/* 135:    */  protected void setDao(com.soofound.portal.dao.BlackWhiteDao dao)
/* 136:    */  {
/* 137:137 */    this.dao = dao;
/* 138:138 */    reload();
/* 139:    */  }
/* 140:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.service.BlackWhiteService
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */