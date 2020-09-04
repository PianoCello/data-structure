package com.pianocello.leetcode;

/**
 * 两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。
 * 给出两个整数 x 和 y，计算它们之间的汉明距离。
 * 注意：
 * 0 ≤ x, y < 2^31.
 *
 * 示例:
 * 输入: x = 1, y = 4
 * 输出: 2
 * 解释:
 * 1   (0 0 0 1)
 * 4   (0 1 0 0)
 *        ↑   ↑
 *
 * 上面的箭头指出了对应二进制位不同的位置。
 *
 * @author PianoCello
 * @date 2020-09-04
 */
public class _0461_HammingDistance {

    /**
     * 解法一：异或 再求 汉明权重
     */
    public int hammingDistance(int x, int y) {
        x = x ^ y;
        int res = 0;
        while (x != 0) {
            ++res;
            x = x & (x - 1);
        }
        return res;
    }

}
