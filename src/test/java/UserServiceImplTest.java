import com.kang.bean.User;
import com.kang.service.UserService;
import com.kang.service.iml.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Kangshitao
 * @date 2021年6月11日 下午7:29
 */
public class UserServiceImplTest {

    UserService userService = new UserServiceImpl();
    @Test
    public void registUser() {
        userService.registUser(new User(null,"kkk","123","128@11.com"));
    }

    @Test
    public void login() {
        System.out.println(userService.login(new User(null, "kkk", "123", null)));
    }

    @Test
    public void existUsername() {
        if(userService.existUsername("asdfasf")){
            System.out.println("username is exist");
        }else{
            System.out.println("username is availble");
        }
    }
}