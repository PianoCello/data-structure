package com.pianocello.leetcode;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * <p>
 * 示例 1：
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * <p>
 * 示例 2：
 * 输入: "cbbd"
 * 输出: "bb"
 *
 * @author PianoCello
 * @date 2020-07-04
 */
public class _0005_LongestPalindromicSubstring {

    /**
     * 解法一：中心扩散法
     * 从左向右遍历字符串 对于每个字符从中心向两边扩散遍历
     * 时间复杂度：O(n^2)，其中 n 是字符串的长度。
     * 长度为 1 和 2 的回文中心分别有 n 和 n-1 个，每个回文中心最多会向外扩展 O(n) 次。
     * 空间复杂度：O(1)。
     */
    public static String longestPalindrome(String str) {
        if (str == null || str.length() <= 1) return str;
        //最大的回文串的长度
        int maxLen = 1;
        //回文串的起点
        int begin = 0;

        int length = str.length();
        char[] chars = str.toCharArray();
        for (int i = 0; i < length - 1; i++) {
            //计算奇数时长度
            int oddMax = getMaxLen(chars, i, i);
            //计算偶数时的长度
            int evenMax = getMaxLen(chars, i, i + 1);
            int max = Math.max(oddMax, evenMax);
            if (max > maxLen) {
                maxLen = max;
                begin = i - (max-1) / 2;
            }
        }
        //因为截取字符串性能消耗比较大，所以只记录起点和终点坐标，最后才截取返回值
        return str.substring(begin, begin + maxLen);
    }

    private static int getMaxLen(char[] chars, int left, int right) {
        int max = 1;

        if (chars[left] != chars[right]) {
            return max;
        }
        //左右不相等 说明是偶数
        if (left != right) {
            max = 2;
        }

        int length = chars.length;
        while (--left >= 0 && ++right < length
                && chars[left] == chars[right]) {
            max = right - left + 1;
        }
        return max;
    }

    public static void main(String[] args) {

//        String str = "cbabbs";
//        String str = "cbbd";
//        String str = "abbbbbbbab";
        String str = "aa";

        String s = longestPalindrome(str);
        System.out.println(s);
    }
}
