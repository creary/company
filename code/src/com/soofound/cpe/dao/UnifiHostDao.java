/*  1:   */package com.soofound.cpe.dao;
/*  2:   */
/*  3:   */import com.soofound.cpe.bean.UnifiHostDto;
/*  4:   */import com.soofound.framework.jdbc.BaseDao;
/*  5:   */import com.soofound.framework.util.CommonUtil;
/*  6:   */import java.util.ArrayList;
/*  7:   */import java.util.List;
/*  8:   */import java.util.Map;
/*  9:   */import org.springframework.jdbc.core.JdbcTemplate;
/* 10:   */import org.springframework.stereotype.Component;
/* 11:   */
/* 12:   */@Component
/* 13:   */public final class UnifiHostDao extends BaseDao<UnifiHostDto>
/* 14:   */{
/* 15:   */  protected String getQuerySQL(Map<String, String> options)
/* 16:   */  {
/* 17:17 */    StringBuffer sqlText = new StringBuffer(100);
/* 18:18 */    sqlText.append("select * from view_cpe_unifi where 1=1 ");
/* 19:19 */    if (!CommonUtil.isEmpty((String)options.get("acip")))
/* 20:20 */      sqlText.append(" and acip like '%").append((String)options.get("acip")).append("%'");
/* 21:21 */    if (!CommonUtil.isEmpty((String)options.get("site")))
/* 22:22 */      sqlText.append(" and site like '%").append((String)options.get("site")).append("%'");
/* 23:23 */    if (!CommonUtil.isEmpty((String)options.get("branchId")))
/* 24:24 */      sqlText.append(" and branch_id like '%").append((String)options.get("branchId")).append("%'");
/* 25:25 */    sqlText.append(" order by id");
/* 26:26 */    return sqlText.toString();
/* 27:   */  }
/* 28:   */  
/* 29:   */  public int save(UnifiHostDto dto)
/* 30:   */  {
/* 31:31 */    dto.setId(getNextID());
/* 32:32 */    StringBuffer sqlText = new StringBuffer(100);
/* 33:33 */    sqlText.append("insert into cpe_unifi(id,acip,port,site,username,password,branch_id,host_id)values(").append(dto.getId());
/* 34:34 */    sqlText.append(",'").append(dto.getAcip()).append("','").append(dto.getPort()).append("','").append(dto.getSite());
/* 35:35 */    sqlText.append("','").append(dto.getUsername()).append("','").append(dto.getPassword()).append("','");
/* 36:36 */    sqlText.append(dto.getBranchId()).append("',").append(dto.getHostId()).append(")");
/* 37:37 */    return getJdbcTemplate().update(sqlText.toString());
/* 38:   */  }
/* 39:   */  
/* 40:   */  public int update(UnifiHostDto dto)
/* 41:   */  {
/* 42:42 */    StringBuffer sqlText = new StringBuffer(100);
/* 43:43 */    sqlText.append("update cpe_unifi set acip='").append(dto.getAcip()).append("',port='");
/* 44:44 */    sqlText.append(dto.getPort()).append("',site='").append(dto.getSite()).append("',username='");
/* 45:45 */    sqlText.append(dto.getUsername()).append("',password='").append(dto.getPassword()).append("' where id=");
/* 46:46 */    sqlText.append(dto.getId());
/* 47:47 */    int result = getJdbcTemplate().update(sqlText.toString());
/* 48:48 */    return result;
/* 49:   */  }
/* 50:   */  
/* 51:   */  public UnifiHostDto findBySite(String site) {
/* 52:52 */    List<UnifiHostDto> dtos = findAll();
/* 53:53 */    for (UnifiHostDto dto : dtos) {
/* 54:54 */      if (dto.getSites().contains(site))
/* 55:55 */        return dto;
/* 56:   */    }
/* 57:57 */    return null;
/* 58:   */  }
/* 59:   */  
/* 60:   */  public int delete(String[] ids)
/* 61:   */  {
/* 62:62 */    List<String> sqls = new ArrayList();
/* 63:63 */    for (String id : ids) {
/* 64:64 */      sqls.add("delete from cpe_host where id=(select host_id from cpe_unifi where id=" + id + ")");
/* 65:65 */      sqls.add("delete from cpe_unifi where id=" + id);
/* 66:   */    }
/* 67:67 */    return batchUpdate(sqls);
/* 68:   */  }
/* 69:   */  
/* 70:   */  public UnifiHostDto findByHostId(String hostId) {
/* 71:71 */    String sql = "select * from cpe_unifi where host_id = " + hostId;
/* 72:72 */    return (UnifiHostDto)super.findOne(sql);
/* 73:   */  }
/* 74:   */  
/* 75:   */  public synchronized int getNextID() {
/* 76:76 */    Integer id = (Integer)super.getJdbcTemplate().queryForObject("select max(id) + 1 from cpe_unifi", Integer.class);
/* 77:77 */    if (id == null)
/* 78:78 */      return 1;
/* 79:79 */    return id.intValue();
/* 80:   */  }
/* 81:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.cpe.dao.UnifiHostDao
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */