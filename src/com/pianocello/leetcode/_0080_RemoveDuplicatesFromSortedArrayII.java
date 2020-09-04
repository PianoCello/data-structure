package com.pianocello.leetcode;

import java.util.Arrays;

/**
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素最多出现两次，返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 *
 * 示例 1:
 * 给定 nums = [1,1,1,2,2,3],
 * 函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3 。
 *
 * @author PianoCello
 * @date 2020-07-07
 */
public class _0080_RemoveDuplicatesFromSortedArrayII {

    /**
     * 解法一：自己写的
     */
    public static int removeDuplicates(int[] nums) {
        if (nums.length <= 2) {
            return nums.length;
        }
        int len = nums.length;
        int i = 0;
        int j = 1;
        boolean flag = false;
        while (j < len) {
            //已经有两个相同元素了
            if (flag) {
                if (nums[i] == nums[j]) {
                    j++;
                } else {
                    flag = false;
                    nums[++i] = nums[j++];
                }
            } else {
                if (nums[i] == nums[j]) {
                    if (nums[i + 1] != nums[i]) {
                        nums[i + 1] = nums[i];
                    }
                    flag = true;
                    i++;
                    j++;
                } else {
                    nums[++i] = nums[j++];
                }
            }
        }
        return i+1;
    }

    /**
     * 解法二：LeetCode 题解
     */
    public static int removeDuplicates2(int[] nums) {
        if(nums.length < 3) return nums.length;
        int i = 1;
        for(int j = 2; j < nums.length; j++) {
            if(nums[j] != nums[i - 1]) {
                nums[++i] = nums[j];
            }
        }
        return  i + 1;
    }

    /**
     * 解法三：充分利用了排序数组的特性
     */
    public int removeDuplicates3(int[] nums) {
        int i = 0;
        for (int n : nums) {
            if (i < 2 || n > nums[i-2])
                nums[i++] = n;
        }
        return i;
    }

    public static void main(String[] args) {

//        int[] ints = {0,0,1,1,1,1,2,3,3};
        int[] ints = {2,2,3,3,3,3,3};

        int i = removeDuplicates(ints);
        System.out.println(i);
        System.out.println(Arrays.toString(ints));
    }

}
