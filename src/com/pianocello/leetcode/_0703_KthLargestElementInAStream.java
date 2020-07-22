package com.pianocello.leetcode;

/**
 * 设计一个找到数据流中第K大元素的类（class）。注意是排序后的第K大元素，不是第K个不同的元素。
 * 你的 KthLargest 类需要一个同时接收整数 k 和整数数组nums 的构造器，它包含数据流中的初始元素。
 * 每次调用 KthLargest.add，返回当前数据流中第K大的元素。
 *
 * 示例:
 * int k = 3;
 * int[] arr = [4,5,8,2];
 * KthLargest kthLargest = new KthLargest(3, arr);
 * kthLargest.add(3);   // returns 4
 * kthLargest.add(5);   // returns 5
 * kthLargest.add(10);  // returns 5
 * kthLargest.add(9);   // returns 8
 * kthLargest.add(4);   // returns 8
 *
 * @author PianoCello
 * @date 2020-07-22
 */
class KthLargest {

    private static class TreeNode {
        //节点的值
        int val;
        //此节点为根的子树中有多少个节点
        int count = 1;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    //二叉搜索树的根节点
    private TreeNode root;

    private int k;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        // 构件二叉搜索树
        for (int num : nums)
            addTreeNode(num);
    }

    private void addTreeNode(int num) {
        if (root == null) {
            root = new TreeNode(num);
            return;
        }
        TreeNode parent = root, p = root;
        while (p != null) {
            parent = p;
            p.count++;
            p = p.val > num ? p.left : p.right;
        }
        if (parent.val > num) {
            parent.left = new TreeNode(num);
        } else {
            parent.right = new TreeNode(num);
        }
    }

    private TreeNode search(TreeNode node, int k) {
        if (node == null) {
            return null;
        }
        int leftNodeCount = node.left != null ? node.left.count : 0;
        int rightNodeCount = node.right != null ? node.right.count : 0;
        int currNodeCount = node.count - leftNodeCount - rightNodeCount;

        if (k > currNodeCount + rightNodeCount) {
            // k > 当前结点数加右子树的结点数，则搜索左子树
            return search(node.left, k - currNodeCount - rightNodeCount);
        } else if (k <= rightNodeCount) {
            // k <= 右子树的结点数，则搜索右子树
            return search(node.right, k);
        } else {
            // k == 当前结点数加右子树的结点数，则找到第k大的结点
            return node;
        }
    }

    public int add(int val) {
        addTreeNode(val);
        TreeNode node = search(root, k);
        return node.val;
    }
}

