/*  1:   */package com.soofound.project.ehulian;
/*  2:   */
/*  3:   */import com.soofound.admin.bean.GroupDao;
/*  4:   */import com.soofound.admin.bean.GroupDto;
/*  5:   */import com.soofound.admin.bean.GroupMembershipBean;
/*  6:   */import com.soofound.cpe.bean.HostBean;
/*  7:   */import com.soofound.cpe.dao.HostDao;
/*  8:   */import com.soofound.project.wifibeijing.WifiBeijingDeviceTree;
/*  9:   */import java.util.ArrayList;
/* 10:   */import java.util.HashMap;
/* 11:   */import java.util.List;
/* 12:   */import java.util.Map;
/* 13:   */import org.springframework.stereotype.Component;
/* 14:   */
/* 15:   */@Component
/* 16:   */public final class EhulianDeviceTree
/* 17:   */  extends WifiBeijingDeviceTree
/* 18:   */{
/* 19:   */  public List<GroupDto> getDeviceTree(String branchId)
/* 20:   */  {
/* 21:21 */    List<GroupDto> resultBgds = new ArrayList();
/* 22:   */    try {
/* 23:23 */      GroupDao gdao = new GroupDao();
/* 24:24 */      GroupMembershipBean gmb = gdao.getGroupMemberByBranchId(branchId);
/* 25:25 */      Map<String, GroupDto> gmaps = new HashMap();
/* 26:26 */      if (gmb != null) {
/* 27:27 */        List<GroupDto> gds = gdao.findByGroup(gmb.getGroupId());
/* 28:28 */        resultBgds.addAll(gds);
/* 29:29 */        GroupDto gd0 = (GroupDto)gds.get(0);
/* 30:30 */        gd0.setOpen(true);
/* 31:31 */        for (GroupDto gd : gds)
/* 32:32 */          gmaps.put(gd.getId(), gd);
/* 33:   */      }
/* 34:34 */      HostDao hdao = new HostDao();
/* 35:35 */      List<HostBean> hosts = hdao.findByBranch("0", null);
/* 36:36 */      for (HostBean host : hosts) {
/* 37:37 */        GroupDto bgd = (GroupDto)gmaps.get(host.getGroupId());
/* 38:38 */        if (bgd != null)
/* 39:   */        {
/* 41:41 */          bgd.setNum(bgd.getNum() + 1);
/* 42:42 */          increaseParent(gmaps, host.getGroupId(), false);
/* 43:43 */          if (host.getOnline() == 1) {
/* 44:44 */            bgd.setOnlineNum(bgd.getOnlineNum() + 1);
/* 45:45 */            increaseParent(gmaps, host.getGroupId(), true);
/* 46:   */          }
/* 47:   */        } }
/* 48:48 */      for (int i = 0; i < resultBgds.size(); i++) {
/* 49:49 */        GroupDto bgd = (GroupDto)resultBgds.get(i);
/* 50:50 */        bgd.setName(bgd.getName() + " [" + bgd.getOnlineNum() + "/" + bgd.getNum() + "]");
/* 51:   */      }
/* 52:   */    } catch (Exception ex) {
/* 53:53 */      ex.printStackTrace();
/* 54:   */    }
/* 55:55 */    return resultBgds;
/* 56:   */  }
/* 57:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.project.ehulian.EhulianDeviceTree
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */