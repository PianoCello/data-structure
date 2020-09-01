package com.pianocello.leetcode;

/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 * 示例:
 * 输入: [-2,1,-3,4,-1,2,1,-5,4]
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 *
 * @author PianoCello
 * @date 2020-08-31
 */
public class _0053_MaximumSubarray {

    /**
     * 解法一：
     *
     */
    public static int maxSubArray(int[] nums) {
        int res = nums[0];
        int sum = 0;
        for (int num : nums) {
            if (sum > 0) {
                sum += num;
            } else {
                sum = num;
            }
            res = Math.max(res, sum);
        }
        return res;
    }

    /**
     * 解法二：动态规划
     *
     * 定义一个函数 f(n)，以第 n 个数为结束点的子数列的最大和
     * 存在一个递推关系 f(n) = max(f(n-1) + A[n], A[n]);
     * 将这些最大和保存下来后，取最大的那个就是，最大子数组和。
     * 因为最大连续子数组 等价于 最大的以 n 个数为结束点的子数列和
     */
    public static int maxSubArray2(int[] nums) {
        int res = Integer.MIN_VALUE;
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            max = Math.max(max + nums[i], nums[i]);
            res = Math.max(max, res);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};

        int res = maxSubArray(nums);
        int res2 = maxSubArray2(nums);
        System.out.println(res);
        System.out.println(res2);

    }

}
