package cn.lifecode.recordaccount.service.bill;

import cn.lifecode.frameworkcore.bean.Request;
import cn.lifecode.frameworkcore.bean.Response;
import cn.lifecode.recordaccount.common.constant.Constant;
import cn.lifecode.recordaccount.dto.bill.QueryBillInfoRequest;
import cn.lifecode.recordaccount.dto.bill.QueryBillInfoResponse;
import cn.lifecode.recordaccount.entity.bill.YearBillDetail;
import cn.lifecode.recordaccount.entity.bill.YearBillDetailObject;
import cn.lifecode.recordaccount.mapper.recordaccount.RecordAccountMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luolin
 * @date 2021-01-27 09:24:28
 */
@Service
public class BillServiceImpl implements BillService {
    private static final String EXPENSE = Constant.EXPENSE;
    private static final String INCOME = Constant.INCOME;
    private static final String YEAR = Constant.YEAR;
    private static final String MONTH = Constant.MONTH;
    private static final String PERIOD = Constant.PERIOD;
    private static final String DAY = Constant.DAY;
    public static final String BILL_TYPE_YEAR = "0";
    public static final String BILL_TYPE_MONTH = "1";
    public static final String BILL_TYPE_PERIOD = "2";
    private final RecordAccountMapper recordAccountMapper;

    public BillServiceImpl(RecordAccountMapper recordAccountMapper) {
        this.recordAccountMapper = recordAccountMapper;
    }

    /**
     * 获取账单信息
     * 根据 billType 进行返回 （0-年,1-月,2-时间段）
     * 1、2 --> 返回每日支出/每日收入；0-->返回每月收入支出
     *
     * @param request
     * @return
     */
    @Override
    public Response<QueryBillInfoResponse> queryBillInfo(Request<QueryBillInfoRequest> request) {
        QueryBillInfoRequest queryBillInfoRequest = request.getBody();
        String userId = queryBillInfoRequest.getUserId();
        String billType = queryBillInfoRequest.getBillType();
        QueryBillInfoResponse queryBillInfoResponse = new QueryBillInfoResponse();

        //1.年
        if (billType.equals(BILL_TYPE_YEAR)) {
            YearBillDetailObject yearBillDetailObject;
            List<YearBillDetailObject> yearBillDetailObjectList = new ArrayList<>();
            String year = queryBillInfoRequest.getYear();
            // 1.1 查询指定年份的每月信息
            String time = "";
            // 循环到month 为1
            int month = 12;
            double expense = 0;
            double income = 0;
            while (month > 0) {
                //eg: 202112
                time = year + (month < 10 ? ("0" + month) : month);
                income = recordAccountMapper.queryTotalMoney(INCOME, MONTH, time, userId);
                expense = recordAccountMapper.queryTotalMoney(EXPENSE, MONTH, time, userId);
                if (income != 0 || expense != 0) {
                    //有月支出或月收入 才计入
                    yearBillDetailObject = new YearBillDetailObject();
                    yearBillDetailObject.setYear(year);
                    yearBillDetailObject.setMonth(month + "");
                    yearBillDetailObject.setExpense(expense);
                    yearBillDetailObject.setIncome(income);
                    yearBillDetailObject.setSurplus(income - expense);
                    yearBillDetailObjectList.add(yearBillDetailObject);
                }
                month--;
            }
            // 1.2 查询年收入、年支出
            double yearIncome = recordAccountMapper.queryTotalMoney(INCOME, YEAR, year, userId);
            double yearExpense = recordAccountMapper.queryTotalMoney(EXPENSE, YEAR, year, userId);
            // 1.3 分装返回对象
            YearBillDetail yearBillDetail = new YearBillDetail();
            yearBillDetail.setYearBillDetailObjectList(yearBillDetailObjectList);
            queryBillInfoResponse.setIncome(yearIncome);
            queryBillInfoResponse.setExpense(yearExpense);
            queryBillInfoResponse.setYearBillDetail(yearBillDetail);
        }
        //2.月
        //前端传入参数： eg: 202101
        if (billType.equals(BILL_TYPE_MONTH)) {
            String month = queryBillInfoRequest.getMonth();
            int pageSize = queryBillInfoRequest.getPageSize();
            int startPage = (queryBillInfoRequest.getStartPage() - 1) * pageSize;
            queryBillInfoResponse.setMonthBillDetailList(recordAccountMapper.queryRecordAccounts(MONTH, month, "", startPage, pageSize));
        }
        // 3.时间段
        if (billType.equals(BILL_TYPE_PERIOD)) {
            String startDate = queryBillInfoRequest.getStartDate();
            String endDate = queryBillInfoRequest.getEndDate();
            int pageSize = queryBillInfoRequest.getPageSize();
            int startPage = (queryBillInfoRequest.getStartPage() - 1) * pageSize;
            queryBillInfoResponse.setPeriodBillDetailList(recordAccountMapper.queryRecordAccounts(PERIOD, startDate, endDate, startPage, pageSize));
        }
        return Response.success(queryBillInfoResponse);
    }

}
