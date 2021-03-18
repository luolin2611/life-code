package cn.lifecode.recordaccount.service.recordaccount;


import cn.lifecode.frameworkcore.bean.Request;
import cn.lifecode.frameworkcore.bean.Response;
import cn.lifecode.frameworkcore.dto.ResponseObject;
import cn.lifecode.recordaccount.dto.home.AddRecordAcctRequest;
import cn.lifecode.recordaccount.dto.home.AddRecordAcctResponse;
import cn.lifecode.recordaccount.dto.home.HomeInitInfoResponse;
import cn.lifecode.recordaccount.entity.RecordAccount;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 记账service
 *
 * @author luolin
 * @date
 */
public interface RecordAccountService {
    Response<ResponseObject> addRecordAccount(RecordAccount recordAccount);

    Response<ResponseObject> excelConversionToDataBase();

    Response<HomeInitInfoResponse> homeInitInfo(String userId);

    Response<AddRecordAcctResponse> addRecordAcct(Request<AddRecordAcctRequest> request);
}
