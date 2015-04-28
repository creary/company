/*  1:   */package com.soofound.framework.license;
/*  2:   */
/*  3:   */import org.jdom2.Element;
/*  4:   */
/*  5:   */public final class LicenseBuilder {
/*  6:   */  private Element getProperty(String name, String value) {
/*  7: 7 */    Element pe = new Element("Property");
/*  8: 8 */    pe.setAttribute("Name", name);
/*  9: 9 */    pe.setAttribute("Value", value);
/* 10:10 */    return pe;
/* 11:   */  }
/* 12:   */  
/* 13:   */  private Element createCustomerDetails(License lic) {
/* 14:14 */    Element cdEle = new Element("CustomerDetails");
/* 15:15 */    cdEle.addContent(getProperty("ProductID", lic.getProductID()));
/* 16:16 */    cdEle.addContent(getProperty("Vendor", lic.getVendor()));
/* 17:17 */    cdEle.addContent(getProperty("Customer", lic.getCustomer()));
/* 18:18 */    cdEle.addContent(getProperty("MachineID", lic.getMachineID()));
/* 19:19 */    cdEle.addContent(getProperty("LicenseType", lic.getLicenseType()));
/* 20:20 */    cdEle.addContent(getProperty("GenerationDate", lic.getCurrentDate()));
/* 21:21 */    cdEle.addContent(getProperty("ExpiryDate", lic.getExpiryDate()));
/* 22:22 */    return cdEle;
/* 23:   */  }
/* 24:   */  
/* 25:   */  private Element createProductDetails(License lic) {
/* 26:26 */    Element pdEle = new Element("ProductDetails");
/* 27:27 */    pdEle.addContent(getProperty("ProductName", lic.getProductName()));
/* 28:28 */    pdEle.addContent(getProperty("ProductType", lic.getProductType()));
/* 29:29 */    pdEle.addContent(getProperty("Version", lic.getVersion()));
/* 30:30 */    pdEle.addContent(getProperty("ReleaseDate", lic.getReleaseDate()));
/* 31:31 */    pdEle.addContent(getProperty("DeviceNumber", String.valueOf(lic.getDeviceNumber())));
/* 32:32 */    if (lic.getBoot() != null)
/* 33:33 */      pdEle.addContent(getProperty("Boot", lic.getBoot()));
/* 34:34 */    return pdEle;
/* 35:   */  }
/* 36:   */  
/* 37:   */  public Element createLicenseXML(License lic) throws Exception {
/* 38:38 */    Element root = new Element("License");
/* 39:39 */    if (lic.getDeveloper() == null) {
/* 40:40 */      root.setAttribute("Developer", "GuangZhou Soofound Software Corporation");
/* 41:   */    } else
/* 42:42 */      root.setAttribute("Developer", lic.getDeveloper());
/* 43:43 */    root.addContent(createCustomerDetails(lic));
/* 44:44 */    root.addContent(createProductDetails(lic));
/* 45:   */    
/* 46:46 */    String reslut = DESedeCoder.doEncode(lic.getMachineID(), lic.toString());
/* 47:47 */    Element ce = new Element("Code");
/* 48:48 */    ce.setText(reslut);
/* 49:49 */    root.addContent(ce);
/* 50:50 */    return root;
/* 51:   */  }
/* 52:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.framework.license.LicenseBuilder
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */