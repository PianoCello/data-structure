package com.hzu.array;

import java.util.Arrays;

/**
 * 数据结构之数组
 * 用户传入下标的地方都要检查下标参数是否符合要求
 * @author zhanghuihong
 * @since 2019-08-09
 */
public class Array {

    private int[] elements;

    //默认构造器,初始化的数组长度为0
    public Array() {
        this.elements = new int[0];
    }

    //获取数组长度
    public int size() {
        return elements.length;
    }

    //获取元素
    public int get(int index) {
        //检查下标是否越界
        if (index < 0 || index > elements.length - 1) {
            throw new RuntimeException("数组下标越界");
        }
        return elements[index];
    }

    //替换元素
    public void set(int index, int element) {
        //检查下标是否越界
        if (index < 0 || index > elements.length - 1) {
            throw new RuntimeException("数组下标越界");
        }
        elements[index] = element;
    }

    //在数组末尾一个添加元素
    public void add(int element) {
        //创建新的数组
        int[] arr = new int[elements.length + 1];

        //将旧的数组的值赋给新的数组
        for (int i = 0; i < elements.length; i++) {
            arr[i] = elements[i];
        }
        //添加元素
        arr[elements.length] = element;
        //使用新数组替换旧数组
        elements = arr;
    }

    //根据索引添加元素
    public void insert(int index, int element) {
        //检查下标是否越界
        if (index < 0 || index > elements.length) {
            throw new RuntimeException("数组下标越界");
        }
        //创建新的数组
        int[] newArr = new int[elements.length + 1];
        //复制原来的元素
        for (int i = 0; i < elements.length; i++) {
            if (index > i) {
                newArr[i] = elements[i];
            } else {
                newArr[i + 1] = elements[i];
            }
        }
        //加入新元素
        newArr[index] = element;
        //将新数组替换旧数组
        elements = newArr;
    }

    //根据索引删除一个元素
    public void remove(int index) {
        //检查下标是否越界
        if (index < 0 || index > elements.length - 1) {
            throw new RuntimeException("数组下标越界");
        }
        //创建新数组
        int[] newArr = new int[elements.length - 1];
        for (int i = 0; i < newArr.length; i++) {
            if (index > i) {
                //index大于i,直接复制
                newArr[i] = elements[i];
            } else {
                //index小于i,索引加一后复制
                newArr[i] = elements[i + 1];
            }
        }
        //新数组替换旧数组
        elements = newArr;
    }

    //线性查找(顺序查找)
    public int search(int target) {
        for (int i = 0; i < elements.length; i++) {
            if (elements[i] == target) {
                return i;
            }
        }
        return -1;
    }

    //二分法查找(折半查找)--数组必须是已经排好序的
    public int binarySearch(int target) {

        int begin = 0;
        int end = elements.length - 1;

        //当起始小于等于终止
        while (begin <= end) {
            int mid = (begin + end) / 2;
            System.out.println("begin:"+begin+"  end:"+end+"  mid:"+mid);
            if (elements[mid] == target) {
                //如果目标等于中间位置的值,返回下标
                return mid;
            } else if (elements[mid] > target) {
                end = mid - 1;
            } else {
                begin = mid + 1;
            }
        }

        return -1;
    }

    //打印所有元素
    @Override
    public String toString() {
        return Arrays.toString(elements);
    }

}
