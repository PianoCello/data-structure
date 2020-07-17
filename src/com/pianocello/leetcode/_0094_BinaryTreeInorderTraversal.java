package com.pianocello.leetcode;

import java.util.*;

/**
 * 给定一个二叉树，返回它的中序 遍历。
 *
 * 示例:
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 * 输出: [1,3,2]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 *
 * @author PianoCello
 * @date 2020-06-25
 */
public class _0094_BinaryTreeInorderTraversal {

    //Definition for a binary tree node.
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 解法一：递归实现中序遍历
     */
    public static List<Integer> inorderTraversal(TreeNode root) {
        // 因为不需要查询，使用 LinkedList 更佳
        List<Integer> list = new LinkedList<>();
        inorderTraversal(root, list);
        return list;
    }

    private static void inorderTraversal(TreeNode root, List<Integer> list) {
        if (root == null) return;
        inorderTraversal(root.left, list);
        list.add(root.val);
        inorderTraversal(root.right, list);
    }

    /**
     * 解法二：使用 栈 迭代实现中序遍历
     */
    public static List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        //存储节点的栈
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            list.add(cur.val);
            cur = cur.right;
        }
        return list;
    }

    /**
     * 解法三：莫里斯 Morris 算法实现中序遍历
     * 时间复杂度： O(N)。
     * 空间复杂度： O(1)。
     */
    public static List<Integer> inorderTraversal3(TreeNode root) {
        // 因为不需要查询，使用 LinkedList 更佳
        List<Integer> list = new LinkedList<>();
        TreeNode cur = root;
        while (cur != null) {
            if (cur.left == null) {
                // 获取根节点的值
                list.add(cur.val);
                cur = cur.right;
            } else {
                // 查找前驱结点
                TreeNode pred = cur.left;
                while (pred.right != null && pred.right != cur) {
                    pred = pred.right;
                }
                // 第一次到达左子树的最右端 将前驱结点的右节点赋值为当前结点
                if (pred.right == null) {
                    pred.right = cur;
                    cur = cur.left;
                } else {
                    // 第二次到达左子树的最右端 还原树结构
                    list.add(cur.val);
                    pred.right = null;
                    cur = cur.right;
                }
            }
        }
        return list;
    }

    public static void main(String[] args) {

        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode6 = new TreeNode(6);
        TreeNode treeNode7 = new TreeNode(7);

        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;

        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;

        treeNode3.left = treeNode6;
        treeNode3.right = treeNode7;

        List<Integer> integers = inorderTraversal(treeNode1);

        System.out.println(integers);
    }
}
