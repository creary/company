/*  1:   */package com.soofound.portal.action;
/*  2:   */
/*  3:   */import com.soofound.framework.web.GenericAction;
/*  4:   */import com.soofound.portal.bean.AddressDto;
/*  5:   */import com.soofound.portal.bean.CityDto;
/*  6:   */import com.soofound.portal.bean.CountyDto;
/*  7:   */import com.soofound.portal.bean.MsiteJsonBean;
/*  8:   */import com.soofound.portal.bean.ProvinceDto;
/*  9:   */import com.soofound.portal.service.CityService;
/* 10:   */import com.soofound.portal.service.CountyService;
/* 11:   */import com.soofound.portal.service.ProvinceService;
/* 12:   */import java.util.ArrayList;
/* 13:   */import java.util.HashMap;
/* 14:   */import java.util.List;
/* 15:   */import org.springframework.beans.factory.annotation.Autowired;
/* 16:   */import org.springframework.stereotype.Controller;
/* 17:   */import org.springframework.ui.ModelMap;
/* 18:   */import org.springframework.web.bind.annotation.RequestMapping;
/* 19:   */import org.springframework.web.bind.annotation.ResponseBody;
/* 20:   */
/* 21:   */@Controller
/* 22:   */public final class AddressAction extends GenericAction
/* 23:   */{
/* 24:   */  @Autowired
/* 25:   */  private CountyService countyService;
/* 26:   */  @Autowired
/* 27:   */  private CityService cityService;
/* 28:   */  @Autowired
/* 29:   */  private ProvinceService provinceService;
/* 30:   */  
/* 31:   */  @RequestMapping({"/portal/getProvinces.do"})
/* 32:   */  @ResponseBody
/* 33:   */  public java.util.Map<String, Object> getProvinces(javax.servlet.http.HttpServletRequest request, ModelMap model)
/* 34:   */  {
/* 35:35 */    String _pid = request.getParameter("pid");
/* 36:36 */    String pid = _pid.split("#")[0];
/* 37:37 */    List<ProvinceDto> dtos = this.provinceService.findByPid(pid);
/* 38:38 */    return getFollowData(dtos);
/* 39:   */  }
/* 40:   */  
/* 41:   */  @RequestMapping({"/portal/getCities.do"})
/* 42:   */  @ResponseBody
/* 43:43 */  public java.util.Map<String, Object> getCities(javax.servlet.http.HttpServletRequest request, ModelMap model) { String _pid = request.getParameter("pid");
/* 44:44 */    String pid = _pid.split("#")[0];
/* 45:45 */    List<CityDto> dtos = this.cityService.findByPid(pid);
/* 46:46 */    return getFollowData(dtos);
/* 47:   */  }
/* 48:   */  
/* 49:   */  @RequestMapping({"/portal/getCounties.do"})
/* 50:   */  @ResponseBody
/* 51:51 */  public java.util.Map<String, Object> getCounties(javax.servlet.http.HttpServletRequest request, ModelMap model) { String _pid = request.getParameter("pid");
/* 52:52 */    String pid = _pid.split("#")[0];
/* 53:53 */    List<CountyDto> dtos = this.countyService.findByPid(pid);
/* 54:54 */    return getFollowData(dtos);
/* 55:   */  }
/* 56:   */  
/* 57:   */  @RequestMapping({"/portal/getSubIndustries.do"})
/* 58:   */  @ResponseBody
/* 59:59 */  public java.util.Map<String, Object> getSubIndustries(javax.servlet.http.HttpServletRequest request, ModelMap model) { int pid = Integer.parseInt(request.getParameter("pid"));
/* 60:60 */    return getFollowData(this.countyService.getSubIndustries(pid));
/* 61:   */  }
/* 62:   */  
/* 63:   */  private java.util.Map<String, Object> getFollowData(List<? extends AddressDto> adds) {
/* 64:64 */    java.util.Map<String, Object> datas = new HashMap();
/* 65:65 */    datas.put("status", Integer.valueOf(1));
/* 66:66 */    List<MsiteJsonBean> fsbs = new ArrayList();
/* 67:67 */    for (AddressDto add : adds) {
/* 68:68 */      MsiteJsonBean fsb = new MsiteJsonBean();
/* 69:69 */      fsb.setValue(add.getId());
/* 70:70 */      fsb.setText(add.getName());
/* 71:71 */      fsbs.add(fsb);
/* 72:   */    }
/* 73:73 */    datas.put("data", fsbs);
/* 74:74 */    return datas;
/* 75:   */  }
/* 76:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.action.AddressAction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */