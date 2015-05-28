package com.simpledemo.cmcc.portal.protocol;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import org.apache.log4j.Logger;
/**
 * <b>Portal服务监听</b>
 * <p>用于接收AC发送是用户异常数据</p>
 * @author TWY.TOM
 *
 */
public class ProtocolListenServer {
	
	static final Logger logger = Logger.getLogger(ProtocolListenServer.class);
	
	private DatagramSocket sock = null;
	private byte[] recvbuf = new byte[4096];
	private DatagramPacket p = null;
	// 监听端口
	private int port = 50100;
	private boolean running = false;
	
	private static ProtocolListenServer instance = new ProtocolListenServer();
	
	private ProtocolListenServer() {
	}

	public static ProtocolListenServer getInstance() {
		return instance;
	}
	
	public synchronized void start(int listenPort) throws IOException {
		if (!running) {
			this.port = listenPort;
			running = true;
			this.createConnSocket();
			new ProtocolServerThread().start();
		}
	}
	
	public synchronized void stop() {
		if (running) {
			running = false;
			try {
				if (this.sock != null) {
					this.sock.close();
				}
			} catch (Exception e) {
				logger.warn("stop", e);
			}
		}
	}
	
	private void createConnSocket() throws IOException {
		try {
			this.sock = new DatagramSocket(this.port);
			this.sock.setSoTimeout(0);//0时无限阻塞
			logger.info("starting ProtocolListenServer on port " + this.port);
		} catch (IOException e) {
			throw e;
		}
	}

	private void loopRecvData() {
		ReceiveDataPackage recvPack = new ReceiveDataPackage();
		try {
			this.p = new DatagramPacket(this.recvbuf, 32);
			this.sock.receive(this.p);
			System.out.println("UDP Server Recieve: ");
			
			byte[] tmp = this.p.getData();
			
			recvPack.decodeHead(tmp);
			recvPack.getClass();
			//recvPack.decodeBody(tmp, 16);

			DatagramPacket sendPack = processRecDataPkg(recvPack);
			if (sendPack != null) {
				DatagramSocket revsoket = null;
				try {
					revsoket = new DatagramSocket();
					revsoket.send(sendPack);
				} catch (Exception e) {
					logger.warn("revsoket", e);
				} finally {
					if (revsoket != null) revsoket.close();
				}
			} else {
				logger.warn("Package error.");
			}
		} catch (Exception e) {
			logger.warn("loopRecvData", e);
		}
	}
	
	protected DatagramPacket processRecDataPkg(ReceiveDataPackage recvPack) throws SocketException {
		if (recvPack.getCode() == Command.NTF_LOGOUT) {
			// 用户异常下线流程
			// AC强制用户下线流程
			SendDataPackage sendPack = new SendDataPackage(Command.ACK_LOGOUT);
			sendPack.setUserIP(recvPack.getUserIP());
			byte[] buf = sendPack.toBytes();
			return new DatagramPacket(buf, buf.length, this.p.getSocketAddress());
		}
		return null;
	}
	/**
	 * 
	 * @author TWY.TOM
	 *
	 */
	private class ProtocolServerThread extends Thread {
		
		@Override
		public void run() {
			while (running) {
				try {
					ProtocolListenServer.this.loopRecvData();
				} catch (Exception e) {
					logger.warn("UDPServerThread.run", e);
				}
			}
		}
		
	}
	
}
