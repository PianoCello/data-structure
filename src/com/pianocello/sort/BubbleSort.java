package com.pianocello.sort;

import java.util.Arrays;

/** 排序算法之冒泡排序(交换排序)
 * @author PianoCello
 * @date 2019-08-11
 */
public class BubbleSort {


    public static void main(String[] args) {

        //待排序的数组
        int[] arr = {5,6,95,45,1,0,4,8,89,12,45,6,5,8,2356,458,5,45,5,45,5};
        System.out.println("排序前:"+Arrays.toString(arr));
        bubbleSort(arr);
        System.out.println("排序后:"+Arrays.toString(arr));
    }

    //冒泡排序
    public static void bubbleSort(int[] arr) {
        //控制比较轮次
        for (int i = 0; i < arr.length-1; i++) {
            //控制每轮比较的次数
            for (int j = 0; j < arr.length-1-i; j++) {
                if (arr[j]>arr[j+1]) {
                    //将大的数和小的数交换
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

}
