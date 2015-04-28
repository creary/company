/*  1:   */package com.soofound.cpe.util;
/*  2:   */
/*  3:   */import com.danga.MemCached.MemCachedClient;
/*  4:   */import com.soofound.cpe.bean.HostBean;
/*  5:   */import com.soofound.cpe.dao.HostDao;
/*  6:   */import com.soofound.framework.job.ScheduleJob;
/*  7:   */import com.soofound.framework.util.SysConfigHelper;
/*  8:   */import java.util.List;
/*  9:   */import java.util.concurrent.TimeUnit;
/* 10:   */import org.springframework.jdbc.core.JdbcTemplate;
/* 11:   */
/* 12:   */public class LongConnectStunJob
/* 13:   */  implements ScheduleJob
/* 14:   */{
/* 15:   */  public void run()
/* 16:   */  {
/* 17:17 */    MemCachedClient mcc = (MemCachedClient)SysConfigHelper.getBean("memcachedClient");
/* 18:18 */    JdbcTemplate jdbc = (JdbcTemplate)SysConfigHelper.getBean("defaultJdbcTemplate");
/* 19:19 */    HostDao dao = new HostDao();
/* 20:20 */    List<HostBean> hosts = dao.findAll();
/* 21:21 */    for (HostBean host : hosts) {
/* 22:22 */      String upkey = "up_" + host.getSerialNumber().replace(":", "_");
/* 23:23 */      StringBuilder sqlText = new StringBuilder(100);
/* 24:24 */      if (mcc.get(upkey) == null) {
/* 25:25 */        sqlText.append("UPDATE cpe_host SET online = 0 where online > 0 and id = ").append(host.getId());
/* 26:   */      } else {
/* 27:27 */        sqlText.append("UPDATE cpe_host SET up_time = DATE_ADD(now(),INTERVAL-").append(mcc.get(upkey)).append(" second)");
/* 28:28 */        sqlText.append(",last_time = now(),online = 1 where id=").append(host.getId()).append(" and online in (0,1)");
/* 29:   */      }
/* 30:30 */      jdbc.update(sqlText.toString());
/* 31:   */    }
/* 32:   */  }
/* 33:   */  
/* 34:   */  public int getDelay() {
/* 35:35 */    return 2;
/* 36:   */  }
/* 37:   */  
/* 38:   */  public int getPeriod() {
/* 39:39 */    return 1;
/* 40:   */  }
/* 41:   */  
/* 42:   */  public TimeUnit getTimeUnit() {
/* 43:43 */    return TimeUnit.MINUTES;
/* 44:   */  }
/* 45:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.cpe.util.LongConnectStunJob
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */