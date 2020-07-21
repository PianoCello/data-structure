package com.pianocello.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 实现一个二叉搜索树迭代器。你将使用二叉搜索树的根节点初始化迭代器。
 * 调用 next() 将返回二叉搜索树中的下一个最小的数。
 *
 * 说明：
 * next() 和 hasNext() 操作的时间复杂度是 O(1)，并使用 O(h) 内存，其中 h 是树的高度。
 * 你可以假设 next() 调用总是有效的，也就是说，当调用 next() 时，BST 中至少存在一个下一个最小的数。
 *
 * @author PianoCello
 * @date 2020-07-20
 */
class BSTIterator {

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 解法一：扁平化二叉搜索树
     * 时间复杂度：构造迭代器花费的时间为 O(N)
     * next()：O(1)
     * hasNext()：O(1)
     * 空间复杂度：O(N)
     */
    private Deque<Integer> list = new LinkedList<>();

    public BSTIterator(TreeNode root) {
        iterator(root);
    }

    private void iterator(TreeNode root) {
        if (root == null) return;
        iterator(root.left);
        list.add(root.val);
        iterator(root.right);
    }

    public int next() {
        return list.removeFirst();
    }

    public boolean hasNext() {
        return list.size() != 0;
    }
}

/**
 * 解法二：受控递归
 */
class BSTIterator2 {

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private Deque<TreeNode> stack = new LinkedList<>();

    public BSTIterator2(TreeNode root) {
        inorderLeft(root);
    }

    /**
     * 将给定节点中的所有左子节点添加到栈中，直到节点没有左子节点为止
     */
    private void inorderLeft(TreeNode root) {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }

    /**
     * 每次弹出一个节点之后 将此节点的右侧的左子节点加入栈中
     */
    public int next() {
        TreeNode node = stack.pop();
        if (node.right != null) {
            inorderLeft(node.right);
        }
        return node.val;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }
}
