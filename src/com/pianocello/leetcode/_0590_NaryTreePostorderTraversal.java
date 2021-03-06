package com.pianocello.leetcode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个 N 叉树，返回其节点值的后序遍历。
 *
 * @author PianoCello
 * @date 2020-07-24
 */
public class _0590_NaryTreePostorderTraversal {

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
     * 解法一：递归
     */
    private static List<Integer> list = new ArrayList<>();
    public static List<Integer> postorder(Node root) {
        if (root == null) return list;

        List<Node> children = root.children;
        if (children != null) {
            for (Node node : root.children) {
                postorder(node);
            }
        }

        list.add(root.val);
        return list;
    }

    /**
     * 解法二：迭代
     */
    public static List<Integer> postorder2(Node root) {
        LinkedList<Integer> list = new LinkedList<>();
        if (root == null) return list;

        // 使用栈临时存储子节点
        Deque<Node> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            list.addFirst(node.val);

            List<Node> children = node.children;
            if (children != null && !children.isEmpty()) {
                for (int i = 0; i < children.size(); i++)
                    stack.push(children.get(i));
            }
        }
        return list;
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

        List<Integer> preorder = postorder(root);
        List<Integer> preorder2 = postorder2(root);

        System.out.println(preorder);
        System.out.println(preorder2);
    }
}
