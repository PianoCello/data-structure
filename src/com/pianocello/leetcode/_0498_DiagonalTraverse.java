package com.pianocello.leetcode;

import java.util.Arrays;

/**
 * 给定一个含有 M x N 个元素的矩阵（M 行，N 列），请以对角线遍历的顺序返回这个矩阵中的所有元素。
 * <p>
 * 示例:
 * 输入:
 * [
 * [ 1, 2, 3 ],
 * [ 4, 5, 6 ],
 * [ 7, 8, 9 ]
 * ]
 * 输出:  [1,2,4,7,5,3,6,8,9]
 *
 * @author PianoCello
 * @date 2020-07-02
 */
public class _0498_DiagonalTraverse {

    public static int[] findDiagonalOrder(int[][] matrix) {

        int m = matrix.length;
        int n = matrix[0].length;
        int[] res = new int[m * n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {



            }
        }



        return res;
    }

    public static void main(String[] args) {

        int[][] matrix = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };

        int[] order = findDiagonalOrder(matrix);

        System.out.println(Arrays.toString(order));

    }

}
