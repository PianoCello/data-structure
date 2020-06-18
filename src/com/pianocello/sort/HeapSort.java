package com.pianocello.sort;

import java.util.Arrays;

/**
 * 排序算法之堆排序
 *
 * @author PianoCello
 * @date 2019-08-21
 */
public class HeapSort {

    public static void main(String[] args) {
        int[] arr = {45, 5, 63, 78, 98, 1, 2, 45, 123, 456, 453};
        System.out.println("排序前:"+Arrays.toString(arr));
        heapSort(arr);
        System.out.println("排序后:"+Arrays.toString(arr));
    }

    //堆排序
    public static void heapSort(int[] arr) {

        //开始位置,最后一个叶子节点的父节点
        int start = ((arr.length - 1) - 1) / 2;
        //调整为最大堆
        for (int i = start; i >= 0; i--) {
            maxSort(arr, arr.length, i);
        }
        //将数组中的第0个数与最后一个数交换
        for (int j = arr.length - 1; j > 0; j--) {

            int temp = arr[0];
            arr[0] = arr[j];
            arr[j] = temp;

            //再次调整为堆,除了第0个数之外,其他都是堆的状态
            maxSort(arr,j,0);
        }
    }

    /**
     * 构建最大堆
     * @param arr   数组
     * @param size  大小
     * @param index 开始位置
     */
    public static void maxSort(int[] arr, int size, int index) {
        int max = index;
        int leftNode = 2 * index + 1;
        int rightNode = 2 * index + 2;
        //对比左节点
        if (leftNode < size && arr[leftNode] > arr[max]) {
            max = leftNode;
        }
        //对比右节点
        if (rightNode < size && arr[rightNode] > arr[max]) {
            max = rightNode;
        }
        //交换位置
        if (max != index) {
            int temp = arr[index];
            arr[index] = arr[max];
            arr[max] = temp;
            maxSort(arr, size, max);
        }
    }
}
