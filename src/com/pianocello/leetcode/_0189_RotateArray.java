package com.pianocello.leetcode;

import java.util.Arrays;

/**
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 * <p>
 * 示例 1:
 * 输入: [1,2,3,4,5,6,7] 和 k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右旋转 1 步: [7,1,2,3,4,5,6]
 * 向右旋转 2 步: [6,7,1,2,3,4,5]
 * 向右旋转 3 步: [5,6,7,1,2,3,4]
 * <p>
 * 说明:
 * 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
 * 要求使用空间复杂度为 O(1) 的 原地 算法。
 *
 * @author PianoCello
 * @date 2020-07-29
 */
public class _0189_RotateArray {

    /**
     * 解法一：双重循环
     * 时间复杂度：O(kn)
     * 空间复杂度：O(1)
     */
    public static void rotate(int[] nums, int k) {
        if (nums == null || nums.length == 0) return;
        int len = nums.length;
        k %= len;
        for (int i = 0; i < k; i++) {
            // 最后一个数
            int temp = nums[len - 1];
            for (int j = len - 1; j > 0; j--) {
                nums[j] = nums[j - 1];
            }
            nums[0] = temp;
        }
    }

    /**
     * 解法二：使用了 (k % nums.length) 的额外空间  (不符合题目要求)
     * 时间复杂度：O(n)
     * 空间复杂度：O( k % nums.length )
     */
    public static void rotate2(int[] nums, int k) {
        if (nums == null || nums.length == 0) return;
        int len = nums.length;
        k %= len;
        int[] temp = new int[k];
        for (int i = len - k, j = 0; i < len; i++) {
            temp[j++] = nums[i];
        }
        for (int i = len - 1, j = k - 1; i >= 0; i--) {
            if (i >= k) {
                nums[i] = nums[i - k];
            } else {
                nums[i] = temp[j--];
            }
        }
    }

    /**
     * 解法三：翻转
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public static void rotate3(int[] nums, int k) {
        if (nums == null || nums.length == 0) return;
        int len = nums.length;
        k %= len;
        // 翻转数组
        reverse(nums, 0, len - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, len - 1);
    }

    private static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start++] = nums[end];
            nums[end--] = temp;
        }
    }

    /**
     * 解法三：使用环状替换
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public static void rotate4(int[] nums, int k) {
        if (nums == null || nums.length == 0) return;
        int len = nums.length;
        k = k % len;
        int count = 0; // 记录交换位置的次数，n个同学一共需要换n次
        for (int start = 0; count < len; start++) {
            int cur = start; // 从0位置开始换位子
            int pre = nums[cur];
            do {
                int next = (cur + k) % len;
                int temp = nums[next]; // 来到角落...
                nums[next] = pre;
                pre = temp;
                cur = next;
                count++;
            } while (start != cur);  // 循环暂停，回到起始位置，角落无人
        }
    }


    public static void main(String[] args) {

        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        // 7,6,5,4,3,2,1
        // 6,7,1,2,3,4,5
        rotate3(nums, 3);

        System.out.println(Arrays.toString(nums));

    }
}
