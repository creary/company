/*  1:   */package com.soofound.portal.util;
/*  2:   */
/*  3:   */import com.soofound.cpe.bean.HostBean;
/*  4:   */import com.soofound.cpe.dao.DeviceLogDao;
/*  5:   */import com.soofound.cpe.dao.HostDao;
/*  6:   */import com.soofound.cpe.util.CpeActionExecutor;
/*  7:   */import com.soofound.cpe.util.CpeWaker;
/*  8:   */import com.soofound.cpe.web.HostService;
/*  9:   */import com.soofound.framework.job.ScheduleJob;
/* 10:   */import com.soofound.framework.util.DateUtil;
/* 11:   */import com.soofound.framework.util.SysConfigHelper;
/* 12:   */import com.soofound.operation.bean.ScheduleTimeDao;
/* 13:   */import com.soofound.operation.bean.ScheduleTimeDto;
/* 14:   */import java.util.Calendar;
/* 15:   */import java.util.Iterator;
/* 16:   */import java.util.List;
/* 17:   */import java.util.concurrent.TimeUnit;
/* 18:   */import org.springframework.jdbc.core.JdbcTemplate;
/* 19:   */
/* 20:   */public class OnlineUserSummaryJob implements ScheduleJob
/* 21:   */{
/* 22:   */  private static final String SQL_AUTH_TOTAL = "INSERT INTO online_user_summary(branch_id,flag,total,log_time) SELECT branch_id,flag,COUNT(*) total,now() FROM view_surfing_session WHERE state = 1 GROUP BY branch_id,flag";
/* 23:   */  private static final String SQL_TOTAL = "INSERT INTO online_user_summary(branch_id,flag,total,log_time) SELECT branch_id,'total',COUNT(*) total,now() FROM view_surfing_session WHERE state > 0 GROUP BY branch_id";
/* 24:   */  private static final String SQL_ACTIVE_TOTAL = "INSERT INTO online_user_summary(branch_id,flag,total,log_time) SELECT branch_id,'active',COUNT(*) total,now() FROM view_surfing_session WHERE state = 1 GROUP BY branch_id";
/* 25:   */  private int loop;
/* 26:   */  
/* 27:   */  public void run()
/* 28:   */  {
/* 29:29 */    JdbcTemplate jdbc = (JdbcTemplate)SysConfigHelper.getBean("defaultJdbcTemplate");
/* 30:30 */    String deleteSQL = "delete from online_user_summary where DATE_FORMAT(log_time,'%Y-%m-%d %H')='" + DateUtil.getCurrentHour() + "'";
/* 31:31 */    jdbc.update(deleteSQL);
/* 32:32 */    jdbc.update("INSERT INTO online_user_summary(branch_id,flag,total,log_time) SELECT branch_id,flag,COUNT(*) total,now() FROM view_surfing_session WHERE state = 1 GROUP BY branch_id,flag");
/* 33:33 */    jdbc.update("INSERT INTO online_user_summary(branch_id,flag,total,log_time) SELECT branch_id,'total',COUNT(*) total,now() FROM view_surfing_session WHERE state > 0 GROUP BY branch_id");
/* 34:34 */    jdbc.update("INSERT INTO online_user_summary(branch_id,flag,total,log_time) SELECT branch_id,'active',COUNT(*) total,now() FROM view_surfing_session WHERE state = 1 GROUP BY branch_id");
/* 35:   */    
/* 38:   */    try
/* 39:   */    {
/* 40:40 */      ScheduleTimeDao dao = new ScheduleTimeDao();
/* 41:41 */      List<ScheduleTimeDto> stds = dao.findAll();
/* 42:42 */      Calendar cal = Calendar.getInstance();
/* 43:43 */      int curDay = cal.get(7);
/* 44:44 */      int curTime = DateUtil.getCurrentIntHour() * 60 + DateUtil.getCurrentIntMinute();
/* 45:45 */      int j; int i; for (Iterator localIterator = stds.iterator(); localIterator.hasNext(); 
/* 46:   */          
/* 47:47 */          i < j)
/* 48:   */      {
/* 49:45 */        ScheduleTimeDto std = (ScheduleTimeDto)localIterator.next();
/* 50:46 */        String[] times = std.getTimes().split("#");
/* 51:47 */        String[] arrayOfString1; j = (arrayOfString1 = times).length;i = 0;continue;String time = arrayOfString1[i];
/* 52:48 */        String[] ths = time.split(":");
/* 53:49 */        if (curDay == Integer.parseInt(ths[0])) {
/* 54:50 */          int hst = Integer.parseInt(ths[1]) * 60 + Integer.parseInt(ths[2]);
/* 55:51 */          if ((curTime - hst > 0) && (curTime - hst <= 10)) {
/* 56:52 */            rebootAP(std.getApId());
/* 57:   */          }
/* 58:   */        }
/* 59:47 */        i++;
/* 61:   */      }
/* 62:   */      
/* 65:   */    }
/* 66:   */    catch (Exception ex)
/* 67:   */    {
/* 69:57 */      ex.printStackTrace();
/* 70:   */    }
/* 71:59 */    this.loop += 1;
/* 72:60 */    if (this.loop % 2 == 0)
/* 73:61 */      Runtime.getRuntime().gc();
/* 74:   */  }
/* 75:   */  
/* 76:   */  private void rebootAP(int apId) {
/* 77:65 */    HostDao dao = new HostDao();
/* 78:66 */    HostBean host = dao.findByID(apId);
/* 79:67 */    if ((host != null) && (host.getOnline() == 1)) {
/* 80:68 */      CpeActionExecutor cae = new CpeActionExecutor();
/* 81:69 */      HostService hs = (HostService)SysConfigHelper.getBean("hostService");
/* 82:70 */      hs.putCommand(apId, cae.getRebootString());
/* 83:   */      
/* 84:72 */      CpeWaker cw = new CpeWaker();
/* 85:73 */      cw.wakeup(String.valueOf(host.getId()));
/* 86:74 */      DeviceLogDao ddao = new DeviceLogDao();
/* 87:75 */      ddao.writeLog(apId, "系统", "执行定时重启任务.");
/* 88:   */    }
/* 89:   */  }
/* 90:   */  
/* 91:   */  public int getDelay() {
/* 92:80 */    return 1;
/* 93:   */  }
/* 94:   */  
/* 95:   */  public int getPeriod() {
/* 96:84 */    return 10;
/* 97:   */  }
/* 98:   */  
/* 99:   */  public TimeUnit getTimeUnit() {
/* 100:88 */    return TimeUnit.MINUTES;
/* 101:   */  }
/* 102:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.util.OnlineUserSummaryJob
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */