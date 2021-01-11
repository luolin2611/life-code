//package cn.lifecode.music.dao.sys_user;
//
//import java.util.List;
//
//import org.apache.ibatis.annotations.Param;
//
//import com.music.core.po.SysUser;
//
//public interface SysUserDao {
//	public SysUser findSysUser(@Param("user_name") String user_name, @Param("password") String password);// 登录查询用户
//	public SysUser findSysUserByUsername(@Param("user_name") String user_name);//根据用户名查询用户
//	public Integer insertSysUser(SysUser sysUser);
//	public List<SysUser> findAllSysUser();
//	public String findSysUserByIdAndPass(@Param("user_id") String user_id, @Param("password") String password);
//	public void updateSysUserPass(@Param("user_id") String user_id, @Param("password") String password);
//}
