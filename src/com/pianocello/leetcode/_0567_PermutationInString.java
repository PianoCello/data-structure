package com.pianocello.leetcode;

/**
 * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
 * 换句话说，第一个字符串的排列之一是第二个字符串的子串。
 * <p>
 * 示例 1:
 * 输入: s1 = "ab" s2 = "eidbaooo"
 * 输出: True
 * 解释: s2 包含 s1 的排列之一 ("ba").
 * <p>
 * 示例 2:
 * 输入: s1= "ab" s2 = "eidboaoo"
 * 输出: False
 * <p>
 * 注意：
 * 输入的字符串只包含小写字母
 * 两个字符串的长度都在 [1, 10,000] 之间
 *
 * @author PianoCello
 * @date 2020-08-28
 */
public class _0567_PermutationInString {

    /**
     * 解法一：滑动窗口
     */
    public boolean checkInclusion(String s1, String s2) {
        // 不存在的情况
        if (s1.length() > s2.length()) return false;
        char[] target = s2.toCharArray();
        int targetLen = target.length;
        // 对 s1 进行计数
        int[] need = new int[123];
        int size = 0;
        for (char c : s1.toCharArray()) {
            if (need[c] == 0) size++;
            need[c]++;
        }
        // 窗口的左右指针
        int left = 0, right = 0;
        // 多少个字符匹配上了
        int match = 0;
        // 用于临时存储窗口内的字符
        int[] window = new int[123];
        while (right < targetLen) {
            char ri = target[right];
            right++;
            // 包含这个字符 窗口右移
            if (need[ri] != 0) {
                window[ri]++;
                if (window[ri] == need[ri]) {
                    match++;
                }
            }
            // 判断左侧窗口是否要收缩
            while (right - left >= s1.length()) {
                // 满足条件了 返回结果
                if (match == size) return true;
                char le = target[left];
                left++;
                // 缩小窗口
                if (need[le] != 0) {
                    if (window[le] == need[le]) {
                        match--;
                    }
                    window[le]--;
                }
            }
        }
        return false;
    }

}
