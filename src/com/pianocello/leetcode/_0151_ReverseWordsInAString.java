package com.pianocello.leetcode;

import java.util.*;

/**
 * 给定一个字符串，逐个翻转字符串中的每个单词。
 * <p>
 * 示例 1：
 * 输入: "the sky is blue"
 * 输出: "blue is sky the"
 *
 * @author PianoCello
 * @date 2020-07-04
 */
public class _0151_ReverseWordsInAString {

    /**
     * 方法一：使用语言特性
     * 使用 split 将字符串按空格分割成字符串数组；
     * 使用 reverse 将字符串数组进行反转；
     * 使用 join 方法将字符串数组拼成一个字符串。
     * <p>
     * 时间复杂度：O(N)，其中 N 为输入字符串的长度。
     * 空间复杂度：O(N)，用来存储字符串分割之后的结果。
     */
    public static String reverseWords(String str) {
        // 除去开头和末尾的空白字符
        str = str.trim();
        // 正则匹配连续的空白字符作为分隔符分割
        List<String> wordList = Arrays.asList(str.split("\\s+"));
        Collections.reverse(wordList);
        return String.join(" ", wordList);
    }

    /**
     * 方法二：用栈实现
     * <p>
     * 时间复杂度：O(N)，其中 N 为输入字符串的长度。
     * 空间复杂度：O(N)，用来存储字符串分割之后的结果。
     */
    public static String reverseWords2(String str) {
        int length = str.length();
        //双指针
        int x, y;
        //临时存储字符串
        Deque<String> deque = new ArrayDeque<>();

        int i = 0;
        while (i < length) {
            //如果当前字符串不是空格
            if (!Character.isSpaceChar(str.charAt(i))) {
                x = i;
                y = i + 1;
                while (y < length && !Character.isSpaceChar(str.charAt(y))) {
                    y++;
                }
                deque.offerFirst(str.substring(x, y));
                i = y;
            }
            i++;
        }
        return String.join(" ", deque);
    }

    public static void main(String[] args) {

//        String str = "  hello world!  ";
        String str = "the sky is blue";
//        String str = " ";

        String s = reverseWords2(str);
        System.out.println(s);

    }

}
