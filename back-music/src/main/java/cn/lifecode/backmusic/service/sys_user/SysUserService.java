package cn.lifecode.backmusic.service.sys_user;

import cn.lifecode.backmusic.po.SysUser;

public interface SysUserService {
	public SysUser findSysUser(String user_name, String password);//登录查询用户
	public String addSysUser(SysUser sysUser, String addUserId);//添加用户
	public String alterSysUserPassword(String userId, String oldPassword, String newPassword); //修改用户密码
}
