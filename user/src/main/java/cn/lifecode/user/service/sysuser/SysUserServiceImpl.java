package cn.lifecode.user.service.sysuser;

import cn.lifecode.user.entity.SysUser;
import cn.lifecode.user.mapper.sysuser.SysUserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 系统用户实现
 *
 * @author luolin
 * @date 2021-01-12 06:55:57
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysUserServiceImpl implements SysUserService {
    private final SysUserMapper userMapper;

    SysUserServiceImpl(SysUserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public void addSysUser(SysUser sysUser) {
        sysUser.setUpdateTime(new Date());
        userMapper.addSysUser(sysUser);
    }
}
