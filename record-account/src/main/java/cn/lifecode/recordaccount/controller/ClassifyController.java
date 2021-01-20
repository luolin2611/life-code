package cn.lifecode.recordaccount.controller;

import cn.lifecode.frameworkcore.bean.Request;
import cn.lifecode.frameworkcore.bean.Response;
import cn.lifecode.frameworkcore.dto.ResponseObject;
import cn.lifecode.recordaccount.dto.classify.InitClassifyRequest;
import cn.lifecode.recordaccount.service.classify.ClassifyService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 分类
 *
 * @author luolin
 * @date 2021-01-15 17:59:08
 */
@RestController
@RequestMapping("/classify")
public class ClassifyController {
    private final ClassifyService classifyService;

    public ClassifyController(ClassifyService classifyService) {
        this.classifyService = classifyService;
    }

    /**
     * 初始化分类信息
     * 当创建新用户的时候会为该用户分配一个初始化的分类ID
     */
    @PostMapping("/initClassify")
    public Response<ResponseObject> initClassify(@RequestBody Request<InitClassifyRequest> request) {
        return classifyService.addClassify(request.getBody().getUserId());
    }
}
