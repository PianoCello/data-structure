package com.pianocello.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给定一个二叉树，检查它是否是镜像对称的。
 *
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 *
 * @author PianoCello
 * @date 2020-07-18
 */
public class _0101_SymmetricTree {

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
    public static boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isSymmetric(root.left,root.right);
    }

    private static boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        // 此时左右子树都不能为空
        if (left == null || right == null || left.val != right.val) {
            return false;
        }
        return isSymmetric(left.left,right.right) && isSymmetric(left.right,right.left);
    }

    /**
     * 解法二：使用 双端队列 迭代
     */
    public static boolean isSymmetric2(TreeNode root) {
        // 只有单个结点
        if (root == null || (root.left == null && root.right == null)) {
            return true;
        }
        // 有左没有右 或者 有右没有左
        if (root.left == null || root.right == null) {
            return false;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.addLast(root.left);
        deque.addFirst(root.right);
        while (!deque.isEmpty()) {
            int size = deque.size();
            // 奇数个元素
            if ((size & 1) == 1) return false;
            int i = 0;
            while (i < size) {
                i += 2;
                TreeNode last = deque.removeLast();
                TreeNode first = deque.removeFirst();
                if (first.val != last.val) {
                    return false;
                }
                // 排除不对称的情况
                if (first.left == null ^ last.right == null) {
                    return false;
                }
                if (first.right == null ^ last.left == null) {
                    return false;
                }

                if (last.left != null) {
                    deque.addLast(last.left);
                }
                if (first.right != null) {
                    deque.addFirst(first.right);
                }
                if (last.right != null) {
                    deque.addFirst(last.right);
                }
                if (first.left != null) {
                    deque.addLast(first.left);
                }
            }
        }

        return true;
    }

    /**
     * 解法三：使用 队列 迭代
     */
    public static boolean isSymmetric3(TreeNode root) {
        if(root==null || (root.left==null && root.right==null)) {
            return true;
        }
        //用队列保存节点
        LinkedList<TreeNode> queue = new LinkedList<>();
        //将根节点的左右孩子放到队列中
        queue.add(root.left);
        queue.add(root.right);
        while(queue.size()>0) {
            //从队列中取出两个节点，再比较这两个节点
            TreeNode left = queue.removeFirst();
            TreeNode right = queue.removeFirst();
            //如果两个节点都为空就继续循环，两者有一个为空就返回false
            if(left==null && right==null) {
                continue;
            }
            // 此时左右子树都不能为空
            if(left==null || right==null) {
                return false;
            }
            if(left.val!=right.val) {
                return false;
            }
            //将左节点的左孩子， 右节点的右孩子放入队列
            queue.add(left.left);
            queue.add(right.right);
            //将左节点的右孩子，右节点的左孩子放入队列
            queue.add(left.right);
            queue.add(right.left);
        }
        return true;
    }

    public static void main(String[] args) {

        TreeNode treeNode1 = new TreeNode(2);
        TreeNode treeNode2 = new TreeNode(3);
        TreeNode treeNode3 = new TreeNode(3);

        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode6 = new TreeNode(5);
        TreeNode treeNode7 = new TreeNode(4);

        TreeNode treeNode8 = new TreeNode(8);
        TreeNode treeNode9 = new TreeNode(9);
        TreeNode treeNode10= new TreeNode(9);
        TreeNode treeNode11 = new TreeNode(8);

        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;

        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;

        treeNode3.left = treeNode6;
        treeNode3.right = treeNode7;

        treeNode5.left = treeNode8;
        treeNode5.right = treeNode9;

        treeNode6.left = treeNode10;
        treeNode6.right = treeNode11;

        boolean symmetric = isSymmetric(treeNode1);

        System.out.println(symmetric);
    }

}
