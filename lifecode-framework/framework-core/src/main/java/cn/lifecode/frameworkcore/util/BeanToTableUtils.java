package cn.lifecode.frameworkcore.util;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Java Bean 转为数据库表工具
 *
 * @author luolin
 * @date 2021-01-16 16:41:54
 */
public class BeanToTableUtils {
    private static final String DATE_TYPE = "java.util.Date";
    private static final String STRING_TYPE = "java.lang.String";
    private static final String INTEGER_TYPE = "java.lang.Integer";

    private BeanToTableUtils() {
    }

    public static String generateSql(Class<Object> obj) {
        // 1.获取类名
        StringBuilder table = new StringBuilder();
        String[] list = obj.getName().split("\\.");
        table.append(list[0]).append("_").append(list[2].substring(4)).append("_").append(list[3].toLowerCase());
        //表名为实体类的下划线小写
        String tableName = humpToUnderline(list[list.length - 1]);
        Field[] fields = obj.getDeclaredFields();
        //字段类型
        String param = null;
        //字段名
        String cameCaseColumn = null;
        // 2. 封装sql
        StringBuilder sql = new StringBuilder();
        sql.append("create table ").append("`").append(tableName).append("`").append(" ( \r\n");
        sql.append("\t`id` int NOT NULL AUTO_INCREMENT COMMENT 'ID'");
        sql.append(",\n");
        for (Field f : fields) {
            cameCaseColumn = f.getName();
            param = f.getType().getTypeName();
            //只有String类型和date类型的属性才创建表字段
            if (param.equals(DATE_TYPE)) {
                sql.append("\t`").append(humpToUnderline(cameCaseColumn)).append("`").append(" ");
                sql.append("timestamp DEFAULT NULL COMMENT '描述内容'");
                sql.append(",\n");
            }
            if (param.equals(STRING_TYPE)) {
                sql.append("\t`").append(humpToUnderline(cameCaseColumn)).append("`").append(" ");
                sql.append("varchar(255) DEFAULT NULL COMMENT '描述内容'");
                sql.append(",\n");
            }
            if (param.equals(INTEGER_TYPE)) {
                sql.append("\t`").append(humpToUnderline(cameCaseColumn)).append("`").append(" ");
                sql.append("int DEFAULT NULL COMMENT '描述内容'");
                sql.append(",\n");
            }
        }
        //设置主键
        sql.append("\tPRIMARY KEY (`id`),\n");
        sql.delete(sql.lastIndexOf(","), sql.length()).append("\n) ENGINE=InnoDB DEFAULT CHARSET=utf8;\r\n");
        return sql.toString();
    }


    /**
     * 驼峰转下划线
     *
     * @param original 驼峰字符串（eg：recordAccount）
     * @return
     */
    public static String humpToUnderline(String original) {
        String regexStr = "[A-Z]";
        Matcher matcher = Pattern.compile(regexStr).matcher(original);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            //得到上一个匹配的字符串
            String g = matcher.group();
            //进行拼接： _record
            matcher.appendReplacement(sb, "_" + g.toLowerCase());
        }
        matcher.appendTail(sb);
        if (sb.charAt(0) == '_') {
            sb.delete(0, 1);
        }
        return sb.toString();
    }
}