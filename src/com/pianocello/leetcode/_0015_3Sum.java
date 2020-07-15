package com.pianocello.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c
 * 使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 *
 * 示例：
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]
 *
 * 满足要求的三元组集合为：
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 *
 * @author PianoCello
 * @date 2020-07-15
 */
public class _0015_3Sum {

    /**
     * 解法一: 排序 + 双指针
     * 时间复杂度: O(n^2)
     * 排序的时间复杂度是 O(NlogN)，for 循环的时间复杂度是 O(n^2)
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        //先排序
        Arrays.sort(nums);
        //外层循环从左向右依次遍历
        for (int i = 0; i < nums.length; i++) {
            //找 a + b + c = 0 等于找 -a = b + c
            int target = 0 - nums[i];
            int low = i + 1;
            int high = nums.length - 1;
            //左右指针
            while (low < high) {
                int left = nums[low];
                int right = nums[high];
                int sum = left + right;
                if (sum == target) {
                    //找到目标
                    lists.add(Arrays.asList(nums[i], nums[low], nums[high]));
                    //左右指针向中间移动
                    while (low < high && nums[low] == left) low++;
                    while (high > low && nums[high] == right) high--;
                } else if (sum > target) {
                    // high 左移，遇到重复数字需要继续左移
                    while (high > low && nums[high] == right) high--;
                } else {
                    // low 右移，遇到重复数字需要继续右移
                    while (low < high && nums[low] == left) low++;
                }
            }
            //外层循环也需要跳过重复的数字
            while ((i + 1) < nums.length && nums[i] == nums[i + 1]) i++;
        }
        return lists;
    }

    public static void main(String[] args) {

        int[] nums = {-2,0,1,1,2};
        List<List<Integer>> lists = threeSum(nums);
        System.out.println(lists);
    }
}
