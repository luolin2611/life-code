package cn.lifecode.recordaccount.service.budget;

import cn.lifecode.frameworkcore.bean.Response;
import cn.lifecode.frameworkcore.dto.ResponseObject;
import cn.lifecode.recordaccount.entity.Budget;
import cn.lifecode.recordaccount.mapper.budget.BudgetMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 预算service
 *
 * @author luolin
 * @date 2021-01-17 11:21:44
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BudgetServiceImpl implements BudgetService {
    private final BudgetMapper budgetMapper;

    BudgetServiceImpl(BudgetMapper budgetMapper) {
        this.budgetMapper = budgetMapper;
    }

    @Override
    public Response<ResponseObject> addBudget(Budget budget) {
        budget.setUpdateTime(new Date());
        budgetMapper.addBudget(budget);
        return Response.success(new ResponseObject());
    }

}
