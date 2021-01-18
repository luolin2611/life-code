package cn.lifecode.user.mapper.user;

import cn.lifecode.user.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author luolin
 * @date 2021-01-14 18:41:50
 */
@Mapper
public interface UserMapper {
    void addUser(@Param("user") User user);
}
