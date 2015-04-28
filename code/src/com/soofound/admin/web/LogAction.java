/*   1:    */package com.soofound.admin.web;
/*   2:    */
/*   3:    */import com.soofound.admin.bean.UserDto;
/*   4:    */import com.soofound.framework.web.BaseAction;
/*   5:    */import java.awt.Color;
/*   6:    */import java.awt.Font;
/*   7:    */import java.awt.Graphics;
/*   8:    */import java.awt.image.BufferedImage;
/*   9:    */import java.util.Map;
/*  10:    */import java.util.Random;
/*  11:    */import javax.imageio.ImageIO;
/*  12:    */import javax.servlet.ServletOutputStream;
/*  13:    */import javax.servlet.http.HttpServletRequest;
/*  14:    */import javax.servlet.http.HttpServletResponse;
/*  15:    */import javax.servlet.http.HttpSession;
/*  16:    */import org.springframework.beans.factory.annotation.Autowired;
/*  17:    */import org.springframework.stereotype.Controller;
/*  18:    */import org.springframework.ui.ModelMap;
/*  19:    */import org.springframework.web.bind.annotation.RequestMapping;
/*  20:    */import org.springframework.web.bind.annotation.ResponseBody;
/*  21:    */
/*  22:    */@Controller
/*  23:    */public class LogAction extends BaseAction<LogService>
/*  24:    */{
/*  25:    */  @RequestMapping({"/admin/logListJsp.do"})
/*  26:    */  public String listJsp(HttpServletRequest request, ModelMap model)
/*  27:    */  {
/*  28: 28 */    UserDto dto = super.getCurrentUser(request);
/*  29: 29 */    if (dto.getUsername().equals("afunms"))
/*  30: 30 */      model.addAttribute("location", "1");
/*  31: 31 */    return "/admin/log/list.jsp";
/*  32:    */  }
/*  33:    */  
/*  34:    */  @RequestMapping({"/admin/logList.do"})
/*  35:    */  @ResponseBody
/*  36: 36 */  public Map<String, Object> list(HttpServletRequest request, ModelMap model) { return super.listByPage(request, model); }
/*  37:    */  
/*  38:    */  @RequestMapping({"/admin/logDelete.do"})
/*  39:    */  @ResponseBody
/*  40:    */  public Map<String, Object> delete(HttpServletRequest request, ModelMap model) {
/*  41: 41 */    return super.deleteByIDs(request, model);
/*  42:    */  }
/*  43:    */  
/*  44:    */  @Autowired
/*  45:    */  protected void setService(LogService service) {
/*  46: 46 */    this.service = service;
/*  47:    */  }
/*  48:    */  
/*  49:    */  @RequestMapping({"/acs/getCheckCode.do"})
/*  50:    */  public void showImage(HttpServletRequest request, HttpServletResponse response)
/*  51:    */  {
/*  52:    */    try {
/*  53: 53 */      int width = 60;
/*  54: 54 */      int height = 20;
/*  55: 55 */      BufferedImage image = new BufferedImage(width, height, 1);
/*  56:    */      
/*  57: 57 */      Graphics g = image.getGraphics();
/*  58:    */      
/*  59: 59 */      Random random = new Random();
/*  60:    */      
/*  61: 61 */      g.setColor(getRandColor(200, 250));
/*  62: 62 */      g.fillRect(0, 0, width, height);
/*  63:    */      
/*  64: 64 */      g.setFont(new Font("Times New Roman", 0, 18));
/*  65:    */      
/*  66: 66 */      g.setColor(getRandColor(160, 200));
/*  67: 67 */      for (int i = 0; i < 100; i++) {
/*  68: 68 */        int x = random.nextInt(width);
/*  69: 69 */        int y = random.nextInt(height);
/*  70: 70 */        int xl = random.nextInt(12);
/*  71: 71 */        int yl = random.nextInt(12);
/*  72: 72 */        g.drawLine(x, y, x + xl, y + yl);
/*  73:    */      }
/*  74: 74 */      String code = "";
/*  75: 75 */      for (int i = 0; i < 4; i++) {
/*  76: 76 */        String str = com.soofound.cpe.util.CpeUtils.CODES[random.nextInt(com.soofound.cpe.util.CpeUtils.CODES.length)];
/*  77: 77 */        code = code + str;
/*  78:    */        
/*  79: 79 */        g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
/*  80:    */        
/*  81: 81 */        g.drawString(str, 13 * i + 6, 16);
/*  82:    */      }
/*  83: 83 */      response.setHeader("Pragma", "No-cache");
/*  84: 84 */      response.setHeader("Cache-Control", "no-cache");
/*  85: 85 */      response.setDateHeader("Expires", 0L);
/*  86:    */      
/*  87: 87 */      request.getSession().setAttribute("image_captcha", code);
/*  88:    */      
/*  89: 89 */      g.dispose();
/*  90:    */      
/*  91: 91 */      ImageIO.write(image, "JPEG", response.getOutputStream());
/*  92:    */      
/*  93: 93 */      response.getOutputStream().flush();
/*  94: 94 */      response.getOutputStream().close();
/*  95: 95 */      response.flushBuffer();
/*  96:    */    }
/*  97:    */    catch (Exception localException) {}
/*  98:    */  }
/*  99:    */  
/* 100:    */  private Color getRandColor(int fc, int bc) {
/* 101:101 */    Random random = new Random();
/* 102:102 */    if (fc > 255) fc = 255;
/* 103:103 */    if (bc > 255) bc = 255;
/* 104:104 */    int r = fc + random.nextInt(bc - fc);
/* 105:105 */    int g = fc + random.nextInt(bc - fc);
/* 106:106 */    int b = fc + random.nextInt(bc - fc);
/* 107:107 */    return new Color(r, g, b);
/* 108:    */  }
/* 109:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.admin.web.LogAction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */