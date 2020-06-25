package com.pianocello.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 使用队列实现栈的下列操作：
 * <p>
 * push(x) -- 元素 x 入栈
 * pop() -- 移除栈顶元素
 * top() -- 获取栈顶元素
 * empty() -- 返回栈是否为空
 *
 * @author PianoCello
 * @date 2020-06-25
 */
class MyStack {

    private Queue<Integer> q1 = new LinkedList<>();
    private Queue<Integer> q2 = new LinkedList<>();
    private int top;

    public MyStack() {

    }

    public void push(int x) {
        q1.offer(x);
        top = x;
    }

    public int pop() {
        if (q2.isEmpty()) {
            Integer[] integers = new Integer[q1.size()];
            for (int i = 0; i < integers.length; i++) {
                integers[i] = q1.poll();
            }
            for (int i = integers.length - 1; i >= 0; i--) {
                q2.offer(integers[i]);
            }
        }
        return q2.poll();
    }

    public int top() {
        if (!q1.isEmpty()) {
            return top;
        }
        return q2.peek();
    }

    public boolean empty() {
        return q1.isEmpty() && q2.isEmpty();
    }

    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        myStack.push(1);
        myStack.push(2);

        myStack.top();
        myStack.pop();

        myStack.empty();
    }
}
