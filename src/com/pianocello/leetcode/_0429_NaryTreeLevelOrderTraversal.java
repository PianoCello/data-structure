package com.pianocello.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给定一个 N 叉树，返回其节点值的层序遍历。 (即从左到右，逐层遍历)。
 *
 * @author PianoCello
 * @date 2020-07-24
 */
public class _0429_NaryTreeLevelOrderTraversal {

    private static class Node {
        private int val;
        private List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    /**
     * 解法一：使用 队列 + BFS
     * 时间复杂度：O(n)。n 指的是节点的数量。
     * 空间复杂度：O(n)。
     */
    public static List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> list = new LinkedList<>();
        if (root == null) return list;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new LinkedList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.remove();
                level.add(node.val);
                List<Node> children = node.children;
                if (children != null) {
                    queue.addAll(children);
                }
            }
            list.add(level);
        }
        return list;
    }

    /**
     * 解法二：递归
     * 时间复杂度：O(n)。
     * 空间复杂度：正常情况 O(logn)，最坏情况 O(n)。运行时在堆栈上的空间。
     */
    private static List<List<Integer>> list = new LinkedList<>();
    public static List<List<Integer>> levelOrder2(Node root) {
        if (root == null) return list;

        levelOrder2(root,0);

        return list;
    }

    private static void levelOrder2(Node root, int level) {
        if (list.size() <= level) {
            list.add(new LinkedList<>());
        }
        list.get(level).add(root.val);
        if (root.children != null) {
            for (Node node : root.children) {
                levelOrder2(node, level + 1);
            }
        }
    }

    public static void main(String[] args) {

        Node root = new Node(1);

        Node root3 = new Node(3);
        Node root2 = new Node(2);
        Node root4 = new Node(4);
        List<Node> nodes = new ArrayList<>();
        nodes.add(root3);
        nodes.add(root2);
        nodes.add(root4);
        root.children = nodes;

        Node root5 = new Node(5);
        Node root6 = new Node(6);
        List<Node> nodes2 = new ArrayList<>();
        nodes2.add(root5);
        nodes2.add(root6);
        root3.children = nodes2;

        List<List<Integer>> lists = levelOrder(root);
        List<List<Integer>> lists2 = levelOrder2(root);

        System.out.println(lists);
        System.out.println(lists2);
    }
}
