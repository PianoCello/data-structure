package com.pianocello.leetcode;

/**
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，
 * 影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
 * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，
 * 计算你不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 *
 * 示例 1：
 * 输入：[1,2,3,1]
 * 输出：4
 * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 *       偷窃到的最高金额 = 1 + 3 = 4 。
 *
 * @author PianoCello
 * @date 2020-09-13
 */
public class _0198_HouseRobber {

    /**
     * 解法一：动态规划
     *
     */
    public int rob(int[] nums) {
        int n = nums.length;
        if (n <= 1) return n == 0 ? 0 : nums[0];
        // memo[i] 表示考虑抢劫 nums[i...n-1] 所能获得最大收益
        int[] mem = new int[n];
        mem[0] = nums[0];
        mem[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; i++) {
            mem[i] = Math.max(mem[i - 1], mem[i - 2] + nums[i]);
        }
        return mem[n - 1];
    }

}
