package com.weijohn.johnbet.util.arrangementAndcombination;

import com.weijohn.johnbet.util.math.MathUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @Description 排列组合
 * @ClassName ArrangeAndCombineUtil
 * @Author weiJohn
 * @Date 09:34 2018-12-23
 * @Version 0.01
 */
@Slf4j
public class ArrangeAndCombineUtil {
    private static List<List<String>> listResultEnd = new ArrayList<>();

    /*****************************计算排列或组合的数量*******************************/

    /**
     * @Description 计算阶乘数，即n! = n * (n-1) * ... * 2 * 1
     * @MethodName
     * @Params
     * @Return
     * @Author weiJohn
     * @Date 09:35 2018-12-23
     * @Version V0.01
     */
    private static long factorial(int n) {
        return (n > 1) ? n * factorial(n - 1) : 1;
    }

    /**
     * @Description 计算排列数，即A(n, m) = n!/(n-m)!
     * @MethodName
     * @Params
     * @Return
     * @Author weiJohn
     * @Date 09:35 2018-12-23
     * @Version V0.01
     */
    public static long arrangement(int n, int m) {
        return (n >= m) ? factorial(n) / factorial(n - m) : 0;
    }

    /**
     * @Description 计算组合数，即C(n, m) = n!/((n-m)! * m!)
     * @MethodName
     * @Params
     * @Return
     * @Author weiJohn
     * @Date 09:35 2018-12-23
     * @Version V0.01
     */
    public static long combination(int n, int m) {
        return (n >= m) ? factorial(n) / factorial(n - m) / factorial(m) : 0;
    }
    /*****************************计算排列或组合的数量*******************************/


    /*****有时候，我们不仅需要知道排列或组合的数量，而且需要知道有哪些排列或组合，并列举出所有的排列或组合，
     * 人工列举工作量大而且容易出错，那么，如何利用计算机帮忙列举出所有的这些排列或组合呢***********/

    /**（1）排列
     **采用递归即可枚举出所有的排列情况，相关Java实现如下**************************************/

    /**
     * @Description 排列选择（从列表中选择n个排列）
     * @MethodName
     * @Params dataList 待选列表 n 选择个数
     * @Return
     * @Author weiJohn
     * @Date 09:38 2018-12-23
     * @Version V0.01
     */
    public static void arrangementSelect(String[] dataList, int n) {
        log.info(String.format("A(%d, %d) = %d", dataList.length, n,
                arrangement(dataList.length, n)));
        arrangementSelect(dataList, new String[n], 0);
    }

    /**
     * @param dataList    待选列表
     * @param resultList  前面（resultIndex-1）个的排列结果
     * @param resultIndex 选择索引，从0开始
     * @Description 排列选择
     * @Author weiJohn
     * @Date 09:38 2018-12-23
     * @Version V0.01
     */
    private static void arrangementSelect(String[] dataList, String[] resultList, int resultIndex) {
        int resultLen = resultList.length;
        if (resultIndex >= resultLen) { // 全部选择完时，输出排列结果
            log.info(Arrays.asList(resultList) + "");
            return;
        }

        // 递归选择下一个
        for (int i = 0; i < dataList.length; i++) {
            // 判断待选项是否存在于排列结果中
            boolean exists = false;
            for (int j = 0; j < resultIndex; j++) {
                if (dataList[i].equals(resultList[j])) {
                    exists = true;
                    break;
                }
            }
            if (!exists) { // 排列结果不存在该项，才可选择
                resultList[resultIndex] = dataList[i];
                arrangementSelect(dataList, resultList, resultIndex + 1);
            }
        }
    }

    /**（2）组合
     **采用递归即可枚举出所有的排列情况，相关Java实现如下**************************************/

    /**
     * 组合选择（从列表中选择n个组合）
     *
     * @param dataList 待选列表
     * @param n        选择个数
     */
    public List<String> combinationSelect(String[] dataList, int n) {
        long combinationResultNum = combination(dataList.length, n);
        log.info(String.format("C(%d, %d) = %d", dataList.length, n, combinationResultNum));
        List<String> oddsMultiplyResultList = new ArrayList<>();
        return combinationSelect(dataList, 0, new String[n], 0, oddsMultiplyResultList);
    }

