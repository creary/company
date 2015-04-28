/*  1:   */package com.soofound.cpe.util;
/*  2:   */
/*  3:   */import java.io.IOException;
/*  4:   */
/*  5:   */public class XMLFilterNS extends java.io.InputStream
/*  6:   */{
/*  7: 7 */  private String pat = "xmlns=\"urn:dslforum-org:cwmp-1-0\"";
/*  8: 8 */  private String pat2 = "xmlns=\"urn:dslforum-org:cwmp-1-1\"";
/*  9: 9 */  private int length = 0;
/* 10:10 */  private int pos = 0;
/* 11:11 */  private boolean f = false;
/* 12:12 */  private byte[] buff = new byte[1024];
/* 13:   */  private java.io.InputStream is;
/* 14:   */  
/* 15:   */  public XMLFilterNS(java.io.InputStream is) {
/* 16:16 */    this.is = is;
/* 17:   */  }
/* 18:   */  
/* 19:   */  public int read() throws IOException {
/* 20:20 */    if (!this.f) {
/* 21:21 */      this.length = this.is.read(this.buff);
/* 22:22 */      if (this.length < this.buff.length) {
/* 23:23 */        byte[] b2 = new byte[this.length];
/* 24:24 */        System.arraycopy(this.buff, 0, b2, 0, this.length);
/* 25:25 */        this.buff = b2;
/* 26:   */      }
/* 27:27 */      String b = new String(this.buff);
/* 28:28 */      b = b.replace(this.pat, "");
/* 29:29 */      b = b.replace(this.pat2, "");
/* 30:30 */      this.buff = b.getBytes();
/* 31:31 */      this.length = this.buff.length;
/* 32:32 */      this.f = true;
/* 33:   */    }
/* 34:34 */    if (this.pos < this.length)
/* 35:35 */      return this.buff[(this.pos++)];
/* 36:36 */    return this.is.read();
/* 37:   */  }
/* 38:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.cpe.util.XMLFilterNS
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */