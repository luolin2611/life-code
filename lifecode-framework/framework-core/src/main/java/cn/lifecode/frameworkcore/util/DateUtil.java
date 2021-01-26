package cn.lifecode.frameworkcore.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @author yuanmin
 * 日期时间工具类
 */
public class DateUtil {
    private DateUtil() {
        throw new IllegalStateException("DateUtil class");
    }

    public static int OPRATETYPE_ADD = 0;
    public static int OPRATETYPE_SUBTRACT = 1;
    public static int DAYTYPE_DAY = 0;
    public static int DAYTYPE_MONTH = 1;
    public static int DAYTYPE_YEAR = 2;

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

    /**
     * 将当前日期 增加/减去 一定日期
     *
     * @param oprateType 0 express add,1 express subtract
     * @param dayType    0 express day, 1 express month, 2 express year
     * @param days
     * @return
     * @author rollin
     * @time 2018-10-23 15:17:03
     */
    public static Date currentDateAddOrSub(int oprateType, int dayType, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        if (oprateType == OPRATETYPE_ADD) {
            switch (dayType) {
                case 0:
                    calendar.add(Calendar.DAY_OF_MONTH, days);
                    break;
                case 1:
                    calendar.add(Calendar.MONTH, days);
                    break;
                case 2:
                    calendar.add(Calendar.YEAR, days);
                    break;
                default:
                    break;
            }
        } else {
            switch (dayType) {
                case 0:
                    calendar.add(Calendar.DAY_OF_MONTH, -days);
                    break;
                case 1:
                    calendar.add(Calendar.MONTH, -days);
                    break;
                case 2:
                    calendar.add(Calendar.YEAR, -days);
                    break;
                default:
                    break;
            }
        }
        return calendar.getTime();
    }

    /**
     * @param oprateType 0 express add,1 express subtract
     * @param dayType    0 express day, 1 express month, 2 express year
     * @param days       day number
     * @param date       Current date
     * @return
     * @desc The specify date is subtracted or added for a certain period of time
     * @author rollin
     * @time 2018-10-23 15:17:03
     */
    public static Date specifyDateAddOrSub(int oprateType, int dayType, int days, Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (oprateType == OPRATETYPE_ADD) {
            switch (dayType) {
                case 0:
                    calendar.add(Calendar.DAY_OF_MONTH, days);
                    break;
                case 1:
                    calendar.add(Calendar.MONTH, days);
                    break;
                case 2:
                    calendar.add(Calendar.YEAR, days);
                    break;
            }
        } else {
            switch (dayType) {
                case 0:
                    calendar.add(Calendar.DAY_OF_MONTH, -days);
                    break;
                case 1:
                    calendar.add(Calendar.MONTH, -days);
                    break;
                case 2:
                    calendar.add(Calendar.YEAR, -days);
                    break;
            }
        }
        return calendar.getTime();
    }

    /**
     * @param time1 eg 8:20
     * @param time2 eg 13:20
     *              resutle 5h
     * @return String result
     * @throws ParseException
     * @desc Calculate the difference between two times
     */
    public static String time24THCalculation(String time1, String time2) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        long ms = sdf.parse(time2).getTime() - sdf.parse(time1).getTime();
        String res = "0 h, " + ms / (1000 * 60) + "min";
        if (ms > 1000 * 60 * 60)
            res = "" + ms / (1000 * 60 * 60) + "h, " + ms % (1000 * 60 * 60) / (1000 * 60) + "min";
        return res;
    }
}
