/*   1:    */package com.soofound.portal.service;
/*   2:    */
/*   3:    */import com.soofound.framework.util.SimpleXMLUtil;
/*   4:    */import com.soofound.framework.util.SysConfigHelper;
/*   5:    */import com.soofound.framework.web.BaseService;
/*   6:    */import com.soofound.portal.bean.AddressDto;
/*   7:    */import com.soofound.portal.bean.CountyDto;
/*   8:    */import com.soofound.portal.dao.CountyDao;
/*   9:    */import java.util.ArrayList;
/*  10:    */import java.util.HashMap;
/*  11:    */import java.util.List;
/*  12:    */import java.util.Map;
/*  13:    */import org.jdom2.Document;
/*  14:    */import org.jdom2.Element;
/*  15:    */import org.springframework.beans.factory.annotation.Autowired;
/*  16:    */import org.springframework.stereotype.Service;
/*  17:    */
/*  22:    */@Service
/*  23:    */public final class CountyService
/*  24:    */  extends BaseService<CountyDao>
/*  25:    */{
/*  26:    */  private List<AddressDto> industries;
/*  27:    */  private Map<Integer, ArrayList<AddressDto>> subIndustries;
/*  28:    */  
/*  29:    */  @Autowired
/*  30:    */  protected void setDao(CountyDao dao)
/*  31:    */  {
/*  32: 32 */    this.dao = dao;
/*  33:    */  }
/*  34:    */  
/*  35:    */  public List<CountyDto> findByPid(String pid) {
/*  36: 36 */    return ((CountyDao)this.dao).findByPid(pid);
/*  37:    */  }
/*  38:    */  
/*  39:    */  public List<AddressDto> getIndustries() {
/*  40: 40 */    String xml = SysConfigHelper.getAttribute("configPath") + "industry.xml";
/*  41: 41 */    Document doc = SimpleXMLUtil.file2Doc(xml);
/*  42: 42 */    Element root = doc.getRootElement();
/*  43: 43 */    List<Element> eles = root.getChildren();
/*  44: 44 */    List<AddressDto> ads = new ArrayList();
/*  45: 45 */    for (Element ele : eles) {
/*  46: 46 */      AddressDto ad = new AddressDto();
/*  47: 47 */      ad.setId(Integer.parseInt(ele.getAttributeValue("id")));
/*  48: 48 */      ad.setName(ele.getAttributeValue("name"));
/*  49: 49 */      ads.add(ad);
/*  50:    */    }
/*  51: 51 */    return ads;
/*  52:    */  }
/*  53:    */  
/*  54:    */  private Map<Integer, ArrayList<AddressDto>> getSubIndustries() {
/*  55: 55 */    String xml = SysConfigHelper.getAttribute("configPath") + "industry.xml";
/*  56: 56 */    Document doc = SimpleXMLUtil.file2Doc(xml);
/*  57: 57 */    Element root = doc.getRootElement();
/*  58: 58 */    List<Element> eles = root.getChildren();
/*  59: 59 */    Map<Integer, ArrayList<AddressDto>> subAds = new HashMap();
/*  60: 60 */    for (Element ele : eles) {
/*  61: 61 */      int pid = Integer.parseInt(ele.getAttributeValue("id"));
/*  62: 62 */      ArrayList<AddressDto> subads = new ArrayList();
/*  63: 63 */      List<Element> subeles = ele.getChildren();
/*  64: 64 */      for (Element subele : subeles) {
/*  65: 65 */        AddressDto ad = new AddressDto();
/*  66: 66 */        ad.setId(Integer.parseInt(subele.getAttributeValue("id")));
/*  67: 67 */        ad.setName(subele.getAttributeValue("name"));
/*  68: 68 */        subads.add(ad);
/*  69:    */      }
/*  70: 70 */      subAds.put(Integer.valueOf(pid), subads);
/*  71:    */    }
/*  72: 72 */    return subAds;
/*  73:    */  }
/*  74:    */  
/*  75:    */  public String getIndustrySelectBox(int pid, String boxName, String propName, String propValue) {
/*  76: 76 */    StringBuffer box = new StringBuffer(100);
/*  77: 77 */    box.append("<select id=\"").append(propName).append("Select\" data-followerdata=\"");
/*  78: 78 */    if (propName.equals("industry")) {
/*  79: 79 */      box.append(SysConfigHelper.CONTEXT_PATH).append("portal/getSubIndustries.do?pid=");
/*  80: 80 */    } else if (propName.equals("subIndustry")) {
/*  81: 81 */      box.append("\" data-follow=\"industrySelect");
/*  82:    */    }
/*  83: 83 */    box.append("\" style=\"width:120px;\" class=\"s\" name=\"").append(boxName).append("\">");
/*  84:    */    
/*  85: 85 */    List<AddressDto> dtos = getBrothers(pid, propName, propValue);
/*  86: 86 */    if (dtos != null) {
/*  87: 87 */      for (AddressDto dto : dtos) {
/*  88: 88 */        box.append("<option value=\"").append(dto.getId());
/*  89: 89 */        if (propValue.equals(String.valueOf(dto.getId()))) {
/*  90: 90 */          box.append("\" selected>");
/*  91:    */        } else
/*  92: 92 */          box.append("\">");
/*  93: 93 */        box.append(dto.getName());
/*  94: 94 */        box.append("</option>");
/*  95:    */      }
/*  96:    */    }
/*  97: 97 */    box.append("</select>");
/*  98: 98 */    return box.toString();
/*  99:    */  }
/* 100:    */  
/* 101:    */  private List<AddressDto> getBrothers(int pid, String propName, String propValue) {
/* 102:102 */    if (this.industries == null) {
/* 103:103 */      this.industries = getIndustries();
/* 104:104 */      this.subIndustries = getSubIndustries();
/* 105:    */    }
/* 106:106 */    if ("industry".equals(propName)) {
/* 107:107 */      return this.industries;
/* 108:    */    }
/* 109:109 */    if ("subIndustry".equals(propName)) {
/* 110:110 */      return (List)this.subIndustries.get(Integer.valueOf(pid));
/* 111:    */    }
/* 112:112 */    return null;
/* 113:    */  }
/* 114:    */  
/* 115:    */  public List<AddressDto> getSubIndustries(int pid) {
/* 116:116 */    return (List)this.subIndustries.get(Integer.valueOf(pid));
/* 117:    */  }
/* 118:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.service.CountyService
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */