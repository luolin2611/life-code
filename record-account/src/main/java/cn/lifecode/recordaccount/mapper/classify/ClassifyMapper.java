package cn.lifecode.recordaccount.mapper.classify;

import cn.lifecode.recordaccount.entity.Classify;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author luolin
 * @date 2021-01-19 14:53:10
 */
@Mapper
public interface ClassifyMapper {
    /**
     * 添加分类
     *
     * @param classify
     */
    void addClassify(@Param("classify") Classify classify);

    /**
     * 根据classifyName查询分类对象
     *
     * @param classifyName
     * @return
     */
    Classify selectClassifyByClassifyName(@Param("classifyName") String classifyName);

    /**
     * 根据userId 和 type查询排序 序号
     *
     * @param userId
     * @param type
     * @return
     */
    int querySortByUserIdAndType(@Param("userId") Integer userId, @Param("type") String type);
}
