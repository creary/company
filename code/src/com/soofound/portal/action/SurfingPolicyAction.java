/*   1:    */package com.soofound.portal.action;
/*   2:    */
/*   3:    */import com.soofound.admin.bean.UserDto;
/*   4:    */import com.soofound.framework.web.BaseAction;
/*   5:    */import com.soofound.portal.bean.SurfingPolicyDto;
/*   6:    */import com.soofound.portal.service.SurfingPolicyService;
/*   7:    */import java.util.Map;
/*   8:    */import javax.servlet.http.HttpServletRequest;
/*   9:    */import org.springframework.beans.factory.annotation.Autowired;
/*  10:    */import org.springframework.stereotype.Controller;
/*  11:    */import org.springframework.ui.ModelMap;
/*  12:    */import org.springframework.web.bind.annotation.RequestMapping;
/*  13:    */import org.springframework.web.bind.annotation.ResponseBody;
/*  14:    */
/*  15:    */@Controller
/*  16:    */public final class SurfingPolicyAction
/*  17:    */  extends BaseAction<SurfingPolicyService>
/*  18:    */{
/*  19:    */  @RequestMapping({"/portal/policyListJsp.do"})
/*  20:    */  public String listJsp(HttpServletRequest request, ModelMap model)
/*  21:    */  {
/*  22: 22 */    return "/portal/apface/policyList.jsp";
/*  23:    */  }
/*  24:    */  
/*  25:    */  @RequestMapping({"/portal/policyList.do"})
/*  26:    */  @ResponseBody
/*  27: 27 */  public Map<String, Object> list(HttpServletRequest request, ModelMap model) { return super.listByPage(request, model); }
/*  28:    */  
/*  29:    */  @RequestMapping({"/portal/policyReadyEdit.do"})
/*  30:    */  public String readyEdit(HttpServletRequest request, ModelMap model)
/*  31:    */  {
/*  32: 32 */    String id = request.getParameter("id");
/*  33: 33 */    SurfingPolicyDto dto = null;
/*  34: 34 */    if ((id == null) || ("0".equals(id))) {
/*  35: 35 */      model.addAttribute("title", "增加");
/*  36: 36 */      model.addAttribute("image", "add.gif");
/*  37: 37 */      dto = SurfingPolicyDto.bornDefaultPolicy();
/*  38:    */    } else {
/*  39: 39 */      model.addAttribute("title", "编辑");
/*  40: 40 */      model.addAttribute("image", "edit.gif");
/*  41: 41 */      dto = (SurfingPolicyDto)((SurfingPolicyService)this.service).findByID(id);
/*  42:    */    }
/*  43: 43 */    model.addAttribute("dto", dto);
/*  44: 44 */    return "/portal/apface/policyEdit.jsp";
/*  45:    */  }
/*  46:    */  
/*  47:    */  @RequestMapping({"/portal/policySimpleSaveOrUpdate.do"})
/*  48:    */  @ResponseBody
/*  49:    */  public Map<String, Object> simpleSaveOrUpdate(HttpServletRequest request, ModelMap model) {
/*  50: 50 */    try { UserDto user = getCurrentUser(request);
/*  51: 51 */      SurfingPolicyDto dto = new SurfingPolicyDto();
/*  52: 52 */      dto.setId(Integer.parseInt(request.getParameter("id")));
/*  53: 53 */      dto.setBranchId(user.getBranchId());
/*  54: 54 */      dto.setName(request.getParameter("name"));
/*  55: 55 */      SurfingPolicyDto _dto = ((SurfingPolicyService)this.service).getPolicyByName(dto.getBranchId(), dto.getName());
/*  56: 56 */      if ((dto.getId() == 0) && (_dto != null))
/*  57: 57 */        return super.getFailedResult("保存失败,[" + dto.getName() + "]已经存在.");
/*  58: 58 */      if ((dto.getId() > 0) && (_dto != null) && (dto.getId() != _dto.getId()))
/*  59: 59 */        return super.getFailedResult("保存失败,[" + dto.getName() + "]已经存在.");
/*  60: 60 */      dto.setTag("1".equals(request.getParameter("tag")) ? 1 : 0);
/*  61: 61 */      ((SurfingPolicyService)this.service).simpleSaveOrUpdate(dto);
/*  62: 62 */      Map<String, Object> result = super.getSucceedResult("保存成功");
/*  63: 63 */      result.put("data", dto.getId());
/*  64: 64 */      return result;
/*  65:    */    } catch (Exception ex) {
/*  66: 66 */      ex.printStackTrace(); }
/*  67: 67 */    return super.getFailedResult("Failed");
/*  68:    */  }
/*  69:    */  
/*  70:    */  @RequestMapping({"/portal/policyReadyEditAuth.do"})
/*  71:    */  public String readyEditAuth(HttpServletRequest request, ModelMap model)
/*  72:    */  {
/*  73: 73 */    String id = request.getParameter("id");
/*  74: 74 */    if ("-1".equals(id)) {
/*  75: 75 */      model.addAttribute("message", "认证策略不存在");
/*  76: 76 */      return "/common/hint.jsp";
/*  77:    */    }
/*  78: 78 */    SurfingPolicyDto dto = null;
/*  79: 79 */    if ("0".equals(id)) {
/*  80: 80 */      dto = SurfingPolicyDto.bornDefaultPolicy();
/*  81:    */    } else
/*  82: 82 */      dto = (SurfingPolicyDto)((SurfingPolicyService)this.service).findByID(id);
/*  83: 83 */    model.addAttribute("dto", dto);
/*  84: 84 */    return "/portal/apface/policyAuth.jsp";
/*  85:    */  }
/*  86:    */  
/*  87:    */  @RequestMapping({"/portal/policyReadyEditAdvanced.do"})
/*  88:    */  public String readyEditAdvanced(HttpServletRequest request, ModelMap model) {
/*  89: 89 */    String id = request.getParameter("id");
/*  90: 90 */    SurfingPolicyDto dto = null;
/*  91: 91 */    if ((id == null) || ("0".equals(id))) {
/*  92: 92 */      dto = SurfingPolicyDto.bornDefaultPolicy();
/*  93:    */    } else
/*  94: 94 */      dto = (SurfingPolicyDto)((SurfingPolicyService)this.service).findByID(id);
/*  95: 95 */    model.addAttribute("dto", dto);
/*  96: 96 */    return "/portal/apface/policyAdvance.jsp";
/*  97:    */  }
/*  98:    */  
/*  99:    */  @RequestMapping({"/portal/policySaveOrUpdate.do"})
/* 100:    */  @ResponseBody
/* 101:101 */  public Map<String, Object> saveOrUpdate(HttpServletRequest request, ModelMap model) { Map<String, Object> result = super.update(request, model);
/* 102:102 */    String id = request.getParameter("id");
/* 103:103 */    SurfingPolicyDto dto = (SurfingPolicyDto)((SurfingPolicyService)this.service).findByID(id);
/* 104:104 */    logOperation(request, "修改认证策略:" + dto.getName());
/* 105:105 */    return result;
/* 106:    */  }
/* 107:    */  
/* 108:    */  @RequestMapping({"/portal/policyDelete.do"})
/* 109:    */  @ResponseBody
/* 110:110 */  public Map<String, Object> delete(HttpServletRequest request, ModelMap model) { return super.deleteByIDs(request, model); }
/* 111:    */  
/* 112:    */  @Autowired
/* 113:    */  protected void setService(SurfingPolicyService service)
/* 114:    */  {
/* 115:115 */    this.service = service;
/* 116:    */  }
/* 117:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.action.SurfingPolicyAction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */