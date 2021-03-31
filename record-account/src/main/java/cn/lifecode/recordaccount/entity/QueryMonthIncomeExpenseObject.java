package cn.lifecode.recordaccount.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 返回整月的单个对象
 *
 * @author rollin
 * @date 2021-03-29 21:49:40
 */
@Data
public class QueryMonthIncomeExpenseObject implements Serializable {
    /**
     * 时间 -- 横轴
     */
    private String time;
    /**
     * 钱的总支 -- 当前时间下的值
     */
    private double money;
}