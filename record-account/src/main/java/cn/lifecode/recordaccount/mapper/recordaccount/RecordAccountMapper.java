package cn.lifecode.recordaccount.mapper.recordaccount;

import cn.lifecode.recordaccount.entity.RecordAccount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author luolin
 * @date 2021-01-18 09:28:54
 */
@Mapper
public interface RecordAccountMapper {
    /**
     * 添加记账
     *
     * @param recordAccount
     */
    void addRecordAccount(@Param("recordAccount") RecordAccount recordAccount);
}
