/*  1:   */package com.soofound.acside.bean;
/*  2:   */
/*  3:   */import org.springframework.stereotype.Component;
/*  4:   */
/*  5:   */@Component
/*  6:   */public class OutsideIPDao extends com.soofound.framework.jdbc.BaseDao<OutsideIPDto>
/*  7:   */{
/*  8:   */  public OutsideIPDto findByID(String ipAddress)
/*  9:   */  {
/* 10:10 */    String sql = "SELECT * FROM outside_ip_address WHERE ip_address='" + ipAddress + "'";
/* 11:11 */    return (OutsideIPDto)super.findOne(sql);
/* 12:   */  }
/* 13:   */  
/* 14:   */  public int save(OutsideIPDto dto) {
/* 15:15 */    StringBuilder sqlText = new StringBuilder(100);
/* 16:16 */    sqlText.append("insert into outside_ip_address(ip_address,province,location)values('").append(dto.getIpAddress());
/* 17:17 */    sqlText.append("','").append(dto.getProvince()).append("','").append(dto.getLocation()).append("')");
/* 18:18 */    return super.saveOrUpdate(sqlText.toString());
/* 19:   */  }
/* 20:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.acside.bean.OutsideIPDao
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */