package com.hzu.sort;

import java.util.Arrays;

/**
 * 排序算法之基数排序(负数不可用)
 *
 * @author PianoCello
 * @date 2019-08-11
 */
public class RadixSort {

    public static void main(String[] args) {

        int[] arr = {501, 12, 0, 1231, 546, 123, 45, 56, 2, 456, 456};

        System.out.println("排序前:" + Arrays.toString(arr));
        radixSort(arr);
        System.out.println("排序后:" + Arrays.toString(arr));
    }

    //基数排序
    public static void radixSort(int[] arr) {
        int max = Integer.MIN_VALUE;

        //找出待排序数组中最大的数
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        //存放每次遍历的数的二维数组
        int[][] temp = new int[10][arr.length];

        //用于计数的数组(表示每个桶已存数的个数)
        int[] counts = new int[10];

        //最大数的位数(循环的次数)
        int maxLength = String.valueOf(max).length();
        //控制循环的次数,n为每次循环时取余的除数
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {

            //遍历取出每一个数，并存入临时二维数组中
            for (int j = 0; j < arr.length; j++) {
                //先确定每个数的余数
                int ys = arr[j] / n % 10;
                //把取出的数放进一个二维数组中
                temp[ys][counts[ys]] = arr[j];
                //计数位置加一
                counts[ys]++;
            }

            //存入数组下标
            int index = 0;
            //将临时数组中的数据取出来重新放进传入的数组中
            for (int y = 0; y < counts.length; y++) {
                //如果计数不等于0
                if (counts[y] != 0) {
                    for (int k = 0; k < counts[y]; k++) {
                        arr[index] = temp[y][k];
                        index++;
                    }
                    //取完数之后,要置空(方便后面遍历)
                    counts[y] = 0;
                }
            }

            /*
            //打印第一次结果
            if (i == 0) {
                for (int[] tem : temp) {
                    System.out.println(Arrays.toString(tem));
                }
                System.out.println(Arrays.toString(counts));
                break;
            }*/
        }
    }
}