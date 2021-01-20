package cn.lifecode.recordaccount.service.recordaccount;


import cn.lifecode.frameworkcore.bean.Response;
import cn.lifecode.frameworkcore.dto.ResponseObject;
import cn.lifecode.frameworkcore.util.DateUtil;
import cn.lifecode.frameworkcore.util.ExcelPoiUtil;
import cn.lifecode.recordaccount.entity.Classify;
import cn.lifecode.recordaccount.entity.RecordAccount;
import cn.lifecode.recordaccount.mapper.classify.ClassifyMapper;
import cn.lifecode.recordaccount.mapper.recordaccount.RecordAccountMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
    private final ClassifyMapper classifyMapper;

    public RecordAccountServiceImpl(RecordAccountMapper recordAccountMapper, ClassifyMapper classifyMapper) {
        this.recordAccountMapper = recordAccountMapper;
        this.classifyMapper = classifyMapper;
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


    /**
     * 从Excel中导入数据库
     *
     * @return
     */
    @Override
    public Response<ResponseObject> excelConversionToDataBase() {
//        String excelFilePath = "/Volumes/FILE_WORK/work/Code/SpringBoot/life-code/folder/recordaccount/网易有钱记账数据20180119-20190118.xlsx";
//        String excelFilePath = "/Volumes/FILE_WORK/work/Code/SpringBoot/life-code/folder/recordaccount/网易有钱记账数据20190119-20200118.xlsx";
        String excelFilePath = "/Volumes/FILE_WORK/work/Code/SpringBoot/life-code/folder/recordaccount/网易有钱记账数据20200119-20210118.xlsx";
        String[] columns = new String[]{"record_time", "classify_name", "bill_money", "remark"};
        List list = ExcelPoiUtil.getExcelToList(excelFilePath, columns);
        List recordList;
        Map sheetList = (Map) list.get(0);
        // 1.支出插入数据库
        recordList = (List) sheetList.get("支出");
        insertRecordAccount(recordList, "0");
        // 2.收入插入数据库
        recordList = (List) sheetList.get("收入");
        insertRecordAccount(recordList, "1");
        return Response.success(new ResponseObject());
    }

    /**
     * @param recordList
     * @param type       0-支出，1-收入
     */
    public void insertRecordAccount(List recordList, String type) {
        RecordAccount recordAccount;
        Map tempMap;
        for (int i = 0; i < recordList.size(); i++) {
            tempMap = (Map) recordList.get(i);
            String classifyName = (String) tempMap.get("classify_name");
            Classify classify = classifyMapper.selectClassifyByClassifyName(classifyName.trim());
            //1.如果classify为空，则新增分类
            if (classify == null) {
                // 1.1 查询当前分类的排序序号
                int sort = classifyMapper.querySortByUserIdAndType(45, type);
                //1.2 封装插入库的classify对象
                classify = new Classify();
                classify.setType(type);
                classify.setSort(sort+1);
                classify.setUpdatTime(new Date());
                classify.setCreateTime(new Date());
                classify.setClassifyName(classifyName);
                // classify.setIconId(); // 此处先不给添加iconId
                //此处 1 表示用户添加
                classify.setAddType("1");
                classify.setUserId(45);
                classifyMapper.addClassify(classify);
                // 1.3 再次查询最新的分类信息
                classify = classifyMapper.selectClassifyByClassifyName(classifyName.trim());
            }
            //写入记账列表
            Date date = DateUtil.returnDateFromString((String) tempMap.get("record_time"), DateUtil.YMD_TIME_SPLIT_PATTERN);
            recordAccount = new RecordAccount();
            recordAccount.setRecordTime(date);
            recordAccount.setUpdateTime(date);
            recordAccount.setRemark((String) tempMap.get("remark"));
            recordAccount.setBillMoney((String) tempMap.get("bill_money"));
            recordAccount.setClassifyId(classify.getClassifyId());
            recordAccount.setClassifyName(classifyName);
            recordAccount.setClassifyType(type);
            recordAccount.setUserId(45);
            recordAccountMapper.addRecordAccount(recordAccount);
        }
    }
}
