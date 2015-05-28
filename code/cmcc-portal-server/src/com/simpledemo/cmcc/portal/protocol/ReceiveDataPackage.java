package com.simpledemo.cmcc.portal.protocol;
/**
 * 接收包封装
 * @author TWY.TOM
 *
 */
public class ReceiveDataPackage extends AbstractDataPackage {

	public ReceiveDataPackage() {
	}
	
	public ReceiveDataPackage(byte[] data) {
		AbstractDataPackage.BodyItem item = null;

		decodeHead(data);

		if (Header.Ver == 2)
			for (int i = 32; i < data.length;) {
				item = decodeBodyItem(data, i);
				i += item.len;
				this.Body.add(item);
			}
		else
			for (int i = 16; i < data.length;) {
				item = decodeBodyItem(data, i);
				i += item.len;
				this.Body.add(item);
			}
	}
	
	public void decodeHead(byte[] data) {
		this.Header.Ver = data[0];
		this.Header.Type = data[1];
		this.Header.PAP_CHAP = data[2];
		this.Header.Rsv = data[3];
		this.Header.SerialNo = bytes2short(data, 4);
		this.Header.ReqID = bytes2short(data, 6);
		this.Header.UserIP = bytes2int(data, 8);
		this.Header.UserPort = bytes2short(data, 12);
		this.Header.ErrCode = data[14];
		this.Header.AttrNum = data[15];
	}
	
	public void decodeBody(byte[] data, int index) {
		AbstractDataPackage.BodyItem item = null;
		int len = 0;

		for (int i = 0; i < data[15]; i++) {
			item = decodeBodyItem(data, index + len);
			len += item.len;
			this.Body.add(item);
		}
	}
	
	public AbstractDataPackage.BodyItem decodeBodyItem(byte[] data) {
		AbstractDataPackage.BodyItem item = new AbstractDataPackage.BodyItem();
		//Dictionary tDict = new Dictionary();

		item.id = data[0];
		item.len = data[1];
		item.value = new byte[data.length - 2];
		System.arraycopy(data, 2, item.value, 0, item.len - 2);
		return item;
	}

	public AbstractDataPackage.BodyItem decodeBodyItem(byte[] data, int index) {
		AbstractDataPackage.BodyItem item = new AbstractDataPackage.BodyItem();
		//Dictionary tDict = new Dictionary();

		item.id = data[index];
		item.len = data[(index + 1)];

		if (item.len > 2) {
			item.value = new byte[item.len - 2];
			System.arraycopy(data, index + 2, item.value, 0, item.len - 2);
		}
		return item;
	}
	
}
