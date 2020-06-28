package com.pianocello.leetcode;

/**
 * 删除链表中等于给定值 val 的所有节点。
 * <p>
 * 示例:
 * 输入: 1->2->6->3->4->5->6, val = 6
 * 输出: 1->2->3->4->5
 *
 * @author PianoCello
 * @date 2020-06-28
 */
public class _0203_RemoveLinkedListElements {

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
     * 时间复杂度：O(N)，只遍历了一次。
     * 空间复杂度：O(1)。
     */
    public static ListNode removeElements(ListNode head, int val) {
        //哨兵结点可以方便删除头结点
        ListNode sentinel = new ListNode(-1);
        sentinel.next = head;

        ListNode cur = sentinel;
        while (cur.next != null) {
            if (cur.next.val == val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return sentinel.next;
    }

    public static void main(String[] args) {

        ListNode listNode = new ListNode(1);
        ListNode listNode2 = new ListNode(3);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);

        listNode.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;

        ListNode listNode1 = removeElements(listNode, 4);

        System.out.println(listNode1);
    }
}
