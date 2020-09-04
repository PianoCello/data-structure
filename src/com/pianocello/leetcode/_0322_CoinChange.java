package com.pianocello.leetcode;

import java.util.Arrays;

/**
 * 给定不同面额的硬币 coins 和一个总金额 amount。
 * 编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
 * 如果没有任何一种硬币组合能组成总金额，返回 -1。
 * <p>
 * 示例 1:
 * 输入: coins = [1, 2, 5], amount = 11
 * 输出: 3
 * 解释: 11 = 5 + 5 + 1
 * <p>
 * 示例 2:
 * 输入: coins = [2], amount = 3
 * 输出: -1
 *
 * 说明:
 * 你可以认为每种硬币的数量是无限的。
 *
 * @author PianoCello
 * @date 2020-08-17
 */
public class _0322_CoinChange {

    /**
     * 解法一：暴力递归
     * 时间复杂度是 O(kn^k)。指数级 服务器要爆炸了
     */
    public static int coinChange(int[] coins, int amount) {
        // base case
        if (amount == 0) return 0;
        if (amount < 0) return -1;
        // 求最小值，所以初始化为正无穷
        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            int subResult = coinChange(coins, amount - coin);
            // 子问题无解，跳过
            if (subResult == -1) {
                continue;
            }
            res = Math.min(res, 1 + subResult);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    /**
     * 解法二：带备忘录的递归
     * 很显然「备忘录」大大减小了子问题数目，完全消除了子问题的冗余
     * 所以子问题总数不会超过金额数 n，即子问题数目为 O(n)。
     * 处理一个子问题的时间不变，仍是 O(k)，所以总的时间复杂度是 O(kn)。
     */
    private static int[] mem;

    public static int coinChange2(int[] coins, int amount) {
        mem = new int[amount + 1];
        return dp(coins, amount);
    }

    /**
     * dp(n) 的定义：输入一个目标金额 amount，返回凑出目标金额 amount 的最少硬币数量。
     * amount 为状态
     */
    private static int dp(int[] coins, int amount) {
        // base case
        if (amount == 0) return 0;
        if (amount < 0) return -1;
        // 如果备忘录已经存在 直接返回
        if (mem[amount] != 0) return mem[amount];
        // 求最小值，所以初始化为正无穷
        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            int subResult = dp(coins, amount - coin);
            // 子问题无解，跳过
            if (subResult == -1) {
                continue;
            }
            res = Math.min(res, 1 + subResult);
        }
        // 更新备忘录
        mem[amount] = res == Integer.MAX_VALUE ? -1 : res;
        return mem[amount];
    }

    /**
     * 解法三：动态规划 自底向上
     * <p>
     * 时间复杂度：O(Sn)，其中 S 是金额，n 是面额数。
     * 我们一共需要计算 O(S) 个状态，S 为题目所给的总金额。
     * 对于每个状态，每次需要枚举 n 个面额来转移状态，所以一共需要 O(Sn) 的时间复杂度。
     * 空间复杂度：O(S)。DP 数组需要开长度为总金额 S 的空间。
     * <p>
     * 为啥 dp 数组初始化为 amount + 1 呢，
     * 因为凑成 amount 金额的硬币数最多只可能等于 amount（全用 1 元面值的硬币）
     * 所以初始化为 amount + 1 就相当于初始化为正无穷，便于后续取最小值。
     */
    public static int coinChange3(int[] coins, int amount) {
        if (amount == 0) return 0;
        // dp 数组的定义：当目标金额为 i 时，至少需要 dp[i] 枚硬币凑出。
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        // base case
        dp[0] = 0;
        // 外层 for 循环在遍历所有状态的所有取值
        for (int i = 1; i <= amount; i++)
            // 内层 for 循环在求所有选择的最小值
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    /**
     * 解法四：贪心+dfs+剪枝 leectode 最快的题解
     */
    private static int res = Integer.MAX_VALUE;
    public static int coinChange4(int[] coins, int amount) {
        Arrays.sort(coins);
        helper(coins, coins.length - 1, amount, 0);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private static void helper(int[] coins, int coLen, int amount, int count) {
        if (coLen < 0) return;
        for (int j = amount / coins[coLen]; j > -1; j--) {
            int extra = amount - j * coins[coLen];
            if (extra == 0) {
                res = Math.min(res, j + count);
                break;
            }
            if (j + count + 1 >= res) break;
            helper(coins, coLen - 1, extra, j + count);
        }
    }


    public static void main(String[] args) {

        int[] coins = {186, 419, 83, 408};
        int amount = 6249;
        System.out.println(coinChange4(coins, amount));
        System.out.println(coinChange3(coins, amount));
        System.out.println(coinChange2(coins, amount));
//        System.out.println(coinChange(coins, amount));
    }
}
