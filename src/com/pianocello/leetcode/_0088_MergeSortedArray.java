package com.pianocello.leetcode;

import java.util.Arrays;

/**
 * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
 * <p>
 * 说明:
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 *
 * 示例:
 * 输入:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 * <p>
 * 输出: [1,2,2,3,5,6]
 *
 * @author PianoCello
 * @date 2020-08-11
 */
public class _0088_MergeSortedArray {

    /**
     * 解法一：双指针 从后往前 将最大的数移到最后
     * 时间复杂度 : O(n + m)
     * 空间复杂度 : O(1)
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int len = m + n - 1;
        m -= 1;
        n -= 1;
        while (m >= 0 && n >= 0) {
            nums1[len--] = nums1[m] > nums2[n] ? nums1[m--] : nums2[n--];
        }
        System.arraycopy(nums2, 0, nums1, 0, n + 1);
    }

    public static void main(String[] args) {

        int[] nums1 = {0, 0, 0, 0, 0};
        int[] nums2 = {1, 2, 12, 13};
        merge(nums1, 1, nums2, 4);

        System.out.println(Arrays.toString(nums1));
    }

}
