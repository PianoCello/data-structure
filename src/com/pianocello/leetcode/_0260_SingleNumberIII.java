package com.pianocello.leetcode;

import java.util.Arrays;

/**
 * 给定一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。
 * <p>
 * 示例 :
 * 输入: [1,2,1,3,2,5]
 * 输出: [3,5]
 * <p>
 * 注意：
 * 结果输出的顺序并不重要，对于上面的例子， [5, 3] 也是正确答案。
 * 你的算法应该具有线性时间复杂度。你能否仅使用常数空间复杂度来实现？
 *
 * @author PianoCello
 * @date 2020-07-09
 */
public class _0260_SingleNumberIII {

    /**
     * 解法一：分组异或法
     */
    public static int[] singleNumber(int[] nums) {

        //先求出这两个数的异或的结果
        int xorRes = 0;
        for (int num : nums) {
            xorRes ^= num;
        }
        //根据异或结果得知哪一位不同，然后根据这一位将数组分成两组
        //这两个不同的数一定在不同的数组中，对两个不同的数组进行异或可以得到答案
        int temp = 1;  // 其实 temp = xorRes & (-xorRes) 就可以了
        while ((xorRes & 1) != 1) {
            xorRes >>= 1;
            temp <<= 1;
        }
        int x = 0, y = 0;
        for (int num : nums) {
            //根据这一位是否为 0 分组
            if ((num & temp) == 0) {
                x ^= num;
            } else {
                y ^= num;
            }
        }
        return new int[]{x, y};
    }

    /**
     * 解法二：对解法一的代码简化
     */
    public int[] singleNumber2(int[] nums) {
        int diff = 0, x = 0;
        for (int num : nums) {
            diff ^= num;
        }
        int oneBit = diff & -diff;
        for (int num : nums) {
            if ((num & oneBit) == 0)
                x ^= num;
        }
        return new int[]{x, x ^ diff};
    }

    public static void main(String[] args) {
        int[] ints = {1, 2, 1, 3, 2, 5};
        int[] res = singleNumber(ints);
        System.out.println(Arrays.toString(res));
    }

}
