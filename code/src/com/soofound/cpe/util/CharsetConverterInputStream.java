/*  1:   */package com.soofound.cpe.util;
/*  2:   */
/*  3:   */import java.io.IOException;
/*  4:   */import java.io.InputStream;
/*  5:   */import java.io.InputStreamReader;
/*  6:   */import java.io.OutputStream;
/*  7:   */import java.io.OutputStreamWriter;
/*  8:   */import java.io.PipedOutputStream;
/*  9:   */import java.io.Reader;
/* 10:   */import java.io.UnsupportedEncodingException;
/* 11:   */import java.io.Writer;
/* 12:   */
/* 13:   */public class CharsetConverterInputStream extends InputStream
/* 14:   */{
/* 15:   */  private java.io.PipedInputStream pipein;
/* 16:   */  private OutputStream pipeout;
/* 17:   */  private Reader reader;
/* 18:   */  private Writer writer;
/* 19:   */  
/* 20:   */  public CharsetConverterInputStream(String csFrom, String csTo, InputStream in) throws UnsupportedEncodingException, IOException
/* 21:   */  {
/* 22:22 */    this.reader = new InputStreamReader(in, csFrom);
/* 23:23 */    this.pipein = new java.io.PipedInputStream();
/* 24:24 */    this.pipeout = new PipedOutputStream(this.pipein);
/* 25:25 */    this.writer = new OutputStreamWriter(this.pipeout, csTo);
/* 26:   */  }
/* 27:   */  
/* 28:   */  public int read() throws IOException {
/* 29:29 */    if (this.pipein.available() > 0)
/* 30:30 */      return this.pipein.read();
/* 31:31 */    int c = this.reader.read();
/* 32:32 */    if (c == -1)
/* 33:33 */      return -1;
/* 34:34 */    this.writer.write(c);
/* 35:35 */    this.writer.flush();
/* 36:36 */    return this.pipein.read();
/* 37:   */  }
/* 38:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.cpe.util.CharsetConverterInputStream
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */