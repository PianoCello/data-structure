package com.pianocello.leetcode;

/**
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 *
 * 示例：
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 *
 * 说明：
 * 给定的 n 保证是有效的。
 *
 * @author PianoCello
 * @date 2020-06-27
 */
public class _0019_RemoveNthNodeFromEndOfList {

    private class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /**
     * 解法一：使用快慢指针一次遍历
     * 时间复杂度：O(L)，该算法对含有 L 个结点的列表进行了一次遍历。因此时间复杂度为 O(L)。
     * 空间复杂度：O(1)，我们只用了常量级的额外空间。
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode slow = head, fast = head;
        //快指针先走 n 步
        while (n-- > 0) {
            fast = fast.next;
        }
        //删除的是第一个结点
        if (fast == null) {
            return head.next;
        }
        //快慢指针同时前进 fast.next == null 时，slow 到达指定结点
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return head;
    }

    /**
     * 解法二：使用哨兵节点 + 快慢指针
     */
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        // 哨兵节点
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode slow = dummy, fast = dummy;
        while (n-- > 0) {
            fast = fast.next;
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }
}
