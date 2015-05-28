package com.simpledemo.cmcc.portal.protocol;

import java.net.SocketException;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
/**
 * 
 * @author TWY.TOM
 *
 */
public class PackageConnectionHandler {
	private ProtocolClient tClient = null;
	//private static Dictionary tDict = new Dictionary();
	static final Logger logger = Logger.getLogger(PackageConnectionHandler.class);
	private SendDataPackage sendPack = null;
	private ReceiveDataPackage recvPack = null;

	/**
	 * 
	 * @param ip IP
	 * @param port 端口
	 * @param timeout 超时(ms)
	 * @param retry 重试次数
	 * @throws UnknownHostException 
	 * @throws SocketException 
	 * @throws WlanException 
	 */
	public PackageConnectionHandler(String ip, int port, int timeout, int retry, boolean printLog) throws Exception {
		logger.debug("ip 地址:"+ip+":port 端口:"+port);
		tClient = new ProtocolClient(ip, port, timeout, retry);
		tClient.setPrintLog(printLog);
	}
	
	public ReceiveDataPackage sandDataPackage(SendDataPackage sendpkg) throws Exception {
		if (tClient != null) {
			this.sendPack = sendpkg;
			tClient.sendPack(sendpkg);
			this.recvPack = new ReceiveDataPackage();
			if (this.sendPack.getVersion() == 2) {
				this.recvPack.setVersionV2();
			}
			tClient.recvPack(recvPack);
			return recvPack;
		}
		return null;
	}
	
	public void sendAndNotReceive(SendDataPackage sendpkg) throws Exception {
		tClient.sendPack(sendpkg);
	}
	
	public void destroy() {
		if (tClient != null) {
			tClient.destroy();
		}
	}
	
}
