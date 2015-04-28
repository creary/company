/*  1:   */package com.soofound.project.sola;
/*  2:   */
/*  3:   */import java.io.IOException;
/*  4:   */import javax.servlet.ServletException;
/*  5:   */import javax.servlet.http.HttpServlet;
/*  6:   */import javax.servlet.http.HttpServletRequest;
/*  7:   */import javax.servlet.http.HttpServletResponse;
/*  8:   */
/* 10:   */public class UnifiGuestAction
/* 11:   */  extends HttpServlet
/* 12:   */{
/* 13:   */  private static final long serialVersionUID = 201412241107001L;
/* 14:   */  
/* 15:   */  protected void doGet(HttpServletRequest request, HttpServletResponse response)
/* 16:   */    throws ServletException, IOException
/* 17:   */  {
/* 18:18 */    processRequest(request, response);
/* 19:   */  }
/* 20:   */  
/* 21:   */  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/* 22:22 */    processRequest(request, response);
/* 23:   */  }
/* 24:   */  
/* 25:   */  protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/* 26:26 */    if (request.getRequestURI().startsWith("/guest/s/")) {
/* 27:27 */      String site = request.getRequestURI().substring(9);
/* 28:28 */      site = site.substring(0, site.length() - 1);
/* 29:29 */      StringBuilder str = new StringBuilder(200);
/* 30:30 */      str.append("http://www.wifiant.cn/softac/wifiant/unifiLogin.do?site=").append(site).append("&acip=");
/* 31:31 */      str.append(request.getRemoteAddr()).append("&").append(request.getQueryString());
/* 32:   */      
/* 33:33 */      response.sendRedirect(str.toString());
/* 34:34 */      return;
/* 35:   */    }
/* 36:36 */    response.sendRedirect("http://www.wifiant.cn/softac/");
/* 37:   */  }
/* 38:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.project.sola.UnifiGuestAction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */