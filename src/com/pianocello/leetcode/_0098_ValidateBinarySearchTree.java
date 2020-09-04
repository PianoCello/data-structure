package com.pianocello.leetcode;

/**
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * 假设一个二叉搜索树具有如下特征：
 *
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 *
 * 示例 1:
 * 输入:
 *     2
 *    / \
 *   1   3
 * 输出: true
 *
 * @author PianoCello
 * @date 2020-07-20
 */
public class _0098_ValidateBinarySearchTree {

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 解法一：中序遍历 直接检查结果
     * pre 不使用 int 类型是因为 int 无法判断第一个节点
     */
    private static long pre = Long.MIN_VALUE;
    public static boolean isValidBST2(TreeNode root) {
        if (root == null) {
            return true;
        }
        // 访问左子树
        if (!isValidBST2(root.left)) {
            return false;
        }
        // 访问当前节点：如果当前节点小于等于中序遍历的前一个节点，说明不满足BST，返回 false；否则继续遍历。
        if (pre >= root.val) {
            return false;
        }
        // 更新前驱节点的值
        pre = root.val;
        // 访问右子树
        return isValidBST2(root.right);
    }

    /**
     * 解法二：递归
     */
    public static boolean isValidBST3(TreeNode root) {
        return helper(root,null,null);
    }

    private static boolean helper(TreeNode root, Integer lower, Integer upper) {
        if (root == null) return true;
        if (lower != null && root.val <= lower) return false;
        if (upper != null && root.val >= upper) return false;
        return helper(root.left,lower,root.val) && helper(root.right,root.val,upper);
    }

    public static void main(String[] args) {

        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);

        treeNode3.left = treeNode1;
        treeNode3.right = treeNode4;

        treeNode4.left = treeNode2;

        boolean b = isValidBST2(treeNode3);
        System.out.println(b);
    }

}
