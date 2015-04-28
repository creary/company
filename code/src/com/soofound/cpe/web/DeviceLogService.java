/*  1:   */package com.soofound.cpe.web;
/*  2:   */
/*  3:   */import com.soofound.cpe.bean.DeviceLogBean;
/*  4:   */import com.soofound.framework.web.BaseService;
/*  5:   */import org.springframework.beans.factory.annotation.Autowired;
/*  6:   */import org.springframework.stereotype.Service;
/*  7:   */
/*  8:   */@Service
/*  9:   */public final class DeviceLogService extends BaseService<com.soofound.cpe.dao.DeviceLogDao>
/* 10:   */{
/* 11:   */  public DeviceLogBean findLastLog(int hostId, String log)
/* 12:   */  {
/* 13:13 */    return ((com.soofound.cpe.dao.DeviceLogDao)this.dao).findLastLog(hostId, log);
/* 14:   */  }
/* 15:   */  
/* 16:   */  @Autowired
/* 17:   */  protected void setDao(com.soofound.cpe.dao.DeviceLogDao dao) {
/* 18:18 */    this.dao = dao;
/* 19:   */  }
/* 20:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.cpe.web.DeviceLogService
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */