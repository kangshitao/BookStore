<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>编辑图书</title>
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

        input {
            text-align: center;
        }
    </style>
</head>
<body>
<div id="header">
    <img class="logo_img" alt="" src="/static/img/logo.png">
    <span class="wel_word">编辑图书</span>
    <%@include file="/pages/common/manager_menu.jsp"%>
</div>

<div id="main">
    <form action="manager/bookServlet" method="get">
        <!--动态判断是add还是update方法
        1.根据是否有id。修改的时候有id属性，添加的时候没有id属性，因此根据id是否为空动态确定是添加还是修改操作
        2.根据是否有book属性，pageScope.book
        3.提交时添加参数method
        -->
        <input type="hidden" name="pageNo" value="${param.pageNo}">
        <input type="hidden" name="action" value="${empty param.id ?"add":"update"}"/>
        <input type="hidden" name="id" value="${requestScope.book.id}"/>
        <table>
            <tr>
                <td>名称</td>
                <td>价格</td>
                <td>作者</td>
                <td>销量</td>
                <td>库存</td>
                <td colspan="2">操作</td>
            </tr>
            <tr>
                <td><input name="name" type="text" value="${requestScope.book.name}"/></td>
                <td><input name="price" type="text" value="${requestScope.book.price}"/></td>
                <td><input name="author" type="text" value="${requestScope.book.author}"/></td>
                <td><input name="sales" type="text" value="${requestScope.book.sales}"/></td>
                <td><input name="stock" type="text" value="${requestScope.book.stock}"/></td>
                <td><input type="submit" value="提交"/></td>
            </tr>
        </table>
    </form>


</div>

<%@ include file="/pages/common/footer.jsp"%>
</body>
</html>