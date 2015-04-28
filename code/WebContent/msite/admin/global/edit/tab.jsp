<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="/common/path.jsp"%>
<%
	String tab = request.getParameter("tab");
	if(tab == null) tab = "global";	
	request.setAttribute("tab",tab); 
%>
<div class="nav">
    <ul class="list">
       <c:forEach items="${pts}" var="p">  
            <c:if test="${p.editable}">
                 <li ${tab==p.id?"class='active'":""}><a href="${base}portal/${pid}/${p.id}/edit.do">${p.name}设置</a></li>
            </c:if>   
        </c:forEach> 
    </ul>
</div>