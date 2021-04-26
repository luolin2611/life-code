package cn.lifecode.recordaccount.dto.report;

import cn.lifecode.recordaccount.entity.DayRecordAccountObject;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 请求详情列表
 *
 * @author rollin
 * @date 2021-04-25 17:07:49
 */
@Data
public class QueryReportDetailsListResponse implements Serializable {
    /**
     * 时间段账单
     */
    private List<DayRecordAccountObject> dayRecordAccountObjects;
}