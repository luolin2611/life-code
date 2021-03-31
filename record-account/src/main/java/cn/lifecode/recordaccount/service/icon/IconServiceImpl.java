package cn.lifecode.recordaccount.service.icon;


import cn.lifecode.frameworkcore.bean.Request;
import cn.lifecode.frameworkcore.bean.Response;
import cn.lifecode.frameworkcore.dto.ResponseObject;
import cn.lifecode.recordaccount.dto.icon.QueryAllIconRequest;
import cn.lifecode.recordaccount.dto.icon.QueryAllIconResponse;
import cn.lifecode.recordaccount.entity.Icon;
import cn.lifecode.recordaccount.mapper.icon.IconMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * icon 图标
 *
 * @author luolin
 * @date 2021-01-18 13:31:26
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class IconServiceImpl implements IconService {
    private final IconMapper iconMapper;

    public IconServiceImpl(IconMapper iconMapper, IconMapper aa) {
        this.iconMapper = iconMapper;
    }

    /**
     * 添加图标
     *
     * @param icon
     * @return
     */
    @Override
    public Response<ResponseObject> addIcon(Icon icon) {
        icon.setUpdateTime(new Date());
        iconMapper.addIcon(icon);
        return Response.success(new ResponseObject());
    }

    /**
     * 查询默认所有图标
     *
     * @return
     */
    @Override
    public Response<QueryAllIconResponse> queryAllIcon(Request<QueryAllIconRequest> request) {
        QueryAllIconResponse queryAllIconResponse = new QueryAllIconResponse();
        List<Icon> iconList = iconMapper.queryAllIcon();
        queryAllIconResponse.setIconList(iconList);
        return Response.success(queryAllIconResponse);
    }
}
