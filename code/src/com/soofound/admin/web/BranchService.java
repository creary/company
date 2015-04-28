/*  1:   */package com.soofound.admin.web;
/*  2:   */
/*  3:   */import com.soofound.admin.bean.BranchDto;
/*  4:   */import com.soofound.framework.jdbc.Persistable;
/*  5:   */import com.soofound.framework.web.BaseService;
/*  6:   */import java.util.HashMap;
/*  7:   */import java.util.List;
/*  8:   */import java.util.Map;
/*  9:   */import org.springframework.beans.factory.annotation.Autowired;
/* 10:   */import org.springframework.stereotype.Service;
/* 11:   */
/* 12:   */@Service
/* 13:   */public final class BranchService extends BaseService<com.soofound.admin.bean.BranchDao>
/* 14:   */{
/* 15:   */  private Map<String, BranchDto> branchs;
/* 16:   */  
/* 17:   */  public BranchService()
/* 18:   */  {
/* 19:19 */    this.branchs = new HashMap();
/* 20:   */  }
/* 21:   */  
/* 22:   */  public BranchDto getBranch(String branchId) {
/* 23:23 */    BranchDto dto = (BranchDto)this.branchs.get(branchId);
/* 24:24 */    if (dto == null) {
/* 25:25 */      dto = ((com.soofound.admin.bean.BranchDao)this.dao).findByID(branchId);
/* 26:26 */      this.branchs.put(branchId, dto);
/* 27:   */    }
/* 28:28 */    return dto;
/* 29:   */  }
/* 30:   */  
/* 31:   */  public List<BranchDto> findByBranch(String branchId) {
/* 32:32 */    return ((com.soofound.admin.bean.BranchDao)this.dao).findByBranch(branchId);
/* 33:   */  }
/* 34:   */  
/* 35:   */  public BranchDto findByName(String name) {
/* 36:36 */    return ((com.soofound.admin.bean.BranchDao)this.dao).findByName(name);
/* 37:   */  }
/* 38:   */  
/* 39:   */  public BranchDto findByOpenId(String openId) {
/* 40:40 */    return ((com.soofound.admin.bean.BranchDao)this.dao).findByOpenId(openId);
/* 41:   */  }
/* 42:   */  
/* 43:   */  public String getNextID(String parentID) {
/* 44:44 */    return ((com.soofound.admin.bean.BranchDao)this.dao).getNextID(parentID);
/* 45:   */  }
/* 46:   */  
/* 47:   */  public int update(Persistable dto)
/* 48:   */  {
/* 49:49 */    BranchDto branch = (BranchDto)dto;
/* 50:50 */    return ((com.soofound.admin.bean.BranchDao)this.dao).update(branch);
/* 51:   */  }
/* 52:   */  
/* 53:   */  @Autowired
/* 54:   */  protected void setDao(com.soofound.admin.bean.BranchDao dao) {
/* 55:55 */    this.dao = dao;
/* 56:   */  }
/* 57:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.admin.web.BranchService
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */