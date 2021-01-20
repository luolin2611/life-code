package cn.lifecode.frameworkcore.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author yuanmin
 * 日期时间工具类
 */
public class DateUtil {
    private DateUtil() {
        throw new IllegalStateException("DateUtil class");
    }

    /**
     * 年月日 20200101
     */
    public static final String YMD_TIME_PATTERN = "yyyyMMdd";
    /**
     * 年月日 2020-01-01
     */
    public static final String YMD_TIME_SPLIT_PATTERN = "yyyy-MM-dd";
    /**
     * 年月日时分秒 20200101122445
     */
    public static final String FULL_TIME_PATTERN = "yyyyMMddHHmmss";
    /**
     * 年月日时分秒 2020-01-01 12:24:45
     */
    public static final String FULL_TIME_SPLIT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static String formatTime(LocalDateTime localDateTime, String pattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return localDateTime.format(dateTimeFormatter);
    }

    public static Date returnDateFromString(String dateStr, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
