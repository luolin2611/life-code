package cn.lifecode.user.service.user;

import cn.lifecode.frameworkcore.bean.Request;
import cn.lifecode.frameworkcore.bean.ResStatusEnum;
import cn.lifecode.frameworkcore.bean.Response;
import cn.lifecode.user.common.dto.user.LoginReq;
import cn.lifecode.user.common.dto.user.LoginRes;
import cn.lifecode.user.entity.User;
import cn.lifecode.user.mapper.user.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author luolin
 * @date 2021-01-14 18:44:29
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public Response<LoginRes> login(Request<LoginReq> request) {
        //1.查询账户是否存在
        if(userMapper.queryUserByAccount(request.getBody().getAccount()) == null) {
            return Response.error(ResStatusEnum.USER_ACCOUNT_NO_EXIST);
        }
        // 2.查询账户和密码是否匹配
        User user = userMapper.login(request.getBody());
        if(user != null) {
            LoginRes loginRes = new LoginRes();
            loginRes.setUserId(user.getUserId());
            loginRes.setUserName(user.getUserName());
            loginRes.setRealName(user.getRealName());
            loginRes.setPersonalResume(user.getPersonalResume());
            return Response.success(loginRes, "登录成功");
        }
        return  Response.error(ResStatusEnum.USER_INFO_ERROR);
    }

    @Override
    public Response registerUser(Request<User> request) {
        User user = request.getBody();
        // 1.查询用户是否已经注册
        if(userMapper.queryUserByUsername(user.getUserName()) != null) {
            // 查询用户名是否存在
            return Response.error(ResStatusEnum.USER_REGISTER_USER_EXIST);
        }
        if(userMapper.queryUserByEmail(user.getEmail()) != null) {
            // 查询邮箱是否存在
            return Response.error(ResStatusEnum.USER_REGISTER_EMAIL_EXIST);
        }
        if(userMapper.queryUserByMobile(user.getMobile()) != null) {
            // 查询手机号是否存在
            return Response.error(ResStatusEnum.USER_REGISTER_MOBILE_EXIST);
        }

        // 2.开始注册用户
        user.setState("0");
        user.setUpdateTime(new Date());
        userMapper.registerUser(user);
        return Response.success("用户注册成功");
    }
}
