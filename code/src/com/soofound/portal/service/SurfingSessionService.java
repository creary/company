/*   1:    */package com.soofound.portal.service;
/*   2:    */
/*   3:    */import com.soofound.framework.util.DateUtil;
/*   4:    */import com.soofound.framework.util.SysConfigHelper;
/*   5:    */import com.soofound.framework.web.BaseService;
/*   6:    */import com.soofound.portal.bean.SurfingAccount;
/*   7:    */import com.soofound.portal.bean.SurfingSession;
/*   8:    */import com.soofound.portal.dao.SurfingSessionDao;
/*   9:    */import java.io.FileOutputStream;
/*  10:    */import java.util.List;
/*  11:    */import org.apache.poi.hssf.usermodel.HSSFCell;
/*  12:    */import org.apache.poi.hssf.usermodel.HSSFCellStyle;
/*  13:    */import org.apache.poi.hssf.usermodel.HSSFFont;
/*  14:    */import org.apache.poi.hssf.usermodel.HSSFRow;
/*  15:    */import org.apache.poi.hssf.usermodel.HSSFSheet;
/*  16:    */import org.apache.poi.hssf.usermodel.HSSFWorkbook;
/*  17:    */import org.springframework.beans.factory.annotation.Autowired;
/*  18:    */import org.springframework.stereotype.Service;
/*  19:    */
/*  20:    */@Service
/*  21:    */public final class SurfingSessionService
/*  22:    */  extends BaseService<SurfingSessionDao>
/*  23:    */{
/*  24: 24 */  private static String[] EXCEL_HEADS = { " ", "用户名", "IP地址", "MAC", "设备", "开始时间", "结束时间", "在线时间", "上行流量", "下行流量", "场强" };
/*  25:    */  
/*  26:    */  public List<SurfingSession> findByCpe(String cpeId) {
/*  27: 27 */    return ((SurfingSessionDao)this.dao).findByCpe(cpeId);
/*  28:    */  }
/*  29:    */  
/*  30:    */  public List<SurfingSession> getOnlineUsers(String cpeId) {
/*  31: 31 */    return ((SurfingSessionDao)this.dao).getOnlineUsers(cpeId);
/*  32:    */  }
/*  33:    */  
/*  34:    */  public List<SurfingSession> getSurfingSessions(String startTime, String stopTime) {
/*  35: 35 */    return ((SurfingSessionDao)this.dao).getSurfingSessions(startTime, stopTime);
/*  36:    */  }
/*  37:    */  
/*  38:    */  public List<SurfingSession> getSurfingSessions(String[] sessionIds) {
/*  39: 39 */    return ((SurfingSessionDao)this.dao).getSurfingSessions(sessionIds);
/*  40:    */  }
/*  41:    */  
/*  42:    */  public SurfingSession getUserForStayTime(String branchId, String username) {
/*  43: 43 */    return ((SurfingSessionDao)this.dao).getUserForStayTime(branchId, username);
/*  44:    */  }
/*  45:    */  
/*  46:    */  public List<SurfingSession> getUsersForStayTime(String branchId) {
/*  47: 47 */    return ((SurfingSessionDao)this.dao).getUsersForStayTime(branchId);
/*  48:    */  }
/*  49:    */  
/*  50:    */  public void deleteSessions(List<SurfingAccount> sas) {
/*  51: 51 */    ((SurfingSessionDao)this.dao).deleteSessions(sas);
/*  52:    */  }
/*  53:    */  
/*  54:    */  public SurfingSession getLastSurfingSession(String branchId, String mac) {
/*  55: 55 */    return ((SurfingSessionDao)this.dao).getLastSurfingSession(branchId, mac);
/*  56:    */  }
/*  57:    */  
/*  58:    */  public String doExport(String branchId, boolean history) {
/*  59: 59 */    String fileName = "/acs/msite/" + DateUtil.getCurrentTimeAsID() + ".xls";
/*  60: 60 */    fileName = SysConfigHelper.getAttribute("sysPath") + fileName;
/*  61: 61 */    List<SurfingSession> dtos = ((SurfingSessionDao)this.dao).getSurfingSessions(branchId, history);
/*  62:    */    try {
/*  63: 63 */      int r = 0;int c = 0;
/*  64: 64 */      HSSFWorkbook workbook = new HSSFWorkbook();
/*  65: 65 */      HSSFCellStyle hcs = workbook.createCellStyle();
/*  66: 66 */      HSSFSheet sheet = workbook.createSheet();
/*  67: 67 */      HSSFFont font = workbook.createFont();
/*  68: 68 */      font.setFontName("宋体");
/*  69: 69 */      font.setFontHeightInPoints((short)12);
/*  70: 70 */      hcs.setFont(font);
/*  71:    */      
/*  72: 72 */      HSSFRow row0 = sheet.createRow(r++);
/*  73: 73 */      HSSFCell cellH1 = row0.createCell(1);
/*  74: 74 */      cellH1.setCellStyle(hcs);
/*  75: 75 */      cellH1.setCellType(1);
/*  76: 76 */      cellH1.setCellValue("用户上网日志");
/*  77:    */      
/*  78: 78 */      HSSFRow row2 = sheet.createRow(r++);
/*  79: 79 */      for (String head : EXCEL_HEADS) {
/*  80: 80 */        HSSFCell cell = row2.createCell(c++);
/*  81: 81 */        cell.setCellStyle(hcs);
/*  82: 82 */        cell.setCellType(1);
/*  83: 83 */        cell.setCellValue(head);
/*  84:    */      }
/*  85: 85 */      int rc = 1;
/*  86: 86 */      for (SurfingSession dto : dtos) {
/*  87: 87 */        HSSFRow hr = sheet.createRow(r++);
/*  88: 88 */        bornCell(hr, hcs, 0, String.valueOf(rc++));
/*  89: 89 */        bornCell(hr, hcs, 1, dto.getUsername());
/*  90: 90 */        bornCell(hr, hcs, 2, dto.getIpAddress());
/*  91: 91 */        bornCell(hr, hcs, 3, dto.getMac());
/*  92: 92 */        bornCell(hr, hcs, 4, dto.getApName());
/*  93: 93 */        bornCell(hr, hcs, 5, dto.getStartTime());
/*  94: 94 */        bornCell(hr, hcs, 6, dto.getStopTime());
/*  95: 95 */        bornCell(hr, hcs, 7, dto.getUpTimeText());
/*  96: 96 */        bornCell(hr, hcs, 8, dto.getTxText());
/*  97: 97 */        bornCell(hr, hcs, 9, dto.getRxText());
/*  98: 98 */        bornCell(hr, hcs, 10, dto.getSnrText());
/*  99:    */      }
/* 100:100 */      FileOutputStream fos = new FileOutputStream(fileName);
/* 101:101 */      workbook.write(fos);
/* 102:102 */      fos.flush();
/* 103:103 */      fos.close();
/* 104:    */    } catch (Exception e) {
/* 105:105 */      e.printStackTrace();
/* 106:    */    }
/* 107:107 */    return fileName;
/* 108:    */  }
/* 109:    */  
/* 110:    */  public int getLastOnlineUserTotal(int hostId) {
/* 111:111 */    return ((SurfingSessionDao)this.dao).getLastOnlineUserTotal(hostId);
/* 112:    */  }
/* 113:    */  
/* 114:    */  private HSSFCell bornCell(HSSFRow hr, HSSFCellStyle style, int row, String val) {
/* 115:115 */    HSSFCell cell = hr.createCell(row);
/* 116:116 */    cell.setCellType(1);
/* 117:117 */    cell.setCellValue(val);
/* 118:118 */    cell.setCellStyle(style);
/* 119:119 */    return cell;
/* 120:    */  }
/* 121:    */  
/* 122:    */  @Autowired
/* 123:    */  protected void setDao(SurfingSessionDao dao) {
/* 124:124 */    this.dao = dao;
/* 125:    */  }
/* 126:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.service.SurfingSessionService
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */