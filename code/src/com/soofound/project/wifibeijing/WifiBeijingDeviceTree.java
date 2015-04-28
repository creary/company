/*   1:    */package com.soofound.project.wifibeijing;
/*   2:    */
/*   3:    */import com.soofound.admin.bean.BranchDao;
/*   4:    */import com.soofound.admin.bean.BranchDto;
/*   5:    */import com.soofound.admin.bean.GroupDao;
/*   6:    */import com.soofound.admin.bean.GroupDto;
/*   7:    */import com.soofound.admin.bean.GroupMembershipBean;
/*   8:    */import com.soofound.cpe.bean.HostBean;
/*   9:    */import com.soofound.cpe.dao.HostDao;
/*  10:    */import com.soofound.cpe.util.DeviceTree;
/*  11:    */import com.soofound.framework.util.CommonUtil;
/*  12:    */import java.util.ArrayList;
/*  13:    */import java.util.HashMap;
/*  14:    */import java.util.List;
/*  15:    */import java.util.Map;
/*  16:    */import java.util.TreeMap;
/*  17:    */import org.springframework.stereotype.Component;
/*  18:    */import org.springframework.util.StringUtils;
/*  19:    */
/*  20:    */@Component
/*  21:    */public class WifiBeijingDeviceTree
/*  22:    */  extends DeviceTree
/*  23:    */{
/*  24:    */  public List<GroupDto> getDeviceTree(String branchId)
/*  25:    */  {
/*  26: 26 */    List<GroupDto> resultBgds = new ArrayList();
/*  27:    */    try {
/*  28: 28 */      GroupDto bgd1 = new GroupDto();
/*  29: 29 */      bgd1.setId("0");
/*  30: 30 */      bgd1.setPid("");
/*  31: 31 */      bgd1.setOpen(true);
/*  32: 32 */      BranchDao dao = new BranchDao();
/*  33: 33 */      BranchDto dto = dao.findByID(branchId);
/*  34: 34 */      bgd1.setName(dto.getName());
/*  35: 35 */      resultBgds.add(bgd1);
/*  36:    */      
/*  37: 37 */      GroupDao gdao = new GroupDao();
/*  38: 38 */      GroupMembershipBean gmb = gdao.getGroupMemberByBranchId(branchId);
/*  39: 39 */      Map<String, GroupDto> gmaps = new HashMap();
/*  40: 40 */      if (gmb != null) {
/*  41: 41 */        List<GroupDto> gds = gdao.findByGroup(gmb.getGroupId());
/*  42: 42 */        resultBgds.addAll(gds);
/*  43: 43 */        GroupDto gd0 = (GroupDto)gds.get(0);
/*  44: 44 */        gd0.setOpen(true);
/*  45: 45 */        for (GroupDto gd : gds)
/*  46: 46 */          gmaps.put(gd.getId(), gd);
/*  47:    */      }
/*  48: 48 */      HostDao hdao = new HostDao();
/*  49: 49 */      List<HostBean> hosts = hdao.findByBranch("0", null);
/*  50: 50 */      for (HostBean host : hosts) {
/*  51: 51 */        GroupDto bgd = (GroupDto)gmaps.get(host.getGroupId());
/*  52: 52 */        if (bgd != null)
/*  53:    */        {
/*  55: 55 */          bgd.setNum(bgd.getNum() + 1);
/*  56: 56 */          increaseParent(gmaps, host.getGroupId(), false);
/*  57: 57 */          if (host.getOnline() == 1) {
/*  58: 58 */            bgd.setOnlineNum(bgd.getOnlineNum() + 1);
/*  59: 59 */            increaseParent(gmaps, host.getGroupId(), true);
/*  60:    */          }
/*  61:    */        } }
/*  62: 62 */      for (int i = 0; i < resultBgds.size(); i++) {
/*  63: 63 */        GroupDto bgd = (GroupDto)resultBgds.get(i);
/*  64: 64 */        bgd.setName(bgd.getName() + " [" + bgd.getOnlineNum() + "/" + bgd.getNum() + "]");
/*  65:    */      }
/*  66:    */    } catch (Exception ex) {
/*  67: 67 */      ex.printStackTrace();
/*  68:    */    }
/*  69: 69 */    return resultBgds;
/*  70:    */  }
/*  71:    */  
/*  72:    */  public List<GroupDto> getOfflineDeviceTree() {
/*  73: 73 */    List<GroupDto> resultBgds = new ArrayList();
/*  74: 74 */    GroupDao gdao = new GroupDao();
/*  75: 75 */    List<GroupDto> bgds = gdao.findByBranch("0");
/*  76: 76 */    Map<String, GroupDto> maps = new TreeMap();
/*  77: 77 */    for (GroupDto bgd : bgds) {
/*  78: 78 */      maps.put(bgd.getId(), bgd);
/*  79:    */    }
/*  80: 80 */    GroupDto bgd1 = new GroupDto();
/*  81: 81 */    bgd1.setId("0");
/*  82: 82 */    bgd1.setPid("");
/*  83: 83 */    bgd1.setName("所有设备");
/*  84: 84 */    bgd1.setOpen(true);
/*  85: 85 */    resultBgds.add(bgd1);
/*  86:    */    
/*  87: 87 */    GroupDto bgd2 = new GroupDto();
/*  88: 88 */    bgd2.setId("ungrouped");
/*  89: 89 */    bgd2.setPid("0");
/*  90: 90 */    bgd2.setName("未分组");
/*  91: 91 */    maps.put("ungrouped", bgd2);
/*  92:    */    try {
/*  93: 93 */      HostDao hdao = new HostDao();
/*  94: 94 */      List<HostBean> hosts = hdao.findAll();
/*  95: 95 */      int offlineNum = 0;
/*  96: 96 */      for (HostBean host : hosts)
/*  97: 97 */        if (host.getOnline() == 0)
/*  98:    */        {
/* 100:100 */          String gid = null;
/* 101:101 */          if (StringUtils.hasText(host.getGroupId())) {
/* 102:102 */            gid = host.getGroupId();
/* 103:    */          } else
/* 104:104 */            gid = "ungrouped";
/* 105:105 */          GroupDto bgd = (GroupDto)maps.get(gid);
/* 106:106 */          if (bgd == null)
/* 107:107 */            bgd = (GroupDto)maps.get("ungrouped");
/* 108:108 */          bgd.setNum(bgd.getNum() + 1);
/* 109:109 */          increaseParent(maps, gid, false);
/* 110:110 */          offlineNum++;
/* 111:    */        }
/* 112:112 */      resultBgds.addAll(maps.values());
/* 113:113 */      bgd1.setName(bgd1.getName() + " [" + offlineNum + "]");
/* 114:114 */      for (int i = 1; i < resultBgds.size(); i++) {
/* 115:115 */        GroupDto bgd = (GroupDto)resultBgds.get(i);
/* 116:116 */        bgd.setName(bgd.getName() + " [" + bgd.getNum() + "]");
/* 117:    */      }
/* 118:    */    } catch (Exception ex) {
/* 119:119 */      ex.printStackTrace();
/* 120:    */    }
/* 121:121 */    return resultBgds;
/* 122:    */  }
/* 123:    */  
/* 124:    */  public String getQuerySQL(Map<String, String> options) {
/* 125:125 */    options.put("branchId", "0");
/* 126:    */    
/* 127:127 */    StringBuffer sqlText = new StringBuffer(200);
/* 128:128 */    if (CommonUtil.isEmpty((String)options.get("branchId"))) {
/* 129:129 */      sqlText.append("SELECT a.*,c.user_num,d.group_id,TIMESTAMPDIFF(SECOND,a.up_time,a.last_time) AS diffsec,b.name branch FROM cpe_host a LEFT JOIN admin_branch b ON a.branch_id=b.id LEFT JOIN view_ap_online_session c ON a.id=c.cpe_id LEFT JOIN (SELECT a.group_id,a.ap_id FROM membership_ap_group a LEFT JOIN admin_group b ON a.group_id=b.id LEFT JOIN admin_branch c ON b.branch_id=c.id WHERE branch_id='$bid') d ON a.id=d.ap_id".replace("$bid", ""));
/* 130:    */    } else
/* 131:131 */      sqlText.append("SELECT a.*,c.user_num,d.group_id,TIMESTAMPDIFF(SECOND,a.up_time,a.last_time) AS diffsec,b.name branch FROM cpe_host a LEFT JOIN admin_branch b ON a.branch_id=b.id LEFT JOIN view_ap_online_session c ON a.id=c.cpe_id LEFT JOIN (SELECT a.group_id,a.ap_id FROM membership_ap_group a LEFT JOIN admin_group b ON a.group_id=b.id LEFT JOIN admin_branch c ON b.branch_id=c.id WHERE branch_id='$bid') d ON a.id=d.ap_id".replace("$bid", (CharSequence)options.get("branchId")));
/* 132:132 */    sqlText.append(" where 1=1 ");
/* 133:133 */    if ("1".equals(options.get("offline"))) {
/* 134:134 */      sqlText.append(" and online = 0 and used = 1");
/* 135:135 */      if (!CommonUtil.isEmpty((String)options.get("offlineTime")))
/* 136:136 */        sqlText.append(" and last_time>DATE_ADD(Now(),INTERVAL-").append((String)options.get("offlineTime")).append(" hour)");
/* 137:    */    }
/* 138:138 */    if (CommonUtil.isEmpty((String)options.get("inputBranch"))) {
/* 139:139 */      if ((!CommonUtil.isEmpty((String)options.get("branchId"))) && (!"0".equals(options.get("branchId"))))
/* 140:140 */        sqlText.append(" and branch_id like '").append((String)options.get("branchId")).append("%'");
/* 141:    */    } else {
/* 142:142 */      sqlText.append(" and b.name like '%").append((String)options.get("inputBranch")).append("%'");
/* 143:    */    }
/* 144:144 */    if (!CommonUtil.isEmpty((String)options.get("serialNumber")))
/* 145:145 */      sqlText.append(" and serial_number like '%").append((String)options.get("serialNumber")).append("%'");
/* 146:146 */    if (!CommonUtil.isEmpty((String)options.get("ssid")))
/* 147:147 */      sqlText.append(" and ssid like '%").append((String)options.get("ssid")).append("%'");
/* 148:148 */    if (!CommonUtil.isEmpty((String)options.get("ipAddress"))) {
/* 149:149 */      sqlText.append(" and ip_address like '%").append((String)options.get("ipAddress")).append("%'");
/* 150:150 */      sqlText.append(" or stun like '%").append((String)options.get("ipAddress")).append("%'");
/* 151:    */    }
/* 152:152 */    if (!CommonUtil.isEmpty((String)options.get("softwareVersion")))
/* 153:153 */      sqlText.append(" and software_version like '%").append((String)options.get("softwareVersion")).append("%'");
/* 154:154 */    if (!CommonUtil.isEmpty((String)options.get("hardwareVersion")))
/* 155:155 */      sqlText.append(" and hardware_version like '%").append((String)options.get("hardwareVersion")).append("%'");
/* 156:156 */    if (!CommonUtil.isEmpty((String)options.get("name")))
/* 157:157 */      sqlText.append(" and a.name like '%").append((String)options.get("name")).append("%'");
/* 158:158 */    if (!CommonUtil.isEmpty((String)options.get("gid"))) {
/* 159:159 */      if ("ungrouped".equals(options.get("gid"))) {
/* 160:160 */        sqlText.append(" and group_id is null");
/* 161:161 */      } else if (!"0".equals(options.get("gid")))
/* 162:162 */        sqlText.append(" and group_id like '").append((String)options.get("gid")).append("%'");
/* 163:    */    }
/* 164:164 */    String sc = (String)options.get("sortColumn");
/* 165:165 */    if ((CommonUtil.isEmpty(sc)) || ("undefined".equals(sc))) {
/* 166:166 */      sqlText.append(" order by online desc,diffsec desc,branch_id");
/* 167:    */    } else {
/* 168:168 */      sqlText.append(" order by ").append((String)this.sortColumns.get(sc));
/* 169:169 */      if ("desc".equals(options.get("order")))
/* 170:170 */        sqlText.append(" desc");
/* 171:    */    }
/* 172:172 */    return sqlText.toString();
/* 173:    */  }
/* 174:    */  
/* 175:    */  public List<GroupDto> getAdminDeviceTree()
/* 176:    */  {
/* 177:177 */    return super.getDeviceTree("0");
/* 178:    */  }
/* 179:    */  
/* 180:    */  public boolean isAdminable()
/* 181:    */  {
/* 182:182 */    return false;
/* 183:    */  }
/* 184:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.project.wifibeijing.WifiBeijingDeviceTree
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */