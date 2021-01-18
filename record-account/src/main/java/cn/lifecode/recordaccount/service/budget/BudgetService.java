package cn.lifecode.recordaccount.service.budget;


import cn.lifecode.frameworkcore.bean.Response;
import cn.lifecode.frameworkcore.dto.ResponseObject;
import cn.lifecode.recordaccount.entity.Budget;

/**
 * 预算service
 * @author luolin
 * @date 2021-01-17 11:21:12
 */
public interface BudgetService {
    Response<ResponseObject> addBudget(Budget budget);
}
