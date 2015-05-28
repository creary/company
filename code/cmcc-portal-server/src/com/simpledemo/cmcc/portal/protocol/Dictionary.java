package com.simpledemo.cmcc.portal.protocol;

import java.util.Vector;
/**
 * 字典
 * @author TWY.TOM
 *
 */
public class Dictionary
{
  public static final byte TYPE_IS_STRING = 0;
  public static final byte TYPE_IS_INTEGER = 1;
  public static final byte TYPE_IS_IPADDR = 2;
  public static final byte TYPE_IS_DATE = 3;
  public static final byte TYPE_IS_OCTETS = 4;
  public static final String ATTR_FLAG = "ATTRIBUTE";
  public static final String VALUE_FLAG = "VALUE";
  public static final String USER_NAME = "User-Name";
  public static final String USER_PASSWORD = "User-Password";
  public static final String CHALLENGE = "Challenge";
  public static final String CHAP_PASSWORD = "CHAP Password";
  public static final String TEXT_INFO = "Text Info";
  public static final String UPLINK_FLUX = "UpLink Flux";
  public static final String DOWNLINK_FLUX = "DownLink Flux";
  public static final String PORT = "Port";
  public static final String BAS_IP = "BAS IP";
  public static final String USER_MAC = "USER MAC";
  public static final byte B_USER_NAME = 1;
  public static final byte B_USER_PASSWORD = 2;
  public static final byte B_CHALLENGE = 3;
  public static final byte B_CHAP_PASSWORD = 4;
  public static final byte B_TEXT_INFO = 5;
  public static final byte B_UPLINK_FLUX = 6;
  public static final byte B_DOWNLINK_FLUX = 7;
  public static final byte B_PORT = 8;
  public static final byte B_BAS_IP = 9;
  public static final byte B_USER_MAC = 10;
  
  private static Vector<Dictionary.Attr> attrList = new Vector<Dictionary.Attr>();
  private static Vector<Dictionary.Value> valueList = new Vector<Dictionary.Value>();

  private static boolean isLoad = false;

  public Dictionary()
  {
    try
    {
      init();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public void init()
    throws Exception
  {
    if (isLoad)
    {
      return;
    }

    Dictionary.Attr attrItem = null;

    attrItem = new Dictionary.Attr(this);
    attrItem.attrName = "User-Name";
    attrItem.id = 1;
    attrItem.type = 0;
    attrList.add(attrItem);

    attrItem = new Dictionary.Attr(this);
    attrItem.attrName = "User-Password";
    attrItem.id = 2;
    attrItem.type = 0;
    attrList.add(attrItem);

    attrItem = new Dictionary.Attr(this);
    attrItem.attrName = "Challenge";
    attrItem.id = 3;
    attrItem.type = 0;
    attrList.add(attrItem);

    attrItem = new Dictionary.Attr(this);
    attrItem.attrName = "CHAP Password";
    attrItem.id = 4;
    attrItem.type = 0;
    attrList.add(attrItem);

    attrItem = new Dictionary.Attr(this);
    attrItem.attrName = "Text Info";
    attrItem.id = 5;
    attrItem.type = 0;
    attrList.add(attrItem);

    attrItem = new Dictionary.Attr(this);
    attrItem.attrName = "UpLink Flux";
    attrItem.id = 6;
    attrItem.type = 4;
    attrList.add(attrItem);

    attrItem = new Dictionary.Attr(this);
    attrItem.attrName = "DownLink Flux";
    attrItem.id = 7;
    attrItem.type = 4;
    attrList.add(attrItem);

    attrItem = new Dictionary.Attr(this);
    attrItem.attrName = "Port";
    attrItem.id = 8;
    attrItem.type = 0;
    attrList.add(attrItem);

    attrItem = new Dictionary.Attr(this);
    attrItem.attrName = "BAS IP";
    attrItem.id = 10;
    attrItem.type = 2;
    attrList.add(attrItem);

    attrItem = new Dictionary.Attr(this);
    attrItem.attrName = "USER MAC";
    attrItem.id = 11;
    attrItem.type = 0;
    attrList.add(attrItem);

    isLoad = true;
  }

  public String getAttrName(byte id)
  {
    Dictionary.Attr attrItem = null;

    for (int i = 0; i < attrList.size(); i++) {
      attrItem = (Dictionary.Attr)attrList.get(i);
      if (attrItem.id == id)
      {
        return attrItem.attrName;
      }
    }
    return null;
  }

  public byte getAttrId(String attrName)
  {
    Dictionary.Attr attrItem = null;

    for (int i = 0; i < attrList.size(); i++) {
      attrItem = (Dictionary.Attr)attrList.get(i);
      if (attrItem.attrName.equals(attrName))
      {
        return attrItem.id;
      }
    }
    return 0;
  }

  public byte getAttrType(String attrName)
  {
    Dictionary.Attr attrItem = null;

    for (int i = 0; i < attrList.size(); i++) {
      attrItem = (Dictionary.Attr)attrList.get(i);
      if (attrItem.attrName.equals(attrName))
      {
        return attrItem.type;
      }
    }
    return 0;
  }

  public byte getAttrType(byte id)
  {
    Dictionary.Attr attrItem = null;

    for (int i = 0; i < attrList.size(); i++) {
      attrItem = (Dictionary.Attr)attrList.get(i);
      if (attrItem.id == id)
      {
        return attrItem.type;
      }
    }
    return 0;
  }

  public String getValueName(String attrName, byte value)
  {
    Dictionary.Value valueItem = null;

    for (int i = 0; i < valueList.size(); i++) {
      valueItem = (Dictionary.Value)valueList.get(i);
      if ((valueItem.attrName.equals(attrName)) && (valueItem.value == value))
      {
        return valueItem.valueName;
      }
    }
    return "";
  }

  public String getValueName(byte id, byte value)
  {
    Dictionary.Value valueItem = null;

    for (int i = 0; i < valueList.size(); i++) {
      valueItem = (Dictionary.Value)valueList.get(i);
      if ((valueItem.id == id) && (valueItem.value == value))
      {
        return valueItem.valueName;
      }
    }
    return "";
  }
  /**
   * 字典值
   * @author TWY.TOM
   *
   */
  class Value {
	  public String attrName = "";
	  public byte id = 0;
	  public String valueName = "";
	  public byte value = 0;

	  private Value(Dictionary paramCoreDictionary)
	  {
	  }
  }
  /**
   * 字典属性
   * @author TWY.TOM
   *
   */
  class Attr {
	  public String attrName = "";
	  public byte id = 0;
	  public byte type = 0;

	  private Attr(Dictionary paramCoreDictionary)
	  {
	  }
  }
  
}
