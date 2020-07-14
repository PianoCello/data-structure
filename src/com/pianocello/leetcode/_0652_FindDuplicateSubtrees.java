package com.pianocello.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给定一棵二叉树，返回所有重复的子树。对于同一类的重复子树，你只需要返回其中任意一棵的根结点即可。
 * 两棵树重复是指它们具有相同的结构以及相同的结点值。
 *
 * 示例 1：
 *         1
 *        / \
 *       2   3
 *      /   / \
 *     4   2   4
 *        /
 *       4
 * 下面是两个重复的子树：
 *       2
 *      /
 *     4
 * 和
 *     4
 * 因此，你需要以列表的形式返回上述重复子树的根结点。
 *
 * @author PianoCello
 * @date 2020-07-13
 */
public class _0652_FindDuplicateSubtrees {

    public static class TreeNode {
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
     * 解法一：前序遍历 在递归的最里层开始将出现的节点加入 HashMap 中
     */
    public static List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        Map<String, Integer> map = new HashMap<>();
        List<TreeNode> list = new ArrayList<>();

        findDuplicateSubtrees(root, map, list);
        return list;
    }

    private static String findDuplicateSubtrees(TreeNode root, Map<String, Integer> map, List<TreeNode> list) {
        StringBuilder sb = new StringBuilder();
        sb.append("#");
        if (root == null)
            return sb.toString();

        sb.append(root.val)
                .append(findDuplicateSubtrees(root.left, map, list))
                .append(findDuplicateSubtrees(root.right, map, list));

        String key = sb.toString();
        Integer val = map.get(key);
        //对已经出现一次的加入返回列表中
        if ( val != null && val == 1) {
            list.add(root);
        }
        //对出现的次数累加
        map.merge(key, 1, (a, b) -> a + b);
        return key;
    }

    public static void main(String[] args) {

        /*TreeNode treeNode = new TreeNode(1);

        treeNode.left = new TreeNode(2);
        treeNode.left.left = new TreeNode(4);

        treeNode.right = new TreeNode(3);
        treeNode.right.left = new TreeNode(2);
        treeNode.right.left.left = new TreeNode(4);
        treeNode.right.right = new TreeNode(4);*/

        TreeNode treeNode = new TreeNode(0);

        treeNode.left = new TreeNode(0);
        treeNode.left.left = new TreeNode(0);

        treeNode.right = new TreeNode(0);
        treeNode.right.right = new TreeNode(0);
        treeNode.right.right.right = new TreeNode(0);

        List<TreeNode> list = findDuplicateSubtrees(treeNode);
        System.out.println(list);

    }

}
