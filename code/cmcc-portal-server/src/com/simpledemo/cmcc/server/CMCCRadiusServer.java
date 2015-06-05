package com.simpledemo.cmcc.server;
import java.net.InetSocketAddress;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

import com.radius.server.attribute.RadiusAttribute;
import com.radius.server.packet.AccessRequest;
import com.radius.server.packet.AccountingRequest;
import com.radius.server.packet.RadiusPacket;
import com.radius.server.util.RadiusException;
import com.radius.server.util.RadiusServer;
import com.simpledemo.cmcc.pifii.dao.UserDAO;

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
		//验证密码：
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
		List<RadiusAttribute> list=	accountingRequest.getAttributes();
		for (RadiusAttribute radiusAttribute : list) {
			logger.debug("radiusAttribute 信息："+radiusAttribute);
		}
		String ipaddr = accountingRequest.getAttributeValue("Framed-IP-Address");//RADIUS服务器为用户分配的IP地址，0xFFFFFFFE表示RADIUS服务器不分配地址，而由设备为用户分配IP地址
		String sessionid = accountingRequest.getAttributeValue("Acct-Session-Id");//计费的连接号，对于同一个连接的开始计费、实时计费和停止计费报文，其中的连接号必须相同
		String acctStatusType = accountingRequest.getAttributeValue("Acct-Status-Type");//计费报文类型，1表示开始计费报文，2表示停止计费报文，3表示实时计费报文
		String acip = accountingRequest.getAttributeValue("NAS-IP-Address");//设备IP地址，如果RADIUS服务器组绑定了接口地址，则取绑定的接口地址，否则取发送报文的接口地址
		String mac = accountingRequest.getAttributeValue("Calling-Station-Id");//允许NAS发送主叫号码
		String inputdata = accountingRequest.getAttributeValue("Acct-Input-Octets");//上行字节数，单位为Byte、kbyte、Mbyte、Gbyte，具体使用何种单位可通过命令配置
		String outputdata = accountingRequest.getAttributeValue("Acct-Output-Octets");//下行字节数，单位为Byte、kbyte、Mbyte、Gbyte，具体使用何种单位可通过命令配置
		String sessiontime = accountingRequest.getAttributeValue("Acct-Session-Time");//用户的上线时间，以秒为单位
		logger.debug("ipaddr"+ipaddr+":sessionid:"+sessionid+":acctStatusType:"+acctStatusType+":acip:"+acip+":mac:"+mac+":inputdata:"+inputdata+"outputdata\n"+outputdata);
		logger.debug("sessiontime"+sessiontime);
		try {
			if(UserDAO.isThere(mac)){
				 int num =(Integer) UserDAO.selectIsonLine(mac);
				 UserDAO.updateIsonLine(mac, num+1);
			}else {
				UserDAO.insertNumber(mac);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("accountingRequestReceived 错误  判断在线");
		}
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
