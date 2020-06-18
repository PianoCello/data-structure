package com.pianocello.leetcode;

import java.util.HashSet;
import java.util.Set;

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
 * @author PianoCello
 * @date 2019-11-22
 */
public class _0003_Longest_SubString_Without_Repeating_Characters {

    public static void main(String[] args) {
        String s = "abcacsefa";
//        int length = lengthOfLongestSubstring(s);
        int length2 = lengthOfLongestSubstring2(s);

//        System.out.println(length);
        System.out.println(length2);
    }


    // a b c s f s d z a b c a a s a

    /**
     * 滑动窗口法
     */
    public static int lengthOfLongestSubstring(String s) {
        if ("".equals(s)) {
            return 0;
        }
        //最长的长度
        int max = 1;
        char[] chars = s.toCharArray();

        for (int i = 0; i < chars.length; i++) {

            Set<Character> set = new HashSet<>();
            set.add(chars[i]);

            for (int j = i + 1; j < chars.length; j++) {

                if (set.contains(chars[j])) {
                    break;
                }

                set.add(chars[j]);
            }

            max = Math.max(max, set.size());
        }

        return max;
    }

    /**
     * 优化的滑动窗口法
     */
    public static int lengthOfLongestSubstring2(String s) {
        if (s == null) {
            return 0;
        }
        if (s.length() <= 1) {
            return s.length();
        }

        char[] chars = s.toCharArray();
        int lastIndex = 0;
        int maxLen = 1;
        int tempLen = 0;

        //外层循环
        for (int i = 1; i < s.length(); i++) {

            //
            for (int j = lastIndex; j < i; j++) {
                if (chars[j] == chars[i]) {
                    lastIndex = j + 1;
                    break;
                }
            }

            tempLen = i - lastIndex + 1;
            if (tempLen > maxLen) {
                maxLen = tempLen;
            }

        }

        return maxLen;
    }

}
