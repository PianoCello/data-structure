package com.pianocello.sort;

import java.util.Arrays;

/**
 * 排序算法之快速排序(交换排序)
 *
 * @author PianoCello
 * @date 2019-08-11
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {50, 12, 45, 62, 3, 45, 6, 52, 21, 0, 12, 78, 6, 56, 23, 23, 232, 26, 26, 12, 3, 232332};
        System.out.println("排序前:" + Arrays.toString(arr));
        quickSort(arr, 0, arr.length - 1);
        System.out.println("排序后:" + Arrays.toString(arr));
    }

    /**
     * 快速排序
     * 挑一个数(pivot)，然后把比它小的那一摊数放在它的左边，
     * 把比它大的那一摊数放在它的右边，
     * 然后再对这个数左右两摊数递归的执行快排过程，直到子数组只剩一个数为止。
     *
     * @param arr   要排序的数组
     * @param start 起始位置
     * @param end   结束位置
     */
    public static void quickSort(int[] arr, int start, int end) {
        if (start < end) {
            //将数组的第0个数作为基准数
            int pivot = arr[start];
            //记录需要排序的下标
            int low = start;
            int high = end;
            //循环找比标准数大的数和比标准数小的数
            while (low < high) {
                while (low < high && arr[high] >= pivot) {
                    //当数值>=标准数,high减一
                    high--;
                }
                //当数值比标准数小时,high位置的值替换low位置的值
                arr[low] = arr[high];
                while (low < high && arr[low] <= pivot) {
                    //当数值<=标准数,low加一
                    low++;
                }
                //当数值比标准数大时,low位置的值替换high位置的值
                arr[high] = arr[low];
            }
            //将pivot替换low和high相同的位置的数组
            arr[low] = pivot;
            //递归调用,处理所有比当前基准数小的数
            quickSort(arr, start, low - 1);
            //递归调用,处理所有比当前基准数大的数
            quickSort(arr, low + 1, end);
        }
    }

}
