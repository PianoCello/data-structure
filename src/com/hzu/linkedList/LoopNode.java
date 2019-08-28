package com.hzu.linkedList;

/**
 * 数据结构之循环链表
 *
 * @author zhanghuihong
 * @since 2019-08-11
 */
public class LoopNode {

    private int data;
    private LoopNode next = this;//只能一个节点就指向自己

    public LoopNode(int data) {
        this.data = data;
    }

    //在任意位置追加节点
    public void after(LoopNode node) {
        //将下个节点赋给新加入的节点的下个节点
        node.next = this.next;
        //将新加入的节点赋给当前节点的下个节点
        this.next = node;
    }

    //删除下一个节点
    public void removeNext() {
        //将下下个节点赋给当前节点的下个节点
        this.next = this.next.next;
    }

    //获取下一个节点
    public LoopNode next() {
        return this.next;
    }

    //获取节点的数据
    public int getData() {
        return this.data;
    }

}
