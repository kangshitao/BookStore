<%--
  Created by IntelliJ IDEA.
  User: Kangshitao
  Date: 2021年6月12日
  Time: 下午5:36
  To change this template use File | Settings | File Templates.
--%>
<!--base标签固定当前页面的相对路径的参考位置。这里将其固定为当前web项目的路径-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String bashPath = request.getScheme()
            +"://"
            +request.getServerName()
            +":"
            +request.getServerPort()
            +request.getContextPath()
            +"/";
    pageContext.setAttribute("bashPath",bashPath);
%>
<base href="<%= bashPath%>">
<link type="text/css" rel="stylesheet" href="static/css/style.css">
<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>