package com.pianocello.leetcode;

/**
 * 给定一个二进制数组，计算其中最大连续 1 的个数。
 * <p>
 * 示例 1:
 * 输入: [1,1,0,1,1,1]
 * 输出: 3
 * 解释: 开头的两位和最后的三位都是连续1，所以最大连续1的个数是 3.
 *
 * @author PianoCello
 * @date 2020-07-05
 */
public class _0485_MaxConsecutiveOnes {

    /**
     * 解法一：使用快慢指针
     */
    public static int findMaxConsecutiveOnes(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int max = 0;
        //是否开始出现 1
        boolean flag = false;
        int slow = 0;
        int fast = 0;
        while (fast < nums.length) {
            //遇到 1 的话
            if (nums[fast] == 1) {
                if (!flag) {
                    slow = fast;
                    flag = true;
                }
            } else {
                if (flag) {
                    max = Math.max(max, fast - slow);
                    flag = false;
                }
            }
            fast++;
        }
        //如果 1 一直持续到末尾
        if (flag) {
            max = Math.max(max, fast - slow);
        }
        return max;
    }

    /**
     * 解法二：一次遍历
     * 时间复杂度：O(N)。N 值得是数组的长度。
     * 空间复杂度：O(1)，仅仅使用了 count 和 maxCount。
     */
    public static int findMaxConsecutiveOnes2(int[] nums) {
        int count = 0;
        int maxCount = 0;

        for (int num : nums) {
            if (num == 1) {
                count++;
            } else {
                maxCount = Math.max(maxCount, count);
                count = 0;
            }
        }
        return Math.max(maxCount, count);
    }

    public static void main(String[] args) {

        int[] nums = {1, 0, 1, 1, 0, 1};
        int ones = findMaxConsecutiveOnes(nums);
        System.out.println(ones);

    }

}
