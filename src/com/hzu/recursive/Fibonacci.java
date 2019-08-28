package com.hzu.recursive;

/** 斐波那契数列 Fibonacci Sequence
 * @author zhanghuihong
 * @since 2019-08-11
 */
public class Fibonacci {

    public static void main(String[] args) {

        //斐波那契数列 1 1 2 3 5 8 13 21 34 .....
        System.out.println(fibonacci(30));
    }

    //获取第n项(当n较大时,性能极差)
    public static int fibonacci(int i) {
        //第一项和第二项都是1
        if (i == 1 || i == 2) {
            return 1;
        } else {
            return fibonacci(i - 1) + fibonacci(i-2);
        }
    }
}
