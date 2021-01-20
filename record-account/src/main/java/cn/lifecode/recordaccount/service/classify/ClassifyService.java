package cn.lifecode.recordaccount.service.classify;


import cn.lifecode.frameworkcore.bean.Response;
import cn.lifecode.frameworkcore.dto.ResponseObject;

/**
 * 分类Service
 *
 * @author luolin
 * @date 2021-01-19 15:43:56
 */
public interface ClassifyService {
    Response<ResponseObject> addClassify(Integer userId);
}
