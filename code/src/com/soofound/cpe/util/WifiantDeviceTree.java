/*   1:    */package com.soofound.cpe.util;
/*   2:    */
/*   3:    */import com.soofound.admin.bean.BranchDao;
/*   4:    */import com.soofound.admin.bean.BranchDto;
/*   5:    */import com.soofound.admin.bean.GroupDto;
/*   6:    */import com.soofound.cpe.bean.HostBean;
/*   7:    */import com.soofound.cpe.dao.HostDao;
/*   8:    */import com.soofound.framework.util.CommonUtil;
/*   9:    */import java.util.ArrayList;
/*  10:    */import java.util.List;
/*  11:    */import java.util.TreeMap;
/*  12:    */
/*  13:    */public final class WifiantDeviceTree extends DeviceTree
/*  14:    */{
/*  15:    */  public List<GroupDto> getAdminDeviceTree()
/*  16:    */  {
/*  17: 17 */    List<GroupDto> resultBgds = new ArrayList();
/*  18: 18 */    GroupDto bgd1 = new GroupDto();
/*  19: 19 */    bgd1.setId("0");
/*  20: 20 */    bgd1.setPid("");
/*  21: 21 */    bgd1.setName("所有设备");
/*  22: 22 */    bgd1.setOpen(true);
/*  23: 23 */    resultBgds.add(bgd1);
/*  24:    */    
/*  25: 25 */    BranchDao bdao = new BranchDao();
/*  26: 26 */    List<BranchDto> bds = bdao.findAll();
/*  27: 27 */    java.util.Map<String, GroupDto> maps = new TreeMap();
/*  28: 28 */    for (BranchDto bd : bds) {
/*  29: 29 */      GroupDto gd = new GroupDto();
/*  30: 30 */      gd.setId(bd.getId());
/*  31: 31 */      gd.setPid(bd.getParentId());
/*  32: 32 */      gd.setName(bd.getName());
/*  33: 33 */      maps.put(gd.getId(), gd);
/*  34:    */    }
/*  35:    */    
/*  36: 36 */    HostDao hdao = new HostDao();
/*  37: 37 */    Object hosts = hdao.findAll();
/*  38: 38 */    int num = ((List)hosts).size();
/*  39: 39 */    int onlineNum = 0;
/*  40: 40 */    for (HostBean host : (List)hosts) {
/*  41: 41 */      GroupDto bgd = (GroupDto)maps.get(host.getBranchId());
/*  42: 42 */      if (bgd != null) {
/*  43: 43 */        bgd.setNum(bgd.getNum() + 1);
/*  44: 44 */        increaseParent2(maps, host.getBranchId(), false);
/*  45: 45 */        if (host.getOnline() == 1) {
/*  46: 46 */          onlineNum++;
/*  47: 47 */          bgd.setOnlineNum(bgd.getOnlineNum() + 1);
/*  48: 48 */          increaseParent2(maps, host.getBranchId(), true);
/*  49:    */        }
/*  50:    */      }
/*  51:    */    }
/*  52: 52 */    resultBgds.addAll(maps.values());
/*  53: 53 */    bgd1.setName(bgd1.getName() + " [" + onlineNum + "/" + num + "]");
/*  54: 54 */    for (int i = 1; i < resultBgds.size(); i++) {
/*  55: 55 */      GroupDto bgd = (GroupDto)resultBgds.get(i);
/*  56: 56 */      bgd.setName(bgd.getName() + " [" + bgd.getOnlineNum() + "/" + bgd.getNum() + "]");
/*  57:    */    }
/*  58: 58 */    return resultBgds;
/*  59:    */  }
/*  60:    */  
/*  61:    */  public String getQuerySQL(java.util.Map<String, String> options)
/*  62:    */  {
/*  63: 63 */    StringBuffer sqlText = new StringBuffer(200);
/*  64: 64 */    if (CommonUtil.isEmpty((String)options.get("branchId"))) {
/*  65: 65 */      sqlText.append("SELECT a.*,c.user_num,d.group_id,TIMESTAMPDIFF(SECOND,a.up_time,a.last_time) AS diffsec,b.name branch FROM cpe_host a LEFT JOIN admin_branch b ON a.branch_id=b.id LEFT JOIN view_ap_online_session c ON a.id=c.cpe_id LEFT JOIN (SELECT a.group_id,a.ap_id FROM membership_ap_group a LEFT JOIN admin_group b ON a.group_id=b.id LEFT JOIN admin_branch c ON b.branch_id=c.id WHERE branch_id='$bid') d ON a.id=d.ap_id".replace("$bid", ""));
/*  66:    */    } else
/*  67: 67 */      sqlText.append("SELECT a.*,c.user_num,d.group_id,TIMESTAMPDIFF(SECOND,a.up_time,a.last_time) AS diffsec,b.name branch FROM cpe_host a LEFT JOIN admin_branch b ON a.branch_id=b.id LEFT JOIN view_ap_online_session c ON a.id=c.cpe_id LEFT JOIN (SELECT a.group_id,a.ap_id FROM membership_ap_group a LEFT JOIN admin_group b ON a.group_id=b.id LEFT JOIN admin_branch c ON b.branch_id=c.id WHERE branch_id='$bid') d ON a.id=d.ap_id".replace("$bid", (CharSequence)options.get("branchId")));
/*  68: 68 */    sqlText.append(" where 1=1 ");
/*  69: 69 */    if (CommonUtil.isEmpty((String)options.get("inputBranch"))) {
/*  70: 70 */      if ((!CommonUtil.isEmpty((String)options.get("branchId"))) && (!"0".equals(options.get("branchId"))))
/*  71: 71 */        sqlText.append(" and branch_id like '").append((String)options.get("branchId")).append("%'");
/*  72:    */    } else {
/*  73: 73 */      sqlText.append(" and b.name like '%").append((String)options.get("inputBranch")).append("%'");
/*  74:    */    }
/*  75: 75 */    if (!CommonUtil.isEmpty((String)options.get("serialNumber")))
/*  76: 76 */      sqlText.append(" and serial_number like '%").append((String)options.get("serialNumber")).append("%'");
/*  77: 77 */    if (!CommonUtil.isEmpty((String)options.get("ssid")))
/*  78: 78 */      sqlText.append(" and ssid like '%").append((String)options.get("ssid")).append("%'");
/*  79: 79 */    if (!CommonUtil.isEmpty((String)options.get("ipAddress"))) {
/*  80: 80 */      sqlText.append(" and ip_address like '%").append((String)options.get("ipAddress")).append("%'");
/*  81: 81 */      sqlText.append(" or stun like '%").append((String)options.get("ipAddress")).append("%'");
/*  82:    */    }
/*  83: 83 */    if (!CommonUtil.isEmpty((String)options.get("softwareVersion")))
/*  84: 84 */      sqlText.append(" and software_version like '%").append((String)options.get("softwareVersion")).append("%'");
/*  85: 85 */    if (!CommonUtil.isEmpty((String)options.get("hardwareVersion")))
/*  86: 86 */      sqlText.append(" and hardware_version like '%").append((String)options.get("hardwareVersion")).append("%'");
/*  87: 87 */    if (!CommonUtil.isEmpty((String)options.get("name")))
/*  88: 88 */      sqlText.append(" and a.name like '%").append((String)options.get("name")).append("%'");
/*  89: 89 */    if (!CommonUtil.isEmpty((String)options.get("gid"))) {
/*  90: 90 */      if ("0".equals(options.get("branchId"))) {
/*  91: 91 */        sqlText.append(" and branch_id like '").append((String)options.get("gid")).append("%'");
/*  92:    */      }
/*  93: 93 */      else if ("ungrouped".equals(options.get("gid"))) {
/*  94: 94 */        sqlText.append(" and group_id is null");
/*  95: 95 */      } else if (!"0".equals(options.get("gid"))) {
/*  96: 96 */        sqlText.append(" and group_id like '").append((String)options.get("gid")).append("%'");
/*  97:    */      }
/*  98:    */    }
/*  99: 99 */    String sc = (String)options.get("sortColumn");
/* 100:100 */    if ((CommonUtil.isEmpty(sc)) || ("undefined".equals(sc))) {
/* 101:101 */      sqlText.append(" order by online desc,diffsec desc,branch_id");
/* 102:    */    } else {
/* 103:103 */      sqlText.append(" order by ").append((String)this.sortColumns.get(sc));
/* 104:104 */      if ("desc".equals(options.get("order")))
/* 105:105 */        sqlText.append(" desc");
/* 106:    */    }
/* 107:107 */    return sqlText.toString();
/* 108:    */  }
/* 109:    */  
/* 110:    */  public boolean isAdminable()
/* 111:    */  {
/* 112:112 */    return false;
/* 113:    */  }
/* 114:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.cpe.util.WifiantDeviceTree
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */