package com.pianocello.leetcode;

import java.util.BitSet;

/**
 * 统计所有小于非负整数 n 的质数的数量。
 *
 * 示例:
 * 输入: 10
 * 输出: 4
 * 解释: 小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
 *
 * @author PianoCello
 * @date 2020-09-08
 */
public class _0204_CountPrimes {

    /**
     * 解法一：使用数组, 把非素数设置为 true
     */
    public int countPrimes(int n) {
        boolean[] temp = new boolean[n];
        for (int i = 2; i * i < n; i++) {
            if (!temp[i]) {
                for (int j = i * i; j < n; j += i) {
                    temp[j] = true;
                }
            }
        }
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (!temp[i]) {
                count++;
            }
        }
        return count;
    }

    /**
     * 使用BitSet对象
     */
    public int countPrimesOptimize(int n) {
        if (n <= 2) {
            return 0;
        }
        BitSet bit = new BitSet(n);
        for (int i = 2; i * i < n; i++) {
            if (!bit.get(i)) {
                for (int j = i * i; j < n; j += i) {
                    bit.set(j);
                }
            }
        }
        //排除 n和 1
        return n - 2 - bit.cardinality();
    }

}
