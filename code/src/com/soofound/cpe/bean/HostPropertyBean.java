/*   1:    */package com.soofound.cpe.bean;
/*   2:    */
/*   3:    */import com.soofound.framework.jdbc.Persistable;
/*   4:    */import org.springframework.util.StringUtils;
/*   5:    */
/*   6:    */@com.soofound.framework.annotation.PersistableAnnotation(associate="cpe_host_property")
/*   7:    */public final class HostPropertyBean implements Persistable
/*   8:    */{
/*   9:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="id")
/*  10:    */  private int id;
/*  11:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="host_id")
/*  12:    */  private int hostId;
/*  13:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="name")
/*  14:    */  private String name;
/*  15:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="cn_name")
/*  16:    */  private String cnName;
/*  17:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="en_name")
/*  18:    */  private String enName;
/*  19:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="value")
/*  20:    */  private String value;
/*  21:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="tag", join=true)
/*  22:    */  private int tag;
/*  23:    */  private String boxString;
/*  24:    */  
/*  25:    */  public HostPropertyBean() {}
/*  26:    */  
/*  27:    */  public HostPropertyBean(String name, String value)
/*  28:    */  {
/*  29: 29 */    this.name = name;
/*  30: 30 */    this.value = value;
/*  31:    */  }
/*  32:    */  
/*  34:    */  public int getId()
/*  35:    */  {
/*  36: 36 */    return this.id;
/*  37:    */  }
/*  38:    */  
/*  39:    */  public void setId(int id) {
/*  40: 40 */    this.id = id;
/*  41:    */  }
/*  42:    */  
/*  43:    */  public int getHostId() {
/*  44: 44 */    return this.hostId;
/*  45:    */  }
/*  46:    */  
/*  47:    */  public void setHostId(int hostId) {
/*  48: 48 */    this.hostId = hostId;
/*  49:    */  }
/*  50:    */  
/*  51:    */  public String getValue() {
/*  52: 52 */    return this.value;
/*  53:    */  }
/*  54:    */  
/*  55:    */  public void setValue(String value) {
/*  56: 56 */    this.value = value;
/*  57:    */  }
/*  58:    */  
/*  59:    */  public String getName() {
/*  60: 60 */    return this.name;
/*  61:    */  }
/*  62:    */  
/*  63:    */  public void setName(String name) {
/*  64: 64 */    this.name = name;
/*  65:    */  }
/*  66:    */  
/*  67:    */  public int getTag() {
/*  68: 68 */    return this.tag;
/*  69:    */  }
/*  70:    */  
/*  71:    */  public void setTag(int tag) {
/*  72: 72 */    this.tag = tag;
/*  73:    */  }
/*  74:    */  
/*  75:    */  public String getCnName() {
/*  76: 76 */    return this.cnName;
/*  77:    */  }
/*  78:    */  
/*  79:    */  public void setCnName(String cnName) {
/*  80: 80 */    this.cnName = cnName;
/*  81:    */  }
/*  82:    */  
/*  83:    */  public String getEnName() {
/*  84: 84 */    return this.enName;
/*  85:    */  }
/*  86:    */  
/*  87:    */  public void setEnName(String enName) {
/*  88: 88 */    this.enName = enName;
/*  89:    */  }
/*  90:    */  
/*  91:    */  public String getBoxString() {
/*  92: 92 */    return this.boxString;
/*  93:    */  }
/*  94:    */  
/*  95:    */  public void setBoxString(String boxString) {
/*  96: 96 */    this.boxString = boxString;
/*  97:    */  }
/*  98:    */  
/*  99:    */  public String getDisplayValue() {
/* 100:100 */    if ((StringUtils.isEmpty(this.value)) || ("N/A".equals(this.value)))
/* 101:101 */      return "";
/* 102:102 */    return this.value;
/* 103:    */  }
/* 104:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.cpe.bean.HostPropertyBean
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */