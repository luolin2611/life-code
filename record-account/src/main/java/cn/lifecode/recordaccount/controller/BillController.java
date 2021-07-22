package cn.lifecode.recordaccount.controller;

import cn.lifecode.frameworkcore.bean.Request;
import cn.lifecode.frameworkcore.bean.Response;
import cn.lifecode.recordaccount.dto.bill.QueryBillInfoRequest;
import cn.lifecode.recordaccount.dto.bill.QueryBillInfoResponse;
import cn.lifecode.recordaccount.dto.bill.QueryMonthIncomeExpenseListRequest;
import cn.lifecode.recordaccount.dto.bill.QueryMonthIncomeExpenseListResponse;
import cn.lifecode.recordaccount.service.bill.BillService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 账单页面
 *
 * @author luolin
 * @date 2021-01-21 22:41:14
 */
@RestController
@RequestMapping("/bill")
public class BillController {
    private final BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    /**
     * 获取账单信息
     * 获取顶部信息根据传入的参数：返回
     * A.月支出/月收入、B.年收入/年支出、C.时间段-收入/支出；
     * AC --> 每日支出/每日收入、B-->返回每月收入支出
     *
     * @param request
     * @return
     */
    @PostMapping("/queryBillInfo") 
    public Response<QueryBillInfoResponse> queryBillInfo(@RequestBody Request<QueryBillInfoRequest> request) {
        return billService.queryBillInfo(request);
    }

    /**
     * 查询 月支出/月收入 列表
     *
     * @return
     */
    @PostMapping("/queryMonthIncomeExpenseList")
    public Response<QueryMonthIncomeExpenseListResponse> queryMonthIncomeExpenseList(@RequestBody Request<QueryMonthIncomeExpenseListRequest> request){
        return billService.queryMonthIncomeExpenseList(request);
    }
}
