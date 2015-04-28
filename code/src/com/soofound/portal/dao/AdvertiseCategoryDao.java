/*  1:   */package com.soofound.portal.dao;
/*  2:   */
/*  3:   */import com.soofound.framework.jdbc.BaseDao;
/*  4:   */import com.soofound.framework.util.CommonUtil;
/*  5:   */import com.soofound.portal.bean.AdvertiseCategoryBean;
/*  6:   */import java.util.ArrayList;
/*  7:   */import java.util.List;
/*  8:   */import java.util.Map;
/*  9:   */import org.springframework.stereotype.Component;
/* 10:   */
/* 11:   */@Component
/* 12:   */public final class AdvertiseCategoryDao
/* 13:   */  extends BaseDao<AdvertiseCategoryBean>
/* 14:   */{
/* 15:   */  protected String getQuerySQL(Map<String, String> options)
/* 16:   */  {
/* 17:17 */    StringBuilder sqlText = new StringBuilder();
/* 18:18 */    sqlText.append("select * from surfing_advertise_category where 1=1 ");
/* 19:19 */    if (!CommonUtil.isEmpty((String)options.get("branchId")))
/* 20:20 */      sqlText.append(" and branch_id like '").append((String)options.get("branchId")).append("%'");
/* 21:21 */    if (!CommonUtil.isEmpty((String)options.get("name")))
/* 22:22 */      sqlText.append(" and name like '%").append((String)options.get("name")).append("%'");
/* 23:23 */    sqlText.append(" order by id");
/* 24:24 */    return sqlText.toString();
/* 25:   */  }
/* 26:   */  
/* 27:   */  public AdvertiseCategoryBean findByName(String name) {
/* 28:28 */    String sql = "select * from surfing_advertise_category where name='" + name + "'";
/* 29:29 */    return (AdvertiseCategoryBean)super.findOne(sql);
/* 30:   */  }
/* 31:   */  
/* 32:   */  public List<AdvertiseCategoryBean> getCpeCategories() {
/* 33:33 */    String sql = "select * from surfing_advertise_category where branch_id = 'cpe'";
/* 34:34 */    return super.findByCriteria(sql);
/* 35:   */  }
/* 36:   */  
/* 37:   */  public List<AdvertiseCategoryBean> getBranchCategories(String branchId) {
/* 38:38 */    String sql = "select * from surfing_advertise_category where branch_id = '" + branchId + "'";
/* 39:39 */    return super.findByCriteria(sql);
/* 40:   */  }
/* 41:   */  
/* 42:   */  public int delete(String[] ids) {
/* 43:43 */    List<String> sqls = new ArrayList();
/* 44:44 */    for (String id : ids) {
/* 45:45 */      sqls.add("delete from surfing_advertise_category where id=" + id);
/* 46:46 */      sqls.add("update surfing_advertisement set category_id=0 where category_id=" + id);
/* 47:   */    }
/* 48:48 */    return batchUpdate(sqls);
/* 49:   */  }
/* 50:   */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.portal.dao.AdvertiseCategoryDao
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */