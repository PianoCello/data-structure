package com.pianocello.leetcode;

/**
 *  f(x) 是 x! 末尾是 0 的数量。（回想一下 x! = 1 * 2 * 3 * ... * x，且 0! = 1）
 * 例如， f(3) = 0 ，因为 3! = 6 的末尾没有 0；而 f(11) = 2 ，因为 11!= 39916800末端有 2 个 0。
 * 给定 K，找出多少个非负整数x ，有 f(x) = K 的性质。
 *
 * 示例 1:
 * 输入:K = 0
 * 输出:5
 * 解释: 0!, 1!, 2!, 3!, and 4! 均符合 K = 0 的条件。
 *
 * 示例 2:
 * 输入:K = 5
 * 输出:0
 * 解释:没有匹配到这样的 x!，符合K = 5 的条件。
 *
 * 注意：
 * K是范围在 [0, 10^9] 的整数。
 *
 * @author PianoCello
 * @date 2020-09-30
 */
public class _0793_PreimageSizeOfFactorialZeroesFunction {

    /**
     * 解法一：答案要么是 0 , 要么是 5 .
     * 如果存在  f(x) = K , 答案是 5 , 否则答案是 0 .
     *
     * 高阶解法 按照数据规律求解
     * 阶乘
     * 1个 5      5   1
     * 2个 5 =    25  6   实际相当于 5个1个5，然后再加25自身是两个5，所以得到的是 5+1 = 6
     * 3个 5 =    125 31  相当于5个25，然后加自身125又多了一个5 所以的（5+1）*5+1=31
     * 按照这个规律，则后续公式可以 f(2)=f(1) * 5+1 f(1)=1 则 f(x+1) = f(x) * 5+1  x是5的次幂数
     */
    public int preimageSizeFZF(int K) {
        //确定阶乘值范围 最终的到的K < start
        int start = 1;
        while (start < K) {
            start = start * 5 + 1;
        }
        //确定范围后，执行精确查找
        while (start > 1) {
            //只有5以下阶乘才会出现start-1成立，其它情况不会存在，因为任何一个阶段分界值都会包含一个以上的5
            if (start - 1 == K) {
                //不存在的返回0
                return 0;
            }
            //逆推下一个阶梯值 从f(x+1) 推导出f(x)
            start = (start - 1) / 5;
            //获取剩余值，进行下一阶梯运算
            K %= start;
        }
        //只要存在，必然是5个
        return 5;
    }

}
