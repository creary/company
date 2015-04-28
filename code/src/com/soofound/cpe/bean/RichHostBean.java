/*  1:   */package com.soofound.cpe.bean;
/*  2:   */
/*  3:   */import com.soofound.framework.annotation.PersistableAnnotation;
/*  4:   */import org.springframework.beans.BeanUtils;
/*  5:   */
/*  8:   */@PersistableAnnotation(associate="cpe_host")
/*  9:   */public final class RichHostBean
/* 10:   */  extends HostBean
/* 11:   */{
/* 12:   */  private static final long serialVersionUID = 2015031618361L;
/* 13:   */  public static final String PIDS = "13,17,18,22,28";
/* 14:   */  private String kickStaRssiLow;
/* 15:   */  private String assocReqRssiThres;
/* 16:   */  private String authRssiThres;
/* 17:   */  private String wirelessTxPower;
/* 18:   */  private String maxStaNum;
/* 19:   */  private String ssidName;
/* 20:   */  private String policyName;
/* 21:   */  private String portalName;
/* 22:   */  
/* 23:   */  public RichHostBean() {}
/* 24:   */  
/* 25:   */  public RichHostBean(HostBean bean)
/* 26:   */  {
/* 27:27 */    BeanUtils.copyProperties(bean, this);
/* 28:   */  }
/* 29:   */  
/* 30:   */  public String getKickStaRssiLow() {
/* 31:31 */    return this.kickStaRssiLow;
/* 32:   */  }
/* 33:   */  
/* 34:   */  public void setKickStaRssiLow(String kickStaRssiLow) {
/* 35:35 */    this.kickStaRssiLow = kickStaRssiLow;
/* 36:   */  }
/* 37:   */  
/* 38:   */  public String getAssocReqRssiThres() {
/* 39:39 */    return this.assocReqRssiThres;
/* 40:   */  }
/* 41:   */  
/* 42:   */  public void setAssocReqRssiThres(String assocReqRssiThres) {
/* 43:43 */    this.assocReqRssiThres = assocReqRssiThres;
/* 44:   */  }
/* 45:   */  
/* 46:   */  public String getAuthRssiThres() {
/* 47:47 */    return this.authRssiThres;
/* 48:   */  }
/* 49:   */  
/* 50:   */  public void setAuthRssiThres(String authRssiThres) {
/* 51:51 */    this.authRssiThres = authRssiThres;
/* 52:   */  }
/* 53:   */  
/* 54:   */  public String getWirelessTxPower() {
/* 55:55 */    return this.wirelessTxPower;
/* 56:   */  }
/* 57:   */  
/* 58:   */  public void setWirelessTxPower(String wirelessTxPower) {
/* 59:59 */    this.wirelessTxPower = wirelessTxPower;
/* 60:   */  }
/* 61:   */  
/* 62:   */  public String getMaxStaNum() {
/* 63:63 */    return this.maxStaNum;
/* 64:   */  }
/* 65:   */  
/* 66:   */  public void setMaxStaNum(String maxStaNum) {
/* 67:67 */    this.maxStaNum = maxStaNum;
/* 68:   */  }
/* 69:   */  
/* 70:   */  public String getSsidName() {
/* 71:71 */    return this.ssidName;
/* 72:   */  }
/* 73:   */  
/* 74:   */  public void setSsidName(String ssidName) {
/* 75:75 */    this.ssidName = ssidName;
/* 76:   */  }
/* 77:   */  
/* 78:   */  public String getPolicyName() {
/* 79:79 */    return this.policyName;
/* 80:   */  }
/* 81:   */  
/* 82:   */  public void setPolicyName(String policyName) {
/* 83:83 */    this.policyName = policyName;
/* 84:   */  }
/* 85:   */  
/* 86:   */  public String getPortalName() {
/* 87:87 */    return this.portalName;
/* 88:   */  }
/* 89:   */  
/* 90:   */  public void setPortalName(String portalName) {
/* 91:91 */    this.portalName = portalName;
/* 92:   */  }
/* 93:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.cpe.bean.RichHostBean
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */