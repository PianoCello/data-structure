package com.pianocello.leetcode;

/**
 * 给定一个整数，写一个函数来判断它是否是 3 的幂次方。
 *
 * 示例 1:
 * 输入: 27
 * 输出: true
 *
 * 示例 2:
 * 输入: 0
 * 输出: false
 *
 * @author PianoCello
 * @date 2020-09-09
 */
public class _0326_PowerOfThree {

    /**
     * 解法一：数学方法
     */
    public boolean isPowerOfThree(int n) {
        if (n <= 0) {
            return false;
        }
        while (n % 3 == 0) {
            n = n / 3;
        }
        return n == 1;
    }

    /**
     * 解法二：3 的幂次的质因子只有 3
     *
     */
    public boolean isPowerOfThree2(int n) {
        return (n > 0) && (1162261467 % n == 0);
    }

}
