package com.pianocello.leetcode;

import java.util.*;

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

    /**
     * 解法一：自己写的
     */
    public static String decodeString(String s) {
        char[] chars = s.toCharArray();
        Stack<Integer> s1 = new Stack<>();
        Stack<String> s2 = new Stack<>();

        int i = 0;
        while (i < chars.length) {
            char c = chars[i];
            //当前字符是数字的话 找出具体的次数k
            if (Character.isDigit(c)) {
                StringBuilder intStr = new StringBuilder("" + c);
                while (Character.isDigit(chars[++i])) {
                    intStr.append(chars[i]);
                }
                s1.push(Integer.valueOf(intStr.toString()));
            } else if (c == ']') {
                ++i;
                // 字符出栈
                Integer loop = s1.pop();
                List<String> list = new ArrayList<>();
                while (!s2.peek().equals("[")) {
                    list.add(s2.pop());
                }
                s2.pop();//把 '[' 扔了
                //将倒序存储在栈中的字符串变成正序
                StringBuilder sb = new StringBuilder();
                for (int j = list.size() - 1; j >= 0; j--) {
                    sb.append(list.get(j));
                }
                s2.push(getStr(sb.toString(), loop));
            } else if (c == '[') {
                // 字符是 '['
                s2.push(""+c);
                ++i;
            } else {
                //当前字符需要解码的字符 找出具体的字符串
                StringBuilder str = new StringBuilder("" + c);
                while (true) {
                    if (i == chars.length - 1) {
                        ++i;
                        break;
                    }
                    char c1 = chars[++i];
                    if (Character.isDigit(c1) || c1 == '[' || c1 == ']') {
                        break;
                    }
                    str.append(c1);
                }
                s2.push(str.toString());
            }
        }
        List<String> list = new ArrayList<>();
        while (!s2.isEmpty()) {
            list.add(s2.pop());
        }
        //将倒序存储在栈中的字符串变成正序
        StringBuilder sb = new StringBuilder();
        for (int j = list.size() - 1; j >= 0; j--) {
            sb.append(list.get(j));
        }
        return sb.toString();
    }

    private static String getStr(String str, Integer loop) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < loop; i++) {
            sb.append(str);
        }
        return sb.toString();
    }

    static int  ptr;
    /**
     * 解法二：LeetCode 官方题解
     */
    public static String decodeString2(String s) {
        Deque<String> stack = new LinkedList<>();
        ptr = 0;
        while (ptr < s.length()) {
            char cur = s.charAt(ptr);
            if (Character.isDigit(cur)) {
                // 获取一个数字并进栈
                String digits = getDigits(s);
                stack.addLast(digits);
            } else if (Character.isLetter(cur) || cur == '[') {
                // 获取一个字母并进栈
                stack.addLast(String.valueOf(s.charAt(ptr++)));
            } else {
                ++ptr;
                LinkedList<String> sub = new LinkedList<>();
                while (!"[".equals(stack.peekLast())) {
                    sub.addLast(stack.removeLast());
                }
                Collections.reverse(sub);
                // 左括号出栈
                stack.removeLast();
                // 此时栈顶为当前 sub 对应的字符串应该出现的次数
                int repTime = Integer.parseInt(stack.removeLast());
                StringBuffer t = new StringBuffer();
                String o = getString(sub);
                // 构造字符串
                while (repTime-- > 0) {
                    t.append(o);
                }
                // 将构造好的字符串入栈
                stack.addLast(t.toString());
            }
        }

        return getString(stack);
    }

    public static String getDigits(String s) {
        StringBuffer ret = new StringBuffer();
        while (Character.isDigit(s.charAt(ptr))) {
            ret.append(s.charAt(ptr++));
        }
        return ret.toString();
    }

    public static String getString(Deque<String> v) {
        StringBuffer ret = new StringBuffer();
        for (String s : v) {
            ret.append(s);
        }
        return ret.toString();
    }

    public static void main(String[] args) {

        String s= "2[abc]3[cd]ef";
//        String decodeStr = decodeString(s);
        String decodeStr = decodeString2(s);
        System.out.println(decodeStr);
    }
}
