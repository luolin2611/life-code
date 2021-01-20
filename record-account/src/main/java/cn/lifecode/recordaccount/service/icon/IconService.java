package cn.lifecode.recordaccount.service.icon;

import cn.lifecode.frameworkcore.bean.Response;
import cn.lifecode.frameworkcore.dto.ResponseObject;
import cn.lifecode.recordaccount.dto.icon.QueryAllIconResponse;
import cn.lifecode.recordaccount.entity.Icon;

import java.util.List;

/**
 * 图标service
 *
 * @author luolin
 * @date 2021-01-18 13:30:35
 */
public interface IconService {
    Response<ResponseObject> addIcon(Icon icon);

    Response<QueryAllIconResponse> queryAllIcon();
}
