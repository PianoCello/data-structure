package com.pianocello.leetcode;

import java.util.Stack;

/**
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 *
 * push(x) —— 将元素 x 推入栈中。
 * pop() —— 删除栈顶的元素。
 * top() —— 获取栈顶元素。
 * getMin() —— 检索栈中的最小元素。
 *
 * 解法一：链表实现
 * 最小栈 每一次压栈都会带有当前栈中的最小值
 */
public class _0155_MinStack {
    private Node head;

    /**
     * initialize your data structure here.
     */
    public _0155_MinStack() {

    }

    public void push(int x) {
        if (head == null) {
            head = new Node(x, x);
        } else {
            head = new Node(x, Math.min(x, head.min), head);
        }

    }

    public void pop() {
        head = head.next;
    }

    public int top() {
        return head.val;
    }

    public int getMin() {
        return head.min;
    }

    private class Node {
        int val;
        int min;
        Node next;

        private Node(int val, int min) {
            this(val, min, null);
        }

        private Node(int val, int min, Node next) {
            this.val = val;
            this.min = min;
            this.next = next;
        }
    }
}

/**
 * 解法二：数组实现
 */
class MinStack {

    private Stack<Integer> stack;
    //使用一个变量存储最小的元素
    private int min = Integer.MAX_VALUE;

    /**
     * initialize your data structure here.
     */
    public MinStack() {
        stack = new Stack<>();
    }

    //加入元素
    public void push(int x) {
        //如果新加入的元素 <= min
        //将原来的 min 也一起加入栈中
        if (x <= min) {
            stack.push(min);
            min = x;
        }
        stack.push(x);
    }

    //弹出栈顶元素
    public void pop() {
        //如果获取的是最小的元素
        //那么还需要再弹出一次恢复原样
        if (stack.pop() == min) {
            min = stack.pop();
        }
    }

    //获取栈顶元素
    public int top() {
        return stack.peek();
    }

    //获取栈中最小的元素
    public int getMin() {
        return min;
    }
}