package com.kang.dao;

import com.kang.bean.User;

/**
 * @author Kangshitao
 * @date 2021年6月11日 下午5:44
 */
public interface UserDao {

    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return 返回null表示没有此用户
     */
    public User queryUserByUsername(String username);

    /**
     * 根据用户名和密码查询用户信息
     * @param username
     * @param password
     * @return
     */

    public User queryUserByUsernameAndPassword(String username,String password);
    /**
     * 保存用户信息
     * @param user
     * @return
     */
    public int saveUser(User user);
}
