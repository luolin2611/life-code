package cn.lifecode.recordaccount.service.report;

import cn.lifecode.frameworkcore.bean.Request;
import cn.lifecode.frameworkcore.bean.Response;
import cn.lifecode.recordaccount.dto.report.QueryReportInfoRequest;
import cn.lifecode.recordaccount.dto.report.QueryReportInfoResponse;
import cn.lifecode.recordaccount.entity.QueryReportInfoObject;
import cn.lifecode.recordaccount.mapper.recordaccount.RecordAccountMapper;
import cn.lifecode.recordaccount.mapper.report.ReportMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 报表service
 *
 * @author rollin
 * @date 2021-03-30 14:21:34
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ReportServiceImpl implements ReportService {
    private final ReportMapper reportMapper;
    private final RecordAccountMapper recordAccountMapper;
    public static final String REPORT_TYPE_YEAR = "0";
    public static final String REPORT_TYPE_MONTH = "1";
    public static final String REPORT_TYPE_PERIOD = "2";

    public ReportServiceImpl(ReportMapper reportMapper, RecordAccountMapper recordAccountMapper) {
        this.reportMapper = reportMapper;
        this.recordAccountMapper = recordAccountMapper;
    }

    /**
     * 报表查询信息
     *
     * @param request
     * @return
     */
    @Override
    public Response<QueryReportInfoResponse> queryReportInfo(Request<QueryReportInfoRequest> request) {
        // 1.初始化内容
        QueryReportInfoRequest body = request.getBody();
        QueryReportInfoResponse response = new QueryReportInfoResponse();
        List<QueryReportInfoObject> objects;
        String reportType = body.getReportType();
        //开始时间
        String startDate = "";
        //请求类型
        String dateType = "";
        //年报表
        if (REPORT_TYPE_YEAR.equals(reportType)) {
            startDate = body.getYear();
            dateType = "year";
        }
        //月报表
        if (REPORT_TYPE_MONTH.equals(reportType)) {
            startDate = body.getMonth();
            dateType = "month";
        }
        //自定义 - 时间段报表
        if (REPORT_TYPE_PERIOD.equals(reportType)) {

        }
        double expense = recordAccountMapper.queryTotalMoney("0", dateType, startDate, body.getUserId());
        double income = recordAccountMapper.queryTotalMoney("1", dateType, startDate, body.getUserId());
        response.setExpense(expense);
        response.setIncome(income);
        List<QueryReportInfoObject> objectList = reportMapper.queryReportInfo(dateType, startDate, "", body.getUserId(), body.getType());
        if (!objectList.isEmpty()) {
            int sum = 0;
            QueryReportInfoObject object;
            objects = new ArrayList<>();
            //计算总和
            for (int i = 0; i < objectList.size(); i++) {
                object = objectList.get(i);
                sum += object.getMoney();
            }
            //放置百分比
            for(int i=0; i<objectList.size(); i++) {
                object = objectList.get(i);
                object.setProportion(object.getMoney()/sum);
                objects.add(object);
            }
            response.setReportInfoList(objects);
        }


        return Response.success(response);
    }
}
