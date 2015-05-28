package com.simpledemo.cmcc.portal;

import org.apache.log4j.Logger;

import com.simpledemo.cmcc.portal.protocol.AbstractProcess;
import com.simpledemo.cmcc.portal.protocol.Command;
import com.simpledemo.cmcc.portal.protocol.ReceiveDataPackage;
import com.simpledemo.cmcc.portal.protocol.SendDataPackage;

public class UserLogoutProcess extends AbstractProcess {
	
	static final Logger logger = Logger.getLogger(UserLogoutProcess.class);

	public UserLogoutProcess(ParamsObject params) throws Exception {
		super(params);
		logger.debug("下线操作");
	}
	
	@Override
	protected Object process() throws Exception {
		return this.logout();
	}
	
	private Object logout() throws Exception {
		
		SendDataPackage sendpkg = new SendDataPackage(Command.REQ_LOGOUT);
		sendpkg.setVersion();
		sendpkg.setCHAP();
		sendpkg.setUserIP(params.userip);
		sendpkg.setSerialNo(PortalServiceUtils.getWlanSerialNo());
		
		if (super.isPrintDebugLog()) sendpkg.debug();
		
		ReceiveDataPackage recpkg = handler.sandDataPackage(sendpkg);
		
		if (recpkg != null && super.isPrintDebugLog()) recpkg.debug();
		if (recpkg != null 
				&& recpkg.getCode() == Command.ACK_LOGOUT 
				&& recpkg.getId() == sendpkg.getId()) {
			//ErrCode＝0，表示AC设备告诉Portal Server此用户下线成功；
			//ErrCode＝1，表示AC设备告诉Portal Server此用户下线被拒绝；
			//ErrCode＝2，表示AC设备告诉Portal Server此用户下线失败（发生错误）；
			if (recpkg.getErrCode() == 0) {
				return null;
			}
		}
		if (recpkg != null) {
			logger.warn("Logout Fail, code=" + recpkg.getErrCode() + ", " +
					"msg=" + Command.errorStrEn((byte) recpkg.getCode(), recpkg.getErrCode()));
		} else {
			logger.warn("Logout Fail");
		}
		return "Logout Fail";
	}

}
