package cn.lifecode.frameworkcore.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 当返回没有参数的时候使用该空对象
 *
 * @author luolin
 * @date 2021-01-18 09:48:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseObject implements Serializable {
    @NotBlank
    private String responseStatus = "REQUEST_SUCCESS";
}
