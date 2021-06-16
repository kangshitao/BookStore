package com.kang.service.iml;

import com.kang.bean.User;
import com.kang.dao.UserDao;
import com.kang.dao.iml.UserDaoImpl;
import com.kang.service.UserService;

/**
 * @author Kangshitao
 * @date 2021年6月11日 下午7:14
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    @Override
    public void registUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean existUsername(String username) {
        if(userDao.queryUserByUsername(username) == null){
            return false;
        }
        return true;
    }
}
