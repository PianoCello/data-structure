package com.pianocello.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * 说明：不允许修改给定的链表。
 *
 * @author PianoCello
 * @date 2020-06-27
 */
public class _0142_LinkedListCycleII {

    private class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /**
     * 解法一：使用快慢指针
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode slow = head, fast = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                break;
            }
        }
        //不存在环 返回 null
        if (slow != fast) {
            return null;
        }
        slow = head;
        //当 slow 和 fast 相遇时，这个位置就是环的起点
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    /**
     * 解法二：使用哈希表
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public ListNode detectCycle2(ListNode head) {
        Set<ListNode> visited = new HashSet<>();

        ListNode node = head;
        while (node != null) {
            if (visited.contains(node)) {
                return node;
            }
            visited.add(node);
            node = node.next;
        }
        return null;
    }
}
