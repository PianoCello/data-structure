package com.pianocello.leetcode;

/**
 * 给定一个整数 n，返回 n! 结果尾数中零的数量。
 *
 * 示例 1:
 * 输入: 3
 * 输出: 0
 * 解释: 3! = 6, 尾数中没有零。
 *
 * 示例 2:
 * 输入: 5
 * 输出: 1
 * 解释: 5! = 120, 尾数中有 1 个零.
 *
 * @author PianoCello
 * @date 2020-09-29
 */
public class _0172_FactorialTrailingZeroes {

    public static int trailingZeroes(int n) {
        int res = 0;
        for (int di = n; di / 5 > 0; di = di / 5) {
            res += di / 5;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(trailingZeroes(125));
    }


}
