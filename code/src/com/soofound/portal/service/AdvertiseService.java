/*  1:   */package com.soofound.portal.service;
/*  2:   */
/*  3:   */import com.soofound.framework.util.DateUtil;
/*  4:   */import com.soofound.framework.web.BaseService;
/*  5:   */import com.soofound.portal.bean.AdvertiseDto;
/*  6:   */import com.soofound.portal.dao.WechatShareDao;
/*  7:   */import java.io.PrintStream;
/*  8:   */import java.util.HashMap;
/*  9:   */import java.util.List;
/* 10:   */import org.springframework.beans.factory.annotation.Autowired;
/* 11:   */import org.springframework.stereotype.Service;
/* 12:   */
/* 13:   */@Service
/* 14:   */public final class AdvertiseService extends BaseService<com.soofound.portal.dao.AdvertiseDao>
/* 15:   */{
/* 16:   */  private final java.util.Map<String, com.soofound.portal.bean.ShareMac> shareIds;
/* 17:   */  @Autowired
/* 18:   */  private WechatShareDao sdao;
/* 19:   */  
/* 20:   */  public AdvertiseService()
/* 21:   */  {
/* 22:22 */    this.shareIds = new HashMap();
/* 23:   */  }
/* 24:   */  
/* 25:   */  public List<AdvertiseDto> getBranchAdvertises(String branchId) {
/* 26:26 */    return ((com.soofound.portal.dao.AdvertiseDao)this.dao).getArticles(branchId);
/* 27:   */  }
/* 28:   */  
/* 29:   */  public List<AdvertiseDto> getShareArticles(String branchId, String portalId, int start, int count) {
/* 30:30 */    return ((com.soofound.portal.dao.AdvertiseDao)this.dao).getShareArticles(branchId, portalId, start, count);
/* 31:   */  }
/* 32:   */  
/* 33:   */  public List<AdvertiseDto> getArticles(String branchId) {
/* 34:34 */    return ((com.soofound.portal.dao.AdvertiseDao)this.dao).getArticles(branchId);
/* 35:   */  }
/* 36:   */  
/* 37:   */  public List<AdvertiseDto> getArticles(String branchId, String cid, String order, int start, int count) {
/* 38:38 */    return ((com.soofound.portal.dao.AdvertiseDao)this.dao).getArticles(branchId, cid, order, start, count);
/* 39:   */  }
/* 40:   */  
/* 41:   */  public int getArticleTotal(String branchId, String cid) {
/* 42:42 */    return ((com.soofound.portal.dao.AdvertiseDao)this.dao).getArticleTotal(branchId, cid);
/* 43:   */  }
/* 44:   */  
/* 45:   */  public int getShareArticleTotal(String portalId) {
/* 46:46 */    return ((com.soofound.portal.dao.AdvertiseDao)this.dao).getShareArticleTotal(portalId);
/* 47:   */  }
/* 48:   */  
/* 49:   */  public boolean isSharingMac(String mac) {
/* 50:50 */    com.soofound.portal.bean.ShareMac bean = (com.soofound.portal.bean.ShareMac)this.shareIds.get(mac);
/* 51:51 */    if (bean != null) {
/* 52:52 */      long ds = DateUtil.getCurrentLongDateTime() - bean.getRefreshTime();
/* 53:53 */      if (ds < 10000L)
/* 54:54 */        return true;
/* 55:   */    }
/* 56:56 */    System.out.println("mac sharing timeout # " + mac);
/* 57:57 */    return false;
/* 58:   */  }
/* 59:   */  
/* 60:   */  public boolean isSharedMac(String mac) {
/* 61:61 */    com.soofound.portal.bean.ShareMac bean = (com.soofound.portal.bean.ShareMac)this.shareIds.get(mac);
/* 62:62 */    if (bean != null) {
/* 63:63 */      if (bean.isShared()) {
/* 64:64 */        System.out.println(mac + " is share ok...");
/* 65:65 */        this.shareIds.remove(mac);
/* 66:66 */        return true;
/* 67:   */      }
/* 68:68 */      bean.setRefreshTime(DateUtil.getCurrentLongDateTime());
/* 69:69 */      return false;
/* 70:   */    }
/* 71:   */    
/* 72:72 */    System.out.println("isSharedMac,mac is null#" + mac);
/* 73:73 */    return false;
/* 74:   */  }
/* 75:   */  
/* 76:   */  public void addShareMac(String mac)
/* 77:   */  {
/* 78:78 */    com.soofound.portal.bean.ShareMac bean = (com.soofound.portal.bean.ShareMac)this.shareIds.get(mac);
/* 79:79 */    if (bean != null) {
/* 80:80 */      bean.setShared(true);
/* 81:81 */      this.sdao.addShareMac(bean.getId(), mac);
/* 82:   */    }
/* 83:   */  }
/* 84:   */  
/* 85:   */  public void setShareMac(com.soofound.portal.bean.ShareMac bean) {
/* 86:86 */    this.shareIds.put(bean.getMac(), bean);
/* 87:   */  }
/* 88:   */  
/* 89:   */  public int getNextID() {
/* 90:90 */    return ((com.soofound.portal.dao.AdvertiseDao)this.dao).getID("surfing_advertise");
/* 91:   */  }
/* 92:   */  
/* 94:   */  @Autowired
/* 95:   */  protected void setDao(com.soofound.portal.dao.AdvertiseDao dao)
/* 96:   */  {
/* 97:97 */    this.dao = dao;
/* 98:   */  }
/* 99:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.service.AdvertiseService
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */