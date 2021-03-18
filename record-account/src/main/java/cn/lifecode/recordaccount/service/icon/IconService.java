package cn.lifecode.recordaccount.service.icon;

import cn.lifecode.frameworkcore.bean.Request;
import cn.lifecode.frameworkcore.bean.Response;
import cn.lifecode.frameworkcore.dto.ResponseObject;
import cn.lifecode.recordaccount.dto.icon.QueryAllIconRequest;
import cn.lifecode.recordaccount.dto.icon.QueryAllIconResponse;
import cn.lifecode.recordaccount.dto.icon.QueryAllIconUserRequest;
import cn.lifecode.recordaccount.dto.icon.QueryAllIconUserResponse;
import cn.lifecode.recordaccount.entity.Icon;

/**
 * 图标service
 *
 * @author luolin
 * @date 2021-01-18 13:30:35
 */
public interface IconService {
    Response<ResponseObject> addIcon(Icon icon);

    Response<QueryAllIconResponse> queryAllIcon(Request<QueryAllIconRequest> request);

    Response<QueryAllIconUserResponse> queryAllIconUser(Request<QueryAllIconUserRequest> request);
}
