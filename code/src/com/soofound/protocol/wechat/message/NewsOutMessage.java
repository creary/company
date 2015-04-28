/*  1:   */package com.soofound.protocol.wechat.message;
/*  2:   */
/*  3:   */import java.util.ArrayList;
/*  4:   */
/*  5:   */public class NewsOutMessage
/*  6:   */{
/*  7:   */  protected String ToUserName;
/*  8:   */  protected String FromUserName;
/*  9:   */  protected Long CreateTime;
/* 10:10 */  protected int FuncFlag = 0;
/* 11:   */  protected String MsgType;
/* 12:12 */  private Integer ArticleCount = Integer.valueOf(0);
/* 13:   */  private java.util.List<Articles> Articles;
/* 14:   */  
/* 15:   */  public NewsOutMessage() {
/* 16:16 */    this.MsgType = "news";
/* 17:   */  }
/* 18:   */  
/* 19:   */  public String getToUserName() {
/* 20:20 */    return this.ToUserName;
/* 21:   */  }
/* 22:   */  
/* 23:   */  public void setToUserName(String toUserName) {
/* 24:24 */    this.ToUserName = toUserName;
/* 25:   */  }
/* 26:   */  
/* 27:   */  public String getFromUserName() {
/* 28:28 */    return this.FromUserName;
/* 29:   */  }
/* 30:   */  
/* 31:   */  public void setFromUserName(String fromUserName) {
/* 32:32 */    this.FromUserName = fromUserName;
/* 33:   */  }
/* 34:   */  
/* 35:   */  public Long getCreateTime() {
/* 36:36 */    return this.CreateTime;
/* 37:   */  }
/* 38:   */  
/* 39:   */  public void setCreateTime(Long createTime) {
/* 40:40 */    this.CreateTime = createTime;
/* 41:   */  }
/* 42:   */  
/* 43:   */  public int getFuncFlag() {
/* 44:44 */    return this.FuncFlag;
/* 45:   */  }
/* 46:   */  
/* 47:   */  public void setFuncFlag(int funcFlag) {
/* 48:48 */    this.FuncFlag = funcFlag;
/* 49:   */  }
/* 50:   */  
/* 51:   */  public String getMsgType() {
/* 52:52 */    return this.MsgType;
/* 53:   */  }
/* 54:   */  
/* 55:   */  public void setMsgType(String msgType) {
/* 56:56 */    this.MsgType = msgType;
/* 57:   */  }
/* 58:   */  
/* 59:   */  public int getArticleCount() {
/* 60:60 */    return this.ArticleCount.intValue();
/* 61:   */  }
/* 62:   */  
/* 63:   */  public java.util.List<Articles> getArticles() {
/* 64:64 */    return this.Articles;
/* 65:   */  }
/* 66:   */  
/* 67:   */  public void setArticles(java.util.List<Articles> articles) {
/* 68:68 */    if (articles != null) {
/* 69:69 */      if (articles.size() > 10)
/* 70:70 */        articles = new ArrayList(articles.subList(0, 10));
/* 71:71 */      this.ArticleCount = Integer.valueOf(articles.size());
/* 72:   */    }
/* 73:73 */    this.Articles = articles;
/* 74:   */  }
/* 75:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.wechat.message.NewsOutMessage
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */