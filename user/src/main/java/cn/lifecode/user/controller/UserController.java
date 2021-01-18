package cn.lifecode.user.controller;

import cn.lifecode.frameworkcore.bean.Request;
import cn.lifecode.frameworkcore.bean.Response;
import cn.lifecode.user.entity.User;
import cn.lifecode.user.service.user.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户
 * @author luolin
 * @date 2021-01-14 18:20:48
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 添加用户
     */
    @PostMapping("/addUser")
    public Response addUser(@RequestBody Request<User> request) {
        userService.addUser(request.getBody());
        return Response.success();
    }
}
