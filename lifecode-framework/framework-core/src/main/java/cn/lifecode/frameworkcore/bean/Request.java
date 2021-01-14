package cn.lifecode.frameworkcore.bean;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author luolin
 * @date 2021-01-13 17:43:52
 */
@Data
public class Request<T extends Serializable> implements Serializable {
    /**
     * 校验请求
     */
    @NotNull
    private CheckRequest checkRequest;

    /**
     * 请求数据json
     */
    @Valid
    @NotNull
    private T body;
}
