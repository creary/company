/*  1:   */package com.soofound.operation.bean;
/*  2:   */
/*  3:   */import java.util.List;
/*  4:   */import org.springframework.jdbc.core.JdbcTemplate;
/*  5:   */import org.springframework.stereotype.Component;
/*  6:   */
/*  7:   */@Component
/*  8:   */public class GroupWechatDao extends com.soofound.framework.jdbc.BaseDao<GroupWechatDto>
/*  9:   */{
/* 10:   */  private static final String FIND_GROUP_ID = "select group_id from membership_ap_group where ap_id = (select id from cpe_host where serial_number = ?)";
/* 11:   */  
/* 12:   */  public GroupWechatDto findBySerialNumber(String serialNo)
/* 13:   */  {
/* 14:   */    try
/* 15:   */    {
/* 16:16 */      String groupId = (String)getJdbcTemplate().queryForObject("select group_id from membership_ap_group where ap_id = (select id from cpe_host where serial_number = ?)", new String[] { serialNo }, String.class);
/* 17:17 */      List<GroupWechatDto> dtos = findAll();
/* 18:18 */      for (GroupWechatDto dto : dtos) {
/* 19:19 */        if (groupId.startsWith(dto.getGroupId()))
/* 20:20 */          return dto;
/* 21:   */      }
/* 22:   */    } catch (Exception ex) {
/* 23:23 */      ex.printStackTrace();
/* 24:   */    }
/* 25:25 */    return null;
/* 26:   */  }
/* 27:   */  
/* 28:   */  public GroupWechatDto findByIP(String ipAddress) {
/* 29:29 */    String sql = "select * from group_wechat where ip_address = '" + ipAddress + "'";
/* 30:30 */    return (GroupWechatDto)super.findOne(sql);
/* 31:   */  }
/* 32:   */  
/* 33:   */  protected String getQuerySQL(java.util.Map<String, String> options)
/* 34:   */  {
/* 35:35 */    return "select * from group_wechat order by group_id";
/* 36:   */  }
/* 37:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.operation.bean.GroupWechatDao
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */