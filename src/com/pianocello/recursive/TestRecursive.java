package com.pianocello.recursive;

/**
 * @author PianoCello
 * @date 2019-08-11
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
