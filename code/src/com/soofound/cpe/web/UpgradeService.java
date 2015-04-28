/*  1:   */package com.soofound.cpe.web;
/*  2:   */
/*  3:   */import com.soofound.cpe.bean.SoftwareBean;
/*  4:   */import com.soofound.cpe.dao.SoftwareDao;
/*  5:   */import com.soofound.cpe.util.CpeUtils;
/*  6:   */import com.soofound.framework.web.BaseService;
/*  7:   */import org.springframework.stereotype.Service;
/*  8:   */
/*  9:   */@Service
/* 10:   */public final class UpgradeService extends BaseService<com.soofound.cpe.dao.UpgradeDao>
/* 11:   */{
/* 12:   */  @org.springframework.beans.factory.annotation.Autowired
/* 13:   */  public SoftwareDao softDao;
/* 14:   */  @org.springframework.beans.factory.annotation.Autowired
/* 15:   */  private com.soofound.admin.bean.BranchDao branchDao;
/* 16:   */  
/* 17:   */  public com.soofound.cpe.bean.UpgradeBean findByID(String id)
/* 18:   */  {
/* 19:19 */    com.soofound.cpe.bean.UpgradeBean dto = ((com.soofound.cpe.dao.UpgradeDao)this.dao).findByID(id);
/* 20:20 */    if (dto == null) {
/* 21:21 */      com.soofound.admin.bean.BranchDto bd = this.branchDao.findByID(id);
/* 22:22 */      dto = new com.soofound.cpe.bean.UpgradeBean();
/* 23:23 */      dto.setBranchId(id);
/* 24:24 */      dto.setBranch(bd.getName());
/* 25:   */    }
/* 26:26 */    return dto;
/* 27:   */  }
/* 28:   */  
/* 29:   */  public SoftwareBean findUpgrade(String version, String productModel, String branchId) {
/* 30:30 */    int ver = CpeUtils.getSoftVersionCode(version);
/* 31:31 */    com.soofound.cpe.bean.UpgradeBean dto = ((com.soofound.cpe.dao.UpgradeDao)this.dao).findByID(branchId);
/* 32:32 */    if ((dto == null) || (dto.getFlag() == 0))
/* 33:33 */      return null;
/* 34:34 */    return this.softDao.findLastSoftware(ver, productModel, null);
/* 35:   */  }
/* 36:   */  
/* 40:   */  @org.springframework.beans.factory.annotation.Autowired
/* 41:   */  protected void setDao(com.soofound.cpe.dao.UpgradeDao dao)
/* 42:   */  {
/* 43:43 */    this.dao = dao;
/* 44:   */  }
/* 45:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.cpe.web.UpgradeService
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */