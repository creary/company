package com.simpledemo.cmcc.portal.protocol;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Date;

import org.apache.log4j.Logger;
/**
 * 
 * @author TWY.TOM
 *
 */
public class ProtocolClient {

	static final Logger logger = Logger.getLogger(ProtocolClient.class);
	
	private String sIP = "127.0.0.1";
	private int iPort = 0;
	
	private int iTimeout = 30 * 1000;
	private int iRetry = 3;

	private DatagramSocket clientSocket = null;
	private InetAddress IPAddress = null;

	private DatagramPacket sendPacket = null;
	private DatagramPacket recvPacket = null;
	
	private boolean printLog = false;
	/**
	 * 构造方法
	 * @throws SocketException
	 * @throws UnknownHostException
	 */
	public ProtocolClient() throws SocketException, UnknownHostException {
		this("127.0.0.1", 2000, 20, 3);
	}

	/**
	 * 构造方法
	 * @param ip
	 * @param port
	 * @param timeout 毫秒
	 * @param retry 重复次数
	 * @throws SocketException 
	 * @throws UnknownHostException 
	 * @throws WlanException
	 */
	public ProtocolClient(String ip, int port, int timeout, int retry) throws SocketException, UnknownHostException {
		sIP = ip;
		if (port > 0) {
			iPort = port;
		}
		if (timeout > 0) {
			iTimeout = timeout;
		}
		if (retry > 0) {
			iRetry = retry;
		}
		clientSocket = new DatagramSocket();
		logger.info("new a UDP socket.");
		if (this.printLog) {
			logger.warn("new a UDP socket......");
		}
		clientSocket.setSoTimeout(iTimeout);
		IPAddress = InetAddress.getByName(sIP);
	}

	public void destroy() {
		try {
			if (clientSocket != null) {
				clientSocket.close();
				logger.info("close a UDP socket.");
			}
			clientSocket = null;
		} catch (Exception e) {
			logger.warn("destroy", e);
		}
	}
	/**
	 * 发送字节数据
	 * @param data
	 * @param len
	 * @throws Exception
	 */
	public void sendto(byte[] data, int len) throws Exception {
		int retry = iRetry;
		Exception err = null;

		if (len > data.length) {
			len = data.length;
		}

		sendPacket = new DatagramPacket(data, len, IPAddress,
				iPort);
		if (this.printLog) {
			logger.warn("Send udp datagram packet to...[ip=" + IPAddress + ", port=" + iPort + "]");
		}
		while (retry > 0) {
			try {
				clientSocket.send(sendPacket);
				break;
			} catch (Exception e) {
				System.out.println(new Date() + ": Send UDP packet:"
						+ String.valueOf(retry) + "\n");
				logger.warn("sendto", e);
				retry--;
				err = e;
			}
		}
		if (retry == 0) {
			throw new Exception(err);
		}
	}
	/**
	 * 接收字节数据
	 * @param buff
	 * @param len
	 * @throws Exception
	 */
	public void recvfrom(byte[] buff, int len) throws Exception {
		byte[] data = new byte[len];
		recvPacket = new DatagramPacket(data, len);
		clientSocket.receive(recvPacket);

		byte[] tmp = recvPacket.getData();

		System.arraycopy(tmp, 0, buff, 0, tmp.length);
	}
	/**
	 * 发送数据包
	 * @param tPackage
	 * @throws Exception
	 */
	public void sendPack(SendDataPackage tPackage) throws Exception {
		byte[] data = tPackage.toBytes();
		try {
			sendto(data, data.length);
		} catch (Exception e) {
			throw e;
		}
	}
	/**
	 * 接收数据包
	 * @param tPackage
	 * @throws Exception
	 */
	public void recvPack(ReceiveDataPackage tPackage) throws Exception {
		tPackage.getClass();
		byte[] data = new byte[1024];
		try {
			tPackage.getClass();
			
			this.recvfrom(data, 1024);

			tPackage.decodeHead(data);

			if (tPackage.getVersion() == 2) {
				tPackage.getClass();
				tPackage.decodeBody(data, 32);
			} else {
				tPackage.getClass();
				tPackage.decodeBody(data, 16);
			}
		} catch (Exception e) {
			logger.warn("Receive a packet error");
			throw e;
		}
	}

	public static int byte2int(byte aByte) {
		return (aByte & 0xFF);
	}

	public static String byte2Hex(byte aByte) {
		String buff = "0123456789ABCDEF";
		int tmp = byte2int(aByte);
		int i = tmp / 16;
		int j = tmp - i * 16;
		return buff.substring(i, i + 1) + buff.substring(j, j + 1);
	}

	public static void debug(byte[] buff, int cols, int len) {
		for (int i = 0; i < len; i++) {
			if (i % cols == 0) {
				System.out.println("");
			}
			System.out.print(byte2Hex(buff[i]) + " ");
		}
		System.out.println("");
	}

	public static void debug(byte[] buff, int cols) {
		for (int i = 0; i < buff.length; i++) {
			if (i % cols == 0) {
				System.out.println("");
			}
			System.out.print(byte2Hex(buff[i]) + " ");
		}
	}

	/**
	 * @param printLog the printLog to set
	 */
	public void setPrintLog(boolean printLog) {
		this.printLog = printLog;
	}

}
