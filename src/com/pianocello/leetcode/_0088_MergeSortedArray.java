package com.pianocello.leetcode;

import java.util.Arrays;

/**
 * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
 *
 * 说明:
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 *  
 * 示例:
 * 输入:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 *
 * 输出: [1,2,2,3,5,6]
 *
 * @author PianoCello
 * @date 2020-07-24
 */
public class _0088_MergeSortedArray {

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        if (n == 0) return;
        for (int i = 0, j = 0; i < m; i++) {
            if (nums1[i] > nums2[j]) {
                int temp = nums1[i];
                nums1[i] = nums2[j];
                nums2[j] = temp;
                // 交换后要将大的数往后移
                while (j + 1 < n && nums2[j] > nums2[j + 1]) {
                    int cur = nums2[j];
                    nums2[j] = nums2[j + 1];
                    nums2[j + 1] = cur;
                    j++;
                }
            }
        }
        for (int k = m, r = 0; k < m + n; k++, r++) {
            nums1[k] = nums2[r];
        }
    }

    public static void main(String[] args) {

        int[] nums1 = {4,5,6,0,0,0};
        int[] nums2 = {1,2,3};
        merge(nums1, 3, nums2, 3);

        System.out.println(Arrays.toString(nums1));
    }

}
