package com.pianocello.leetcode;

/**
 * 斐波那契数，通常用 F(n) 表示，形成的序列称为斐波那契数列。
 * 该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。
 * <p>
 * 也就是：
 * F(0) = 0,   F(1) = 1
 * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 * 给定 N，计算 F(N)。
 * <p>
 * 示例 1：
 * 输入：2
 * 输出：1
 * 解释：F(2) = F(1) + F(0) = 1 + 0 = 1.
 * <p>
 * 提示：
 * 0 ≤ N ≤ 30
 *
 * @author PianoCello
 * @date 2020-08-17
 */
public class _0509_FibonacciNumber {

    /**
     * 解法一：暴力递归
     * 时间复杂度：O(2^n) 指数级 要爆炸了
     */
    public static int fib(int N) {
        if (N == 0) {
            return 0;
        } else if (N == 1) {
            return 1;
        }
        return fib(N - 1) + fib(N - 2);
    }

    /**
     * 解法二：备忘录递归
     * 时间复杂度：O(n)
     * 空间复杂度：O(n) --> mem O(n) + 隐式栈 O(n)
     */
    public static int fib2(int N) {
        // 备忘录
        int[] mem = new int[N + 1];
        return fib2(mem, N);
    }

    private static int fib2(int[] mem, int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }else if (mem[n] != 0) {
            return mem[n];
        }
        mem[n] = fib2(mem, n - 1) + fib2(mem, n - 2);
        return mem[n];
    }

    /**
     * 解法三：动态规划 迭代
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public static int fib3(int N) {
        if (N == 0) return 0;
        // 备忘录
        int[] dp = new int[N + 1];
        dp[1] = 1;
        for (int i = 2; i <= N; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[N];
    }

    /**
     * 解法四：动态规划优化 + 对 dp 状态进行压缩
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public static int fib4(int N) {
        if (N == 0) return 0;
        int t1 = 0, t2 = 1;
        for (int i = 2; i <= N; i++) {
            int cur = t1 + t2;
            t1 = t2;
            t2 = cur;
        }
        return t2;
    }

    public static void main(String[] args) {
        System.out.println(fib4(3));
        System.out.println(fib3(3));
        System.out.println(fib2(3));
        System.out.println(fib(3));
    }
}
