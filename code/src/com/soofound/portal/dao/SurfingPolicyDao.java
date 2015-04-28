/*   1:    */package com.soofound.portal.dao;
/*   2:    */
/*   3:    */import com.soofound.framework.jdbc.BaseDao;
/*   4:    */import com.soofound.framework.util.CommonUtil;
/*   5:    */import com.soofound.portal.bean.SurfingPolicyDto;
/*   6:    */import java.util.ArrayList;
/*   7:    */import java.util.List;
/*   8:    */import java.util.Map;
/*   9:    */import org.springframework.jdbc.core.JdbcTemplate;
/*  10:    */import org.springframework.stereotype.Component;
/*  11:    */
/*  12:    */@Component
/*  13:    */public final class SurfingPolicyDao extends BaseDao<SurfingPolicyDto>
/*  14:    */{
/*  15:    */  protected String getQuerySQL(Map<String, String> options)
/*  16:    */  {
/*  17: 17 */    StringBuffer sqlText = new StringBuffer(100);
/*  18: 18 */    sqlText.append("select * from view_surfing_policy where 1=1 ");
/*  19: 19 */    if (!CommonUtil.isEmpty((String)options.get("branchId")))
/*  20: 20 */      sqlText.append(" and branch_id = '").append((String)options.get("branchId")).append("'");
/*  21: 21 */    if (!CommonUtil.isEmpty((String)options.get("name")))
/*  22: 22 */      sqlText.append(" and name like '%").append((String)options.get("name")).append("%'");
/*  23: 23 */    sqlText.append(" order by id desc");
/*  24: 24 */    return sqlText.toString();
/*  25:    */  }
/*  26:    */  
/*  27:    */  public SurfingPolicyDto getDefaultPolicy(String branchId) {
/*  28: 28 */    String sql = "select * from view_surfing_policy where tag=1 and branch_id='" + branchId + "'";
/*  29: 29 */    return (SurfingPolicyDto)super.findOne(sql);
/*  30:    */  }
/*  31:    */  
/*  32:    */  public SurfingPolicyDto findByID(String id)
/*  33:    */  {
/*  34: 34 */    String sql = "select * from view_surfing_policy where id=" + id;
/*  35: 35 */    return (SurfingPolicyDto)super.findOne(sql);
/*  36:    */  }
/*  37:    */  
/*  38:    */  public SurfingPolicyDto getPolicyBySSID(String apId, String ife) {
/*  39: 39 */    String sql = "SELECT * FROM view_surfing_policy a,cpe_ssid b WHERE a.id=b.policy_id AND b.ap_id=" + apId + " AND b.ife=" + ife;
/*  40: 40 */    return (SurfingPolicyDto)findOne(sql);
/*  41:    */  }
/*  42:    */  
/*  43:    */  public SurfingPolicyDto getPolicyByName(String branchId, String name) {
/*  44: 44 */    StringBuilder sqlText = new StringBuilder(200);
/*  45: 45 */    sqlText.append("select * from view_surfing_policy where name='").append(name).append("' and branch_id='");
/*  46: 46 */    sqlText.append(branchId).append("'");
/*  47: 47 */    return (SurfingPolicyDto)super.findOne(sqlText.toString());
/*  48:    */  }
/*  49:    */  
/*  50:    */  public int update(SurfingPolicyDto dto)
/*  51:    */  {
/*  52: 52 */    StringBuffer sqlText = new StringBuffer(100);
/*  53: 53 */    if (dto.getTag() == 1) {
/*  54: 54 */      sqlText.append("update portal_surfing_policy set redirect=").append(dto.getRedirect());
/*  55: 55 */      sqlText.append(",surfing_time=").append(dto.getSurfingTime());
/*  56: 56 */      sqlText.append(",idle_time=").append(dto.getIdleTime());
/*  57: 57 */      sqlText.append(",stay_time=").append(dto.getStayTime());
/*  58: 58 */      sqlText.append(",auth=").append(dto.getAuth());
/*  59: 59 */      sqlText.append(",pwd_auth=").append(dto.getPwdAuth());
/*  60: 60 */      sqlText.append(",sms_auth=").append(dto.getSmsAuth());
/*  61: 61 */      sqlText.append(",wechat_auth=").append(dto.getWechatAuth());
/*  62: 62 */      sqlText.append(",wechat_guide=").append(dto.getWechatGuide());
/*  63: 63 */      sqlText.append(",wechat_share=").append(dto.getWechatShare());
/*  64: 64 */      sqlText.append(",jump_to=").append(dto.getJumpTo());
/*  65: 65 */      sqlText.append(",jump_url='").append(dto.getJumpUrl());
/*  66: 66 */      sqlText.append("',one_account_device=").append(dto.getOneAccountDevice());
/*  67: 67 */      sqlText.append(",second_free=").append(dto.getSecondFree());
/*  68: 68 */      sqlText.append(",up_speed=").append(dto.getUpSpeed());
/*  69: 69 */      sqlText.append(",down_speed=").append(dto.getDownSpeed());
/*  70: 70 */      sqlText.append(",validity=").append(dto.getValidity());
/*  71: 71 */      sqlText.append(",sms_flag=").append(dto.getSmsFlag());
/*  72: 72 */      sqlText.append(",separate_portal=").append(dto.getSeparatePortal());
/*  73: 73 */      sqlText.append(" where id=").append(dto.getId());
/*  74:    */    } else {
/*  75: 75 */      sqlText.append("update portal_surfing_policy set cmcc=").append(dto.getCmcc());
/*  76: 76 */      sqlText.append(",portal_ip='").append(dto.getPortalIP());
/*  77: 77 */      sqlText.append("',portal_port='").append(dto.getPortalPort());
/*  78: 78 */      sqlText.append("',portal_url='").append(dto.getPortalUrl());
/*  79: 79 */      sqlText.append("' where id=").append(dto.getId());
/*  80:    */    }
/*  81: 81 */    return super.saveOrUpdate(sqlText.toString());
/*  82:    */  }
/*  83:    */  
/*  84:    */  public void simpleSaveOrUpdate(SurfingPolicyDto dto) {
/*  85: 85 */    if (dto.getTag() == 1) {
/*  86: 86 */      String sql = "update portal_surfing_policy set tag=0 where tag=1 and branch_id='" + dto.getBranchId() + "'";
/*  87: 87 */      getJdbcTemplate().update(sql);
/*  88:    */    }
/*  89: 89 */    StringBuilder sqlText = new StringBuilder(200);
/*  90: 90 */    if (dto.getId() == 0) {
/*  91: 91 */      dto.setId(getNextID());
/*  92: 92 */      sqlText.append("insert into portal_surfing_policy(id,branch_id,name,redirect,jump_to,tag,one_account_device,wechat_guide,second_free,");
/*  93: 93 */      sqlText.append("up_speed,down_speed,validity,portal_ip,portal_port,portal_url)values(").append(dto.getId()).append(",'").append(dto.getBranchId()).append("','");
/*  94: 94 */      sqlText.append(dto.getName()).append("',1,1,").append(dto.getTag()).append(",0,0,0,0,0,0,'','','')");
/*  95:    */    } else {
/*  96: 96 */      sqlText.append("update portal_surfing_policy set name='").append(dto.getName()).append("',tag=").append(dto.getTag());
/*  97: 97 */      sqlText.append(" where id=").append(dto.getId());
/*  98:    */    }
/*  99: 99 */    getJdbcTemplate().update(sqlText.toString());
/* 100:    */  }
/* 101:    */  
/* 102:    */  public List<SurfingPolicyDto> findByBranch(String branchId) {
/* 103:103 */    String sql = "select * from view_surfing_policy where branch_id = '" + branchId + "'";
/* 104:104 */    return super.findByCriteria(sql);
/* 105:    */  }
/* 106:    */  
/* 107:    */  public synchronized int getNextID() {
/* 108:108 */    Integer id = (Integer)super.getJdbcTemplate().queryForObject("select max(id) + 1 from portal_surfing_policy", Integer.class);
/* 109:109 */    if (id == null)
/* 110:110 */      return 1;
/* 111:111 */    return id.intValue();
/* 112:    */  }
/* 113:    */  
/* 114:    */  public int delete(String[] ids)
/* 115:    */  {
/* 116:116 */    List<String> sqls = new ArrayList();
/* 117:117 */    for (String id : ids) {
/* 118:118 */      sqls.add("delete from portal_surfing_policy where id=" + id);
/* 119:119 */      sqls.add("update cpe_ssid set policy_id=0 where policy_id=" + id);
/* 120:    */    }
/* 121:121 */    return batchUpdate(sqls);
/* 122:    */  }
/* 123:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.dao.SurfingPolicyDao
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */