package com.pianocello.leetcode;

/**
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * <p>
 * 示例 1:
 * 输入: [7,1,5,3,6,4]
 * 输出: 7
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
 *
 * @author PianoCello
 * @date 2020-07-29
 */
public class _0122_BestTimeToBuyAndSellStockII {

    /**
     * 解法一：峰谷法
     * 买入: 后一天比今天大就买入
     * 卖出: 后一天比今天小就卖出
     */
    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) return 0;
        // 最大利润
        int maxProfit = 0;
        // 是否已买入
        boolean flag = false;
        //买入的索引
        int index = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            if (flag) {
                if (prices[i] > prices[i + 1]) {
                    // 卖出
                    flag = false;
                    maxProfit += prices[i] - prices[index];
                }
            } else {
                if (prices[i + 1] > prices[i]) {
                    // 买入
                    flag = true;
                    index = i;
                }
            }
        }
        // 最后一个数
        if (flag) {
            maxProfit += prices[prices.length - 1] - prices[index];
        }
        return maxProfit;
    }

    public static void main(String[] args) {

        int[] prices = {7, 3, 2, 4, 5, 6, 5, 8};
//        int[] prices = {7, 1, 5, 3, 6, 4,5};
//        int[] prices = {1, 8, 91};
        int profit = maxProfit(prices);
        System.out.println(profit);

    }
}
