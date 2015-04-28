/*   1:    */package com.soofound.framework.util;
/*   2:    */
/*   3:    */import java.io.ByteArrayOutputStream;
/*   4:    */import java.io.File;
/*   5:    */import java.io.FileOutputStream;
/*   6:    */import java.io.InputStream;
/*   7:    */import java.io.PrintWriter;
/*   8:    */import java.io.StringReader;
/*   9:    */import org.jdom2.Document;
/*  10:    */import org.jdom2.Element;
/*  11:    */import org.jdom2.JDOMException;
/*  12:    */import org.jdom2.input.SAXBuilder;
/*  13:    */import org.jdom2.output.Format;
/*  14:    */import org.jdom2.output.XMLOutputter;
/*  15:    */import org.jdom2.xpath.XPath;
/*  16:    */import org.xml.sax.InputSource;
/*  17:    */
/*  20:    */public final class SimpleXMLUtil
/*  21:    */{
/*  22:    */  public static Document file2Doc(String xmlPath)
/*  23:    */  {
/*  24: 24 */    return file2Doc(xmlPath, false);
/*  25:    */  }
/*  26:    */  
/*  27:    */  public static Document file2Doc(String xmlPath, boolean validate) {
/*  28: 28 */    SAXBuilder builder = new SAXBuilder(validate);
/*  29: 29 */    Document doc = null;
/*  30:    */    try {
/*  31: 31 */      doc = builder.build(new File(xmlPath.replace("%20", " ")));
/*  32:    */    } catch (Exception e) {
/*  33: 33 */      e.printStackTrace();
/*  34:    */    }
/*  35: 35 */    return doc;
/*  36:    */  }
/*  37:    */  
/*  38:    */  public static Document file2Doc(InputStream stream, boolean validate) {
/*  39: 39 */    SAXBuilder builder = new SAXBuilder(validate);
/*  40: 40 */    Document doc = null;
/*  41:    */    try {
/*  42: 42 */      doc = builder.build(stream);
/*  43:    */    } catch (Exception e) {
/*  44: 44 */      e.printStackTrace();
/*  45:    */    }
/*  46: 46 */    return doc;
/*  47:    */  }
/*  48:    */  
/*  49:    */  public static Document file2Doc(InputStream stream) {
/*  50: 50 */    SAXBuilder builder = new SAXBuilder(true);
/*  51: 51 */    Document doc = null;
/*  52:    */    try {
/*  53: 53 */      doc = builder.build(stream);
/*  54:    */    } catch (Exception e) {
/*  55: 55 */      e.printStackTrace();
/*  56:    */    }
/*  57: 57 */    return doc;
/*  58:    */  }
/*  59:    */  
/*  60:    */  public static String doc2String(Document doc) {
/*  61:    */    try {
/*  62: 62 */      ByteArrayOutputStream baos = new ByteArrayOutputStream();
/*  63: 63 */      PrintWriter pw = new PrintWriter(baos);
/*  64: 64 */      Format format = Format.getCompactFormat();
/*  65: 65 */      format.setEncoding("UTF-8");
/*  66: 66 */      XMLOutputter xmlop = new XMLOutputter();
/*  67: 67 */      xmlop.setFormat(format);
/*  68: 68 */      xmlop.output(doc, pw);
/*  69:    */      
/*  70: 70 */      return baos.toString();
/*  71:    */    } catch (Exception e) {
/*  72: 72 */      e.printStackTrace();
/*  73:    */    }
/*  74: 74 */    return null;
/*  75:    */  }
/*  76:    */  
/*  77:    */  public static Document string2Doc(String xml) {
/*  78: 78 */    if (xml == null) { return null;
/*  79:    */    }
/*  80: 80 */    Document doc = null;
/*  81:    */    try {
/*  82: 82 */      StringReader sr = new StringReader(xml);
/*  83: 83 */      InputSource is = new InputSource(sr);
/*  84: 84 */      SAXBuilder builder = new SAXBuilder(false);
/*  85: 85 */      doc = builder.build(is);
/*  86:    */    } catch (Exception e) {
/*  87: 87 */      e.printStackTrace();
/*  88:    */    }
/*  89: 89 */    return doc;
/*  90:    */  }
/*  91:    */  
/*  92:    */  public static void updateXML(String xmlFullPath, Document doc) {
/*  93:    */    try {
/*  94: 94 */      Format format = Format.getCompactFormat();
/*  95: 95 */      format.setEncoding("UTF-8");
/*  96: 96 */      format.setIndent("\t");
/*  97: 97 */      XMLOutputter serializer = new XMLOutputter(format);
/*  98: 98 */      FileOutputStream fos = new FileOutputStream(xmlFullPath.replace("%20", " "));
/*  99: 99 */      serializer.output(doc, fos);
/* 100:100 */      fos.close();
/* 101:    */    } catch (Exception e) {
/* 102:102 */      e.printStackTrace();
/* 103:    */    }
/* 104:    */  }
/* 105:    */  
/* 106:    */  public static Element getElement(Element root, String path) {
/* 107:    */    try {
/* 108:108 */      return (Element)XPath.selectSingleNode(root, path);
/* 109:    */    } catch (JDOMException e) {
/* 110:110 */      e.printStackTrace();
/* 111:    */    }
/* 112:112 */    return null;
/* 113:    */  }
/* 114:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.framework.util.SimpleXMLUtil
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */