/*  1:   */package com.soofound.framework.web;
/*  2:   */
/*  3:   */import com.soofound.framework.jdbc.Persistable;
/*  4:   */import com.soofound.framework.util.CommonUtil;
/*  5:   */import java.util.HashMap;
/*  6:   */import java.util.List;
/*  7:   */import java.util.Map;
/*  8:   */import javax.servlet.http.HttpServletRequest;
/*  9:   */import org.apache.log4j.Logger;
/* 10:   */import org.springframework.ui.ModelMap;
/* 11:   */
/* 13:   */public abstract class BaseAction<SVC extends BaseService>
/* 14:   */  extends GenericAction
/* 15:   */{
/* 16:16 */  protected Logger logger = Logger.getLogger(BaseAction.class);
/* 17:   */  protected SVC service;
/* 18:   */  
/* 19:   */  public Map<String, Object> listByPage(HttpServletRequest request, ModelMap model) {
/* 20:20 */    Map<String, Object> results = new HashMap();
/* 21:21 */    int pp = getPerPageRowTotal(request);
/* 22:22 */    int cp = getCurrentPage(request);
/* 23:23 */    results.put("data", this.service.listByPage(pp, cp, getParas(request)));
/* 24:24 */    results.put("page", this.service.getPagination());
/* 25:25 */    return results;
/* 26:   */  }
/* 27:   */  
/* 28:   */  public Map<String, Object> deleteByIDs(HttpServletRequest request, ModelMap model) {
/* 29:   */    try {
/* 30:30 */      String[] ids = request.getParameterValues("checkbox");
/* 31:31 */      if (!CommonUtil.isEmpty(ids))
/* 32:32 */        this.service.delete(ids);
/* 33:33 */      return super.getSucceedResult("删除成功");
/* 34:   */    } catch (Exception e) {
/* 35:35 */      this.logger.error(e); }
/* 36:36 */    return super.getFailedResult("删除失败");
/* 37:   */  }
/* 38:   */  
/* 39:   */  public Map<String, Object> delete(HttpServletRequest request, ModelMap model)
/* 40:   */  {
/* 41:   */    try {
/* 42:42 */      String id = request.getParameter("radio");
/* 43:43 */      this.service.delete(id);
/* 44:44 */      return super.getSucceedResult("删除成功");
/* 45:   */    } catch (Exception e) {
/* 46:46 */      this.logger.error(e); }
/* 47:47 */    return super.getFailedResult("删除失败");
/* 48:   */  }
/* 49:   */  
/* 50:   */  public void saveDto(HttpServletRequest request, ModelMap model)
/* 51:   */  {
/* 52:52 */    Persistable dto = extractForm(this.service.getDTOClazz(), request);
/* 53:53 */    this.service.save(dto);
/* 54:   */  }
/* 55:   */  
/* 56:   */  public Map<String, Object> save(HttpServletRequest request, ModelMap model) {
/* 57:   */    try {
/* 58:58 */      saveDto(request, model);
/* 59:59 */      return super.getSucceedResult("增加成功");
/* 60:   */    } catch (Exception ex) {
/* 61:61 */      ex.printStackTrace(); }
/* 62:62 */    return super.getFailedResult("增加失败");
/* 63:   */  }
/* 64:   */  
/* 65:   */  public void updateDto(HttpServletRequest request, ModelMap model)
/* 66:   */  {
/* 67:67 */    Persistable dto = extractForm(this.service.getDTOClazz(), request);
/* 68:68 */    this.service.update(dto);
/* 69:   */  }
/* 70:   */  
/* 71:   */  public Map<String, Object> update(HttpServletRequest request, ModelMap model) {
/* 72:   */    try {
/* 73:73 */      updateDto(request, model);
/* 74:74 */      return super.getSucceedResult("更新成功");
/* 75:   */    } catch (Exception ex) {
/* 76:76 */      ex.printStackTrace(); }
/* 77:77 */    return super.getFailedResult("更新失败");
/* 78:   */  }
/* 79:   */  
/* 80:   */  protected Map<String, Object> bornData(List list)
/* 81:   */  {
/* 82:82 */    Map<String, Object> result = new HashMap();
/* 83:83 */    result.put("status", Integer.valueOf(1));
/* 84:84 */    result.put("data", list);
/* 85:85 */    return result;
/* 86:   */  }
/* 87:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.framework.web.BaseAction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */