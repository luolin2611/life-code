package cn.lifecode.backmusic.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import cn.lifecode.backmusic.comm.Util;
import cn.lifecode.backmusic.po.SysUser;
import cn.lifecode.backmusic.service.sys_user.SysUserService;

@Controller
public class SysUserController {
	@Autowired
	private SysUserService sysUserService;
	
	@RequestMapping("/sysUserLogin")
	@ResponseBody
	public String sysUserLogin(String username,String password,HttpSession session) {
		SysUser sysUser = sysUserService.findSysUser(username, Util.getMD5(password));
		JSONObject object = new JSONObject();
		object.put("status", "fail");
		object.put("message", "System Busy!");
		if(sysUser != null) {
			session.setAttribute("USER_SESSION", sysUser);
			object.put("status", "success");
			object.put("message", "登录成功！");
		} else {
			object.put("status", "fail");
			object.put("message", "用户名或密码不正确！");
		}
		
		return object.toString();
	}
	
	@RequestMapping("/toMainPage")
	public String toMainPage(HttpSession session, Model model) {
		SysUser su = (SysUser) session.getAttribute("USER_SESSION");
		model.addAttribute("sysUser",su);
		model.addAttribute("greetings_str",Util.getTimeSuggest() + " " + su.getReal_name());
		return "main";
	}
	
	@RequestMapping("/sysUserLogout")
	public String sysUserLogout(HttpSession session) {
	    session.invalidate();// 清除Session
	    return "login"; // 重定向到登录页面的跳转方法
	}
	
	@RequestMapping("/addSysUser")
	@ResponseBody
	public String addSysUser(SysUser sysUser, HttpSession session) {
		SysUser _sys = (SysUser) session.getAttribute("USER_SESSION");
		return sysUserService.addSysUser(sysUser, _sys.getUser_name());
	}
	
	@RequestMapping("/alterSysPassword")
	@ResponseBody
	public String alterSysPassword(HttpSession session,String oldPassword, String newPassword1) {
		SysUser su = (SysUser) session.getAttribute("USER_SESSION");
		return sysUserService.alterSysUserPassword(su.getUser_id(), oldPassword, newPassword1);
	}
}
