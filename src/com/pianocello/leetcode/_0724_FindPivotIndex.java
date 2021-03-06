package com.pianocello.leetcode;

/**
 * 给定一个整数类型的数组 nums，请编写一个能够返回数组 “中心索引” 的方法。
 * 我们是这样定义数组 中心索引 的：数组中心索引的左侧所有元素相加的和等于右侧所有元素相加的和。
 * 如果数组不存在中心索引，那么我们应该返回 -1。如果数组有多个中心索引，那么我们应该返回最靠近左边的那一个。
 * <p>
 * 示例 1：
 * nums = [1, 7, 3, 6, 5, 6]
 * 输出：3
 * 索引 3 (nums[3] = 6) 的左侧数之和 (1 + 7 + 3 = 11)，与右侧数之和 (5 + 6 = 11) 相等。
 * 同时, 3 也是第一个符合要求的中心索引。
 * <p>
 * 示例 2：
 * nums = [1, 2,0, 3]
 * 输出：-1
 * 数组中不存在满足此条件的中心索引。
 *
 * @author PianoCello
 * @date 2020-07-01
 */
public class _0724_FindPivotIndex {

    /**
     * 解法一：前缀和 （LeetCode题解）
     * S 是数组的和，当索引 i 是中心索引时，位于 i 左边数组元素的和 leftsum 满足 S - nums[i] - leftsum。
     * 我们只需要判断当前索引 i 是否满足 leftsum==S-nums[i]-leftsum 并动态计算 leftsum 的值。
     *
     * 时间复杂度：O(N)，其中 N 是 nums 的长度。
     * 空间复杂度：O(1)，使用了 S 和 leftsum 。
     */
    public static int pivotIndex(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int sum = 0, leftsum = 0;
        for (int x : nums) {
            sum += x;
        }
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            if (sum - nums[i] - leftsum == leftsum) {
                return i;
            }
            leftsum += nums[i];
        }
        return -1;
    }

    public static void main(String[] args) {

        int[] ints = {-1,-1,-1,-1,0,-1};

        int i = pivotIndex(ints);
        System.out.println(i);
    }

}
