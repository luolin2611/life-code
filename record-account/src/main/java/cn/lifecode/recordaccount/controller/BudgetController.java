package cn.lifecode.recordaccount.controller;

import cn.lifecode.frameworkcore.bean.Request;
import cn.lifecode.frameworkcore.bean.Response;
import cn.lifecode.frameworkcore.dto.ResponseObject;
import cn.lifecode.recordaccount.entity.Budget;
import cn.lifecode.recordaccount.service.budget.BudgetService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 预算
 * 预算的相关操作
 *
 * @author luolin
 * @date 2021-01-17 11:29:41
 */
@RestController
@RequestMapping("/budget")
public class BudgetController {
    private final BudgetService budgetService;

    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    /**
     * 添加预算
     *
     * @param request
     * @return
     */
    @PostMapping("/addBudget")
    public Response<ResponseObject> addBudget(@RequestBody Request<Budget> request) {
        return budgetService.addBudget(request.getBody());
    }
}
