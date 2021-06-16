import com.kang.bean.User;
import com.kang.dao.UserDao;
import com.kang.dao.iml.UserDaoImpl;
import org.junit.jupiter.api.Test;

/**
 * @author Kangshitao
 * @date 2021年6月11日 下午6:06
 */
public class UserDaoTest {
    @Test
    public void queryUserByUsername(){
        UserDao userDao = new UserDaoImpl();
        if(userDao.queryUserByUsername("admin") == null){
            System.out.println("用户名可用");
        }else{
            System.out.println("用户名已存在");
        }
    }

    @Test
    public void queryUserByUsernameAndPassword(){
        UserDao userDao = new UserDaoImpl();
        if(userDao.queryUserByUsernameAndPassword("admin", "admin") == null){
            System.out.println("用户名或密码错误");
        }else{
            System.out.println("登录成功");
        }
    }

    @Test
    public void savaUser(){
        UserDao userDao = new UserDaoImpl();
        int i = userDao.saveUser(new User(null, "kang", "123456", "kang@qq.com"));
        System.out.println(i);
    }
}