    /**
     * 组合选择
     *
     * @param dataList    待选列表
     * @param dataIndex   待选开始索引
     * @param resultList  前面（resultIndex-1）个的组合结果
     * @param resultIndex 选择索引，从0开始
     */
    private List<String> combinationSelect(String[] dataList, int dataIndex, String[] resultList,
                                           int resultIndex, List<String> oddsMultiplyResultList) {
        int resultLen = resultList.length;
        int resultCount = resultIndex + 1;

        double listResultD = 1.0;
        if (resultCount > resultLen) { // 全部选择完时，输出组合结果
            List<String> listResult = Arrays.asList(resultList);
            log.info(listResult + "");

            StringBuilder display = new StringBuilder();
            int count = listResult.size();
            for (String result : listResult) {
                double resultD = MathUtil.getStrToDoubleWithOutLosePrecision(result);
                listResultD = MathUtil.multiplyDouble(listResultD, resultD);

                if (count > 1) {
                    display.append(result).append(" * ");
                    count--;
                } else {
                    display.append(result);
                    //log.info(display + " = listResultD");
                }
            }
            //log.info("listResultD = " + listResultD);
            oddsMultiplyResultList.add(listResultD + "");
            return oddsMultiplyResultList;
        }

        // 递归选择下一个
        //log.info("递归选择下一个");
        for (int i = dataIndex; i < dataList.length + resultCount - resultLen; i++) {
            resultList[resultIndex] = dataList[i];
            combinationSelect(dataList, i + 1, resultList,
                    resultIndex + 1, oddsMultiplyResultList);
        }
        return oddsMultiplyResultList;
    }

    /**
     * @Description 计算收益
     * @MethodName
     * @Params PerNoteAmount：每注金额(每注金额相同)  result：赔率列表
     * @Return
     * @Author weiJohn
     * @Date 17:09 2018-12-23
     * @Version V0.02
     */
    private static String calculateIncome(List<String> result, String perNoteAmount) {
        final double[] totalIncome = {0.0};
        result.forEach(r -> {
            double resultD = MathUtil.getStrToDoubleWithOutLosePrecision(r);
            double perNoteAmountD = MathUtil.getStrToDoubleWithOutLosePrecision(perNoteAmount);

            //每注收益
            Double perNoteIncome = MathUtil.multiplyDouble(resultD, perNoteAmountD);
            log.info("每注收益:" + perNoteIncome);
            totalIncome[0] = totalIncome[0] + perNoteIncome;
        });


        return totalIncome[0] + "";
    }

    public static void main(String[] args) {
        //赔率数组，热刺输
        String[] strArr = new String[]{"1.60", "1.55", "1.60", "1.89", "1.74",
                "1.77", "2.01", "1"};//, "1.87", "1.94"};

        log.info("组合情况如下：");
        List<String> result = new ArrangeAndCombineUtil().combinationSelect(strArr, 7);

        log.info("组合累成赔率如下：");
        log.info(result + "");

        log.info("计算总计盈利：");
        String resultIncome = calculateIncome(result, "200");
        log.info("热刺输总计盈利: " + resultIncome);

        //组合选择  十串七
        log.info("-----------------------------------------");

        //赔率数组，热刺赢
        String[] strArr1 = new String[]{"1.60", "1.55", "1.60", "1.89", "1.74",
                "1.77", "2.01", "1", "1.87"};

        log.info("组合情况如下：");
        List<String> result1 = new ArrangeAndCombineUtil().combinationSelect(strArr1, 7);

        log.info("组合累成赔率如下：");
        log.info(result1 + "");

        log.info("热次赢总计盈利：");
        String resultIncome1 = calculateIncome(result1, "200");
        log.info("热次赢总计盈利: " + resultIncome1);

    }


}
