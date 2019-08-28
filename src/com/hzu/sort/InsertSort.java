package com.hzu.sort;

import java.util.Arrays;

/**
 * 排序算法之直接插入排序
 *
 * @author zhanghuihong
 * @since 2019-08-12
 */
public class InsertSort {

    public static void main(String[] args) {

        int[] arr = {3, 45, 1, 21, 7, 8, 78, 654, 4, 545, 45, 45, 1221, 12, 45, 45, 23, 23, 12, 89, 56, 3, 748};

        System.out.println("排序前:" + Arrays.toString(arr));

        insertSort(arr);

        System.out.println("排序后:" + Arrays.toString(arr));
    }

    //直接插入排序
    public static void insertSort(int[] arr) {
        //第一个数不需要排序
        for (int i = 1; i < arr.length; i++) {
            //后一个数比前一个数小,开始交换位置
            if (arr[i] < arr[i - 1]) {
                //取出当前i位置的值
                int temp = arr[i];
                //循环对比之前已经排序好的数
                int j;
                for ( j = i - 1; j >= 0 && temp < arr[j]; j--) {
                    arr[j + 1] = arr[j];
                }
                //最后将i位置的临时变量赋给不满足条件的后一个值
                arr[j+1] = temp;
            }
        }
    }

}

