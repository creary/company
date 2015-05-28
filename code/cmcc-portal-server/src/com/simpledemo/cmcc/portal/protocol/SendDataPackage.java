package com.simpledemo.cmcc.portal.protocol;

/**
 * 发送数据包封装
 * @author TWY.TOM
 *
 */
public class SendDataPackage extends AbstractDataPackage {

	public SendDataPackage(byte command) {
		this.Header.Type = command;
	}
	
	public SendDataPackage(byte command, short serialId)  {
		this.Header.Type = command;
		this.Header.SerialNo = serialId;
	}
	
	public void addBodyItem(byte attr, String value) {
		AbstractDataPackage.BodyItem item = new AbstractDataPackage.BodyItem();

		item.id = attr;
		item.value = value.getBytes();
		item.len = (byte) (2 + item.value.length);
		this.Body.add(item);
		this.Header.AttrNum = (byte) (this.Header.AttrNum + 1);
	}

	public void addBodyItem(String attrName, String value) {
		AbstractDataPackage.BodyItem item = new AbstractDataPackage.BodyItem();
		Dictionary tDict = new Dictionary();

		item.id = tDict.getAttrId(attrName);
		item.value = value.getBytes();
		item.len = (byte) (2 + value.length());
		this.Body.add(item);
		this.Header.AttrNum = (byte) (this.Header.AttrNum + 1);
	}

	public void addBodyItem(String attrName) {
		AbstractDataPackage.BodyItem item = new AbstractDataPackage.BodyItem();
		Dictionary tDict = new Dictionary();

		item.id = tDict.getAttrId(attrName);
		item.len = 2;
		this.Body.add(item);
		this.Header.AttrNum = (byte) (this.Header.AttrNum + 1);
	}

	public void addBodyItem(String attrName, byte[] value) {
		AbstractDataPackage.BodyItem item = new AbstractDataPackage.BodyItem();
		Dictionary tDict = new Dictionary();

		item.id = tDict.getAttrId(attrName);
		item.value = value;
		item.len = (byte) (2 + item.value.length);
		this.Body.add(item);
		this.Header.AttrNum = (byte) (this.Header.AttrNum + 1);
	}
	
}
