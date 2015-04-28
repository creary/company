/*  1:   */package com.soofound.portal.service;
/*  2:   */
/*  3:   */import com.soofound.framework.util.SysConfigHelper;
/*  4:   */import com.soofound.framework.web.BaseService;
/*  5:   */import com.soofound.portal.bean.PortalTemplate;
/*  6:   */import com.soofound.portal.bean.PortalTemplateDto;
/*  7:   */import com.soofound.portal.dao.PortalTemplateDao;
/*  8:   */import com.soofound.portal.util.WifiDogUtils;
/*  9:   */import java.io.File;
/* 10:   */import java.io.PrintStream;
/* 11:   */import java.util.ArrayList;
/* 12:   */import java.util.HashMap;
/* 13:   */import java.util.Iterator;
/* 14:   */import java.util.List;
/* 15:   */import java.util.Map;
/* 16:   */import org.springframework.beans.factory.annotation.Autowired;
/* 17:   */import org.springframework.stereotype.Service;
/* 18:   */
/* 23:   */@Service
/* 24:   */public final class PortalTemplateService
/* 25:   */  extends BaseService<PortalTemplateDao>
/* 26:   */{
/* 27:   */  private List<String> modules;
/* 28:   */  private Map<String, Object> moduleValues;
/* 29:   */  private Map<String, PortalTemplate> templates;
/* 30:   */  public static final String MODULE_ROOT = "/msite/admin/module/";
/* 31:   */  public static final String GLOBAL_DEFINE_JSON = "/msite/admin/global/define/global_define.json";
/* 32:   */  
/* 33:   */  public PortalTemplateService()
/* 34:   */  {
/* 35:35 */    this.modules = new ArrayList();
/* 36:36 */    File root = new File(SysConfigHelper.getAttribute("sysPath") + "/msite/admin/module/");
/* 37:37 */    File[] folders = root.listFiles();
/* 38:38 */    for (File folder : folders) {
/* 39:39 */      if (folder.isDirectory()) {
/* 40:40 */        int loc = folder.getPath().lastIndexOf("/");
/* 41:41 */        if (loc == -1)
/* 42:42 */          loc = folder.getPath().lastIndexOf("\\");
/* 43:43 */        String mn = folder.getPath().substring(loc + 1);
/* 44:44 */        if (!mn.equals(".svn"))
/* 45:45 */          this.modules.add(mn);
/* 46:   */      }
/* 47:   */    }
/* 48:48 */    this.templates = new HashMap();
/* 49:49 */    PortalTemplateDao dao = new PortalTemplateDao();
/* 50:50 */    Object dtos = dao.findAll();
/* 51:51 */    for (??? = ((List)dtos).iterator(); ((Iterator)???).hasNext();) { PortalTemplateDto dto = (PortalTemplateDto)((Iterator)???).next();
/* 52:52 */      this.templates.put(dto.getId(), new PortalTemplate(dto.getId()));
/* 53:   */    }
/* 54:54 */    this.moduleValues = new HashMap();
/* 55:55 */    for (??? = this.modules.iterator(); ((Iterator)???).hasNext();) { String module = (String)((Iterator)???).next();
/* 56:56 */      String mpath = null;
/* 57:57 */      if ("crossGlobal".equals(module)) {
/* 58:58 */        mpath = "/msite/admin/global/define/global_define.json";
/* 59:   */      } else
/* 60:60 */        mpath = "/msite/admin/module/" + module + "/define/module_define.json";
/* 61:61 */      this.moduleValues.putAll(WifiDogUtils.getProperties(WifiDogUtils.getMsiteJsonContent(mpath)));
/* 62:   */    }
/* 63:   */  }
/* 64:   */  
/* 65:   */  public void addPortalTemplate(PortalTemplate pt) {
/* 66:66 */    System.out.println("导入模板:" + pt.getName());
/* 67:67 */    this.templates.put(pt.getId(), pt);
/* 68:   */  }
/* 69:   */  
/* 70:   */  public List<PortalTemplate> getPortalTemplates() {
/* 71:71 */    return new ArrayList(this.templates.values());
/* 72:   */  }
/* 73:   */  
/* 74:   */  public int delete(String[] ids)
/* 75:   */  {
/* 76:76 */    for (String id : ids) {
/* 77:77 */      System.out.println("删除模板:" + id);
/* 78:78 */      this.templates.remove(id);
/* 79:   */    }
/* 80:80 */    return ((PortalTemplateDao)this.dao).delete(ids);
/* 81:   */  }
/* 82:   */  
/* 83:   */  public Map<String, Object> getModuleValues() {
/* 84:84 */    return this.moduleValues;
/* 85:   */  }
/* 86:   */  
/* 87:   */  public PortalTemplate getPortalTemplate(String tid) {
/* 88:88 */    return (PortalTemplate)this.templates.get(tid);
/* 89:   */  }
/* 90:   */  
/* 91:   */  @Autowired
/* 92:   */  protected void setDao(PortalTemplateDao dao) {
/* 93:93 */    this.dao = dao;
/* 94:   */  }
/* 95:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.service.PortalTemplateService
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */