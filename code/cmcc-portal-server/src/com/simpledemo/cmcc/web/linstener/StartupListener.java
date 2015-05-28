package com.simpledemo.cmcc.web.linstener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.simpledemo.cmcc.portal.protocol.ProtocolListenServer;
import com.simpledemo.cmcc.server.CMCCRadiusServer;

/**
 * Application Lifecycle Listener implementation class StartupListener
 *
 */
public class StartupListener implements ServletContextListener {
	
	private final static Logger logger = Logger.getLogger(StartupListener.class);
	private CMCCRadiusServer cmccRadiusServer = null;

    /**
     * Default constructor. 
     */
    public StartupListener() {
    	
    }
	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  {
    	try {
    		// 启动Radius监听服务
        	cmccRadiusServer = new CMCCRadiusServer("cmcc123");
        	cmccRadiusServer.start(true, true);
        	// 启动AC异常接收监听服务
        	ProtocolListenServer.getInstance().start(5100);
		} catch (Exception e) {
			logger.warn("StartupListener#contextInitialized", e);
			throw new RuntimeException("init startuplistener excetion");
		}
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
    	try {
    		cmccRadiusServer.stop();
		} catch (Exception e) {
			logger.warn("StartupListener#contextDestroyed", e);
		}
    	try {
    		ProtocolListenServer.getInstance().stop();
		} catch (Exception e) {
			logger.warn("StartupListener#contextDestroyed", e);
		}
    }
	
}
