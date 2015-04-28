/*  1:   */package com.soofound.cpe.dao;
/*  2:   */
/*  3:   */import com.soofound.cpe.web.HostService;
/*  4:   */import com.soofound.framework.jdbc.BaseDao;
/*  5:   */import com.soofound.framework.util.SysConfigHelper;
/*  6:   */import java.util.List;
/*  7:   */import org.springframework.jdbc.core.JdbcTemplate;
/*  8:   */import org.springframework.stereotype.Component;
/*  9:   */
/* 10:   */@Component
/* 11:   */public final class BssidDao extends BaseDao<com.soofound.cpe.bean.BssidDto>
/* 12:   */{
/* 13:   */  public List<com.soofound.cpe.bean.BssidDto> findByAP(String apId)
/* 14:   */  {
/* 15:15 */    String sql = "select * from view_cpe_ssid where ap_id=" + apId + " order by ife";
/* 16:16 */    return super.findByCriteria(sql);
/* 17:   */  }
/* 18:   */  
/* 19:   */  public com.soofound.cpe.bean.BssidDto findByKey(String apId, String ife) {
/* 20:20 */    String sql = "select * from view_cpe_ssid where ap_id=" + apId + " and ife=" + ife;
/* 21:21 */    return (com.soofound.cpe.bean.BssidDto)super.findOne(sql);
/* 22:   */  }
/* 23:   */  
/* 24:   */  public List<com.soofound.cpe.bean.BssidDto> findByHosts(String hostIds) {
/* 25:25 */    StringBuilder sqlText = new StringBuilder(100);
/* 26:26 */    sqlText.append("select * from view_cpe_ssid where ap_id in (").append(hostIds).append(")");
/* 27:27 */    return super.findByCriteria(sqlText.toString());
/* 28:   */  }
/* 29:   */  
/* 30:   */  public List<com.soofound.cpe.bean.BssidDto> findByHost(String apId) {
/* 31:31 */    String sql = "select * from view_cpe_ssid where ap_id=" + apId + " order by ife";
/* 32:32 */    return super.findByCriteria(sql);
/* 33:   */  }
/* 34:   */  
/* 35:   */  public List<com.soofound.cpe.bean.BssidDto> findByPolicy(String pid) {
/* 36:36 */    String sql = "select * from view_cpe_ssid where policy_id=" + pid + " order by ife";
/* 37:37 */    return super.findByCriteria(sql);
/* 38:   */  }
/* 39:   */  
/* 40:   */  public void updateSSid(com.soofound.cpe.bean.BssidDto dto) {
/* 41:41 */    StringBuilder sqlText1 = new StringBuilder(200);
/* 42:42 */    sqlText1.append("update cpe_ssid set enable = ").append(dto.getEnable());
/* 43:43 */    if (dto.getName() != null)
/* 44:44 */      sqlText1.append(",name='").append(dto.getName()).append("'");
/* 45:45 */    if (dto.getCodeId() > 0)
/* 46:46 */      sqlText1.append(",code_id=").append(dto.getCodeId());
/* 47:47 */    if (dto.getPolicyId() > 0)
/* 48:48 */      sqlText1.append(",policy_id=").append(dto.getPolicyId());
/* 49:49 */    if (dto.getPortalId() > 0)
/* 50:50 */      sqlText1.append(",portal_id=").append(dto.getPortalId());
/* 51:51 */    sqlText1.append(" where ap_id=").append(dto.getApId()).append(" and ife=").append(dto.getIfe());
/* 52:52 */    int result = getJdbcTemplate().update(sqlText1.toString());
/* 53:53 */    if (result == 0) {
/* 54:54 */      sqlText1.setLength(0);
/* 55:55 */      sqlText1.append("insert into cpe_ssid(ap_id,ife,name,policy_id,portal_id,code_id,enable)values(").append(dto.getApId());
/* 56:56 */      sqlText1.append(",").append(dto.getIfe()).append(",'").append(dto.getName()).append("',").append(dto.getPolicyId());
/* 57:57 */      sqlText1.append(",").append(dto.getPortalId()).append(",").append(dto.getCodeId()).append(",").append(dto.getEnable()).append(")");
/* 58:58 */      getJdbcTemplate().update(sqlText1.toString());
/* 59:   */    }
/* 60:60 */    if (dto.getName() != null) {
/* 61:61 */      List<com.soofound.cpe.bean.BssidDto> dtos = findByHost(dto.getApId());
/* 62:62 */      sqlText1.setLength(0);
/* 63:63 */      sqlText1.append("update cpe_host set ssid='").append(getSsidText(dtos)).append("' where id=").append(dto.getApId());
/* 64:64 */      getJdbcTemplate().update(sqlText1.toString());
/* 65:   */      
/* 66:66 */      HostService hs = (HostService)SysConfigHelper.getBean("hostService");
/* 67:67 */      String pn = hs.getIfName(dto.getIfe());
/* 68:68 */      sqlText1.setLength(0);
/* 69:69 */      sqlText1.append("update cpe_host_property set value='").append(dto.getName()).append("' where host_id=").append(dto.getApId());
/* 70:70 */      sqlText1.append(" and pid=(select id from cpe_property where name='").append(pn).append("')");
/* 71:71 */      getJdbcTemplate().update(sqlText1.toString());
/* 72:   */    }
/* 73:   */  }
/* 74:   */  
/* 75:   */  private String getSsidText(List<com.soofound.cpe.bean.BssidDto> dtos) {
/* 76:76 */    StringBuilder str = new StringBuilder(100);
/* 77:77 */    for (com.soofound.cpe.bean.BssidDto dto : dtos) {
/* 78:78 */      if (dto.getEnable() == 1)
/* 79:79 */        str.append("/").append(dto.getName());
/* 80:   */    }
/* 81:81 */    if (str.length() > 0)
/* 82:82 */      return str.substring(1);
/* 83:83 */    return "";
/* 84:   */  }
/* 85:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.cpe.dao.BssidDao
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */