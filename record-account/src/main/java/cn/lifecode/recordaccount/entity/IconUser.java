package cn.lifecode.recordaccount.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户图标
 * @author rollin
 * @date 2021-03-15 16:53:17
 */
@Data
public class IconUser implements Serializable {
    /**
     * id
     */
    private Integer id;
    /**
     * userId
     */
    private Integer userId;
    /**
     * 图标ID
     */
    private Integer iconId;
    /**
     * 图标名称
     */
    private String iconName;
    /**
     * 图标中文名称
     */
    private String iconNameCn;
    /**
     * 更新时间
     */
    private Date updateTime;
}
