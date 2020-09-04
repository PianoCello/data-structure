package com.pianocello.leetcode;

/**
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * 请找出其中最小的元素。
 * 你可以假设数组中不存在重复元素。
 * <p>
 * 示例 1:
 * 输入: [3,4,5,1,2]
 * 输出: 1
 * <p>
 * 示例 2:
 * 输入: [4,5,6,7,0,1,2]
 * 输出: 0
 *
 * @author PianoCello
 * @date 2020-07-07
 */
public class _0153_FindMinimumInRotatedSortedArray {

    /**
     * 二分查找
     * 时间复杂度：和二分搜索一样 O(logN)
     * 空间复杂度：O(1)O(1)
     */
    public static int findMin(int[] nums) {
        return findMin(nums, 0, nums.length - 1);
    }

    private static int findMin(int[] nums, int left, int right) {
        if (nums[left] <= nums[right]) {
            return nums[left];
        }
        int mid = (left + right) / 2;
        if (nums[mid] > nums[right]) {
            return findMin(nums, mid + 1, right);
        } else {
            return findMin(nums, left + 1, mid);
        }
    }

    /**
     * 另一种二分查找
     */
    public static int findMin2(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int middle = (left + right) / 2;
            if (nums[middle] < nums[right]) {
                // middle可能是最小值
                right = middle;
            } else {
                // middle肯定不是最小值
                left = middle + 1;
            }
        }
        return nums[left];
    }

    public static void main(String[] args) {

//        int[] ints = {3,2};
//        int[] ints = {3,4,5,1,2};
        int[] ints = {4, 5, 6, 7, 0, 1, 2};
//        int[] ints = {1};

        int min = findMin2(ints);
        System.out.println(min);

    }

}
