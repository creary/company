/*   1:    */package com.soofound.portal.bean;
/*   2:    */
/*   3:    */import com.alibaba.fastjson.JSON;
/*   4:    */import com.alibaba.fastjson.JSONObject;
/*   5:    */import com.soofound.framework.annotation.PersistableAnnotation;
/*   6:    */import com.soofound.framework.jdbc.Persistable;
/*   7:    */import com.soofound.portal.util.WifiDogUtils;
/*   8:    */import java.util.HashMap;
/*   9:    */import java.util.Map;
/*  10:    */
/*  13:    */@PersistableAnnotation(associate="portal_instance")
/*  14:    */public final class PortalInstanceDto
/*  15:    */  implements Persistable
/*  16:    */{
/*  17:    */  @PersistableAnnotation(associate="id", primaryKey=true)
/*  18:    */  private int id;
/*  19:    */  @PersistableAnnotation(associate="name")
/*  20:    */  private String name;
/*  21:    */  @PersistableAnnotation(associate="branch_id")
/*  22:    */  private String branchId;
/*  23:    */  @PersistableAnnotation(associate="tid")
/*  24:    */  private String tid;
/*  25:    */  @PersistableAnnotation(associate="branch", join=true)
/*  26:    */  private String branch;
/*  27:    */  @PersistableAnnotation(associate="template", join=true)
/*  28:    */  private String template;
/*  29:    */  @PersistableAnnotation(associate="cover", join=true)
/*  30:    */  private String cover;
/*  31:    */  @PersistableAnnotation(associate="tag")
/*  32:    */  private int tag;
/*  33:    */  private String path;
/*  34:    */  private Map<String, Map<String, String>> pages;
/*  35:    */  
/*  36:    */  public int getId()
/*  37:    */  {
/*  38: 38 */    return this.id;
/*  39:    */  }
/*  40:    */  
/*  41:    */  public void setId(int id) {
/*  42: 42 */    this.id = id;
/*  43:    */  }
/*  44:    */  
/*  45:    */  public String getBranchId() {
/*  46: 46 */    return this.branchId;
/*  47:    */  }
/*  48:    */  
/*  49:    */  public void setBranchId(String branchId) {
/*  50: 50 */    this.branchId = branchId;
/*  51:    */  }
/*  52:    */  
/*  53:    */  public String getTid() {
/*  54: 54 */    return this.tid;
/*  55:    */  }
/*  56:    */  
/*  57:    */  public void setTid(String tid) {
/*  58: 58 */    this.tid = tid;
/*  59: 59 */    this.path = WifiDogUtils.getTemplatePath(tid);
/*  60:    */  }
/*  61:    */  
/*  62:    */  public String getName() {
/*  63: 63 */    return this.name;
/*  64:    */  }
/*  65:    */  
/*  66:    */  public void setName(String name) {
/*  67: 67 */    this.name = name;
/*  68:    */  }
/*  69:    */  
/*  70:    */  public String getBranch() {
/*  71: 71 */    return this.branch;
/*  72:    */  }
/*  73:    */  
/*  74:    */  public void setBranch(String branch) {
/*  75: 75 */    this.branch = branch;
/*  76:    */  }
/*  77:    */  
/*  78:    */  public String getTemplate() {
/*  79: 79 */    return this.template;
/*  80:    */  }
/*  81:    */  
/*  82:    */  public void setTemplate(String template) {
/*  83: 83 */    this.template = template;
/*  84:    */  }
/*  85:    */  
/*  86:    */  public void setPage(String page, Map<String, String> fields) {
/*  87: 87 */    if (this.pages == null)
/*  88: 88 */      this.pages = new HashMap();
/*  89: 89 */    this.pages.put(page, fields);
/*  90:    */  }
/*  91:    */  
/*  92:    */  public Map<String, String> getPage(String page) {
/*  93: 93 */    if (this.pages == null)
/*  94: 94 */      this.pages = new HashMap();
/*  95: 95 */    return (Map)this.pages.get(page);
/*  96:    */  }
/*  97:    */  
/*  98:    */  public String getCover() {
/*  99: 99 */    return this.cover;
/* 100:    */  }
/* 101:    */  
/* 102:    */  public void setCover(String cover) {
/* 103:103 */    this.cover = cover;
/* 104:    */  }
/* 105:    */  
/* 106:    */  public int getTag() {
/* 107:107 */    return this.tag;
/* 108:    */  }
/* 109:    */  
/* 110:    */  public void setTag(int tag) {
/* 111:111 */    this.tag = tag;
/* 112:    */  }
/* 113:    */  
/* 114:    */  public String getDefaultTag() {
/* 115:115 */    if (this.tag == 1)
/* 116:116 */      return "默认";
/* 117:117 */    return "";
/* 118:    */  }
/* 119:    */  
/* 120:    */  public String getPath() {
/* 121:121 */    return this.path;
/* 122:    */  }
/* 123:    */  
/* 124:    */  public static PortalInstanceDto bornDefaultPortal() {
/* 125:125 */    PortalInstanceDto dto = new PortalInstanceDto();
/* 126:126 */    dto.setTag(1);
/* 127:127 */    dto.setName("default");
/* 128:128 */    dto.setTid("soofound.sp");
/* 129:129 */    dto.setCover("/msite/admin/tpl/soofound/sp/define/img/snap/1.jpg");
/* 130:130 */    String jsonContent = WifiDogUtils.getMsiteJsonContent("/msite/admin/tpl/soofound/sp/define/tpl_define.json");
/* 131:131 */    Map<String, Object> jsondata = (Map)JSON.parseObject(jsonContent, Map.class);
/* 132:132 */    JSONObject jobj = (JSONObject)jsondata.get("fields");
/* 133:133 */    for (String page : jobj.keySet()) {
/* 134:134 */      Map<String, Object> fields = new HashMap();
/* 135:135 */      fields.put("fields", jobj.get(page));
/* 136:136 */      dto.setPage(page, WifiDogUtils.getProperties(JSONObject.toJSONString(fields)));
/* 137:    */    }
/* 138:138 */    return dto;
/* 139:    */  }
/* 140:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.bean.PortalInstanceDto
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */