/*   1:    */package com.soofound.portal.service;
/*   2:    */
/*   3:    */import com.soofound.admin.bean.BranchDao;
/*   4:    */import com.soofound.admin.bean.BranchDto;
/*   5:    */import com.soofound.framework.util.DateUtil;
/*   6:    */import com.soofound.framework.util.SysConfigHelper;
/*   7:    */import com.soofound.framework.web.BaseService;
/*   8:    */import com.soofound.portal.bean.SurfingAccount;
/*   9:    */import com.soofound.portal.custom.CustomFactory;
/*  10:    */import com.soofound.portal.custom.SmsSender;
/*  11:    */import com.soofound.portal.dao.SurfingAccountDao;
/*  12:    */import java.io.FileOutputStream;
/*  13:    */import java.util.List;
/*  14:    */import java.util.Random;
/*  15:    */import org.apache.poi.hssf.usermodel.HSSFCell;
/*  16:    */import org.apache.poi.hssf.usermodel.HSSFCellStyle;
/*  17:    */import org.apache.poi.hssf.usermodel.HSSFFont;
/*  18:    */import org.apache.poi.hssf.usermodel.HSSFRow;
/*  19:    */import org.apache.poi.hssf.usermodel.HSSFSheet;
/*  20:    */import org.apache.poi.hssf.usermodel.HSSFWorkbook;
/*  21:    */import org.springframework.beans.factory.annotation.Autowired;
/*  22:    */import org.springframework.beans.factory.annotation.Qualifier;
/*  23:    */import org.springframework.jdbc.core.JdbcTemplate;
/*  24:    */import org.springframework.stereotype.Service;
/*  25:    */
/*  26:    */@Service
/*  27:    */public final class SurfingAccountService extends BaseService<SurfingAccountDao>
/*  28:    */{
/*  29: 29 */  private static String[] CODES = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
/*  30: 30 */  private static String[] EXCEL_HEADS = { " ", "用户名", "MAC", "类型", "登录次数" };
/*  31:    */  
/*  32:    */  public SurfingAccount getSequenceAccount(String branchId, String phone) {
/*  33: 33 */    String username = null;
/*  34: 34 */    int seqId = ((SurfingAccountDao)this.dao).getNextSequence(branchId);
/*  35: 35 */    if (phone == null) {
/*  36: 36 */      if (seqId < 10) {
/*  37: 37 */        username = "s00" + seqId;
/*  38: 38 */      } else if (seqId < 100) {
/*  39: 39 */        username = "s0" + seqId;
/*  40:    */      } else
/*  41: 41 */        username = "s" + seqId;
/*  42:    */    } else {
/*  43: 43 */      username = phone;
/*  44:    */    }
/*  45: 45 */    SurfingAccount dto = ((SurfingAccountDao)this.dao).findByUsername(branchId, username);
/*  46: 46 */    if (dto == null) {
/*  47: 47 */      dto = new SurfingAccount();
/*  48: 48 */      dto.setUsername(username);
/*  49: 49 */      if (phone == null) {
/*  50: 50 */        dto.setFlag("db");
/*  51:    */      } else
/*  52: 52 */        dto.setFlag("mobile");
/*  53: 53 */      dto.setBranchId(branchId);
/*  54: 54 */      dto.setPassword(getRandomPassword());
/*  55: 55 */      ((SurfingAccountDao)this.dao).save(dto);
/*  56:    */    } else {
/*  57: 57 */      dto.setPassword(getRandomPassword());
/*  58: 58 */      StringBuilder sqlText = new StringBuilder(200);
/*  59: 59 */      sqlText.append("update surfing_account set password='").append(dto.getPassword());
/*  60: 60 */      sqlText.append("',create_time = NOW(),times = 0 where id=").append(dto.getId());
/*  61: 61 */      this.jdbcTemplate.update(sqlText.toString());
/*  62:    */    }
/*  63: 63 */    dto.setOpenId(seqId);
/*  64: 64 */    return dto;
/*  65:    */  }
/*  66:    */  
/*  67:    */  public void sendSMS(SurfingAccount sa) {
/*  68: 68 */    if (this.custom.getSmsSender() != null)
/*  69: 69 */      this.custom.getSmsSender().sendSMS(sa.getBranchId(), sa.getUsername(), getSMSContent(sa));
/*  70:    */  }
/*  71:    */  
/*  72:    */  public SurfingAccount findByAuth(String branchId, String username, String password) {
/*  73: 73 */    return ((SurfingAccountDao)this.dao).findByAuth(branchId, username, password);
/*  74:    */  }
/*  75:    */  
/*  76:    */  public synchronized SurfingAccount findByUsername(String branchId, String username) {
/*  77: 77 */    return ((SurfingAccountDao)this.dao).findByUsername(branchId, username);
/*  78:    */  }
/*  79:    */  
/*  80:    */  public SurfingAccount findByMac(String branchId, String mac) {
/*  81: 81 */    return ((SurfingAccountDao)this.dao).findByMac(branchId, mac);
/*  82:    */  }
/*  83:    */  
/*  84:    */  public SurfingAccount findByOpenId(String openId) {
/*  85: 85 */    return ((SurfingAccountDao)this.dao).findByOpenId(openId);
/*  86:    */  }
/*  87:    */  
/*  88:    */  public void deleteOpenId(String openId) {
/*  89: 89 */    ((SurfingAccountDao)this.dao).deleteOpenId(openId);
/*  90:    */  }
/*  91:    */  
/*  92:    */  private String getSMSContent(SurfingAccount sa) {
/*  93: 93 */    if (sa.getSmsstr() == null) {
/*  94: 94 */      BranchDao bdao = new BranchDao();
/*  95: 95 */      BranchDto bd = bdao.findByID(sa.getBranchId());
/*  96: 96 */      StringBuilder sms = new StringBuilder();
/*  97: 97 */      sms.append("欢迎来到").append(bd.getName()).append(",您的上网密码是").append(sa.getPassword());
/*  98: 98 */      return sms.toString();
/*  99:    */    }
/* 100:100 */    return sa.getSmsstr();
/* 101:    */  }
/* 102:    */  
/* 103:    */  public List<SurfingAccount> getSurfingAccounts(String[] ids) {
/* 104:104 */    return ((SurfingAccountDao)this.dao).getSurfingAccounts(ids);
/* 105:    */  }
/* 106:    */  
/* 107:    */  public List<SurfingAccount> getSurfingAccountsByType(String branchId, String type) {
/* 108:108 */    return ((SurfingAccountDao)this.dao).getSurfingAccountsByType(branchId, type);
/* 109:    */  }
/* 110:    */  
/* 111:    */  public List<SurfingAccount> findByBranch(String branchId, String online) {
/* 112:112 */    return ((SurfingAccountDao)this.dao).findByBranch(branchId, online);
/* 113:    */  }
/* 114:    */  
/* 115:    */  public int getWifiUserCount(String branchId, String tag) {
/* 116:116 */    return ((SurfingAccountDao)this.dao).getWifiUserCount(branchId, tag);
/* 117:    */  }
/* 118:    */  
/* 119:    */  public String getRandomPassword() {
/* 120:120 */    return getRandomPassword(4);
/* 121:    */  }
/* 122:    */  
/* 123:    */  public String getRandomPassword(int len) {
/* 124:124 */    Random random = new Random();
/* 125:125 */    StringBuilder code = new StringBuilder(100);
/* 126:126 */    for (int i = 0; i < len; i++) {
/* 127:127 */      String str = CODES[random.nextInt(CODES.length)];
/* 128:128 */      code.append(str);
/* 129:    */    }
/* 130:130 */    return code.toString();
/* 131:    */  }
/* 132:    */  
/* 133:    */  public void updateNickname(String id, String nickaname) {
/* 134:134 */    StringBuilder sqlText = new StringBuilder(100);
/* 135:135 */    sqlText.append("update surfing_account set nickname='").append(nickaname).append("' where id=").append(id);
/* 136:136 */    this.jdbcTemplate.update(sqlText.toString());
/* 137:    */  }
/* 138:    */  
/* 139:    */  public void updatePassword(String id, String password) {
/* 140:140 */    StringBuilder sqlText = new StringBuilder(100);
/* 141:141 */    sqlText.append("update surfing_account set password='").append(password).append("' where id=").append(id);
/* 142:142 */    this.jdbcTemplate.update(sqlText.toString());
/* 143:    */  }
/* 144:    */  
/* 145:    */  public String getNextWX(String branchId) {
/* 146:146 */    return ((SurfingAccountDao)this.dao).getNextWX(branchId);
/* 147:    */  }
/* 148:    */  
/* 149:    */  public SurfingAccount getWechatShareAccount(String branchId, String mac) {
/* 150:150 */    SurfingAccount sa = ((SurfingAccountDao)this.dao).findByWechat(branchId, mac);
/* 151:151 */    if (sa == null) {
/* 152:152 */      sa = new SurfingAccount();
/* 153:153 */      sa.setUsername(getNextWX(branchId));
/* 154:154 */      sa.setMac(mac);
/* 155:155 */      sa.setBranchId(branchId);
/* 156:156 */      sa.setFlag("wechat");
/* 157:157 */      sa.setOpenId("-");
/* 158:158 */      sa.setPassword("-");
/* 159:159 */      sa.setNickname("");
/* 160:160 */      ((SurfingAccountDao)this.dao).save(sa);
/* 161:    */    } else {
/* 162:162 */      ((SurfingAccountDao)this.dao).updateTimes(sa);
/* 163:    */    }
/* 164:164 */    return sa;
/* 165:    */  }
/* 166:    */  
/* 167:    */  public String doExport(String branchId) {
/* 168:168 */    String fileName = "/acs/msite/" + DateUtil.getCurrentTimeAsID() + ".xls";
/* 169:169 */    List<SurfingAccount> dtos = ((SurfingAccountDao)this.dao).findByBranch(branchId, null);
/* 170:170 */    fileName = SysConfigHelper.getAttribute("sysPath") + fileName;
/* 171:    */    try {
/* 172:172 */      int r = 0;int c = 0;
/* 173:173 */      HSSFWorkbook workbook = new HSSFWorkbook();
/* 174:174 */      HSSFCellStyle hcs = workbook.createCellStyle();
/* 175:175 */      HSSFSheet sheet = workbook.createSheet();
/* 176:176 */      HSSFFont font = workbook.createFont();
/* 177:177 */      font.setFontName("宋体");
/* 178:178 */      font.setFontHeightInPoints((short)12);
/* 179:179 */      hcs.setFont(font);
/* 180:    */      
/* 181:181 */      HSSFRow row0 = sheet.createRow(r++);
/* 182:182 */      HSSFCell cellH1 = row0.createCell(1);
/* 183:183 */      cellH1.setCellStyle(hcs);
/* 184:184 */      cellH1.setCellType(1);
/* 185:185 */      cellH1.setCellValue("上网用户");
/* 186:    */      
/* 187:187 */      HSSFRow row2 = sheet.createRow(r++);
/* 188:188 */      for (String head : EXCEL_HEADS) {
/* 189:189 */        HSSFCell cell = row2.createCell(c++);
/* 190:190 */        cell.setCellStyle(hcs);
/* 191:191 */        cell.setCellType(1);
/* 192:192 */        cell.setCellValue(head);
/* 193:    */      }
/* 194:194 */      int rc = 1;
/* 195:195 */      for (SurfingAccount dto : dtos) {
/* 196:196 */        HSSFRow hr = sheet.createRow(r++);
/* 197:197 */        bornCell(hr, hcs, 0, String.valueOf(rc++));
/* 198:198 */        bornCell(hr, hcs, 1, dto.getUsername());
/* 199:199 */        bornCell(hr, hcs, 2, dto.getMac());
/* 200:200 */        bornCell(hr, hcs, 3, dto.getFlagText());
/* 201:201 */        bornCell(hr, hcs, 4, String.valueOf(dto.getTimes()));
/* 202:    */      }
/* 203:203 */      FileOutputStream fos = new FileOutputStream(fileName);
/* 204:204 */      workbook.write(fos);
/* 205:205 */      fos.flush();
/* 206:206 */      fos.close();
/* 207:    */    } catch (Exception e) {
/* 208:208 */      e.printStackTrace();
/* 209:    */    }
/* 210:210 */    return fileName;
/* 211:    */  }
/* 212:    */  
/* 213:    */  private HSSFCell bornCell(HSSFRow hr, HSSFCellStyle style, int row, String val) {
/* 214:214 */    HSSFCell cell = hr.createCell(row);
/* 215:215 */    cell.setCellType(1);
/* 216:216 */    cell.setCellValue(val);
/* 217:217 */    cell.setCellStyle(style);
/* 218:218 */    return cell;
/* 219:    */  }
/* 220:    */  
/* 221:    */  @Autowired
/* 222:    */  @Qualifier("defaultJdbcTemplate")
/* 223:    */  private JdbcTemplate jdbcTemplate;
/* 224:    */  @Autowired
/* 225:    */  @Qualifier("customFactory")
/* 226:    */  private CustomFactory custom;
/* 227:    */  @Autowired
/* 228:    */  protected void setDao(SurfingAccountDao dao) {
/* 229:229 */    this.dao = dao;
/* 230:    */  }
/* 231:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.service.SurfingAccountService
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */