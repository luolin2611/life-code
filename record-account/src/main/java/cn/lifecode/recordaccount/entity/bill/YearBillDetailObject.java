package cn.lifecode.recordaccount.entity.bill;

import cn.lifecode.recordaccount.entity.DayRecordAccountObject;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 年账单明细
 * 返回每月的：收入、支出、结余以及当月的单个对象数组
 *
 * @author luolin
 * @date 2021-01-23 11:56:14
 */
@Data
public class YearBillDetailObject implements Serializable {
    /**
     * 时间字符串：eg: 202101
     */
    @NotNull
    private String dateStr;
    /**
     * 收入
     */
    @NotNull
    private double income;
    /**
     * 支出
     */
    @NotNull
    private double expense;
    /**
     * 结余
     */
    @NotNull
    private double surplus;
    /**
     * 每日的单个对象列表
     */
    @NotNull
    private List<DayRecordAccountObject> dayRecordAccountObjectList;
}
