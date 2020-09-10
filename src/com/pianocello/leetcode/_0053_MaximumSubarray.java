package com.pianocello.leetcode;

/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 * 示例:
 * 输入: [-2,1,-3,4,-1,2,1,-5,4]
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 *
 * @author PianoCello
 * @date 2020-08-31
 */
public class _0053_MaximumSubarray {

    /**
     * 解法一：
     */
    public static int maxSubArray(int[] nums) {
        int res = nums[0];
        int sum = 0;
        for (int num : nums) {
            if (sum > 0) {
                sum += num;
            } else {
                sum = num;
            }
            res = Math.max(res, sum);
        }
        return res;
    }

    /**
     * 解法二：动态规划
     * <p>
     * 定义一个函数 f(n)，以第 n 个数为结束点的子数列的最大和
     * 存在一个递推关系 f(n) = max(f(n-1) + A[n], A[n]);
     * 将这些最大和保存下来后，取最大的那个就是，最大子数组和。
     * 因为最大连续子数组 等价于 最大的以 n 个数为结束点的子数列和
     */
    public static int maxSubArray2(int[] nums) {
        int res = Integer.MIN_VALUE;
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            max = Math.max(max + nums[i], nums[i]);
            res = Math.max(max, res);
        }
        return res;
    }


    /**
     * 对于一个区间 [left, right]，维护四个变量
     */
    private static class Status {
        // [left, right] 内以 left 为左端点的最大子段和
        int leftSum;
        // [left, right] 内以 right 为右端点的最大子段和
        int rightSum;
        // [left, right] 内的最大子段和
        int maxSum;
        // [left, right] 的区间和
        int allSum;

        public Status(int leftSum, int rightSum, int maxSum, int allSum) {
            this.leftSum = leftSum;
            this.rightSum = rightSum;
            this.maxSum = maxSum;
            this.allSum = allSum;
        }
    }

    /**
     * 解法三：分治法 (区间树)
     *
     */
    public static int maxSubArray3(int[] nums) {
        return get(nums, 0, nums.length - 1).maxSum;
    }

    /**
     * 获取区间树
     * @param nums 要查询的数组
     * @param left 区间左边界
     * @param right 区间右边界
     */
    private static Status get(int[] nums, int left, int right) {
        // 递归逐层深入直到区间长度缩小为 1 的时候 递归「开始回升」
        if (left == right) return new Status(nums[left], nums[left], nums[left], nums[left]);
        // 求中点 不会溢出
        int mid = (left + right) >>> 1;
        Status leftSub = get(nums, left, mid);
        Status rightSub = get(nums, mid + 1, right);
        return pushUp(leftSub, rightSub);
    }

    /**
     * 合并 左右子区间
     * @param left 左子区间
     * @param right 右子区间
     */
    private static Status pushUp(Status left, Status right) {
        // 区间和
        int allSum = left.allSum + right.allSum;
        // 以 left 为左端点的最大子段和
        int leftSum = Math.max(left.leftSum, left.allSum + right.leftSum);
        // 以 right 为左端点的最大子段和
        int rightSum = Math.max(right.rightSum, right.allSum + left.rightSum);
        // 最大子段和
        int maxSum = Math.max(Math.max(left.maxSum, right.maxSum), left.rightSum + right.leftSum);
        // 返回合并后的区间树
        return new Status(leftSum, rightSum, maxSum, allSum);
    }

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};

        int res = maxSubArray(nums);
        int res2 = maxSubArray2(nums);
        System.out.println(res);
        System.out.println(res2);

    }

}
