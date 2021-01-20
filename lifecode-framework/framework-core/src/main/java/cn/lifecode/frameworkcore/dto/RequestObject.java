package cn.lifecode.frameworkcore.dto;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 当请求没有参数的时候使用该空对象
 *
 * @author luolin
 * @date 2021-01-19 23:47:33
 */
@Data
public class RequestObject implements Serializable {
    @NotBlank
    private String requestStatus = "REQUEST_OBJECT";
}
