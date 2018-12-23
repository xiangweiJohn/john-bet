package com.weijohn.johnbet.util.math;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * Created by vincent on 29/10/2018.
 */
@Slf4j
public class MathUtil {

    /**
     * @Description 获取指定保留位数的double值，并以String类型返回
     * @MethodName
     * @Params
     * @Return
     * @Author weiJohn
     * @Date 22:27 2018-12-17
     * @Version V0.02
     */
    public static String getSpecificFormatDouble(double value, int maximumFractionDigits) {
        final DecimalFormat formater = new DecimalFormat();
        formater.setMaximumFractionDigits(maximumFractionDigits);
        formater.setGroupingSize(0);
        formater.setRoundingMode(RoundingMode.FLOOR);
        System.out.println(formater.format(value));
        return formater.format(value);
    }

    /**
     * string to double 不损失精度
     *
     * @param str
     * @return
     */
    public static double getStrToDoubleWithOutLosePrecision(String str) {
        if (str == null || "null".equals(str)) {
            str = "0.0";
        }
        BigDecimal bigDecimalStr = new BigDecimal(str);
        return bigDecimalStr.doubleValue();
    }

    /**
     * 两个double值相加,不损失精度
     *
     * @param v1
     * @param v2
     * @return
     */
    public static Double addDouble(Double v1, Double v2) {
        if (v1 == null) {
            v1 = 0.0;
        }
        if (v2 == null) {
            v2 = 0.0;
        }

        BigDecimal b1 = new BigDecimal(v1.toString());

        BigDecimal b2 = new BigDecimal(v2.toString());

        return b1.add(b2).doubleValue();

    }

    /**
     * 两个double值相减,不损失精度
     *
     * @param v1
     * @param v2
     * @return
     */
    public static Double subtractDouble(Double v1, Double v2) {
        if (v1 == null) {
            v1 = 0.0;
        }
        if (v2 == null) {
            v2 = 0.0;
        }

        BigDecimal b1 = new BigDecimal(v1.toString());

        BigDecimal b2 = new BigDecimal(v2.toString());

        return b1.subtract(b2).doubleValue();

    }


    /**
     * 三个double值相减,不损失精度
     *
     * @param v1
     * @param v2
     * @param v3
     * @return v1 - v2 - v3
     */
    public static Double subtractDouble(Double v1, Double v2, Double v3) {
        if (v1 == null) {
            v1 = 0.0;
        }
        if (v2 == null) {
            v2 = 0.0;
        }
        if (v3 == null) {
            v3 = 0.0;
        }


        BigDecimal b1 = new BigDecimal(v1.toString());

        BigDecimal b2 = new BigDecimal(v2.toString());

        BigDecimal b3 = new BigDecimal(v3.toString());

        double subtractFirst = b1.subtract(b2).doubleValue();

        BigDecimal b4 = new BigDecimal(subtractFirst + "");


        return b4.subtract(b3).doubleValue();

    }

    /**
     * 两个double值相乘,不损失精度
     *
     * @param v1
     * @param v2
     * @return
     */
    public static Double multiplyDouble(Double v1, Double v2) {

        if (v1 == null) {
            v1 = 0.0;
        }
        if (v2 == null) {
            v2 = 0.0;
        }

        BigDecimal b1 = new BigDecimal(v1.toString());

        BigDecimal b2 = new BigDecimal(v2.toString());

        return b1.multiply(b2).doubleValue();

    }


    /**
     * 两个double值相除,不损失精度
     *
     * @param v1
     * @param v2
     * @return
     */
    public static Double divideDouble(Double v1, Double v2) {
        if (v1 == null) {
            v1 = 0.0;
        }
        if (v2 == null || v2 == 0.0) {
            return 0.0;
        }

        BigDecimal b1 = new BigDecimal(v1.toString());

        BigDecimal b2 = new BigDecimal(v2.toString());

        return b1.divide(b2, 16, BigDecimal.ROUND_HALF_UP).doubleValue();

    }


    /**
     * 三个double值相除,不损失精度
     *
     * @param v1
     * @param v2
     * @param v3
     * @return v1 / v2 /v3
     */
    public static Double divideDouble(Double v1, Double v2, Double v3) {
        if (v1 == null) {
            v1 = 0.0;
        }
        if (v2 == null || v2 == 0.0 || v3 == null || v3 == 0.0) {
            return 0.0;
        }


        BigDecimal b1 = new BigDecimal(v1.toString());

        BigDecimal b2 = new BigDecimal(v2.toString());

        Double b3 = b1.divide(b2).doubleValue();

        BigDecimal b4 = new BigDecimal(b3.toString());
        BigDecimal b5 = new BigDecimal(v3.toString());

        return b4.divide(b5, 16, BigDecimal.ROUND_HALF_UP).doubleValue();

    }


    /**
     * @Description 判断字符串是否是正整数
     * @MethodName isPureDigital
     * @Params String
     * @Return boolean
     * @Author weiJohn
     * @Date 19:57 2018/11/29
     */
    public static boolean isPureDigital(String str) {
        if (isBlank(str))
            return false;
        //String regEx1 = "\\d+";
        String regEx1 = "^[0-9]*[1-9][0-9]*$";
        Pattern p;
        Matcher m;
        p = Pattern.compile(regEx1);
        m = p.matcher(str);
        return m.matches();
    }


    public static void main(String... args) {
        boolean pureDigital = isPureDigital("5.0");
        log.info(pureDigital + "");

    }

}
