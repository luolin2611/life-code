package cn.lifecode.recordaccount.controller;

import cn.lifecode.frameworkcore.bean.Request;
import cn.lifecode.frameworkcore.bean.Response;
import cn.lifecode.frameworkcore.dto.ResponseObject;
import cn.lifecode.recordaccount.dto.icon.QueryAllIconRequest;
import cn.lifecode.recordaccount.dto.icon.QueryAllIconResponse;
import cn.lifecode.recordaccount.dto.icon.QueryAllIconUserRequest;
import cn.lifecode.recordaccount.dto.icon.QueryAllIconUserResponse;
import cn.lifecode.recordaccount.entity.Icon;
import cn.lifecode.recordaccount.service.icon.IconService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 图标
 * 图标相关操作
 *
 * @author luolin
 * @date 2021-01-18 13:24:29
 */
@RestController
@RequestMapping("/icon")
public class IconController {
    private final IconService iconService;

    public IconController(IconService iconService) {
        this.iconService = iconService;
    }

    /**
     * 添加图标
     *
     * @param request
     * @return
     */
    @PostMapping("/addIcon")
    public Response<ResponseObject> addIcon(@RequestBody Request<Icon> request) {
        return iconService.addIcon(request.getBody());
    }

    /**
     * 查询所有图标
     *
     * @return
     */
    @PostMapping("/queryAllIcon")
    public Response<QueryAllIconResponse> queryAllIcon(@RequestBody Request<QueryAllIconRequest> request) {
        return iconService.queryAllIcon(request);
    }

    /**
     * 查询所有用户图标
     *
     * @return
     */
    @PostMapping("/queryAllIconUser")
    public Response<QueryAllIconUserResponse> queryAllIconUser(@RequestBody Request<QueryAllIconUserRequest> request) {
        return iconService.queryAllIconUser(request);
    }
}
