/*  1:   */package com.soofound.portal.service;
/*  2:   */
/*  3:   */import com.soofound.framework.web.BaseService;
/*  4:   */import com.soofound.portal.bean.SurfingSmsStat;
/*  5:   */import com.soofound.portal.dao.SurfingSmsDao;
/*  6:   */import java.util.List;
/*  7:   */import org.springframework.beans.factory.annotation.Autowired;
/*  8:   */import org.springframework.stereotype.Service;
/*  9:   */
/* 10:   */@Service
/* 11:   */public final class SurfingSmsService extends BaseService<SurfingSmsDao>
/* 12:   */{
/* 13:   */  public List<SurfingSmsStat> getSurfingSmsStats(String branchId)
/* 14:   */  {
/* 15:15 */    return ((SurfingSmsDao)this.dao).getSurfingSmsStats(branchId);
/* 16:   */  }
/* 17:   */  
/* 18:   */  @Autowired
/* 19:   */  protected void setDao(SurfingSmsDao dao) {
/* 20:20 */    this.dao = dao;
/* 21:   */  }
/* 22:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.service.SurfingSmsService
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */