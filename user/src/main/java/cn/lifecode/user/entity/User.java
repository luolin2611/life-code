package cn.lifecode.user.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author luolin
 * @date 2021-01-14 18:24:02
 */
@Data
public class User implements Serializable {
    private Integer userId;
    private String userName;
    private String password;
    private String personalResume;
    private String state;
    private String realName;
    private String email;
    private String mobile;
    private Date updateTime;
}
