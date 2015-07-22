package com.simpledemo.cmcc.portal;

import org.apache.log4j.Logger;

import com.simpledemo.cmcc.portal.protocol.AbstractProcess;
import com.simpledemo.cmcc.portal.protocol.Command;
import com.simpledemo.cmcc.portal.protocol.Dictionary;
import com.simpledemo.cmcc.portal.protocol.ReceiveDataPackage;
import com.simpledemo.cmcc.portal.protocol.SendDataPackage;
/**
 * 用户登录流程
 * @author TWY.TOM
 *
 */
public class UserLoginProcess extends AbstractProcess {

	static final Logger logger = Logger.getLogger(UserLoginProcess.class);
	
	public UserLoginProcess(ParamsObject params) throws Exception {
		super(params);
		logger.debug(" UserLoginProcess 登陆处理流程 ");
	}
	@Override
	protected Object process() throws Exception {
		// 与AC握手，获取密码加密KEY
		Object[] objects = this.challgnge();
		if (objects.length == 3) {
			// 认证
			logger.debug("认证");
			for (Object object : objects) {
				logger.debug("object 认证信息： "+object);
			}
			return auth((String)objects[0], (byte[])objects[1], Integer.parseInt(objects[2].toString()));
		} else {
			return objects[0];
		}
	}
	
	/**
	 * 握手
	 * @return
	 * @throws Exception
	 */
	private Object[] challgnge() throws Exception {
		logger.debug(" 握手  challgnge () ");
		SendDataPackage sendpkg = new SendDataPackage(Command.REQ_CHALLENGE);
		sendpkg.setVersion();
		sendpkg.setCHAP();
		sendpkg.setUserIP(params.userip);
		// 请统一修改PortalServiceUtils.getWlanSerialNo()生成
		sendpkg.setSerialNo(PortalServiceUtils.getWlanSerialNo());
		
		// debug
		if (super.isPrintDebugLog()) {
			sendpkg.debug();
		}
		
		ReceiveDataPackage recpkg = handler.sandDataPackage(sendpkg);
		
		// debug
		if (super.isPrintDebugLog() && recpkg != null) {
			recpkg.debug();
		}
		if (recpkg != null 
				&& recpkg.getCode() == Command.ACK_CHALLENGE 
				&& recpkg.getId() == sendpkg.getId()) {
			//ErrCode＝0，表示AC设备告诉Portal Server请求Challenge成功；
			//ErrCode＝1，表示AC设备告诉Portal Server请求Challenge被拒绝； 
			//ErrCode＝2，表示AC设备告诉Portal Server此链接已建立；
			//ErrCode＝3，表示AC设备告诉Portal Server有一个用户正在认证过程中，请稍后再试；
			//ErrCode＝4，则表示AC设备告诉Portal Server此用户请求Challenge失败（发生错误）；
			logger.debug(" challgnge () 认证码 ："+recpkg.getCode()+"changeller()方法中： recpkg.getErrCode() "+recpkg.getErrCode());
			if (recpkg.getErrCode() == 0) {
				Object[] objects = new Object[3];
				for (Object object : objects) {
					logger.debug("changller() 中握手信息 ："+object); 
				}
				objects[0] = recpkg.getUserIPStr();
				//objects[1] = recpkg.getBodyItemStr("Challenge");
				objects[1] = recpkg.getBodyItemBytes("Challenge");
				objects[2] = recpkg.getReqId();
				logger.debug("recpkg.getUserIPStr()" +recpkg.getUserIPStr());
				logger.debug("recpkg.getBodyItemBytes" +recpkg.getBodyItemBytes("Challenge"));
				logger.debug(" recpkg.getReqId()" + recpkg.getReqId());
				return objects;
			} else if (recpkg.getErrCode() == 2) {
				return new Object[]{"已经验证,不需要再验证"};
			} else if (recpkg.getErrCode() == 3) {
				return new Object[]{"有一个用户正在认证过程中，请稍后再试"};
			} else if (recpkg.getErrCode() == 4) {
				return new Object[]{"用户请求Challenge失败（发生错误）"};
			}
		}
		logger.warn("Challenge Fail: " + params.username+":error code "+recpkg.getErrCode());
		return new Object[]{"验证失败,请重新尝试"};
	}
	
	/**
	 * 通知AC到AAA认证
	 * @param ip
	 * @param challgnge
	 * @param reqid
	 * @return
	 * @throws Exception
	 */
	private String auth(String ip, byte[] challgnge, int reqid) throws Exception {
		logger.debug("通知AC到AAA认证 ");
		SendDataPackage sendpkg = new SendDataPackage(Command.REQ_AUTH);
		sendpkg.setVersion();
		sendpkg.setCHAP();
		sendpkg.setUserIP(params.userip);
		sendpkg.setReqId((short) reqid);
		sendpkg.setSerialNo(PortalServiceUtils.getWlanSerialNo());
		if (params.username != null || params.username.trim().length() == 0) {
			sendpkg.addBodyItem(Dictionary.USER_NAME, params.username);
		} else {
			throw new Exception("User name is null");
		}
		sendpkg.addBodyItem(Dictionary.CHAP_PASSWORD, Command.chapPassword((short)reqid, params.password, challgnge));
		
		// debug
		if (super.isPrintDebugLog()) sendpkg.debug();
		
		ReceiveDataPackage recpkg = handler.sandDataPackage(sendpkg);
		
		// debug
		if (super.isPrintDebugLog() && recpkg != null) recpkg.debug();
		
		if (recpkg != null 
				&& recpkg.getCode() == Command.ACK_AUTH 
				&& recpkg.getId() == sendpkg.getId()) {
			//ErrCode＝0，表示AC设备告诉Portal Server此用户认证成功；
			//ErrCode＝1，表示AC设备告诉Portal Server此用户认证请求被拒绝；
			//ErrCode＝2，表示AC设备告诉Portal Server此链接已建立； 
			//ErrCode＝3，表示AC设备告诉Portal Server有一个用户正在认证过程中，请稍后再试；
			//ErrCode＝4 ，表示AC设备告诉Portal Server此用户认证失败（发生错误）；
			logger.debug("认证码："+recpkg.getCode()+": auth()方法: recpkg.getErrCode() "+recpkg.getErrCode());
			if (recpkg.getErrCode() == 0) {
				// 认证成功后，通知AC
				return this.authOK(reqid, sendpkg.getId());
			} else if (recpkg.getErrCode() == 2) {
				
				return "已经验证,不需要再验证";
			} else if (recpkg.getErrCode() == 3) {
				return "有一个用户正在认证过程中，请稍后再试";
			} else if (recpkg.getErrCode() == 4) {
				return "此用户认证失败";
			}
		}
		logger.info("Auth Fail: auth()  认证码 "+recpkg.getErrCode()+"主要认证参数username：" + params.username);
		return "验证失败,请重新尝试";
	}
	
	/**
	 * 通知AC，认证成功，可以计费
	 * @param reqid
	 * @param serialNo
	 * @return
	 * @throws Exception
	 */
	private String authOK(int reqid, short serialNo) throws Exception {
		logger.debug("通知AC，认证成功，可以计费");
		SendDataPackage sendpkg = new SendDataPackage(Command.AFF_ACK_AUTH);
		sendpkg.setVersion();
		sendpkg.setCHAP();
		sendpkg.setUserIP(params.userip);
		sendpkg.setReqId((short) reqid);
		sendpkg.setSerialNo(serialNo);
		if (super.isPrintDebugLog()) sendpkg.debug();
		handler.sendAndNotReceive(sendpkg);
		return null;
	}

}
