package cn.lifecode.recordaccount.service.bill;

import cn.lifecode.frameworkcore.bean.Request;
import cn.lifecode.frameworkcore.bean.Response;
import cn.lifecode.recordaccount.dto.bill.QueryBillInfoRequest;
import cn.lifecode.recordaccount.dto.bill.QueryBillInfoResponse;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author luolin
 * @date 2021-01-27 09:18:10
 */
public interface BillService {
    Response<QueryBillInfoResponse> queryBillInfo(@RequestBody Request<QueryBillInfoRequest> request);
}
