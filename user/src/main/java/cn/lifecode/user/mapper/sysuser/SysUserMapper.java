package cn.lifecode.user.mapper.sysuser;

import cn.lifecode.user.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author luolin
 * @date 2021-01-13 17:10:53
 */
@Mapper
public interface SysUserMapper {
    void addSysUser(SysUser sysUser);
}
