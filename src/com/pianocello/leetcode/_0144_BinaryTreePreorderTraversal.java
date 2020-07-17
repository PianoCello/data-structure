package com.pianocello.leetcode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个二叉树，返回它的 前序 遍历。
 *
 * 示例:
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [1,2,3]
 *
 * @author PianoCello
 * @date 2020-07-17
 */
public class _0144_BinaryTreePreorderTraversal {

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 解法一：递归实现前序遍历
     */
    public static List<Integer> preorderTraversal(TreeNode root) {
        // 因为不需要查询，使用 LinkedList 更佳
        List<Integer> list = new LinkedList<>();
        preorderTraversal(root, list);
        return list;
    }

    private static void preorderTraversal(TreeNode root, List<Integer> list) {
        if (root == null) return;
        list.add(root.val);
        preorderTraversal(root.left, list);
        preorderTraversal(root.right, list);
    }

    /**
     * 解法二：使用 栈 迭代实现前序遍历
     * 时间复杂度：访问每个节点恰好一次，时间复杂度为 O(N) ，其中 N 是节点的个数，也就是树的大小。
     * 空间复杂度：取决于树的结构，最坏情况存储整棵树，因此空间复杂度是 O(N)。
     */
    public static List<Integer> preorderTraversal2(TreeNode root) {
        // 因为不需要查询，使用 LinkedList 更佳
        List<Integer> list = new LinkedList<>();
        if (root == null) {
            return list;
        }
        //临时存储树的结点
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            list.add(node.val);
            if (node.right != null) {
                stack.push(node.right );
            }
            if (node.left != null) {
                stack.push(node.left );
            }
        }
        return list;
    }

    /**
     * 解法三：莫里斯算法实现前序遍历
     *
     * 假设当前节点为 cur，并且开始时赋值为根节点 root。
     * 1. 判断 cur 节点是否为空
     * 2. 如果不为空
     * 2.1 如果 cur 没有左孩子，cur 向右更新，即（cur = cur.right）
     * 2.2 如果 cur 有左孩子，则从左子树找到最右侧节点 pre
     * 2.2.1 如果 pre 的右孩子为空，则将右孩子指向 cur。pre.right = cur
     * 2.2.2 如果 pre 的右孩子为 cur，则将其指向为空。pre.right = null。（还原树结构）
     * 3. cur 为空时，停止遍历
     *
     * 时间复杂度： O(N)。
     * 空间复杂度： O(1)。
     */
    public static List<Integer> preorderTraversal3(TreeNode root) {
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
                    list.add(cur.val);
                    pred.right = cur;
                    cur = cur.left;
                } else {
                    // 第二次到达左子树的最右端 还原树结构
                    pred.right = null;
                    cur = cur.right;
                }
            }
        }
        return list;
    }

    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        root.left = node2;
        root.right = node3;

        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        node2.left = node4;
        node2.right = node5;

        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        node3.left = node6;
        node3.right = node7;

        List<Integer> list = preorderTraversal3(root);
        System.out.println(list);

    }

}
