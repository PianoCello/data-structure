package com.pianocello.leetcode;

import java.util.Arrays;

/**
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的子数组
 * 并返回其长度。如果不存在符合条件的子数组，返回 0。
 * <p>
 * 示例：
 * 输入：s = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 *
 * @author PianoCello
 * @date 2020-07-05
 */
public class _0209_MinimumSizeSubarraySum {

    public static int minSubArrayLen(int s, int[] nums) {
        int sum = 0;
        Arrays.sort(nums);
        for (int i = nums.length - 1; i >= 0; i--) {
            sum += nums[i];
            if (sum >= s) {
                return nums.length - i;
            }
        }
        return 0;
    }

    public static void main(String[] args) {

        int[] nums = {12, 28, 83, 4, 25, 26, 25, 2, 25, 25, 25, 12};
        int i = minSubArrayLen(213, nums);
        System.out.println(i);
    }
}
