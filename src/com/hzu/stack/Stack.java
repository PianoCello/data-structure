package com.hzu.stack;

import java.util.Arrays;

/** 数据结构之栈
 * @author PianoCello
 * @date 2019-08-10
 */
public class Stack {

    //存放数据元素的底层结构
    private int[] elements;


    public Stack() {
        this.elements = new int[0];
    }

    //压入一个元素
    public void push(int element) {
        //创建新的栈
        int[] newStack = new int[elements.length+1];
        for (int i = 0; i < elements.length; i++) {
            newStack[i] = elements[i];
        }
        newStack[elements.length] = element;
        //将新的数组替换旧的数组
        elements = newStack;
    }

    //弹出栈顶元素
    public int pop() {
        //栈为空
        if (elements.length==0) {
            throw new RuntimeException("栈为空,不可再弹出元素");
        }
        int[] newStack = new int[elements.length-1];
        for (int i = 0; i < newStack.length; i++) {
            newStack[i] = elements[i];
        }
        //取出栈顶元素
        int el = elements[elements.length - 1];
        //将新的数组替换旧的数组
        elements = newStack;
        return el;
    }

    //获取栈顶元素
    public int top() {
        //栈为空
        if (elements.length==0) {
            throw new RuntimeException("栈为空");
        }
        //取出栈顶元素
        int el = elements[elements.length - 1];
        return el;
    }

    //判断栈是否为空
    public boolean isEmpty() {
        return elements.length == 0;
    }

    //打印栈元素
    @Override
    public String toString() {
        return "Stack:" + Arrays.toString(elements);
    }
}
