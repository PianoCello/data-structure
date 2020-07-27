package com.pianocello.leetcode;

/**
 * 给定一个非空数组，数组中元素为 a0, a1, a2, … , an-1，其中 0 ≤ ai < 2^31 。
 * 找到 ai 和 aj 最大的异或 (XOR) 运算结果，其中 0 ≤ i,  j < n 。
 * 你能在 O(n) 的时间解决这个问题吗？
 * <p>
 * 示例:
 * 输入: [3, 10, 5, 25, 2, 8]
 * 输出: 28
 * 解释: 最大的结果是 5 ^ 25 = 28.
 *
 * @author PianoCello
 * @date 2020-07-26
 */
public class _0421_MaximumXOROfTwoNumbersInAnArray {

    private class TrieNode {
        // 当前节点代表的数
        public int val;
        // 当前比特位为 0, 延长左子节点
        public TrieNode left;
        // 当前比特位为 1, 延长右子节点
        public TrieNode right;
    }

    private TrieNode root = new TrieNode();

    private void insert(int num) {
        TrieNode cur = root;
        // 对目标数的从高位到低位拆分 存到前缀树中
        for (int i = 30; i >= 0; --i) {
            int bit = (num >> i) & 1;
            if (bit == 1) {
                if (cur.right == null) {
                    cur.right = new TrieNode();
                }
                cur = cur.right;
            } else {
                if (cur.left == null) {
                    cur.left = new TrieNode();
                }
                cur = cur.left;
            }
        }
        cur.val = num;
    }

    /**
     * 解法一：前缀树
     * 异或结果要最大，那么尽可能的保证高位是 1
     * 先将所有的元素按二进制插入到前缀树中
     * 再次遍历数组，在前缀树中找能使 异或 结果更大的路径
     */
    public int findMaximumXOR(int[] nums) {
        // 构建前缀树
        for (int num : nums) {
            insert(num);
        }
        // 在前缀树中找能使 异或 结果更大的路径
        int res = 0;
        for (int num : nums) {
            TrieNode cur = root;
            for (int i = 30; i >= 0; --i) {
                int bit = (num >> i) & 1;
                if (bit == 1) {
                    if (cur.left != null) {
                        cur = cur.left;
                    } else {
                        cur = cur.right;
                    }
                } else {
                    if (cur.right != null) {
                        cur = cur.right;
                    } else {
                        cur = cur.left;
                    }
                }
            }
            res = Math.max(res, num ^ cur.val);
        }
        return res;
    }

}
