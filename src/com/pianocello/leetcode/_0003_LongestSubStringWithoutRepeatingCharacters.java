package com.pianocello.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 无重复字符的最长子串:
 * 给定一个字符串，请你找出其中不含有重复字符的最长子串的长度。
 * <p>
 * 示例 1:
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
public class _0003_LongestSubStringWithoutRepeatingCharacters {

    /**
     * 解法一：从左往右逐一求解
     */
    public static int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        char[] chars = s.toCharArray();
        int len = chars.length;
        int num = 0;
        for (int i = 0; i < len; i++) {
            int j = i;
            while (j < len && !set.contains(chars[j])) {
                set.add(chars[j++]);
            }
            num = Math.max(num, set.size());
            set.clear();
        }
        return num;
    }

    /**
     * 解法二：双指针 滑动窗口
     */
    public static int lengthOfLongestSubstring2(String s) {
        if (s.length() <= 1)
            return s.length();

        int res = 1;
        char[] chars = s.toCharArray();
        int len = chars.length;
        int last = 0;
        //外层循环是右指针
        for (int i = 1; i < len; i++) {
            //内层循环是左指针 从上次相同的下一位开始
            for (int j = last; j < i; j++) {
                if (chars[j] == chars[i]) {
                    last = j + 1;
                    break;
                }
            }
            res = Math.max(res, i - last + 1);
        }
        return res;
    }

    /**
     * 解法三： 滑动窗口 自己实现的
     *
     */
    public static int lengthOfLongestSubstring3(String source) {
        char[] chars = source.toCharArray();
        // 窗口的左右指针
        int left = 0, right = 0;
        int res = 0;
        // 窗口
        int[] window = new int[127];
        while (right < chars.length) {
            char c = chars[right];
            right++;
            window[c]++;
            // 判断左侧窗口是否要收缩
            while (window[c] > 1) {
                char d = chars[left];
                left++;
                window[d]--;
            }
            // 更新结果
            res = Math.max(res, right - left);
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "aassddfccc";
        int length = lengthOfLongestSubstring2(s);
        System.out.println(length);
        int length2 = lengthOfLongestSubstring3(s);
        System.out.println(length2);
    }

}
