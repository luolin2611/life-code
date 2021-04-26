package cn.lifecode.recordaccount.service.report;

import cn.lifecode.frameworkcore.bean.Request;
import cn.lifecode.frameworkcore.bean.Response;
import cn.lifecode.recordaccount.dto.report.QueryReportDetailsListRequest;
import cn.lifecode.recordaccount.dto.report.QueryReportDetailsListResponse;
import cn.lifecode.recordaccount.dto.report.QueryReportInfoRequest;
import cn.lifecode.recordaccount.dto.report.QueryReportInfoResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 报表service
 *
 * @author rollin
 * @date 2021-03-30 14:21:34
 */
public interface ReportService {
    /**
     * 报表查询信息
     *
     * @param request
     * @return
     */
    Response<QueryReportInfoResponse> queryReportInfo(Request<QueryReportInfoRequest> request);

    /**
     * 请求详情列表
     *
     * @param request
     * @return
     */
    Response<QueryReportDetailsListResponse> queryReportDetailsList(Request<QueryReportDetailsListRequest> request);
}
