package com.pianocello.leetcode;

import java.util.Arrays;

/**
 * 给你一幅由 N × N 矩阵表示的图像，其中每个像素的大小为 4 字节。
 * 请你设计一种算法，将图像旋转 90 度。
 *
 * @author PianoCello
 * @date 2020-07-02
 */
public class _RotateMatrix {

    /**
     * 解法一：暴力法
     * 时间复杂度：O(N^2)，其中 N 是 matrix 的边长。
     * 空间复杂度：O(N^2)，我们需要使用一个和 matrix 的大小相同的辅助数组。
     */
    public static void rotate(int[][] matrix) {
        int len = matrix.length;
        int[][] newMatrix = new int[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                newMatrix[j][len - 1 - i] = matrix[i][j];
            }
        }
        //重新赋值给 matrix
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                matrix[i][j] = newMatrix[i][j];
            }
        }
    }

    /**
     * 解法二：先对角线翻转再左右翻转
     * 时间复杂度：O(N^2)，其中 N 是 matrix 的边长。
     * 空间复杂度：O(1)
     */
    public static void rotate2(int[][] matrix) {
        int len = matrix.length;
        //对角线翻转
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        //左右翻转的轮次 ( >> 1 是除以二的意思)
        int mid = len >> 1;
        //左右翻转
        for (int i = 0; i < mid; i++) {
            for (int j = 0; j < len; j++) {
                int temp = matrix[j][i];
                matrix[j][i] = matrix[j][len - 1 - i];
                matrix[j][len - 1 - i] = temp;
            }
        }
    }

    public static void main(String[] args) {

        int[][] matrix = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };

        rotate2(matrix);

        System.out.println(Arrays.deepToString(matrix));
    }

}
