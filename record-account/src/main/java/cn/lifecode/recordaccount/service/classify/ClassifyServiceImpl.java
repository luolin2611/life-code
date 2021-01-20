package cn.lifecode.recordaccount.service.classify;

import cn.lifecode.frameworkcore.bean.Response;
import cn.lifecode.frameworkcore.dto.ResponseObject;
import cn.lifecode.frameworkcore.util.ExcelPoiUtil;
import cn.lifecode.recordaccount.entity.Classify;
import cn.lifecode.recordaccount.mapper.classify.ClassifyMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 分类Service
 *
 * @author luolin
 * @date 2021-01-19 15:45:07
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ClassifyServiceImpl implements ClassifyService {
    private final ClassifyMapper classifyMapper;

    public ClassifyServiceImpl(ClassifyMapper classifyMapper) {
        this.classifyMapper = classifyMapper;
    }

    /**
     * 添加分类
     *
     * @param userId
     * @return
     */
    @Override
    public Response<ResponseObject> addClassify(Integer userId) {
        String excelFilePath = "/Volumes/FILE_WORK/work/Code/SpringBoot/life-code/folder/recordaccount/默认分类数据.xlsx";
        String[] columns = new String[]{"classify_name", "icon_id", "sort"};
        List list = ExcelPoiUtil.getExcelToList(excelFilePath, columns);
        List classifyList;
        Map sheetList = (Map) list.get(0);
        // 1.支出插入数据库
        classifyList = (List) sheetList.get("支出");
        insertClassify(classifyList, "0", userId);
        // 2.收入插入数据库
        classifyList = (List) sheetList.get("收入");
        insertClassify(classifyList, "1", userId);
        return Response.success(new ResponseObject());
    }

    /**
     * @param classifyList
     * @param type         0-支出，1-收入
     * @param userId
     */
    public void insertClassify(List classifyList, String type, Integer userId) {
        Classify classify;
        Map tempMap;
        for (int i = 0; i < classifyList.size(); i++) {
            tempMap = (Map) classifyList.get(i);
            Date date = new Date();
            String iconId = (String) tempMap.get("icon_id");
            String sort = (String) tempMap.get("sort");
            classify = new Classify();
            //0表示预设
            classify.setAddType("0");
            classify.setUserId(userId);
            classify.setIconId((int) Double.parseDouble(iconId));
            classify.setClassifyName((String) tempMap.get("classify_name"));
            classify.setSort((int) Double.parseDouble(sort));
            classify.setCreateTime(date);
            classify.setUpdatTime(date);
            classify.setType(type);
            classifyMapper.addClassify(classify);
        }
    }
}
