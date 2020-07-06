package com.pianocello.leetcode;

import java.util.Arrays;

/**
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的子数组
 * 并返回其长度。如果不存在符合条件的子数组，返回 0。
 * <p>
 * 示例：
 * 输入：s = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 *
 * @author PianoCello
 * @date 2020-07-05
 */
public class _0209_MinimumSizeSubarraySum {

    /**
     * 解法一：暴力法
     * 时间复杂度：O(n*n)
     * 空间复杂度：O(1)
     */
    public static int minSubArrayLen(int s, int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += nums[j];
                if (sum >= s) {
                    ans = Math.min(ans, j - i + 1);
                    break;
                }
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    /**
     * 解法二：前缀和 + 二分查找
     * 时间复杂度：O(nlogn)，其中 n 是数组的长度。
     * 需要遍历每个下标作为子数组的开始下标，遍历的时间复杂度是 O(n)，
     * 对于每个开始下标，需要通过二分查找得到长度最小的子数组，二分查找得时间复杂度是 O(logn)，因此总时间复杂度是 (nlogn)。
     * 空间复杂度：O(n)，其中 n 是数组的长度。额外创建数组 sums 存储前缀和。
     */
    public static int minSubArrayLen2(int s, int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        // 为了方便计算，令 size = n + 1
        // sums[0] = 0 意味着前 0 个元素的前缀和为 0
        int[] sums = new int[n + 1];
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }
        for (int i = 1; i <= n; i++) {
            int target = s + sums[i - 1];
            int bound = Arrays.binarySearch(sums, target);
            if (bound < 0) {
                bound = -bound - 1;
            }
            if (bound <= n) {
                ans = Math.min(ans, bound - (i - 1));
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    /**
     * 解法三：快慢指针 （滑动窗口）
     * 时间复杂度：O(n)，其中 n 是数组的长度。指针 start 和 end 最多各移动 n 次。
     * 空间复杂度：O(1)。
     */
    public static int minSubArrayLen3(int s, int[] nums) {
        //定义两个指针
        int start = 0, end = 0;
        int sum = 0;
        //最小数组长度
        int res = Integer.MAX_VALUE;
        while (end < nums.length) {
            sum += nums[end];
            //移除元素
            while (sum >= s) {
                res = Math.min(res, end - start + 1);
                sum -= nums[start++];
            }
            end++;
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }

    public static void main(String[] args) {

        int[] nums = {1, 4, 4};
        int i = minSubArrayLen3(4, nums);
        System.out.println(i);
    }
}
