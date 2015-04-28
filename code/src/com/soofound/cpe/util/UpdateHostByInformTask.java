/*  1:   */package com.soofound.cpe.util;
/*  2:   */
/*  3:   */import com.soofound.cpe.bean.HostBean;
/*  4:   */import com.soofound.cpe.bean.PropertyBean;
/*  5:   */import com.soofound.cpe.dao.PropertyDao;
/*  6:   */import com.soofound.cpe.web.HostService;
/*  7:   */import com.soofound.framework.util.SysConfigHelper;
/*  8:   */import com.soofound.protocol.cwmp.Inform;
/*  9:   */import java.io.PrintStream;
/* 10:   */import java.util.List;
/* 11:   */import org.springframework.dao.DuplicateKeyException;
/* 12:   */import org.springframework.jdbc.core.JdbcTemplate;
/* 13:   */import org.springframework.util.StringUtils;
/* 14:   */
/* 15:   */public class UpdateHostByInformTask
/* 16:   */  implements Runnable
/* 17:   */{
/* 18:   */  private HostBean host;
/* 19:   */  private Inform inform;
/* 20:   */  
/* 21:   */  public UpdateHostByInformTask(HostBean host, Inform inform)
/* 22:   */  {
/* 23:23 */    this.host = host;
/* 24:24 */    this.inform = inform;
/* 25:   */  }
/* 26:   */  
/* 27:   */  public void run() {
/* 28:   */    try {
/* 29:29 */      updateHost();
/* 30:   */    } catch (DuplicateKeyException e) {
/* 31:31 */      System.out.println(this.host.getName() + "#UpdateHostByInformTask.error");
/* 32:   */    }
/* 33:   */  }
/* 34:   */  
/* 35:   */  private void updateHost() {
/* 36:36 */    HostService service = (HostService)SysConfigHelper.getBean("hostService");
/* 37:37 */    HostBean host = service.findBySerialNumber(this.inform.getProperty("InternetGatewayDevice.DeviceInfo.SerialNumber"));
/* 38:38 */    if (host == null) {
/* 39:39 */      System.out.println(this.inform.getProperty("InternetGatewayDevice.DeviceInfo.SerialNumber") + "#host doesn't exists.");
/* 40:40 */      return;
/* 41:   */    }
/* 42:42 */    JdbcTemplate jdbc = (JdbcTemplate)SysConfigHelper.getBean("defaultJdbcTemplate");
/* 43:43 */    PropertyDao pdao = new PropertyDao();
/* 44:44 */    List<PropertyBean> pbeans = pdao.findProperties("", 2);
/* 45:45 */    for (PropertyBean pb : pbeans) {
/* 46:46 */      String pvs = this.inform.getProperty(pb.getName());
/* 47:   */      
/* 50:50 */      if ((StringUtils.hasText(pvs)) && (pvs.indexOf("'") == -1)) {
/* 51:51 */        StringBuilder sqlstr = new StringBuilder(200);
/* 52:52 */        sqlstr.append("update cpe_host_property set value ='").append(pvs);
/* 53:53 */        sqlstr.append("' where pid=").append(pb.getId()).append(" and host_id=").append(this.host.getId());
/* 54:54 */        int row = jdbc.update(sqlstr.toString());
/* 55:55 */        if (row == 0) {
/* 56:56 */          sqlstr.setLength(0);
/* 57:57 */          sqlstr.append("insert into cpe_host_property(host_id,pid,value)values(").append(this.host.getId());
/* 58:58 */          sqlstr.append(",").append(pb.getId()).append(",'").append(pvs).append("')");
/* 59:59 */          jdbc.update(sqlstr.toString());
/* 60:   */        }
/* 61:61 */      } else if (pvs.indexOf("'") != -1) {
/* 62:62 */        System.out.println(pb.getName() + "#error value=" + pvs);
/* 63:   */      }
/* 64:   */    }
/* 65:   */  }
/* 66:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.cpe.util.UpdateHostByInformTask
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */