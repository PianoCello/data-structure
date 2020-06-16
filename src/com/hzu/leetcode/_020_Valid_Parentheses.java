package com.hzu.leetcode;

import java.util.Stack;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 *
 * @author PianoCello
 * @date 2020-06-17
 */
class Solution {
    public boolean isValid(String s) {
        if (s == null || s.isEmpty()) {
            return true;
        }
        //() {} []
        //存放字符的栈
        Stack<Character> stack = new Stack<>();

        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            //当前遍历的是右括号
            if (isRight(chars[i])) {
                if (stack.size() == 0 || stack.pop() != getLeft(chars[i])) {
                    return false;
                }
            } else {
                //当前遍历的是左括号
                stack.push(chars[i]);
            }
        }

        //当括号全部有效的时候 栈应该是空的
        if (stack.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    //判断字符是否是右括号
    private boolean isRight(char c) {
        return c == ')' || c == ']' || c == '}';
    }

    //获取右括号对应的左括号
    private char getLeft(char c) {
        if (c == ')') {
            return '(';
        } else if (c == ']') {
            return '[';
        } else {
            return '{';
        }
    }

    public static void main(String[] args) {

        Solution solution = new Solution();

        boolean valid = solution.isValid("(((({}{}{}))))");
        System.out.println(valid);

    }
}
