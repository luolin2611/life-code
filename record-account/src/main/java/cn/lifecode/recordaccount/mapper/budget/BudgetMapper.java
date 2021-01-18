package cn.lifecode.recordaccount.mapper.budget;

import cn.lifecode.recordaccount.entity.Budget;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author luolin
 * @date 2021-01-17 11:05:28
 */
@Mapper
public interface BudgetMapper {
    /**
     * 添加预算
     *
     * @param budget
     */
    void addBudget(@Param("budget") Budget budget);
}
