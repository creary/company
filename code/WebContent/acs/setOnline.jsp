<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="org.springframework.jdbc.core.JdbcTemplate"%>
<%@page import="com.soofound.framework.util.SysConfigHelper"%>
<%
   String mac = request.getParameter("mac");
   String sql = "UPDATE cpe_host SET online=1 WHERE serial_number='" + mac + "'";
   JdbcTemplate jdbc = (JdbcTemplate)SysConfigHelper.getBean("defaultJdbcTemplate"); 
   jdbc.update(sql);
%>