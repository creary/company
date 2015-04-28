/*  1:   */package com.soofound.operation.bean;
/*  2:   */
/*  3:   */import com.soofound.framework.jdbc.BaseDao;
/*  4:   */
/*  5:   */@org.springframework.stereotype.Component
/*  6:   */public class ScheduleTimeDao extends BaseDao<ScheduleTimeDto>
/*  7:   */{
/*  8:   */  public ScheduleTimeDto findByID(String apId)
/*  9:   */  {
/* 10:10 */    String sql = "select * from cpe_schedule_time where ap_id=" + apId;
/* 11:11 */    return (ScheduleTimeDto)findOne(sql);
/* 12:   */  }
/* 13:   */  
/* 14:   */  public int delete(String apId) {
/* 15:15 */    String sql = "delete from cpe_schedule_time where ap_id=" + apId;
/* 16:16 */    return getJdbcTemplate().update(sql);
/* 17:   */  }
/* 18:   */  
/* 19:   */  public int save(ScheduleTimeDto dto) {
/* 20:20 */    StringBuilder sqlText = new StringBuilder(200);
/* 21:21 */    sqlText.append("update cpe_schedule_time set bits='").append(dto.getBits()).append("',times='");
/* 22:22 */    sqlText.append(dto.getTimes()).append("' where ap_id=").append(dto.getApId());
/* 23:23 */    int row = getJdbcTemplate().update(sqlText.toString());
/* 24:24 */    if (row == 0) {
/* 25:25 */      sqlText.setLength(0);
/* 26:26 */      sqlText.append("insert into cpe_schedule_time(ap_id,bits,times)values(").append(dto.getApId()).append(",'");
/* 27:27 */      sqlText.append(dto.getBits()).append("','").append(dto.getTimes()).append("')");
/* 28:28 */      row = getJdbcTemplate().update(sqlText.toString());
/* 29:   */    }
/* 30:30 */    return row;
/* 31:   */  }
/* 32:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.operation.bean.ScheduleTimeDao
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */