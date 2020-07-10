package com.pianocello.leetcode;

/**
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * 说明：
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * <p>
 * 示例 1:
 * 输入: [2,2,1]
 * 输出: 1
 * <p>
 * 示例 2:
 * 输入: [4,1,2,1,2]
 * 输出: 4
 *
 * @author PianoCello
 * @date 2020-07-09
 */
public class _0136_SingleNumber {

    /**
     * 交换律：a ^ b ^ c <=> a ^ c ^ b
     * 任何数和 0 异或为任何数 0 ^ n => n
     * 相同的数异或为 0   n ^ n => 0
     */
    public static int singleNumber(int[] nums) {
        int res = 0;
        for (int i : nums) {
            res ^= i;
        }
        return res;
    }

    public static void main(String[] args) {

        int[] ints = {4, 1, 2, 1, 2};
        int i = singleNumber(ints);
        System.out.println(i);
    }


}
