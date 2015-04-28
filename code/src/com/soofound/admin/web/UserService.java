/*  1:   */package com.soofound.admin.web;
/*  2:   */
/*  3:   */import com.soofound.admin.bean.UserDao;
/*  4:   */import com.soofound.admin.bean.UserDto;
/*  5:   */import com.soofound.framework.web.BaseService;
/*  6:   */import java.util.List;
/*  7:   */import org.springframework.beans.factory.annotation.Autowired;
/*  8:   */import org.springframework.stereotype.Service;
/*  9:   */
/* 10:   */@Service
/* 11:   */public final class UserService extends BaseService<UserDao>
/* 12:   */{
/* 13:   */  public int updatePerson(UserDto dto)
/* 14:   */  {
/* 15:15 */    return ((UserDao)this.dao).updatePerson(dto);
/* 16:   */  }
/* 17:   */  
/* 18:   */  public UserDto findByUsername(String username, String password) {
/* 19:19 */    return ((UserDao)this.dao).findByUsername(username, password);
/* 20:   */  }
/* 21:   */  
/* 22:   */  public List<UserDto> findByBranch(String branchId) {
/* 23:23 */    return ((UserDao)this.dao).findByBranch(branchId);
/* 24:   */  }
/* 25:   */  
/* 26:   */  @Autowired
/* 27:   */  protected void setDao(UserDao dao) {
/* 28:28 */    this.dao = dao;
/* 29:   */  }
/* 30:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.admin.web.UserService
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */