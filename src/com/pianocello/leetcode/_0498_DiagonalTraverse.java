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

    /**
     * 解法一：根据规律变换坐标遍历二维数组
     * 时间复杂度：O(N⋅M)，每个元素只处理一次。
     * 空间复杂度：O(1)，不使用额外空间
     */
    public static int[] findDiagonalOrder(int[][] matrix) {
        //避免多余的循环
        if (matrix == null || matrix.length == 0) {
            return new int[0];
        }

        int m = matrix.length;
        int n = matrix[0].length;

        int size = m * n;
        int[] res = new int[size];

        //遍历的坐标
        int x = 0, y = 0;
        //true代表向右上角遍历，false代表向左下角遍历
        boolean flag = true;
        for (int i = 0; i < size; i++) {
            res[i] = matrix[x][y];
            //向右上角遍历
            if (flag) {
                x--;
                y++;
                //到了右边界
                if (y > n - 1) {
                    y = n - 1;
                    x += 2;
                    flag = false;
                }
                //到了上边界
                if (x < 0) {
                    x = 0;
                    flag = false;
                }
            } else {
                //向左下角遍历
                x++;
                y--;
                //到了下边界
                if (x > m - 1) {
                    x = m - 1;
                    y += 2;
                    flag = true;
                }
                //到了左边界
                if (y < 0) {
                    y = 0;
                    flag = true;
                }
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
