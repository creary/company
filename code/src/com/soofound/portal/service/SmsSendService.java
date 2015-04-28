/*  1:   */package com.soofound.portal.service;
/*  2:   */
/*  3:   */import com.soofound.framework.web.BaseService;
/*  4:   */import org.springframework.beans.factory.annotation.Autowired;
/*  5:   */import org.springframework.stereotype.Service;
/*  6:   */
/*  7:   */@Service
/*  8:   */public final class SmsSendService extends BaseService<com.soofound.portal.dao.SmsSendDao>
/*  9:   */{
/* 10:   */  @Autowired
/* 11:   */  protected void setDao(com.soofound.portal.dao.SmsSendDao dao)
/* 12:   */  {
/* 13:13 */    this.dao = dao;
/* 14:   */  }
/* 15:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.service.SmsSendService
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */