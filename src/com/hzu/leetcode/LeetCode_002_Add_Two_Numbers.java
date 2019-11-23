package com.hzu.leetcode;

/**
 * 两数相加:
 * <p>
 * 给出两个非空的链表用来表示两个非负的整数。
 * 其中，它们各自的位数是按照逆序的方式存储的，并且它们的每个节点只能存储一位数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 示例：
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 * @author zhanghuihong
 * @since 2019-11-22
 */
public class LeetCode_002_Add_Two_Numbers {

    public static void main(String[] args) {

        // 342
        ListNode listNode1 = new ListNode(3);
        ListNode listNode2 = new ListNode(4);
        ListNode listNode3 = new ListNode(2);
        listNode2.next = listNode1;
        listNode3.next = listNode2;

        // 465
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(6);
        ListNode listNode6 = new ListNode(5);
        listNode5.next = listNode4;
        listNode6.next = listNode5;

        // 807
        ListNode node = addTwoNumbers(listNode3, listNode6);
        // 7 -> 0 -> 8
        System.out.println(node);

    }


    public static ListNode addTwoNumbers(ListNode left, ListNode right) {


        return null;
    }

}

/**
 * Definition for singly-linked list.
 */
class ListNode {

    int val;
    ListNode next;

    public ListNode(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}
