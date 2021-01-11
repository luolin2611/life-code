package cn.lifecode.backmusic.dao.sys_user;//package cn.lifecode.music.dao.sys_user;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.lifecode.backmusic.po.SysUser;

/**
 * @author luolin
 * @date 2021-01-08 19:19:56
 */
@Mapper
public interface SysUserDao {
    SysUser findSysUser(@Param("user_name") String userName, @Param("password") String password);// 登录查询用户

    SysUser findSysUserByUsername(@Param("user_name") String userName);//根据用户名查询用户

    Integer insertSysUser(SysUser sysUser);

    List<SysUser> findAllSysUser();

    String findSysUserByIdAndPass(@Param("user_id") String userId, @Param("password") String password);

    void updateSysUserPass(@Param("user_id") String userId, @Param("password") String password);
}
