package cn.lifecode.recordaccount.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author luolin
 * @date 2021-01-16 16:13:58
 */
@Data
public class Classify implements Serializable {
    /**
     * 分类id
     */
    private Integer classifyId;
    /**
     * 分类名称
     */
    private String classifyName;
    /**
     * 支付类型
     * 0-支出，1-收入
     */
    private String payType;
    /**
     * 分类类型
     * 0-预设，1-用户新增
     */
    private String type;
    /**
     * 图标ID
     */
    private Integer iconId;
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 排序
     * 存放当前的顺序
     */
    private Integer sort;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updatTime;
}
