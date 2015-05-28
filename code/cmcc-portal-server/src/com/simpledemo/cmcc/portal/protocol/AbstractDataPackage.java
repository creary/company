package com.simpledemo.cmcc.portal.protocol;

import java.nio.ByteBuffer;
import java.util.Vector;

import org.apache.log4j.Logger;
/**
 * 数据包
 * @author TWY.TOM
 *
 */
public abstract class AbstractDataPackage {
	
	static final Logger logger = Logger.getLogger(AbstractDataPackage.class);
	
	protected Vector<BodyItem> Body = new Vector<BodyItem>();
	protected AbstractDataPackage.Head Header = new AbstractDataPackage.Head();
	protected String Key = null;

	public final short PACK_MAX_LEN = 1024;
	public final short PACK_HDR_LEN = 16;
	public final short PACK_HDR_LEN_V2 = 32;
	public final short PACK_HDR_MD5_LEN = 16;

	public void destroy() {
	}

	public static int bytes2int(byte[] b, int index) {
		int s = 0;
		for (int i = index; i < index + 3; i++) {
			if (b[i] >= 0)
				s += b[i];
			else
				s = s + 256 + b[i];
			s *= 256;
		}
		if (b[(index + 3)] >= 0)
			s += b[(index + 3)];
		else
			s = s + 256 + b[(index + 3)];
		return s;
	}

	public static short bytes2short(byte[] b, int index) {
		int s = 0;
		if (b[(index + 0)] >= 0)
			s += b[(index + 0)];
		else
			s = s + 256 + b[(index + 0)];
		s *= 256;
		if (b[(index + 1)] >= 0)
			s += b[(index + 1)];
		else
			s = s + 256 + b[(index + 1)];
		return (short) s;
	}

	public long bytes2long(byte[] b, int index) {
		long s = 0L;
		for (int i = index; i < index + 7; i++) {
			if (b[i] >= 0)
				s += b[i];
			else
				s = s + 256L + b[i];
			s *= 256L;
		}
		if (b[(index + 7)] >= 0)
			s += b[(index + 7)];
		else
			s = s + 256L + b[(index + 7)];
		return s;
	}

	public void setVersion() {
		this.Header.Ver = 1;
	}

	public void setVersionV2() {
		this.Header.Ver = 2;
	}

	public void setKey(String key) {
		this.Key = key;
	}

	public void setCHAP() {
		this.Header.PAP_CHAP = 0;
	}

	public void setPAP() {
		this.Header.PAP_CHAP = 1;
	}

	public void setUserIP(int ip) {
		this.Header.UserIP = ip;
	}

	public void setSerialNo(short serId) {
		this.Header.SerialNo = serId;
	}

	public void setReqId(short reqId) {
		this.Header.ReqID = reqId;
	}

	public void setAuthenticator(Byte[] author) {
		if (this.Header.Authenticator == null)
			this.Header.Authenticator = new byte[16];
		System.arraycopy(author, 0, this.Header.Authenticator, 0, 16);
	}

	public void setUserIP(String ip) {
		this.Header.sUserIP = ip;
	}

	public void setErrCode(byte errCode) {
		this.Header.ErrCode = errCode;
	}

	public void setAttrNum(byte AttrNum) {
		this.Header.AttrNum = AttrNum;
	}
	
