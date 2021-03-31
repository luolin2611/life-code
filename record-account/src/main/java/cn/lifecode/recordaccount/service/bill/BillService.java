package cn.lifecode.recordaccount.service.bill;

import cn.lifecode.frameworkcore.bean.Request;
import cn.lifecode.frameworkcore.bean.Response;
import cn.lifecode.recordaccount.dto.bill.QueryBillInfoRequest;
import cn.lifecode.recordaccount.dto.bill.QueryBillInfoResponse;
import cn.lifecode.recordaccount.dto.bill.QueryMonthIncomeExpenseListRequest;
import cn.lifecode.recordaccount.dto.bill.QueryMonthIncomeExpenseListResponse;

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
}
