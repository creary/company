/*  1:   */package com.soofound.operation.cache;
/*  2:   */
/*  3:   */import com.soofound.cpe.bean.BssidDto;
/*  4:   */import com.soofound.cpe.bean.HostBean;
/*  5:   */import com.soofound.cpe.dao.BssidDao;
/*  6:   */import com.soofound.framework.util.SysConfigHelper;
/*  7:   */import com.soofound.portal.bean.SurfingAccount;
/*  8:   */import com.soofound.portal.bean.SurfingPolicyDto;
/*  9:   */import com.soofound.portal.dao.SurfingPolicyDao;
/* 10:   */import com.soofound.portal.util.WifiDogUtils;
/* 11:   */import java.io.PrintStream;
/* 12:   */import java.sql.ResultSet;
/* 13:   */import java.sql.SQLException;
/* 14:   */import javax.sql.DataSource;
/* 15:   */import org.springframework.dao.DataAccessException;
/* 16:   */import org.springframework.jdbc.core.JdbcTemplate;
/* 17:   */import org.springframework.jdbc.core.ResultSetExtractor;
/* 18:   */import org.springframework.jdbc.core.support.JdbcDaoSupport;
/* 19:   */
/* 20:   */public final class LookupCacheDaoImpl
/* 21:   */  extends JdbcDaoSupport implements LookupCacheDao
/* 22:   */{
/* 23:   */  private static final String SQL_ONE_HOST = "SELECT * FROM cpe_host WHERE serial_number=? limit 1";
/* 24:   */  
/* 25:   */  public LookupCacheDaoImpl()
/* 26:   */  {
/* 27:27 */    DataSource ds = (DataSource)SysConfigHelper.getBean("defaultDS");
/* 28:28 */    setDataSource(ds);
/* 29:   */  }
/* 30:   */  
/* 31:   */  public HostBean getCacheByApmac(String apmac) {
/* 32:32 */    HostBean host = (HostBean)getJdbcTemplate().query("SELECT * FROM cpe_host WHERE serial_number=? limit 1", new String[] { apmac }, new ResultSetExtractor() {
/* 33:   */      public HostBean extractData(ResultSet rs) throws SQLException, DataAccessException {
/* 34:34 */        HostBean host = null;
/* 35:35 */        if (rs.next()) {
/* 36:36 */          host = new HostBean();
/* 37:37 */          host.setId(rs.getInt("id"));
/* 38:38 */          host.setName(rs.getString("name"));
/* 39:39 */          host.setSerialNumber(rs.getString("serial_number"));
/* 40:40 */          host.setProductClass(rs.getString("product_class"));
/* 41:41 */          host.setSoftwareVersion(rs.getString("software_version"));
/* 42:42 */          host.setHardwareVersion(rs.getString("hardware_version"));
/* 43:43 */          host.setBranchId(rs.getString("branch_id"));
/* 44:44 */          host.setSsid(rs.getString("ssid"));
/* 45:45 */          host.setIpAddress(rs.getString("ip_address"));
/* 46:46 */          host.setStun(rs.getString("stun"));
/* 47:47 */          host.setChannel(rs.getString("channel"));
/* 48:48 */          host.setOnline(rs.getInt("online"));
/* 49:49 */          host.setUpTime(rs.getString("up_time"));
/* 50:50 */          host.setLastTime(rs.getString("last_time"));
/* 51:   */        }
/* 52:52 */        return host;
/* 53:53 */      } } );
/* 54:54 */    return host;
/* 55:   */  }
/* 56:   */  
/* 57:   */  public SurfingPolicyDto getCacheByPolicy(String ssid) {
/* 58:58 */    SurfingPolicyDto spd = null;
/* 59:   */    try {
/* 60:60 */      String[] temp = ssid.split("-");
/* 61:61 */      String ife = WifiDogUtils.getInterface(temp[1]);
/* 62:62 */      BssidDao bdao = new BssidDao();
/* 63:63 */      BssidDto smd = bdao.findByKey(temp[0], ife);
/* 64:64 */      if (smd != null) {
/* 65:65 */        SurfingPolicyDao dao = new SurfingPolicyDao();
/* 66:66 */        spd = dao.findByID(smd.getPolicyId());
/* 67:   */      }
/* 68:   */    } catch (Exception ex) {
/* 69:69 */      ex.printStackTrace();
/* 70:   */    }
/* 71:71 */    if (spd == null) {
/* 72:72 */      System.out.println("Error#Can not find policy of SSID=" + ssid);
/* 73:73 */      spd = SurfingPolicyDto.bornDefaultPolicy();
/* 74:   */    }
/* 75:75 */    return spd;
/* 76:   */  }
/* 77:   */  
/* 78:   */  public SurfingAccount getCacheByAccount(String branchId, String username) {
/* 79:79 */    String sqlText = "SELECT * FROM surfing_account WHERE branch_id='" + branchId + "' AND username='" + username + "'";
/* 80:80 */    SurfingAccount sa = (SurfingAccount)getJdbcTemplate().query(sqlText, new ResultSetExtractor() {
/* 81:   */      public SurfingAccount extractData(ResultSet rs) throws SQLException, DataAccessException {
/* 82:82 */        SurfingAccount sa = null;
/* 83:83 */        if (rs.next()) {
/* 84:84 */          sa = new SurfingAccount();
/* 85:85 */          sa.setUsername(rs.getString("username"));
/* 86:86 */          sa.setOpenId(rs.getString("open_id"));
/* 87:87 */          sa.setUpSpeed(rs.getInt("up_speed"));
/* 88:88 */          sa.setDownSpeed(rs.getInt("down_speed"));
/* 89:   */        }
/* 90:90 */        return sa;
/* 91:91 */      } } );
/* 92:92 */    return sa;
/* 93:   */  }
/* 94:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.operation.cache.LookupCacheDaoImpl
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */