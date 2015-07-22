package com.simpledemo.cmcc.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * 
	* @Title: getNow 
	* @Description: TODO(格式化当前时间为 yyyy-MM-dd HH:mm:ss ) 
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public static  String getNow(){
		return sdf.format(new Date());
	}
	
	
	
}
