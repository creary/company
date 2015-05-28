package com.simpledemo.cmcc.portal.protocol;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * 协议交互命令
 * @author TWY.TOM
 *
 */
public class Command {
	
	public static final byte REQ_CHALLENGE = 1;
	public static final byte ACK_CHALLENGE = 2;
	public static final byte REQ_AUTH = 3;
	public static final byte ACK_AUTH = 4;
	public static final byte REQ_LOGOUT = 5;
	public static final byte ACK_LOGOUT = 6;
	public static final byte AFF_ACK_AUTH = 7;
	public static final byte NTF_LOGOUT = 8;
	public static final byte REQ_INFO = 9;
	public static final byte ACK_INFO = 10;
	public static final byte WEB_STATUS_NOTIFY = -127;
	public static final byte WEB_ACK_STATUS_NOTIFY = -126;

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
	/**
	 * 密码加密
	 * @param reqId
	 * @param sPwd
	 * @param sChallenge
	 * @return
	 */
	public static byte[] chapPassword(short reqId, String sPwd,
			byte[] sChallenge) {
		
		MessageDigest md5 = getMd5Digest();
		md5.reset();
	    md5.update((byte) reqId);
	    md5.update(getUtf8Bytes(sPwd));
	    byte[] chapHash = md5.digest(sChallenge);
	    return chapHash;
		
	}
	
	static MessageDigest md5Digest = null;
	
	protected static MessageDigest getMd5Digest() {
		if (md5Digest == null)
			try {
				md5Digest = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException nsae) {
				throw new RuntimeException("md5 digest not available", nsae);
			}
		return md5Digest;
	}

	public static void main(String[] args) {
		short reqId = 46;
		String sPwd = "123";
		byte[] sc = new byte[16];

		sc[0] = -95;
		sc[1] = 51;
		sc[2] = -21;
		sc[3] = 61;
		sc[4] = -23;
		sc[5] = -15;
		sc[6] = -55;
		sc[7] = 125;
		sc[8] = 117;
		sc[9] = 117;
		sc[10] = 111;
		sc[11] = 13;
		sc[12] = -103;
		sc[13] = 7;
		sc[14] = -9;
		sc[15] = -103;

		chapPassword(reqId, sPwd, sc);
	}
	
	public static byte[] getUtf8Bytes(String str) {
		try {
			return str.getBytes("UTF-8");
		} catch (UnsupportedEncodingException uee) {
			return str.getBytes();
		}
	}

	public static String errorStrEn(byte code, byte errNo) {
		switch (code) {
		case 2:
			switch (errNo) {
			case 0:
				return "BRAS Response Challenge Success.";
			case 1:
				return "BRAS Reject The Challenge Request.";
			case 2:
				return "Connection Have Builded. Challenge Fail! please <a href='<!--ACTION_URL-->?func=Logout'><img border='0' src='<!--IMAGE_PATH-->/logout.gif'></a>.";
			case 3:
				return "Connection Building By Other, Challenge Fail.";
			case 4:
				return "BRAS Can't Response The Challenge, Internal Error.";
			}
			return "Unknow Error NO.";
		case 4:
			switch (errNo) {
			case 0:
				return "BRAS User Authentication Success.";
			case 1:
				return "BRAS User Authentication Fail.";
			case 2:
				return "Connection Have Builded. User Authentication Fail! please <a href='<!--ACTION_URL-->?func=Logout'><img border='0' src='<!--IMAGE_PATH-->/logout.gif'></a>.";
			case 3:
				return "Connection Building By Other, User Authentication Fail.";
			case 4:
				return "BRAS Can't Response User Authentication, Internal Error.";
			}
			return "Unknow Error NO.";
		case 6:
			switch (errNo) {
			case 0:
				return "BRAS User Logout Success.";
			case 1:
				return "BRAS User Logout Reject.";
			case 2:
				return "BRAS Can't Response User Logout, Internal Error.";
			}
			return "Unknow Error NO.";
		case 8:
			switch (errNo) {
			case 0:
				return "BRAS User Kill Success.";
			case 1:
				return "BRAS User Kill Unsupport.";
			case 2:
				return "BRAS Can't Response User Kill, Internal Error.";
			}
			return "Unknow Error NO.";
		case 10:
			switch (errNo) {
			case 0:
				return "success.";
			case 1:
				return "not implement.";
			case 2:
				return "fail.";
			}
			return "unkonow error.";
		case 3:
		case 5:
		case 7:
		case 9:
		}
		return "Unknow Package Type";
	}

}
