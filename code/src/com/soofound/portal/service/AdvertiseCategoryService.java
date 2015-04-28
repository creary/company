/*  1:   */package com.soofound.portal.service;
/*  2:   */
/*  3:   */import com.soofound.framework.web.BaseService;
/*  4:   */import com.soofound.portal.bean.AdvertiseCategoryBean;
/*  5:   */import com.soofound.portal.dao.AdvertiseCategoryDao;
/*  6:   */import java.util.HashMap;
/*  7:   */import java.util.List;
/*  8:   */import java.util.Map;
/*  9:   */import org.springframework.beans.factory.annotation.Autowired;
/* 10:   */import org.springframework.stereotype.Service;
/* 11:   */
/* 12:   */@Service
/* 13:   */public final class AdvertiseCategoryService extends BaseService<AdvertiseCategoryDao>
/* 14:   */{
/* 15:   */  private final Map<String, List<AdvertiseCategoryBean>> pcbs;
/* 16:   */  
/* 17:   */  public AdvertiseCategoryService()
/* 18:   */  {
/* 19:19 */    this.pcbs = new HashMap();
/* 20:   */  }
/* 21:   */  
/* 22:   */  public List<AdvertiseCategoryBean> getBranchCategories(String branchId) {
/* 23:23 */    if (!this.pcbs.containsKey(branchId)) {
/* 24:24 */      this.pcbs.put(branchId, ((AdvertiseCategoryDao)this.dao).getBranchCategories(branchId));
/* 25:   */    }
/* 26:26 */    return (List)this.pcbs.get(branchId);
/* 27:   */  }
/* 28:   */  
/* 29:   */  public void removeBranchCategories(String branchId) {
/* 30:30 */    if (this.pcbs.containsKey(branchId))
/* 31:31 */      this.pcbs.remove(branchId);
/* 32:   */  }
/* 33:   */  
/* 34:   */  public AdvertiseCategoryBean findByName(String name) {
/* 35:35 */    return ((AdvertiseCategoryDao)this.dao).findByName(name);
/* 36:   */  }
/* 37:   */  
/* 38:   */  public List<AdvertiseCategoryBean> getADCategories(String branchId) {
/* 39:39 */    return ((AdvertiseCategoryDao)this.dao).getBranchCategories(branchId);
/* 40:   */  }
/* 41:   */  
/* 42:   */  @Autowired
/* 43:   */  protected void setDao(AdvertiseCategoryDao dao) {
/* 44:44 */    this.dao = dao;
/* 45:   */  }
/* 46:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.service.AdvertiseCategoryService
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */