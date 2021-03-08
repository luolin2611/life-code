package cn.lifecode.user.service.user;

import cn.lifecode.frameworkcore.bean.Request;
import cn.lifecode.frameworkcore.bean.Response;
import cn.lifecode.user.common.dto.user.LoginReq;
import cn.lifecode.user.common.dto.user.LoginRes;
import cn.lifecode.user.entity.User;

/**
 * @author luolin
 * @date 2021-01-14 18:42:45
 */
public interface UserService {
    Response<LoginRes> login(Request<LoginReq> request);
    Response registerUser(Request<User> request);
}
