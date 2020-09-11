package com.pianocello.leetcode;

import java.util.Arrays;
import java.util.Random;

/**
 * 打乱一个没有重复元素的数组。
 * <p>
 * 示例:
 * // 以数字集合 1, 2 和 3 初始化数组。
 * int[] nums = {1,2,3};
 * Solution solution = new Solution(nums);
 * <p>
 * // 打乱数组 [1,2,3] 并返回结果。任何 [1,2,3]的排列返回的概率应该相同。
 * solution.shuffle();
 * <p>
 * // 重设数组到它的初始状态[1,2,3]。
 * solution.reset();
 * <p>
 * // 随机返回数组[1,2,3]打乱后的结果。
 * solution.shuffle();
 *
 * @author PianoCello
 * @date 2020-09-11
 */
public class _0384_ShuffleAnArray {

    private static class Solution {

        private final int[] nums;

        public Solution(int[] nums) {
            this.nums = nums;
        }

        /**
         * Resets the array to its original configuration and return it.
         */
        public int[] reset() {
            return nums;
        }

        /**
         * 在前 n-1 张牌洗好的情况下
         * 第 n 张牌随机与前 n-1 张牌的其中一张牌交换
         */
        public int[] shuffle() {
            int[] copy = Arrays.copyOf(nums, nums.length);
            Random random = new Random();
            for (int i = 1; i < copy.length; ++i) {
                int pre = random.nextInt() % (i + 1);
                if (i != pre) {
                    int temp = copy[i];
                    copy[i] = copy[pre];
                    copy[pre] = temp;
                }
            }
            return copy;
        }
    }
}
