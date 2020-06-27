package com.pianocello.leetcode;

/**
 * 找到两个单链表相交的起始节点
 * <p>
 * 注意：
 * 如果两个链表没有交点，返回 null.
 * 在返回结果后，两个链表仍须保持原有的结构。
 * 可假定整个链表结构中没有循环。
 * 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
 *
 * @author PianoCello
 * @date 2020-06-27
 */
public class _0160_IntersectionOfTwoLinkedLists {

    private class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        //求两人相遇的地点
        ListNode she = headA, he = headB;
        //两个链表长度不等 she 走完了 headA 将走 headB ；反之同理；
        //当 she == he 时，如果是 null 没有相遇，反之相遇了
        while (she != he) {
            she = she == null ? headB : she.next;
            he = he == null ? headA : he.next;
        }
        return she;
    }
}
