<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>认证</title>
</head>
<body>
<form action="<%=request.getContextPath()  %>/PortalServlet" method="post">

<table>

	<tr>
		<td>用户名:</td>
		<td><input type="text" name="username" /></td>
	</tr>
	<tr>
		<td>paramvalue:</td>
		<td><input type="text" name="ssid"  value="<%=request.getParameter("ssid") %>"/></td>
		<td><input type="text" name="wlanacname"  value="<%=request.getParameter("wlanacname") %>"/></td>
		<td><input type="text" name="wlanuserip"  value="<%=request.getParameter("wlanuserip") %>"/></td>
	</tr>
	<tr>
		<td>密码:</td>
		<td><input type="text" name="password" /></td>
	</tr>
	<tr>
		<td><input type="submit" value="登录" /></td>
	</tr>
</table>
</form>
</body>
</html>