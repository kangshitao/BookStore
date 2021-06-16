package com.kang.service;

import com.kang.bean.User;

/**
 * @author Kangshitao
 * @date 2021年6月11日 下午7:08
 */
public interface UserService {
    /**
     * 注册用户
     * @param user
     */
    public void registUser(User user);

    /**
     * 登陆
     * @param user
     * @return 如果返回null，说明登录失败；返回值不为空说明登陆成功
     */
    public User login(User user);

    /**
     * 检查用户名是否可用
     * @param username
     * @return 返回true表示表示用户名已存在，返回false表示用户名可用
     */
    public boolean existUsername(String username);
}
