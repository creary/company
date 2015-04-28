/*  1:   */package com.soofound.portal.dao;
/*  2:   */
/*  3:   */import com.soofound.framework.jdbc.BaseDao;
/*  4:   */import com.soofound.framework.util.CommonUtil;
/*  5:   */import com.soofound.portal.bean.CountyDto;
/*  6:   */import java.util.List;
/*  7:   */import java.util.Map;
/*  8:   */import org.springframework.stereotype.Component;
/*  9:   */
/* 15:   */@Component
/* 16:   */public final class CountyDao
/* 17:   */  extends BaseDao<CountyDto>
/* 18:   */{
/* 19:   */  protected String getQuerySQL(Map<String, String> options)
/* 20:   */  {
/* 21:21 */    StringBuffer sqlText = new StringBuffer(100);
/* 22:22 */    sqlText.append("select * from view_address_county where 1=1 ");
/* 23:23 */    if (!CommonUtil.isEmpty((String)options.get("name"))) {
/* 24:24 */      sqlText.append(" and (name like '%").append((String)options.get("name")).append("%'");
/* 25:25 */      sqlText.append(" or en_name '%").append((String)options.get("name")).append("%')");
/* 26:   */    }
/* 27:27 */    if (!CommonUtil.isEmpty((String)options.get("city"))) {
/* 28:28 */      sqlText.append(" and (city = '").append((String)options.get("city")).append("'");
/* 29:29 */      sqlText.append(" or en_city = '").append((String)options.get("city")).append("')");
/* 30:   */    }
/* 31:31 */    if (!CommonUtil.isEmpty((String)options.get("cityId"))) {
/* 32:32 */      sqlText.append(" and city_id='").append((String)options.get("cityId")).append("'");
/* 33:   */    }
/* 34:34 */    sqlText.append(" order by id");
/* 35:35 */    return sqlText.toString();
/* 36:   */  }
/* 37:   */  
/* 38:   */  public List<CountyDto> getBrothers(int addressPid) {
/* 39:39 */    StringBuilder sqlText = new StringBuilder();
/* 40:40 */    sqlText.append("select * from view_address_county ");
/* 41:41 */    if (addressPid > 0)
/* 42:42 */      sqlText.append(" where city_id=").append(addressPid);
/* 43:43 */    return super.findByCriteria(sqlText.toString());
/* 44:   */  }
/* 45:   */  
/* 46:   */  public CountyDto findByID(String id)
/* 47:   */  {
/* 48:48 */    String sql = "select * from view_address_county where id=" + id;
/* 49:49 */    return (CountyDto)super.findOne(sql);
/* 50:   */  }
/* 51:   */  
/* 52:   */  public List<CountyDto> findByPid(String pid) {
/* 53:53 */    String sql = "select * from view_address_county where city_id='" + pid + "'";
/* 54:54 */    return super.findByCriteria(sql);
/* 55:   */  }
/* 56:   */  
/* 57:   */  public CountyDto findByEnName(String enName, String pEnName) {
/* 58:58 */    String sql = "select * from view_address_county where en_name='" + enName + "' and en_city='" + pEnName + "'";
/* 59:59 */    return (CountyDto)super.findOne(sql);
/* 60:   */  }
/* 61:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.dao.CountyDao
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */