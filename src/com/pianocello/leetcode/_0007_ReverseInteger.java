package com.pianocello.leetcode;

/**
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * <p>
 * 示例 1:
 * 输入: 123
 * 输出: 321
 * <p>
 * 示例 2:
 * 输入: -123
 * 输出: -321
 * <p>
 * 示例 3:
 * 输入: 120
 * 输出: 21
 * <p>
 * 注意:
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−2^31,  2^31 − 1]。
 * 请根据这个假设，如果反转后整数溢出那么就返回 0。
 *
 * @author PianoCello
 * @date 2020-08-05
 */
public class _0007_ReverseInteger {

    /**
     * 解法一：先转成字符串再反转
     */
    public static int reverse(int x) {
        // 去掉末尾的0
        while ((x / 10) != 0 && (x % 10) == 0) {
            x /= 10;
        }
        // 反转字符串
        char[] chars = String.valueOf(x).toCharArray();
        int start = x < 0 ? 1 : 0;
        int end = chars.length - 1;
        while (start < end) {
            char temp = chars[start];
            chars[start++] = chars[end];
            chars[end--] = temp;
        }
        // 判断是否溢出
        String s = new String(chars);
        long longVal = Long.parseLong(s);
        if (longVal > Integer.MAX_VALUE || longVal < Integer.MIN_VALUE) {
            return 0;
        }
        return (int) longVal;
    }

    /**
     * 解法二：
     */
    public static int reverse2(int x) {
        // 123
        // 120
        long res = 0;
        for (; ; res = res*10+x%10, x /= 10) {

            res = x % 10;


        }

        return res > Integer.MAX_VALUE || res < Integer.MIN_VALUE ? 0 : (int) res;
    }

    public static void main(String[] args) {

        System.out.println(reverse(120));

    }
}
