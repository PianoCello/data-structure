package com.hzu.recursive;

/**
 * 汉诺塔问题
 *
 * @author PianoCello
 * @date 2019-08-11
 */
public class Hanoi {

    public static void main(String[] args) {

        hanoi(3, 'A', 'B', 'C');
        System.out.println("移动完成!");
    }

    /**
     * @param n    共有N个盘子
     * @param from 开始柱子
     * @param in   中间柱子
     * @param to   目标柱子
     */
    public static void hanoi(int n, char from, char in, char to) {
        if (n == 1) {
            System.out.println("第1个盘子从" + from + "移动到" + to);
        } else {
            //无论有多少个盘子,认为只有两个盘子,上面所有的是一个,最下面是一个

            //将上面所有盘子从开始位置移动到中间位置
            hanoi(n - 1, from, to, in); //递归执行

            //将最下面的盘子移动到目标位置
            System.out.println("第" + n + "个盘子从" + from + "移动到" + to);

            //将上面所有盘子从中间位置移动到目标位置
            hanoi(n - 1, in, from, to); //递归执行
        }
    }
}
