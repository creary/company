package com.simpledemo.cmcc.pifii.test;
/**
 * 计费处理
 */
public class RadiusJiTest {
	/*
	@Override
	public RadiusPacket accountingRequestReceived(
			AccountingRequest accountingRequest, InetSocketAddress client)
			throws RadiusException {
		// 不同AC可以打印包信息查看属性
		// System.out.println(accountingRequest);
		RadiusPacket answer = super.accountingRequestReceived(accountingRequest, client);
		///////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////
		// 用户名
		String username = accountingRequest.getAttributeValue("User-Name");
		// 用户IP
		String ipaddr = accountingRequest.getAttributeValue("Framed-IP-Address");
		// 回话ID
		String sessionid = accountingRequest.getAttributeValue("Acct-Session-Id");
		// 数据包状态
		String acctStatusType = accountingRequest.getAttributeValue("Acct-Status-Type");
		// AC IP
		String acip = accountingRequest.getAttributeValue("NAS-IP-Address");
		// 用户MAC
		String mac = accountingRequest.getAttributeValue("Calling-Station-Id");
		// 下行流量
		String inputdata = accountingRequest.getAttributeValue("Acct-Input-Octets");
		// 上行流量
		String outputdata = accountingRequest.getAttributeValue("Acct-Output-Octets");
		// 处理数据包
		if("Start".equalsIgnoreCase(acctStatusType)) {
			// 插入登录记录
			System.out.println("sessionid=" + sessionid + "," 
					+ "username=" + username + ","
					 + "ipaddr=" + ipaddr + ","
					 + "acip=" + acip + ","
					 + "mac=" + mac + ","
					 + "inputdata=" + inputdata + ","
					 + "outputdata=" + outputdata + ","
					 + "login=" + new Date() + ",");
		} else if ("Active".equalsIgnoreCase(acctStatusType)) {
			// Active 这个值根据不同设备有些不同
			// 根据sessionid查找登录记录，并更新登录流量、时长信息
			System.out.println("sessionid=" + sessionid + "," 
					+ "username=" + username + ","
					 + "ipaddr=" + ipaddr + ","
					 + "acip=" + acip + ","
					 + "mac=" + mac + ","
					 + "inputdata=" + inputdata + ","
					 + "outputdata=" + outputdata + ","
					 + "update=" + new Date() + ",");
		} else if ("Stop".equalsIgnoreCase(acctStatusType)) {
			// 根据sessionid查找登录记录，并更新登录流量、时长信息，网上网历史表，插入上网历史记录
			// 删除该条登录信息
			System.out.println("sessionid=" + sessionid + "," 
					+ "username=" + username + ","
					 + "ipaddr=" + ipaddr + ","
					 + "acip=" + acip + ","
					 + "mac=" + mac + ","
					 + "inputdata=" + inputdata + ","
					 + "outputdata=" + outputdata + ","
					 + "logout=" + new Date() + ",");
		}
		///////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////
		return answer;
	}
*/}
