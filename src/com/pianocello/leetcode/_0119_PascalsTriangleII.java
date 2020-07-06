package com.pianocello.leetcode;

import java.util.Arrays;
import java.util.List;

/**
 * 给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。要求 O(k) 空间复杂度。
 * <p>
 * 示例:
 * 输入: 3
 * 输出: [1,3,3,1]
 *
 * @author PianoCello
 * @date 2020-07-06
 */
public class _0119_PascalsTriangleII {

    public static List<Integer> getRow(int rowIndex) {
        Integer[] temp = new Integer[rowIndex + 1];
        for (int i = 0; i <= rowIndex; i++) {
            for (int j = i; j >= 0; j--) {
                if (j == i) {
                    temp[j] = 1;
                } else if (j != 0) {
                    temp[j] = temp[j - 1] + temp[j];
                }
            }
        }
        return Arrays.asList(temp);
    }

    public static void main(String[] args) {

        List<Integer> list = getRow(5);
        System.out.println(list);
    }
}
