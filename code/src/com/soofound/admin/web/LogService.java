/*  1:   */package com.soofound.admin.web;
/*  2:   */
/*  3:   */import com.soofound.framework.web.BaseService;
/*  4:   */import org.springframework.beans.factory.annotation.Autowired;
/*  5:   */import org.springframework.stereotype.Service;
/*  6:   */
/*  7:   */@Service
/*  8:   */public final class LogService extends BaseService<com.soofound.admin.bean.LogDao>
/*  9:   */{
/* 10:   */  @Autowired
/* 11:   */  protected void setDao(com.soofound.admin.bean.LogDao dao)
/* 12:   */  {
/* 13:13 */    this.dao = dao;
/* 14:   */  }
/* 15:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.admin.web.LogService
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */