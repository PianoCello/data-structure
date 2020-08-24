package com.pianocello.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个二叉树，找出其最小深度。
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 * 给定二叉树 [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最小深度 2.
 *
 * @author PianoCello
 * @date 2020-08-24
 */
public class _0111_MinimumDepthOfBinaryTree {

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 解法一：BFS
     */
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        int min = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            min++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.remove();
                // 得出结果 直接返回
                if (node.left == null && node.right == null) {
                    return min;
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return min;
    }

}
