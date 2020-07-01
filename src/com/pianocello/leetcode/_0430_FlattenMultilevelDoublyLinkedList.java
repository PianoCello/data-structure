package com.pianocello.leetcode;

/**
 * 多级双向链表中，除了指向下一个节点和前一个节点指针之外，它还有一个子链表指针，可能指向单独的双向链表。
 * 这些子列表也可能会有一个或多个自己的子项，依此类推，生成多级数据结构，如下面的示例所示。
 * 给你位于列表第一级的头节点，请你扁平化列表，使所有结点出现在单级双链表中。
 * <p>
 * 示例 1：
 * 输入：head = [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
 * 输出：[1,2,3,7,8,11,12,9,10,4,5,6]
 *
 * @author PianoCello
 * @date 2020-06-29
 */
public class _0430_FlattenMultilevelDoublyLinkedList {

    private static class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, Node prev, Node next, Node child) {
            this.val = val;
            this.prev = prev;
            this.next = next;
            this.child = child;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    ", next=" + next +
                    ", child=" + child +
                    '}';
        }
    }

    /**
     * 解法一：递归
     * 时间复杂度：O(N)。
     * 空间复杂度：O(N)。
     */
    public static Node flatten(Node head) {
        if (head == null) {
            return head;
        }
        Node sentinel = head;

        doFlatten(head);

        return sentinel;
    }

    public static Node doFlatten(Node head) {
        while (head.child != null || head.next != null) {
            //把下一个节点找出来备用
            Node curNext = head.next;
            //先处理子链表
            if (head.child != null) {
                Node child = head.child;
                head.next = child;
                child.prev = head;
                head.child = null;
                head = head.next;

                head = doFlatten(head);
            }
            //再处理后继节点
            if (curNext != null) {
                head.next = curNext;
                curNext.prev = head;
                head = head.next;
            }
        }
        return head;
    }

    /**
     * 解法二：前序遍历
     * 将链表顺时针旋转 90 度，可以看似一棵二叉树，然后使用前序遍历
     * 时间复杂度：O(N)。N 指的是列表的节点数，深度优先搜索遍历每个节点一次。
     * 空间复杂度：O(N)，N 指的是列表的节点数，二叉树很可能不是个平衡的二叉树，
     * 若节点仅通过 child 指针相互链接，则在递归调用的过程中堆栈的深度会达到 N。
     */
    public static Node flatten2(Node head) {
        if (head == null) {
            return null;
        }
        Node sentinel = head;

        doFlatten2(head);

        return sentinel;
    }

    public static Node doFlatten2(Node head) {
        //临时存储右节点
        Node curNext = head.next;

        //处理左节点
        if (head.child != null) {
            Node child = head.child;
            head.next = child;
            child.prev = head;
            head.child = null;
            head = head.next;

            head = doFlatten2(head);
        }
        //处理左节点
        if (curNext != null) {
            head.next = curNext;
            curNext.prev = head;
            head = head.next;

            head = doFlatten2(head);
        }
        return head;
    }


    public static void main(String[] args) {

        Node head = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);

        Node node7 = new Node(7);
        Node node8 = new Node(8);

        Node node11 = new Node(11);

        head.next = node2;
        node2.prev = head;

        node2.next = node3;
        node3.prev = node2;

        node3.next = node4;
        node4.prev = node3;

        node4.next = node5;
        node5.prev = node4;

        node3.child = node7;

        node7.next = node8;
        node8.prev = node7;

        node8.child = node11;

        Node flatten = flatten2(head);

        System.out.println(flatten);

    }
}
