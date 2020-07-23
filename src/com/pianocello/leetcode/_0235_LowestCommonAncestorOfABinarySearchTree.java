package com.pianocello.leetcode;

/**
 * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 *
 * @author PianoCello
 * @date 2020-07-23
 */
public class _0235_LowestCommonAncestorOfABinarySearchTree {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 解法一：递归求解
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root.val == p.val || root.val == q.val) {
            return root;
        }
        if (root.val > p.val && root.val > q.val) {
            // root 的值大于 p 和 q
            return lowestCommonAncestor(root.left, p, q);
        } if (root.val < p.val && root.val < q.val) {
            // root 的值小于 p 和 q
            return lowestCommonAncestor(root.right, p, q);
        }else {
            return root;
        }
    }
}
