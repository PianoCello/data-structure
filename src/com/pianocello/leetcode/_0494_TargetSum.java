package com.pianocello.leetcode;

/**
 * 给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。
 * 现在你有两个符号 + 和 -。对于数组中的任意一个整数，你都可以从 + 或 -中选择一个符号添加在前面。
 *
 * 返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
 *
 * 示例：
 * 输入：nums: [1, 1, 1, 1, 1], S: 3
 * 输出：5
 * 解释：
 * -1+1+1+1+1 = 3
 *
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 *
 * @author PianoCello
 * @date 2020-06-21
 */
public class _0494_TargetSum {

    public static int findTargetSumWays(int[] nums, int s) {

        int target = 0;








        return target;
    }

    public static void main(String[] args) {
        int[] nums = {1,1,1,1,1};
        int s = 3;

        int i = findTargetSumWays(nums,s);
        System.out.println(i);
    }
}
