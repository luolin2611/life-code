package cn.lifecode.user.controller;

import cn.lifecode.frameworkcore.bean.Request;
import cn.lifecode.frameworkcore.bean.Response;
import cn.lifecode.user.entity.SysUser;
import cn.lifecode.user.service.sysuser.SysUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author luolin
 * @date 2021-01-12 10:38:48
 */
@RestController
@RequestMapping("/sysUser")
public class SysUserController {
    private final SysUserService sysUserService;

    SysUserController(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    @PostMapping("/addSysUser")
    public Response addSysUser(@RequestBody Request<SysUser> request) {
        this.sysUserService.addSysUser(request.getBody());
        return Response.success();
    }
}
