package cn.lifecode.recordaccount.dto.home;

import cn.lifecode.recordaccount.entity.DayRecordAccount;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 首页返回
 *
 * @author luolin
 * @date 2021-01-20 23:46:40
 */
@Data
public class HomeInitInfoResponse implements Serializable {
    /**
     * 本月总支出
     */
    private double monthOutTotal;
    /**
     * 本月总收入
     */
    private double monthInTotal;
    /**
     * 此处的需求是：最近三日记账对象
     */
    private List<DayRecordAccount> threedayRecordAccount;
}
