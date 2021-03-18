package cn.lifecode.recordaccount.dto.icon;

import cn.lifecode.recordaccount.entity.IconUser;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 查询所有图标返回
 *
 * @author luolin
 * @date 2021-01-18 14:46:13
 */
@Data
public class QueryAllIconUserResponse implements Serializable {
    /**
     * 图标列表
     */
    @NotNull
    private List<IconUser> iconList;
}
