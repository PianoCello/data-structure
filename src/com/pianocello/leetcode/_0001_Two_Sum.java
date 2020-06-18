package com.pianocello.leetcode;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 两数之和:
 * <p>
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那两个整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 * </p>
 * 示例:
 * 给定 nums = [2, 7, 11, 15], target = 9
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 *
 * @author PianoCello
 * @date 2019-11-22
 */
public class _0001_Two_Sum {

    public static void main(String[] args) {

        //整数数组
        int[] nums = {2, 7, 11, 15};
        //目标值
        int target = 18;

        int[] res = twoSum(nums, target);

        System.out.println(Arrays.toString(res));
    }

    public static int[] twoSum(int[] nums, int target) {

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int diff = target - nums[i];
            if (map.containsKey(diff)) {
                //返回下标数组
                return new int[]{map.get(diff), i};
            }
            //key = nums[i], value = i
            map.put(nums[i], i);
        }
        return null;
    }
}
