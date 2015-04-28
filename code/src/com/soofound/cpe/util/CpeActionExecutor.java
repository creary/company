/*   1:    */package com.soofound.cpe.util;
/*   2:    */
/*   3:    */import com.soofound.cpe.bean.ConfigParamBean;
/*   4:    */import com.soofound.cpe.bean.HostPropertyBean;
/*   5:    */import com.soofound.cpe.bean.PropertyBean;
/*   6:    */import com.soofound.cpe.bean.SoftwareBean;
/*   7:    */import com.soofound.cpe.dao.PropertyDao;
/*   8:    */import com.soofound.cpe.dao.SoftwareDao;
/*   9:    */import com.soofound.framework.util.CommonUtil;
/*  10:    */import com.soofound.framework.util.SysConfigHelper;
/*  11:    */import com.soofound.protocol.cwmp.Apply;
/*  12:    */import com.soofound.protocol.cwmp.Download;
/*  13:    */import com.soofound.protocol.cwmp.FactoryReset;
/*  14:    */import com.soofound.protocol.cwmp.GetParameterValues;
/*  15:    */import com.soofound.protocol.cwmp.Message;
/*  16:    */import com.soofound.protocol.cwmp.Reboot;
/*  17:    */import com.soofound.protocol.cwmp.SetParameterValues;
/*  18:    */import java.io.ByteArrayOutputStream;
/*  19:    */import java.io.File;
/*  20:    */import java.io.PrintStream;
/*  21:    */import java.util.List;
/*  22:    */import org.apache.log4j.Logger;
/*  23:    */import org.springframework.util.StringUtils;
/*  24:    */
/*  25:    */public class CpeActionExecutor
/*  26:    */{
/*  27: 27 */  private static Logger loger = Logger.getLogger(CpeActionExecutor.class);
/*  28:    */  private static final String CHARSET_ISO = "ISO-8859-1";
/*  29: 29 */  private static String KEY_SOFTWARE = "dlSoftware";
/*  30:    */  
/*  31:    */  public String getGetParameterValuesString(String model, int action) {
/*  32:    */    try {
/*  33: 33 */      PropertyDao dao = new PropertyDao();
/*  34: 34 */      List<PropertyBean> pbs = dao.findProperties(model, action);
/*  35: 35 */      String[] pas = new String[pbs.size()];
/*  36: 36 */      for (int i = 0; i < pbs.size(); i++) {
/*  37: 37 */        pas[i] = ((PropertyBean)pbs.get(i)).getName();
/*  38:    */      }
/*  39: 39 */      GetParameterValues gpv = new GetParameterValues(pas);
/*  40: 40 */      return toString(gpv);
/*  41:    */    } catch (Exception e) {
/*  42: 42 */      loger.error("---getGetParameterValues---", e); }
/*  43: 43 */    return null;
/*  44:    */  }
/*  45:    */  
/*  46:    */  public String getUpdateConfigString(String hostId, String fileId)
/*  47:    */  {
/*  48: 48 */    SoftwareDao softDao = new SoftwareDao();
/*  49: 49 */    SoftwareBean cfgBean = softDao.findByID(fileId);
/*  50: 50 */    List<ConfigParamBean> cpbs = softDao.getConfigParams(Integer.parseInt(fileId));
/*  51: 51 */    if ((cfgBean == null) || (CommonUtil.isEmpty(cpbs)))
/*  52: 52 */      return null;
/*  53:    */    try {
/*  54: 54 */      SetParameterValues cmd = new SetParameterValues();
/*  55: 55 */      for (ConfigParamBean cpb : cpbs)
/*  56: 56 */        cmd.addValue(cpb.getName(), cpb.getValue());
/*  57: 57 */      return toString(cmd);
/*  58:    */    } catch (Exception e) {
/*  59: 59 */      loger.error("---getUpdateConfigString---", e); }
/*  60: 60 */    return null;
/*  61:    */  }
/*  62:    */  
/*  63:    */  public String getConfigString(List<ConfigParamBean> cpbs)
/*  64:    */  {
/*  65:    */    try {
/*  66: 66 */      SetParameterValues cmd = new SetParameterValues();
/*  67: 67 */      for (ConfigParamBean cpb : cpbs)
/*  68: 68 */        cmd.addValue(cpb.getName(), cpb.getValue());
/*  69: 69 */      return toString(cmd);
/*  70:    */    } catch (Exception e) {
/*  71: 71 */      loger.error("---getConfigString---", e); }
/*  72: 72 */    return null;
/*  73:    */  }
/*  74:    */  
/*  75:    */  public String toString(Message message) throws Exception
/*  76:    */  {
/*  77: 77 */    ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
/*  78: 78 */    message.writeTo(outBuffer);
/*  79: 79 */    String responseString = new String(outBuffer.toByteArray(), "ISO-8859-1");
/*  80: 80 */    return responseString;
/*  81:    */  }
/*  82:    */  
/*  83:    */  public String getSetParameterValuesString(List<HostPropertyBean> beans) {
/*  84:    */    try {
/*  85: 85 */      SetParameterValues cmd = new SetParameterValues();
/*  86: 86 */      for (HostPropertyBean prop : beans)
/*  87: 87 */        cmd.addValue(prop.getName(), prop.getValue());
/*  88: 88 */      return toString(cmd);
/*  89:    */    } catch (Exception e) {
/*  90: 90 */      loger.error("---getSetParameterValuesString---", e); }
/*  91: 91 */    return null;
/*  92:    */  }
/*  93:    */  
/*  94:    */  public String getRebootString()
/*  95:    */  {
/*  96:    */    try {
/*  97: 97 */      Reboot cmd = new Reboot("forced_reboot");
/*  98: 98 */      return toString(cmd);
/*  99:    */    } catch (Exception e) {
/* 100:100 */      loger.error("---getRebootString---", e); }
/* 101:101 */    return null;
/* 102:    */  }
/* 103:    */  
/* 104:    */  public String getApplyString()
/* 105:    */  {
/* 106:    */    try {
/* 107:107 */      Apply cmd = new Apply();
/* 108:108 */      return toString(cmd);
/* 109:    */    } catch (Exception e) {
/* 110:110 */      loger.error("---getApplyString---", e); }
/* 111:111 */    return null;
/* 112:    */  }
/* 113:    */  
/* 114:    */  public String getFactoryResetString()
/* 115:    */  {
/* 116:116 */    FactoryReset cmd = new FactoryReset();
/* 117:    */    try {
/* 118:118 */      return toString(cmd);
/* 119:    */    } catch (Exception e) {
/* 120:120 */      loger.error("---getFactoryResetString---", e); }
/* 121:121 */    return null;
/* 122:    */  }
/* 123:    */  
/* 124:    */  public String getUpdateFirmwareString(String hostId, String fileId)
/* 125:    */  {
/* 126:126 */    SoofacACS sfacs = (SoofacACS)SysConfigHelper.getBean("soofacACS");
/* 127:127 */    SoftwareDao softDao = new SoftwareDao();
/* 128:128 */    SoftwareBean softBean = softDao.findByID(fileId);
/* 129:129 */    String result = null;
/* 130:130 */    if ((softBean == null) || (StringUtils.isEmpty(softBean.getFileName()))) {
/* 131:131 */      result = "固件不存在,ID=" + fileId;
/* 132:    */    } else {
/* 133:133 */      File file = new File(SoofacACS.PATH_FULL_FIRMWARE + softBean.getFileName());
/* 134:134 */      if (!file.exists()) {
/* 135:135 */        result = "固件不存在,文件名:" + softBean.getFileName();
/* 136:136 */      } else if (!file.canRead())
/* 137:137 */        result = "固件不存在,文件名:" + softBean.getFileName();
/* 138:    */    }
/* 139:139 */    if (result != null) {
/* 140:140 */      System.out.println("固件更新失败:" + result);
/* 141:141 */      return null;
/* 142:    */    }
/* 143:143 */    String url = sfacs.getAcsURL() + SoofacACS.PATH_FIRMWARE + softBean.getFileName();
/* 144:144 */    Download download = new Download(KEY_SOFTWARE, url, "1. Firmware Upgrade Image");
/* 145:145 */    download.setFileSize(softBean.getSize());
/* 146:146 */    download.setMD5(softBean.getMd5());
/* 147:147 */    download.setVersion(softBean.getVersion());
/* 148:    */    try {
/* 149:149 */      return toString(download);
/* 150:    */    } catch (Exception e) {
/* 151:151 */      loger.error("---getUpdateFirmwareString---", e); }
/* 152:152 */    return null;
/* 153:    */  }
/* 154:    */  
/* 155:    */  public String getUpdateFirmwareString(String fileUrl, String fileMd5, int fileSize, String fileVerion)
/* 156:    */  {
/* 157:157 */    Download download = new Download(KEY_SOFTWARE, fileUrl, "1. Firmware Upgrade Image");
/* 158:158 */    download.setMD5(fileMd5);
/* 159:159 */    download.setFileSize(fileSize);
/* 160:160 */    download.setVersion(fileVerion);
/* 161:    */    try {
/* 162:162 */      return toString(download);
/* 163:    */    } catch (Exception e) {
/* 164:164 */      loger.error("---getUpdateFirmwareString2---", e); }
/* 165:165 */    return null;
/* 166:    */  }
/* 167:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.cpe.util.CpeActionExecutor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */