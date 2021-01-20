package cn.lifecode.frameworkcore.bean;

import lombok.Data;

/**
 * 此处为了解决插入数据时返回插入自增的ID
 *
 * @author luolin
 * @date 2021-01-20 10:54:51
 */
@Data
public class InsertObject {
    /**
     * 插入ID
     */
    private int insertId;
    /**
     * 插入sql语句
     */
    private String insertSql;
}
