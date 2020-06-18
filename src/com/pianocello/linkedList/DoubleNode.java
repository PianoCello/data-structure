package com.pianocello.linkedList;

/**
 * 数据结构之双链表(循环)
 *
 * @author PianoCello
 * @date 2019-08-11
 */
public class DoubleNode {

    //上一个节点
    private DoubleNode pre = this;
    //下一个节点
    private DoubleNode next = this;
    //节点数据
    private int data;

    public DoubleNode(int data) {
        this.data = data;
    }

    //获取节点数据
    public int getData() {
        return data;
    }

    //在当前节点之后增加节点
    public void after(DoubleNode newNode) {
        //当前节点的下一个节点的前节点赋给新节点
        this.next.pre = newNode;
        //当前节点的下一个节点赋给新节点的下一个节点
        newNode.next = this.next;

        //新节点的前节点赋给当前节点
        newNode.pre = this;
        //将新节点赋给当前节点的下一个节点
        this.next = newNode;
    }

    //获取下一个节点
    public DoubleNode next() {
        return this.next;
    }

    //获取上一个节点
    public DoubleNode pre() {
        return this.pre;
    }

}
