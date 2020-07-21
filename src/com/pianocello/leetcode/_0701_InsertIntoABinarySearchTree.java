package com.pianocello.leetcode;

/**
 * 给定二叉搜索树（BST）的根节点和要插入树中的值，将值插入二叉搜索树。
 * 返回插入后二叉搜索树的根节点。 保证原始二叉搜索树中不存在新值。
 * 注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回任意有效的结果。
 *
 * 例如, 
 * 给定二叉搜索树:
 *
 *         4
 *        / \
 *       2   7
 *      / \
 *     1   3
 *
 * 和 插入的值: 5
 * 你可以返回这个二叉搜索树:
 *
 *          4
 *        /   \
 *       2     7
 *      / \   /
 *     1   3 5
 *
 * @author PianoCello
 * @date 2020-07-21
 */
public class _0701_InsertIntoABinarySearchTree {

    public class TreeNode {
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
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null)
            return new TreeNode(val);
        if (root.val > val) {
            root.left = insertIntoBST(root.left, val);
        } else if (root.val < val) {
            root.right = insertIntoBST(root.right, val);
        }
        return root;
    }

    /**
     * 解法二：迭代
     */
    public TreeNode insertIntoBST2(TreeNode root, int val) {
        if (root == null)
            return new TreeNode(val);
        TreeNode parent = root, p = root;
        while (p != null) {
            parent = p;
            p = p.val < val ? p.right : p.left;
        }
        if (parent.val < val) {
            parent.right = new TreeNode(val);
        } else {
            parent.left = new TreeNode(val);
        }
        return root;
    }
}
