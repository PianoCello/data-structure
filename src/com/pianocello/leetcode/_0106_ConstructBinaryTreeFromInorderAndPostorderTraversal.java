package com.pianocello.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 * 中序遍历 in = [9,3,15,20,7]
 * 后序遍历 post = [9,15,7,20,3]
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
public class _0106_ConstructBinaryTreeFromInorderAndPostorderTraversal {

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

    // 根节点在后序中的索引
    private static int postRoot;
    private static int[] postorder;
    private static Map<Integer, Integer> map = new HashMap<>();

    public static TreeNode buildTree(int[] inorder, int[] postOrder) {
        postorder = postOrder;
        postRoot = postorder.length - 1;
        // HashMap 存储中序遍历的值和索引
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return helper(0, inorder.length - 1);
    }

    /**
     * @param inLeft 中序的左边界
     * @param inRight 中序的右边界
     * @return 子树根节点
     */
    private static TreeNode helper(int inLeft, int inRight) {
        if (inLeft > inRight) return null;
        // 找出根节点
        int rootVal = postorder[postRoot];
        TreeNode root = new TreeNode(rootVal);
        // 根节点在中序的索引
        int index = map.get(rootVal);

        postRoot--;
        root.right = helper(index + 1, inRight);
        root.left = helper(inLeft, index - 1);
        return root;
    }

    public static void main(String[] args) {

        int[] inorder = {1, 2, 3, 4};
        int[] postorder = {2, 3, 4, 1};

        TreeNode treeNode = buildTree(inorder, postorder);
        System.out.println(treeNode);
    }
}
