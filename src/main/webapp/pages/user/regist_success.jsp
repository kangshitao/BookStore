<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>书城会员注册页面</title>
    <!--base标签固定当前页面的相对路径的参考位置。这里将其固定为当前web项目的路径-->
    <%-- 静态包含：base标签、css样式、jQuery文件--%>
    <%@include file="/pages/common/head.jsp" %>
    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }

        h1 a {
            color: red;
        }
    </style>
</head>
<body>
<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.png">
    <span class="wel_word"></span>
    <%--静态包含，登录成功之后的菜单栏 --%>
    <%@ include file="/pages/common/login_success_menu.jsp" %>
</div>

<div id="main">

    <h1>注册成功! <a href="index.jsp">转到主页</a></h1>

</div>

<%@ include file="/pages/common/footer.jsp"%>
</body>
</html>