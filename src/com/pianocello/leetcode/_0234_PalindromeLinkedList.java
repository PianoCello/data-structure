package com.pianocello.leetcode;

/**
 * 请判断一个链表是否为回文链表。
 * <p>
 * 示例 1:
 * 输入: 1->2
 * 输出: false
 * <p>
 * 示例 2:
 * 输入: 1->2->2->1
 * 输出: true
 *
 * @author PianoCello
 * @date 2020-06-29
 */
public class _0234_PalindromeLinkedList {

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
     * 解法一：
     * 将链表的后半部分反转（修改链表结构），然后将前半部分和后半部分进行比较
     * 时间复杂度：O(n)，其中 n 指的是链表的大小。
     * 空间复杂度：O(1)，我们是一个接着一个的改变指针，我们在堆栈上的堆栈帧不超过 O(1)。
     */
    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        //先找链表的中点
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        //反转中点到尾部的链表
        ListNode pre = null;
        while (slow != null) {
            ListNode cur = slow.next;
            slow.next = pre;
            pre = slow;
            slow = cur;
        }
        //比较两个链表是否相等（链表长度的奇数的话最后一个不用管）
        while (head != null && pre != null) {
            if (head.val != pre.val) {
                return false;
            }
            head = head.next;
            pre = pre.next;
        }
        return true;
    }

    /**
     * 解法二：递归
     * 时间复杂度：O(n)，其中 n 指的是链表的大小。
     * 空间复杂度：O(n)，其中 n 指的是链表的大小。
     */
    private static ListNode frontPointer;

    private static boolean recursivelyCheck(ListNode currentNode) {
        if (currentNode != null) {
            if (!recursivelyCheck(currentNode.next)) return false;
            if (currentNode.val != frontPointer.val) return false;
            frontPointer = frontPointer.next;
        }
        return true;
    }

    public static boolean isPalindrome2(ListNode head) {
        frontPointer = head;
        return recursivelyCheck(head);
    }

    public static void main(String[] args) {

        ListNode listNode = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(3);
        ListNode listNode5 = new ListNode(2);
        ListNode listNode6 = new ListNode(1);

        listNode.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = listNode6;

        boolean b = isPalindrome2(listNode);
        System.out.println(b);

    }
}
