package com.pianocello.leetcode;

/**
 * 给定一个非空二叉树，返回其最大路径和。
 * 本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。
 * 该路径至少包含一个节点，且不一定经过根节点。
 *
 * 示例 1:
 * 输入: [1,2,3]
 *
 *        1
 *       / \
 *      2   3
 * 输出: 6
 *
 * 示例 2:
 * 输入: [-10,9,20,null,null,15,7]
 *
 *    -10
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 输出: 42
 *
 * @author PianoCello
 * @date 2020-08-15
 */
public class _0124_BinaryTreeMaximumPathSum {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    /**
     * 解法一：后序遍历
     * 时间复杂度：O(N)，其中 N 是二叉树中的节点个数。对每个节点访问不超过 2 次。
     * 空间复杂度：O(N)，其中 N 是二叉树中的节点个数。
     * 空间复杂度主要取决于递归调用层数，最大层数等于二叉树的高度，最坏情况下，二叉树的高度等于二叉树中的节点个数。
     */
    private static int maxSum = Integer.MIN_VALUE;
    public static int maxPathSum(TreeNode root) {
        maxGain(root);
        return maxSum;
    }

    /**
     * 空节点的最大贡献值等于 0。
     * 非空节点的最大贡献值等于节点值与其子节点中的最大贡献值之和（对于叶节点而言，最大贡献值等于节点值）。
     */
    private static int maxGain(TreeNode node) {
        if (node == null) {
            return 0;
        }
        // 递归计算左右子节点的最大贡献值
        // 只有在最大贡献值大于 0 时，才会选取对应子节点
        int leftGain = Math.max(maxGain(node.left), 0);
        int rightGain = Math.max(maxGain(node.right), 0);

        // 节点的最大路径和取决于该节点的值与该节点的左右子节点的最大贡献值
        int priceNewpath = node.val + leftGain + rightGain;

        // 更新答案
        maxSum = Math.max(maxSum, priceNewpath);

        // 返回节点的最大贡献值
        return node.val + Math.max(leftGain, rightGain);
    }

    public static void main(String[] args) {

        TreeNode node = new TreeNode(-10);
        TreeNode node2 = new TreeNode(9);
        TreeNode node3 = new TreeNode(20);
        TreeNode node4 = new TreeNode(15);
        TreeNode node5 = new TreeNode(7);
        node5.left =

        node.left = node2;
        node.right = node3;
        node3.left = node4;
        node3.right = node5;
        node5.left = new TreeNode(1);
        int sum = maxPathSum(node);

        TreeNode treeNode = new TreeNode(-10);
        TreeNode treeNod2 = new TreeNode(-9);
        TreeNode treeNod3 = new TreeNode(20);
        treeNode.left = treeNod2;
        treeNode.right = treeNod3;
//        int sum2 = maxPathSum(treeNode);

        System.out.println(sum);
    }
}
