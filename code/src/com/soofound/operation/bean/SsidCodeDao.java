/*  1:   */package com.soofound.operation.bean;
/*  2:   */
/*  3:   */import com.soofound.framework.jdbc.BaseDao;
/*  4:   */import java.util.List;
/*  5:   */import java.util.Map;
/*  6:   */import org.springframework.stereotype.Component;
/*  7:   */import org.springframework.util.StringUtils;
/*  8:   */
/*  9:   */@Component
/* 10:   */public class SsidCodeDao
/* 11:   */  extends BaseDao<SsidCodeDto>
/* 12:   */{
/* 13:   */  protected String getQuerySQL(Map<String, String> options)
/* 14:   */  {
/* 15:15 */    StringBuffer sqlText = new StringBuffer(100);
/* 16:16 */    sqlText.append("select * from cpe_ssid_code where 1 = 1 ");
/* 17:17 */    if (StringUtils.hasText((String)options.get("name")))
/* 18:18 */      sqlText.append(" and code like '%").append((String)options.get("name")).append("%'");
/* 19:19 */    if (StringUtils.hasText((String)options.get("ssid")))
/* 20:20 */      sqlText.append(" and ssid like '%").append((String)options.get("ssid")).append("%'");
/* 21:21 */    sqlText.append(" order by id desc");
/* 22:22 */    return sqlText.toString();
/* 23:   */  }
/* 24:   */  
/* 25:   */  public List<SsidCodeDto> findByBranch(String branchId) {
/* 26:26 */    StringBuilder sqlText = new StringBuilder(100);
/* 27:27 */    sqlText.append("select * from cpe_ssid_code where branch_id ='").append(branchId).append("'");
/* 28:28 */    return super.findByCriteria(sqlText.toString());
/* 29:   */  }
/* 30:   */  
/* 31:   */  public SsidCodeDto findBySSID(String ssid) {
/* 32:32 */    String sql = "select * from cpe_ssid_code where ssid='" + ssid + "'";
/* 33:33 */    return (SsidCodeDto)findOne(sql);
/* 34:   */  }
/* 35:   */  
/* 36:   */  public SsidCodeDto findByID(String id)
/* 37:   */  {
/* 38:38 */    String sql = "select * from cpe_ssid_code where id=" + id;
/* 39:39 */    return (SsidCodeDto)findOne(sql);
/* 40:   */  }
/* 41:   */  
/* 42:   */  public int update(SsidCodeDto dto)
/* 43:   */  {
/* 44:44 */    StringBuilder sqlText = new StringBuilder(100);
/* 45:45 */    sqlText.append("update cpe_ssid_code set ssid='").append(dto.getSsid()).append("',name='");
/* 46:46 */    sqlText.append(dto.getName()).append("' where id=").append(dto.getId());
/* 47:47 */    return super.saveOrUpdate(sqlText.toString());
/* 48:   */  }
/* 49:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.operation.bean.SsidCodeDao
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */