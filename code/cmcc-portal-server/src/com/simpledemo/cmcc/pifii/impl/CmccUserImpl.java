package com.simpledemo.cmcc.pifii.impl;

import com.simpledemo.cmcc.pifii.inter.ICmccUser;

public class CmccUserImpl implements ICmccUser {

	
	/**
	 * 判断当前用户是否在线
	 */
	@Override
	public boolean isOnLine() {
		return false;
	
	}
	/**
	 * 指定用户的 状态
	 */
	@Override
	public void Linenow(String user) {
		
	}
	/**
	 * 用户下线操作
	 */
	@Override
	public void outUser(String user) {
		
	}

}
