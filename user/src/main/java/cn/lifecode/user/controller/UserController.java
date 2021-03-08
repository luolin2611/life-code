package cn.lifecode.user.controller;

import cn.lifecode.frameworkcore.bean.Request;
import cn.lifecode.frameworkcore.bean.Response;
import cn.lifecode.user.common.dto.user.LoginReq;
import cn.lifecode.user.common.dto.user.LoginRes;
import cn.lifecode.user.entity.User;
import cn.lifecode.user.service.user.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 用户
 *
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
     * 用户登录
     * @param request
     * @return
     */
    @PostMapping("/login")
    public Response<LoginRes> login(@Valid @RequestBody Request<LoginReq> request) {
        return userService.login(request);
    }

    /**
     * 注册用户
     *
     * @param request
     * @return
     */
    @PostMapping("/registerUser")
    public Response registerUser(@RequestBody Request<User> request) {
        return userService.registerUser(request);
    }
}
