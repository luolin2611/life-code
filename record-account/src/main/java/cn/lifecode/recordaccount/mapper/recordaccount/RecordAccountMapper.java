package cn.lifecode.recordaccount.mapper.recordaccount;

import cn.lifecode.recordaccount.dto.bill.BillExportRequest;
import cn.lifecode.recordaccount.dto.bill.BillExportResponse;
import cn.lifecode.recordaccount.dto.home.AddRecordAcctRequest;
import cn.lifecode.recordaccount.entity.DayRecordAccount;
import cn.lifecode.recordaccount.entity.DayRecordAccountObject;
import cn.lifecode.recordaccount.entity.RecordAccount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author luolin
 * @date 2021-01-18 09:28:54
 */
@Mapper
public interface RecordAccountMapper {
    /**
     * 添加记账
     *
     * @param recordAccount
     */
    void addRecordAccount(@Param("recordAccount") RecordAccount recordAccount);

    /**
     * 查询总额
     *
     * @param classifyType 0-支出，1-收入
     * @param dateType     year-年，month-月，day-日
     * @param dateTime     year-2021，month-202101，day-20210120
     */
    double queryTotalMoney(
            @Param("classifyType") String classifyType,
            @Param("dateType") String dateType,
            @Param("dateTime") String dateTime,
            @Param("userId") String userId
    );

    /**
     * 查询每天的记账list （ DayRecordAccountObject ）
     *
     * @param dateType  month-月(startDate 202101), day-日（startDate 为指定查询的日期），period-时间段（如果查询时间段：startDate，endDate不能为空）
     * @param startDate 开始时间（20210120）
     * @param endDate   结束时间 (20210121)
     * @param startPage 起始索引 = 当前页码 * pageSize (1 * 10)
     * @param pageSize  页码大小
     * @return
     */
    List<DayRecordAccountObject> queryRecordAccountObject(
            @Param("dateType") String dateType,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate,
            @Param("startPage") int startPage,
            @Param("pageSize") int pageSize,
            @Param("userId") String userId
    );

    /**
     * 查询指定日期的记账list （ DayRecordAccount ）
     *
     * @param dateType  month-月(202101), day-日（startDate 为指定查询的日期），period-时间段（如果查询时间段：startDate，endDate不能为空）
     * @param startDate 开始时间（20210120）
     * @param endDate   结束时间 (20210121)
     * @param startPage 起始索引 = 当前页码 * pageSize (1 * 10)
     * @param pageSize  页码大小
     * @return
     */
    List<DayRecordAccount> queryRecordAccounts(
            @Param("dateType") String dateType,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate,
            @Param("startPage") int startPage,
            @Param("pageSize") int pageSize,
            @Param("userId") String userId
    );

    /**
     * 记账
     *
     * @param addRecordAcctRequest
     */
    void addRecordAcct(@Param("addRecordAcctRequest") AddRecordAcctRequest addRecordAcctRequest);

    /**
     * 查询 收入每月list
     *
     * @param billType 账单类型： 0 - 收入，1-支出
     * @param year     年
     * @return list
     */
    List<String> queryYearBrokeLineIncomeOrExpenseList(@Param("billType") String billType, @Param("year") String year);

    /**
     * 导出账单查询记账信息
     *
     * @param billType  账单类型 year/month/range
     * @param year      年值 (yyyy)
     * @param month     月值 (yyyyMM)
     * @param startDate 开始时间 (yyyyMMdd)
     * @param endDate   结束时间 (yyyyMMdd)
     * @param userId    用户ID
     * @return
     */
    List<RecordAccount> billExportQueryRecordAccount(
            @Param("exportType") String exportType,
            @Param("year") String year,
            @Param("month") String month,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate,
            @Param("userId") String userId
    );
}
