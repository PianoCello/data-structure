package com.pianocello.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * @author PianoCello
 * @date 2020-07-19
 */
public class _0105_ConstructBinaryTreeFromPreorderAndInorderTraversal {

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

    // 存储中序遍历的值和索引
    private static Map<Integer, Integer> map = new HashMap<>();
    // 根节点在前序中的索引
    private static int rootIndex;

    /**
     * 以中序遍历的值作为构建二叉树的基础
     * 前序遍历的第一个值是整棵树的根节点 在中序遍历中找出这个节点 这个节点左边的为左子树 右边的值为右子树
     * 递归遍历中序左边和右边的值构建二叉树即可
     */
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        rootIndex = 0;
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildTree(0, inorder.length - 1, preorder);
    }

    private static TreeNode buildTree(int inLeft, int inRight, int[] preorder) {
        if (inLeft > inRight) return null;
        int rootVal = preorder[rootIndex];
        Integer index = map.get(rootVal);

        TreeNode root = new TreeNode(rootVal);
        rootIndex++;// 递归过程中的树的根节点刚好是前序遍历顺序的节点
        root.left = buildTree(inLeft, index - 1, preorder);
        root.right = buildTree(index + 1, inRight, preorder);
        return root;
    }

    public static void main(String[] args) {

        int[] preorder = {-1};
        int[] inorder = {-1};

        TreeNode treeNode = buildTree(preorder, inorder);
        System.out.println(treeNode);
    }
}
