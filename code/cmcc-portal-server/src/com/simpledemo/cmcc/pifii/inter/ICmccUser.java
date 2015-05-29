package com.simpledemo.cmcc.pifii.inter;

public interface ICmccUser {
	/**
	 * 
	* @Title: isOnLine 
	* @Description: TODO(用户是否在线) 
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @throws
	 */
	public boolean isOnLine();
	/**
	 * 
	* @Title: Linenow 
	* @Description: TODO(查询用户状态) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void Linenow(String user);
	/**
	 * 
	* @Title: outUser 
	* @Description: TODO(用户下线操作) 
	* @param     需要下线的用户
	* @return void    可以根据用户是否在线判断 用户是否下线成功
	* @throws
	 */
	public void outUser(String user);
}
