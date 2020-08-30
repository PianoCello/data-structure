package com.pianocello.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
 * 字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。
 *
 * 说明：
 * 字母异位词指字母相同，但排列不同的字符串。
 * 不考虑答案输出的顺序。
 * 
 * 示例 1:
 * 输入:
 * s: "cbaebabacd" p: "abc"
 * 输出:
 * [0, 6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
 *
 * @author PianoCello
 * @date 2020-08-29
 */
public class _0438_FindAllAnagramsInAString {

    /**
     * 解法一：滑动窗口
     *
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (p.length() > s.length()) return res;

        int[] need = new int[26];
        int size = 0;
        for (char c : p.toCharArray()) {
            if (need[c - 'a'] == 0)
                size++;
            need[c - 'a']++;
        }

        char[] source = s.toCharArray();
        // 窗口的左右指针
        int left = 0, right = 0;
        // 匹配上的字符数
        int match = 0;
        int[] window = new int[26];
        while (right < source.length) {
            // 窗口向右滑动
            char rChar = source[right];
            right++;
            // 窗口内数据的一系列更新
            if (need[rChar - 'a'] != 0) {
                window[rChar - 'a']++;
                if (window[rChar - 'a'] == need[rChar - 'a']) {
                    match++;
                }
            }
            // 判断是否需要缩小窗口
            while (right - left >= p.length()) {
                // 更新答案
                if (match == size) {
                    res.add(left);
                }
                char lChar = source[left];
                left++;
                if (window[lChar - 'a'] != 0) {
                    if (window[lChar - 'a'] == need[lChar - 'a']) {
                        match--;
                    }
                    window[lChar - 'a']--;
                }
            }
        }
        return res;
    }

}
