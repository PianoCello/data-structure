package com.pianocello.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个字符串 S、一个字符串 T 。
 * 设计一种算法，可以在 O(n) 的时间复杂度内，从字符串 S 里面找出：包含 T 所有字符的最小子串。
 * <p>
 * 示例：
 * 输入：S = "ADOBECODEBANC", T = "ABC"
 * 输出："BANC"
 * <p>
 * 提示：
 * 如果 S 中不存这样的子串，则返回空字符串 ""。
 * 如果 S 中存在这样的子串，我们保证它是唯一的答案。
 *
 * @author PianoCello
 * @date 2020-08-27
 */
public class _0076_MinimumWindowSubstring {

    /**
     * 解法一：滑动窗口
     */
    public String minWindow(String source, String target) {
        // 表示需要的字符以及个数
        Map<Character, Integer> needs = new HashMap<>();
        for (char c : target.toCharArray()) {
            needs.merge(c, 1, Integer::sum);
        }
        // 窗口的左右边界 （左闭右开）
        int left = 0, right = 0;
        // 窗口中满足 need 条件的字符个数
        int valid = 0;
        // 结果的起始索引和长度
        int start = 0, len = Integer.MAX_VALUE;
        int[] window = new int[127];
        while (right < source.length()) {
            // 将要加入窗口的字符
            char rChar = source.charAt(right);
            right++;
            // 进行窗口内数据的一系列更新
            if (needs.containsKey(rChar)) {
                window[rChar]++;
                // 满足 need 条件
                if (window[rChar] == needs.get(rChar)) {
                    valid++;
                }
            }
            // 判断左侧窗口是否要收缩
            while (valid == needs.size()) {
                // 更新答案
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }
                char lChar = source.charAt(left);
                left++;
                if (needs.containsKey(lChar)) {
                    if (window[lChar] == needs.get(lChar)) {
                        valid--;
                    }
                    window[lChar]--;
                }
            }
        }
        return len == Integer.MAX_VALUE ? "" : source.substring(start, start + len);
    }

}