	public void debug() {
		logger.warn("debug information: \n" + this.toString());
	}
	
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("------Packet Header Information: ------\n");
		buffer.append(this.debugHead());
		buffer.append("Packet Body Information: \n");
		buffer.append(this.debugBody());
		buffer.append("------Packet End-----------------------");
		return buffer.toString();
	}

	private StringBuffer debugHead() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("  Version \t= " + this.Header.Ver + "\n");
		buffer.append("  Type    \t= " + this.Header.Type + "\n");
		buffer.append("  PAP CHAP \t= " + this.Header.PAP_CHAP + "\n");
		buffer.append("  Rsv     \t= " + this.Header.Rsv + "\n");
		buffer.append("  SerialNo \t= " + this.Header.SerialNo + "\n");
		buffer.append("  ReqID   \t= " + this.Header.ReqID + "\n");
		buffer.append("  UserIP  \t= "
				+ Integer.toHexString(this.Header.UserIP) + " ("
				+ this.Header.sUserIP + ")" + "\n");
		buffer.append("  UserPort \t= " + this.Header.UserPort + "\n");
		buffer.append("  ErrCode  \t= " + this.Header.ErrCode + "\n");
		buffer.append("  AttrNum  \t= " + this.Header.AttrNum + "\n");
		return buffer;
	}

	private StringBuffer debugBody() {
		StringBuffer buffer = new StringBuffer();
		AbstractDataPackage.BodyItem item = null;
		for (int i = 0; i < this.Body.size(); i++) {
			item = (AbstractDataPackage.BodyItem) this.Body.get(i);
			debugBodyItem(item, buffer);
		}
		return buffer;
	}
	
	private void debugBodyItem(AbstractDataPackage.BodyItem item, StringBuffer buffer) {
		Dictionary tDict = new Dictionary();
		String itemname = tDict.getAttrName(item.id);
		if(itemname == null || item.value == null) {
			return;
		}
		if(Dictionary.CHALLENGE.equals(itemname) 
				|| Dictionary.CHAP_PASSWORD.equals(itemname)
				|| Dictionary.CHAP_PASSWORD.equals(itemname)) {
			buffer.append("\t" + itemname + "\t = "
					+ ProtocolUtils.getHexString(item.value) + "\n");
		} else {
			if (item.value == null) {
				buffer.append("\t" + itemname + "\t = (null)\n");
			} else {
				buffer.append("\t" + itemname + "\t = " + new String(item.value) + "\n");
			}
		}
	}

	public int length() {
		int packLen = 16;
		if (this.Header.Ver == 2) {
			packLen = 32;
		}
		AbstractDataPackage.BodyItem item = null;
		for (int i = 0; i < this.Body.size(); i++) {
			item = this.Body.get(i);
			packLen += item.len;
		}
		return packLen;
	}

	public byte[] toBytes() {
		AbstractDataPackage.BodyItem item = null;
		int len = length();
		ByteBuffer buff = ByteBuffer.allocate(len);

		buff.put(toHeadBytes());

		if (this.Header.Ver == 2) {
			if (this.Header.Authenticator == null) {
				for (int i = 0; i < 16; i++) {
					buff.put((byte) 0);
				}
			} else {
				buff.put(this.Header.Authenticator);
			}
		}

		if (this.Body != null) {
			for (int i = 0; i < this.Body.size(); i++) {
				item = (AbstractDataPackage.BodyItem) this.Body.get(i);
				buff.put(toBodyItemBytes(item));
			}
			this.Body.removeAllElements();
		}

		if (this.Header.Ver == 2) {
			byte[] buffRet = buff.array();

			byte[] buffNew = new byte[len + this.Key.length()];
			System.arraycopy(buffRet, 0, buffNew, 0, len);
			System.arraycopy(this.Key.getBytes(), 0, buffNew, len,
					this.Key.length());

			MD5 md5 = new MD5();
			md5.Update(buffNew);

			System.arraycopy(md5.Final(), 0, buffRet, 16, 16);

			return buffRet;
		}
		return buff.array();
	}
	
	private byte[] toHeadBytes() {
		ByteBuffer buff = null;

		buff = ByteBuffer.allocate(16);

		buff.put(this.Header.Ver);
		buff.put(this.Header.Type);
		buff.put(this.Header.PAP_CHAP);
		buff.put(this.Header.Rsv);
		buff.putShort(this.Header.SerialNo);
		buff.putShort(this.Header.ReqID);

		this.Header.UserIP = (int) ProtocolUtils.ip2long(this.Header.sUserIP);
		buff.putInt(this.Header.UserIP);
		buff.putShort(this.Header.UserPort);
		buff.put(this.Header.ErrCode);
		buff.put(this.Header.AttrNum);
		return buff.array();
	}
	
	private byte[] toBodyItemBytes(AbstractDataPackage.BodyItem item) {
		ByteBuffer buff = ByteBuffer.allocate(item.len);
		buff.put(item.id);
		buff.put(item.len);

		if (item.len > 2) {
			buff.put(item.value, 0, item.len - 2);
		}
		return buff.array();
	}

	public String getBodyItemStr(byte attr) {
		AbstractDataPackage.BodyItem item = null;
		for (int i = 0; i < this.Body.size(); i++) {
			item = (AbstractDataPackage.BodyItem) this.Body.get(i);
			if (item.id == attr) {
				return new String(item.value);
			}
		}
		return null;
	}

	public long getBodyItemLong(byte attr) {
		AbstractDataPackage.BodyItem item = null;
		for (int i = 0; i < this.Body.size(); i++) {
			item = (AbstractDataPackage.BodyItem) this.Body.get(i);
			if (item.id == attr) {
				return bytes2long(item.value, 0);
			}
		}
		return 0L;
	}

	public String getBodyItemStr(String attrName) {
		AbstractDataPackage.BodyItem item = null;

		Dictionary tDict = new Dictionary();
		byte type = tDict.getAttrId(attrName);

		for (int i = 0; i < this.Body.size(); i++) {
			item = (AbstractDataPackage.BodyItem) this.Body.get(i);
			if (item.id == type) {
				return new String(item.value);
			}
		}
		return null;
	}

	public byte[] getBodyItemBytes(String attrName) {
		AbstractDataPackage.BodyItem item = null;

		Dictionary tDict = new Dictionary();
		byte type = tDict.getAttrId(attrName);

		for (int i = 0; i < this.Body.size(); i++) {
			item = (AbstractDataPackage.BodyItem) this.Body.get(i);
			if (item.id == type) {
				return item.value;
			}
		}
		return null;
	}

	public int getUserIP() {
		return this.Header.UserIP;
	}

	public String getUserIPStr() {
		return ProtocolUtils.ip2str(this.Header.UserIP);
	}

	public byte getErrCode() {
		return this.Header.ErrCode;
	}

	public byte getAttrNum() {
		return this.Header.AttrNum;
	}

	public short getCode() {
		return (short) this.Header.Type;
	}

	public short getReqId() {
		return this.Header.ReqID;
	}

	/**
	 * SerialNo
	 * @return
	 */
	public short getId() {
		return this.Header.SerialNo;
	}

	public int getBodyItems() {
		return this.Body.size();
	}

	public byte getVersion() {
		return this.Header.Ver;
	}

	public String getKey() {
		return this.Key;
	}

	public byte[] getAuthenticator() {
		return this.Header.Authenticator;
	}
	/**
	 * 数据包体
	 */
	protected static class BodyItem {
		public byte id = 0;
		public byte len = 0;
		public byte[] value = null;
	}
	/**
	 * 数据包头
	 * @author TWY.TOM
	 *
	 */
	protected static class Head {
		public byte Ver = 1;
		public byte Type = 0;
		public byte PAP_CHAP = 0;
		public byte Rsv = 0;
		public short SerialNo = 0;
		public short ReqID = 0;
		public int UserIP = 0;
		public String sUserIP = "";
		public short UserPort = 0;
		public byte ErrCode = 0;
		public byte AttrNum = 0;
		public byte[] Authenticator = null;
	}
	
	protected static class MD5 {
		
		MD5State state;
		MD5State finals;
		static byte[] padding = { -128 };

		public synchronized void Init() {
			this.state = new MD5State();
			this.finals = null;
		}

		public MD5() {
			Init();
		}

		public MD5(Object ob) {
			this();
			Update(ob.toString());
		}

		private int rotate_left(int x, int n) {
			return x << n | x >>> 32 - n;
		}

		private int uadd(int a, int b) {
			long aa = a & 0xFFFFFFFF;
			long bb = b & 0xFFFFFFFF;

			aa += bb;

			return (int) (aa & 0xFFFFFFFF);
		}

		private int uadd(int a, int b, int c) {
			return uadd(uadd(a, b), c);
		}

		private int uadd(int a, int b, int c, int d) {
			return uadd(uadd(a, b, c), d);
		}

		private int FF(int a, int b, int c, int d, int x, int s, int ac) {
			a = uadd(a, b & c | (b ^ 0xFFFFFFFF) & d, x, ac);
			return uadd(rotate_left(a, s), b);
		}

		private int GG(int a, int b, int c, int d, int x, int s, int ac) {
			a = uadd(a, b & d | c & (d ^ 0xFFFFFFFF), x, ac);
			return uadd(rotate_left(a, s), b);
		}

		private int HH(int a, int b, int c, int d, int x, int s, int ac) {
			a = uadd(a, b ^ c ^ d, x, ac);
			return uadd(rotate_left(a, s), b);
		}

		private int II(int a, int b, int c, int d, int x, int s, int ac) {
			a = uadd(a, c ^ (b | d ^ 0xFFFFFFFF), x, ac);
			return uadd(rotate_left(a, s), b);
		}

		private int[] Decode(byte[] buffer, int len, int shift) {
			int[] out = new int[16];
			int j;
			for (int i = j = 0; j < len; j += 4) {
				out[i] = (buffer[(j + shift)] & 0xFF
						| (buffer[(j + 1 + shift)] & 0xFF) << 8
						| (buffer[(j + 2 + shift)] & 0xFF) << 16 | (buffer[(j + 3 + shift)] & 0xFF) << 24);

				i++;
			}

			return out;
		}

		private void Transform(MD5State state, byte[] buffer, int shift) {
			int a = state.state[0];
			int b = state.state[1];
			int c = state.state[2];
			int d = state.state[3];

			int[] x = Decode(buffer, 64, shift);

			a = FF(a, b, c, d, x[0], 7, -680876936);
			d = FF(d, a, b, c, x[1], 12, -389564586);
			c = FF(c, d, a, b, x[2], 17, 606105819);
			b = FF(b, c, d, a, x[3], 22, -1044525330);
			a = FF(a, b, c, d, x[4], 7, -176418897);
			d = FF(d, a, b, c, x[5], 12, 1200080426);
			c = FF(c, d, a, b, x[6], 17, -1473231341);
			b = FF(b, c, d, a, x[7], 22, -45705983);
			a = FF(a, b, c, d, x[8], 7, 1770035416);
			d = FF(d, a, b, c, x[9], 12, -1958414417);
			c = FF(c, d, a, b, x[10], 17, -42063);
			b = FF(b, c, d, a, x[11], 22, -1990404162);
			a = FF(a, b, c, d, x[12], 7, 1804603682);
			d = FF(d, a, b, c, x[13], 12, -40341101);
			c = FF(c, d, a, b, x[14], 17, -1502002290);
			b = FF(b, c, d, a, x[15], 22, 1236535329);

			a = GG(a, b, c, d, x[1], 5, -165796510);
			d = GG(d, a, b, c, x[6], 9, -1069501632);
			c = GG(c, d, a, b, x[11], 14, 643717713);
			b = GG(b, c, d, a, x[0], 20, -373897302);
			a = GG(a, b, c, d, x[5], 5, -701558691);
			d = GG(d, a, b, c, x[10], 9, 38016083);
			c = GG(c, d, a, b, x[15], 14, -660478335);
			b = GG(b, c, d, a, x[4], 20, -405537848);
			a = GG(a, b, c, d, x[9], 5, 568446438);
			d = GG(d, a, b, c, x[14], 9, -1019803690);
			c = GG(c, d, a, b, x[3], 14, -187363961);
			b = GG(b, c, d, a, x[8], 20, 1163531501);
			a = GG(a, b, c, d, x[13], 5, -1444681467);
			d = GG(d, a, b, c, x[2], 9, -51403784);
			c = GG(c, d, a, b, x[7], 14, 1735328473);
			b = GG(b, c, d, a, x[12], 20, -1926607734);

			a = HH(a, b, c, d, x[5], 4, -378558);
			d = HH(d, a, b, c, x[8], 11, -2022574463);
			c = HH(c, d, a, b, x[11], 16, 1839030562);
			b = HH(b, c, d, a, x[14], 23, -35309556);
			a = HH(a, b, c, d, x[1], 4, -1530992060);
			d = HH(d, a, b, c, x[4], 11, 1272893353);
			c = HH(c, d, a, b, x[7], 16, -155497632);
			b = HH(b, c, d, a, x[10], 23, -1094730640);
			a = HH(a, b, c, d, x[13], 4, 681279174);
			d = HH(d, a, b, c, x[0], 11, -358537222);
			c = HH(c, d, a, b, x[3], 16, -722521979);
			b = HH(b, c, d, a, x[6], 23, 76029189);
			a = HH(a, b, c, d, x[9], 4, -640364487);
			d = HH(d, a, b, c, x[12], 11, -421815835);
			c = HH(c, d, a, b, x[15], 16, 530742520);
			b = HH(b, c, d, a, x[2], 23, -995338651);

			a = II(a, b, c, d, x[0], 6, -198630844);
			d = II(d, a, b, c, x[7], 10, 1126891415);
			c = II(c, d, a, b, x[14], 15, -1416354905);
			b = II(b, c, d, a, x[5], 21, -57434055);
			a = II(a, b, c, d, x[12], 6, 1700485571);
			d = II(d, a, b, c, x[3], 10, -1894986606);
			c = II(c, d, a, b, x[10], 15, -1051523);
			b = II(b, c, d, a, x[1], 21, -2054922799);
			a = II(a, b, c, d, x[8], 6, 1873313359);
			d = II(d, a, b, c, x[15], 10, -30611744);
			c = II(c, d, a, b, x[6], 15, -1560198380);
			b = II(b, c, d, a, x[13], 21, 1309151649);
			a = II(a, b, c, d, x[4], 6, -145523070);
			d = II(d, a, b, c, x[11], 10, -1120210379);
			c = II(c, d, a, b, x[2], 15, 718787259);
			b = II(b, c, d, a, x[9], 21, -343485551);

			state.state[0] += a;
			state.state[1] += b;
			state.state[2] += c;
			state.state[3] += d;
		}

		public void Update(MD5State stat, byte[] buffer, int offset, int length) {
			this.finals = null;

			if (length - offset > buffer.length) {
				length = buffer.length - offset;
			}

			int index = stat.count[0] >>> 3 & 0x3F;

			if ((stat.count[0] += (length << 3)) < (length << 3)) {
				stat.count[1] += 1;
			}
			stat.count[1] += (length >>> 29);

			int partlen = 64 - index;
			int i;
			if (length >= partlen) {
				for (i = 0; i < partlen; i++) {
					stat.buffer[(i + index)] = buffer[(i + offset)];
				}
				Transform(stat, stat.buffer, 0);

				for (i = partlen; i + 63 < length; i += 64) {
					Transform(stat, buffer, i);
				}
				index = 0;
			} else {
				i = 0;
			}

			if (i < length) {
				int start = i;
				for (; i < length; i++)
					stat.buffer[(index + i - start)] = buffer[(i + offset)];
			}
		}

		public void Update(byte[] buffer, int offset, int length) {
			Update(this.state, buffer, offset, length);
		}

		public void Update(byte[] buffer, int length) {
			Update(this.state, buffer, 0, length);
		}

		public void Update(byte[] buffer) {
			Update(buffer, 0, buffer.length);
		}

		public void Update(byte b) {
			byte[] buffer = new byte[1];
			buffer[0] = b;

			Update(buffer, 1);
		}

		@SuppressWarnings("deprecation")
		public void Update(String s) {
			byte[] chars = new byte[s.length()];
			s.getBytes(0, s.length(), chars, 0);
			Update(chars, chars.length);
		}

		public void Update(int i) {
			Update((byte) (i & 0xFF));
		}

		private byte[] Encode(int[] input, int len) {
			byte[] out = new byte[len];
			int j;
			for (int i = j = 0; j < len; j += 4) {
				out[j] = (byte) (input[i] & 0xFF);
				out[(j + 1)] = (byte) (input[i] >>> 8 & 0xFF);
				out[(j + 2)] = (byte) (input[i] >>> 16 & 0xFF);
				out[(j + 3)] = (byte) (input[i] >>> 24 & 0xFF);

				i++;
			}

			return out;
		}

		public synchronized byte[] Final() {
			if (this.finals == null) {
				MD5State fin = new MD5State(this.state);

				byte[] bits = Encode(fin.count, 8);

				int index = fin.count[0] >>> 3 & 0x3F;
				int padlen = index < 56 ? 56 - index : 120 - index;

				Update(fin, padding, 0, padlen);

				Update(fin, bits, 0, 8);

				this.finals = fin;
			}

			return Encode(this.finals.state, 16);
		}

		public static String asHex(byte[] hash) {
			StringBuffer buf = new StringBuffer(hash.length * 2);

			for (int i = 0; i < hash.length; i++) {
				if ((hash[i] & 0xFF) < 16) {
					buf.append("0");
				}
				buf.append(Long.toString(hash[i] & 0xFF, 16));
			}

			return buf.toString();
		}

		public String asHex() {
			return asHex(Final());
		}
	}
	/**
	 * 
	 * @author TWY.TOM
	 *
	 */
	static class MD5State
	{
	  int[] state;
	  int[] count;
	  byte[] buffer;

	  public MD5State()
	  {
	    this.buffer = new byte[64];
	    this.count = new int[2];
	    this.state = new int[4];

	    this.state[0] = 1732584193;
	    this.state[1] = -271733879;
	    this.state[2] = -1732584194;
	    this.state[3] = 271733878;
	    int tmp69_68 = 0; this.count[1] = tmp69_68; this.count[0] = tmp69_68;
	  }

	  public MD5State(MD5State from)
	  {
	    this();

	    for (int i = 0; i < this.buffer.length; i++) {
	      this.buffer[i] = from.buffer[i];
	    }
	    for (int i = 0; i < this.state.length; i++) {
	      this.state[i] = from.state[i];
	    }
	    for (int i = 0; i < this.count.length; i++)
	      this.count[i] = from.count[i];
	  }
	}

}
