package com.simpledemo.cmcc.server;
import java.net.InetSocketAddress;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

import com.radius.server.attribute.RadiusAttribute;
import com.radius.server.packet.AccessRequest;
import com.radius.server.packet.AccountingRequest;
import com.radius.server.packet.RadiusPacket;
import com.radius.server.util.RadiusException;
import com.radius.server.util.RadiusServer;

/**
 * CMCC Radius服务
 * @author TWY.TOM
 *
 */
public class CMCCRadiusServer extends RadiusServer {

	private String sharedSecret;
	static final Logger logger = Logger.getLogger(RadiusServer.class);
	/**
	 * 构造方法
	 * @param sharedSecret 加密key
	 */
	public CMCCRadiusServer(String sharedSecret) {
		logger.debug("CMCCRadiusServer ()构造方法");
		this.sharedSecret = sharedSecret;
	}
	/**
	 * 构造方法
	 * @param authPort 认证端口
	 * @param acctPort 计费端口
	 * @param sharedSecret 加密key
	 */
	public CMCCRadiusServer(int authPort, int acctPort, String sharedSecret) {
		super(authPort, acctPort);
		logger.debug(" 认证处理 CMCCRadiusServer ---> authPort  认证端口: "+authPort+"acctPort 计费端口 :"+acctPort+"加密key:"+sharedSecret);
		this.sharedSecret = sharedSecret;
	}
	/**
	 * 认证处理
	 */
	@Override
	public RadiusPacket accessRequestReceived(AccessRequest accessRequest,
			InetSocketAddress client) throws RadiusException {
		
		logger.debug(" 认证处理 accessRequestReceived()");
		List<RadiusAttribute> list= accessRequest.getAttributes();
		for (RadiusAttribute radiusAttribute : list) {
			logger.debug(" RadiusAttribute 信息：" +radiusAttribute);
		}
		/*String password = getUserPassword(accessRequest.getUserName());
		int type = RadiusPacket.ACCESS_REJECT;
		if (password != null
				&& accessRequest.verifyPassword(password)) {
			type = RadiusPacket.ACCESS_ACCEPT;
		}*/
		int type = RadiusPacket.ACCESS_ACCEPT;
		RadiusPacket answer = new RadiusPacket(type, accessRequest.getPacketIdentifier());
		copyProxyState(accessRequest, answer);
		answer.addAttribute("User-Name", accessRequest.getUserName());
		// 配置实时计费信息采集的时间间隔,以秒为单位。
		answer.addAttribute("Acct-Interim-Interval", String.valueOf(60));
		if (type == RadiusPacket.ACCESS_ACCEPT) {
			logger.debug("认证成功");
			// 认证成功
			// 配置WLAN用户每次连接最长时间。超过该时长后，用户被切断。
			// answer.addAttribute("Session-Timeout", "");
			// 用户的闲置切断时间，以秒为单位
			// answer.addAttribute("Idle-Timeout", "");
			
		}
		return answer;
	}

	/**
	 * 计费处理
	 */
	@SuppressWarnings("unused")
	@Override
	public RadiusPacket accountingRequestReceived(
			AccountingRequest accountingRequest, InetSocketAddress client)
			throws RadiusException {
		// 不同AC可以打印包信息查看属性
		// System.out.println(accountingRequest);
		logger.debug("计费处理 ："+accountingRequest);
		RadiusPacket answer = super.accountingRequestReceived(accountingRequest, client);
		String ipaddr = accountingRequest.getAttributeValue("Framed-IP-Address");
		String sessionid = accountingRequest.getAttributeValue("Acct-Session-Id");
		String acctStatusType = accountingRequest.getAttributeValue("Acct-Status-Type");
		String acip = accountingRequest.getAttributeValue("NAS-IP-Address");
		String mac = accountingRequest.getAttributeValue("Calling-Station-Id");
		String inputdata = accountingRequest.getAttributeValue("Acct-Input-Octets");
		String outputdata = accountingRequest.getAttributeValue("Acct-Output-Octets");
		String sessiontime = accountingRequest.getAttributeValue("Acct-Session-Time");
		
		logger.debug("ipaddr"+ipaddr+":sessionid:"+sessionid+":acctStatusType:"+acctStatusType+":acip:"+acip+":mac:"+mac+":inputdata:"+inputdata+"outputdata\n"+outputdata);
		logger.debug("sessiontime"+sessiontime);
		return answer;
	}
	
	@Override
	public String getSharedSecret(InetSocketAddress client) {
		// 加密key
		
		return sharedSecret;
	}
	/**
	 * 根据用户名获取用户数据库密码
	 * @param userName 用户名
	 */
	@Override
	public String getUserPassword(String userName) {
		return null;
	}

}
