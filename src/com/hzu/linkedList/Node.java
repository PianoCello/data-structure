package com.hzu.linkedList;

/**
 * 数据结构之单链表
 *
 * @author zhanghuihong
 * @since 2019-08-11
 */
public class Node {

    private int data;
    private Node next;

    public Node(int data) {
        this.data = data;
    }

    //在最后追加节点
    public Node append(Node node) {
        //当前节点
        Node currentNode = this;
        //循环向下找最后一个节点
        while (true) {
            Node nextNode = currentNode.next;
            //如果下一个是空,说明这是最后一个节点了
            if (nextNode == null) {
                break;
            }
            //将找出的节点赋给当前节点
            currentNode = nextNode;
        }
        //将需要追加的节点赋给最后一个节点的next
        currentNode.next = node;
        return this;
    }

    //在任意位置追加节点
    public void after(Node node) {
        //将下个节点赋给新加入的节点的下个节点
        node.next = this.next;
        //将新加入的节点赋给当前节点的下个节点
        this.next = node;
    }

    //删除下一个节点
    public void removeNext() {
        if (next == null) {
            throw new RuntimeException("当前节点不存在下一个节点");
        }
        //将下下个节点赋给当前节点的下个节点
        this.next = this.next.next;
    }

    //获取下一个节点
    public Node next() {
        return this.next;
    }

    //获取节点的数据
    public int getData() {
        return this.data;
    }

    //判断是否为最后一个节点
    public boolean isLast() {
        return next == null;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", next=" + next +
                '}';
    }
}
