package com.pianocello.leetcode;

/**
 * 反转一个单链表。
 * <p>
 * 示例:
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 *
 * @author PianoCello
 * @date 2020-06-27
 */
public class _0206_ReverseLinkedList {

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }

    /**
     * 解法一：迭代
     * 时间复杂度：O(n)，假设 n 是列表的长度，时间复杂度是 O(n)。
     * 空间复杂度：O(1)。
     */
    public static ListNode reverseList(ListNode head) {
        //反转后的链表
        ListNode reverse = null;

        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            //当前结点指向反转链表
            curr.next = reverse;
            //更新反转链表
            reverse = curr;
            curr = nextTemp;
        }
        return reverse;
    }

    /**
     * 解法一：递归
     * 时间复杂度：O(n)，假设 n 是列表的长度，那么时间复杂度为 O(n)。
     * 空间复杂度：O(n)，由于使用递归，将会使用隐式栈空间。递归深度可能会达到 n 层。
     */
    public static ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode p = reverseList2(head.next);

        head.next.next = head;
        head.next = null;
        return p;
    }


    public static void main(String[] args) {

        ListNode listNode = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);

        listNode.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;

        ListNode reverseList = reverseList2(listNode);
        System.out.println(reverseList);

    }
}
