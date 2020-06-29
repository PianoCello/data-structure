package com.pianocello.leetcode;

/**
 * 将两个升序链表合并为一个新的升序链表并返回。
 * 新链表是通过拼接给定的两个链表的所有节点组成的。
 * <p>
 * 示例：
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 *
 * @author PianoCello
 * @date 2020-06-29
 */
public class _0021_MergeTwoSortedLists {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
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
     * 设置哨兵节点，当 left 和 right 都不是空链表时，判断 left 和 right 哪一个链表的头节点的值更小
     * 将较小值的节点添加到结果里，当一个节点被添加到结果里之后，将对应链表中的节点向后移一位。
     *
     * 时间复杂度：O(n + m) ，其中 n 和 m 分别为两个链表的长度。
     * 空间复杂度：O(1) 。我们只需要常数的空间存放若干变量。
     */
    public static ListNode mergeTwoLists(ListNode left, ListNode right) {
        //哨兵节点
        ListNode merge = new ListNode(-1);
        ListNode cur = merge;

        while (true) {
            if (right == null) {
                cur.next = left;
                break;
            }
            if (left == null) {
                cur.next = right;
                break;
            }
            ListNode temp;
            if (left.val < right.val) {
                temp = left;
                left = left.next;
            } else {
                temp = right;
                right = right.next;
            }
            cur.next = temp;
            cur = cur.next;
        }
        return merge.next;
    }

    public static void main(String[] args) {

        ListNode listNode = new ListNode(1,new ListNode(2,new ListNode(4)));
        ListNode listNode2 = new ListNode(1,new ListNode(3,new ListNode(4)));

        ListNode merge = mergeTwoLists(listNode, listNode2);

        System.out.println(merge);
    }
}
