package com.pianocello.leetcode;

/**
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 * <p>
 * 示例 1:
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * <p>
 * 示例 2:
 * 输入: s = "rat", t = "car"
 * 输出: false
 *
 * @author PianoCello
 * @date 2020-08-06
 */
public class _0242_ValidAnagram {

    /**
     * 解法一：使用数组记录每个字符出现的次数
     */
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;

        int[] num = new int[26];
        for (char c : s.toCharArray()) {
            num[c - 'a']++;
        }
        for (char c : t.toCharArray()) {
            if (--num[c - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

        String s = "rat";
        String t = "car";
        System.out.println(isAnagram(s, t));
    }
}
