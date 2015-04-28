/*  1:   */package com.soofound.portal.service;
/*  2:   */
/*  3:   */import com.soofound.framework.util.CommonUtil;
/*  4:   */import com.soofound.framework.util.SysConfigHelper;
/*  5:   */import com.soofound.framework.web.BaseService;
/*  6:   */import com.soofound.portal.bean.AddressDto;
/*  7:   */import com.soofound.portal.bean.CityDto;
/*  8:   */import com.soofound.portal.bean.CountyDto;
/*  9:   */import com.soofound.portal.bean.ProvinceDto;
/* 10:   */import com.soofound.portal.dao.CityDao;
/* 11:   */import com.soofound.portal.dao.CountyDao;
/* 12:   */import com.soofound.portal.dao.ProvinceDao;
/* 13:   */import java.util.List;
/* 14:   */import org.springframework.beans.factory.annotation.Autowired;
/* 15:   */import org.springframework.stereotype.Service;
/* 16:   */
/* 22:   */@Service
/* 23:   */public final class CityService
/* 24:   */  extends BaseService<CityDao>
/* 25:   */{
/* 26:26 */  private String ADDRESS_PROVINCE = "province";
/* 27:27 */  private String ADDRESS_CITY = "city";
/* 28:28 */  private String ADDRESS_COUNTY = "county";
/* 29:   */  
/* 30:   */  @Autowired
/* 31:   */  protected void setDao(CityDao dao) {
/* 32:32 */    this.dao = dao;
/* 33:   */  }
/* 34:   */  
/* 35:   */  public List<CityDto> findByPid(String pid) {
/* 36:36 */    return ((CityDao)this.dao).findByPid(pid);
/* 37:   */  }
/* 38:   */  
/* 39:   */  public synchronized String getCpePropertySelectBox(int pid, String boxName, String propName, String propValue) {
/* 40:40 */    StringBuffer box = new StringBuffer(100);
/* 41:41 */    box.append("<select id=\"").append(propName).append("Select\" data-followerdata=\"");
/* 42:42 */    if (propName.equals(this.ADDRESS_PROVINCE)) {
/* 43:43 */      box.append(SysConfigHelper.CONTEXT_PATH).append("portal/getCities.do?pid=");
/* 44:44 */    } else if (propName.equals(this.ADDRESS_CITY)) {
/* 45:45 */      box.append(SysConfigHelper.CONTEXT_PATH).append("portal/getCounties.do?pid=");
/* 46:46 */      box.append("\" data-follow=\"").append(this.ADDRESS_PROVINCE).append("Select");
/* 47:47 */    } else if (propName.equals(this.ADDRESS_COUNTY)) {
/* 48:48 */      box.append("\" data-follow=\"").append(this.ADDRESS_CITY).append("Select");
/* 49:   */    }
/* 50:50 */    box.append("\" style=\"width:120px;\" class=\"s\" name=\"").append(boxName).append("\">");
/* 51:   */    
/* 52:52 */    List<? extends AddressDto> dtos = getBrothers(pid, propName, propValue);
/* 53:53 */    if (dtos != null) {
/* 54:54 */      for (AddressDto dto : dtos) {
/* 55:55 */        box.append("<option value=\"").append(dto.getId());
/* 56:56 */        if (propValue.equals(String.valueOf(dto.getId()))) {
/* 57:57 */          box.append("\" selected>");
/* 58:   */        } else
/* 59:59 */          box.append("\">");
/* 60:60 */        box.append(dto.getName());
/* 61:61 */        box.append("</option>");
/* 62:   */      }
/* 63:   */    }
/* 64:64 */    box.append("</select>");
/* 65:65 */    return box.toString();
/* 66:   */  }
/* 67:   */  
/* 68:   */  private List<? extends AddressDto> getBrothers(int addressPid, String propName, String propValue) {
/* 69:69 */    if ("province".equals(propName)) {
/* 70:70 */      ProvinceDao dao = new ProvinceDao();
/* 71:71 */      List<ProvinceDto> pds = dao.getBrothers(propValue, addressPid);
/* 72:72 */      if (CommonUtil.isEmpty(pds))
/* 73:73 */        pds = dao.findByPid("1");
/* 74:74 */      return pds;
/* 75:   */    }
/* 76:76 */    if ("city".equals(propName)) {
/* 77:77 */      CityDao dao = new CityDao();
/* 78:   */      
/* 79:79 */      List<CityDto> pds = dao.getBrothers(addressPid);
/* 80:80 */      if (CommonUtil.isEmpty(pds))
/* 81:81 */        pds = dao.findByPid("1");
/* 82:82 */      return pds;
/* 83:   */    }
/* 84:84 */    if ("county".equals(propName)) {
/* 85:85 */      CountyDao dao = new CountyDao();
/* 86:86 */      List<CountyDto> pds = dao.getBrothers(addressPid);
/* 87:87 */      if (CommonUtil.isEmpty(pds))
/* 88:88 */        pds = dao.findByPid("1");
/* 89:89 */      return pds;
/* 90:   */    }
/* 91:91 */    return null;
/* 92:   */  }
/* 93:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.service.CityService
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */