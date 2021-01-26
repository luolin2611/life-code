package cn.lifecode.recordaccount.dto.bill;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 账单首页初始化信息
 *
 * @author luolin
 * @date 2021-01-21 23:29:49
 */
@Data
public class QueryBillInfoResponse implements Serializable {
    /**
     * 支出
     */
    @NotNull
    private double outTotal;
    /**
     * 收入
     */
    @NotNull
    private double inTotal;
}
