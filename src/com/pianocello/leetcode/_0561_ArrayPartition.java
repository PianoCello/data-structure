package com.pianocello.leetcode;

import java.util.Arrays;

/**
 * 给定长度为 2n 的数组, 你的任务是将这些数分成 n 对,
 * 例如 (a1, b1), (a2, b2), ..., (an, bn) ，使得从 1 到 n 的 min(ai, bi) 总和最大。
 *
 * 示例 1:
 * 输入: [1,4,3,2]
 * 输出: 4
 * 解释: n 等于 2, 最大总和为 4 = min(1, 2) + min(3, 4).
 *
 * @author PianoCello
 * @date 2020-07-05
 */
public class _0561_ArrayPartition {

    /**
     * 垃圾题目，为了考排序？？？
     * 时间复杂度：O(nlog(n))。排序需要 O(nlog(n)) 的时间。另外会有一次数组的遍历。
     * 空间复杂度：O(1)。仅仅需要常数级的空间.
     */
    public int arrayPairSum(int[] nums) {
        int res = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i += 2) {
            res += nums[i];
        }
        return res;
    }

}
