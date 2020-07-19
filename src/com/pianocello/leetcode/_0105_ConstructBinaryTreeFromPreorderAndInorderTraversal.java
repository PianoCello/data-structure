package com.pianocello.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 * 前序遍历 preorder = [3,9,20,15,7]
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

    private static Map<Integer, Integer> map = new HashMap<>();
    private static int rootIndex;

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
        rootIndex++;
        root.left = buildTree(inLeft, index-1, preorder);
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
