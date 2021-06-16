<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>购物车</title>
    <%@include file="/pages/common/head.jsp" %>
    <script type="text/javascript">
        $(function () {
            //给删除标签绑定二次确认的单击事件
            $("a.deleteItem").click(function () {
                //如果是false会停止提交
                return confirm("确定要删除《" + $(this).parent().parent().find("td:first").text() + "》吗？");
            });
            $("#clearCart").click(function () {
                //如果是false会停止提交
                return confirm("确定要清除当前购物车吗？");
            });
            //如果数量发生改变，则提示
            $(".updateCount").change(function () {
                var name = $(this).parent().parent().find("td:first").text();
                var bookId = $(this).attr("bookId");
                var count = this.value;
                var value =  /^[1-9]+[0-9]*$/; <!--只能填数字，不能以0开头-->
                if (!value.test(count)) {
                    // 提示用户结果
                    alert("输入格式非法");
                    this.value = this.defaultValue;
                    return false;  //如果不合法，设置页面不跳转
                }else{
                    if(confirm("确定要修改《" + name +"》数量为" + count + "吗？")){
                        location.href="${pageScope.bashPath}cartServlet?action=updateCount&count="+count+"&id="+bookId;
                    }else {
                        <!-- defaultValue为默认值，这里表示不修改 -->
                        this.value = this.defaultValue;
                    }
                }
            });
        });
    </script>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.png">
    <span class="wel_word">购物车</span>
    <%--静态包含，登录成功之后的菜单栏 --%>
    <%@ include file="/pages/common/login_success_menu.jsp" %>
</div>

<div id="main">

    <table>
        <tr>
            <td>商品名称</td>
            <td>数量</td>
            <td>单价</td>
            <td>金额</td>
            <td>操作</td>
        </tr>
        <%--遍历session中包含的商品数据并显示--%>
        <c:forEach items="${sessionScope.cart.items}" var="entry">
            <tr>
                <td>${entry.value.name}</td>
                <td>
                    <input class="updateCount" type="text"  bookId="${entry.value.id}"
                           value="${entry.value.count}" style="width: 30px">
                </td>
                <td>${entry.value.price}</td>
                <td>${entry.value.totalPrice}</td>
                <td><a class = "deleteItem" href="cartServlet?action=deleteItem&id=${entry.value.id}">删除</a></td>
            </tr>
        </c:forEach>

    </table>
    <%--判断购物车是否为空--%>
    <c:if  test="${empty sessionScope.cart.items}">
        <table>
            <tr>
                <td>当前购物车空空如也~<a href="index.jsp">点击浏览商品</a></td>
            </tr>
        </table>

    </c:if>
    <c:if  test="${not empty sessionScope.cart.items}">
        <div class="cart_info">
            <span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
            <span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
            <span class="cart_span"><a id="clearCart" href="cartServlet?action=clear">清空购物车</a></span>
            <span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span>
        </div>
    </c:if>


</div>

<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>