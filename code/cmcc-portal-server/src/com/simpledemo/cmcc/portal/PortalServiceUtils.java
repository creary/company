package com.simpledemo.cmcc.portal;

/**
 * 
 * @author TWY.TOM
 *
 */
public class PortalServiceUtils {

	public static short getWlanSerialNo() {
		// 一定时期内不重复
		// 请实现该序列号生成规则
		short s=	(short) System.currentTimeMillis();
		return s;
	}
	
}
