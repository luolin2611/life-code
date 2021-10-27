package cn.lifecode.frameworkcore.util;

import org.apache.poi.ss.formula.functions.T;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * @author rollin
 * @date 2021年10月27日07:23:28
 */
public class Utils {
    /**
     * 格式化两位小数的数字
     */
    private static DecimalFormat decimalFormat = new DecimalFormat("0.00");

    /**
     * 返回两位小数
     *
     * @param value 需要格式化的值
     * @return 格式化后的值
     */
    public static Double getTwoDecimalPlaces(Double value) {
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        return Double.parseDouble(decimalFormat.format(value));
    }

}
