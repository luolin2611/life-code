package cn.lifecode.recordaccount.dto.classify;

import lombok.Data;

import java.io.Serializable;

/**
 * 初始化分类请求
 *
 * @author luolin
 * @date 2021-01-19 17:33:51
 */
@Data
public class InitClassifyRequest implements Serializable {
    private Integer userId;
}
