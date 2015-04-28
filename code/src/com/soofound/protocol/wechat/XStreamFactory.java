/*  1:   */package com.soofound.protocol.wechat;
/*  2:   */
/*  3:   */import com.thoughtworks.xstream.XStream;
/*  4:   */import com.thoughtworks.xstream.core.util.QuickWriter;
/*  5:   */import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
/*  6:   */import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
/*  7:   */import com.thoughtworks.xstream.io.xml.XppDriver;
/*  8:   */import java.io.Writer;
/*  9:   */
/* 10:   */public class XStreamFactory
/* 11:   */{
/* 12:12 */  protected static String PREFIX_CDATA = "<![CDATA[";
/* 13:13 */  protected static String SUFFIX_CDATA = "]]>";
/* 14:   */  
/* 19:   */  public static XStream init(boolean isAddCDATA)
/* 20:   */  {
/* 21:21 */    XStream xstream = null;
/* 22:22 */    if (isAddCDATA) {
/* 23:23 */      xstream = (new XppDriver() {
/* 24:   */        public HierarchicalStreamWriter createWriter(Writer out) {
/* 25:25 */          new PrettyPrintWriter(out) {
/* 26:   */            protected void writeText(QuickWriter writer, String text) {
/* 27:27 */              if (!text.startsWith(XStreamFactory.PREFIX_CDATA)) {
/* 28:28 */                text = XStreamFactory.PREFIX_CDATA + text + XStreamFactory.SUFFIX_CDATA;
/* 29:   */              }
/* 30:30 */              writer.write(text);
/* 31:   */            }
/* 32:   */          };
/* 33:   */        }
/* 34:   */      });
/* 35:   */    } else {
/* 36:36 */      xstream = new XStream();
/* 37:   */    }
/* 38:38 */    return xstream;
/* 39:   */  }
/* 40:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.wechat.XStreamFactory
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */