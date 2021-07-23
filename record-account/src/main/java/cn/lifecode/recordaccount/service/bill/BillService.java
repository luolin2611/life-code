package cn.lifecode.recordaccount.service.bill;

import cn.lifecode.frameworkcore.bean.Request;
import cn.lifecode.frameworkcore.bean.Response;
import cn.lifecode.recordaccount.dto.bill.*;

/**
 * @author luolin
 * @date 2021-01-27 09:18:10
 */
public interface BillService {
    Response<QueryBillInfoResponse> queryBillInfo(Request<QueryBillInfoRequest> request);

    /**
     * 查询 月支出/月收入 列表
     *
     * @return
     */
    Response<QueryMonthIncomeExpenseListResponse> queryMonthIncomeExpenseList(Request<QueryMonthIncomeExpenseListRequest> request);

    /**
     * 查询年账单折线图数据
     *
     * @param request 请求参数
     * @return 每月的收入和支出list
     */
    Response<QueryYearBrokeLineListResponse> queryYearBrokeLineList(Request<QueryYearBrokeLineListRequest> request);
}
