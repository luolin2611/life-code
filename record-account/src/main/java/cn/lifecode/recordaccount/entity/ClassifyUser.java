package cn.lifecode.recordaccount.entity;

import lombok.Data;

/**
 * 分类User
 * @author rollin
 * @date 2021-03-17 17:07:40
 */
@Data
public class ClassifyUser {
    /**
     * Classify User Id
     */
    private String classifyUserId;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 收入分类IDS
     */
    private String incomeClassifyIds;

    /**
     * 支出分类IDS
     */
    private String expenseClassifyIds;

    /**
     * 更新时间
     */
    private String updateTime;
}
