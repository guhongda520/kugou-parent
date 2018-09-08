package cn.com.kugou.server.contoller;

import cn.com.kugou.domain.entity.User;
import cn.com.kugou.domain.service.UserService;
import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 黄尚
 * @brief
 * @details
 * @date 2018/9/5
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public String user(){
        User user = userService.getUser();
        System.out.println(user);
        return JSONArray.toJSONString(userService.getUser());
    }
}
