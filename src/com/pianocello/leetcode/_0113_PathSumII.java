package com.pianocello.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 *
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \    / \
 *         7    2  5   1
 * 返回:
 * [
 *    [5,4,11,2],
 *    [5,8,4,5]
 * ]
 *
 * @author PianoCello
 * @date 2020-07-18
 */
public class _0113_PathSumII {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 解法一：递归
     */
    public static List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new LinkedList<>();
        //存储当前路径
        LinkedList<Integer> curPath = new LinkedList<>();
        recur(result, curPath, root, sum);
        return result;
    }

    private static void recur(List<List<Integer>> result, LinkedList<Integer> curPath, TreeNode curNode, int sum) {
        if (curNode == null) {
            return;
        }
        curPath.add(curNode.val);
        if (curNode.val == sum && curNode.left == null && curNode.right == null) {
            result.add(new LinkedList<>(curPath));
        } else {
            recur(result, curPath, curNode.left, sum - curNode.val);
            recur(result, curPath, curNode.right, sum - curNode.val);
        }
        // 回退到上一个节点
        curPath.removeLast();
    }

    public static void main(String[] args) {

        TreeNode treeNode1 = new TreeNode(5);
        TreeNode treeNode2 = new TreeNode(4);
        TreeNode treeNode3 = new TreeNode(8);

        TreeNode treeNode5 = new TreeNode(11);
        TreeNode treeNode6 = new TreeNode(13);
        TreeNode treeNode7 = new TreeNode(4);
        TreeNode treeNode8 = new TreeNode(7);
        TreeNode treeNode9 = new TreeNode(2);
        TreeNode treeNode10 = new TreeNode(5);
        TreeNode treeNode11 = new TreeNode(1);

        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;

        treeNode2.left = treeNode5;

        treeNode3.left = treeNode6;
        treeNode3.right = treeNode7;

        treeNode5.left = treeNode8;
        treeNode5.right = treeNode9;

        treeNode7.left = treeNode10;
        treeNode7.right = treeNode11;


        List<List<Integer>> lists = pathSum(treeNode1, 22);

        System.out.println(lists);
    }

}
