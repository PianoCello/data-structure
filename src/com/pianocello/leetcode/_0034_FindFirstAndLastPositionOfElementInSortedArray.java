package com.pianocello.leetcode;

import java.util.Arrays;

/**
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。
 * 找出给定目标值在数组中的开始位置和结束位置。你的算法时间复杂度必须是 O(log n) 级别。
 * 如果数组中不存在目标值，返回 [-1, -1]。
 *
 * 示例 1:
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: [3,4]
 *
 * 示例 2:
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: [-1,-1]
 *
 * @author PianoCello
 * @date 2020-08-26
 */
public class _0034_FindFirstAndLastPositionOfElementInSortedArray {

    /**
     * 解法一：二分法
     *
     */
    public static int[] searchRange(int[] nums, int target) {
        int[] res = {-1, -1};
        if (nums == null || nums.length == 0) {
            return res;
        }
        // 二分法的左右边界 左闭右开区间
        int left = 0, right = nums.length;
        // 先找出左边界, 当 left == right 时, 跳出循环
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (nums[mid] == target) {
                right = mid;// 往左边靠
            } else if (nums[mid] > target) {
                // 因为这里的 right 是开区间
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        if (left < nums.length && nums[left] == target) {
            res[0] = left;
        } else {
            return res;
        }
        // 找出右边界
        left = 0;
        right = nums.length;
        // 当 left == right 时, 跳出循环
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (nums[mid] == target) {
                left = mid + 1; // 往右边靠
            } else if (nums[mid] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        res[1] = left - 1;
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {5, 7, 7, 8, 8, 10};
        int[] ints = searchRange(nums, 9);

        System.out.println(Arrays.toString(ints));
    }

}
