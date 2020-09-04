package com.pianocello.leetcode;

/**
 * 两数相加:
 * <p>
 * 给出两个非空的链表用来表示两个非负的整数。
 * 其中，它们各自的位数是按照逆序的方式存储的，并且它们的每个节点只能存储一位数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 示例：
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 * @author PianoCello
 * @date 2019-11-22
 */
public class _0002_AddTwoNumbers {

    private static class ListNode {

        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
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
     * 解法一：
     */
    public static ListNode addTwoNumbers(ListNode left, ListNode right) {
        /// 返回的对象
        ListNode listNode = new ListNode(0);
        //临时对象
        ListNode temp = listNode;
        //进位
        int up = 0;
        //两链表不能同时为空
        while (left != null || right != null) {
            int i = 0;
            int j = 0;
            //左边
            if (left != null) {
                i = left.val;
                left = left.next;
            }
            //右边
            if (right != null) {
                j = right.val;
                right = right.next;
            }
            int sum = i + j + up;

            int num = sum % 10;
            up = sum / 10;

            temp.next = new ListNode(num);
            temp = temp.next;
        }
        //最后可能存在的进位
        if (up == 1) {
            temp.next = new ListNode(up);
        }
        return listNode.next;
    }

    /**
     * 解法二：LeetCode 题解
     * <p>
     * 时间复杂度：O(max(m,n))，假设 m 和 n 分别表示 left 和 right 的长度，上面的算法最多重复 max(m,n) 次。
     * 空间复杂度：O(max(m,n))， 新列表的长度最多为 max(m,n) + 1
     */
    public static ListNode addTwoNumbers2(ListNode left, ListNode right) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = left, q = right, curr = dummyHead;

        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {

        // 342
        ListNode listNode1 = new ListNode(2);
        ListNode listNode2 = new ListNode(4);
        ListNode listNode3 = new ListNode(3);
        listNode1.next = listNode2;
//        listNode2.next = listNode3;

        // 465
        ListNode listNode4 = new ListNode(5);
        ListNode listNode5 = new ListNode(6);
        ListNode listNode6 = new ListNode(4);
        listNode4.next = listNode5;
        listNode5.next = listNode6;

        // 807
        ListNode node = addTwoNumbers2(listNode1, listNode4);
        // 7 -> 0 -> 8
        System.out.println(node);

    }

}


