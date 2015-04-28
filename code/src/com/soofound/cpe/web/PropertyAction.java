/*   1:    */package com.soofound.cpe.web;
/*   2:    */
/*   3:    */import com.danga.MemCached.MemCachedClient;
/*   4:    */import com.soofound.cpe.bean.HostBean;
/*   5:    */import com.soofound.cpe.bean.HostPropertyBean;
/*   6:    */import com.soofound.cpe.util.CpeActionExecutor;
/*   7:    */import com.soofound.cpe.util.CpeWaker;
/*   8:    */import com.soofound.framework.util.SysConfigHelper;
/*   9:    */import com.soofound.framework.web.BaseAction;
/*  10:    */import java.util.ArrayList;
/*  11:    */import java.util.HashMap;
/*  12:    */import java.util.List;
/*  13:    */import java.util.Map;
/*  14:    */import javax.servlet.http.HttpServletRequest;
/*  15:    */import org.springframework.beans.factory.annotation.Autowired;
/*  16:    */import org.springframework.stereotype.Controller;
/*  17:    */import org.springframework.ui.ModelMap;
/*  18:    */import org.springframework.web.bind.annotation.RequestMapping;
/*  19:    */import org.springframework.web.bind.annotation.ResponseBody;
/*  20:    */
/*  23:    */@Controller
/*  24:    */public class PropertyAction
/*  25:    */  extends BaseAction<PropertyService>
/*  26:    */{
/*  27:    */  private static final String EXCEPTION_PAGE = "/cpe/device/operationException.jsp";
/*  28:    */  private Map<String, String> cmds;
/*  29:    */  @Autowired
/*  30:    */  private HostService hostService;
/*  31:    */  
/*  32:    */  public PropertyAction()
/*  33:    */  {
/*  34: 34 */    this.cmds = new HashMap();
/*  35: 35 */    this.cmds.put("ifcfg", "ifconfig");
/*  36: 36 */    this.cmds.put("iwcfg", "iwconfig");
/*  37: 37 */    this.cmds.put("redirect", "cat /proc/reurl_info");
/*  38: 38 */    this.cmds.put("ping", "ping www.wifiant.cn");
/*  39: 39 */    this.cmds.put("netstat", "netstat -apn");
/*  40: 40 */    this.cmds.put("route", "route -n");
/*  41: 41 */    this.cmds.put("remoteControl", "sshpass -p \"123&abc\" ssh -N -y -R 121.201.5.43:9001:127.0.0.1:22 -l lanzl 121.201.5.43 -p 22000 &");
/*  42:    */  }
/*  43:    */  
/*  44:    */  @RequestMapping({"/cpe/propertyListJsp.do"})
/*  45:    */  public String listJsp(HttpServletRequest request, ModelMap model) {
/*  46: 46 */    return "/cpe/struct/propertyList.jsp";
/*  47:    */  }
/*  48:    */  
/*  49:    */  @RequestMapping({"/cpe/propertyList.do"})
/*  50:    */  @ResponseBody
/*  51: 51 */  public Map<String, Object> list(HttpServletRequest request, ModelMap model) { return super.listByPage(request, model); }
/*  52:    */  
/*  55:    */  @RequestMapping({"/cpe/propertyReadyAdd.do"})
/*  56: 56 */  public String readyAdd(HttpServletRequest request, ModelMap model) { return "/cpe/struct/propertyAdd.jsp"; }
/*  57:    */  
/*  58:    */  @RequestMapping({"/cpe/propertySave.do"})
/*  59:    */  @ResponseBody
/*  60:    */  public Map<String, Object> save(HttpServletRequest request, ModelMap model) {
/*  61: 61 */    String name = request.getParameter("name");
/*  62: 62 */    if (((PropertyService)this.service).findByName(name) != null) {
/*  63: 63 */      return getFailedResult("[" + name + "]已经存在.");
/*  64:    */    }
/*  65: 65 */    return super.save(request, model);
/*  66:    */  }
/*  67:    */  
/*  68:    */  @RequestMapping({"/cpe/propertyReadyEdit.do"})
/*  69:    */  public String readyEdit(HttpServletRequest request, ModelMap model) {
/*  70: 70 */    String id = request.getParameter("id");
/*  71: 71 */    model.addAttribute("dto", ((PropertyService)this.service).findByID(id));
/*  72: 72 */    return "/cpe/struct/propertyEdit.jsp";
/*  73:    */  }
/*  74:    */  
/*  75:    */  @RequestMapping({"/cpe/propertyUpdate.do"})
/*  76:    */  @ResponseBody
/*  77: 77 */  public Map<String, Object> update(HttpServletRequest request, ModelMap model) { return super.update(request, model); }
/*  78:    */  
/*  79:    */  @RequestMapping({"/cpe/propertyDelete.do"})
/*  80:    */  @ResponseBody
/*  81:    */  public Map<String, Object> delete(HttpServletRequest request, ModelMap model) {
/*  82: 82 */    return super.deleteByIDs(request, model);
/*  83:    */  }
/*  84:    */  
/*  85:    */  @RequestMapping({"/cpe/readyDebug.do"})
/*  86:    */  public String readyDebug(HttpServletRequest request, ModelMap model) {
/*  87: 87 */    String id = request.getParameter("id");
/*  88: 88 */    HostBean host = (HostBean)this.hostService.findByID(id);
/*  89: 89 */    MemCachedClient mcc = (MemCachedClient)SysConfigHelper.getBean("memcachedClient");
/*  90: 90 */    if (mcc != null) {
/*  91: 91 */      String ipkey = "ip_" + host.getSerialNumber().replace(":", "_");
/*  92: 92 */      if (mcc.get(ipkey) == null) {
/*  93: 93 */        host.setStun("0.0.0.0");
/*  94:    */      } else
/*  95: 95 */        host.setStun((String)mcc.get(ipkey));
/*  96:    */    }
/*  97: 97 */    model.addAttribute("host", host);
/*  98: 98 */    if (host.getOnline() != 1) {
/*  99: 99 */      model.addAttribute("message", "设备不在线.");
/* 100:100 */      return "/cpe/device/operationException.jsp";
/* 101:    */    }
/* 102:102 */    return "/cpe/device/debug.jsp";
/* 103:    */  }
/* 104:    */  
/* 105:    */  @RequestMapping({"/cpe/doActionForDebug.do"})
/* 106:    */  @ResponseBody
/* 107:107 */  public Map<String, Object> doActionForDebug(HttpServletRequest request, ModelMap model) { String cmdType = request.getParameter("cmdType");
/* 108:108 */    String debugCommand = request.getParameter("debugCommand");
/* 109:109 */    if (cmdType.endsWith("def"))
/* 110:110 */      debugCommand = (String)this.cmds.get(debugCommand);
/* 111:111 */    debugCommand = debugCommand.replace(" ", "@");
/* 112:112 */    String hid = request.getParameter("id");
/* 113:113 */    int hostId = Integer.parseInt(hid);
/* 114:114 */    CpeActionExecutor cae = new CpeActionExecutor();
/* 115:115 */    List<HostPropertyBean> props = new ArrayList();
/* 116:    */    
/* 117:117 */    HostPropertyBean prop = new HostPropertyBean();
/* 118:118 */    prop.setName("InternetGatewayDevice.DeviceInfo.debug_command");
/* 119:119 */    prop.setValue(debugCommand);
/* 120:120 */    props.add(prop);
/* 121:    */    
/* 122:122 */    String command = cae.getSetParameterValuesString(props).replace("&#13;", "");
/* 123:123 */    this.hostService.putDebugResult(hostId, "DEBUG:" + prop.getValue());
/* 124:124 */    this.hostService.putCommand(hostId, command);
/* 125:125 */    CpeWaker cw = new CpeWaker();
/* 126:126 */    cw.wakeup(hid);
/* 127:    */    
/* 128:128 */    String result = null;
/* 129:129 */    for (int i = 0; i < 100; i++) {
/* 130:    */      try {
/* 131:131 */        Thread.sleep(500L);
/* 132:    */      } catch (Exception ex) {
/* 133:133 */        ex.printStackTrace();
/* 134:    */      }
/* 135:135 */      result = this.hostService.getDebugResult(hostId);
/* 136:136 */      if ((result != null) && (result.startsWith("DEBUG_RESULT")))
/* 137:    */        break;
/* 138:    */    }
/* 139:139 */    model.addAttribute("debugCommand", debugCommand);
/* 140:140 */    Map<String, Object> resultmap = new HashMap();
/* 141:141 */    if ((result == null) || (!result.startsWith("DEBUG_RESULT"))) {
/* 142:142 */      resultmap.put("status", Integer.valueOf(2));
/* 143:143 */      model.addAttribute("data", "no result");
/* 144:    */    } else {
/* 145:145 */      result = result.substring("DEBUG_RESULT:".length());
/* 146:146 */      resultmap.put("status", Integer.valueOf(1));
/* 147:147 */      resultmap.put("data", result.replace("\n", "<br/>"));
/* 148:    */    }
/* 149:149 */    this.hostService.removeDebugResult(hostId);
/* 150:150 */    return resultmap;
/* 151:    */  }
/* 152:    */  
/* 154:    */  @Autowired
/* 155:    */  protected void setService(PropertyService service)
/* 156:    */  {
/* 157:157 */    this.service = service;
/* 158:    */  }
/* 159:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.cpe.web.PropertyAction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */