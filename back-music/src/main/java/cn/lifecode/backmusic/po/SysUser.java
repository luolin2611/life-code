package cn.lifecode.backmusic.po;

import lombok.Data;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class SysUser implements Serializable {
    String user_id;
    String user_name;
    String password;
    Date update_time;
    String status;
    String user_level;
    String real_name;
    String add_user_name;

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "[user_id = " + user_id + ", user_name = " + user_name + ", password = " + password + ", update_time = "
                + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(update_time) + ", status = " + status + ", user_level = " + user_level + ", real_name = " + real_name
                + ", add_user_name = " + add_user_name + "]";
    }
}
