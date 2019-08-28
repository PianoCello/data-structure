package com.hzu.queue;

import java.util.Arrays;

/**
 * 数据结构之队列
 *
 * @author zhanghuihong
 * @since 2019-08-10
 */
public class Queue {

    private int[] elements;

    public Queue() {
        this.elements = new int[0];
    }

    //队尾入队
    public void add(int element) {
        //创建新的队列
        int[] newQueue = new int[elements.length + 1];
        for (int i = 0; i < elements.length; i++) {
            newQueue[i] = elements[i];
        }
        newQueue[elements.length] = element;
        //将新的数组替换旧的数组
        elements = newQueue;
    }

    //队头出队
    public int poll() {
        //队列为空
        if (elements.length == 0) {
            throw new RuntimeException("队列为空");
        }
        int[] newQueue = new int[elements.length - 1];
        for (int i = 0; i < newQueue.length; i++) {
            newQueue[i] = elements[i + 1];
        }
        //取出队头元素
        int el = elements[0];
        //将新的数组替换旧的数组
        elements = newQueue;
        return el;
    }

    //获取队头元素
    public int peek() {
        //队列为空
        if (elements.length == 0) {
            throw new RuntimeException("队列为空");
        }
        //获取队头元素
        int el = elements[0];
        return el;
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return elements.length == 0;
    }

    //打印队列元素
    @Override
    public String toString() {
        return "Queue:" + Arrays.toString(elements);
    }
}
