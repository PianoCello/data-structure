package com.pianocello.leetcode;

/**
 * 给定一个包含 0, 1, 2, ..., n 中 n 个数的序列，找出 0 .. n 中没有出现在序列中的那个数。
 *
 * 示例 1:
 * 输入: [3,0,1]
 * 输出: 2
 *
 * 示例 2:
 * 输入: [9,6,4,2,3,5,7,0,1]
 * 输出: 8
 *
 * @author PianoCello
 * @date 2020-09-06
 */
public class _0268_MissingNumber {

    /**
     * 解法一：使用额外的数组
     */
    public int missingNumber(int[] nums) {
        boolean[] exists = new boolean[nums.length + 1];
        for (int num : nums) {
            exists[num] = true;
        }
        for (int i = 0; i < exists.length; i++) {
            if (!exists[i]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 解法二：异或大法
     * 与 [重复一次的数字] 一题类似
     */
    public int missingNumber2(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res = res ^ nums[i];
            res = res ^ i;
        }
        res = res ^ nums.length;
        return res;
    }
}
