package com.pianocello.leetcode;

/**
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 * <p>
 * 说明:
 * 1 ≤ m ≤ n ≤ 链表长度。
 * <p>
 * 示例:
 * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
 * 输出: 1->4->3->2->5->NULL
 *
 * @author PianoCello
 * @date 2020-06-27
 */
public class _0092_ReverseLinkedListII {

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
     *
     */
    public static ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dmy = new ListNode(0);
        dmy.next = head;
        int delta = n - m;
        ListNode pre = dmy, tail = head;
        //先定位出m节点和m之前的节点
        while (m > 1) {
            pre = tail;
            tail = tail.next;
            m--;
        }
        while (delta > 0) {
            ListNode next = tail.next;
            tail.next = next.next;//tail一直不变，只要修改指针到next.next
            next.next = pre.next;//next.next指向pre的next，也就是最新的第m个位置
            pre.next = next;//更新next为最新的第m个位置
            delta--;
        }

        return dmy.next;
    }

    public static void main(String[] args) {

        ListNode listNode = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);

        listNode.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;

        ListNode reverseList = reverseBetween(listNode, 2, 4);
        System.out.println(reverseList);

    }
}
