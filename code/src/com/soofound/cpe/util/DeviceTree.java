/*   1:    */package com.soofound.cpe.util;
/*   2:    */
/*   3:    */import com.soofound.admin.bean.GroupDao;
/*   4:    */import com.soofound.admin.bean.GroupDto;
/*   5:    */import com.soofound.cpe.bean.HostBean;
/*   6:    */import com.soofound.cpe.dao.HostDao;
/*   7:    */import java.util.ArrayList;
/*   8:    */import java.util.HashMap;
/*   9:    */import java.util.List;
/*  10:    */import java.util.Map;
/*  11:    */import java.util.TreeMap;
/*  12:    */import org.springframework.util.StringUtils;
/*  13:    */
/*  18:    */public abstract class DeviceTree
/*  19:    */{
/*  20:    */  protected static final String unGroupedId = "ungrouped";
/*  21:    */  protected static final String SQL_GROUP_HOST = "SELECT a.*,c.user_num,d.group_id,TIMESTAMPDIFF(SECOND,a.up_time,a.last_time) AS diffsec,b.name branch FROM cpe_host a LEFT JOIN admin_branch b ON a.branch_id=b.id LEFT JOIN view_ap_online_session c ON a.id=c.cpe_id LEFT JOIN (SELECT a.group_id,a.ap_id FROM membership_ap_group a LEFT JOIN admin_group b ON a.group_id=b.id LEFT JOIN admin_branch c ON b.branch_id=c.id WHERE branch_id='$bid') d ON a.id=d.ap_id";
/*  22:    */  protected Map<String, String> sortColumns;
/*  23:    */  
/*  24:    */  public DeviceTree()
/*  25:    */  {
/*  26: 26 */    this.sortColumns = new HashMap();
/*  27: 27 */    this.sortColumns.put("branch", "branch_id");
/*  28: 28 */    this.sortColumns.put("userNum", "online desc,user_num");
/*  29: 29 */    this.sortColumns.put("name", "name");
/*  30: 30 */    this.sortColumns.put("serialNumber", "serial_number");
/*  31: 31 */    this.sortColumns.put("productClass", "product_class");
/*  32: 32 */    this.sortColumns.put("softwareVersion", "software_version");
/*  33: 33 */    this.sortColumns.put("hardwareVersion", "hardware_version");
/*  34: 34 */    this.sortColumns.put("description", "last_time");
/*  35: 35 */    this.sortColumns.put("ipAddress", "ip_address");
/*  36: 36 */    this.sortColumns.put("id", "online desc,diffsec");
/*  37:    */  }
/*  38:    */  
/*  39:    */  public List<GroupDto> getDeviceTree(String branchId) {
/*  40: 40 */    List<GroupDto> resultBgds = new ArrayList();
/*  41:    */    try {
/*  42: 42 */      GroupDao gdao = new GroupDao();
/*  43: 43 */      List<GroupDto> bgds = gdao.findByBranch(branchId);
/*  44: 44 */      Map<String, GroupDto> maps = new TreeMap();
/*  45: 45 */      for (GroupDto bgd : bgds) {
/*  46: 46 */        maps.put(bgd.getId(), bgd);
/*  47:    */      }
/*  48: 48 */      GroupDto bgd1 = new GroupDto();
/*  49: 49 */      bgd1.setId("0");
/*  50: 50 */      bgd1.setPid("");
/*  51: 51 */      bgd1.setName("所有设备");
/*  52: 52 */      bgd1.setOpen(true);
/*  53: 53 */      resultBgds.add(bgd1);
/*  54:    */      
/*  55: 55 */      GroupDto bgd2 = new GroupDto();
/*  56: 56 */      bgd2.setId("ungrouped");
/*  57: 57 */      bgd2.setPid("0");
/*  58: 58 */      bgd2.setName("未分组");
/*  59: 59 */      maps.put("ungrouped", bgd2);
/*  60:    */      
/*  61: 61 */      HostDao hdao = new HostDao();
/*  62: 62 */      List<HostBean> hosts = hdao.findByBranch(branchId, null);
/*  63: 63 */      int num = hosts.size();
/*  64: 64 */      int onlineNum = 0;
/*  65: 65 */      for (HostBean host : hosts) {
/*  66: 66 */        String gid = null;
/*  67: 67 */        if (StringUtils.hasText(host.getGroupId())) {
/*  68: 68 */          gid = host.getGroupId();
/*  69:    */        } else
/*  70: 70 */          gid = "ungrouped";
/*  71: 71 */        GroupDto bgd = (GroupDto)maps.get(gid);
/*  72: 72 */        if (bgd == null)
/*  73: 73 */          bgd = (GroupDto)maps.get("ungrouped");
/*  74: 74 */        bgd.setNum(bgd.getNum() + 1);
/*  75: 75 */        increaseParent(maps, gid, false);
/*  76: 76 */        if (host.getOnline() == 1) {
/*  77: 77 */          onlineNum++;
/*  78: 78 */          bgd.setOnlineNum(bgd.getOnlineNum() + 1);
/*  79: 79 */          increaseParent(maps, gid, true);
/*  80:    */        }
/*  81:    */      }
/*  82: 82 */      resultBgds.addAll(maps.values());
/*  83: 83 */      bgd1.setName(bgd1.getName() + " [" + onlineNum + "/" + num + "]");
/*  84: 84 */      for (int i = 1; i < resultBgds.size(); i++) {
/*  85: 85 */        GroupDto bgd = (GroupDto)resultBgds.get(i);
/*  86: 86 */        bgd.setName(bgd.getName() + " [" + bgd.getOnlineNum() + "/" + bgd.getNum() + "]");
/*  87:    */      }
/*  88:    */    } catch (Exception ex) {
/*  89: 89 */      ex.printStackTrace();
/*  90:    */    }
/*  91: 91 */    return resultBgds;
/*  92:    */  }
/*  93:    */  
/*  94:    */  protected void increaseParent(Map<String, GroupDto> maps, String gid, boolean online) {
/*  95: 95 */    if ((!"ungrouped".equals(gid)) && (gid.length() > 4)) {
/*  96: 96 */      String pid = gid;
/*  97:    */      for (;;) {
/*  98: 98 */        pid = pid.substring(0, pid.length() - 4);
/*  99: 99 */        if (pid.endsWith("-")) {
/* 100:100 */          pid = pid.substring(0, 5);
/* 101:    */        }
/* 102:102 */        GroupDto gd = (GroupDto)maps.get(pid);
/* 103:103 */        if (gd == null)
/* 104:    */          break;
/* 105:105 */        if (online) {
/* 106:106 */          gd.setOnlineNum(gd.getOnlineNum() + 1);
/* 107:    */        } else
/* 108:108 */          gd.setNum(gd.getNum() + 1);
/* 109:    */      }
/* 110:    */    }
/* 111:    */  }
/* 112:    */  
/* 113:    */  protected void increaseParent2(Map<String, GroupDto> maps, String gid, boolean online) {
/* 114:114 */    String pid = gid;
/* 115:    */    
/* 116:116 */    while (pid.length() >= 5)
/* 117:    */    {
/* 118:118 */      pid = pid.substring(0, pid.length() - 5);
/* 119:119 */      GroupDto gd = (GroupDto)maps.get(pid);
/* 120:120 */      if (gd == null)
/* 121:    */        break;
/* 122:122 */      if (online) {
/* 123:123 */        gd.setOnlineNum(gd.getOnlineNum() + 1);
/* 124:    */      } else {
/* 125:125 */        gd.setNum(gd.getNum() + 1);
/* 126:    */      }
/* 127:    */    }
/* 128:    */  }
/* 129:    */  
/* 130:    */  public abstract List<GroupDto> getAdminDeviceTree();
/* 131:    */  
/* 132:    */  public abstract boolean isAdminable();
/* 133:    */  
/* 134:    */  public abstract String getQuerySQL(Map<String, String> paramMap);
/* 135:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.cpe.util.DeviceTree
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */