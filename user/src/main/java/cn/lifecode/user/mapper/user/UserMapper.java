package cn.lifecode.user.mapper.user;

import cn.lifecode.user.common.dto.user.LoginReq;
import cn.lifecode.user.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author luolin
 * @date 2021-01-14 18:41:50
 */
@Mapper
public interface UserMapper {
    User queryUserByAccount(@Param("account") String account);

    User login(@Param("loginReq") LoginReq loginReq);

    User queryUserByUsername(@Param("userName") String userName);

    User queryUserByEmail(@Param("email") String email);

    User queryUserByMobile(@Param("mobile") String mobile);

    void registerUser(@Param("user") User user);
}
