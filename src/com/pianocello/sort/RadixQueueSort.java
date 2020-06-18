package com.pianocello.sort;

import com.pianocello.queue.Queue;

import java.util.Arrays;

/**
 * 排序算法之基数排序之队列实现(负数不可用)
 *
 * @author PianoCello
 * @date 2019-08-11
 */
public class RadixQueueSort {

    public static void main(String[] args) {

        int[] arr = {501, 12, 0, 1231, 546, 123, 45, 56, 2, 456, 456};

        System.out.println("排序前:" + Arrays.toString(arr));
        radixQueueSort(arr);
        System.out.println("排序后:" + Arrays.toString(arr));
    }

    //基数排序之队列实现
    public static void radixQueueSort(int[] arr) {
        int max = Integer.MIN_VALUE;

        //找出待排序数组中最大的数
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        //创建队列数组,用于保存遍历的数据
        Queue[] temp = new Queue[10];
        //初始化数组里面的队列,避免空指针异常
        for (int i = 0; i < temp.length; i++) {
            temp[i] = new Queue();
        }

        //最大数的位数(循环的次数)
        int maxLength = String.valueOf(max).length();
        //控制循环的次数,n为每次循环时取余的除数
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {

            //遍历取出每一个数，并存入临时二维数组中
            for (int j = 0; j < arr.length; j++) {
                //先确定每个数的余数
                int ys = arr[j] / n % 10;

                //把取出的数放进一个队列中
                temp[ys].add(arr[j]);
            }

            //存入数组下标
            int index = 0;
            //将临时队列中的数据取出来重新放进传入的数组中
            for (int y = 0; y < temp.length; y++) {
                //当队列不为空
                while (!temp[y].isEmpty()) {
                    arr[index] = temp[y].poll();
                    index++;
                }
            }

        }
    }
}