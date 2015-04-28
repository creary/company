/*  1:   */package com.soofound.framework.web;
/*  2:   */
/*  3:   */import com.soofound.admin.bean.UserDto;
/*  4:   */import com.soofound.framework.jdbc.Pagination;
/*  5:   */import com.soofound.framework.jdbc.Persistable;
/*  6:   */import java.util.List;
/*  7:   */import java.util.Map;
/*  8:   */
/*  9:   */public abstract class BaseService<DAO extends com.soofound.framework.jdbc.BaseDao>
/* 10:   */{
/* 11:   */  protected DAO dao;
/* 12:   */  protected UserDto currentUser;
/* 13:   */  
/* 14:   */  public List listByPage(int perPage, int currentPage, Map<String, String> options)
/* 15:   */  {
/* 16:16 */    return this.dao.listByPage(perPage, currentPage, options);
/* 17:   */  }
/* 18:   */  
/* 19:   */  public List findAll() {
/* 20:20 */    return this.dao.findAll();
/* 21:   */  }
/* 22:   */  
/* 23:   */  public int save(Persistable dto) {
/* 24:24 */    return this.dao.save(dto);
/* 25:   */  }
/* 26:   */  
/* 27:   */  public Persistable findByID(String id) {
/* 28:28 */    return this.dao.findByID(id);
/* 29:   */  }
/* 30:   */  
/* 31:   */  public int update(Persistable dto) {
/* 32:32 */    return this.dao.update(dto);
/* 33:   */  }
/* 34:   */  
/* 35:   */  public int delete(String[] ids) {
/* 36:36 */    return this.dao.delete(ids);
/* 37:   */  }
/* 38:   */  
/* 39:   */  public int delete(String id) {
/* 40:40 */    return this.dao.delete(id);
/* 41:   */  }
/* 42:   */  
/* 43:   */  public Pagination getPagination() {
/* 44:44 */    return this.dao.getPagination();
/* 45:   */  }
/* 46:   */  
/* 47:   */  public Class getDTOClazz() {
/* 48:48 */    return this.dao.getDTOClazz();
/* 49:   */  }
/* 50:   */  
/* 51:   */  public void setCurrentUser(UserDto currentUser) {
/* 52:52 */    this.currentUser = currentUser;
/* 53:   */  }
/* 54:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.framework.web.BaseService
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */