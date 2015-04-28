/*  1:   */package com.soofound.cpe.util;
/*  2:   */
/*  3:   */import java.io.IOException;
/*  4:   */
/*  5:   */public class XMLFilterInputStream extends java.io.InputStream
/*  6:   */{
/*  7:   */  private java.io.InputStream istream;
/*  8:   */  private int lvl;
/*  9:   */  private int lastchar;
/* 10:   */  private int len;
/* 11:   */  private int nextchar;
/* 12:12 */  private boolean intag = false;
/* 13:13 */  private StringBuffer buff = new StringBuffer(16);
/* 14:   */  
/* 15:   */  public XMLFilterInputStream(java.io.InputStream is, int len) {
/* 16:16 */    this.len = len;
/* 17:17 */    this.istream = is;
/* 18:   */  }
/* 19:   */  
/* 20:   */  public int read() throws IOException {
/* 21:21 */    if ((this.lastchar == 62) && (this.lvl == 0)) {
/* 22:22 */      return -1;
/* 23:   */    }
/* 24:24 */    int l = this.lastchar;
/* 25:25 */    if (this.nextchar != -1) {
/* 26:26 */      this.lastchar = this.nextchar;
/* 27:27 */      this.nextchar = -1;
/* 28:   */    } else {
/* 29:29 */      if (this.buff.length() > 0) {
/* 30:30 */        this.lastchar = this.buff.charAt(0);
/* 31:31 */        this.buff.deleteCharAt(0);
/* 32:32 */        return this.lastchar;
/* 33:   */      }
/* 34:34 */      this.lastchar = this.istream.read();
/* 35:   */    }
/* 36:36 */    if (this.lastchar == 60) {
/* 37:37 */      this.intag = true;
/* 38:38 */    } else if (this.lastchar == 62)
/* 39:39 */      this.intag = false;
/* 40:40 */    if ((!this.intag) && (this.lastchar == 38)) {
/* 41:41 */      int amppos = this.buff.length();
/* 42:42 */      this.buff.append((char)this.lastchar);
/* 43:43 */      for (int c = 0; c < 10; c++) {
/* 44:44 */        int ch = this.istream.read();
/* 45:45 */        if (ch == -1)
/* 46:   */          break;
/* 47:47 */        if (ch == 38) {
/* 48:48 */          this.nextchar = ch;
/* 49:49 */          break;
/* 50:   */        }
/* 51:51 */        this.buff.append((char)ch);
/* 52:   */      }
/* 53:53 */      String s = this.buff.substring(amppos);
/* 54:54 */      if ((!s.startsWith("&amp;")) && (!s.startsWith("&lt;")) && (!s.startsWith("&gt;")) && 
/* 55:55 */        (!s.startsWith("&apos;")) && (!s.startsWith("&quot;")) && (!s.startsWith("&#"))) {
/* 56:56 */        this.buff.replace(amppos, amppos + 1, "&amp;");
/* 57:   */      }
/* 58:58 */      return read();
/* 59:   */    }
/* 60:60 */    if (l == 60) {
/* 61:61 */      this.intag = true;
/* 62:62 */      if (this.lastchar == 47) {
/* 63:63 */        this.lvl -= 1;
/* 64:   */      } else
/* 65:65 */        this.lvl += 1;
/* 66:   */    }
/* 67:67 */    this.len -= 1;
/* 68:68 */    return this.lastchar;
/* 69:   */  }
/* 70:   */  
/* 71:   */  public boolean next() throws IOException {
/* 72:72 */    while ((this.nextchar = this.istream.read()) != -1) {
/* 73:73 */      if (!Character.isWhitespace(this.nextchar))
/* 74:   */        break;
/* 75:   */    }
/* 76:76 */    this.lvl = 0;
/* 77:77 */    this.lastchar = 0;
/* 78:78 */    return this.nextchar != -1;
/* 79:   */  }
/* 80:   */  
/* 81:   */  public int getLength() {
/* 82:82 */    return this.len;
/* 83:   */  }
/* 84:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.cpe.util.XMLFilterInputStream
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */