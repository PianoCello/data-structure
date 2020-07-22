package com.pianocello.leetcode;

/**
 * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，
 * 并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
 * <p>
 * 一般来说，删除节点可分为两个步骤：
 * 首先找到需要删除的节点；
 * 如果找到了，删除它。
 *
 * 说明： 要求算法时间复杂度为 O(h)，h 为树的高度。
 *
 * @author PianoCello
 * @date 2020-07-21
 */
public class _0450_DeleteNodeInABST {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 解法一：递归
     * 1. 如果目标节点没有子节点，我们可以直接移除该目标节点。
     * 2. 如果目标节只有一个子节点，我们可以用其子节点作为替换。
     * 3. 如果目标节点有两个子节点，我们需要用其中序 后继节点 或者 前驱节点 来替换，再删除该目标节点。
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;

        if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else {
            if (root.left == null && root.right == null) {
                root = null;
            } else if (root.right != null) {
                //使用后继节点替换当前节点再删除后继节点
                root.val = successor(root);
                root.right = deleteNode(root.right, root.val);
            } else {
                //使用前驱节点替换当前节点再删除前驱节点
                root.val = predecessor(root);
                root.left = deleteNode(root.left, root.val);
            }
        }
        return root;
    }

    private int successor(TreeNode root) {
        root = root.right;
        while (root.left != null) {
            root = root.left;
        }
        return root.val;
    }

    private int predecessor(TreeNode root) {
        root = root.left;
        while (root.right != null) {
            root = root.right;
        }
        return root.val;
    }

}
