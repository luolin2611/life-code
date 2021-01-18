package cn.lifecode.user.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统用户
 * @author luolin
 * @date 2021-01-12 07:05:40
 */
@Data
public class SysUser implements Serializable {
    Integer userId;
    String userName;
    String password;
    Date updateTime;
    String status;
    String userLevel;
    String realName;
    String addUserName;
}
