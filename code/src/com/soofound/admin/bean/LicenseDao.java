/*  1:   */package com.soofound.admin.bean;
/*  2:   */
/*  3:   */import com.soofound.cpe.util.CpeUtils;
/*  4:   */import com.soofound.framework.jdbc.BaseDao;
/*  5:   */import com.soofound.framework.util.CommonUtil;
/*  6:   */import java.util.ArrayList;
/*  7:   */import java.util.List;
/*  8:   */import java.util.Map;
/*  9:   */import org.springframework.jdbc.core.JdbcTemplate;
/* 10:   */import org.springframework.stereotype.Component;
/* 11:   */
/* 16:   */@Component
/* 17:   */public final class LicenseDao
/* 18:   */  extends BaseDao<LicenseDto>
/* 19:   */{
/* 20:   */  protected String getQuerySQL(Map<String, String> options)
/* 21:   */  {
/* 22:22 */    StringBuffer sqlText = new StringBuffer(100);
/* 23:23 */    sqlText.append("select * from view_admin_license where 1=1 ");
/* 24:24 */    if (!CommonUtil.isEmpty((String)options.get("mac")))
/* 25:25 */      sqlText.append(" and mac like '%").append((String)options.get("mac")).append("%'");
/* 26:26 */    if (!CommonUtil.isEmpty((String)options.get("branch")))
/* 27:27 */      sqlText.append(" and branch like '%").append((String)options.get("branch")).append("%'");
/* 28:28 */    if (!CommonUtil.isEmpty((String)options.get("branchId")))
/* 29:29 */      sqlText.append(" and branch_id like '").append((String)options.get("branchId")).append("%'");
/* 30:30 */    sqlText.append(" order by branch_id,mac");
/* 31:31 */    return sqlText.toString();
/* 32:   */  }
/* 33:   */  
/* 34:   */  public LicenseDto findByID(String mac)
/* 35:   */  {
/* 36:36 */    String sql = "select * from view_admin_license where mac='" + mac + "'";
/* 37:37 */    return (LicenseDto)super.findOne(sql);
/* 38:   */  }
/* 39:   */  
/* 40:   */  public LicenseDto findByKey(String mac, String flashId) {
/* 41:41 */    String sql = "select * from view_admin_license where mac='" + mac + "' or flash_id='" + flashId + "'";
/* 42:42 */    return (LicenseDto)super.findOne(sql);
/* 43:   */  }
/* 44:   */  
/* 45:   */  public LicenseDto findByKey(String apkey) {
/* 46:46 */    String sql = "select * from view_admin_license where apkey='" + apkey + "'";
/* 47:47 */    return (LicenseDto)super.findOne(sql);
/* 48:   */  }
/* 49:   */  
/* 50:   */  public int save(LicenseDto dto)
/* 51:   */  {
/* 52:52 */    StringBuilder sqlText = new StringBuilder(100);
/* 53:53 */    sqlText.append("insert into admin_license(mac,flash_id,apkey,branch_id,log_time)values('").append(dto.getMac().toUpperCase());
/* 54:54 */    sqlText.append("','").append(dto.getFlashId()).append("','").append(dto.getApkey()).append("','0',now())");
/* 55:55 */    return getJdbcTemplate().update(sqlText.toString());
/* 56:   */  }
/* 57:   */  
/* 58:   */  public int update(LicenseDto dto)
/* 59:   */  {
/* 60:60 */    StringBuilder sqlText = new StringBuilder(100);
/* 61:61 */    sqlText.append("update admin_license set flash_id='").append(dto.getFlashId()).append("',apkey='");
/* 62:62 */    sqlText.append(CpeUtils.generateAPKey(dto.getMac(), dto.getFlashId())).append("',log_time=NOW() where mac='");
/* 63:63 */    sqlText.append(dto.getMac().toUpperCase()).append("'");
/* 64:64 */    return getJdbcTemplate().update(sqlText.toString());
/* 65:   */  }
/* 66:   */  
/* 67:   */  public LicenseDto findByFlashID(String flashId) {
/* 68:68 */    String sql = "select * from view_admin_license where flash_id='" + flashId + "'";
/* 69:69 */    return (LicenseDto)super.findOne(sql);
/* 70:   */  }
/* 71:   */  
/* 72:   */  public int getMacNumber(String branchId) {
/* 73:73 */    StringBuilder sqlText = new StringBuilder(100);
/* 74:74 */    sqlText.append("SELECT COUNT(*) FROM admin_license WHERE branch_id='").append(branchId).append("'");
/* 75:   */    try {
/* 76:76 */      return ((Integer)super.getJdbcTemplate().queryForObject(sqlText.toString(), Integer.class)).intValue();
/* 77:   */    } catch (Exception e) {}
/* 78:78 */    return 0;
/* 79:   */  }
/* 80:   */  
/* 82:   */  public int delete(String[] ids)
/* 83:   */  {
/* 84:84 */    List<String> sqls = new ArrayList();
/* 85:85 */    for (String id : ids) {
/* 86:86 */      String sql1 = "delete from admin_license where mac='" + id + "'";
/* 87:87 */      sqls.add(sql1);
/* 88:88 */      String sql2 = "delete from cpe_host where serial_number='" + id + "'";
/* 89:89 */      sqls.add(sql2);
/* 90:   */    }
/* 91:91 */    return batchUpdate(sqls);
/* 92:   */  }
/* 93:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.admin.bean.LicenseDao
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */