<%--
  Created by IntelliJ IDEA.
  User: Kangshitao
  Date: 2021年6月12日
  Time: 下午5:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <span>当前用户：<span class="um_span">${sessionScope.user.username}</span></span>
    <a href="pages/order/order.jsp">我的订单</a>
    <a href="userServlet?action=logout">注销</a>
    <a href="index.jsp">返回</a>
</div>
