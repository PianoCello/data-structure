package com.pianocello.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d 
 * 使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
 *
 * 注意：
 * 答案中不可以包含重复的四元组。
 *
 * 示例：
 * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 * 满足要求的四元组集合为：
 * [
 *   [-1,  0, 0, 1],
 *   [-2, -1, 1, 2],
 *   [-2,  0, 0, 2]
 * ]
 *
 * @author PianoCello
 * @date 2020-07-14
 */
public class _0018_4Sum {

    /**
     * 解法一：排序 + 左右指针 + 快速跳过无解的条件
     */
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 4) return result;
        // 先对数组进行排序
        Arrays.sort(nums);
        int len = nums.length;
        // 找出最大和最小的和
        int minValue = nums[0] + nums[1] + nums[2] + nums[3];
        int maxValue = nums[len - 1] + nums[len - 2] + nums[len - 3] + nums[len - 4];
        // 要找的数不存在
        if (minValue > target || maxValue < target) return result;
        for (int i = 0; i < len - 3; i++) {
            // 跳过重复的数
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            for (int j = i + 1; j < len - 2; j++) {
                // 跳过重复的数
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                int left = j + 1;
                int right = len - 1;
                // 找出最大和最小的和
                minValue = nums[i] + nums[j] + nums[left] + nums[left + 1];
                maxValue = nums[i] + nums[j] + nums[right] + nums[right - 1];
                // 要找的数不存在
                if (minValue > target || maxValue < target) continue;

                while (left < right) {
                    int temp = nums[left] + nums[right] + nums[i] + nums[j];
                    if (temp == target) {
                        List<Integer> list = new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        result.add(list);
                        // 左指针右移 跳过重复的数
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        // 右指针左移 跳过重复的数
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        left++;
                        right--;
                    } else if (temp > target) {
                        // 右指针左移 跳过重复的数
                        while (left < right && nums[right] == nums[right - 1]) right--;
                        right--;
                    } else {
                        // 左指针右移 跳过重复的数
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        left++;
                    }
                }
            }
        }
        return result;
    }

    /**
     * 解法二：外层循环加 threeSum 结果
     */
    public static List<List<Integer>> fourSum2(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            List<List<Integer>> threeSum = threeSum(nums, i + 1, target - num);
            for (List<Integer> list : threeSum) {
                list.add(num);
                lists.add(list);
            }
            while ((i + 1) < nums.length && nums[i] == nums[i + 1]) i++;
        }
        return lists;
    }

    public static List<List<Integer>> threeSum(int[] nums, int start, int target) {
        List<List<Integer>> lists = new ArrayList<>();
        //外层循环从左向右依次遍历
        for (int i = start; i < nums.length; i++) {
            //找 a + b + c = 0 等于找 -a = b + c
            int tempTarget = target - nums[i];
            int low = i + 1;
            int high = nums.length - 1;
            //左右指针
            while (low < high) {
                int left = nums[low];
                int right = nums[high];
                int sum = left + right;
                if (sum == tempTarget) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[low]);
                    list.add(nums[high]);
                    //找到目标
                    lists.add(list);
                    //左右指针向中间移动
                    while (low < high && nums[low] == left) low++;
                    while (high > low && nums[high] == right) high--;
                } else if (sum > tempTarget) {
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

    /**
     * 解法三：套用通用解题模板
     */
    public static List<List<Integer>> fourSum3(int[] nums, int target) {
        Arrays.sort(nums);
        return nSumTarget(nums, 4, 0, target);
    }

    /**
     * N 数之和模板 调用这个函数之前一定要先给 nums 排序
     *
     * @param nums   给定的数组
     * @param n      几个数之和
     * @param start  从哪个位置开始
     * @param target 要找的目标
     */
    private static List<List<Integer>> nSumTarget(int[] nums, int n, int start, int target) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        // 至少是 2Sum，且数组大小不应该小于 n
        if (n < 2 || len < n) return res;
        // 2Sum 是 base case
        if (n == 2) {
            // 双指针那一套操作
            int low = start, high = len - 1;
            while (low < high) {
                int left = nums[low], right = nums[high];
                int sum = left + right;
                if (sum < target) {
                    while (low < high && nums[low] == left) low++;
                } else if (sum > target) {
                    while (low < high && nums[high] == right) high--;
                } else {
                    List<Integer> list = new ArrayList<>();
                    list.add(left);
                    list.add(right);
                    res.add(list);
                    while (low < high && nums[low] == left) low++;
                    while (low < high && nums[high] == right) high--;
                }
            }
        } else {
            // n > 2 时，递归计算 (n-1)Sum 的结果
            for (int i = start; i < len; i++) {
                List<List<Integer>> sub = nSumTarget(nums, n - 1, i + 1, target - nums[i]);
                for (List<Integer> arr : sub) {
                    // (n-1)Sum 加上 nums[i] 就是 nSum
                    arr.add(nums[i]);
                    res.add(arr);
                }
                while (i < len - 1 && nums[i] == nums[i + 1]) i++;
            }
        }
        return res;
    }

    public static void main(String[] args) {

        int[] nums = {1, 0, -1, 0, -2, 2};
        List<List<Integer>> lists = fourSum3(nums, 0);
        System.out.println(lists);
    }
}
