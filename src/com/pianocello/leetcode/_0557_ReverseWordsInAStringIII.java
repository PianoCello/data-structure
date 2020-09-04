package com.pianocello.leetcode;

/**
 * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
 * 注意：在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。
 *
 * 示例 1:
 * 输入: "Let's take LeetCode contest"
 * 输出: "s'teL ekat edoCteeL tsetnoc"
 *
 *
 * @author PianoCello
 * @date 2020-07-06
 */
public class _0557_ReverseWordsInAStringIII {

    /**
     * 解法一 ：使用双指针
     */
    public static String reverseWords(String s) {
        char[] chars = s.toCharArray();
        int i = 0;
        while (i < chars.length) {
            if (Character.isSpaceChar(chars[i])) {
                i++;
            }
            int j = i;
            while (j < chars.length && !Character.isSpaceChar(chars[j])) {
                j++;
            }
            reverseWords(chars, i, j - 1);
            i = j + 1;
        }
        return new String(chars);
    }

    /**
     * 反转从 start 到 end 的字符
     */
    private static void reverseWords(char[] chars, int start, int end) {
        while (start < end) {
            char temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        String s = "Let's take LeetCode contest";

        String s1 = reverseWords(s);
        System.out.println(s1);
    }
}
