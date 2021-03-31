package cn.lifecode.recordaccount.service.bill;

import cn.lifecode.frameworkcore.bean.Request;
import cn.lifecode.frameworkcore.bean.Response;
import cn.lifecode.frameworkcore.util.DateUtil;
import cn.lifecode.recordaccount.common.constant.Constant;
import cn.lifecode.recordaccount.dto.bill.QueryBillInfoRequest;
import cn.lifecode.recordaccount.dto.bill.QueryBillInfoResponse;
import cn.lifecode.recordaccount.dto.bill.QueryMonthIncomeExpenseListRequest;
import cn.lifecode.recordaccount.dto.bill.QueryMonthIncomeExpenseListResponse;
import cn.lifecode.recordaccount.entity.DayRecordAccount;
import cn.lifecode.recordaccount.entity.DayRecordAccountObject;
import cn.lifecode.recordaccount.entity.QueryMonthIncomeExpenseObject;
import cn.lifecode.recordaccount.entity.bill.YearBillDetail;
import cn.lifecode.recordaccount.entity.bill.YearBillDetailObject;
import cn.lifecode.recordaccount.mapper.recordaccount.RecordAccountMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
                    yearBillDetailObject.setYear(year + "年");
                    yearBillDetailObject.setMonth(month + "月");
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
            // 查询年收入、年支出
            double monthIncome = recordAccountMapper.queryTotalMoney(INCOME, MONTH, month, userId);
            double monthExpense = recordAccountMapper.queryTotalMoney(EXPENSE, MONTH, month, userId);
            List<DayRecordAccountObject> dayRecordAccountObjectList = recordAccountMapper.queryRecordAccountObject(MONTH, month, "", startPage, pageSize, userId);
            List<DayRecordAccount> recordAccountsList = processDayRecordAccountList(dayRecordAccountObjectList);
            queryBillInfoResponse.setIncome(monthIncome);
            queryBillInfoResponse.setExpense(monthExpense);
            queryBillInfoResponse.setMonthBillDetailList(recordAccountsList);
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
     * 处理返回的list
     *
     * @param dayRecordAccountObjectList
     * @return
     */
    private List<DayRecordAccount> processDayRecordAccountList(List<DayRecordAccountObject> dayRecordAccountObjectList) {
        //存放本次返回集合
        List<DayRecordAccount> list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        if (dayRecordAccountObjectList.isEmpty()) {
            return list;
        }

        //存放的是当天时间（03月19日）
        String currentDate = "";
        //当天总支出
        double dayExpense = 0;
        DayRecordAccountObject object;
        // 如果是当天和第二天的临界值，则使用该对象进行过渡
        DayRecordAccountObject tempObject = null;
        Calendar calendar = Calendar.getInstance();
        //存放当天的集合
        List<DayRecordAccountObject> dayRecordAccountObjects = null;
        //存放当前对象
        DayRecordAccount dayRecordAccount = null;
        for (int i = 0; i < dayRecordAccountObjectList.size(); i++) {
            object = dayRecordAccountObjectList.get(i);
            calendar.setTime(object.getRecordTime());
            if (dayRecordAccountObjects == null) {
                // 表示新的一天
                currentDate = sdf.format(object.getRecordTime());
                if ("0".equals(object.getClassifyType())) {
                    dayExpense += Double.parseDouble(object.getBillMoney());
                }
                dayRecordAccountObjects = new ArrayList<>();
                dayRecordAccount = new DayRecordAccount();
                //临界值不为空，将上次做操作的今天第一条插入
                if (tempObject != null) {
                    dayRecordAccountObjects.add(tempObject);
                }
                //向当日对象中封装值
                dayRecordAccount.setDateStr(sdf.format(object.getRecordTime()) + " " + weekDays[calendar.get(Calendar.DAY_OF_WEEK) - 1]);
                dayRecordAccount.setDayExpense(dayExpense);
                dayRecordAccountObjects.add(object);
                // 如果是最后一条也只有一条
                if (dayRecordAccountObjectList.size() == (i + 1)) {
                    //最后一条直接封装对象
                    dayRecordAccount.setDayRecordAccountObjects(dayRecordAccountObjects);
                    list.add(dayRecordAccount);
                }
            } else {
                // 表示之前操作过的一天 （比较之前记录的时间 和 当前时间是否相同）
                if (currentDate.equals(sdf.format(object.getRecordTime()))) {
                    //表示还是之前的一天  置空临界对象
                    tempObject = null;
                    //表示是当天对象继续封装当天对象
                    dayRecordAccountObjects.add(object);
                    if (dayRecordAccountObjectList.size() == (i + 1)) {
                        //最后一条直接封装对象
                        dayRecordAccount.setDayRecordAccountObjects(dayRecordAccountObjects);
                        list.add(dayRecordAccount);
                    }
                } else {
                    //表示第二天 （1.将之前的写入，2.如果是最后一条 ，3，置空相应的对象 并 将今天的一条对象进行保存，在下一次进入的时候填充）
                    // 1.将之前的日对象写入
                    dayRecordAccount.setDayRecordAccountObjects(dayRecordAccountObjects);
                    list.add(dayRecordAccount);
                    //2.最后一条直接封装对象
                    if (dayRecordAccountObjectList.size() == (i + 1)) {
                        dayRecordAccountObjects.add(object);
                        dayRecordAccount.setDayRecordAccountObjects(dayRecordAccountObjects);
                        list.add(dayRecordAccount);
                    }
                    // 3.置空相应对象 并 将今天的一条对象进行保存，在下一次进入的时候填充
                    tempObject = object;
                    dayRecordAccountObjects = null;
                    dayRecordAccount = null;
                }
            }
        }
        return list;
    }
}
