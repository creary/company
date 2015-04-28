/*  1:   */package com.soofound.acside.web;
/*  2:   */
/*  3:   */import com.soofound.acside.bean.OutsideIPDao;
/*  4:   */import com.soofound.acside.bean.OutsideIPDto;
/*  5:   */import com.soofound.framework.web.BaseService;
/*  6:   */import com.soofound.portal.util.IP138Util;
/*  7:   */import org.springframework.beans.factory.annotation.Autowired;
/*  8:   */import org.springframework.stereotype.Service;
/*  9:   */
/* 10:   */@Service
/* 11:   */public final class OutsideIPService extends BaseService<OutsideIPDao>
/* 12:   */{
/* 13:   */  public OutsideIPDto findByID(String ipAddress)
/* 14:   */  {
/* 15:15 */    OutsideIPDto ipdto = ((OutsideIPDao)this.dao).findByID(ipAddress);
/* 16:16 */    if (ipdto == null) {
/* 17:17 */      String location = IP138Util.getIPLocate(ipAddress);
/* 18:18 */      ipdto = new OutsideIPDto();
/* 19:19 */      ipdto.setIpAddress(ipAddress);
/* 20:20 */      ipdto.setLocation(location);
/* 21:21 */      int loc = location.indexOf("省");
/* 22:22 */      if (loc == -1)
/* 23:23 */        loc = location.indexOf("市");
/* 24:24 */      if (loc > 0)
/* 25:25 */        ipdto.setProvince(location.substring(0, loc));
/* 26:26 */      ((OutsideIPDao)this.dao).save(ipdto);
/* 27:   */    }
/* 28:28 */    return ipdto;
/* 29:   */  }
/* 30:   */  
/* 31:   */  @Autowired
/* 32:   */  protected void setDao(OutsideIPDao dao) {
/* 33:33 */    this.dao = dao;
/* 34:   */  }
/* 35:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.acside.web.OutsideIPService
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */