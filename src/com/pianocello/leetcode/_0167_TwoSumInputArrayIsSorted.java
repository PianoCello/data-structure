package com.pianocello.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个已按照升序排列的有序数组，找到两个数使得它们相加之和等于目标数。
 * 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
 * <p>
 * 说明:
 * 返回的下标值（index1 和 index2）不是从零开始的。
 * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
 * <p>
 * 示例:
 * 输入: numbers = [2, 7, 11, 15], target = 9
 * 输出: [1,2]
 * 解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
 *
 * @author PianoCello
 * @date 2020-07-05
 */
public class _0167_TwoSumInputArrayIsSorted {

    /**
     * 使用左右指针
     * 时间复杂度 o(n)
     */
    public static int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                return new int[]{left + 1, right + 1};
            } else if (sum > target) {
                right--;
            } else {
                left++;
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * 使用哈希表临时存储索引
     */
    public static int[] twoSum2(int[] numbers, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            int diff = target - numbers[i];
            if (map.containsKey(diff)) {
                return new int[]{map.get(diff) + 1, i + 1};
            }
            map.put(numbers[i], i);
        }
        return null;
    }

    public static void main(String[] args) {

        int[] numbers = {5, 25, 75};
        int target = 100;

        int[] ints = twoSum(numbers, target);
        System.out.println(Arrays.toString(ints));
    }

}
