package com.simpledemo.cmcc.portal.protocol;
/**
 * 工具
 * @author TWY.TOM
 *
 */
public class ProtocolUtils {

	/**
	 * Returns the byte array as a hex string in the format
	 * "0x1234".
	 * @param data byte array
	 * @return hex string
	 */
	public static String getHexString(byte[] data) {
		StringBuffer hex = new StringBuffer("0x");
		if (data != null)
			for (int i = 0; i < data.length; i++) {
				String digit = Integer.toString(data[i] & 0x0ff, 16);
				if (digit.length() < 2)
					hex.append('0');
				hex.append(digit);
			}
		return hex.toString();
	}
	
	public static long ip2longN(String sIP) {
		String[] ssIP = new String[4];
		int items = 0;
		int j = 0;

		for (int i = 0; i < sIP.length(); i++) {
			if (sIP.charAt(i) != '.')
				continue;
			ssIP[items] = sIP.substring(j, i);
			j = i + 1;
			items++;
		}

		ssIP[items] = sIP.substring(j);

		if (items != 3) {
			return -1L;
		}
		try {
			long ip1 = Integer.parseInt(ssIP[0]);
			long ip2 = Integer.parseInt(ssIP[1]);
			long ip3 = Integer.parseInt(ssIP[2]);
			long ip4 = Integer.parseInt(ssIP[3]);
			if ((ip1 > 255L) || (ip2 > 255L) || (ip3 > 255L) || (ip4 > 255L)) {
				return -1L;
			}
			return ip4 * 256L * 256L * 256L + ip3 * 256L * 256L + ip2 * 256L
					+ ip1;
		} catch (Exception e) {
		}
		return -1L;
	}

	public static long ip2long(String sIP) {
		String[] ssIP = new String[4];
		int items = 0;
		int j = 0;

		for (int i = 0; i < sIP.length(); i++) {
			if (sIP.charAt(i) != '.')
				continue;
			ssIP[items] = sIP.substring(j, i);
			j = i + 1;
			items++;
		}

		ssIP[items] = sIP.substring(j);

		if (items != 3) {
			return -1L;
		}
		try {
			long ip1 = Integer.parseInt(ssIP[0]);
			long ip2 = Integer.parseInt(ssIP[1]);
			long ip3 = Integer.parseInt(ssIP[2]);
			long ip4 = Integer.parseInt(ssIP[3]);
			if ((ip1 > 255L) || (ip2 > 255L) || (ip3 > 255L) || (ip4 > 255L)) {
				return -1L;
			}
			return ip1 * 256L * 256L * 256L + ip2 * 256L * 256L + ip3 * 256L
					+ ip4;
		} catch (Exception e) {
		}
		return -1L;
	}

	public static String ip2str(long iIP) {
		long ip1 = iIP / 16777216L;
		long ip2 = (iIP - ip1 * 256L * 256L * 256L) / 65536L;
		long ip3 = (iIP - ip1 * 256L * 256L * 256L - ip2 * 256L * 256L) / 256L;
		long ip4 = iIP - ip1 * 256L * 256L * 256L - ip2 * 256L * 256L - ip3
				* 256L;

		if ((ip1 > 255L) || (ip2 > 255L) || (ip3 > 255L) || (ip4 > 255L)) {
			return null;
		}
		return String.valueOf(ip1) + "." + String.valueOf(ip2) + "."
				+ String.valueOf(ip3) + "." + String.valueOf(ip4);
	}

	public static String ip2str(String sIP) {
		long iIP = Long.parseLong(sIP);
		long ip1 = iIP / 16777216L;
		long ip2 = (iIP - ip1 * 256L * 256L * 256L) / 65536L;
		long ip3 = (iIP - ip1 * 256L * 256L * 256L - ip2 * 256L * 256L) / 256L;
		long ip4 = iIP - ip1 * 256L * 256L * 256L - ip2 * 256L * 256L - ip3
				* 256L;

		if ((ip1 > 255L) || (ip2 > 255L) || (ip3 > 255L) || (ip4 > 255L)) {
			return null;
		}
		return String.valueOf(ip1) + "." + String.valueOf(ip2) + "."
				+ String.valueOf(ip3) + "." + String.valueOf(ip4);
	}
	
}
