package com.pianocello.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个 N 叉树，找到其最大深度。
 * 最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。
 *
 * @author PianoCello
 * @date 2020-07-24
 */
public class _0559_MaximumDepthOfNaryTree {

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
     * 解法一：自底向上递归
     */
    public static int maxDepth(Node root) {
        if (root == null) return 0;
        int maxDepth = 0;
        List<Node> children = root.children;
        if (children != null) {
            for (Node node : children) {
                maxDepth = Math.max(maxDepth, maxDepth(node));
            }
        }
        return maxDepth + 1;
    }

    /**
     * 解法二：自顶向下递归
     */
    private static int max = 0;

    public static int maxDepth2(Node root) {
        if (root == null) return 0;
        maxDepth2(root, 1);
        return max;
    }

    private static void maxDepth2(Node root, int depth) {
        List<Node> children = root.children;
        if (children == null || children.size() == 0) {
            max = Math.max(max, depth);
            return;
        }
        for (Node node : children) {
            maxDepth2(node, depth + 1);
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

        int depth = maxDepth(root);
        System.out.println(depth);
    }

}
