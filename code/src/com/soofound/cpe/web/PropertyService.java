/*  1:   */package com.soofound.cpe.web;
/*  2:   */
/*  3:   */import com.soofound.cpe.bean.PropertyBean;
/*  4:   */import com.soofound.cpe.dao.PropertyDao;
/*  5:   */import com.soofound.framework.web.BaseService;
/*  6:   */import java.util.List;
/*  7:   */import org.springframework.beans.factory.annotation.Autowired;
/*  8:   */import org.springframework.stereotype.Service;
/*  9:   */
/* 10:   */@Service
/* 11:   */public final class PropertyService extends BaseService<PropertyDao>
/* 12:   */{
/* 13:   */  @Autowired
/* 14:   */  protected void setDao(PropertyDao dao)
/* 15:   */  {
/* 16:16 */    this.dao = dao;
/* 17:   */  }
/* 18:   */  
/* 19:   */  public PropertyBean findByName(String name) {
/* 20:20 */    return ((PropertyDao)this.dao).findByName(name);
/* 21:   */  }
/* 22:   */  
/* 23:   */  public List<PropertyBean> findByConfigable(String model, boolean admin) {
/* 24:24 */    return ((PropertyDao)this.dao).findByConfigable(model, admin);
/* 25:   */  }
/* 26:   */  
/* 27:   */  public List<PropertyBean> findProperties(String model, int action) {
/* 28:28 */    return ((PropertyDao)this.dao).findProperties(model, action);
/* 29:   */  }
/* 30:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.cpe.web.PropertyService
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */