/*  1:   */package com.soofound.portal.dao;
/*  2:   */
/*  3:   */import com.soofound.framework.jdbc.BaseDao;
/*  4:   */import com.soofound.framework.util.CommonUtil;
/*  5:   */import com.soofound.portal.bean.ProvinceDto;
/*  6:   */import java.util.List;
/*  7:   */import java.util.Map;
/*  8:   */import org.springframework.stereotype.Component;
/*  9:   */
/* 15:   */@Component
/* 16:   */public final class ProvinceDao
/* 17:   */  extends BaseDao<ProvinceDto>
/* 18:   */{
/* 19:   */  protected String getQuerySQL(Map<String, String> options)
/* 20:   */  {
/* 21:21 */    StringBuffer sqlText = new StringBuffer(100);
/* 22:22 */    sqlText.append("select * from address_province where 1=1 ");
/* 23:23 */    if (!CommonUtil.isEmpty((String)options.get("name"))) {
/* 24:24 */      sqlText.append(" and ( name ='").append((String)options.get("name")).append("'");
/* 25:25 */      sqlText.append(" or en_name ='").append((String)options.get("name")).append("')");
/* 26:   */    }
/* 27:27 */    if (!CommonUtil.isEmpty((String)options.get("area"))) {
/* 28:28 */      sqlText.append(" and (area = '").append((String)options.get("area")).append("'");
/* 29:29 */      sqlText.append(" or en_area = '").append((String)options.get("area")).append("')");
/* 30:   */    }
/* 31:31 */    if (!CommonUtil.isEmpty((String)options.get("areaId"))) {
/* 32:32 */      sqlText.append(" and area_id='").append((String)options.get("areaId")).append("'");
/* 33:   */    }
/* 34:34 */    sqlText.append(" order by id");
/* 35:35 */    return sqlText.toString();
/* 36:   */  }
/* 37:   */  
/* 38:   */  public ProvinceDto findByID(String id)
/* 39:   */  {
/* 40:40 */    String sql = "select * from address_province where id=" + id;
/* 41:41 */    return (ProvinceDto)super.findOne(sql);
/* 42:   */  }
/* 43:   */  
/* 44:   */  public List<ProvinceDto> getBrothers(String enName, int addressPid) {
/* 45:45 */    return super.findByCriteria("select * from address_province order by id");
/* 46:   */  }
/* 47:   */  
/* 48:   */  public List<ProvinceDto> findByPid(String pid) {
/* 49:49 */    String sql = "select * from address_province where area_id='" + pid + "'";
/* 50:50 */    return super.findByCriteria(sql);
/* 51:   */  }
/* 52:   */  
/* 53:   */  public ProvinceDto findByEnName(String enName, String pEnName) {
/* 54:54 */    String sql = "select * from address_province where en_name='" + enName + "' and en_area='" + pEnName + "'";
/* 55:55 */    return (ProvinceDto)super.findOne(sql);
/* 56:   */  }
/* 57:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.dao.ProvinceDao
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */