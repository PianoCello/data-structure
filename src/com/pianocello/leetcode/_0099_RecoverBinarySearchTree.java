package com.pianocello.leetcode;

/**
 * 二叉搜索树中的两个节点被错误地交换。
 * 请在不改变其结构的情况下，恢复这棵树。
 *
 * 示例 1:
 * 输入: [1,3,null,null,2]
 *    1
 *   /
 *  3
 *   \
 *    2
 * 输出: [3,1,null,null,2]
 *    3
 *   /
 *  1
 *   \
 *    2
 *
 * @author PianoCello
 * @date 2020-08-16
 */
public class _0099_RecoverBinarySearchTree {

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
     * 解法一：中序遍历
     * 结果中如果有一个降序对，说明该两个 node 需交换；
     * 若有两个降序对，说明第一对的前一个 node 和第二对的后一个 node 需要交换。
     * 时间复杂度：O(N)
     * 空间复杂度：O(H)，H 为树的高度。
     */
    private TreeNode t1, t2, pre;
    public void recoverTree(TreeNode root) {
        inorder(root);
        int temp = t1.val;
        t1.val = t2.val;
        t2.val = temp;
    }

    private void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        // 判断是否在这里出错
        if (pre != null && pre.val > root.val) {
            if (t1 == null) {
                t1 = pre;
            }
            t2 = root;
        }
        // 更新前驱节点
        pre = root;
        inorder(root.right);
    }

    /**
     * 解法二：Morris 中序遍历
     * 时间复杂度：O(N)，Morris 遍历中每个节点会被访问两次，因此总时间复杂度为 O(2N)=O(N)。
     * 空间复杂度：O(1)。
     */
    public void recoverTree2(TreeNode root) {
        TreeNode x = null, y = null, pre = null;
        while (root != null) {
            if (root.left == null) {
                if (pre != null && pre.val > root.val) {
                    if (x == null) {
                        x = pre;
                    }
                    y = root;
                }
                pre = root;
                root = root.right;
            } else {
                // 查找前驱结点
                TreeNode pred = root.left;
                while (pred.right != null && pred.right != root) {
                    pred = pred.right;
                }
                // 第一次到达左子树的最右端 将前驱结点的右节点赋值为当前结点
                if (pred.right == null) {
                    pred.right = root;
                    root = root.left;
                } else {
                    // 第二次到达左子树的最右端 还原树结构
                    if (pre != null && pre.val > root.val) {
                        if (x == null) {
                            x = pre;
                        }
                        y = root;
                    }
                    pre = root;
                    pred.right = null;
                    root = root.right;
                }
            }
        }
        // 交换错误节点的值
        if (x != null) {
            int temp = x.val;
            x.val = y.val;
            y.val = temp;
        }
    }
}
