package com.pianocello.leetcode;

/**
 * 给定一个二叉树，找出其最大深度。
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最大深度 3 。
 *
 * @author PianoCello
 * @date 2020-07-18
 */
public class _0104_MaximumDepthOfBinaryTree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 解法一：自底向上 递归
     */
    public static int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        int i = maxDepth(root.left);
        int j = maxDepth(root.right);
        return Math.max(i, j) + 1;
    }

    /**
     * 解法二：自顶向下 递归
     */
    private static int depth = 0;
    public static int maxDepth2(TreeNode root) {
        maxDepth2(root, depth +1);
        return depth;
    }

    private static void maxDepth2(TreeNode root, int d) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            depth = Math.max(depth, d);
        }
        maxDepth2(root.left, d + 1);
        maxDepth2(root.right, d + 1);
    }

    public static void main(String[] args) {

        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode6 = new TreeNode(6);
        TreeNode treeNode7 = new TreeNode(7);
        TreeNode treeNode8 = new TreeNode(8);
        TreeNode treeNode9 = new TreeNode(9);
        TreeNode treeNode10 = new TreeNode(10);

        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;

        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;

        treeNode3.left = treeNode6;
        treeNode3.right = treeNode7;

        treeNode4.left = treeNode8;
        treeNode8.left = treeNode9;
        treeNode9.left = treeNode10;

        int i = maxDepth2(treeNode1);

        System.out.println(i);
    }
}
