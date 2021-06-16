package com.kang.web; /**
 * @author Kangshitao
 * @date 2021年6月13日 下午2:54
 */

import com.google.gson.Gson;
import com.kang.bean.User;
import com.kang.service.UserService;
import com.kang.service.iml.UserServiceImpl;
import com.kang.utils.WebUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

@WebServlet(name = "UserServlet", value = "/userServlet")
public class UserServlet extends BaseSevlet {
    private UserService userService = new UserServiceImpl();

    /**
     * 注销登陆
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();  //注销登陆,设置session无效即可
        //重定向到首页
        response.sendRedirect(request.getContextPath());
    }

    /**
     * 处理登陆请求
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = WebUtils.copyParamToBean(request.getParameterMap(),new User()); //从输入框获取的用户信息
        User loginUser = userService.login(user); //从数据库查询的用户信息
        if(loginUser != null){
            Cookie cookie_username = new Cookie("username",user.getUsername());
            Cookie cookie_pwd = new Cookie("password",user.getPassword());

            response.addCookie(cookie_username);
            response.addCookie(cookie_pwd);
            //登陆成功后，将用户登录信息保存到session中，用于在其他界面显示用户信息
            request.getSession().setAttribute("user",loginUser);
            request.getRequestDispatcher("pages/user/login_success.jsp").forward(request,response);
        }else{//如果用户名或密码错误，则请求转发到登陆界面
            //把错误信息和表单项回显到登陆界面
            request.setAttribute("msg","用户名或密码错误!");
            request.setAttribute("username",user.getUsername());
            request.getRequestDispatcher("pages/user/login.jsp").forward(request,response);
        }
    }

    /**
     * 处理注册请求
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");

        // 将各个参数注入到bean中
        // 之所以使用map作为参数，而不是使用request，是为了降低耦合度
        User user = WebUtils.copyParamToBean(request.getParameterMap(),new User());
        //获取验证码
        String token = (String)request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //得到验证码以后，要马上从session中删除，防止二次使用，防止重复提交。
        request.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        if (token!=null && token.equalsIgnoreCase(code)) {
            if (userService.existUsername(user.getUsername())) { //如果用户名已存在，请求转发到注册页面
                request.setAttribute("msg", "用户名已存在");
                request.setAttribute("username", user.getUsername());
                request.setAttribute("email", user.getEmail());
                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
            } else { //注册成功
                userService.registUser(user);
                request.getRequestDispatcher("/pages/user/regist_success.jsp").forward(request, response);
            }

        } else { //如果验证码不正确，则请求转发到注册页面
            //把回显信息保存到request中
            request.setAttribute("msg", "验证码错误");
            request.setAttribute("username", user.getUsername());
            request.setAttribute("email", user.getEmail());
            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
        }
    }

    private void ajaxExistUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        boolean existUsername = userService.existUsername(username);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("existUsername",existUsername);
        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        response.getWriter().write(json);

    }
}
