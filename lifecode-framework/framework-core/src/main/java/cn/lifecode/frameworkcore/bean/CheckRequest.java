package cn.lifecode.frameworkcore.bean;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 校验请求
 *
 * @author luolin
 * @date 2021-01-13 18:02:39
 */
@Data
public class CheckRequest implements Serializable {
    private static final long serialVersionUID = 7851506189529190406L;

    /**
     * 时间戳
     */
    @NotNull
    private String timestamps;

    /**
     * 签名
     * sessionId + token + timestamps 使用MD5加密
     */
    @NotNull
    private String sign;
}
