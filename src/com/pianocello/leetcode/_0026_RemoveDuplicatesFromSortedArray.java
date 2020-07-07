package com.pianocello.leetcode;

import java.util.Arrays;

/**
 * 给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 *
 * 示例 1:
 * 给定数组 nums = [1,1,2],
 * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
 * 你不需要考虑数组中超出新长度后面的元素。
 *
 * @author PianoCello
 * @date 2020-07-07
 */
public class _0026_RemoveDuplicatesFromSortedArray {

    /**
     * 解法一：自己写的
     */
    public static int removeDuplicates(int[] nums) {
        if (nums.length <= 1) return nums.length;
        int i = 0;
        int j = 1;
        while (j < nums.length) {
            if (nums[i] == nums[j]) {
                j++;
            } else {
                nums[++i] = nums[j++];
            }
        }
        return i+1;
    }

    /**
     * 解法二：LeetCode 题解
     */
    public static int removeDuplicates2(int[] nums) {
        if (nums.length == 0) return 0;
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                nums[++i] = nums[j];
            }
        }
        return i + 1;
    }

    public static void main(String[] args) {

        int[] ints = {0,0,1,1,1,2,2,3,3,4};

        int i = removeDuplicates2(ints);
        System.out.println(i);
        System.out.println(Arrays.toString(ints));
    }

}
