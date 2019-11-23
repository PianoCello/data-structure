package com.hzu.leetcode;

/**
 * 无重复字符的最长子串:
 * <p>
 * 给定一个字符串，请你找出其中不含有重复字符的最长子串的长度。
 * <p>
 * 示例 1:
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * <p>
 * 示例 2:
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *
 * @author zhanghuihong
 * @since 2019-11-22
 */
public class LeetCode_003_Longest_SubString_Without_Repeating_Characters {

    public static void main(String[] args) {
        String  s = "asasas";
        int length = lengthOfLongestSubstring(s);

        System.out.println(length);
    }

    public static int lengthOfLongestSubstring(String s) {
        //最长的长度
        int max = 1;
        //每次的长度
        int len = 1;

        char[] chars = s.toCharArray();
        //基准点
        int pivot = 0;
        for (int i = 1; i < chars.length; i++) {

            if (chars[pivot] != chars[i]) {
                len++;
            } else {
                max = Math.max(max, len);
                pivot++;
                len = 1;
            }
        }
        max = Math.max(max, len);
        return max;
    }
}
