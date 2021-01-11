package cn.lifecode.backmusic.service.sys_user;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import cn.lifecode.backmusic.comm.UUID;
import cn.lifecode.backmusic.comm.Util;
import cn.lifecode.backmusic.dao.sys_user.SysUserDao;
import cn.lifecode.backmusic.po.SysUser;

@Service
@Transactional
public class SysUserServiceImpl implements SysUserService{
	@Autowired
	private SysUserDao sysUserDao;

	@Override
	public SysUser findSysUser(String user_name, String password) {
		return sysUserDao.findSysUser(user_name, password);
	}

	@Override
	public String addSysUser(SysUser _sysUser, String addUserName) {
		SysUser sysUser = sysUserDao.findSysUserByUsername(_sysUser.getUser_name());
		JSONObject object = new JSONObject();
		if(sysUser != null) {
			//用户已经注册过
			object.put("status","repeat");
			object.put("message", "用户已经添加过了！");
		} else {
			sysUser = _sysUser;
			sysUser.setPassword(Util.getMD5(_sysUser.getPassword()));
			sysUser.setStatus("00");
			sysUser.setUpdate_time(new Date());
			sysUser.setUser_id(UUID.getUUID("Y", "45"));
			sysUser.setAdd_user_name(addUserName);
			System.out.println(sysUser.toString());
			Integer n = sysUserDao.insertSysUser(sysUser);
			if(n.intValue() < 1) {
				object.put("status","fail");
				object.put("message", "添加用户失败，请重试！");
			} else {
				object.put("status","success");
				object.put("message", "添加用户成功！");
			}
			
		}
		return object.toString();
	}

	@Override
	public String alterSysUserPassword(String userId, String oldPassword, String newPassword) {
		JSONObject object = new JSONObject();
		oldPassword = Util.getMD5(oldPassword);
		newPassword = Util.getMD5(newPassword);
		String res = sysUserDao.findSysUserByIdAndPass(userId, oldPassword);
		if(res != null && res.length() > 0) {
			sysUserDao.updateSysUserPass(userId, newPassword);
			object.put("status", "success");
			object.put("message", "修改密码成功！");
		} else {
			object.put("status", "error");
			object.put("message", "旧密码错误！");
		}
		return object.toString();
	}
	
}
