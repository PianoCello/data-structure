package com.pianocello.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。
 * 为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。
 * 所有整数的范围在 -2^28 到 2^28 - 1 之间，最终结果不会超过 2^31 - 1 。
 *
 * 例如:
 * 输入:
 * A = [ 1, 2]
 * B = [-2,-1]
 * C = [-1, 2]
 * D = [ 0, 2]
 * 输出:
 * 2
 *
 * 解释:
 * 两个元组如下:
 * 1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
 * 2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
 *
 * @author PianoCello
 * @date 2020-07-14
 */
public class _0454_4SumII {

    /**
     * 解法一：暴力破解
     */
    public static int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        if (A == null || A.length == 0) return 0;
        int len = A.length;
        int res = 0;
        Arrays.sort(A);
        Arrays.sort(B);
        Arrays.sort(C);
        Arrays.sort(D);
        int minValue = A[0] + B[0] + C[0] + D[0];
        int maxValue = A[len - 1] + B[len - 1] + C[len - 1] + D[len - 1];
        // 不存在解
        if (minValue > 0 || maxValue < 0) return 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                for (int k = 0; k < len; k++) {
                    int minVal = A[i] + B[j] + C[k] + D[0];
                    int maxVal = A[i] + B[j] + C[k] + D[len - 1];
                    // 不存在解
                    if (minVal > 0 || maxVal < 0) continue;
                    for (int s = 0; s < len; s++) {
                        int temp = A[i] + B[j] + C[k] + D[s];
                        if (temp == 0) {
                            res++;
                        }
                    }
                }
            }
        }
        return res;
    }

    /**
     * 解法二：HashMap 存两个数组之和，如 AB。然后计算另外两个数组之和，如 CD。时间复杂度为：O(n^2) + O(n^2),得到 O(n^2).
     */
    public static int fourSumCount2(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> map = new HashMap<>((int) (A.length * A.length / 0.75));
        for (int a : A) {
            for (int b : B) {
                map.merge(a + b, 1, (x, y) -> x + y);
            }
        }
        int sum = 0;
        for (int a : C) {
            for (int b : D) {
                if (map.containsKey(0 - a - b)) {
                    sum += map.get(0 - a - b);
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] A = {};
        int[] B = {};
        int[] C = {};
        int[] D = {};

        int i = fourSumCount2(A, B, C, D);
        System.out.println(i);
    }
}
