/*  1:   */package com.soofound.portal.util;
/*  2:   */
/*  3:   */import com.soofound.cpe.bean.UnifiHostDto;
/*  4:   */import com.soofound.cpe.dao.UnifiHostDao;
/*  5:   */import com.soofound.framework.job.ScheduleJob;
/*  6:   */import com.soofound.framework.util.CommonUtil;
/*  7:   */import com.soofound.framework.util.SysConfigHelper;
/*  8:   */import java.io.BufferedReader;
/*  9:   */import java.io.InputStreamReader;
/* 10:   */import java.util.ArrayList;
/* 11:   */import java.util.Iterator;
/* 12:   */import java.util.List;
/* 13:   */import java.util.concurrent.TimeUnit;
/* 14:   */import org.springframework.jdbc.core.JdbcTemplate;
/* 15:   */import org.springframework.util.StringUtils;
/* 16:   */
/* 17:   */public class UnifiOnlineUserJob implements ScheduleJob
/* 18:   */{
/* 19:   */  public void run()
/* 20:   */  {
/* 21:21 */    UnifiHostDao dao = new UnifiHostDao();
/* 22:22 */    List<UnifiHostDto> dtos = dao.findAll();
/* 23:23 */    List<String> lines = new ArrayList();
/* 24:24 */    List<String> sites; Iterator localIterator2; for (Iterator localIterator1 = dtos.iterator(); localIterator1.hasNext(); 
/* 25:   */        
/* 26:26 */        localIterator2.hasNext())
/* 27:   */    {
/* 28:24 */      UnifiHostDto dto = (UnifiHostDto)localIterator1.next();
/* 29:25 */      sites = dto.getSites();
/* 30:26 */      localIterator2 = sites.iterator();continue;String site = (String)localIterator2.next();
/* 31:27 */      handleSite(dto, site, lines);
/* 32:   */    }
/* 33:29 */    if (!lines.isEmpty()) {
/* 34:30 */      List<String> sqls = new ArrayList();
/* 35:31 */      for (String line : lines)
/* 36:32 */        handleLine(line, sqls);
/* 37:33 */      JdbcTemplate jdbc = (JdbcTemplate)SysConfigHelper.getBean("defaultJdbcTemplate");
/* 38:34 */      jdbc.batchUpdate(CommonUtil.toArray(sqls));
/* 39:   */    }
/* 40:   */  }
/* 41:   */  
/* 42:   */  private void handleSite(UnifiHostDto dto, String site, List<String> lines) {
/* 43:39 */    StringBuilder cmd = new StringBuilder(100);
/* 44:40 */    cmd.append("/home/lanzl/unifi/unifi-wifiant -c ").append(dto.getAcip()).append(" -u ").append(dto.getUsername()).append(" -p ");
/* 45:41 */    cmd.append(dto.getPassword()).append(" -P ").append(dto.getPort()).append(" -v v3 -s ").append(site).append(" -L y ");
/* 46:42 */    Process pid = null;
/* 47:43 */    BufferedReader br = null;
/* 48:   */    try {
/* 49:45 */      pid = Runtime.getRuntime().exec(cmd.toString());
/* 50:46 */      if (pid != null) {
/* 51:47 */        br = new BufferedReader(new InputStreamReader(pid.getInputStream()), 1024);
/* 52:48 */        pid.waitFor();
/* 53:   */      }
/* 54:50 */      String line = null;
/* 55:51 */      while ((line = br.readLine()) != null) {
/* 56:52 */        if ((StringUtils.hasText(line)) && (!line.startsWith("NAME"))) {
/* 57:53 */          lines.add(line);
/* 58:   */        }
/* 59:   */      }
/* 60:   */    }
/* 61:   */    catch (Exception localException) {
/* 62:   */      try {
/* 63:59 */        pid.destroy();
/* 64:60 */        br.close();
/* 65:   */      }
/* 66:   */      catch (Exception localException1) {}
/* 67:   */    }
/* 68:   */    finally
/* 69:   */    {
/* 70:   */      try
/* 71:   */      {
/* 72:59 */        pid.destroy();
/* 73:60 */        br.close();
/* 74:   */      }
/* 75:   */      catch (Exception localException2) {}
/* 76:   */    }
/* 77:   */  }
/* 78:   */  
/* 79:   */  private void handleLine(String line, List<String> sqls) {
/* 80:   */    try {
/* 81:68 */      String[] strs = line.split(" ");
/* 82:69 */      List<String> pas = new ArrayList();
/* 83:70 */      for (String str : strs) {
/* 84:71 */        String _str = str.trim();
/* 85:72 */        if (_str.length() > 0)
/* 86:73 */          pas.add(_str);
/* 87:   */      }
/* 88:75 */      String mac = (String)pas.get(1);
/* 89:76 */      int snr = Integer.parseInt((String)pas.get(3)) - 95;
/* 90:77 */      int rx = Integer.parseInt((String)pas.get(4));
/* 91:78 */      int tx = Integer.parseInt((String)pas.get(5));
/* 92:   */      
/* 93:80 */      StringBuilder sqlText = new StringBuilder(200);
/* 94:81 */      sqlText.append("update surfing_session set session_time=TIMESTAMPDIFF(SECOND,start_time,NOW())");
/* 95:82 */      sqlText.append(",input_octets = input_octets + ").append(rx).append(",output_octets = output_octets + ").append(tx);
/* 96:83 */      sqlText.append(",snr=").append(snr).append(" where mac='").append(mac).append("' and state > 0");
/* 97:84 */      sqls.add(sqlText.toString());
/* 98:   */    } catch (Exception ex) {
/* 99:86 */      ex.printStackTrace();
/* 100:   */    }
/* 101:   */  }
/* 102:   */  
/* 103:   */  public int getDelay() {
/* 104:91 */    return 0;
/* 105:   */  }
/* 106:   */  
/* 107:   */  public int getPeriod() {
/* 108:95 */    return 1;
/* 109:   */  }
/* 110:   */  
/* 111:   */  public TimeUnit getTimeUnit() {
/* 112:99 */    return TimeUnit.MINUTES;
/* 113:   */  }
/* 114:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.util.UnifiOnlineUserJob
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */