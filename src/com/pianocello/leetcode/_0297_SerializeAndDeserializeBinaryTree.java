package com.pianocello.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 序列化是将一个数据结构或者对象转换为连续的 比特位 的操作，进而可以将转换后的数据存储在一个文件或者内存中，
 * 同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 *
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，
 * 你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 *
 * 示例: 
 * 你可以将以下二叉树：
 *     1
 *    / \
 *   2   3
 *      / \
 *     4   5
 *
 * 序列化为 "[1,2,3,null,null,4,5]"
 *
 * @author PianoCello
 * @date 2020-07-20
 */
class Codec {

    private static class TreeNode {
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


    /**
     * 序列化：使用前序遍历得到字符串 空用 "None" 表示
     */
    public static String serialize(TreeNode root) {
        StringBuilder s = doSerialize(root, new  StringBuilder());
        return s.substring(0,s.length()-1);
    }

    private static StringBuilder doSerialize(TreeNode root, StringBuilder str) {
        if (root == null) {
            str.append("None,");
        } else {
            str.append(root.val).append(",");
            doSerialize(root.left, str);
            doSerialize(root.right, str);
        }
        return str;
    }

    /**
     * 反序列化：根据字符串转化成 List 递归构建二叉树
     */
    public static TreeNode deserialize(String data) {
        // 使用 LinkedList 为了更快的删除头结点
        List<String> preorder = new LinkedList<>(Arrays.asList(data.split(",")));
        return doDeserialize(preorder);
    }

    private static TreeNode doDeserialize(List<String> preorder) {
        if (preorder.get(0).equals("None")) {
            preorder.remove(0);
            return null;
        }
        Integer val = Integer.valueOf(preorder.get(0));
        preorder.remove(0);
        TreeNode node = new TreeNode(val);
        node.left = doDeserialize(preorder);
        node.right = doDeserialize(preorder);
        return node;
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

        String s = serialize(root);
        System.out.println(s);

        TreeNode node = deserialize(s);
        System.out.println(node);
    }
}
