package com.pianocello.leetcode;

/**
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * 请找出其中最小的元素。
 * 注意数组中可能存在重复的元素。
 *
 * 示例 1：
 * 输入: [1,3,5]
 * 输出: 1
 *
 * 示例 2：
 * 输入: [2,2,2,0,1]
 * 输出: 0
 *
 * @author PianoCello
 * @date 2020-07-07
 */
public class _0154_FindMinimumInRotatedSortedArrayII {

    /**
     * 二分查找
     * 时间复杂度：平均时间复杂度为 O(logN)，其中 N 为数组长度。
     * 但是在最坏情况下，也就是数组中包含相同元素时(nums[pivot]==nums[high])，需要逐个遍历元素，复杂度为 O(N)。
     * 空间复杂度：O(1)。
     *
     * 计算中间点是用 pivot = low + (high-low)/2 来计算的，
     * 而不是直接 pivot = (high + low)/2，这主要是为了防止两个数字相加后发生溢出。
     */
    public static int findMin(int[] nums) {
        //左右指针
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            //
            int pivot = low+(high-low)/2;
            if (nums[pivot] > nums[high]) {
                low = pivot + 1;
            } else if (nums[pivot] < nums[high]) {
                high = pivot;
            } else {
                high--;
            }
        }
        return nums[low];
    }

    public static void main(String[] args) {

        int[] ints = {10,1,10,10,10};
        int min = findMin(ints);
        System.out.println(min);
    }

}
