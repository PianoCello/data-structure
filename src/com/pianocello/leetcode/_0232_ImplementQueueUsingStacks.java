package com.pianocello.leetcode;

import java.util.Stack;

/**
 * 使用栈实现队列的下列操作：
 *
 * push(x) -- 将一个元素放入队列的尾部。
 * pop() -- 从队列首部移除元素。
 * peek() -- 返回队列首部的元素。
 * empty() -- 返回队列是否为空。
 *
 * @author PianoCello
 * @date 2020-06-25
 */
class MyQueue {

    private Stack<Integer> s1 = new Stack<>();
    private Stack<Integer> s2 = new Stack<>();
    private int front;

    public MyQueue() {
    }

    // 使用两个栈 入队 - O(1)，出队 - 摊还复杂度 O(1)
    public void push(int x) {
        if (s1.isEmpty()) {
            front = x;
        }
        s1.push(x);
    }

    public int pop() {
        if (s2.isEmpty()) {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }
        return s2.pop();
    }

    public int peek() {
        if (!s2.isEmpty()) {
            return s2.peek();
        }
        return front;
    }

    public boolean empty() {
        return s1.isEmpty() && s2.isEmpty();
    }

    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue();
        myQueue.push(1);
        myQueue.push(2);

        myQueue.peek();
        myQueue.pop();
        myQueue.empty();
    }
}
