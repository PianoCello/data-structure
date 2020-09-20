package com.pianocello.leetcode;

/**
 * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。
 * 这个地区只有一个入口，我们称之为“根”。
 * 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。
 * 一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
 * 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
 *
 * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
 *
 * 示例 1:
 * 输入: [3,4,5,1,3,null,1]
 *      3
 *     / \
 *    4   5
 *   / \   \
 *  1   3   1
 *
 * 输出: 9
 * 解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
 *
 * @author PianoCello
 * @date 2020-09-19
 */
public class _0337_HouseRobberIII {

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 解法一：后序遍历
     *
     */
    public int rob(TreeNode root) {
        int[] res = help(root);
        return Math.max(res[0], res[1]);
    }

    /**
     * int[2]
     * 第一个元素为当前结点被选中时的最大权值
     * 第二个元素为当前结点不被选中时的最大权值
     */
    private int[] help(TreeNode root) {
        // 结束条件
        if (root == null)
            return new int[2];
        // 递归遍历左子树
        int[] left = help(root.left);
        // 递归遍历右子树
        int[] right = help(root.right);
        // 被选中时, 权值=当前结点值+左右节点不被选中的权值
        int selected = root.val + left[1] + right[1];
        // 不被选中时, 权值=左节点[被或不被]选中的最大值 + 右节点[被或不被]选中的最大值
        int unSelected = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        return new int[]{selected, unSelected};
    }

}
