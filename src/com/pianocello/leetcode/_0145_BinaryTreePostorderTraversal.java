package com.pianocello.leetcode;

import java.util.*;

/**
 * 给定一个二叉树，返回它的 后序 遍历。
 *
 * 示例:
 *
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [3,2,1]
 *
 * @author PianoCello
 * @date 2020-07-17
 */
public class _0145_BinaryTreePostorderTraversal {

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 解法一：递归实现后序遍历
     */
    public static List<Integer> postorderTraversal(TreeNode root) {
        // 因为不需要查询，使用 LinkedList 更佳
        List<Integer> list = new LinkedList<>();
        postorderTraversal(root, list);
        return list;
    }

    private static void postorderTraversal(TreeNode root, List<Integer> list) {
        if (root == null) return;
        postorderTraversal(root.left, list);
        postorderTraversal(root.right, list);
        list.add(root.val);
    }

    /**
     * 解法二：使用 栈 迭代实现后序遍历
     * 时间复杂度：访问每个节点恰好一次，时间复杂度为 O(N) ，其中 N 是节点的个数，也就是树的大小。
     * 空间复杂度：取决于树的结构，最坏情况存储整棵树，因此空间复杂度是 O(N)。
     */
    public static List<Integer> postorderTraversal2(TreeNode root) {
        LinkedList<Integer> list = new LinkedList<>();
        if (root == null) {
            return list;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            list.addFirst(node.val);
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        return list;
    }

    /**
     * 解法二：使用 栈 迭代实现后序遍历
     */
    public static List<Integer> postorderTraversal4(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        Deque<TreeNode> stack = new LinkedList<>();

        TreeNode cur = root;
        TreeNode p = null;//用来记录上一节点
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.getFirst();
            if (cur.right == null || cur.right == p) {
                list.add(cur.val);
                stack.pop();
                p = cur;
                cur = null;
            } else {
                cur = cur.right;
            }
        }
        return list;
    }

    /**
     * 解法三：莫里斯算法实现后序遍历
     * 时间复杂度： O(N)。
     * 空间复杂度： O(1)。
     */
    public static List<Integer> postorderTraversal3(TreeNode root) {
        // 因为不需要查询，使用 LinkedList 更佳
        List<Integer> list = new LinkedList<>();
        TreeNode cur = root;
        while (cur != null) {
            if (cur.left == null) {
                cur = cur.right;
            } else {
                // 查找前驱结点
                TreeNode pred = cur.left;
                while (pred.right != null && pred.right != cur) {
                    pred = pred.right;
                }
                // 第一次到达左子树的最右端 将前驱结点的右节点赋值为当前结点
                if (pred.right == null) {
                    pred.right = cur;
                    cur = cur.left;
                } else {
                    // 第二次到达左子树的最右端 还原树结构
                    pred.right = null;
                    printNode(cur.left, list);
                    cur = cur.right;
                }
            }
        }
        return list;
    }

    private static void printNode(TreeNode node,List<Integer> list) {
        TreeNode tail = reverseNode(node);
        TreeNode cur = tail;
        while (cur != null) {
            list.add(cur.val);
            cur = cur.right;
        }
        reverseNode(tail);
    }

    // 反转链表
    private static TreeNode reverseNode(TreeNode node) {
        TreeNode pre = null;
        TreeNode next;
        while (node != null) {
            next = node.right;
            node.right = pre;
            pre = node;
            node = next;
        }
        return pre;
    }


    //后序 Morris
    public static void postOrderMorris(TreeNode head) {
        if (head == null) {
            return;
        }
        TreeNode cur1 = head;//遍历树的指针变量
        TreeNode cur2 = null;//当前子树的最右节点
        while (cur1 != null) {
            cur2 = cur1.left;
            if (cur2 != null) {
                while (cur2.right != null && cur2.right != cur1) {
                    cur2 = cur2.right;
                }
                if (cur2.right == null) {
                    cur2.right = cur1;
                    cur1 = cur1.left;
                    continue;
                } else {
                    cur2.right = null;
                    postMorrisPrint(cur1.left);
                }
            }
            cur1 = cur1.right;
        }
        postMorrisPrint(head);
    }
    //打印函数
    private static void postMorrisPrint(TreeNode head) {
        TreeNode reverseList = postMorrisReverseList(head);
        TreeNode cur = reverseList;
        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.right;
        }
        postMorrisReverseList(reverseList);
    }
    //翻转单链表
    private static TreeNode postMorrisReverseList(TreeNode head) {
        TreeNode cur = head;
        TreeNode pre = null;
        while (cur != null) {
            TreeNode next = cur.right;
            cur.right = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        root.left = node2;
        root.right = node3;

        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        node2.left = node4;
        node2.right = node5;

        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        node3.left = node6;
        node3.right = node7;

//        List<Integer> list = postorderTraversal2(root);

        postOrderMorris(root);
//        System.out.println(list);

    }
}
