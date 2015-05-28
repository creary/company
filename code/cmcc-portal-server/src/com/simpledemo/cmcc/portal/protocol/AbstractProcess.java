package com.simpledemo.cmcc.portal.protocol;

import java.util.Map;

import org.apache.log4j.Logger;
/**
 * 抽象流程处理
 * @author TWY.TOM
 *
 */
public abstract class AbstractProcess {

	static final Logger logger = Logger.getLogger(AbstractProcess.class);
	
	protected ParamsObject params;
	protected PackageConnectionHandler handler;
	protected int timeout = 60 * 1000;
	protected int retry = 3;
	
	public AbstractProcess(ParamsObject params) throws Exception {
		this.params = params;
		boolean printLog = this.isPrintDebugLog();
		handler = new PackageConnectionHandler(params.ip, params.port, timeout, retry, printLog);
	}
	/**
	 * 处理流程
	 * @return Object
	 * @throws Exception
	 */
	public final Object doHandler() throws Exception {
		try {
			return this.process();
		} catch (Exception e) {
			logger.warn("AbstractProcess#doHandler: " + e.getMessage());
			throw e;
		} finally {
			if (handler != null) {
				handler.destroy();
			}
		}
	}
	
	protected boolean isPrintDebugLog() {
		return true;
	}
	
	protected abstract Object process() throws Exception;
	/**
	 * 参数对象
	 * @author TWY.TOM
	 *
	 */
	public static class ParamsObject {

		public Map<String, String> params;
		public String ip;
		public int port;
		
		public String userip;
		public String username;
		public String password;
		
	}
	
}
