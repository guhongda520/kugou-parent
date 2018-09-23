package cn.com.kugou.provider.contoller;

import cn.com.kugou.domain.entity.User;
import cn.com.kugou.domain.service.UserService;
import com.alibaba.fastjson.JSONArray;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * @author 黄尚
 * @brief
 * @details
 * @date 2018/9/5
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired(required = false)
    private UserService userService;

    @GetMapping("/user")
    @ApiOperation(value="更新用户详细信息", notes="根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long"),
    })
    public String user(@ApiIgnore Long id) throws InterruptedException {
        if(id % 2 ==0){
             Thread.sleep(10000L);
        }
        User user = (User)userService.getUser();
        System.out.println(user);
        return JSONArray.toJSONString(user);
    }
}
