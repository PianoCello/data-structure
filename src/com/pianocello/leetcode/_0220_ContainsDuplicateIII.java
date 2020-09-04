package com.pianocello.leetcode;

import java.util.TreeSet;

/**
 * 在整数数组 nums 中，是否存在两个下标 i 和 j，使得 nums [i] 和 nums [j] 的差的绝对值小于等于 t
 * 且满足 i 和 j 的差的绝对值也小于等于 ķ 。 如果存在则返回 true，不存在返回 false。
 * <p>
 * 示例 1:
 * 输入: nums = [1,2,3,1], k = 3, t = 0
 * 输出: true
 * <p>
 * 示例 2:
 * 输入: nums = [1,5,9,1,5,9], k = 2, t = 3
 * 输出: false
 *
 * @author PianoCello
 * @date 2020-07-09
 */
public class _0220_ContainsDuplicateIII {

    /**
     * 解法一：滑动窗口 + TreeSet
     * ceiling 是获取大于等于给定值的最小值
     * floor 是获取小于等于给定值的最大值
     */
    public static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        //使用 long 是因为可能会产生 int 溢出
        TreeSet<Long> set = new TreeSet<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (i > k) {
                set.remove((long) nums[i - k - 1]);
            }
            Long low = set.ceiling((long) nums[i] - t);
            //是否找到了符合条件的数
            if (low != null && low <= (long) nums[i] + t) {
                return true;
            }
            set.add((long) nums[i]);
        }
        return false;
    }

    public static void main(String[] args) {

        int[] nums = {3, 6, 0, 4};
        boolean b = containsNearbyAlmostDuplicate(nums, 2, 2);
        System.out.println(b);
    }

}
