package cn.lifecode.recordaccount.service.report;

import cn.lifecode.frameworkcore.bean.Request;
import cn.lifecode.frameworkcore.bean.Response;
import cn.lifecode.recordaccount.dto.report.QueryReportInfoRequest;
import cn.lifecode.recordaccount.dto.report.QueryReportInfoResponse;

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
}
