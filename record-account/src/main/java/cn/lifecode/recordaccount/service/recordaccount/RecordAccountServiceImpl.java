package cn.lifecode.recordaccount.service.recordaccount;


import cn.lifecode.frameworkcore.bean.Request;
import cn.lifecode.frameworkcore.bean.Response;
import cn.lifecode.frameworkcore.dto.ResponseObject;
import cn.lifecode.frameworkcore.util.DateUtil;
import cn.lifecode.frameworkcore.util.ExcelPoiUtil;
import cn.lifecode.recordaccount.dto.home.AddRecordAcctRequest;
import cn.lifecode.recordaccount.dto.home.AddRecordAcctResponse;
import cn.lifecode.recordaccount.dto.home.HomeInitInfoResponse;
import cn.lifecode.recordaccount.entity.Classify;
import cn.lifecode.recordaccount.entity.DayRecordAccount;
import cn.lifecode.recordaccount.entity.DayRecordAccountObject;
import cn.lifecode.recordaccount.entity.RecordAccount;
import cn.lifecode.recordaccount.mapper.classify.ClassifyMapper;
import cn.lifecode.recordaccount.mapper.recordaccount.RecordAccountMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 记账service impl
 *
 * @author luolin
 * @date 2021-01-18 09:13:28
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RecordAccountServiceImpl implements RecordAccountService {
    private final RecordAccountMapper recordAccountMapper;
    private final ClassifyMapper classifyMapper;

    public RecordAccountServiceImpl(RecordAccountMapper recordAccountMapper, ClassifyMapper classifyMapper) {
        this.recordAccountMapper = recordAccountMapper;
        this.classifyMapper = classifyMapper;
    }

    /**
     * 插入记账记录
     *
     * @param recordAccount
     */
    @Override
    public Response<ResponseObject> addRecordAccount(RecordAccount recordAccount) {
        Date date = new Date();
        recordAccount.setUpdateTime(date);
        recordAccount.setRecordTime(date);
        recordAccountMapper.addRecordAccount(recordAccount);
        return Response.success(new ResponseObject());
    }

    /**
     * 查询总额
     *
     * @return
     */
    @Override
    public Response<HomeInitInfoResponse> homeInitInfo(String userId) {
        String dateStr = DateUtil.formatTime(LocalDateTime.now(), DateUtil.YMD_TIME_SPLIT_PATTERN);
        String[] dateArr = dateStr.split("-");
        //用于标记三日内是否有记录
        boolean isQuery = false;
        //用户查询当日是否有值
        boolean isCurrentQuery = false;
        // 近三日账单总数
        int billNum = 0;
        //1.本月支出 （202101）
        double moneyOutMonth = recordAccountMapper.queryTotalMoney("0", "month", dateArr[0] + dateArr[1], userId);
        //2.本月收入
        double moneyInMonth = recordAccountMapper.queryTotalMoney("1", "month", dateArr[0] + dateArr[1], userId);
        //3.查询最近3日的账单记录
        //过去时间
        Date passDate;
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        List<DayRecordAccount> dayRecordAccountList = new ArrayList<>();
        DayRecordAccount dayRecordAccount;
        Calendar calendar = Calendar.getInstance();
        // 3.1 查询当天
        dayRecordAccount = rawDayRecordAccount("今天", new Date(), userId);
        isCurrentQuery = dayRecordAccount.getDayRecordAccountObjects().size() > 0;
        billNum += dayRecordAccount.getDayRecordAccountObjects().size();
        if (isCurrentQuery) {
            isQuery = true;
            dayRecordAccountList.add(dayRecordAccount);
        }
        // 3.2 查询昨天
        passDate = DateUtil.currentDateAddOrSub(DateUtil.OPRATETYPE_SUBTRACT, DateUtil.DAYTYPE_DAY, 1);
        calendar.setTime(passDate);
        dayRecordAccount = rawDayRecordAccount(weekDays[(calendar.get(Calendar.DAY_OF_WEEK) - 1)], passDate, userId);
        calendar.setTime(passDate);
        isCurrentQuery = dayRecordAccount.getDayRecordAccountObjects().size() > 0;
        billNum += dayRecordAccount.getDayRecordAccountObjects().size();
        if (isCurrentQuery) {
            isQuery = true;
            dayRecordAccountList.add(dayRecordAccount);
        }
        // 3.3 查询前天
        passDate = DateUtil.currentDateAddOrSub(DateUtil.OPRATETYPE_SUBTRACT, DateUtil.DAYTYPE_DAY, 2);
        calendar.setTime(passDate);
        dayRecordAccount = rawDayRecordAccount(weekDays[(calendar.get(Calendar.DAY_OF_WEEK) - 1)], passDate, userId);
        calendar.setTime(passDate);
        isCurrentQuery = dayRecordAccount.getDayRecordAccountObjects().size() > 0;
        billNum += dayRecordAccount.getDayRecordAccountObjects().size();
        if (isCurrentQuery) {
            isQuery = true;
            dayRecordAccountList.add(dayRecordAccount);
        }
        // 4.封装response对象
        HomeInitInfoResponse homeInitInfoResponse = new HomeInitInfoResponse();
        homeInitInfoResponse.setMonthOutTotal(moneyOutMonth);
        homeInitInfoResponse.setMonthInTotal(moneyInMonth);
        homeInitInfoResponse.setBillNum(billNum);
        homeInitInfoResponse.setThreedayRecordAccount(isQuery ? dayRecordAccountList : new ArrayList());
        return Response.success(homeInitInfoResponse);
    }

    /**
     * 从Excel中导入数据库
     *
     * @return
     */
    @Override
    public Response<ResponseObject> excelConversionToDataBase() {
//        String excelFilePath = "/Volumes/FILE_WORK/work/Code/SpringBoot/life-code/folder/recordaccount/网易有钱记账数据20180119-20190118.xlsx";
//        String excelFilePath = "/Volumes/FILE_WORK/work/Code/SpringBoot/life-code/folder/recordaccount/网易有钱记账数据20190119-20200118.xlsx";
        String excelFilePath = "/Volumes/FILE_WORK/work/Code/SpringBoot/life-code/folder/recordaccount/网易有钱记账数据20200119-20210118.xlsx";
        String[] columns = new String[]{"record_time", "classify_name", "bill_money", "remark"};
        List list = ExcelPoiUtil.getExcelToList(excelFilePath, columns);
        List recordList;
        Map sheetList = (Map) list.get(0);
        // 1.支出插入数据库
        recordList = (List) sheetList.get("支出");
        insertRecordAccount(recordList, "0");
        // 2.收入插入数据库
        recordList = (List) sheetList.get("收入");
        insertRecordAccount(recordList, "1");
        return Response.success(new ResponseObject());
    }

    /**
     * 记账功能
     *
     * @param request
     * @return
     */
    @Override
    public Response<AddRecordAcctResponse> addRecordAcct(Request<AddRecordAcctRequest> request) {
        recordAccountMapper.addRecordAcct(request.getBody());
        return Response.success("记账成功");
    }

    /**
     * @param recordList
     * @param type       0-支出，1-收入
     */
    public void insertRecordAccount(List recordList, String type) {
        RecordAccount recordAccount;
        Map tempMap;
        for (int i = 0; i < recordList.size(); i++) {
            tempMap = (Map) recordList.get(i);
            String classifyName = (String) tempMap.get("classify_name");
            Classify classify = classifyMapper.selectClassifyByClassifyName(classifyName.trim());
            //1.如果classify为空，则新增分类
            if (classify == null) {
                // 1.1 查询当前分类的排序序号
                int sort = classifyMapper.querySortByUserIdAndType(45, type);
                //1.2 封装插入库的classify对象
                classify = new Classify();
                classify.setType(type);
                classify.setUpdatTime(new Date());
                classify.setClassifyName(classifyName);
                // classify.setIconId(); // 此处先不给添加iconId
                //此处 1 表示用户添加
                classify.setAddType("1");
                classify.setUserId(45);
                classifyMapper.addClassify(classify);
                // 1.3 再次查询最新的分类信息
                classify = classifyMapper.selectClassifyByClassifyName(classifyName.trim());
            }
            //写入记账列表
            Date date = DateUtil.returnDateFromString((String) tempMap.get("record_time") + " 12:00:00", DateUtil.FULL_TIME_SPLIT_PATTERN);
            recordAccount = new RecordAccount();
            recordAccount.setRecordTime(date);
            recordAccount.setUpdateTime(date);
            recordAccount.setRemark((String) tempMap.get("remark"));
            recordAccount.setBillMoney(Double.parseDouble(tempMap.get("bill_money") + ""));
            recordAccount.setClassifyId(classify.getClassifyId());
            recordAccount.setClassifyName(classifyName);
            recordAccount.setClassifyType(type);
            recordAccount.setUserId(45);
            recordAccountMapper.addRecordAccount(recordAccount);
        }
    }

    /**
     * 加工DayRecordAccount
     *
     * @param dayType 时间显示内容（"今天"、"星期X"）
     * @return
     */
    public DayRecordAccount rawDayRecordAccount(String dayType, Date date, String userId) {
        DayRecordAccount dayRecordAccount = new DayRecordAccount();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sdf1 = new SimpleDateFormat("MM月dd日");
        List<DayRecordAccountObject> dayRecordAccountObjectList;
        dayRecordAccountObjectList = recordAccountMapper.queryRecordAccountObject("day", sdf.format(date), "", 0, 0, userId);
        dayRecordAccount.setDateStr(sdf1.format(date) + " " + dayType);
        double totalOutDay = !dayRecordAccountObjectList.isEmpty() ? recordAccountMapper.queryTotalMoney("0", "day", sdf.format(date), userId) : 0;
        dayRecordAccount.setDayExpense(totalOutDay);
        dayRecordAccount.setDayRecordAccountObjects(dayRecordAccountObjectList);
        return dayRecordAccount;
    }
}
