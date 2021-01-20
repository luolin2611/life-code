package cn.lifecode.recordaccount.mapper.icon;

import cn.lifecode.recordaccount.entity.Icon;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author luolin
 * @date 2021-01-18 13:29:07
 */
@Mapper
public interface IconMapper {
    /**
     * 添加图标
     *
     * @param icon
     */
    void addIcon(@Param("icon") Icon icon);

    /**
     * 查询所有图标
     *
     * @return
     */
    List<Icon> queryAllIcon();
}
