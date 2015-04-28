/*  1:   */package com.soofound.portal.bean;
/*  2:   */
/*  3:   */import com.alibaba.fastjson.JSON;
/*  4:   */import com.alibaba.fastjson.JSONArray;
/*  5:   */import com.soofound.framework.util.SysConfigHelper;
/*  6:   */import com.soofound.portal.util.WifiDogUtils;
/*  7:   */import java.util.ArrayList;
/*  8:   */import java.util.HashMap;
/*  9:   */import java.util.List;
/* 10:   */import java.util.Map;
/* 11:   */
/* 14:   */public class PortalTemplate
/* 15:   */{
/* 16:   */  private String id;
/* 17:   */  private String name;
/* 18:   */  private String descr;
/* 19:   */  private String path;
/* 20:   */  private String cover;
/* 21:   */  private Map<String, Object> props;
/* 22:   */  private List<PortalTemplatePage> pages;
/* 23:   */  
/* 24:   */  public PortalTemplate(String id)
/* 25:   */  {
/* 26:26 */    this.id = id;
/* 27:27 */    this.path = WifiDogUtils.getTemplatePath(id);
/* 28:28 */    this.props = new HashMap();
/* 29:29 */    this.props.put("currentTplContext", SysConfigHelper.CONTEXT_PATH + this.path);
/* 30:30 */    this.props.put("currentTplRoot", this.path);
/* 31:31 */    this.props.put("moduleRoot", "/msite/admin/module/");
/* 32:32 */    this.props.put("moduleContext", SysConfigHelper.CONTEXT_PATH + "/msite/admin/module/");
/* 33:   */    
/* 34:34 */    Map<String, Object> currentTpl = (Map)JSON.parseObject(WifiDogUtils.getMsiteJsonContent(this.path + "/define/tpl_define.json"), Map.class);
/* 35:35 */    this.props.put("currentTpl", currentTpl);
/* 36:   */    
/* 37:37 */    JSONArray _array = (JSONArray)currentTpl.get("snaps");
/* 38:38 */    this.cover = _array.getString(0);
/* 39:39 */    this.name = ((String)currentTpl.get("name"));
/* 40:40 */    this.descr = ((String)currentTpl.get("desc"));
/* 41:41 */    this.pages = new ArrayList();
/* 42:42 */    JSONArray array = (JSONArray)currentTpl.get("pages");
/* 43:43 */    for (int i = 0; i < array.size(); i++) {
/* 44:44 */      Map<String, Object> vals = (Map)JSON.parseObject(array.get(i).toString(), Map.class);
/* 45:45 */      PortalTemplatePage pp = new PortalTemplatePage();
/* 46:46 */      pp.setId((String)vals.get("id"));
/* 47:47 */      pp.setName((String)vals.get("name"));
/* 48:48 */      if ((vals.get("editable") == null) || (vals.get("editable") == Boolean.FALSE)) {
/* 49:49 */        pp.setEditable(false);
/* 50:   */      } else
/* 51:51 */        pp.setEditable(true);
/* 52:52 */      if ((vals.get("previewable") == null) || (vals.get("previewable") == Boolean.FALSE)) {
/* 53:53 */        pp.setPreviewable(false);
/* 54:   */      } else
/* 55:55 */        pp.setPreviewable(true);
/* 56:56 */      this.pages.add(pp);
/* 57:   */    }
/* 58:   */  }
/* 59:   */  
/* 60:   */  public String getId() {
/* 61:61 */    return this.id;
/* 62:   */  }
/* 63:   */  
/* 64:   */  public String getName() {
/* 65:65 */    return this.name;
/* 66:   */  }
/* 67:   */  
/* 68:   */  public String getDescr() {
/* 69:69 */    return this.descr;
/* 70:   */  }
/* 71:   */  
/* 72:   */  public String getPath() {
/* 73:73 */    return this.path;
/* 74:   */  }
/* 75:   */  
/* 76:   */  public Map<String, Object> getProps() {
/* 77:77 */    return this.props;
/* 78:   */  }
/* 79:   */  
/* 80:   */  public String getCover() {
/* 81:81 */    return this.cover;
/* 82:   */  }
/* 83:   */  
/* 84:   */  public List<PortalTemplatePage> getPages() {
/* 85:85 */    return this.pages;
/* 86:   */  }
/* 87:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.bean.PortalTemplate
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */