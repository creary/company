/*  1:   */package com.soofound.cpe.util;
/*  2:   */
/*  3:   */import com.soofound.admin.bean.GroupDto;
/*  4:   */import com.soofound.framework.util.CommonUtil;
/*  5:   */import java.util.List;
/*  6:   */
/*  7:   */public final class GenericDeviceTree extends DeviceTree
/*  8:   */{
/*  9:   */  public List<GroupDto> getAdminDeviceTree()
/* 10:   */  {
/* 11:11 */    return getDeviceTree("0");
/* 12:   */  }
/* 13:   */  
/* 14:   */  public boolean isAdminable() {
/* 15:15 */    return true;
/* 16:   */  }
/* 17:   */  
/* 18:   */  public String getQuerySQL(java.util.Map<String, String> options)
/* 19:   */  {
/* 20:20 */    StringBuffer sqlText = new StringBuffer(200);
/* 21:21 */    if (CommonUtil.isEmpty((String)options.get("branchId"))) {
/* 22:22 */      sqlText.append("SELECT a.*,c.user_num,d.group_id,TIMESTAMPDIFF(SECOND,a.up_time,a.last_time) AS diffsec,b.name branch FROM cpe_host a LEFT JOIN admin_branch b ON a.branch_id=b.id LEFT JOIN view_ap_online_session c ON a.id=c.cpe_id LEFT JOIN (SELECT a.group_id,a.ap_id FROM membership_ap_group a LEFT JOIN admin_group b ON a.group_id=b.id LEFT JOIN admin_branch c ON b.branch_id=c.id WHERE branch_id='$bid') d ON a.id=d.ap_id".replace("$bid", ""));
/* 23:   */    } else
/* 24:24 */      sqlText.append("SELECT a.*,c.user_num,d.group_id,TIMESTAMPDIFF(SECOND,a.up_time,a.last_time) AS diffsec,b.name branch FROM cpe_host a LEFT JOIN admin_branch b ON a.branch_id=b.id LEFT JOIN view_ap_online_session c ON a.id=c.cpe_id LEFT JOIN (SELECT a.group_id,a.ap_id FROM membership_ap_group a LEFT JOIN admin_group b ON a.group_id=b.id LEFT JOIN admin_branch c ON b.branch_id=c.id WHERE branch_id='$bid') d ON a.id=d.ap_id".replace("$bid", (CharSequence)options.get("branchId")));
/* 25:25 */    sqlText.append(" where 1=1 ");
/* 26:26 */    if (CommonUtil.isEmpty((String)options.get("inputBranch"))) {
/* 27:27 */      if ((!CommonUtil.isEmpty((String)options.get("branchId"))) && (!"0".equals(options.get("branchId"))))
/* 28:28 */        sqlText.append(" and branch_id like '").append((String)options.get("branchId")).append("%'");
/* 29:   */    } else {
/* 30:30 */      sqlText.append(" and b.name like '%").append((String)options.get("inputBranch")).append("%'");
/* 31:   */    }
/* 32:32 */    if (!CommonUtil.isEmpty((String)options.get("serialNumber")))
/* 33:33 */      sqlText.append(" and serial_number like '%").append((String)options.get("serialNumber")).append("%'");
/* 34:34 */    if (!CommonUtil.isEmpty((String)options.get("ssid")))
/* 35:35 */      sqlText.append(" and ssid like '%").append((String)options.get("ssid")).append("%'");
/* 36:36 */    if (!CommonUtil.isEmpty((String)options.get("ipAddress"))) {
/* 37:37 */      sqlText.append(" and ip_address like '%").append((String)options.get("ipAddress")).append("%'");
/* 38:38 */      sqlText.append(" or stun like '%").append((String)options.get("ipAddress")).append("%'");
/* 39:   */    }
/* 40:40 */    if (!CommonUtil.isEmpty((String)options.get("softwareVersion")))
/* 41:41 */      sqlText.append(" and software_version like '%").append((String)options.get("softwareVersion")).append("%'");
/* 42:42 */    if (!CommonUtil.isEmpty((String)options.get("hardwareVersion")))
/* 43:43 */      sqlText.append(" and hardware_version like '%").append((String)options.get("hardwareVersion")).append("%'");
/* 44:44 */    if (!CommonUtil.isEmpty((String)options.get("name")))
/* 45:45 */      sqlText.append(" and a.name like '%").append((String)options.get("name")).append("%'");
/* 46:46 */    if (!CommonUtil.isEmpty((String)options.get("gid"))) {
/* 47:47 */      if ("ungrouped".equals(options.get("gid"))) {
/* 48:48 */        sqlText.append(" and group_id is null");
/* 49:49 */      } else if (!"0".equals(options.get("gid")))
/* 50:50 */        sqlText.append(" and group_id like '").append((String)options.get("gid")).append("%'");
/* 51:   */    }
/* 52:52 */    String sc = (String)options.get("sortColumn");
/* 53:53 */    if ((CommonUtil.isEmpty(sc)) || ("undefined".equals(sc))) {
/* 54:54 */      sqlText.append(" order by online desc,diffsec desc,branch_id");
/* 55:   */    } else {
/* 56:56 */      sqlText.append(" order by ").append((String)this.sortColumns.get(sc));
/* 57:57 */      if ("desc".equals(options.get("order")))
/* 58:58 */        sqlText.append(" desc");
/* 59:   */    }
/* 60:60 */    return sqlText.toString();
/* 61:   */  }
/* 62:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.cpe.util.GenericDeviceTree
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */