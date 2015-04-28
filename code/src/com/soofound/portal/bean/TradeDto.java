/*   1:    */package com.soofound.portal.bean;
/*   2:    */
/*   3:    */import com.soofound.framework.jdbc.Persistable;
/*   4:    */
/*   5:    */@com.soofound.framework.annotation.PersistableAnnotation(associate="surfing_trade")
/*   6:    */public final class TradeDto implements Persistable
/*   7:    */{
/*   8:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="order_no", primaryKey=true)
/*   9:    */  private String orderNo;
/*  10:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="branch_id")
/*  11:    */  private String branchId;
/*  12:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="branch", join=true)
/*  13:    */  private String branch;
/*  14:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="summary")
/*  15:    */  private String summary;
/*  16:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="amount")
/*  17:    */  private int amount;
/*  18:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="fee")
/*  19:    */  private float fee;
/*  20:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="pay_way")
/*  21:    */  private int payWay;
/*  22:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="trade_no")
/*  23:    */  private String tradeNo;
/*  24:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="state")
/*  25:    */  private int state;
/*  26:    */  @com.soofound.framework.annotation.PersistableAnnotation(associate="log_time")
/*  27:    */  private String logTime;
/*  28:    */  
/*  29:    */  public String getOrderNo() {
/*  30: 30 */    return this.orderNo;
/*  31:    */  }
/*  32:    */  
/*  33:    */  public void setOrderNo(String orderNo) {
/*  34: 34 */    this.orderNo = orderNo;
/*  35:    */  }
/*  36:    */  
/*  37:    */  public String getBranchId() {
/*  38: 38 */    return this.branchId;
/*  39:    */  }
/*  40:    */  
/*  41:    */  public void setBranchId(String branchId) {
/*  42: 42 */    this.branchId = branchId;
/*  43:    */  }
/*  44:    */  
/*  45:    */  public String getBranch() {
/*  46: 46 */    return this.branch;
/*  47:    */  }
/*  48:    */  
/*  49:    */  public void setBranch(String branch) {
/*  50: 50 */    this.branch = branch;
/*  51:    */  }
/*  52:    */  
/*  53:    */  public String getSummary() {
/*  54: 54 */    return this.summary;
/*  55:    */  }
/*  56:    */  
/*  57:    */  public void setSummary(String summary) {
/*  58: 58 */    this.summary = summary;
/*  59:    */  }
/*  60:    */  
/*  61:    */  public float getFee() {
/*  62: 62 */    return this.fee;
/*  63:    */  }
/*  64:    */  
/*  65:    */  public void setFee(float fee) {
/*  66: 66 */    this.fee = fee;
/*  67:    */  }
/*  68:    */  
/*  69:    */  public int getPayWay() {
/*  70: 70 */    return this.payWay;
/*  71:    */  }
/*  72:    */  
/*  73:    */  public void setPayWay(int payWay) {
/*  74: 74 */    this.payWay = payWay;
/*  75:    */  }
/*  76:    */  
/*  77:    */  public int getState() {
/*  78: 78 */    return this.state;
/*  79:    */  }
/*  80:    */  
/*  81:    */  public void setState(int state) {
/*  82: 82 */    this.state = state;
/*  83:    */  }
/*  84:    */  
/*  85:    */  public String getLogTime() {
/*  86: 86 */    return this.logTime;
/*  87:    */  }
/*  88:    */  
/*  89:    */  public void setLogTime(String logTime) {
/*  90: 90 */    this.logTime = logTime;
/*  91:    */  }
/*  92:    */  
/*  93:    */  public int getAmount() {
/*  94: 94 */    return this.amount;
/*  95:    */  }
/*  96:    */  
/*  97:    */  public void setAmount(int amount) {
/*  98: 98 */    this.amount = amount;
/*  99:    */  }
/* 100:    */  
/* 101:    */  public String getTradeNo() {
/* 102:102 */    return this.tradeNo;
/* 103:    */  }
/* 104:    */  
/* 105:    */  public void setTradeNo(String tradeNo) {
/* 106:106 */    this.tradeNo = tradeNo;
/* 107:    */  }
/* 108:    */  
/* 109:    */  public String getPayWayText() {
/* 110:110 */    if (this.payWay == 1)
/* 111:111 */      return "线下支付";
/* 112:112 */    if (this.payWay == 2)
/* 113:113 */      return "支付宝";
/* 114:114 */    if (this.payWay == 3)
/* 115:115 */      return "网银";
/* 116:116 */    return "";
/* 117:    */  }
/* 118:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.bean.TradeDto
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */