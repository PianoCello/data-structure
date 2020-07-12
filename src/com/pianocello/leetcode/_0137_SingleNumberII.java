package com.pianocello.leetcode;

/**
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。找出那个只出现了一次的元素。
 * 说明：
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * <p>
 * 示例 1:
 * 输入: [2,2,3,2]
 * 输出: 3
 * <p>
 * 示例 2:
 * 输入: [0,1,0,1,0,1,99]
 * 输出: 99
 *
 * @author PianoCello
 * @date 2020-07-09
 */
public class _0137_SingleNumberII {

    /**
     * 解法一：位操作
     * 遍历每个元素的每一个比特位，将相同位的 1 相加再对 3 取模（结果为 0 或 1 ），将结果全部组装起来形成的数就是返回值
     */
    public int singleNumber(int[] nums) {
        int res = 0;
        // int 类型占 32 位
        for (int i = 0; i < 32; i++) {
            int sum = 0;
            for (int num : nums) {
                //取第 i 位并累加
                sum += (num >> i) & 1;
            }
            //取余
            sum %= 3;
            if (sum == 1) {
                //先恢复到原来位的大小再或运算
                res = res | sum << i;
            }
        }
        return res;
    }

    /**
     * 设计一个算法当一个数出现三次就自动抵消
     */
    public int singleNumber2(int[] nums) {

        return 0;
    }

    // 版本3,leetcode大约1ms
    // 用 one 记录到当前处理的元素为止，二进制1出现“1次”（mod 3 之后的 1）的有哪些二进制位；
    // 用 two 记录到当前计算的变量为止，二进制1出现“2次”（mod 3 之后的 2）的有哪些二进制位。
    // 当 one 和 two 中的某一位同时为1时表示该二进制位上1出现了3次，此时需要清零。
    // 即用二进制模拟三 进制运算。最终 one 记录的是最终结果。
    public static int singleNumber3(int[] nums) {
        int one = 0, two = 0, three;
        for (int num : nums) {
            // two的相应的位等于1，表示该位出现2次
            two |= (one & num);
            // one的相应的位等于1，表示该位出现1次
            one ^= num;
            // three的相应的位等于1，表示该位出现3次
            three = (one & two);
            // 如果相应的位出现3次，则该位重置为0
            two &= ~three;
            one &= ~three;
        }
        return one;
    }

    // 版本4,leetcode大约1ms
    // 假设有一个数为x,那么则有如下规律：
    // 0 ^ x = x,
    // x ^ x = 0；
    // x & ~x = 0,
    // x & ~0 =x;
    // 一开始a = 0, b = 0;
    // x第一次出现后，a = (a ^ x) & ~b的结果为 a = x, b = (b ^ x) & ~a的结果为b = 0。
    // x第二次出现：a = (a ^ x) & ~b, a = (x ^ x) & ~0, a = 0;
    // b = (b ^ x) & ~a 化简， b = (0 ^ x) & ~0 ,b = x;
    // x第三次出现：a = (a ^ x) & ~b， a = (0 ^ x) & ~x ,a = 0;
    // b = (b ^ x) & ~a 化简， b = (x ^ x) & ~0 , b = 0;
    // 所以出现三次同一个数，a和b最终都变回了0.
    // 只出现一次的数，按照上面x第一次出现的规律可知a = x, b = 0;因此最后返回a.
    public static int singleNumber4(int[] nums) {
        int a = 0, b = 0;
        for (int x : nums) {
            a = (a ^ x) & ~b;
            b = (b ^ x) & ~a;
        }
        return a;
    }


    public static void main(String[] args) {

        System.out.println(1<<4);

    }

}
