package com.pianocello.leetcode;

/**
 * 编写一个程序判断给定的数是否为丑数。
 * 丑数就是只包含质因数 2, 3, 5 的正整数。1 是丑数。
 *
 * 示例 1:
 * 输入: 6
 * 输出: true
 * 解释: 6 = 2 × 3
 *
 * 示例 2:
 * 输入: 8
 * 输出: true
 * 解释: 8 = 2 × 2 × 2
 *
 * 示例 3:
 * 输入: 14
 * 输出: false
 * 解释: 14 不是丑数，因为它包含了另外一个质因数 7。
 *
 * @author PianoCello
 * @date 2020-08-22
 */
public class _0263_UglyNumber {

    public boolean isUgly(int num) {
        if (num <= 0) {
            return false;
        }
        while (num != 1) {
            if (num % 5 == 0) {
                num /= 5;
            } else if (num % 3 == 0) {
                num /= 3;
            } else if ((num & 1) == 0) {
                num >>= 1; // 除以 2 的意思
            } else {
                return false;
            }
        }
        return true;
    }

}
