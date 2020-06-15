package com.hzu.sort;

import java.util.Arrays;

/**
 * 排序算法之希尔排序(直接插入排序的升级)
 *
 * @author PianoCello
 * @date 2019-08-12
 */
public class ShellSort {

    public static void main(String[] args) {

        int[] arr = {3, 45, 1, 21, 7, 8, 78, 654, 4, 545, 45, 45, 1221, 12, 45, 45, 23, 23, 12, 89, 56, 3, 748};

        System.out.println("排序前:" + Arrays.toString(arr));
        shellSort(arr);
        System.out.println("排序后:" + Arrays.toString(arr));
    }

    //希尔排序(前确定步长,步长数实际就是分组数,然后对每个分组进行插入排序)
    public static void shellSort(int[] arr) {
        //定义步长step
        int step = arr.length;
        //遍历所有步长,首次遍历的步长=数组长度/2
        while (true) {
            step = step / 2;

            //遍历所有分组(以步长分组)
            for (int d = 0; d < step; d++) {

                //遍历本组中的所有元素,以步长为step的直接插入排序
                for (int i = d + step; i < arr.length; i = i + step) {
                    int temp = arr[i];
                    int j;
                    //循环对比之前已经排序好的数
                    for (j = i - step; j >= 0 && arr[j] > temp; j = j - step) {
                        arr[j + step] = arr[j];
                    }
                    //最后临时变量赋给不满足条件的下一个值
                    arr[j + step] = temp;
                }
            }
            //如果此时步长为1,说明已经是最后一次排序了,可以结束循环
            if (step == 1) {
                break;
            }
        }
    }

}

