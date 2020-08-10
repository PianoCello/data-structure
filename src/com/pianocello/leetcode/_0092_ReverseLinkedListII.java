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
        //先定位出m节点和n之前的节点
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

    public static ListNode reverseBetween2(ListNode head, int m, int n) {
        //设置哨兵头结点 方便后面的节点反转
        ListNode dmy = new ListNode(0);
        dmy.next = head;

        //反转前的位置
        ListNode pre = dmy;
        int s = m;
        while (s-- > 1) {
            pre = pre.next;
        }
        //反转链表
        ListNode reverse = null;
        ListNode start = pre.next;
        //记录开始反转的地方
        ListNode mStart = start;
        int i = n - m;
        while (i-- >= 0) {
            ListNode temp = start.next;
            start.next = reverse;
            reverse = start;
            //最后一次循环拼接节点
            if (i < 0) {
                pre.next = reverse;
            }
            start = temp;
            //最后一次循环拼接节点
            if (i < 0) {
                mStart.next = start;
            }
        }
        return dmy.next;
    }

    public static ListNode reverseBetween3(ListNode head, int m, int n) {
        // Move the two pointers until they reach the proper starting point in the list.
        ListNode cur = head, prev = null;
        while (m > 1) {
            prev = cur;
            cur = cur.next;
            m--;
            n--;
        }
        // The two pointers that will fix the final connections.
        ListNode con = prev, tail = cur;
        // Iteratively reverse the nodes until n becomes 0.
        ListNode third = null;
        while (n > 0) {
            third = cur.next;
            cur.next = prev;
            prev = cur;
            cur = third;
            n--;
        }
        // Adjust the final connections as explained in the algorithm
        if (con != null) {
            con.next = prev;
        } else {
            head = prev;
        }
        tail.next = cur;
        return head;
    }

    public static void main(String[] args) {

        ListNode listNode = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        ListNode listNode6 = new ListNode(6);
        ListNode listNode7 = new ListNode(7);

        listNode.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = listNode6;
        listNode6.next = listNode7;

        ListNode reverseList = reverseBetween3(listNode, 1, 2);
        System.out.println(reverseList);

    }
}
