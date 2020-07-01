package com.pianocello.leetcode;

/**
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * <p>
 * 你可以假设数组中无重复元素。
 * <p>
 * 示例 1:
 * 输入: [1,3,5,6], 5
 * 输出: 2
 * <p>
 * 示例 2:
 * 输入: [1,3,5,6], 2
 * 输出: 1
 *
 * @author PianoCello
 * @date 2020-07-01
 */
public class _0035_SearchInsertPosition {

    /**
     * 解法一：线性查找
     * 时间复杂度：O(N)。
     * 空间复杂度：O(1)
     */
    public static int searchInsert2(int[] nums, int target) {
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            if (target <= nums[i]) {
                return i;
            }
        }
        return length;
    }

    /**
     * 解法二：二分查找
     * 时间复杂度：O(logn)。
     * 空间复杂度：O(1)
     */
    public static int searchInsert(int[] nums, int target) {
        int begin = 0;
        int end = nums.length - 1;
        //当起始小于等于终止
        while (begin <= end) {
            int mid = (begin + end) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                end = mid - 1;
            } else {
                begin = mid + 1;
            }
        }
        return begin;
    }

    public static void main(String[] args) {

        int[] ints = {1, 3, 5, 6, 7};
        int i = searchInsert(ints, 0);
        System.out.println(i);
    }
}
