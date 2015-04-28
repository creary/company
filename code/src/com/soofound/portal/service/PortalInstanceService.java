/*   1:    */package com.soofound.portal.service;
/*   2:    */
/*   3:    */import com.alibaba.fastjson.JSON;
/*   4:    */import com.alibaba.fastjson.JSONObject;
/*   5:    */import com.soofound.cpe.bean.BssidDto;
/*   6:    */import com.soofound.cpe.dao.BssidDao;
/*   7:    */import com.soofound.framework.jdbc.Persistable;
/*   8:    */import com.soofound.framework.web.BaseService;
/*   9:    */import com.soofound.portal.bean.PortalInstanceDto;
/*  10:    */import com.soofound.portal.dao.PortalInstanceDao;
/*  11:    */import com.soofound.portal.util.WifiDogUtils;
/*  12:    */import java.io.PrintStream;
/*  13:    */import java.util.HashMap;
/*  14:    */import java.util.List;
/*  15:    */import java.util.Map;
/*  16:    */import org.springframework.beans.factory.annotation.Autowired;
/*  17:    */import org.springframework.beans.factory.annotation.Qualifier;
/*  18:    */import org.springframework.jdbc.core.JdbcTemplate;
/*  19:    */import org.springframework.stereotype.Service;
/*  20:    */
/*  22:    */@Service
/*  23:    */public final class PortalInstanceService
/*  24:    */  extends BaseService<PortalInstanceDao>
/*  25:    */{
/*  26:    */  @Autowired
/*  27:    */  @Qualifier("defaultJdbcTemplate")
/*  28:    */  private JdbcTemplate jdbcTemplate;
/*  29:    */  @Autowired
/*  30:    */  private BssidDao smdao;
/*  31:    */  
/*  32:    */  public PortalInstanceDto getPortalByName(String branchId, String name)
/*  33:    */  {
/*  34: 34 */    return ((PortalInstanceDao)this.dao).getPortalByName(branchId, name);
/*  35:    */  }
/*  36:    */  
/*  37:    */  public int save(Persistable dto)
/*  38:    */  {
/*  39: 39 */    PortalInstanceDto pid = (PortalInstanceDto)dto;
/*  40: 40 */    if (pid.getTag() == 1) {
/*  41: 41 */      String sql = "update portal_instance set tag=0 where tag=1 and branch_id='" + pid.getBranchId() + "'";
/*  42: 42 */      this.jdbcTemplate.update(sql);
/*  43:    */    }
/*  44: 44 */    int result = ((PortalInstanceDao)this.dao).save(pid);
/*  45: 45 */    insertPortalPages(String.valueOf(pid.getId()), pid.getTid());
/*  46: 46 */    return result;
/*  47:    */  }
/*  48:    */  
/*  49:    */  public int update(Persistable dto)
/*  50:    */  {
/*  51: 51 */    PortalInstanceDto pid = (PortalInstanceDto)dto;
/*  52: 52 */    if (pid.getTag() == 1) {
/*  53: 53 */      String sql = "update portal_instance set tag=0 where tag=1 and branch_id='" + pid.getBranchId() + "'";
/*  54: 54 */      this.jdbcTemplate.update(sql);
/*  55:    */    }
/*  56: 56 */    return ((PortalInstanceDao)this.dao).update(pid);
/*  57:    */  }
/*  58:    */  
/*  59:    */  public void insertPortalPages(String pid, String tid) {
/*  60: 60 */    String jsonContent = WifiDogUtils.getMsiteJsonContent(WifiDogUtils.getTemplatePath(tid) + "/define/tpl_define.json");
/*  61: 61 */    Map<String, Object> jsondata = (Map)JSON.parseObject(jsonContent, Map.class);
/*  62: 62 */    JSONObject jobj = (JSONObject)jsondata.get("fields");
/*  63: 63 */    for (String page : jobj.keySet()) {
/*  64: 64 */      Map<String, Object> fields = new HashMap();
/*  65: 65 */      fields.put("fields", jobj.get(page));
/*  66: 66 */      ((PortalInstanceDao)this.dao).insertPortalPage(pid, page, JSONObject.toJSONString(fields));
/*  67:    */    }
/*  68:    */  }
/*  69:    */  
/*  70:    */  public int insertPortalPage(String pid, String page, String value) {
/*  71: 71 */    return ((PortalInstanceDao)this.dao).insertPortalPage(pid, page, value);
/*  72:    */  }
/*  73:    */  
/*  74:    */  public Map<String, String> getPortalPage(String pid, String page) {
/*  75: 75 */    String pageValue = ((PortalInstanceDao)this.dao).getPortalPage(pid, page);
/*  76: 76 */    if (pageValue == null)
/*  77: 77 */      return new HashMap();
/*  78: 78 */    return WifiDogUtils.getProperties(pageValue);
/*  79:    */  }
/*  80:    */  
/*  81:    */  public int updatePortalPage(String pid, String page, String value) {
/*  82: 82 */    return ((PortalInstanceDao)this.dao).updatePortalPage(pid, page, value);
/*  83:    */  }
/*  84:    */  
/*  85:    */  public List<PortalInstanceDto> findByBranch(String branchId) {
/*  86: 86 */    return ((PortalInstanceDao)this.dao).findByBranch(branchId);
/*  87:    */  }
/*  88:    */  
/*  89:    */  public PortalInstanceDto getDefaultPortal(String branchId) {
/*  90: 90 */    PortalInstanceDto dto = ((PortalInstanceDao)this.dao).getDefaultPortal(branchId);
/*  91: 91 */    if (dto == null) {
/*  92: 92 */      List<PortalInstanceDto> dtos = ((PortalInstanceDao)this.dao).findByBranch(branchId);
/*  93: 93 */      if (dtos.size() > 0) {
/*  94: 94 */        dto = (PortalInstanceDto)dtos.get(0);
/*  95:    */      } else
/*  96: 96 */        dto = PortalInstanceDto.bornDefaultPortal();
/*  97:    */    }
/*  98: 98 */    return dto;
/*  99:    */  }
/* 100:    */  
/* 101:    */  public PortalInstanceDto getPortalByID(String pid) {
/* 102:102 */    PortalInstanceDto dto = ((PortalInstanceDao)this.dao).findByID(pid);
/* 103:103 */    if (dto == null)
/* 104:104 */      return PortalInstanceDto.bornDefaultPortal();
/* 105:105 */    Map<String, String> pages = ((PortalInstanceDao)this.dao).getPortalPages(pid);
/* 106:106 */    if (pages != null) {
/* 107:107 */      for (String p : pages.keySet())
/* 108:108 */        dto.setPage(p, WifiDogUtils.getProperties((String)pages.get(p)));
/* 109:    */    }
/* 110:110 */    return dto;
/* 111:    */  }
/* 112:    */  
/* 113:    */  public PortalInstanceDto getPortal(String ssid) {
/* 114:114 */    PortalInstanceDto pid = null;
/* 115:    */    try {
/* 116:116 */      String[] temp = ssid.split("-");
/* 117:117 */      String ife = WifiDogUtils.getInterface(temp[1]);
/* 118:118 */      BssidDto smd = this.smdao.findByKey(temp[0], ife);
/* 119:119 */      if (smd != null)
/* 120:120 */        pid = getPortalByID(smd.getPortalId());
/* 121:    */    } catch (Exception ex) {
/* 122:122 */      ex.printStackTrace();
/* 123:    */    }
/* 124:124 */    if (pid == null) {
/* 125:125 */      System.out.println("Error#Can not find Portal,SSID=" + ssid);
/* 126:126 */      pid = PortalInstanceDto.bornDefaultPortal();
/* 127:    */    }
/* 128:128 */    return pid;
/* 129:    */  }
/* 130:    */  
/* 135:    */  @Autowired
/* 136:    */  protected void setDao(PortalInstanceDao dao)
/* 137:    */  {
/* 138:138 */    this.dao = dao;
/* 139:    */  }
/* 140:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.service.PortalInstanceService
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */