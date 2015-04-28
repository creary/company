/*   1:    */package com.soofound.acside.web;
/*   2:    */
/*   3:    */import com.soofound.acside.bean.TraceFlowDao;
/*   4:    */import com.soofound.acside.bean.TraceFlowDto;
/*   5:    */import com.soofound.framework.util.DateUtil;
/*   6:    */import com.soofound.framework.util.SysConfigHelper;
/*   7:    */import com.soofound.framework.web.BaseService;
/*   8:    */import com.soofound.portal.dao.SurfingSessionDao;
/*   9:    */import java.io.FileOutputStream;
/*  10:    */import java.io.PrintStream;
/*  11:    */import java.util.ArrayList;
/*  12:    */import java.util.List;
/*  13:    */import java.util.Map;
/*  14:    */import org.apache.poi.hssf.usermodel.HSSFCell;
/*  15:    */import org.apache.poi.hssf.usermodel.HSSFCellStyle;
/*  16:    */import org.apache.poi.hssf.usermodel.HSSFFont;
/*  17:    */import org.apache.poi.hssf.usermodel.HSSFRow;
/*  18:    */import org.apache.poi.hssf.usermodel.HSSFSheet;
/*  19:    */import org.apache.poi.hssf.usermodel.HSSFWorkbook;
/*  20:    */import org.springframework.beans.factory.annotation.Autowired;
/*  21:    */import org.springframework.stereotype.Service;
/*  22:    */
/*  23:    */@Service
/*  24:    */public final class TraceFlowService extends BaseService<TraceFlowDao>
/*  25:    */{
/*  26: 26 */  private static String[] EXCEL_HEADS = { " ", "MAC", "IP地址", "URL", "时间" };
/*  27:    */  
/*  28: 28 */  public List<String> getTraceTables(String mac, String startTime, String endTime) { List<String> aps = this.ssdao.getAPTraces(mac, startTime, endTime);
/*  29: 29 */    List<String> tbls = null;
/*  30: 30 */    if (!aps.isEmpty()) {
/*  31: 31 */      tbls = new ArrayList();
/*  32: 32 */      for (String ap : aps) {
/*  33: 33 */        String tblName = "trace_flow_" + ap.replace(":", "");
/*  34: 34 */        if (((TraceFlowDao)this.dao).hasTable(tblName)) {
/*  35: 35 */          tbls.add(tblName);
/*  36: 36 */          System.out.println(mac + "-->>" + tblName);
/*  37:    */        }
/*  38:    */      }
/*  39:    */    }
/*  40: 40 */    return tbls;
/*  41:    */  }
/*  42:    */  
/*  43:    */  public boolean hasTable(String tableName) {
/*  44: 44 */    return ((TraceFlowDao)this.dao).hasTable(tableName);
/*  45:    */  }
/*  46:    */  
/*  47:    */  public String doExport(Map<String, String> options) {
/*  48: 48 */    String fileName = "/acs/msite/" + DateUtil.getCurrentTimeAsID() + ".xls";
/*  49: 49 */    List<TraceFlowDto> dtos = ((TraceFlowDao)this.dao).getTraceFlow(options);
/*  50: 50 */    fileName = SysConfigHelper.getAttribute("sysPath") + fileName;
/*  51:    */    try {
/*  52: 52 */      int r = 0;int c = 0;
/*  53: 53 */      HSSFWorkbook workbook = new HSSFWorkbook();
/*  54: 54 */      HSSFCellStyle cellStyle = workbook.createCellStyle();
/*  55: 55 */      HSSFSheet sheet = workbook.createSheet();
/*  56: 56 */      HSSFFont font = workbook.createFont();
/*  57: 57 */      font.setFontName("宋体");
/*  58: 58 */      font.setFontHeightInPoints((short)12);
/*  59: 59 */      cellStyle.setFont(font);
/*  60:    */      
/*  61: 61 */      HSSFRow row0 = sheet.createRow(r++);
/*  62: 62 */      HSSFCell cellH1 = row0.createCell(1);
/*  63: 63 */      cellH1.setCellStyle(cellStyle);
/*  64: 64 */      cellH1.setCellType(1);
/*  65: 65 */      cellH1.setCellValue("溯源记录");
/*  66:    */      
/*  67: 67 */      HSSFRow row2 = sheet.createRow(r++);
/*  68: 68 */      for (String head : EXCEL_HEADS) {
/*  69: 69 */        HSSFCell cell = row2.createCell(c++);
/*  70: 70 */        cell.setCellStyle(cellStyle);
/*  71: 71 */        cell.setCellType(1);
/*  72: 72 */        cell.setCellValue(head);
/*  73:    */      }
/*  74: 74 */      int rc = 1;
/*  75: 75 */      for (TraceFlowDto dto : dtos) {
/*  76: 76 */        HSSFRow row = sheet.createRow(r++);
/*  77: 77 */        HSSFCell cell0 = row.createCell(0);
/*  78: 78 */        cell0.setCellType(1);
/*  79: 79 */        cell0.setCellValue(rc++);
/*  80: 80 */        cell0.setCellStyle(cellStyle);
/*  81:    */        
/*  82: 82 */        HSSFCell cell1 = row.createCell(1);
/*  83: 83 */        cell1.setCellType(1);
/*  84: 84 */        cell1.setCellValue(dto.getMac());
/*  85: 85 */        cell1.setCellStyle(cellStyle);
/*  86:    */        
/*  87: 87 */        HSSFCell cell2 = row.createCell(2);
/*  88: 88 */        cell2.setCellType(1);
/*  89: 89 */        cell2.setCellValue(dto.getIp());
/*  90: 90 */        cell2.setCellStyle(cellStyle);
/*  91:    */        
/*  92: 92 */        HSSFCell cell3 = row.createCell(3);
/*  93: 93 */        cell3.setCellType(1);
/*  94: 94 */        cell3.setCellValue(dto.getUrl());
/*  95: 95 */        cell3.setCellStyle(cellStyle);
/*  96:    */        
/*  97: 97 */        HSSFCell cell4 = row.createCell(4);
/*  98: 98 */        cell4.setCellType(1);
/*  99: 99 */        cell4.setCellValue(dto.getLogTime());
/* 100:100 */        cell4.setCellStyle(cellStyle);
/* 101:    */      }
/* 102:102 */      FileOutputStream fos = new FileOutputStream(fileName);
/* 103:103 */      workbook.write(fos);
/* 104:104 */      fos.flush();
/* 105:105 */      fos.close();
/* 106:    */    } catch (Exception e) {
/* 107:107 */      e.printStackTrace();
/* 108:    */    }
/* 109:109 */    return fileName;
/* 110:    */  }
/* 111:    */  
/* 112:    */  @Autowired
/* 113:    */  private SurfingSessionDao ssdao;
/* 114:    */  @Autowired
/* 115:    */  protected void setDao(TraceFlowDao dao) {
/* 116:116 */    this.dao = dao;
/* 117:    */  }
/* 118:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.acside.web.TraceFlowService
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */