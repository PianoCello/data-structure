package com.pianocello.leetcode;

import java.math.BigInteger;

/**
 * 请你来实现一个 atoi 函数，使其能将字符串转换成整数。
 * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。接下来的转化规则如下：
 * <p>
 * 1. 如果第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字字符组合起来，形成一个有符号整数。
 * 2. 假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成一个整数。
 * 3. 该字符串在有效的整数部分之后也可能会存在多余的字符，那么这些字符可以被忽略，它们对函数不应该造成影响。
 * <p>
 * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换，即无法进行有效转换。
 * <p>
 * 在任何情况下，若函数不能进行有效的转换时，请返回 0 。
 *
 * @author PianoCello
 * @date 2020-08-07
 */
public class _0008_StringToInteger {

    /**
     * 解法一：按题目自左向右遍历字符串
     */
    public static int myAtoi(String str) {
        char[] chars = str.toCharArray();
        int len = chars.length;
        int i = 0;
        while (i < len && chars[i] == ' ') {
            // 去掉前导空格
            i++;
        }
        //去掉前导空格以后到了末尾了
        if (i == len) return 0;
        boolean negative = false;
        if (chars[i] == '-') {
            //遇到负号
            negative = true;
            i++;
        } else if (chars[i] == '+') {
            // 遇到正号
            i++;
        } else if (!Character.isDigit(chars[i])) {
            // 其他符号
            return 0;
        }
        int ans = 0;
        while (i < len && Character.isDigit(chars[i])) {
            int digit = chars[i] - '0';
            if (ans > (Integer.MAX_VALUE - digit) / 10) {
                // 本来应该是 ans * 10 + digit > Integer.MAX_VALUE
                // 但是 *10 和 + digit 都有可能越界，所有都移动到右边去就可以了。
                return negative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            ans = ans * 10 + digit;
            i++;
        }
        return negative ? -ans : ans;
    }

    /**
     * 解法二：确定有限状态机 DFA
     */
    public static int myAtoi3(String str) {


    }


    public static void main(String[] args) {

//        String s = "42";
//        String s = "   -42";
//        String s = "45642sds//dcscd";
//        String s = "+45642sdsdcscd";
//        String s = "++++45642sdsdcscd";
//        String s = "-456425661655656sdsdcscd";
//        String s = "-+551";
//        String s = "            ";
        String s = "9223372036854775808 156ui";
        System.out.println(myAtoi(s));
    }
}
