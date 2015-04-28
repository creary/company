<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="java.net.*"%>
<%@page import="java.util.*"%>
<%@page import="com.soofound.protocol.uaparser.*"%>
<%@page import="com.soofound.framework.jdbc.*"%>
<%@page import="com.soofound.framework.util.*"%>
<%@page import="com.soofound.framework.util.*"%>
<%@page import="com.soofound.portal.dao.*"%>
<%@page import="com.alibaba.fastjson.*"%>
<%
  	List<String> lists = new ArrayList<String>();
	lists.add("111");
	lists.add("222");

    String jsonText = JSONObject.toJSONString(lists);
	System.out.println(jsonText);
%>
