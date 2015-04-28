/*  1:   */package com.soofound.protocol.cmcc;
/*  2:   */
/*  3:   */import java.util.List;
/*  4:   */
/*  5: 5 */public class AcknowledgeChallenge extends PortalPacket { public AcknowledgeChallenge() { this.type = 2; }
/*  6:   */  
/*  7:   */  public AcknowledgeChallenge(RequestChallenge rc)
/*  8:   */  {
/*  9: 9 */    this.type = 2;
/* 10:   */    
/* 13:13 */    this.papChap = rc.getPapChap();
/* 14:14 */    if (this.papChap == 0)
/* 15:15 */      this.requestId = PortalUtils.getRequestID();
/* 16:16 */    this.id = rc.getId();
/* 17:17 */    this.serialNo = rc.getSerialNo();
/* 18:18 */    this.rsv = rc.getRsv();
/* 19:   */    
/* 20:20 */    this.userIP = rc.getUserIP();
/* 21:21 */    this.errCode = rc.getErrCode();
/* 22:   */    
/* 23:23 */    ChapChallengeAttribute cca = new ChapChallengeAttribute(rc.getBytes());
/* 24:24 */    addAttribute(cca);
/* 25:   */    
/* 26:26 */    this.attrNum = this.attrs.size();
/* 27:   */  }
/* 28:   */  
/* 29:   */  public String getStep()
/* 30:   */  {
/* 31:31 */    return "Step.9[ AC-->>Portal ]Acknowledge_Challenge";
/* 32:   */  }
/* 33:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.cmcc.AcknowledgeChallenge
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */