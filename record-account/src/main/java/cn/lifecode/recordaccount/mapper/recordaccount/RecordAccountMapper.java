package cn.lifecode.recordaccount.mapper.recordaccount;

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
    double queryTotalMoney(@Param("classifyType") String classifyType, @Param("dateType") String dateType, @Param("dateTime") String dateTime);

    /**
     * 查询 DayRecordAccountObject
     *
     * @param dateType  day-日（startDate 为指定查询的日期），period-时间段（如果查询时间段：startDate，endDate不能为空）
     * @param startDate 开始时间（20210120）
     * @param endDate   结束时间 (20210121)
     * @return
     */
    List<DayRecordAccountObject> queryRecordAccountObject(@Param("dateType") String dateType, @Param("startDate") String startDate, @Param("endDate") String endDate);
}
