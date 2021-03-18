package cn.lifecode.recordaccount.controller;

import cn.lifecode.frameworkcore.bean.Request;
import cn.lifecode.frameworkcore.bean.Response;
import cn.lifecode.recordaccount.dto.home.AddRecordAcctRequest;
import cn.lifecode.recordaccount.dto.home.AddRecordAcctResponse;
import cn.lifecode.recordaccount.dto.home.HomeInitInfoRequest;
import cn.lifecode.recordaccount.dto.home.HomeInitInfoResponse;
import cn.lifecode.recordaccount.service.recordaccount.RecordAccountService;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 添加记账
     *
     * @param request
     * @return
     */
    @PostMapping("/addRecordAcct")
    public Response<AddRecordAcctResponse> addRecordAcct(@RequestBody Request<AddRecordAcctRequest> request) {
        return recordAccountService.addRecordAcct(request);
    }
}
