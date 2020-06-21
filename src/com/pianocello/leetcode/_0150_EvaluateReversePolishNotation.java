package com.pianocello.leetcode;

import java.util.Stack;

/**
 * 根据 逆波兰表示法，求表达式的值。
 * 有效的运算符包括 +, -, *, / 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
 * <p>
 * 逆波兰表达式：
 * 逆波兰表达式是一种后缀表达式，所谓后缀就是指算符写在后面。
 * 平常使用的算式则是一种中缀表达式，如 ( 1 + 2 ) * ( 3 + 4 ) 。
 * 该算式的逆波兰表达式写法为 ( ( 1 2 + ) ( 3 4 + ) * ) 。
 * <p>
 * 逆波兰表达式主要有以下两个优点：
 * 去掉括号后表达式无歧义，上式即便写成 1 2 + 3 4 + * 也可以依据次序计算出正确结果。
 * 适合用栈操作运算：遇到数字则入栈；遇到算符则取出栈顶两个数字进行计算，并将结果压入栈中。
 * <p>
 * 说明：
 * 整数除法只保留整数部分。
 * 给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
 * <p>
 * 举例：
 * 输入: ["4", "13", "5", "/", "+"]
 * 输出: 6
 * 解释: 该算式转化为常见的中缀算术表达式为：(4 + (13 / 5)) = 6
 *
 * @author PianoCello
 * @date 2020-06-18
 */
public class _0150_EvaluateReversePolishNotation {

    /**
     * 用栈实现
     */
    public static int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        Integer operand2;
        Integer operand;

        for (String token : tokens) {
            switch (token) {
                case "+":
                    operand2 = stack.pop();
                    operand = stack.pop();
                    stack.push(operand + operand2);
                    break;
                case "-":
                    operand2 = stack.pop();
                    operand = stack.pop();
                    stack.push(operand - operand2);
                    break;
                case "*":
                    operand2 = stack.pop();
                    operand = stack.pop();
                    stack.push(operand * operand2);
                    break;
                case "/":
                    operand2 = stack.pop();
                    operand = stack.pop();
                    stack.push(operand / operand2);
                    break;
                default:
                    stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }

    /**
     * 纯数组模拟栈实现(推荐)
     * <p>
     * 其他 Java 相关优化操作：
     * 1. 数组最大长度为 tokens.length / 2 + 1
     * 2. switch代替 if-else，效率优化
     * 3. Integer.parseInt 代替 Integer.valueOf ,减少自动拆箱装箱操作
     */
    public static int evalRPN2(String[] tokens) {
        int[] stack = new int[tokens.length / 2 + 1];
        int index = 0;
        for (String s : tokens) {
            switch (s) {
                case "+":
                    stack[index - 2] += stack[--index];
                    break;
                case "-":
                    stack[index - 2] -= stack[--index];
                    break;
                case "*":
                    stack[index - 2] *= stack[--index];
                    break;
                case "/":
                    stack[index - 2] /= stack[--index];
                    break;
                default:
                    stack[index++] = Integer.parseInt(s);
            }
        }
        return stack[0];
    }

    public static void main(String[] args) {
        String[] tokens = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        int rpn = evalRPN(tokens);
        System.out.println(rpn);
    }
}