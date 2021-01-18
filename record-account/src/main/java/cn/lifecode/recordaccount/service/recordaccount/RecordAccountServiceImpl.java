package cn.lifecode.recordaccount.service.recordaccount;


import cn.lifecode.frameworkcore.bean.Response;
import cn.lifecode.frameworkcore.dto.ResponseObject;
import cn.lifecode.recordaccount.entity.RecordAccount;
import cn.lifecode.recordaccount.mapper.recordaccount.RecordAccountMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 记账service impl
 *
 * @author luolin
 * @date 2021-01-18 09:13:28
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RecordAccountServiceImpl implements RecordAccountService {
    private final RecordAccountMapper recordAccountMapper;

    public RecordAccountServiceImpl(RecordAccountMapper recordAccountMapper) {
        this.recordAccountMapper = recordAccountMapper;
    }

    /**
     * 插入记账记录
     *
     * @param recordAccount
     */
    @Override
    public Response<ResponseObject> addRecordAccount(RecordAccount recordAccount) {
        Date date = new Date();
        recordAccount.setUpdateTime(date);
        recordAccount.setRecordTime(date);
        recordAccountMapper.addRecordAccount(recordAccount);
        return Response.success(new ResponseObject());
    }
}
