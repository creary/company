/*  1:   */package com.soofound.portal.service;
/*  2:   */
/*  3:   */import com.soofound.framework.web.BaseService;
/*  4:   */import com.soofound.portal.bean.ProvinceDto;
/*  5:   */import com.soofound.portal.dao.ProvinceDao;
/*  6:   */import java.util.List;
/*  7:   */import org.springframework.beans.factory.annotation.Autowired;
/*  8:   */import org.springframework.stereotype.Service;
/*  9:   */
/* 14:   */@Service
/* 15:   */public final class ProvinceService
/* 16:   */  extends BaseService<ProvinceDao>
/* 17:   */{
/* 18:   */  @Autowired
/* 19:   */  protected void setDao(ProvinceDao dao)
/* 20:   */  {
/* 21:21 */    this.dao = dao;
/* 22:   */  }
/* 23:   */  
/* 24:   */  public List<ProvinceDto> findByPid(String pid) {
/* 25:25 */    return ((ProvinceDao)this.dao).findByPid(pid);
/* 26:   */  }
/* 27:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.service.ProvinceService
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */