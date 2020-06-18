package com.pianocello.sort;

import java.util.Arrays;

/**
 * 排序算法之选择排序
 *
 * @author PianoCello
 * @date 2019-08-12
 */
public class SelectSort {

    public static void main(String[] args) {

        int[] arr = {3, 45, 1, 21, 7, 8, 78, 654, 4, 545, 45, 45, 1221, 12, 45, 45, 23, 23, 12, 89, 56, 3, 748};

        System.out.println("排序前:" + Arrays.toString(arr));

        selectSort(arr);

        System.out.println("排序后:" + Arrays.toString(arr));
    }

    //选择排序(每趟选择最小的数,放到最前面)
    public static void selectSort(int[] arr) {
        //遍历所有元素
        for (int i = 0; i < arr.length; i++) {
            //临时最小下标
            int minIndex = i;
            //把当前遍历的数和后面的数依次进行比较,并记录最小的数的下标
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[minIndex] > arr[j]) {
                    minIndex = j;
                }
            }
            //将最小值提前
            if (i != minIndex) {
                int temp = arr[minIndex];
                arr[minIndex] = arr[i];
                arr[i] = temp;
            }
        }
    }

}

