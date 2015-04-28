/*  1:   */package com.soofound.portal.service;
/*  2:   */
/*  3:   */import com.soofound.framework.util.SysConfigHelper;
/*  4:   */import com.soofound.framework.web.BaseService;
/*  5:   */import com.soofound.portal.dao.TradeDao;
/*  6:   */import org.springframework.beans.factory.annotation.Autowired;
/*  7:   */import org.springframework.jdbc.core.JdbcTemplate;
/*  8:   */import org.springframework.stereotype.Service;
/*  9:   */
/* 10:   */@Service
/* 11:   */public final class TradeService extends BaseService<TradeDao>
/* 12:   */{
/* 13:   */  @Autowired
/* 14:   */  protected void setDao(TradeDao dao)
/* 15:   */  {
/* 16:16 */    this.dao = dao;
/* 17:   */  }
/* 18:   */  
/* 19:   */  public int confirmPay(String orderNo) {
/* 20:20 */    com.soofound.portal.bean.TradeDto dto = (com.soofound.portal.bean.TradeDto)findByID(orderNo);
/* 21:21 */    if (dto.getState() != 1) {
/* 22:22 */      return -1;
/* 23:   */    }
/* 24:24 */    String[] sqls = new String[2];
/* 25:25 */    StringBuilder sqlText1 = new StringBuilder(100);
/* 26:26 */    sqlText1.append("update surfing_trade set state=2 where order_no = '").append(orderNo).append("'");
/* 27:27 */    sqls[0] = sqlText1.toString();
/* 28:   */    
/* 29:29 */    StringBuilder sqlText2 = new StringBuilder(100);
/* 30:30 */    sqlText2.append("update admin_branch_config set sms_remain = sms_remain + ").append(dto.getAmount());
/* 31:31 */    sqlText2.append(" where branch_id='").append(dto.getBranchId()).append("'");
/* 32:32 */    sqls[1] = sqlText2.toString();
/* 33:   */    
/* 34:34 */    JdbcTemplate jt = (JdbcTemplate)SysConfigHelper.getBean("defaultJdbcTemplate");
/* 35:35 */    jt.batchUpdate(sqls);
/* 36:   */    
/* 37:37 */    return 2;
/* 38:   */  }
/* 39:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.service.TradeService
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */