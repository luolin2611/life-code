package cn.lifecode.recordaccount.controller;

import cn.lifecode.frameworkcore.bean.Request;
import cn.lifecode.frameworkcore.bean.Response;
import cn.lifecode.frameworkcore.dto.RequestObject;
import cn.lifecode.frameworkcore.dto.ResponseObject;
import cn.lifecode.recordaccount.entity.RecordAccount;
import cn.lifecode.recordaccount.service.recordaccount.RecordAccountService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 记账
 * 记账的一些操作
 *
 * @author luolin
 * @date 2021-01-18 09:08:38
 */
@RestController
@RequestMapping("/recordAccount")
public class RecordAccountController {
    private final RecordAccountService recordAccountService;

    public RecordAccountController(RecordAccountService recordAccountService) {
        this.recordAccountService = recordAccountService;
    }

    /**
     * 添加记账记录
     *
     * @param request
     * @return
     */
    @PostMapping("/addRecordAccount")
    public Response<ResponseObject> addRecordAccount(@RequestBody Request<RecordAccount> request) {
        return recordAccountService.addRecordAccount(request.getBody());
    }

    /**
     * 将Excel账单导入到数据库
     *
     * @param request
     * @return
     */
    @PostMapping("/excelConversionToDataBase")
    public Response<ResponseObject> excelConversionToDataBase(@RequestBody Request<RequestObject> request) {
        return recordAccountService.excelConversionToDataBase();
    }


}
