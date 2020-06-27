package com.pianocello.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个链表，判断链表中是否有环。
 *
 * @author PianoCello
 * @date 2020-06-27
 */
public class _0141_LinkedListCycle {

    /**
     * 解法一：使用快慢指针
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)，我们只使用了慢指针和快指针两个结点，所以空间复杂度为 O(1)O(1)。
     */
    public boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    /**
     * 解法一：使用哈希表
     *
     * 时间复杂度：O(n)，对于含有 n 个元素的链表，我们访问每个元素最多一次。
     *  -- 添加一个结点到哈希表中只需要花费 O(1) 的时间。
     * 空间复杂度：O(n)，空间取决于添加到哈希表中的元素数目，最多可以添加 n 个元素。
     */
    public boolean hasCycle2(ListNode head) {

        Set<ListNode> set = new HashSet<>();
        while (head != null) {
            if (set.contains(head)) {
                return true;
            } else {
                set.add(head);
            }
            head = head.next;
        }
        return false;
    }

    private class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

}
