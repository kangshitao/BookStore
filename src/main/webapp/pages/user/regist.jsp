<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>书城会员注册页面</title>
    <%-- 静态包含：base标签、css样式、jQuery文件--%>
    <%@include file="/pages/common/head.jsp" %>
    <script type="text/javascript">
        $(function () {

            <!--使用ajax请求，在鼠标离开输入框时提示用户名是否可用-->
            $("#username").blur(function () {
                var username = this.value;
                $.getJSON("${bashPath}/userServlet", "action=ajaxExistUsername&username=" + username, function (data) {
                    if (data.existUsername) {
                        $("span.errorMsg").text("用户名已存在×");
                    } else {
                        $("span.errorMsg").text("用户名可用√");
                    }
                });
            });

            //给验证码图片绑定单击事件，点击图片刷新验证码
            //原理是每次点击，重新发起一次请求
            $("#code_img").click(function () {
                //在事件响应的function函数中有一个this对象，这个对象是当前正在响应事件的dom对象
                //src属性表示验证码img标签的图片路径，可读可写
                //如果每次请求相同，部分浏览器的缓存会导致图片不改变，只需要在请求后面加一个不重复的值，使每次请求不同即可。
                //这里在后面添加时间戳，值一定不会重复
                this.src = "${bashPath}kaptcha.jpg?d=" + new Date().getTime();
            });

            //给注册绑定单击事件
            $("#sub_btn").click(function () {
                // 验证用户名:必须由字母，数字下划线组成，并且长度为5到12位
                //1 获取用户名输入框里的内容
                var usernameText = $("#username").val();
                //2 创建正则表达式对象
                var usernameRe = /^\w{5,12}$/;
                //3 使用test方法验证
                if (!usernameRe.test(usernameText)) {
                    //4 提示用户结果
                    $("span.errorMsg").text("用户名不合法!");
                    return false;  //如果不合法，设置页面不跳转
                }

                // 验证密码:必须由字母,数字下划线组成，并且长度为5到12位
                var passwordText = $("#password").val();
                var passwordRe = /^\w{5,12}$/;
                if (!passwordRe.test(passwordText)) {
                    $("span.errorMsg").text("密码不合法!");
                    return false;
                }

                // 验证确认密码:和密码相同
                //如果两次密码不同，提示用户
                var rePassword = $("#repwd").val();
                if (rePassword != passwordText) {
                    alert("两次输入密码不匹配!");//警告框
                    //$("span.errorMsg").text("两次输入密码不匹配!");
                    return false;  //如果不合法，设置页面不跳转
                }

                // 邮箱验证: xxxxx@xxx.com
                var emailText = $("#email").val();
                var emailRe = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/
                if (!emailRe.test(emailText)) {
                    $("span.errorMsg").text("邮箱格式不合法!");
                    return false;
                }

                // 验证码:目前只需要验证用户已输入
                var codeText = $("#code").val();
                codeText = $.trim(codeText);  //去掉前后空格
                if (codeText == null || codeText == "") {
                    $("span.errorMsg").text("请输入验证码");
                    return false;
                }

                $("span.errorMsg").text("");  //如果信息都合法，将错误信息去掉
            });
        });
    </script>
    <style type="text/css">
        .login_form {
            height: 420px;
            margin-top: 25px;
        }

    </style>
</head>
<body>
<div id="login_header">
    <img class="logo_img" alt="" src="static/img/logo.png">
</div>

<div class="login_banner">

    <div id="l_content">
        <span class="login_word">欢迎注册</span>
    </div>

    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>注册书城会员</h1>
                    <span class="errorMsg">
                        ${requestScope.msg}
                    </span>
                </div>
                <div class="form">
                    <form action="userServlet" method="post">
                        <input type="hidden" name="action" value="regist"/>
                        <label>用户名称：</label>
                        <input class="itxt" type="text" placeholder="请输入用户名"
                               autocomplete="off" tabindex="1" name="username" id="username"
                               value="${requestScope.username}"
                        />
                        <br/>
                        <br/>
                        <label>用户密码：</label>
                        <input class="itxt" type="password" placeholder="请输入密码"
                               autocomplete="off" tabindex="1" name="password" id="password"/>
                        <br/>
                        <br/>
                        <label>确认密码：</label>
                        <input class="itxt" type="password" placeholder="确认密码"
                               autocomplete="off" tabindex="1" name="repwd" id="repwd"/>
                        <br/>
                        <br/>
                        <label>电子邮件：</label>
                        <input class="itxt" type="text" placeholder="请输入邮箱地址"
                               autocomplete="off" tabindex="1" name="email" id="email"
                               value="${requestScope.email}"
                        />
                        <br/>
                        <br/>
                        <label>验证码：</label>
                        <input class="itxt" type="text" style="width: 80px;" name="code" id="code"/>
                        <img id="code_img" alt="" src="kaptcha.jpg"
                             style="float: right; margin-right: 40px;width:100px;height: 30px">
                        <br/>
                        <br/>
                        <input type="submit" value="注册" id="sub_btn"/>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>