package cn.lifecode.recordaccount.service.bill;

import cn.lifecode.frameworkcore.bean.Request;
import cn.lifecode.frameworkcore.bean.Response;
import cn.lifecode.frameworkcore.util.DateUtil;
import cn.lifecode.frameworkcore.util.Utils;
import cn.lifecode.recordaccount.common.constant.Constant;
import cn.lifecode.recordaccount.dto.bill.*;
import cn.lifecode.recordaccount.entity.DayRecordAccount;
import cn.lifecode.recordaccount.entity.DayRecordAccountObject;
import cn.lifecode.recordaccount.entity.QueryMonthIncomeExpenseObject;
import cn.lifecode.recordaccount.entity.RecordAccount;
import cn.lifecode.recordaccount.entity.bill.YearBillDetail;
import cn.lifecode.recordaccount.entity.bill.YearBillDetailObject;
import cn.lifecode.recordaccount.mapper.recordaccount.RecordAccountMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author luolin
 * @date 2021-01-27 09:24:28
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BillServiceImpl implements BillService {
    private static final String EXPENSE = Constant.EXPENSE;
    private static final String INCOME = Constant.INCOME;
    private static final String YEAR = Constant.YEAR;
    private static final String MONTH = Constant.MONTH;
    private static final String PERIOD = Constant.PERIOD;
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
            double totalSurplus = 0;
            double surplus = 0;
            while (month > 0) {
                //eg: 202112
                time = year + (month < 10 ? ("0" + month) : month);
                income = recordAccountMapper.queryTotalMoney(INCOME, MONTH, time, userId);
                expense = recordAccountMapper.queryTotalMoney(EXPENSE, MONTH, time, userId);
                if (income != 0 || expense != 0) {
                    //有月支出或月收入 才计入
                    surplus = income - expense;
                    totalSurplus += surplus;
                    yearBillDetailObject = new YearBillDetailObject();
                    yearBillDetailObject.setYear(year + "年");
                    yearBillDetailObject.setMonth(month + "月");
                    yearBillDetailObject.setExpense(Utils.getTwoDecimalPlaces(expense));
                    yearBillDetailObject.setIncome(Utils.getTwoDecimalPlaces(income));
                    yearBillDetailObject.setSurplus(Utils.getTwoDecimalPlaces(surplus));
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
            queryBillInfoResponse.setIncome(Utils.getTwoDecimalPlaces(yearIncome));
            queryBillInfoResponse.setExpense(Utils.getTwoDecimalPlaces(yearExpense));
            queryBillInfoResponse.setYearBillDetail(yearBillDetail);
            yearBillDetail.setTotalSurplus(Utils.getTwoDecimalPlaces(totalSurplus));
        }
        //2.月
        //前端传入参数： eg: 202101
        if (billType.equals(BILL_TYPE_MONTH)) {
            String month = queryBillInfoRequest.getMonth();
            int pageSize = queryBillInfoRequest.getPageSize();
            int startPage = (queryBillInfoRequest.getStartPage() - 1) * pageSize;
            // 查询年收入、年支出
            double monthIncome = recordAccountMapper.queryTotalMoney(INCOME, MONTH, month, userId);
            double monthExpense = recordAccountMapper.queryTotalMoney(EXPENSE, MONTH, month, userId);
            List<DayRecordAccountObject> dayRecordAccountObjectList = recordAccountMapper.queryRecordAccountObject(MONTH, month, "", startPage, pageSize, userId);
            List<DayRecordAccount> recordAccountsList = processDayRecordAccountList(dayRecordAccountObjectList);
            //计算每日消费的总和
            List<DayRecordAccount> newRecordAccountsList = new ArrayList<>();
            for (int i = 0; i < recordAccountsList.size(); i++) {
                DayRecordAccount obj = recordAccountsList.get(i);
                Double sum = obj.getDayRecordAccountObjects().stream().filter(dayRecordAccountObjectFilter -> "0".equals(dayRecordAccountObjectFilter.getClassifyType())).collect(Collectors.summingDouble(DayRecordAccountObject::getBillMoney));
                obj.setDayExpense(Utils.getTwoDecimalPlaces(sum));
                newRecordAccountsList.add(obj);
            }
            //封装返回内容
            queryBillInfoResponse.setIncome(Utils.getTwoDecimalPlaces(monthIncome));
            queryBillInfoResponse.setExpense(Utils.getTwoDecimalPlaces(monthExpense));
            queryBillInfoResponse.setTotal(dayRecordAccountObjectList.size());
            queryBillInfoResponse.setMonthBillDetailList(newRecordAccountsList);
        }
        // 3.时间段
        if (billType.equals(BILL_TYPE_PERIOD)) {
            String startDate = queryBillInfoRequest.getStartDate();
            String endDate = queryBillInfoRequest.getEndDate();
            int pageSize = queryBillInfoRequest.getPageSize();
            int startPage = (queryBillInfoRequest.getStartPage() - 1) * pageSize;
            queryBillInfoResponse.setPeriodBillDetailList(recordAccountMapper.queryRecordAccounts(PERIOD, startDate, endDate, startPage, pageSize, userId));
        }
        return Response.success(queryBillInfoResponse);
    }

    /**
     * 查询 月支出/月收入 列表
     *
     * @param request
     * @return
     */
    @Override
    public Response<QueryMonthIncomeExpenseListResponse> queryMonthIncomeExpenseList(Request<QueryMonthIncomeExpenseListRequest> request) {
        QueryMonthIncomeExpenseListRequest body = request.getBody();
        String queryYearMonthTime = body.getQueryYearMonthTime();
        Date date = DateUtil.returnDateFromString(queryYearMonthTime, DateUtil.FULL_YEAR_MONTH_PATTERN);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        //1.拼接数据
        //获取本月的最后一天
        int lastMonth = calendar.getActualMaximum(Calendar.DATE);
        List<QueryMonthIncomeExpenseObject> list = new ArrayList<>();
        QueryMonthIncomeExpenseObject object;
        for (int i = 1; i <= lastMonth; i++) {
            object = new QueryMonthIncomeExpenseObject();
            //放入时间yyyyMMdd
            String dayStr = i + "";
            if (i < 10) {
                dayStr = "0" + i;
            }
            String timeStr = queryYearMonthTime + dayStr;
            object.setTime(timeStr);
            //查询指定日期的余额
            double totalMoney = recordAccountMapper.queryTotalMoney(body.getType(), "day", timeStr, body.getUserId());
            object.setMoney(totalMoney);
            list.add(object);
        }
        //2.填充返回内容
        QueryMonthIncomeExpenseListResponse response = new QueryMonthIncomeExpenseListResponse();
        response.setList(list);
        return Response.success(response);
    }

    /**
     * 查询年账单折线图数据
     *
     * @param request 请求参数
     * @return 每月的收入和支出list
     */
    @Override
    public Response<QueryYearBrokeLineListResponse> queryYearBrokeLineList(Request<QueryYearBrokeLineListRequest> request) {
        String year = request.getBody().getYear();
        List<String> incomeList = recordAccountMapper.queryYearBrokeLineIncomeOrExpenseList("1", year);
        List<String> expenseList = recordAccountMapper.queryYearBrokeLineIncomeOrExpenseList("0", year);
        QueryYearBrokeLineListResponse object = new QueryYearBrokeLineListResponse();
        object.setIncomeList(incomeList);
        object.setExpenseList(expenseList);
        return Response.success(object);
    }

    /**
     * 处理返回的list
     *
     * @param dayRecordAccountObjectList
     * @return
     */
    private List<DayRecordAccount> processDayRecordAccountList(List<DayRecordAccountObject> dayRecordAccountObjectList) {
        // 1.初始化内容
        //存放本次返回集合
        List<DayRecordAccount> list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        if (dayRecordAccountObjectList.isEmpty()) {
            return list;
        }
        DayRecordAccountObject object;
        Calendar calendar = Calendar.getInstance();
        //存放重新组合过后的List
        LinkedHashMap<String, DayRecordAccount> map = new LinkedHashMap<>();
        //存放当前对象
        DayRecordAccount dayRecordAccount = null;
        //存放当天的集合
        List<DayRecordAccountObject> dayRecordAccountObjects = null;
        // 2.封装数据
        for (int i = 0, size = dayRecordAccountObjectList.size(); i < size; i++) {
            object = dayRecordAccountObjectList.get(i);
            calendar.setTime(object.getRecordTime());
            String dateStr = sdf.format(object.getRecordTime()) + " " + weekDays[calendar.get(Calendar.DAY_OF_WEEK) - 1];
            if (!map.containsKey(dateStr)) {
                // 没有添加过今天的内容
                dayRecordAccountObjects = new ArrayList<>();
                dayRecordAccount = new DayRecordAccount();
                dayRecordAccountObjects.add(object);
                dayRecordAccount.setDayRecordAccountObjects(dayRecordAccountObjects);
                dayRecordAccount.setDateStr(dateStr);
            } else {
                // 已经添加过了当前的内容，补全当天内容
                dayRecordAccount = map.get(dateStr);
                dayRecordAccountObjects = dayRecordAccount.getDayRecordAccountObjects();
                dayRecordAccountObjects.add(object);
                dayRecordAccount.setDayRecordAccountObjects(dayRecordAccountObjects);
            }
            map.put(dateStr, dayRecordAccount);
        }
        // 3.将map中的内容添加到list中
        list.addAll(map.values());
        return list;
    }

    @Override
    public BillExportResponse billExportQueryRecordAccount(BillExportRequest request) {
        List<RecordAccount> recordAccounts = recordAccountMapper.billExportQueryRecordAccount(request.getExportType(),
                request.getYear(), request.getYear() + request.getMonth(), request.getStartDate(), request.getEndDate(), request.getUserId());
        return new BillExportResponse(recordAccounts);
    }
}
