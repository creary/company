/*   1:    */package com.soofound.portal.bean;
/*   2:    */
/*   3:    */import com.soofound.framework.jdbc.Persistable;
/*   4:    */
/*   5:    */@com.soofound.framework.annotation.PersistableAnnotation(associate="surfing_advertise")
/*   6:    */public final class AdvertiseDto implements Persistable
/*   7:    */{
/*   8:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="id", primaryKey=true)
/*   9:    */  private int id;
/*  10:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="title")
/*  11:    */  private String title;
/*  12:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="branch_id")
/*  13:    */  private String branchId;
/*  14:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="branch", join=true)
/*  15:    */  private String branch;
/*  16:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="content")
/*  17:    */  private String content;
/*  18:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="creator")
/*  19:    */  private String creator;
/*  20:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="create_time")
/*  21:    */  private String createTime;
/*  22:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="summary")
/*  23:    */  private String summary;
/*  24:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="cover")
/*  25:    */  private String cover;
/*  26:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="category_id")
/*  27:    */  private int categoryId;
/*  28:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="category", join=true)
/*  29:    */  private String category;
/*  30:    */  
/*  31:    */  public int getId() {
/*  32: 32 */    return this.id;
/*  33:    */  }
/*  34:    */  
/*  35:    */  public void setId(int id) {
/*  36: 36 */    this.id = id;
/*  37:    */  }
/*  38:    */  
/*  39:    */  public String getBranchId() {
/*  40: 40 */    return this.branchId;
/*  41:    */  }
/*  42:    */  
/*  43:    */  public void setBranchId(String branchId) {
/*  44: 44 */    this.branchId = branchId;
/*  45:    */  }
/*  46:    */  
/*  47:    */  public String getCreator() {
/*  48: 48 */    return this.creator;
/*  49:    */  }
/*  50:    */  
/*  51:    */  public void setCreator(String creator) {
/*  52: 52 */    this.creator = creator;
/*  53:    */  }
/*  54:    */  
/*  55:    */  public String getCreateTime() {
/*  56: 56 */    return this.createTime;
/*  57:    */  }
/*  58:    */  
/*  59:    */  public void setCreateTime(String createTime) {
/*  60: 60 */    this.createTime = createTime;
/*  61:    */  }
/*  62:    */  
/*  63:    */  public String getBranch() {
/*  64: 64 */    return this.branch;
/*  65:    */  }
/*  66:    */  
/*  67:    */  public void setBranch(String branch) {
/*  68: 68 */    this.branch = branch;
/*  69:    */  }
/*  70:    */  
/*  71:    */  public String getTitle() {
/*  72: 72 */    return this.title;
/*  73:    */  }
/*  74:    */  
/*  75:    */  public void setTitle(String title) {
/*  76: 76 */    this.title = title;
/*  77:    */  }
/*  78:    */  
/*  79:    */  public String getCover() {
/*  80: 80 */    return this.cover;
/*  81:    */  }
/*  82:    */  
/*  83:    */  public void setCover(String cover) {
/*  84: 84 */    this.cover = cover;
/*  85:    */  }
/*  86:    */  
/*  87:    */  public String getSummary() {
/*  88: 88 */    return this.summary;
/*  89:    */  }
/*  90:    */  
/*  91:    */  public void setSummary(String summary) {
/*  92: 92 */    this.summary = summary;
/*  93:    */  }
/*  94:    */  
/*  95:    */  public int getCategoryId() {
/*  96: 96 */    return this.categoryId;
/*  97:    */  }
/*  98:    */  
/*  99:    */  public void setCategoryId(int categoryId) {
/* 100:100 */    this.categoryId = categoryId;
/* 101:    */  }
/* 102:    */  
/* 103:    */  public String getCategory() {
/* 104:104 */    return this.category;
/* 105:    */  }
/* 106:    */  
/* 107:    */  public void setCategory(String category) {
/* 108:108 */    this.category = category;
/* 109:    */  }
/* 110:    */  
/* 111:    */  public String getContent() {
/* 112:112 */    return this.content;
/* 113:    */  }
/* 114:    */  
/* 115:    */  public void setContent(String content) {
/* 116:116 */    this.content = content;
/* 117:    */  }
/* 118:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.bean.AdvertiseDto
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */