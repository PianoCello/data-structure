package com.pianocello.leetcode;

/**
 * 给定一个完美二叉树，其所有叶子节点都在同一层，每个父节点都有两个子节点和一个 next 节点。
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 * 初始状态下，所有 next 指针都被设置为 NULL。
 *
 * @author PianoCello
 * @date 2020-07-20
 */
public class _0116_PopulatingNextRightPointersInEachNode {

    private static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    /**
     * 解法一：递归
     */
    public static Node connect(Node root) {
        if (root == null || root.left == null) {
            return root;
        }
        root.left.next = root.right;
        root.right.next = (root.next == null ? null : root.next.left);

        connect(root.left);
        connect(root.right);
        return root;
    }

    /**
     * 解法二：层次遍历
     * 从最左边的路径依次遍历下去，每一层从最左边开始通过 next 节点遍历这一层的节点，为其子节点设置 next
     */
    public static Node connect2(Node root) {
        if (root == null) return null;

        Node leftmost = root;
        // 从最左边开始向下遍历
        while (leftmost.left != null) {
            Node head = leftmost;
            // 水平向右遍历
            while (head != null) {
                head.left.next = head.right;
                if (head.next != null) {
                    head.right.next = head.next.left;
                }
                head = head.next;
            }
            leftmost = leftmost.left;
        }
        return root;
    }

    public static void main(String[] args) {

        Node root = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        root.left = node2;
        root.right = node3;

        Node node4 = new Node(4);
        Node node5 = new Node(5);
        node2.left = node4;
        node2.right = node5;

        Node node6 = new Node(6);
        Node node7 = new Node(7);
        node3.left = node6;
        node3.right = node7;

        Node connect = connect(root);
        System.out.println(connect);
    }

}
