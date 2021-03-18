package cn.lifecode.recordaccount.dto.icon;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 查询所有图标返回
 *
 * @author luolin
 * @date 2021-03-15 16:26:13
 */
@Data
public class QueryAllIconUserRequest implements Serializable {
    /**
     * 用户ID
     */
    @NotNull
    private String userId;
}
