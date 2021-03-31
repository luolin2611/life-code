package cn.lifecode.recordaccount.dto.bill;

import cn.lifecode.recordaccount.entity.QueryMonthIncomeExpenseObject;
import lombok.Data;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 查询 月支出/月收入 列表
 *
 * @author rollin
 * @date 2021-03-29 17:40:38
 */
@Data
public class QueryMonthIncomeExpenseListResponse implements Serializable {
    /**
     * 返回带有请求月的账单信息
     */
    @NotNull
    private List<QueryMonthIncomeExpenseObject> list;
}


