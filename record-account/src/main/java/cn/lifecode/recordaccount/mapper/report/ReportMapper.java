package cn.lifecode.recordaccount.mapper.report;

import cn.lifecode.recordaccount.entity.DayRecordAccountObject;
import cn.lifecode.recordaccount.entity.QueryReportInfoObject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author rollin
 * @date 2021-03-30 14:18:50
 */
@Mapper
public interface ReportMapper {
    /**
     * 添加分类
     *
     * @param dateType  month-月(startDate 202101),
     *                  day-日（startDate 为指定查询的日期），
     *                  period-时间段（如果查询时间段：startDate，endDate不能为空）
     * @param startDate 开始时间（20210120）
     * @param endDate   结束时间 (20210121)
     * @param type      消费类型 （0-支出，1-收入）
     * @return
     */
    List<QueryReportInfoObject> queryReportInfo(
            @Param("dateType") String dateType,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate,
            @Param("userId") String userId,
            @Param("type") String type
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
            @Param("userId") String userId,
            @Param("classifyId") String classifyId,
            @Param("orderByType") String orderByType
    );
}
