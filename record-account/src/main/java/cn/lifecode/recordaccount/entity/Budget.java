package cn.lifecode.recordaccount.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 预算
 *
 * @author luolin
 * @date 2021-01-16 16:10:32
 */
@Data
public class Budget implements Serializable {
    /**
     * 预算id
     */
    private Integer budgetId;
    /**
     * 所属分类ID
     */
    private Integer classifyId;
    /**
     * 预算金额
     */
    private String budgetMoney;
    /**
     * 预算类型 （0.分类预算 1.月预算）
     */
    private String budgetType;
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 创建时间
     */
    private Date updateTime;
}
