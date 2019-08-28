package com.hzu.recursive;

/**
 * @author zhanghuihong
 * @since 2019-08-11
 */
public class TestRecursive {

    public static void main(String[] args) {


        print(10);

    }

    public static void print(int i) {
        if (i > 0) {
            System.out.println(i);
            print(i-1);
        }
    }

}
