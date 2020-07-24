package com.pianocello.leetcode;

/**
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 * 本题中，一棵高度平衡二叉树定义为：
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
 *
 * 示例 1:
 * 给定二叉树 [3,9,20,null,null,15,7]
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回 true 。
 *
 * @author PianoCello
 * @date 2020-07-24
 */
public class _0110_BalancedBinaryTree {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 解法一：递归判断左右子树的高度差是否大于 1
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        // 左右子树的高度差的绝对值超过 1
        if (Math.abs(height(root.left) - height(root.right)) > 1) {
            return false;
        }
        // 递归判断左子树
        if (!isBalanced(root.left)) {
            return false;
        }
        // 再递归判断右子树
        return isBalanced(root.right);
    }

    // 求节点的高度
    private int height(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int left = height(root.left);
            int right = height(root.right);
            return Math.max(left,right) + 1;
        }
    }

}
