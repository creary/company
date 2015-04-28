/*  1:   */package com.soofound.framework.jdbc;
/*  2:   */
/*  3:   */public final class Pagination {
/*  4:   */  private int perPage;
/*  5:   */  private int totalRecord;
/*  6:   */  private int currentPage;
/*  7:   */  private int minNum;
/*  8:   */  private int maxNum;
/*  9:   */  private int totalPage;
/* 10:   */  
/* 11:   */  public Pagination(int currentPage, int totalRecord) {
/* 12:12 */    this(20, currentPage, totalRecord);
/* 13:   */  }
/* 14:   */  
/* 15:   */  public Pagination(int _perPage, int _currentPage, int _totalRecord) {
/* 16:16 */    this.perPage = _perPage;
/* 17:17 */    this.totalRecord = _totalRecord;
/* 18:   */    
/* 19:19 */    if (_currentPage <= 0) { _currentPage = 1;
/* 20:   */    }
/* 21:21 */    this.totalPage = ((this.totalRecord + this.perPage - 1) / this.perPage);
/* 22:   */    
/* 23:23 */    if (this.totalPage == 0) { this.totalPage = 1;
/* 24:   */    }
/* 25:25 */    if (this.totalPage < _currentPage) {
/* 26:26 */      this.currentPage = this.totalPage;
/* 27:   */    } else
/* 28:28 */      this.currentPage = _currentPage;
/* 29:29 */    this.minNum = ((this.currentPage - 1) * this.perPage);
/* 30:   */    
/* 31:31 */    if (this.totalRecord == 0) {
/* 32:32 */      this.minNum = 0;
/* 33:33 */      this.maxNum = 0;
/* 34:34 */      return; }
/* 35:35 */    if ((this.totalRecord % this.perPage != 0) && (this.currentPage == this.totalPage)) {
/* 36:36 */      this.maxNum = this.totalRecord;
/* 37:   */    } else {
/* 38:38 */      this.maxNum = (this.minNum + this.perPage);
/* 39:   */    }
/* 40:40 */    this.minNum += 1;
/* 41:   */  }
/* 42:   */  
/* 43:   */  public int getPerSize() {
/* 44:44 */    return this.perPage;
/* 45:   */  }
/* 46:   */  
/* 47:   */  public void setPerSize(int newPerPage) {
/* 48:48 */    this.perPage = newPerPage;
/* 49:   */  }
/* 50:   */  
/* 51:   */  public int getRecordsTotal() {
/* 52:52 */    return this.totalRecord;
/* 53:   */  }
/* 54:   */  
/* 55:   */  public void setRecordsTotal(int newTotalRecord) {
/* 56:56 */    this.totalRecord = newTotalRecord;
/* 57:   */  }
/* 58:   */  
/* 59:   */  public int getCur() {
/* 60:60 */    return this.currentPage;
/* 61:   */  }
/* 62:   */  
/* 63:   */  public int getEndRow() {
/* 64:64 */    return this.maxNum;
/* 65:   */  }
/* 66:   */  
/* 67:   */  public int getTotal() {
/* 68:68 */    return this.totalPage;
/* 69:   */  }
/* 70:   */  
/* 71:   */  public int getStartRow() {
/* 72:72 */    return this.minNum;
/* 73:   */  }
/* 74:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.framework.jdbc.Pagination
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */