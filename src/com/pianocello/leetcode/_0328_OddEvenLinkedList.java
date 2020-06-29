package com.pianocello.leetcode;

/**
 * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。
 * 请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
 * <p>
 * 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(n)，n 为节点总数。
 * <p>
 * 示例 1:
 * 输入: 2->1->3->5->6->4->7->NULL
 * 输出: 2->3->6->7->1->5->4->NULL
 *
 * @author PianoCello
 * @date 2020-06-29
 */
public class _0328_OddEvenLinkedList {

    private static class ListNode {
        int val;
        ListNode next;

        public ListNode() {
        }

        ListNode(int x) {
            val = x;
        }

        ListNode(int val, ListNode next) {
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
     */
    public static ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode odd = head;
        ListNode even = head.next;

        //存储奇偶链表的头结点
        ListNode oddHead = odd;
        ListNode evenHead = even;

        //第三个结点
        ListNode third = head.next.next;
        //将第二和第三和节点断开
        head.next.next = null;
        head = third;

        int i = 0;
        while (true) {
            i++;
            //到链表的末尾了
            if (head == null) {
                odd.next = evenHead;
                break;
            }
            ListNode cur = head.next;
            head.next = null;
            //奇数链表
            if ((i & 1) == 1) {
                odd.next = head;
                odd = odd.next;
            } else {
                even.next = head;
                even = even.next;
            }

            head = cur;
        }
        return oddHead;
    }

    /**
     * 解法一：LeetCode 题解
     * 时间复杂度： O(n) 。总共有 n 个节点，我们每个遍历一次。
     * 空间复杂度： O(1) 。我们只需要 4 个指针。
     */
    public static ListNode oddEvenList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // head 为奇链表头结点，oddTail 为奇链表尾节点
        ListNode oddTail = head;
        // evenHead 为偶链表头结点
        ListNode evenHead = head.next;
        // evenTail 为偶链表尾节点
        ListNode evenTail = evenHead;

        //一层一层往后推进
        while (oddTail.next != null && evenTail.next != null) {
            oddTail.next = evenTail.next;
            oddTail = oddTail.next;
            evenTail.next = oddTail.next;
            evenTail = evenTail.next;
        }
        //最后将奇链表的尾连接到偶链表的头
        oddTail.next = evenHead;
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

        ListNode reverseList = oddEvenList2(listNode);
        System.out.println(reverseList);
    }
}
