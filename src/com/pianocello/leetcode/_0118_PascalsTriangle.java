package com.pianocello.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 *
 * 示例:
 * 输入: 5
 * 输出:
 * [
 *      [1],
 *     [1,1],
 *    [1,2,1],
 *   [1,3,3,1],
 *  [1,4,6,4,1]
 * ]
 *
 * @author PianoCello
 * @date 2020-07-06
 */
public class _0118_PascalsTriangle {

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> yang = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    list.add(1);
                } else {
                    //即第 n+1 行的第 i 个数等于第 n 行的第 i-1 个数和第 i 个数之和
                    List<Integer> temp = yang.get(i - 1);
                    list.add(temp.get(j - 1) + temp.get(j));
                }
            }
            yang.add(list);
        }
        return yang;
    }

    public static void main(String[] args) {

        List<List<Integer>> generate = generate(5);
        System.out.println(generate);
    }
}
