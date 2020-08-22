package com.pianocello.leetcode;

/**
 * 编写一个程序，找出第 n 个丑数。
 * 丑数就是质因数只包含 2, 3, 5 的正整数。
 *
 * 示例:
 * 输入: n = 10
 * 输出: 12
 * 解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
 *
 * 说明:
 * 1 是丑数。
 * n 不超过 1690。
 *
 * @author PianoCello
 * @date 2020-08-22
 */
public class _0264_UglyNumberII {

    private static class Ugly {
        int[] res = new int[1690];
        public Ugly() {
            res[0] = 1;
            int i = 0, j = 0, k = 0;
            int min;
            for (int index = 1; index < 1690; ++index) {
                int v1 = res[i] * 2;
                int v2 = res[j] * 3;
                int v3 = res[k] * 5;
                min = Math.min(Math.min(v1, v2), v3);
                if (min == v1) ++i;
                if (min == v2) ++j;
                if (min == v3) ++k;
                res[index] = min;
            }
        }
    }
    private static final Ugly ugly = new Ugly();
    // 只生成一次答案 后续直接拿
    public int nthUglyNumber(int n) {
        return ugly.res[n - 1];
    }
}
