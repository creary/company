/*  1:   */package com.soofound.operation.web;
/*  2:   */
/*  3:   */import com.soofound.cpe.bean.HostPropertyBean;
/*  4:   */import com.soofound.cpe.dao.RichHostDao;
/*  5:   */import com.soofound.cpe.util.CpeWaker;
/*  6:   */import com.soofound.cpe.web.HostService;
/*  7:   */import com.soofound.framework.web.BaseService;
/*  8:   */import java.util.ArrayList;
/*  9:   */import java.util.List;
/* 10:   */import org.springframework.beans.factory.annotation.Autowired;
/* 11:   */import org.springframework.stereotype.Service;
/* 12:   */
/* 13:   */@Service
/* 14:   */public final class SsidCodeService extends BaseService<com.soofound.operation.bean.SsidCodeDao>
/* 15:   */{
/* 16:   */  @Autowired
/* 17:   */  protected HostService hs;
/* 18:   */  @Autowired
/* 19:   */  protected RichHostDao hdao;
/* 20:   */  
/* 21:   */  public int delete(String[] ids)
/* 22:   */  {
/* 23:23 */    com.soofound.operation.bean.SsidCodeDto def = ((com.soofound.operation.bean.SsidCodeDao)this.dao).findByID("1");
/* 24:24 */    if (def != null) {
/* 25:25 */      for (String id : ids) {
/* 26:26 */        com.soofound.operation.bean.SsidCodeDto scd = ((com.soofound.operation.bean.SsidCodeDao)this.dao).findByID(id);
/* 27:27 */        modifySSIDs(scd.getSsid(), def.getSsid());
/* 28:   */      }
/* 29:   */    }
/* 30:30 */    return ((com.soofound.operation.bean.SsidCodeDao)this.dao).delete(ids);
/* 31:   */  }
/* 32:   */  
/* 33:   */  private void modifySSIDs(String fromSsid, String toSsid) {
/* 34:34 */    com.soofound.cpe.util.CpeActionExecutor cae = new com.soofound.cpe.util.CpeActionExecutor();
/* 35:35 */    CpeWaker cw = new CpeWaker();
/* 36:36 */    List<com.soofound.cpe.bean.RichHostBean> hosts = this.hdao.findBySSID(fromSsid);
/* 37:37 */    for (com.soofound.cpe.bean.RichHostBean host : hosts) {
/* 38:38 */      if (host.getOnline() == 1) {
/* 39:39 */        List<HostPropertyBean> props = new ArrayList();
/* 40:40 */        HostPropertyBean prop = new HostPropertyBean("InternetGatewayDevice.DeviceInfo.wireless_ssid", toSsid);
/* 41:41 */        props.add(prop);
/* 42:42 */        cw.wakeup(String.valueOf(host.getId()));
/* 43:43 */        this.hs.putCommand(host.getId(), cae.getSetParameterValuesString(props));
/* 44:44 */        this.hdao.updateSSID(host.getId(), toSsid);
/* 45:   */      }
/* 46:   */    }
/* 47:   */  }
/* 48:   */  
/* 49:   */  public com.soofound.operation.bean.SsidCodeDto findBySSID(String ssid) {
/* 50:50 */    return ((com.soofound.operation.bean.SsidCodeDao)this.dao).findBySSID(ssid);
/* 51:   */  }
/* 52:   */  
/* 53:   */  public int update(com.soofound.operation.bean.SsidCodeDto dto) {
/* 54:54 */    com.soofound.operation.bean.SsidCodeDto scd = ((com.soofound.operation.bean.SsidCodeDao)this.dao).findByID(dto.getId());
/* 55:55 */    modifySSIDs(scd.getSsid(), dto.getSsid());
/* 56:56 */    return ((com.soofound.operation.bean.SsidCodeDao)this.dao).update(dto);
/* 57:   */  }
/* 58:   */  
/* 62:   */  @Autowired
/* 63:   */  protected void setDao(com.soofound.operation.bean.SsidCodeDao dao)
/* 64:   */  {
/* 65:65 */    this.dao = dao;
/* 66:   */  }
/* 67:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.operation.web.SsidCodeService
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */