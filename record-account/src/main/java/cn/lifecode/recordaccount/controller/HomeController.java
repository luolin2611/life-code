package cn.lifecode.recordaccount.controller;

import cn.lifecode.frameworkcore.bean.Request;
import cn.lifecode.frameworkcore.bean.Response;
import cn.lifecode.frameworkcore.dto.RequestObject;
import cn.lifecode.frameworkcore.util.DateUtil;
import cn.lifecode.recordaccount.dto.home.HomeInitInfoRequest;
import cn.lifecode.recordaccount.dto.home.HomeInitInfoResponse;
import cn.lifecode.recordaccount.service.recordaccount.RecordAccountService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;

@RestController
@RequestMapping("/home")
public class HomeController {
    private final RecordAccountService recordAccountService;

    public HomeController(RecordAccountService recordAccountService) {
        this.recordAccountService = recordAccountService;
    }

    /**
     * Home 页面加载获取必要信息
     *
     * @param request
     * @return
     */
    @PostMapping("/homeInitInfo")
    public Response<HomeInitInfoResponse> homeInitInfo(@RequestBody Request<HomeInitInfoRequest> request) {
        String userId = request.getBody().getUserId();
        return recordAccountService.homeInitInfo(userId);
    }

    @PostMapping("/getSystem")
    public Response getSystem() {
        HashMap map = new HashMap();
        map.put("system_time", DateUtil.formatTime(LocalDateTime.now(), DateUtil.FULL_TIME_PATTERN));
        return Response.success(map);
    }
}
