package com.pianocello.leetcode;

/**
 * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
 *
 * 示例 1:
 * 输入: 1->2->3->4->5->NULL, k = 2
 * 输出: 4->5->1->2->3->NULL
 * 解释:
 * 向右旋转 1 步: 5->1->2->3->4->NULL
 * 向右旋转 2 步: 4->5->1->2->3->NULL
 *
 * @author PianoCello
 * @date 2020-07-01
 */
public class _0061_RotateList {

    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
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
     * 解法一：自己写的
     * 时间复杂度：O(N)，其中 N 是链表中的元素个数
     * 空间复杂度：O(1)，因为只需要常数的空间
     */
    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }
        //遍历链表找出链表的大小
        int size = 0;
        ListNode cur = head;
        while (cur != null) {
            ++size;
            cur = cur.next;
        }
        //取余
        k = k % size;
        //如果 k 是 0 的话，说明不用移动
        if (k == 0) {
            return head;
        }
        //使用双指针移动到倒数第 k 个位置
        ListNode slow = head, fast = head;
        while (k-- > 0) {
            fast = fast.next;
        }
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        //最后将新链表的头和尾赋予正确的值
        ListNode res = slow.next;
        fast.next = head;
        slow.next = null;
        return res;
    }

    /**
     * 解法二：LeetCode 题解
     * 时间复杂度：O(N)，其中 N 是链表中的元素个数
     * 空间复杂度：O(1)，因为只需要常数的空间
     */
    public static ListNode rotateRight2(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }

        // close the linked list into the ring
        ListNode oldTail = head;
        int n;
        for(n = 1; oldTail.next != null; n++)
            oldTail = oldTail.next;
        oldTail.next = head;

        ListNode newTail = head;
        for (int i = 0; i < n - k % n - 1; i++)
            newTail = newTail.next;
        ListNode newHead = newTail.next;

        newTail.next = null;

        return newHead;
    }

    public static void main(String[] args) {
        // 1->2->3->4->5->NULL, k = 2

        ListNode head = new ListNode(1,new ListNode(2,new ListNode(3,new ListNode(4, new ListNode(5)))));

        ListNode head2 = new ListNode(1,new ListNode(3, new ListNode(5)));

        ListNode listNode = rotateRight2(head2, 4);

        System.out.println(listNode);
    }
}
