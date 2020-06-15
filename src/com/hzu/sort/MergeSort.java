package com.hzu.sort;

import java.util.Arrays;

/**
 * 排序算法之归并排序
 *
 * @author PianoCello
 * @date 2019-08-12
 */
public class MergeSort {

    public static void main(String[] args) {

        int[] arr = {3,2,45,45,1,12,1,5,4,54,78,4,123,45,15,4,5652,15,1456,53,123,315};

        System.out.println("排序前:" + Arrays.toString(arr));

        mergeSort(arr, 0, arr.length - 1);

        System.out.println("排序后:" + Arrays.toString(arr));
    }

    //归并排序,递归调用
    public static void mergeSort(int[] arr,int low ,int high) {

        if (low < high) {
            //确定中间位置
            int middle = (low + high) / 2;

            //处理左边
            mergeSort(arr, low, middle);
            //处理右边
            mergeSort(arr, middle + 1, high);

            //合并两边的数据
            merge(arr, low, middle, high);
        }
    }

    /**
     * 归并排序
     * @param arr 需要排序的数组
     * @param low 排序开始位置
     * @param middle 逻辑分割位置
     * @param high 排序结束位置
     */
    public static void merge(int[] arr,int low ,int middle,int high) {
        //临时数组,保存排好序的数
        int[] temp = new int[high - low + 1];
        //左边遍历开始位置
        int i = low;
        //右边遍历开始位置
        int j = middle + 1;
        //临时数组的索引
        int index = 0;
        //遍历两边的数组
        while (i <= middle && j <= high) {
            //左边的数比较小,将数放入临时数组
            if (arr[i] <= arr[j]) {
                temp[index] = arr[i];
                i++;
            } else {
                //右边的数比较小,将数放入临时数组
                temp[index] = arr[j];
                j++;
            }
            //临时数组的索引向后移一位
            index++;
        }

        //处理左边多余的数据
        while (i<=middle) {
            temp[index] = arr[i];
            index++;
            i++;
        }
        //处理右边多余的数据
        while (j<=high) {
            temp[index] = arr[j];
            index++;
            j++;
        }

        //将临时数组的值重新放入传入的数组中
        for (int k = 0; k < temp.length; k++) {
            arr[low + k] = temp[k];
        }

    }

}
