package com.pianocello.leetcode;

import java.util.Stack;

/**
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 *
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 *
 * 示例 1：
 * 输入：s = "3[a]2[bc]"   （队列）
 * 输出："aaabcbc"
 *
 * 示例 2：
 * 输入：s = "3[a2[c]]"    (栈)
 * 输出："accaccacc"
 *
 * @author PianoCello
 * @date 2020-06-25
 */
public class _0394_DecodeString {

    public static String decodeString(String s) {
        StringBuilder sb = new StringBuilder();

        char[] chars = s.toCharArray();
        Stack<Integer> s1 = new Stack<>();
        Stack<String> s2 = new Stack<>();
        // 3[a2[c2[s]]2[d]]
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];

            if (c == '[') {

            }else if(c == ']'){

            }else if (c >= '0' && c <= '9') {

            } else {

            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {

        String s= "3[a2[c]]";
        String decodeStr = decodeString(s);
        System.out.println(decodeStr);

    }
}
