/*  1:   */package com.soofound.cpe.web;
/*  2:   */
/*  3:   */import com.soofound.cpe.bean.ConfigParamBean;
/*  4:   */import com.soofound.cpe.bean.SoftwareBean;
/*  5:   */import com.soofound.cpe.dao.SoftwareDao;
/*  6:   */import com.soofound.framework.web.BaseService;
/*  7:   */import java.util.List;
/*  8:   */import org.springframework.beans.factory.annotation.Autowired;
/*  9:   */import org.springframework.stereotype.Service;
/* 10:   */
/* 11:   */@Service
/* 12:   */public final class SoftwareService extends BaseService<SoftwareDao>
/* 13:   */{
/* 14:   */  @Autowired
/* 15:   */  protected void setDao(SoftwareDao dao)
/* 16:   */  {
/* 17:17 */    this.dao = dao;
/* 18:   */  }
/* 19:   */  
/* 20:   */  public List<SoftwareBean> findSoftware(int tag, String branchId) {
/* 21:21 */    return ((SoftwareDao)this.dao).findSoftware(tag, branchId);
/* 22:   */  }
/* 23:   */  
/* 24:   */  public SoftwareBean findAccessConfig(String branchId) {
/* 25:25 */    return ((SoftwareDao)this.dao).findConfig(branchId, "access", null);
/* 26:   */  }
/* 27:   */  
/* 28:   */  public SoftwareBean findDefaultConfig(String branchId, String productModel) {
/* 29:29 */    return ((SoftwareDao)this.dao).findConfig(branchId, "default", productModel);
/* 30:   */  }
/* 31:   */  
/* 32:   */  public List<ConfigParamBean> getConfigParams(int cfgId) {
/* 33:33 */    return ((SoftwareDao)this.dao).getConfigParams(cfgId);
/* 34:   */  }
/* 35:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.cpe.web.SoftwareService
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */