package com.pianocello.leetcode;

/**
 * 实现 strStr() 函数。
 * <p>
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。
 * 如果不存在，则返回  -1。
 * <p>
 * 示例 1:
 * 输入: haystack = "hello", needle = "ll"
 * 输出: 2
 *
 * @author PianoCello
 * @date 2020-07-05
 */
public class _0028_Implement_strStr {

    /**
     * KMP 算法实现
     */
    public int strStr(String haystack, String needle) {
        if (needle.equals("")) {
            return 0;
        }
        int[] next = buildNext(needle);

        int strLen = haystack.length();
        int pLen = needle.length();
        int i = 0, j = 0;
        while (i < strLen && j < pLen) {
            if (j < 0 || haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }

        if (j == pLen) {
            return i - j;
        }
        return -1;
    }

    /**
     * 构建 next 表
     */
    private int[] buildNext(String needle) {
        int pLen = needle.length();
        int[] next = new int[pLen];

        int i = next[0] = -1;
        int j = 0;
        while (j < pLen - 1) {
            if (i < 0 || needle.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
                next[j] = needle.charAt(i) != needle.charAt(j) ? i : next[i];
            } else {
                i = next[i];
            }
        }
        return next;
    }

}
