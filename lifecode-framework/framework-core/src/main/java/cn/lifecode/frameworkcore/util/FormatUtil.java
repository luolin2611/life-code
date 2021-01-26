package cn.lifecode.frameworkcore.util;

import java.text.DecimalFormat;

/**
 * 格式化输入工具类
 * @author zhangyu
 * @description
 */
public final class FormatUtil {
    private FormatUtil(){
        throw new IllegalStateException("FormatUtil class");
    }
    private static DecimalFormat df = new DecimalFormat("00000000");

    /**
     * 格式化json
     *
     * @param jsonStr
     * @return
     * @author cjz
     */
    public static String formatJson(String jsonStr) {
        if (null == jsonStr || "".equals(jsonStr)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        char last = '\0';
        char current = '\0';
        int indent = 0;
        boolean isInQuotationMarks = false;
        for (int i = 0; i < jsonStr.length(); i++) {
            last = current;
            current = jsonStr.charAt(i);
            switch (current) {
                case '"':
                    if (last != '\\') {
                        isInQuotationMarks = !isInQuotationMarks;
                    }
                    sb.append(current);
                    break;
                case '{':
                case '[':
                    sb.append(current);
                    if (!isInQuotationMarks) {
                        sb.append('\n');
                        indent++;
                        addIndentBlank(sb, indent);
                    }
                    break;
                case '}':
                case ']':
                    if (!isInQuotationMarks) {
                        sb.append('\n');
                        indent--;
                        addIndentBlank(sb, indent);
                    }
                    sb.append(current);
                    break;
                case ',':
                    sb.append(current);
                    if (last != '\\' && !isInQuotationMarks) {
                        sb.append('\n');
                        addIndentBlank(sb, indent);
                    }
                    break;
                default:
                    sb.append(current);
            }
        }

        return sb.toString();
    }

    /**
     * 添加space
     *
     * @param sb
     * @param indent
     * @author cjz
     */
    private static void addIndentBlank(StringBuilder sb, int indent) {
        for (int i = 0; i < indent; i++) {
            sb.append('\t');
        }
    }

    /**
     * 格式化报文长度
     *
     * @param pattern
     * @param obj
     * @return
     */
    public static String formatBodyLength(String pattern, Object obj) {
        if (pattern != null && pattern.trim().length() > 0) {
            df.applyPattern(pattern);
        }
        return df.format(obj);
    }
}

