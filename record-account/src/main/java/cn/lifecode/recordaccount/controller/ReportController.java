package cn.lifecode.recordaccount.controller;

import cn.lifecode.frameworkcore.bean.Request;
import cn.lifecode.frameworkcore.bean.Response;
import cn.lifecode.recordaccount.dto.report.QueryReportInfoRequest;
import cn.lifecode.recordaccount.dto.report.QueryReportInfoResponse;
import cn.lifecode.recordaccount.service.report.ReportService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 报表Controller
 *
 * @author rollin
 * @date 2021-03-30 14:16:36
 */
@RestController
@RequestMapping("/report")
public class ReportController {
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    /**
     * 报表信息查询
     *
     * @param request
     * @return
     */
    @PostMapping("/queryReportInfo")
    public Response<QueryReportInfoResponse> queryReportInfo(@RequestBody Request<QueryReportInfoRequest> request) {
        return reportService.queryReportInfo(request);
    }
}
